package edu.grsu.practice.practice.integration.mail;

import edu.grsu.practice.practice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestMailController {

    private final BookingRepository bookingRepository;
    private final MailService mailService;

    @GetMapping("/test-mail/{id}")
    public ResponseEntity<String> sendTestMail(@PathVariable UUID id) {
        return bookingRepository.findWithUserAndTicketById(id)
                .map(booking -> {
                    mailService.sendBooking(booking);
                    return ResponseEntity.ok("Письмо отправляется...");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
