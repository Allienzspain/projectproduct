package com.soprasteria.Microservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.soprasteria.Microservice.dto.UserDto;
import com.soprasteria.Microservice.model.Role;
import com.soprasteria.Microservice.model.User;
import com.soprasteria.Microservice.repository.RoleRepository;
import com.soprasteria.Microservice.repository.UserRepository;
import com.soprasteria.Microservice.service.serviceImpl.UserServiceImpl;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper; // Mocked modelMapper

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks
    }

    @Test
    void testCreateUser_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("john@example.com");
        userDto.setUsername("johndoe");
        userDto.setPassword("password123");

        Role role = new Role();
        role.setRoleId(2); // Assuming ID 2 is for normal user
        when(roleRepository.findById(2)).thenReturn(Optional.of(role));

        when(userRepository.findByUsername("johndoe")).thenReturn(null);

        // Act
        User result = userService.createUser(userDto);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("johndoe", result.getUsername());
        assertEquals("password123", result.getPassword());
        assertEquals(Collections.singleton(role), result.getRoles());
    }

    // Add more test cases
}
