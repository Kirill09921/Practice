package edu.grsu.practice.practice.controller.thymeleaf;

import edu.grsu.practice.practice.dto.TicketDto;
import edu.grsu.practice.practice.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    @GetMapping("/{id}")
    public String findTicket(@PathVariable UUID id, Model model) {
        var ticket = ticketService.getTicket(id);
        model.addAttribute("ticket", ticket);
        return "ticket/ticket";
    }

    @GetMapping("/all")
    public String findAllTickets(Model model) {
        var tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "ticket/tickets";
    }

    @PostMapping("/create")
    public String createTicket(@RequestBody TicketDto ticketDto, Model model) {
        var ticket = ticketService.addTicket(ticketDto);
        model.addAttribute("ticket", ticket);
        return "createTicket";
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable UUID id, Model model) {
        var isDeleted = ticketService.deleteTicket(id);
        model.addAttribute("isDeleted", isDeleted);
        return "deleteTicket";
    }

    @PutMapping("/{id}")
    public String updateTicket(@PathVariable UUID id, @RequestBody TicketDto ticketDto, Model model) {
        var updatedTicket = ticketService.updateTicket(ticketDto);
        model.addAttribute("ticket", updatedTicket);
        return "updateTicket";
    }
}
