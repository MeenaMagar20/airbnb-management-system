package com.airbnb.backendAirbnb.mapper;

import com.airbnb.backendAirbnb.dto.UserDto;
import com.airbnb.backendAirbnb.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Convert DTO → Entity
    public User toEntity(UserDto dto) {
        if (dto == null) return null;

        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword()) // encode later in service
                .role(dto.getRole())
                .build();
    }

    // Convert Entity → DTO
    public UserDto toDto(User user) {
        if (user == null) return null;

        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                // Don't include password when sending to client
                .build();
    }
}
