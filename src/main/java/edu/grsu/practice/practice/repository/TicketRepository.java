package edu.grsu.practice.practice.repository;

import edu.grsu.practice.practice.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
