package edu.grsu.practice.practice.integration.mail;

import edu.grsu.practice.practice.model.Booking;
import jakarta.mail.Authenticator;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Base64;
import java.util.Properties;

@Slf4j
@Service
public class MailService {

    @Value("${mail.username}")
    private String senderEmail;
    @Value("${mail.password}")
    private String senderPassword;

    @Async
    public void sendBooking(Booking booking) {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(booking.getUser().getEmail()));
            message.setSubject("Ваше бронирование: " +
                    booking.getDepartureLocation() + " → " +
                    booking.getArrivalLocation());

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(MailContentBuilder.buildMessage(booking), "utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setFileName("FlightDetails.pdf");
            attachmentPart.setContent(Base64.getDecoder().decode(booking.getTicket().getFlightDetail()), "application/pdf");
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            log.info("send: {}", booking.getUser().getEmail());

        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
        }
    }

}
