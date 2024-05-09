package com.soprasteria.Microservice.exceptionHandler;

import java.sql.SQLDataException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soprasteria.Microservice.payload.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorMessage> handleException(Throwable ex) {

        if (ex instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage("Internal Error Occured", false, HttpStatus.INTERNAL_SERVER_ERROR));
        } else if (ex instanceof SQLDataException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage("Error due to bad Request", false, HttpStatus.BAD_REQUEST));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage("Some Error Occured", false, HttpStatus.INTERNAL_SERVER_ERROR));
        }

    }

}
