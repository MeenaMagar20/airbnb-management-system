package com.airbnb.backendAirbnb.mapper;

import com.airbnb.backendAirbnb.dto.PropertyDto;
import com.airbnb.backendAirbnb.entity.Property;
import com.airbnb.backendAirbnb.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component  // <- This is important for Spring to manage the bean
public class PropertyMapper {

    // Convert DTO to Entity
    public Property toEntity(PropertyDto dto, User owner) {
        return Property.builder()
                .owner(owner)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .pricePerNight(dto.getPricePerNight())
                .imageUrl(dto.getImageUrl())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                .build();
    }

    // Convert Entity to DTO
    public PropertyDto toDto(Property property) {
        return PropertyDto.builder()
                .id(property.getId())
                .ownerId(property.getOwner() != null ? property.getOwner().getId() : null)
                .title(property.getTitle())
                .description(property.getDescription())
                .location(property.getLocation())
                .pricePerNight(property.getPricePerNight())
                .imageUrl(property.getImageUrl())
               // .createdAt(property.getCreatedAt())
                .build();
    }
}
