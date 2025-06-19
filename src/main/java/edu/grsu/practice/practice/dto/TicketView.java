package edu.grsu.practice.practice.dto;

import edu.grsu.practice.practice.model.Ticket;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class TicketView {
    private Ticket ticket;
    private String plane;
    private String departureLocation;
    private String destinationLocation;
    private LocalDateTime departureTime;
    private LocalDateTime destinationTime;
}
