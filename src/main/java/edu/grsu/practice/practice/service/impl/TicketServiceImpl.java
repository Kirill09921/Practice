package edu.grsu.practice.practice.service.impl;

import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.dto.TicketDto;
import edu.grsu.practice.practice.dto.TicketView;
import edu.grsu.practice.practice.mapper.BookingMapper;
import edu.grsu.practice.practice.mapper.TicketMapper;
import edu.grsu.practice.practice.mapper.UserMapper;
import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.model.Flight;
import edu.grsu.practice.practice.model.Ticket;
import edu.grsu.practice.practice.model.User;
import edu.grsu.practice.practice.repository.TicketRepository;
import edu.grsu.practice.practice.service.BookingService;
import edu.grsu.practice.practice.service.FlightService;
import edu.grsu.practice.practice.service.TicketService;
import edu.grsu.practice.practice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    public TicketMapper ticketMapper;
    public TicketRepository ticketRepository;

    @Override
    public TicketDto addTicket(TicketDto ticketDto) {
        log.info("adding ticket: {}", ticketDto);
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        log.info("adding ticket: {}", ticket);
        ticketRepository.save(ticket);
        return ticket;
    }


    @Override
    public List<TicketDto> getAllTickets() {
        log.info("getting all tickets");
        List<Ticket> tickets = ticketRepository.findAll();

        return ticketMapper.toDto(tickets);
    }

    @Override
    public TicketDto getTicket(UUID ticketId) {
        log.info("getting ticket: {}", ticketId);
        Optional<Ticket>  ticketOptional = ticketRepository.findById(ticketId);
        Ticket ticket = ticketOptional.orElseThrow();
        return ticketMapper.toDto(ticket);
    }

    @Override
    public boolean deleteTicket(UUID ticketId) {
        log.info("deleting ticket: {}", ticketId);
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        Ticket ticket = optionalTicket.orElseThrow();
        ticket.getBooking().setTicket(null);
        ticketRepository.deleteById(ticketId);
        return true;
    }

    @Override
    public TicketDto updateTicket(TicketDto ticketDto) {
        UUID ticketId =  ticketDto.getId();
        log.info("updating ticket: {}", ticketId);
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        Ticket existingTicket = ticketOptional.orElseThrow();
        existingTicket = ticketMapper.partialUpdate(ticketDto, existingTicket);
        ticketRepository.save(existingTicket);
        return ticketMapper.toDto(existingTicket);
    }

    @Override
    public TicketView getTicketView(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        Flight flight = ticket.getFlight();
        Booking booking = ticket.getBooking();

        return TicketView.builder()
                .ticket(ticket)
                .plane(flight.getPlane().getModel())
                .departureLocation(booking.getDepartureLocation())
                .departureTime(booking.getDepartureTime())
                .destinationLocation(booking.getArrivalLocation())
                .destinationTime(booking.getArrivalTime())
                .build();
    }

    @Override
    public List<TicketView> getAllTicketViews() {
        log.info("getting all ticket views");
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(ticket -> {
                    Flight flight = ticket.getFlight();
                    Booking booking = ticket.getBooking();

                    return TicketView.builder()
                            .ticket(ticket)
                            .plane(flight != null ? flight.getPlane().getModel() : null)
                            .departureLocation(booking.getDepartureLocation())
                            .departureTime(booking.getDepartureTime())
                            .destinationLocation(booking.getArrivalLocation())
                            .destinationTime(booking.getArrivalTime())
                            .build();
                })
                .collect(Collectors.toList());
    }

}
