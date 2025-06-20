package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.model.Flight;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FlightMapper {

    @Mapping(target = "plane", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(source = "plane.id", target = "planeId")
    FlightDto toDto(Flight flight);

    Flight toEntity(FlightDto flightDto);

    List<FlightDto> toDto(List<Flight> flights);

    List<Flight> toEntity(List<Flight> flights);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Flight partialUpdate(FlightDto flightDto, @MappingTarget Flight flight);
}
