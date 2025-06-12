package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.FlightDto;

import java.util.List;
import java.util.UUID;

public interface FlightService {
    public void addFlight(FlightDto flightDto);
    public List<FlightDto> getAllFlights();
    public FlightDto getFlight(UUID flightId);
    public void deleteFlight(UUID flightId);
    public void updateFlight(UUID flightId,  FlightDto flightDto);
}
