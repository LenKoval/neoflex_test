package ru.neoflex.kovaleva.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.kovaleva.dtos.VacationCalculatorEntityDto;
import ru.neoflex.kovaleva.models.VacationCalculatorEntity;
import ru.neoflex.kovaleva.services.VacationCalculatorServiceImpl;

import java.time.LocalDate;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class VacationCalculatorController {

    private final VacationCalculatorServiceImpl service;

    @GetMapping(value = "/calculate", params = {"salary", "vacationDays"})
    public VacationCalculatorEntityDto calculate(@RequestParam("salary") Double salary,
                                                 @RequestParam("vacationDays") Integer vacationDays) {

        return toDto(service.getVacationPay(salary, vacationDays));
    }

    @GetMapping(value = "/calculate", params = {"salary", "startDate", "endDate"})
    public VacationCalculatorEntityDto calculate(@RequestParam("salary") Double salary,
                                                 @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                 @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return toDto(service.getVacationsPayBetweenDates(salary, startDate, endDate));
    }

    private VacationCalculatorEntityDto toDto(VacationCalculatorEntity entity) {
        return new VacationCalculatorEntityDto(entity.getVacationPay());
    }
}
