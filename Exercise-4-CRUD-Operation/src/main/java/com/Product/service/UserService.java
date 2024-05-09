package com.Product.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.Product.dto.UserDto;

@Service
public class UserService {

    // @Autowired
    // private RestTemplate restTemplate;
    private final RestTemplate restTemplate;

    public UserService() {
        this.restTemplate = new RestTemplate();
    }

    private static final String USER_SERVICE_URL = "http://localhost:8082";

    public String[] getUserProfile(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", jwt);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String[]> response = restTemplate.exchange(
                    USER_SERVICE_URL + "/api/profile",
                    HttpMethod.GET,
                    requestEntity,
                    String[].class);

            return response.getBody();
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    public UserDto getBothProfile(String jwt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", jwt);
            HttpEntity<?> requestEntity = new HttpEntity<>(headers);

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

    // public Boolean getBoth2Profile(String jwt) {
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_JSON);
    // headers.set("Authorization", jwt);
    // HttpEntity<?> requestEntity = new HttpEntity<>(headers);

    // try {
    // ResponseEntity<String> response = restTemplate.exchange(
    // USER_SERVICE_URL + "/api/profile/both",
    // HttpMethod.GET,
    // requestEntity,
    // String.class);

    // return response.getBody();
    // } catch (HttpClientErrorException.Unauthorized unauthorizedException) {
    // throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    // }
    // }

    public UserDto getAdminProfile(String jwt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", jwt);
            HttpEntity<?> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<UserDto> response = restTemplate.exchange(
                    USER_SERVICE_URL + "/api/profile/admin",
                    HttpMethod.GET,
                    requestEntity,
                    UserDto.class);

            return response.getBody();
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

    }

}
