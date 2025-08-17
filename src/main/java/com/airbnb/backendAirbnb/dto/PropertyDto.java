package com.airbnb.backendAirbnb.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PropertyDto {

    private Long id;

    @NotNull(message = "Owner ID must be provided")
    private Long ownerId;

    @NotNull(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "price per night must be provided")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal pricePerNight;

    private String imageUrl;

    private LocalDateTime createdAt;




}
