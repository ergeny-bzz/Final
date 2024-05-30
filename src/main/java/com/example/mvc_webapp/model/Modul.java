package com.example.mvc_webapp.model;

import jakarta.persistence.*;
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

    private LocalDateTime start;

    private LocalDateTime ende;

    private Double kosten;

    private Boolean aktiv;
}
