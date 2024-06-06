package com.example.m295.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    private String review;
    @ManyToOne
    @JoinColumn(name = "tripId", nullable = false)
    private Trip trip;
}
