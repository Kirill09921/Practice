package edu.grsu.practice.practice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "departure")
    private String departure;
    @Column(name = "destination")
    private String destination;
    @ManyToOne(fetch = FetchType.LAZY)
    private Plane plane;
    @OneToMany(mappedBy = "flight")
    private Set<Ticket> tickets;
}
