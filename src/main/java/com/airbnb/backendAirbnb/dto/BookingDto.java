package com.airbnb.backendAirbnb.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookingDto {



    @NotNull(message = "Start date must be provided")
    private LocalDate startDate;

    @NotNull(message = "End date must be provided")
    private LocalDate endDate;

    private String Day;
     private String User;


}
