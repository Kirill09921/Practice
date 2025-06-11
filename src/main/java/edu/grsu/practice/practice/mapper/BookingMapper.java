package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {
    BookingDto toDto(Booking booking);
    Booking toEntity(BookingDto bookingDto);
}
