package com.soprasteria.Microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.Microservice.dto.UserDto;
import com.soprasteria.Microservice.exceptionHandler.UserNotFoundException;
import com.soprasteria.Microservice.model.User;
import com.soprasteria.Microservice.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User saveUser(@RequestBody UserDto userDto) {

        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public UserDto updateUser(@PathVariable("id") String username, @RequestBody UserDto userDto) {
        return userService.updateUser(username, userDto);
    }

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getAllUser();
    }

    @PutMapping("/updateByUsername/{username}")
    public UserDto updateUserByUsername(@PathVariable("username") String username, @RequestBody UserDto userDto) {
        return userService.updateUser(username, userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
    }

    @GetMapping("/profile")
    // @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) {
        try {
            UserDto userDto = userService.getUserProfile(jwt);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @GetMapping("/profile/admin")
    // public ResponseEntity<UserDto>
    // getAdminProfile(@RequestHeader("Authorization") String jwt) throws Exception
    // {
    // // try {
    // UserDto userProfile = userService.getAdminProfile(jwt);
    // return new ResponseEntity<>(userProfile, HttpStatus.OK);
    // // } catch (UserNotFoundException e) {
    // // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // // }
    // }
    @GetMapping("/profile/admin")
    public ResponseEntity<UserDto> getAdminProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        try {
            UserDto userProfile = userService.getAdminProfile(jwt);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/profile/both")
    public ResponseEntity<UserDto> getRoleUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        try {
            UserDto userProfile = userService.getBothProfile(jwt);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
