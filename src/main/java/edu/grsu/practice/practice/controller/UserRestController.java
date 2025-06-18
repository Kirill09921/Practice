package edu.grsu.practice.practice.controller;

import edu.grsu.practice.practice.dto.UserDto;
import edu.grsu.practice.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private UserService userService;

    @GetMapping("/{id}")
    public UserDto findUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    @GetMapping("/")
    public List<UserDto> findAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @DeleteMapping("/")
    public boolean deleteUser(@RequestBody UUID userId) {
        return userService.deleteUser(userId);
    }

    @PutMapping("/")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }
}
