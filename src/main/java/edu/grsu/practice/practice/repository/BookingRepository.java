package edu.grsu.practice.practice.repository;

import edu.grsu.practice.practice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
