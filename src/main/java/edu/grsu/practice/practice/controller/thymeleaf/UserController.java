package edu.grsu.practice.practice.controller.thymeleaf;

import edu.grsu.practice.practice.dto.UserDto;
import edu.grsu.practice.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String createUser(@RequestBody UserDto userDto, Model model) {
        var user = userService.addUser(userDto);
        model.addAttribute("user", user);
        return "createUser";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id, Model model) {
        var isDeleted = userService.deleteUser(id);
        model.addAttribute("isDeleted", isDeleted);
        return "deleteUser";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable UUID id, @RequestBody UserDto userDto, Model model) {
        var updatedUser = userService.updateUser(userDto);
        model.addAttribute("user", updatedUser);
        return "updateUser";
    }
}

