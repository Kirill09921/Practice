package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.BookingDto;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    public void addBooking(BookingDto bookingDto);
    public List<BookingDto> getAllBookings();
    public BookingDto getBooking(UUID bookingId);
    public void deleteBooking(UUID bookingId);
    public void updateBooking(UUID bookingId, BookingDto bookingDto);
}
