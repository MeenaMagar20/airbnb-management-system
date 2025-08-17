package com.airbnb.backendAirbnb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


    @Entity
    @Table(name = "properties")
    @Data
    @Builder                    // @Builder makes creating objects easy, readable, and flexible.
    @NoArgsConstructor
    @AllArgsConstructor
    public class Property {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Owner relationship
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "owner_id", nullable = false)
        private User owner;

        @NotBlank(message = "Title cannot be blank")
        private String title;

        @NotBlank(message = "Description cannot be blank")
        @Column(columnDefinition = "TEXT")
        private String description;

        @NotBlank(message = "Location cannot be blank")
        private String location;

        @NotNull(message = "Price per night must be provided")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
        private BigDecimal pricePerNight;

        private String imageUrl;

        private LocalDateTime createdAt = LocalDateTime.now();
    }


