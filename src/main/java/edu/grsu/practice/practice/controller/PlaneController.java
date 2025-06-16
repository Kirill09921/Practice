package edu.grsu.practice.practice.controller;

import edu.grsu.practice.practice.dto.PlaneDto;
import edu.grsu.practice.practice.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/plane")
public class PlaneController {

    private PlaneService planeService;

    @GetMapping("/{id}")
    public PlaneDto findPlane(@PathVariable UUID id) {
        return planeService.getPlane(id);
    }

    @GetMapping("/")
    public List<PlaneDto> findAllPlanes() {
        return planeService.getAllPlanes();
    }

    @PostMapping("/")
    public PlaneDto createPlane(@RequestBody PlaneDto planeDto) {
        return planeService.addPlane(planeDto);
    }

    @DeleteMapping("/")
    public boolean deletePlane(@RequestBody UUID bookingId) {
        return planeService.deletePlane(bookingId);
    }

    @PutMapping("/")
    public  PlaneDto updatePlane(@RequestBody PlaneDto planeDto) {
        return planeService.updatePlane(planeDto);
    }
}
