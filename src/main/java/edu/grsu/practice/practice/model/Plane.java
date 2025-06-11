package edu.grsu.practice.practice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "seats")
    private int seats;
    @Column(name = "max_load")
    private int maxLoad;
    @Column(name = "model")
    private String model;
    @OneToMany(mappedBy = "plane")
    private Set<Flight> flights;
}
