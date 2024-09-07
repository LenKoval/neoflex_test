package ru.neoflex.kovaleva.services;

import ru.neoflex.kovaleva.models.VacationCalculatorEntity;

import java.time.LocalDate;

public interface VacationCalculatorService {

    VacationCalculatorEntity getVacationPay(double salary, int vacationDays);

    VacationCalculatorEntity getVacationsPayBetweenDates(double salary, LocalDate startDate, LocalDate endDate);
}
