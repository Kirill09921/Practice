package edu.grsu.practice.practice.dto;

import edu.grsu.practice.practice.model.Ticket;
import edu.grsu.practice.practice.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class BookingDto {
    private UUID id;
    private LocalTime time;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String departureLocation;
    private String arrivalLocation;
    private User user;
    private Ticket ticket;
}
