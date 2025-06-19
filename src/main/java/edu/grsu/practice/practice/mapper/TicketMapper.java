package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.*;
import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.model.Flight;
import edu.grsu.practice.practice.model.Ticket;
import org.mapstruct.*;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {

    @Mapping(target = "ticket", source = "ticket")
    @Mapping(target = "plane", source = "flight.plane.model")
    @Mapping(target = "departureLocation", source = "booking.departureLocation")
    @Mapping(target = "destinationLocation", source = "booking.arrivalLocation")
    @Mapping(target = "departureTime", source = "booking.departureTime")
    @Mapping(target = "destinationTime", source = "booking.arrivalTime")
    TicketView toView(Ticket ticket, Flight flight, Booking booking);

    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(source = "flightDetail", target = "flightDetail", qualifiedByName = "stringToBytes") //ticket.booking.
    TicketDto toDto(Ticket ticket);

    @Mapping(source = "flightDetail", target = "flightDetail", qualifiedByName = "bytesToString")
    Ticket toEntity(TicketDto ticketDto);

    @Mapping(source = "flightDetail", target = "flightDetail", qualifiedByName = "stringToBytes")
    List<TicketDto> toDto(List<Ticket> tickets);

    @Mapping(source = "flightDetail", target = "flightDetail", qualifiedByName = "bytesToString")
    List<Ticket> toEntity(List<Ticket> tickets);

    @Mapping(source = "flightDetail", target = "flightDetail", qualifiedByName = "bytesToString")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ticket partialUpdate(TicketDto ticketDto, @MappingTarget Ticket ticket);

    @Named("bytesToString")
    default String bytesToString(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }
    @Named("stringToBytes")
    default byte[] stringToBytes(String string){
        return Base64.getDecoder().decode(string);
    }
}
