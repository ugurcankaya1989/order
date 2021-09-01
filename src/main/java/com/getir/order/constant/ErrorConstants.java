package com.getir.order.constant;

public enum ErrorConstants {
    ORDER_NOT_FOUND_BY_ID("10","Order not found By Id!"),
    NO_ORDER_FOUND_IN_GIVEN_DATE_RANGE("20","There is no order in given date range!"),
    ORDER_NOT_FOUND_BY_CUSTOMER("30","There is no order for given customer Id!"),
    STOCK_NOT_ENOUGH("40","There is no stock for this book id :"),
    NO_BOOK_FOUND_BY_ID("50","There is no book found for given book Id : "),
    BOOK_LIST_NOT_UPDATED("60","Thrown exception during the update book stock information."),
    DATE_FORMAT_IS_NOT_VALID("70","Date format is not valid.");


    private String errorCode;
    private String description;

    ErrorConstants(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
