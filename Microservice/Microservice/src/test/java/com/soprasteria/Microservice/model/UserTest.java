package com.soprasteria.Microservice.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class UserTest {

    @Test
    void testGetAuthorities() {

        User user = new User();
        Role role = new Role();
        role.setRoleName("ROLE_ADMIN");
        user.getRoles().add(role);

        Set<GrantedAuthority> authorities = new HashSet<>(user.getAuthorities());

        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void testGetUsername() {

        User user = new User();
        user.setUsername("test");

        String username = user.getUsername();

        assertEquals("test", username);
    }

    @Test
    void testIsAccountNonExpired() {

        User user = new User();

        boolean accountNonExpired = user.isAccountNonExpired();

        assertTrue(accountNonExpired);
    }

    @Test
    void testIsAccountNonLocked() {

        User user = new User();

        boolean accountNonLocked = user.isAccountNonLocked();

        assertTrue(accountNonLocked);
    }

    @Test
    void testIsCredentialsNonExpired() {

        User user = new User();

        boolean credentialsNonExpired = user.isCredentialsNonExpired();

        assertTrue(credentialsNonExpired);
    }

    @Test
    void testIsEnabled() {

        User user = new User();

        boolean enabled = user.isEnabled();

        assertTrue(enabled);
    }

}
