package com.soprasteria.Microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.soprasteria.Microservice.security.JwtAuthenticationEntryPoint;
import com.soprasteria.Microservice.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

        @Autowired
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Autowired
        JwtAuthenticationFilter jwtAuthenticationFilter;

        @Autowired
        UserDetailsService userDetailsService;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf(csrf -> csrf.disable())
                                .cors(cors -> cors.disable())
                                .authorizeHttpRequests(
                                                auth -> auth.requestMatchers("/api/login").permitAll()

                                                                .requestMatchers(HttpMethod.POST, "/api/**")
                                                                .permitAll()
                                                                .requestMatchers(HttpMethod.POST, "/api/register**")
                                                                .permitAll()
                                                                .anyRequest().authenticated())
                                .exceptionHandling(
                                                exceptionHandling -> exceptionHandling
                                                                .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(userDetailsService);
                provider.setPasswordEncoder(passwordEncoder);
                return provider;

        }
}
