package com.airbnb.backendAirbnb.controller;

import com.airbnb.backendAirbnb.dto.PropertyDto;
import com.airbnb.backendAirbnb.entity.Property;
import com.airbnb.backendAirbnb.entity.User;
import com.airbnb.backendAirbnb.mapper.PropertyMapper;
import com.airbnb.backendAirbnb.service.PropertyService;
import com.airbnb.backendAirbnb.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController             // is used for handling HTTP requests and sending HTTP responses in a RESTful way.
@RequiredArgsConstructor
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    // Create property
    // http://localhost:8081/api/properties
    @PostMapping
    public ResponseEntity<PropertyDto> createProperty(@RequestBody @Valid PropertyDto propertyDto) {
        PropertyDto created = propertyService.createProperty(propertyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @GetMapping
    public ResponseEntity<List<PropertyDto>> getAllProperties(){
        System.out.println("Listing Property");
        return ResponseEntity.ok(propertyService.getAllProperties());

    }


    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Long id) {
        PropertyDto property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDto> updateProperty(@PathVariable Long id,
                                                      @RequestBody PropertyDto propertyDto){
        PropertyDto updated = propertyService.updateProperty(id, propertyDto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<PropertyDto> deletePropertyById(@PathVariable Long id){
       propertyService.deletePropertyById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    

}
