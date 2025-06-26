package edu.grsu.practice.practice.dto;

import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.model.Flight;
import edu.grsu.practice.practice.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class TicketDto {
    private UUID id;
    private int price;
    private byte[] flightDetail;
    private UserDto user;
    private FlightDto flight;
    private BookingDto booking;
    private UUID bookingId;
}
