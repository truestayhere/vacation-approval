package com.company.vacation.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class VacationRequest extends PanacheEntity {
    public LocalDate startDate;
    public LocalDate endDate;
    public String employeeName;
    public String status;
}



