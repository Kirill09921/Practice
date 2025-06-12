package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public void addUser(UserDto userDto);
    public List<UserDto> getAllUsers();
    public UserDto getUser(UUID userId);
    public void deleteUser(UUID userId);
    public void updateUser(UUID userId, UserDto userDto);
}
