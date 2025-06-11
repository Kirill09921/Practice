package edu.grsu.practice.practice.mapper;

import edu.grsu.practice.practice.dto.TicketDto;
import edu.grsu.practice.practice.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.nio.charset.Charset;
import java.util.Base64;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {

    @Mapping(source = "flightDetail", target = "flightDetail", qualifiedByName = "stringToBytes")
    TicketDto toDto(Ticket ticket);
    @Mapping(source = "flightDetail", target = "flightDetail", qualifiedByName = "bytesToString")
    Ticket toEntity(TicketDto ticketDto);

    @Named("bytesToString")
    default String bytesToString(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }
    @Named("stringToBytes")
    default byte[] stringToBytes(String string){
        return Base64.getDecoder().decode(string);
    }
}
