package com.soprasteria.Microservice.exceptionHandler;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
