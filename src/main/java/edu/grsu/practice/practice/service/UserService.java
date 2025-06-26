package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.UserDto;
import edu.grsu.practice.practice.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public UserDto addUser(UserDto userDto);
    public List<UserDto> getAllUsers();
    public UserDto getUser(UUID userId);
    public User getUserEntity(UUID userId);
    public boolean deleteUser(UUID userId);
    public UserDto updateUser(UserDto userDto);
}
