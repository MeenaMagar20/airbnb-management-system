package com.airbnb.backendAirbnb.service;

import com.airbnb.backendAirbnb.dto.PropertyDto;
import com.airbnb.backendAirbnb.entity.Property;
import com.airbnb.backendAirbnb.entity.User;
import com.airbnb.backendAirbnb.enums.Role;
import com.airbnb.backendAirbnb.exception.ResourceNotFoundException;
import com.airbnb.backendAirbnb.exception.UnauthorizedActionException;
import com.airbnb.backendAirbnb.mapper.PropertyMapper;
import com.airbnb.backendAirbnb.repository.PropertyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {


    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final UserService userService; // To fetch the owner

    @Transactional                  // Ensures all DB operations in this method are executed as a single unit; rolls back if any operation fails

    public PropertyDto createProperty(PropertyDto dto) {
        // 1️⃣ Fetch owner, throws exception if not found
        User owner = userService.getUserByIdOrThrow(dto.getOwnerId());

        // 2️⃣ Check if the user has the OWNER role
        if (owner.getRole() != Role.OWNER) {
            throw new UnauthorizedActionException("Only users with role OWNER can create properties");
        }

        // 3️⃣ Convert DTO + owner to entity
        Property property = propertyMapper.toEntity(dto, owner);

        // 4️⃣ Save entity
        Property saved = propertyRepository.save(property);

        // 5️⃣ Convert back to DTO
        return propertyMapper.toDto(saved);

    }

    public List<PropertyDto> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(propertyMapper::toDto)   // ✅ instance method
                .collect(Collectors.toList());
    }


    public PropertyDto getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .map(propertyMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
    }


    public PropertyDto updateProperty(Long id, PropertyDto propertyDto){
        // Fetch existing property
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));

        // Update fields
        existing.setTitle(propertyDto.getTitle());
        existing.setDescription(propertyDto.getDescription());
        existing.setLocation(propertyDto.getLocation());
        existing.setPricePerNight(propertyDto.getPricePerNight());
        existing.setImageUrl(propertyDto.getImageUrl());

        // ave updated entity
        Property saved = propertyRepository.save(existing);

        //  Convert to DTO
        return propertyMapper.toDto(saved);

    }


    public String deletePropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
        propertyRepository.delete(property);
        return "Property with id " + id + " has been deleted successfully.";
    }


}
