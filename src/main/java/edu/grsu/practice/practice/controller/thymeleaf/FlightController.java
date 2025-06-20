package edu.grsu.practice.practice.controller.thymeleaf;

import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.service.FlightService;
import edu.grsu.practice.practice.service.PlaneService;
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
    private PlaneService planeService;

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
    public String createFlight(@ModelAttribute FlightDto flightDto) {
        var flight = flightService.addFlight(flightDto);
        return "redirect:/flight/all";
    }

    @GetMapping("/create")
    public String createFlight(Model model) {
        FlightDto dto = FlightDto.builder().id(UUID.randomUUID()).build();
        var planes = planeService.getAllPlanes();
        model.addAttribute("planes", planes);;
        model.addAttribute("flight", dto);
        return "flight/create";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFlight(@PathVariable UUID id, Model model) {
        var isDeleted = flightService.deleteFlight(id);
        model.addAttribute("isDeleted", isDeleted);
        return "redirect:/flight/all";
    }

    @PutMapping("/update/{id}")
    public String updateFlight(@PathVariable UUID id, @ModelAttribute FlightDto flightDto) {
        flightService.updateFlight(flightDto);
        return "redirect:/flight/{id}";
    }

    @GetMapping("/update/{id}")
    public String updateFlight(@PathVariable UUID id, Model model) {
        FlightDto flight = flightService.getFlight(id);
        model.addAttribute("flight", flight);
        return "flight/update";
    }
}
