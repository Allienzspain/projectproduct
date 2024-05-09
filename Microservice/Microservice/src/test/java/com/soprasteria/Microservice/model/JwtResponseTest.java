package com.soprasteria.Microservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JwtResponseTest {

    @Test
    void testAllArgsConstructor() {

        String jwtToken = "testToken";
        String username = "testUser";

        JwtResponse jwtResponse = new JwtResponse(jwtToken, username);

        assertNotNull(jwtResponse);
        assertEquals(jwtToken, jwtResponse.getJwtToken());
        assertEquals(username, jwtResponse.getUsername());
    }

    @Test
    void testGettersAndSetters() {

        JwtResponse jwtResponse = new JwtResponse();
        String jwtToken = "testToken";
        String username = "testUser";

        jwtResponse.setJwtToken(jwtToken);
        jwtResponse.setUsername(username);

        assertEquals(jwtToken, jwtResponse.getJwtToken());
        assertEquals(username, jwtResponse.getUsername());
    }

    @Test
    void testNoArgsConstructor() {

        JwtResponse jwtResponse = new JwtResponse();

        assertNotNull(jwtResponse);
        assertNull(jwtResponse.getJwtToken());
        assertNull(jwtResponse.getUsername());
    }

}
