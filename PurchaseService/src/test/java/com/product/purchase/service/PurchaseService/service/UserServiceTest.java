package com.product.purchase.service.PurchaseService.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.product.purchase.service.PurchaseService.models.UserDto;

class UserServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserProfile_Success() {

        String jwt = "dummy_jwt";
        UserDto expectedUser = new UserDto(1, "John Doe", "john@example.com", "john", "password", null);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(expectedUser, HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(), any(), any(Class.class))).thenReturn(responseEntity);

        UserDto actualUser = userService.getUserProfile(jwt);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetUserProfile_Unauthorized() {
        String jwt = "dummy_invalid_jwt";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        when(restTemplate.exchange(any(String.class), any(), any(), any(Class.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        assertThrows(HttpClientErrorException.class, () -> userService.getUserProfile(jwt));
    }

    @Test
    void testGetBothProfile_Success() {
        String jwt = "valid_jwt_token";
        UserDto expectedUser = new UserDto(1, "John Doe", "john@example.com", "john", "password", null);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(expectedUser, HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(), any(), any(Class.class))).thenReturn(responseEntity);

        UserDto actualUser = userService.getBothProfile(jwt);

        assertEquals(expectedUser, actualUser);
    }

}