package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.model.Booking;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ticket", ignore = true)
    BookingDto toDto(Booking booking);

    Booking toEntity(BookingDto bookingDto);

    List<BookingDto> toDto(List<Booking> bookings);

    List<Booking> toEntity(List<Booking> bookings);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Booking partialUpdate(BookingDto bookingDto, @MappingTarget Booking booking);
}
