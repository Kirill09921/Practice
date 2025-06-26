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
    public UserDto addUser(UserDto userDto) {
        log.info("adding user {}", userDto);
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return userMapper.toDto(user);
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
    public User getUserEntity(UUID userId){
        log.info("getting user {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow();
        return user;
    }

    @Override
    public boolean deleteUser(UUID userId) {
        log.info("deleting user: {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow();
        if (user.getBookings() == null || user.getTickets() == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UUID userId  = userDto.getId();
        log.info("updating user {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        User existingUser = userOptional.orElseThrow();
        existingUser = userMapper.partialUpdate(userDto, existingUser);
        userRepository.save(existingUser);
        return userMapper.toDto(existingUser);
    }
}
