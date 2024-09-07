package ru.neoflex.kovaleva.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationCalculatorEntity {

    private double salary;

    private int vacationDays;

    private LocalDate startDate;

    private LocalDate endDate;

    private String vacationPay;
}
