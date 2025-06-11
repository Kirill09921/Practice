package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.UserDto;
import edu.grsu.practice.practice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
