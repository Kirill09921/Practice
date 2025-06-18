package edu.grsu.practice.practice.controller.thymeleaf;

import edu.grsu.practice.practice.dto.UserDto;
import edu.grsu.practice.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public String findUser(@PathVariable UUID id, Model model) {
        var user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/user";
    }

    @GetMapping("/all")
    public String findAllUsers(Model model) {
        var users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/users";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserDto userDto) {
        var user = userService.addUser(userDto);
        return "redirect:/user/all";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        UserDto dto = UserDto.builder().id(UUID.randomUUID()).build();
        model.addAttribute("user", dto);
        return "user/create";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable UUID id, Model model) {
        var isDeleted = userService.deleteUser(id);
        model.addAttribute("isDeleted", isDeleted);
        return "redirect:/user/all";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable UUID id, @ModelAttribute UserDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/user/{id}";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable UUID id, Model model) {
        UserDto user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/update";
    }
}
