package com.zerobase.weather.error;

public class InvalidDateException extends RuntimeException {
    private static final String INVALID_DATE_MESSAGE = "너무 과거 혹은 미래의 날짜입니다.";

    public InvalidDateException() {
        super(INVALID_DATE_MESSAGE);
    }
}
