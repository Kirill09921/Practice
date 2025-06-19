package edu.grsu.practice.practice.service.impl;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.mapper.BookingMapper;
import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.repository.BookingRepository;
import edu.grsu.practice.practice.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public BookingDto addBooking(BookingDto bookingDto) {
        log.info("adding booking: {}", bookingDto);
        Booking booking = bookingMapper.toEntity(bookingDto);
        bookingRepository.save(booking);
        return bookingMapper.toDto(booking);
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
}
