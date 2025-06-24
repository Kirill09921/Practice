package edu.grsu.practice.practice.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import edu.grsu.practice.practice.dto.FlightDto;
import edu.grsu.practice.practice.dto.PlaneDto;
import edu.grsu.practice.practice.dto.TicketDto;
import edu.grsu.practice.practice.dto.TicketView;
import edu.grsu.practice.practice.mapper.*;
import edu.grsu.practice.practice.model.*;
import edu.grsu.practice.practice.repository.TicketRepository;
import edu.grsu.practice.practice.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    public TicketMapper ticketMapper;
    public TicketRepository ticketRepository;
    public PlaneService planeService;
    public PlaneMapper planeMapper;
    public FlightService flightService;
    public FlightMapper flightMapper;



    @Override
    public TicketDto addTicket(TicketDto ticketDto) {
        log.info("adding ticket: {}", ticketDto);
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticketRepository.save(ticket);
        //flightService.getFlight(ticket.getFlight().getId()) - planeId
        ticket = ticketRepository.findById(ticket.getId()).orElseThrow();
        Flight flight = flightMapper.toEntity(flightService.getFlight(ticket.getFlight().getId()));
        Plane plane = planeMapper.toEntity(planeService.getPlane(flightService.getFlight(ticket.getFlight().getId()).getPlaneId()));
        flight.setPlane(plane);
//        Plane plane = flight.getPlane();
//        flight.setPlane(plane);
        ticket.setFlight(flight);
        byte[] details = generatePdf(ticket);
        String encoded = Base64.getEncoder().encodeToString(details);
        ticket.setFlightDetail(encoded);
        ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        log.info("adding ticket: {}", ticket);
        ticketRepository.save(ticket);
        return ticket;
    }


    @Override
    public List<TicketDto> getAllTickets() {
        log.info("getting all tickets");
        List<Ticket> tickets = ticketRepository.findAll();

        return ticketMapper.toDto(tickets);
    }

    @Override
    public TicketDto getTicket(UUID ticketId) {
        log.info("getting ticket: {}", ticketId);
        Optional<Ticket>  ticketOptional = ticketRepository.findById(ticketId);
        Ticket ticket = ticketOptional.orElseThrow();
        return ticketMapper.toDto(ticket);
    }

    @Override
    public boolean deleteTicket(UUID ticketId) {
        log.info("deleting ticket: {}", ticketId);
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        Ticket ticket = optionalTicket.orElseThrow();
        ticket.getBooking().setTicket(null);
        ticketRepository.deleteById(ticketId);
        return true;
    }

    @Override
    public TicketDto updateTicket(TicketDto ticketDto) {
        UUID ticketId =  ticketDto.getId();
        log.info("updating ticket: {}", ticketId);
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
//        ticketDto.setFlightDetail(generatePdf(ticketDto));
        Ticket existingTicket = ticketOptional.orElseThrow();
        existingTicket = ticketMapper.partialUpdate(ticketDto, existingTicket);
        byte[] updatedPdf = generatePdf(existingTicket);
        String encodedPdf = Base64.getEncoder().encodeToString(updatedPdf);
        existingTicket.setFlightDetail(encodedPdf);
        ticketRepository.save(existingTicket);
        return ticketMapper.toDto(existingTicket);
    }

    @Override
    public TicketView getTicketView(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        Flight flight = ticket.getFlight();
        Booking booking = ticket.getBooking();

        return TicketView.builder()
                .ticket(ticket)
                .plane(flight.getPlane().getModel())
                .departureLocation(booking.getDepartureLocation())
                .departureTime(booking.getDepartureTime())
                .destinationLocation(booking.getArrivalLocation())
                .destinationTime(booking.getArrivalTime())
                .build();
    }

    @Override
    public List<TicketView> getAllTicketViews() {
        log.info("getting all ticket views");
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(ticket -> {
                    Flight flight = ticket.getFlight();
                    Booking booking = ticket.getBooking();

                    return TicketView.builder()
                            .ticket(ticket)
                            .plane(flight != null ? flight.getPlane().getModel() : null)
                            .departureLocation(booking.getDepartureLocation())
                            .departureTime(booking.getDepartureTime())
                            .destinationLocation(booking.getArrivalLocation())
                            .destinationTime(booking.getArrivalTime())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public byte[] generatePdf(Ticket ticket) {
        try {
            BaseFont baseFont = BaseFont.createFont(
                    getClass().getClassLoader().getResource("fonts/arial.ttf").getPath(),
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );

            Font font = new Font(baseFont, 12, Font.NORMAL);


            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Билет № " + ticket.getId(), font));
            document.add(new Paragraph("Пользователь: " + ticket.getUser().getLogin(), font));
            document.add(new Paragraph("Самолёт: " + ticket.getFlight().getPlane().getModel(), font));
            document.add(new Paragraph("Рейс: " + ticket.getFlight().getId(), font));
            document.add(new Paragraph("Откуда: " + ticket.getBooking().getDepartureLocation(), font));
            document.add(new Paragraph("Куда: " + ticket.getBooking().getArrivalLocation(), font));
            document.add(new Paragraph("Вылет: " + ticket.getBooking().getDepartureTime(), font));
            document.add(new Paragraph("Прибытие: " + ticket.getBooking().getArrivalTime(), font));
            document.add(new Paragraph("Цена: " + ticket.getPrice() + " BYN", font));
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            log.error("Ошибка при генерации PDF", e);
            return null;
        }
    }

    @Override
    public ResponseEntity<byte[]> viewPdf(UUID id) {
        TicketDto dto = ticketMapper.toDto(ticketRepository.findById(id).orElseThrow());
        byte[] pdf = dto.getFlightDetail();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
                .inline()
                .filename("ticket_" + id + ".pdf")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }


}
