package com.airbnb.backendAirbnb.controller;

import com.airbnb.backendAirbnb.dto.BookingDto;
import com.airbnb.backendAirbnb.dto.PropertyDto;
import com.airbnb.backendAirbnb.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController             // is used for handling HTTP requests and sending HTTP responses in a RESTful way.
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    // Create booking
    // http://localhost:8081/api/bookings

}
