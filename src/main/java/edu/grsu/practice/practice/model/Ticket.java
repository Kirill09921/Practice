package edu.grsu.practice.practice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "price")
    private int price;
    @Column(name = "flight_detail", columnDefinition = "TEXT")
    private String flightDetail;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;
    @OneToOne(fetch = FetchType.LAZY)
    private Booking  booking;
}
