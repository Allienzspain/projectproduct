package com.product.purchase.service.PurchaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.product.purchase.service.PurchaseService.models.UserDto;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://localhost:8082";

    public UserDto getBothProfile(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", jwt);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<UserDto> response = restTemplate.exchange(
                    USER_SERVICE_URL + "/api/profile/both",
                    HttpMethod.GET,
                    requestEntity,
                    UserDto.class);

            return response.getBody();
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

    }

    public UserDto getUserProfile(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", jwt);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<UserDto> response = restTemplate.exchange(
                    USER_SERVICE_URL + "/api/profile",
                    HttpMethod.GET,
                    requestEntity,
                    UserDto.class);

            return response.getBody();
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

}
