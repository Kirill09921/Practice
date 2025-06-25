package edu.grsu.practice.practice.repository;

import edu.grsu.practice.practice.model.Booking;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @EntityGraph(attributePaths = {"user", "ticket"})
    @Query("SELECT b FROM Booking b WHERE b.departureTime BETWEEN :start AND :end")
    List<Booking> findBookings(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @EntityGraph(attributePaths = {"user", "ticket"})
    @Query("SELECT b FROM Booking b WHERE b.id = :id")
    Optional<Booking> findWithUserAndTicketById(@Param("id") UUID id);

}
