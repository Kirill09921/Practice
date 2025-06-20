package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.BookingDto;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    public BookingDto addBooking(BookingDto bookingDto);
    public List<BookingDto> getAllBookings();
    public BookingDto getBooking(UUID bookingId);
    public boolean deleteBooking(UUID bookingId);
    public BookingDto updateBooking(BookingDto bookingDto);
    public UUID getUserId(UUID bookingId);
}
