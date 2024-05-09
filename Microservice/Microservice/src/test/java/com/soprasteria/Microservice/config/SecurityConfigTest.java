package com.soprasteria.Microservice.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.soprasteria.Microservice.security.JwtAuthenticationEntryPoint;
import com.soprasteria.Microservice.security.JwtAuthenticationFilter;

@SpringJUnitConfig
@SpringBootTest
class SecurityConfigTest {

    @InjectMocks
    private SecurityConfig securityConfig;

    @Mock
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testSecurityFilterChain() throws Exception {

        SecurityFilterChain securityFilterChain = securityConfig.securityFilterChain(null);

        assertNotNull(securityFilterChain);
    }

    @Test
    void testDaoAuthenticationProvider() {
        // Act
        DaoAuthenticationProvider provider = securityConfig.daoAuthenticationProvider();

        // Assert
        assertNotNull(provider);
        // assertSame(userDetailsService, provider.getUserDetailsService());
        // assertSame(passwordEncoder, provider.getPasswordEncoder());
    }

    @Test
    void testDependenciesInjected() {
        // Assert
        assertNotNull(jwtAuthenticationEntryPoint);
        assertNotNull(jwtAuthenticationFilter);
        assertNotNull(userDetailsService);
        assertNotNull(passwordEncoder);
    }

    // Add more tests as needed to cover other aspects of the SecurityConfig class
}
