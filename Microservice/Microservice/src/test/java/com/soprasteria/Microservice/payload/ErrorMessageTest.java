package com.soprasteria.Microservice.payload;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ErrorMessageTest {

    @Test
    @Order(1)
    public void testErrorMessage_functionalities() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Error Message");
        errorMessage.setStatus(HttpStatus.OK);
        errorMessage.setSuccess(false);

        assertEquals("Error Message", errorMessage.getMessage());
        assertEquals(HttpStatus.OK, errorMessage.getStatus());
    }

}
