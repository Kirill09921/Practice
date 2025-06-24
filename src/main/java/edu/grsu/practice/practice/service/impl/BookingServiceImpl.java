package edu.grsu.practice.practice.service.impl;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.dto.TicketDto;
import edu.grsu.practice.practice.dto.UserDto;
import edu.grsu.practice.practice.integration.price.PriceServiceClient;
import edu.grsu.practice.practice.mapper.BookingMapper;
import edu.grsu.practice.practice.mapper.FlightMapper;
import edu.grsu.practice.practice.mapper.TicketMapper;
import edu.grsu.practice.practice.mapper.UserMapper;
import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.model.Flight;
import edu.grsu.practice.practice.model.Price;
import edu.grsu.practice.practice.repository.BookingRepository;
import edu.grsu.practice.practice.service.BookingService;
import edu.grsu.practice.practice.service.FlightService;
import edu.grsu.practice.practice.service.TicketService;
import edu.grsu.practice.practice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    public BookingMapper bookingMapper;
    public BookingRepository bookingRepository;
    public UserService userService;
    public UserMapper userMapper;
    public FlightService flightService;
    public FlightMapper flightMapper;
    public TicketService ticketService;
    public TicketMapper ticketMapper;
    public PriceServiceClient priceServiceClient;


    @Override
    public BookingDto addBooking(BookingDto bookingDto) {
        log.info("adding booking: {}", bookingDto);

        //save pure booking
        Booking booking = bookingMapper.toEntity(bookingDto);
        Booking saved = bookingRepository.save(booking);
        bookingDto.setId(saved.getId());
        //attach user
        attachUser(booking, bookingDto.getUserId());
        //create and attach ticket
        Price price = priceServiceClient.getPrice(booking.getDepartureLocation(), booking.getArrivalLocation());
        UserDto userDto = userService.getUser(bookingDto.getUserId());
        FlightDto flightDto = flightService.getFlight(bookingDto.getFlightId());
        TicketDto ticketDto = TicketDto.builder()
                .booking(bookingDto)
                .user(userDto)
                .flight(flightDto)
                .flightDetail("".getBytes(StandardCharsets.UTF_8))
                .price(price.getPrice())
                .build();

        var ticket = ticketService.addTicket(ticketDto);

        //link ticket to booking
        booking.setTicket(ticketMapper.toEntity(ticket));
        bookingRepository.save(booking);


        return bookingMapper.toDto(booking);
    }

    private void attachUser(Booking booking, UUID userId) {
        booking.setUser(userService.getUserEntity(userId));
        bookingRepository.save(booking);
    }


    @Override
    public List<BookingDto> getAllBookings() {
        log.info("getting all bookings");
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.toDto(bookings);
    }

    @Override
    public BookingDto getBooking(UUID bookingId) {
        log.info("getting booking: {}", bookingId);
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        Booking booking = bookingOptional.orElseThrow();
        return bookingMapper.toDto(booking);

    }

    @Override
    public boolean deleteBooking(UUID bookingId) {
        log.info("deleting booking: {}", bookingId);
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        Booking booking = bookingOptional.orElseThrow();
        bookingRepository.deleteById(bookingId);
        return true;
    }

    @Override
    public BookingDto updateBooking(BookingDto bookingDto) {
        UUID bookingId = bookingDto.getId();
        log.info("updating booking: {}", bookingId);
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        Booking existingBooking = bookingOptional.orElseThrow();
        existingBooking = bookingMapper.partialUpdate(bookingDto, existingBooking);
        bookingRepository.save(existingBooking);
        return bookingMapper.toDto(existingBooking);
    }

    @Override
    public UUID getUserId(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        return booking.getUser().getId();
    }
}
