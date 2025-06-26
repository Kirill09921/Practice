package edu.grsu.practice.practice.integration.mail;

import edu.grsu.practice.practice.model.Booking;

public class MailContentBuilder {
    public static String buildMessage(Booking booking) {
        String sb = "Здравствуйте, " + booking.getUser().getUsername() + "!\n\n" +
                "Ваше бронирование:\n" +
                "Откуда: " + booking.getDepartureLocation() + "\n" +
                "Куда: " + booking.getArrivalLocation() + "\n" +
                "Время отправления: " + booking.getDepartureTime() + "\n" +
                "Время прибытия: " + booking.getArrivalTime() + "\n" +
                "Цена билета: " + booking.getTicket().getPrice() + " BYN\n";
        return sb;
    }
}

