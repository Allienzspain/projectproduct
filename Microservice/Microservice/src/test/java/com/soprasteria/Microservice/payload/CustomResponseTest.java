package com.soprasteria.Microservice.payload;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class CustomResponseTest {

    @Test
    @Order(1)
    public void customResponseFunctionality() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setObject("Hello From Custom Response");
        assertEquals("Hello From Custom Response", customResponse.getObject());
    }

}
