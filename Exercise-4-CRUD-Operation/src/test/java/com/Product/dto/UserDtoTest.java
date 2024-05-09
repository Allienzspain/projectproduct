package com.Product.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class UserDtoTest {

    @Test
    public void testUserDtoConstructor() {
        int userId = 1;
        String name = "John Doe";
        String email = "john@example.com";
        String username = "johndoe";
        String password = "password";
        Set<RoleDto> roles = new HashSet<>();

        UserDto userDto = new UserDto(userId, name, email, username, password, roles);

        assertEquals(userId, userDto.getUserId());
        assertEquals(name, userDto.getName());
        assertEquals(email, userDto.getEmail());
        assertEquals(username, userDto.getUsername());
        assertEquals(password, userDto.getPassword());
        assertEquals(roles, userDto.getRoles());
    }

    @Test
    public void testUserDtoSetters() {
        UserDto userDto = new UserDto();
        int userId = 1;
        String name = "John Doe";
        String email = "john@example.com";
        String username = "johndoe";
        String password = "password";
        Set<RoleDto> roles = new HashSet<>();

        userDto.setUserId(userId);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setRoles(roles);

        assertEquals(userId, userDto.getUserId());
        assertEquals(name, userDto.getName());
        assertEquals(email, userDto.getEmail());
        assertEquals(username, userDto.getUsername());
        assertEquals(password, userDto.getPassword());
        assertEquals(roles, userDto.getRoles());
    }

}