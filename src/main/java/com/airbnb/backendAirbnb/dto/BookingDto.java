package com.airbnb.backendAirbnb.dto;

import com.airbnb.backendAirbnb.entity.Property;
import com.airbnb.backendAirbnb.entity.User;
import com.airbnb.backendAirbnb.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Property relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    // Guest relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private User guest;

    @NotNull(message = "Start date must be provided") // required
    @FutureOrPresent(message = "Start date cannot be in the past") // must be today or future
    private LocalDate startDate;

    @NotNull(message = "End date must be provided") // required
    @Future(message = "End date must be in the future") // must be strictly future
    private LocalDate endDate;

    @NotNull(message = "Number of guests must be provided") // required
    @Min(value = 1, message = "At least 1 guest is required") // must be at least 1
    private Integer guests; // ✅ added for guest count

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();





}
