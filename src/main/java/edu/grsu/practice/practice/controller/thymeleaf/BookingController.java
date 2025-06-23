package edu.grsu.practice.practice.controller.thymeleaf;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.service.BookingService;
import edu.grsu.practice.practice.service.FlightService;
import edu.grsu.practice.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;
    private UserService userService;
    private FlightService flightService;

    @GetMapping("/{id}")
    public String findBooking(@PathVariable UUID id, Model model) {
        var booking = bookingService.getBooking(id);
        model.addAttribute("booking", booking);
        return "booking/booking";
    }

    @GetMapping("/all")
    public String findAllBookings(Model model) {
        var bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "booking/bookings";
    }

    @PostMapping("/create")
    public String createBooking(@ModelAttribute BookingDto bookingDto) {
        var booking = bookingService.addBooking(bookingDto);
        return "redirect:/booking/all";
    }

    @GetMapping("/create")
    public String createBooking(Model model) {
        BookingDto dto = BookingDto.builder().id(UUID.randomUUID()).build();
        var users = userService.getAllUsers();
        var flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);
        model.addAttribute("users", users);
        model.addAttribute("booking", dto);
        return "booking/create";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable UUID id, Model model) {
        var isDeleted = bookingService.deleteBooking(id);
        model.addAttribute("isDeleted", isDeleted);
        return "redirect:/booking/all";
    }

    @PutMapping("/update/{id}")
    public String updateBooking(@PathVariable UUID id, @ModelAttribute BookingDto bookingDto) {
        bookingService.updateBooking(bookingDto);
        return "redirect:/booking/{id}";
    }
    @GetMapping("/update/{id}")
    public String updateBooking(@PathVariable UUID id, Model model) {
        BookingDto booking = bookingService.getBooking(id);
        model.addAttribute("booking", booking);
        return "booking/update";
    }

}
