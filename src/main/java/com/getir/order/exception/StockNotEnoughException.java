package com.getir.order.exception;

import lombok.Getter;

@Getter
public class StockNotEnoughException extends RuntimeException{
    private long id;
    public StockNotEnoughException(Long id) {
        this.id = id;
    }
}
