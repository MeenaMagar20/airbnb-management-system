package com.airbnb.backendAirbnb.service;

import com.airbnb.backendAirbnb.dto.LoginResponseDto;
import com.airbnb.backendAirbnb.dto.UserDto;
import com.airbnb.backendAirbnb.entity.User;
import com.airbnb.backendAirbnb.enums.Role;
import com.airbnb.backendAirbnb.exception.ResourceNotFoundException;
import com.airbnb.backendAirbnb.mapper.UserMapper;
import com.airbnb.backendAirbnb.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user
    public void registerUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);

        // Set default role if none provided
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }


        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    // Login user
    public LoginResponseDto loginUser(UserDto loginRequest) {
        return userRepository.findByEmail(loginRequest.getEmail())
                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
                .map(user -> new LoginResponseDto("dummy-token", user.getRole().name(), user.getName()))
                .orElse(null);
    }

    public User getUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

}
