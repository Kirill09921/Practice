package edu.grsu.practice.practice.controller;

import edu.grsu.practice.practice.dto.TicketDto;
import edu.grsu.practice.practice.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private TicketService ticketService;

    @GetMapping("/{id}")
    public TicketDto findTicket(@PathVariable UUID id) {
        return ticketService.getTicket(id);
    }

    @GetMapping("/")
    public List<TicketDto> findAllTickets() {
        return ticketService.getAllTickets();
    }

    @PostMapping("/")
    public TicketDto createTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.addTicket(ticketDto);
    }

    @DeleteMapping("/")
    public boolean deleteTicket(@RequestBody UUID ticketId) {
        return ticketService.deleteTicket(ticketId);
    }

    @PutMapping("/")
    public TicketDto updateTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.updateTicket(ticketDto);
    }
}
