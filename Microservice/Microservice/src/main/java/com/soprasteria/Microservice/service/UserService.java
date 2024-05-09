package com.soprasteria.Microservice.service;

import java.util.List;

import com.soprasteria.Microservice.dto.UserDto;
import com.soprasteria.Microservice.model.User;

public interface UserService {

    User createUser(UserDto userDto);

    List<UserDto> getAllUser();

    UserDto getUserById(int userId);

    UserDto updateUser(String username, UserDto userDto);

    void deleteUser(String username);

    UserDto getUserByUsername(String username);

    UserDto getUserProfile(String jwt);

    UserDto getBothProfile(String jwt) throws Exception;

    UserDto getAdminProfile(String jwt) throws Exception;

}
