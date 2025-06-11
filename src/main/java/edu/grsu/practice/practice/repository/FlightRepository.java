package edu.grsu.practice.practice.repository;

import edu.grsu.practice.practice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
}
