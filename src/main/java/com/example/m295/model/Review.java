package com.example.m295.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @Size(min = 3, max = 64)
    private String review;

    @FutureOrPresent
    private LocalDateTime createdAt;

    private double rating;
    private boolean recommend;
    private int wordCount;

    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;
}