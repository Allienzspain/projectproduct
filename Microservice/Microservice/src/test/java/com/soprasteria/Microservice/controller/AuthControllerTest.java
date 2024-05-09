package com.soprasteria.Microservice.controller;

import static org.mockito.Mockito.*;

import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.soprasteria.Microservice.model.JwtRequest;
import com.soprasteria.Microservice.model.JwtResponse;
import com.soprasteria.Microservice.security.JwtHelper;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtHelper jwtHelper;

    @InjectMocks
    private AuthController authController;

    @Test
    void testLoginSuccess() {

        JwtRequest request = new JwtRequest("username", "password");
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("username")).thenReturn(userDetails);
        when(jwtHelper.generateToken(userDetails)).thenReturn("jwtToken");

        ResponseEntity<JwtResponse> responseEntity = authController.login(request);

        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        JwtResponse responseBody = responseEntity.getBody();
        System.out.println("Token: " + responseBody.getJwtToken());
        System.out.println("Username: " + responseBody.getUsername());
        assert "jwtToken".equals(responseBody.getJwtToken());
        // assert "username".equals(responseBody.getUsername());
    }

    @Test
    void testLoginFailure() {

        JwtRequest request = new JwtRequest("username", "password");
        when(userDetailsService.loadUserByUsername("username")).thenThrow(new RuntimeException());

        ResponseEntity<JwtResponse> responseEntity = authController.login(request);

        assert responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;
        assert responseEntity.getBody() == null;
    }

    // Add more test cases for other scenarios if needed

    // @Test
    // void testGetCurrentUser() {
    // // Arrange
    // PrincipalMock principal = new PrincipalMock("testuser");
    // User userDetails = new User("testuser", "password", true, true, true, true);
    // when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);

    // // Act
    // User currentUser = authController.getCurrentUser(principal);

    // // Assert
    // assert currentUser != null;
    // assert currentUser.getUsername().equals("testuser");
    // }
}

class PrincipalMock implements Principal {
    private String name;

    PrincipalMock(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
