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
        var ticket = ticketService.getTicketView(id);
        model.addAttribute("ticketView", ticket);
        return "ticket/ticket";
    }

    @GetMapping("/all")
    public String findAllTickets(Model model) {
        var tickets = ticketService.getAllTicketViews();
        model.addAttribute("ticketViews", tickets);
        return "ticket/tickets";
    }

    @PostMapping("/create")
    public String createTicket(@ModelAttribute TicketDto ticketDto) {
        var ticket = ticketService.addTicket(ticketDto);
        return "redirect:/ticket/all";
    }

    @GetMapping("/create")
    public String createTicket(Model model) {
        TicketDto dto = TicketDto.builder().id(UUID.randomUUID()).build();
        model.addAttribute("ticket", dto);
        return "ticket/create";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTicket(@PathVariable UUID id, Model model) {
        var isDeleted = ticketService.deleteTicket(id);
        model.addAttribute("isDeleted", isDeleted);
        return "redirect:/ticket/all";
    }

    @PutMapping("/update/{id}")
    public String updateTicket(@PathVariable UUID id, @ModelAttribute TicketDto ticketDto) {
        ticketService.updateTicket(ticketDto);
        return "redirect:/ticket/{id}";
    }

    @GetMapping("/update/{id}")
    public String updateTicket(@PathVariable UUID id, Model model) {
        TicketDto ticket = ticketService.getTicket(id);
        model.addAttribute("ticket", ticket);
        return "ticket/update";
    }
}
