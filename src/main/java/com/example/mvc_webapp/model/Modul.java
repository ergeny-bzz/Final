package com.example.mvc_webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Module")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Modul {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modulNr;
    private String bezeichnung;
    @FutureOrPresent
    private LocalDateTime start;
    @Future
    private LocalDateTime ende;

    private Double kosten;

    private Boolean aktiv;
}
