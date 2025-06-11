package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.PlaneDto;
import edu.grsu.practice.practice.model.Plane;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlaneMapper {
    PlaneDto toDto(Plane plane);
    Plane toEntity(PlaneDto planeDto);
}
