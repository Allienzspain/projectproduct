package com.soprasteria.Microservice.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soprasteria.Microservice.dto.UserDto;
import com.soprasteria.Microservice.exceptionHandler.UserNotFoundException;
import com.soprasteria.Microservice.model.Role;
import com.soprasteria.Microservice.model.User;
import com.soprasteria.Microservice.repository.RoleRepository;
import com.soprasteria.Microservice.repository.UserRepository;
import com.soprasteria.Microservice.security.JwtHelper;
import com.soprasteria.Microservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User createUser(UserDto userDto) {
        User existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser != null) {
            throw new UserNotFoundException("This Username is already exist");
        } else {
            User user = modelMapper.map(userDto, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // fetch role of normal and set it to User
            Role role = roleRepository.findById(2).orElseThrow(() -> new UserNotFoundException("Role not found"));
            user.getRoles().add(role);

            return userRepository.save(user);
        }
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found of this userId"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            throw new UserNotFoundException("User Not Found of this Username");
        } else {
            modelMapper.map(userDto, existingUser);
            existingUser.setPassword(passwordEncoder.encode(existingUser.getPassword()));
            User updatedUser = userRepository.save(existingUser);
            return modelMapper.map(updatedUser, UserDto.class);
        }
    }

    @Override
    public void deleteUser(String username) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            existingUser.getRoles().clear(); // Clearing roles before deletion
            userRepository.save(existingUser); // Saving the user to update the association table
            userRepository.delete(existingUser);
        } else {
            throw new UserNotFoundException("User Not Found of this Username");
        }
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User Not Found of this Username");
        } else {
            return modelMapper.map(user, UserDto.class);
        }
    }

    @Override
    public UserDto getUserProfile(String jwt) {
        String username = JwtHelper.getUsernameFromJwtToken(jwt);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User Not Found of this Username");
        } else {
            return modelMapper.map(user, UserDto.class);
        }
    }

    @Override
    public UserDto getAdminProfile(String jwt) throws Exception {
        String username = JwtHelper.getUsernameFromJwtToken(jwt);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException("User Not Found of this Username");
        } else {
            boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ROLE_ADMIN"));

            if (!isAdmin) {
                throw new UserNotFoundException("User is not an admin");
            }

            return modelMapper.map(user, UserDto.class);
        }
    }

    @Override
    public UserDto getBothProfile(String jwt) throws Exception {
        String username = JwtHelper.getUsernameFromJwtToken(jwt);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException("User Not Found of this Username");
        } else {
            boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ROLE_ADMIN"));
            boolean isUser = user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ROLE_USER"));

            if (!isUser && !isAdmin) {
                throw new UserNotFoundException("User is not a regular user");
            }

            return modelMapper.map(user, UserDto.class);
        }
    }

}
