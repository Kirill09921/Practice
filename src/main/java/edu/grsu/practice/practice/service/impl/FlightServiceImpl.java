package edu.grsu.practice.practice.service.impl;

import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.mapper.FlightMapper;
import edu.grsu.practice.practice.mapper.PlaneMapper;
import edu.grsu.practice.practice.model.Flight;
import edu.grsu.practice.practice.model.Plane;
import edu.grsu.practice.practice.repository.FlightRepository;
import edu.grsu.practice.practice.service.FlightService;
import edu.grsu.practice.practice.service.PlaneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class FlightServiceImpl implements FlightService {

    public FlightMapper flightMapper;
    public FlightRepository flightRepository;
    public PlaneService planeService;
    public PlaneMapper planeMapper;

    @Override
    public FlightDto addFlight(FlightDto flightDto) {
        log.info("Adding flight: {}", flightDto);
        Flight flight = flightMapper.toEntity(flightDto);
        flight.setPlane(planeMapper.toEntity(planeService.getPlane(flightDto.getPlaneId())));
        flightRepository.save(flight);
        return flightMapper.toDto(flight);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        log.info("Getting all flights");
        List<Flight> flights = flightRepository.findAll();
        return flightMapper.toDto(flights);
    }

    @Override
    public FlightDto getFlight(UUID flightId) {
        log.info("Getting flight: {}", flightId);
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        Flight flight = flightOptional.orElseThrow();
        Plane plane = planeMapper.toEntity(planeService.getPlane(flight.getPlane().getId()));
        flight.setPlane(plane);
        return flightMapper.toDto(flight);
    }

    @Override
    public Flight getFlightEntity(UUID flightId){
        log.info("Getting flight: {}", flightId);
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        Flight flight = flightOptional.orElseThrow();
//        Plane plane = planeService.getPlaneEntity(flight.getPlane().getId());
//        flight.setPlane(plane);
        return flight;
    }

    @Override
    public boolean deleteFlight(UUID flightId) {
        log.info("Deleting flight: {}", flightId);
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        Flight flight = flightOptional.orElseThrow();
        if (!flight.getTickets().isEmpty()) {
            return false;
        }
        flightRepository.delete(flight);
        return true;
    }

    @Override
    public FlightDto updateFlight(FlightDto flightDto) {
        UUID flightId =  flightDto.getId();
        log.info("Updating flight: {}", flightId);
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        Flight existingFlight = flightOptional.orElseThrow();
        existingFlight = flightMapper.partialUpdate(flightDto, existingFlight);
        flightRepository.save(existingFlight);
        return flightMapper.toDto(existingFlight);
    }
}
