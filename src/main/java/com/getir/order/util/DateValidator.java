package com.getir.order.util;

public class DateValidator {
    private static final String DATE_FORMAT = "\\d{4}-\\d{2}-\\d{2}";
    public static Boolean isDateStringValid(String date) {
        return date.matches(DATE_FORMAT);
    }
}
