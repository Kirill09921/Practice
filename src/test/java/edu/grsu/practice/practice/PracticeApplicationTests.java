package edu.grsu.practice.practice;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.dto.TicketDto;
import edu.grsu.practice.practice.mapper.BookingMapper;
import edu.grsu.practice.practice.mapper.BookingMapperImpl;
import edu.grsu.practice.practice.mapper.TicketMapper;
import edu.grsu.practice.practice.mapper.TicketMapperImpl;
import edu.grsu.practice.practice.model.Booking;
import edu.grsu.practice.practice.model.Ticket;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

@SpringBootTest
class PracticeApplicationTests {



	@Test
	public void testTicketMapper()
	{
		TicketMapper ticketMapper = new TicketMapperImpl();
		Ticket ticket = new Ticket();
		ticket.setId(UUID.randomUUID());
		ticket.setFlightDetail("details");
		TicketDto ticketDto = ticketMapper.toDto(ticket);
		assert (ticketDto.getId().equals(ticket.getId()));
		assert (Base64.getEncoder().encodeToString(ticketDto.getFlightDetail()).equals(ticket.getFlightDetail()));
	}
	@Test
	public void testBookingMapper()
	{
		BookingMapper mapper = new BookingMapperImpl();
		Booking booking = new Booking();
		booking.setId(UUID.randomUUID());
		booking.setArrivalLocation("arrivalLocation");
		booking.setDepartureLocation("departureLocation");
		booking.setDepartureTime(LocalDateTime.now());
		BookingDto bookingDto = mapper.toDto(booking);
		assert (bookingDto.getId().equals(booking.getId()));
		assert (bookingDto.getArrivalLocation().equals(booking.getArrivalLocation()));
		assert (bookingDto.getDepartureLocation().equals(booking.getDepartureLocation()));
		assert (bookingDto.getDepartureTime().equals(booking.getDepartureTime()));
	}
	@Test
	void contextLoads() {
	}

}
