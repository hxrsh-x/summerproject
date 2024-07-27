package com.kapil.NgoEventManager.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Subcription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate subcriptionStartDate;

    private LocalDate getSubcriptionEndDate;

    private PlanType plantype;

    private boolean isValid;

    @OneToOne
    private User user;
}
