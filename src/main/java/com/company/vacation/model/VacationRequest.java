package com.company.vacation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class VacationRequest extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @JsonIgnore
    public Long id;

    @Column(unique = true, nullable = false)
    public UUID uuid;

    public LocalDate startDate;
    public LocalDate endDate;
    public String employeeName;

    @Enumerated(EnumType.STRING)
    public VacationStatus status;
}



