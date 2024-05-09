package com.soprasteria.Microservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JwtRequestTest {

    @Test
    void testAllArgsConstructor() {

        String username = "testUser";
        String password = "testPassword";

        JwtRequest jwtRequest = new JwtRequest(username, password);

        assertNotNull(jwtRequest);
        assertEquals(username, jwtRequest.getUsername());
        assertEquals(password, jwtRequest.getPassword());
    }

    @Test
    void testGettersAndSetters() {

        JwtRequest jwtRequest = new JwtRequest();
        String username = "testUser";
        String password = "testPassword";

        jwtRequest.setUsername(username);
        jwtRequest.setPassword(password);

        assertEquals(username, jwtRequest.getUsername());
        assertEquals(password, jwtRequest.getPassword());
    }

    @Test
    void testNoArgsConstructor() {

        JwtRequest jwtRequest = new JwtRequest();

        assertNotNull(jwtRequest);
        assertNull(jwtRequest.getUsername());
        assertNull(jwtRequest.getPassword());
    }

}
