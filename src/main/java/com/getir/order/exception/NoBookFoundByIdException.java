package com.getir.order.exception;

import lombok.Getter;

@Getter
public class NoBookFoundByIdException extends RuntimeException{
    private long id;
    public NoBookFoundByIdException(Long id) {
        this.id = id;
    }
}
