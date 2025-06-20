package edu.grsu.practice.practice.dto;

import edu.grsu.practice.practice.model.Plane;
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
public class FlightDto {
    private UUID id;
    private String departure;
    private String destination;
    private Plane plane;
    private Set<Ticket> tickets;
    private UUID planeId;
}
