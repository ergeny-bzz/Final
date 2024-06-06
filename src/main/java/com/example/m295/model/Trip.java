package com.example.m295.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "trip")
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int tripId;
    @Size(min = 3, max = 64)
    private String destination;
    @FutureOrPresent
    private LocalDateTime start;
    @Future
    private LocalDateTime end;
    private double cost;
    private boolean available;
    private int passengers;
    @Size(min = 1, max = 64)
    private String organizer;


}

