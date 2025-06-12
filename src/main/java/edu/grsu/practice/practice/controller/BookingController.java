package edu.grsu.practice.practice.controller;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private BookingService bookingService;

    @GetMapping("/{id}")
    public BookingDto findBooking(@PathVariable UUID id) {
        return bookingService.getBooking(id);
    }

    @GetMapping("/")
    public List<BookingDto> findAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/")
    public BookingDto createBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.addBooking(bookingDto);
    }

    @DeleteMapping("/")
    public boolean deleteBooking(@RequestBody UUID bookingId) {
        return bookingService.deleteBooking(bookingId);
    }

    @PutMapping("/")
    public BookingDto updateBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.updateBooking(bookingDto);
    }
}
