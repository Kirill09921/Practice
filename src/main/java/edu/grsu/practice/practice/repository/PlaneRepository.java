package edu.grsu.practice.practice.repository;

import edu.grsu.practice.practice.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaneRepository extends JpaRepository<Plane, UUID> {
}
