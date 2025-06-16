package edu.grsu.practice.practice.dto;

import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.model.Ticket;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String login;
    private Set<Ticket> tickets;
    private Set<Booking> bookings;

}
