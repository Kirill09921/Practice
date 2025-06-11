package edu.grsu.practice.practice.repository;

import edu.grsu.practice.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
