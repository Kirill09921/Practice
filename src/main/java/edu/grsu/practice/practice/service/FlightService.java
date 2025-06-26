package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.model.Flight;

import java.util.List;
import java.util.UUID;

public interface FlightService {
    public FlightDto addFlight(FlightDto flightDto);
    public List<FlightDto> getAllFlights();
    public FlightDto getFlight(UUID flightId);
    public Flight getFlightEntity(UUID flightId);
    public boolean deleteFlight(UUID flightId);
    public FlightDto updateFlight(FlightDto flightDto);
}
