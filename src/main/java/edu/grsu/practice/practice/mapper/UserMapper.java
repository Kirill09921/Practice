package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.dto.UserDto;
import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDto(List<User> users);

    List<User> toEntity(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}
