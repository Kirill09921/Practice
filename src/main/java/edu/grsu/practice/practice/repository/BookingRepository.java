package edu.grsu.practice.practice.repository;

import edu.grsu.practice.practice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
