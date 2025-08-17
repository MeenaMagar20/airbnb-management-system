package com.airbnb.backendAirbnb.controller;

import com.airbnb.backendAirbnb.dto.LoginResponseDto;
import com.airbnb.backendAirbnb.dto.UserDto;
import com.airbnb.backendAirbnb.entity.User;
import com.airbnb.backendAirbnb.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController                     // Create REST API endpoints that return JSON data directly. is used to build endpoints that communicate between a client (like a frontend app or Postman) and your server, by sending and receiving data (usually JSON).
@RequiredArgsConstructor                // For Constructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;


    // http://localhost:8081/api/users/register
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody UserDto userDto) {
        // Method returns an HTTP response with a Map containing messages
        // @RequestBody tells Spring to convert incoming JSON to UserDto
        // @Valid triggers validation annotations in UserDto (like @NotBlank)

        userService.registerUser(userDto);
        // Calls the service layer to handle the actual user registration logic

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "User registered successfully"));
    }


    // http://localhost:8081/api/users/login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody UserDto loginRequest) {
        // The service takes in credentials and returns login response
        LoginResponseDto response = userService.loginUser(loginRequest);

        if (response != null) {
            return ResponseEntity.ok(response); // 200 OK with token, role, etc.
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 if login fails
    }



}
