package edu.grsu.practice.practice.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {
    private String departureLocation;
    private String arrivalLocation;
    private int price;
}

