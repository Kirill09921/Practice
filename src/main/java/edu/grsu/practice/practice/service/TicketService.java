package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.TicketDto;
import edu.grsu.practice.practice.dto.TicketView;
import edu.grsu.practice.practice.model.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    public TicketDto addTicket(TicketDto ticketDto);
    public Ticket addTicket(Ticket ticket);
    public List<TicketDto> getAllTickets();
    public TicketDto getTicket(UUID ticketId);
    public boolean deleteTicket(UUID ticketId);
    public TicketDto updateTicket(TicketDto ticketDto);
    public TicketView  getTicketView(UUID ticketId);
    public List<TicketView> getAllTicketViews();
    public byte[] generatePdf(UUID id);
    public ResponseEntity<byte[]> viewPdf(UUID id);
}
