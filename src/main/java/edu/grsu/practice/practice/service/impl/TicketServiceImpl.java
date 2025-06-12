package edu.grsu.practice.practice.service.impl;

import edu.grsu.practice.practice.dto.TicketDto;
import edu.grsu.practice.practice.mapper.TicketMapper;
import edu.grsu.practice.practice.model.Ticket;
import edu.grsu.practice.practice.repository.TicketRepository;
import edu.grsu.practice.practice.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    public TicketMapper ticketMapper;
    public TicketRepository ticketRepository;

    @Override
    public void addTicket(TicketDto ticketDto) {
        log.info("adding ticket: {}", ticketDto);
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticketRepository.save(ticket);
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
    public void deleteTicket(UUID ticketId) {
        log.info("deleting ticket: {}", ticketId);
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        Ticket ticket = ticketOptional.orElseThrow();
        ticketRepository.delete(ticket);
    }

    @Override
    public void updateTicket(UUID ticketId, TicketDto ticketDto) {
        log.info("updating ticket: {}", ticketId);
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        Ticket existingTicket = ticketOptional.orElseThrow();
        existingTicket = ticketMapper.partialUpdate(ticketDto, existingTicket);
        ticketRepository.save(existingTicket);
    }
}
