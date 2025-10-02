package com.company.vacation.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class VacationRequestDTO {

    @NotBlank
    public String employeeName;

    @NotNull
    @FutureOrPresent
    public LocalDate startDate;

    @NotNull
    @FutureOrPresent
    public LocalDate endDate;
}
