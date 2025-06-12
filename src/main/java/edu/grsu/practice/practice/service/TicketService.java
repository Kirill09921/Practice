package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.TicketDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    public void addTicket(TicketDto ticketDto);
    public List<TicketDto> getAllTickets();
    public TicketDto getTicket(UUID ticketId);
    public void deleteTicket(UUID ticketId);
    public void updateTicket(UUID ticketId, TicketDto ticketDto);
}
