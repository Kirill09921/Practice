package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.dto.PlaneDto;
import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.model.Plane;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlaneMapper {

    @Mapping(target = "flights", ignore = true)
    PlaneDto toDto(Plane plane);

    Plane toEntity(PlaneDto planeDto);

    List<PlaneDto> toDto(List<Plane> planes);

    List<Plane> toEntity(List<PlaneDto> planeDtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    Plane partialUpdate(PlaneDto planeDto, @MappingTarget Plane plane);
}
