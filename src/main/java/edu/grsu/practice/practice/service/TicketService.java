package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.TicketDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    public TicketDto addTicket(TicketDto ticketDto);
    public List<TicketDto> getAllTickets();
    public TicketDto getTicket(UUID ticketId);
    public boolean deleteTicket(UUID ticketId);
    public TicketDto updateTicket(TicketDto ticketDto);
}
