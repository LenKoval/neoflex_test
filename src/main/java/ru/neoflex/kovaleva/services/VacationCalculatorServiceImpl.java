package ru.neoflex.kovaleva.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.kovaleva.exceptions.VacationCalculatorException;
import ru.neoflex.kovaleva.models.VacationCalculatorEntity;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class VacationCalculatorServiceImpl implements VacationCalculatorService {

    private static DecimalFormat df = new DecimalFormat("#0.00");

    private static final double AVERAGE_NUMBERS_DAYS_MONTH = 29.3;

    @Override
    public VacationCalculatorEntity getVacationPay(double salary, int vacationDays) {

        validateSalary(salary);

        if (vacationDays <= 0) {
            throw new VacationCalculatorException(VacationCalculatorException.ErrorCode.INVALID_INPUT,
                    "Vacation days must be greater than zero.");
        }

        String vacationPay = df.format(salary / AVERAGE_NUMBERS_DAYS_MONTH * vacationDays);

        return new VacationCalculatorEntity(salary, vacationDays, null, null, vacationPay);
    }

    @Override
    public VacationCalculatorEntity getVacationsPayBetweenDates(double salary, LocalDate startDate, LocalDate endDate) {

        validateSalary(salary);

        if (startDate.isAfter(endDate)) {
            throw new VacationCalculatorException(VacationCalculatorException.ErrorCode.INVALID_DATE_RANGE,
                    "Start date must be before end date.");
        }

        int daysBetween = Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate));

        String vacationPay = df.format(salary / AVERAGE_NUMBERS_DAYS_MONTH * daysBetween);

        return new VacationCalculatorEntity(salary, 0, startDate, endDate, vacationPay);
    }

    private void validateSalary(double salary) {
        if (salary <= 0) {
            throw new VacationCalculatorException(VacationCalculatorException.ErrorCode.INVALID_INPUT,
                    "Salary must be greater than zero.");
        }
    }

}
