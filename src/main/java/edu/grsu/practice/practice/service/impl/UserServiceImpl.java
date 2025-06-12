package edu.grsu.practice.practice.service.impl;

import edu.grsu.practice.practice.dto.UserDto;
import edu.grsu.practice.practice.mapper.UserMapper;
import edu.grsu.practice.practice.model.User;
import edu.grsu.practice.practice.repository.UserRepository;
import edu.grsu.practice.practice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    public UserMapper userMapper;
    public UserRepository userRepository;

    @Override
    public void addUser(UserDto userDto) {
        log.info("adding user {}", userDto);
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("getting all users");
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    @Override
    public UserDto getUser(UUID userId) {
        log.info("getting user {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow();
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(UUID userId) {
        log.info("deleting user: {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow();
        userRepository.delete(user);
    }

    @Override
    public void updateUser(UUID userId, UserDto userDto) {
        log.info("updating user {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        User existingUser = userOptional.orElseThrow();
        existingUser = userMapper.partialUpdate(userDto, existingUser);
        userRepository.save(existingUser);
    }
}
