package com.getir.order.exception;

import com.getir.order.constant.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(OrderNotFoundByIdException.class)
    public ResponseEntity<?> orderNotFoundByIdException() {
        return new ResponseEntity<>(ExceptionResponse.builder().dateTime(LocalDateTime.now()).errorCode(ErrorConstants.ORDER_NOT_FOUND_BY_ID.getErrorCode()).errorMessage(ErrorConstants.ORDER_NOT_FOUND_BY_ID.getDescription()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoOrderFoundInGivenDateRangeException.class)
    public ResponseEntity<?> noOrderFoundInGivenDateRangeException() {
        return new ResponseEntity<>(ExceptionResponse.builder().dateTime(LocalDateTime.now()).errorCode(ErrorConstants.NO_ORDER_FOUND_IN_GIVEN_DATE_RANGE.getErrorCode()).errorMessage(ErrorConstants.NO_ORDER_FOUND_IN_GIVEN_DATE_RANGE.getDescription()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundByCustomerException.class)
    public ResponseEntity<?> orderNotFoundByCustomerException() {
        return new ResponseEntity<>(ExceptionResponse.builder().dateTime(LocalDateTime.now()).errorCode(ErrorConstants.ORDER_NOT_FOUND_BY_CUSTOMER.getErrorCode()).errorMessage(ErrorConstants.ORDER_NOT_FOUND_BY_CUSTOMER.getDescription()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockNotEnoughException.class)
    public ResponseEntity<?> stockNotEnoughException(StockNotEnoughException ex) {
        return new ResponseEntity<>(ExceptionResponse.builder().dateTime(LocalDateTime.now()).errorCode(ErrorConstants.STOCK_NOT_ENOUGH.getErrorCode()).errorMessage(ErrorConstants.STOCK_NOT_ENOUGH.getDescription() + ex.getId()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoBookFoundByIdException.class)
    public ResponseEntity<?> noBookFoundByIdException(NoBookFoundByIdException ex) {
        return new ResponseEntity<>(ExceptionResponse.builder().dateTime(LocalDateTime.now()).errorCode(ErrorConstants.NO_BOOK_FOUND_BY_ID.getErrorCode()).errorMessage(ErrorConstants.NO_BOOK_FOUND_BY_ID.getDescription() + ex.getId()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookListNotUpdatedException.class)
    public ResponseEntity<?> bookListNotUpdatedException() {
        return new ResponseEntity<>(ExceptionResponse.builder().dateTime(LocalDateTime.now()).errorCode(ErrorConstants.BOOK_LIST_NOT_UPDATED.getErrorCode()).errorMessage(ErrorConstants.BOOK_LIST_NOT_UPDATED.getDescription()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateFormatIsNotValidException .class)
    public ResponseEntity<?> dateFormatIsNotValidException() {
        return new ResponseEntity<>(ExceptionResponse.builder().dateTime(LocalDateTime.now()).errorCode(ErrorConstants.DATE_FORMAT_IS_NOT_VALID.getErrorCode()).errorMessage(ErrorConstants.DATE_FORMAT_IS_NOT_VALID.getDescription()).build(), HttpStatus.NOT_FOUND);
    }


}
