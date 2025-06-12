package edu.grsu.practice.practice.controller;

import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private FlightService flightService;

    @GetMapping("/{id}")
    public FlightDto findFlight(@PathVariable UUID id) {
        return  flightService.getFlight(id);
    }

    @GetMapping("/")
    public List<FlightDto> findAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping("/")
    public FlightDto createFlight(@RequestBody FlightDto flightDto) {
        return flightService.addFlight(flightDto);
    }

    @DeleteMapping("/")
    public boolean deleteFlight(@RequestBody UUID userId) {
        return flightService.deleteFlight(userId);
    }

    @PutMapping("/")
    public FlightDto updateFlight(@RequestBody FlightDto flightDto) {
        return flightService.updateFlight(flightDto);
    }
}
