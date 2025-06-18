package edu.grsu.practice.practice.controller.thymeleaf;

import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/flight")
public class FlightController {

    private FlightService flightService;

    @GetMapping("/{id}")
    public String findFlight(@PathVariable UUID id, Model model) {
        var flight = flightService.getFlight(id);
        model.addAttribute("flight", flight);
        return "flight/flight";
    }

    @GetMapping("/all")
    public String findAllFlights(Model model) {
        var flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);
        return "flight/flights";
    }

    @PostMapping("/create")
    public String createFlight(@RequestBody FlightDto flightDto, Model model) {
        var flight = flightService.addFlight(flightDto);
        model.addAttribute("flight", flight);
        return "createFlight";
    }

    @DeleteMapping("/{id}")
    public String deleteFlight(@PathVariable UUID id, Model model) {
        var isDeleted = flightService.deleteFlight(id);
        model.addAttribute("isDeleted", isDeleted);
        return "deleteFlight";
    }

    @PutMapping("/{id}")
    public String updateFlight(@PathVariable UUID id, @RequestBody FlightDto flightDto, Model model) {
        var updatedFlight = flightService.updateFlight(flightDto);
        model.addAttribute("flight", updatedFlight);
        return "updateFlight";
    }
}
