package edu.grsu.practice.practice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_customer")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "login")
    private String login;
    @OneToMany(mappedBy = "user")
    private Set<Ticket> tickets;
    @OneToMany(mappedBy = "user")
    private Set<Booking> bookings;
}
