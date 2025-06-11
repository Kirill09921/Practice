package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.model.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FlightMapper {
    FlightDto toDto(Flight flight);
    Flight toEntity(FlightDto flightDto);
}
