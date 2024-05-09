package com.soprasteria.Microservice.security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.soprasteria.Microservice.service.serviceImpl.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilterTest {

    @InjectMocks
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Mock
    JwtHelper jwtHelper;

    @Mock
    UserDetailsService userDetailsService;

    @Test
    public void testDoFilterInternal() throws Exception {
        JwtHelper jwtHelper = mock(JwtHelper.class);
        CustomUserDetailsService userDetailsService = mock(CustomUserDetailsService.class);
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filter.jwtHelper = jwtHelper;
        filter.userDetailsService = userDetailsService;

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        String token = "test-token";
        String username = "user";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtHelper.getUsernameFromToken(token)).thenReturn(username);

        UserDetails userDetails = User.withUsername(username).password("password").authorities("ROLE_USER").build();
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        filter.doFilterInternal(request, response, filterChain);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        verify(userDetailsService).loadUserByUsername(username);
        verify(filterChain).doFilter(request, response);
        verify(request).getHeader("Authorization");
        verify(jwtHelper).getUsernameFromToken(token);
    }
}
