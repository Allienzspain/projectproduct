// package com.Product.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.http.HttpMethod;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// public class SecurityConfig {
// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http.csrf(csrf -> csrf.disable())
// .cors(cors -> cors.disable())
// .authorizeHttpRequests(
// auth -> auth.requestMatchers(HttpMethod.POST,
// "/product/createProduct").hasRole("ROLE_ADMIN"));

// return http.build();
// }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
// }

// }
