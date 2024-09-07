package ru.neoflex.kovaleva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.neoflex.kovaleva.exceptions.VacationCalculatorException;
import ru.neoflex.kovaleva.models.VacationCalculatorEntity;
import ru.neoflex.kovaleva.services.VacationCalculatorServiceImpl;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationCalculatorServiceImplTest {

    private VacationCalculatorServiceImpl service;

    @BeforeEach
    void setUp() {
        this.service = new VacationCalculatorServiceImpl();
    }

    @Test
    void testGetVacationPayWithoutDates() {
        VacationCalculatorEntity actual = new VacationCalculatorEntity(50000.0, 28, null, null, "47781,57");
        VacationCalculatorEntity expected = service.getVacationPay(50000.0, 28);

        assertThat(expected.getVacationPay()).isNotNull();
        assertEquals(actual.getVacationDays(), expected.getVacationDays());
    }

    @Test
    void testGetVacationPayWithDates() {
        LocalDate startDate = LocalDate.of(2024, 9, 2);
        LocalDate endDate = LocalDate.of(2024, 9, 16);

        VacationCalculatorEntity actual = new VacationCalculatorEntity(60000.0, 0, startDate, endDate, "28668,94");
        VacationCalculatorEntity expected = service.getVacationsPayBetweenDates(60000.0, startDate, endDate);

        assertThat(expected.getVacationPay()).isNotNull();
        assertEquals(actual.getVacationPay(), expected.getVacationPay());
    }

    @Test
    void testGetVacationPayInvalidSalary() {
        double salary = -1000.0;
        int vacationDays = 10;

        assertThrows(VacationCalculatorException.class, () ->
                service.getVacationPay(salary, vacationDays));
    }

    @Test
    void testGetVacationPayZeroVacationDays() {
        double salary = 5000.0;
        int vacationDays = 0;

        assertThrows(VacationCalculatorException.class, () ->
                service.getVacationPay(salary, vacationDays));
    }

    @Test
    void testGetVacationsPayBetweenDatesInvalidSalary() {
        double salary = -1000.0;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusMonths(1);

        assertThrows(VacationCalculatorException.class, () ->
                service.getVacationsPayBetweenDates(salary, startDate, endDate));
    }

    @Test
    void testGetVacationsPayBetweenDatesInvalidDateRange() {
        double salary = 50000.0;
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now();

        assertThrows(VacationCalculatorException.class, () ->
                service.getVacationsPayBetweenDates(salary, startDate, endDate));
    }
}
