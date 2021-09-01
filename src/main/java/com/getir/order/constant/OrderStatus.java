package com.getir.order.constant;

public enum OrderStatus {
    ACTIVE("A"),COMPLETED("D"),CANCEL("C");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
