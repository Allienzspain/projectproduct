package com.Product.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int userId;

    private String name;

    private String email;

    private String username;

    private String password;
    private Set<RoleDto> roles = new HashSet<>();
}
