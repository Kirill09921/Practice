package edu.grsu.practice.practice.integration.mail;

import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class MailScheduler {

    private final BookingRepository bookingRepository;
    private final MailService mailService;

    @Scheduled(cron = "0 0 */2 * * *")
    public void notifyMail() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime windowStart = now.plusHours(23);
        LocalDateTime windowEnd = now.plusHours(25);

        List<Booking> bookings = bookingRepository.findBookings(windowStart, windowEnd);
        bookings.forEach(mailService::sendBooking);
    }
}
