package edu.grsu.practice.practice.controller.thymeleaf;

import edu.grsu.practice.practice.dto.PlaneDto;
import edu.grsu.practice.practice.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/plane")
public class PlaneController {

    private PlaneService planeService;

    @GetMapping("/{id}")
    public String findPlane(@PathVariable UUID id, Model model) {
        var plane = planeService.getPlane(id);
        model.addAttribute("plane", plane);
        return "plane/plane";
    }

    @GetMapping("/all")
    public String findAllPlanes(Model model) {
        var planes = planeService.getAllPlanes();
        model.addAttribute("planes", planes);
        return "plane/planes";
    }

    @PostMapping("/create")
    public String createPlane(@RequestBody PlaneDto planeDto, Model model) {
        var plane = planeService.addPlane(planeDto);
        model.addAttribute("plane", plane);
        return "createPlane";
    }

    @DeleteMapping("/{id}")
    public String deletePlane(@PathVariable UUID id, Model model) {
        var isDeleted = planeService.deletePlane(id);
        model.addAttribute("isDeleted", isDeleted);
        return "deletePlane";
    }

    @PutMapping("/{id}")
    public String updatePlane(@PathVariable UUID id, @RequestBody PlaneDto planeDto, Model model) {
        var updatedPlane = planeService.updatePlane(planeDto);
        model.addAttribute("plane", updatedPlane);
        return "updatePlane";
    }
}
