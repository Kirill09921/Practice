package edu.grsu.practice.practice.repository;

import edu.grsu.practice.practice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
