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
    public String createPlane(@ModelAttribute PlaneDto planeDto) {
        var plane = planeService.addPlane(planeDto);
        return "redirect:/plane/all";
    }

    @GetMapping("/create")
    public String createPlane(Model model) {
        PlaneDto dto = PlaneDto.builder().id(UUID.randomUUID()).build();
        model.addAttribute("plane", dto);
        return "plane/create";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePlane(@PathVariable UUID id, Model model) {
        var isDeleted = planeService.deletePlane(id);
        model.addAttribute("isDeleted", isDeleted);
        return "redirect:/plane/all";
    }

    @PutMapping("/update/{id}")
    public String updatePlane(@PathVariable UUID id, @ModelAttribute PlaneDto planeDto) {
        planeService.updatePlane(planeDto);
        return "redirect:/plane/{id}";
    }

    @GetMapping("/update/{id}")
    public String updatePlane(@PathVariable UUID id, Model model) {
        PlaneDto plane = planeService.getPlane(id);
        model.addAttribute("plane", plane);
        return "plane/update";
    }
}
