package ru.neoflex.kovaleva.exceptions;

import lombok.Getter;

@Getter
public class VacationCalculatorException extends RuntimeException {

    private final ErrorCode errorCode;

    public VacationCalculatorException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Getter
    public enum ErrorCode {
        INVALID_INPUT("INVALID_INPUT", "Invalid input"),
        INVALID_DATE_RANGE("INVALID_DATE_RANGE", "Invalid date range");

        private final String code;
        private final String description;

        ErrorCode(String code, String description) {
            this.code = code;
            this.description = description;
        }
    }
}
