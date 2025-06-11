package edu.grsu.practice.practice.dto;


import edu.grsu.practice.practice.model.Flight;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PlaneDto {
    private UUID id;
    private int seats;
    private int maxLoad;
    private String model;
    private Set<Flight> flights;
}
