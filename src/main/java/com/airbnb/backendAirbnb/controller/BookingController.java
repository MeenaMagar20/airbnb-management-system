package com.airbnb.backendAirbnb.controller;

import com.airbnb.backendAirbnb.dto.BookingDto;
import com.airbnb.backendAirbnb.dto.PropertyDto;
import com.airbnb.backendAirbnb.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController             // is used for handling HTTP requests and sending HTTP responses in a RESTful way.
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    // Create booking
    // http://localhost:8081/api/bookings




    // GET all bookings
    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<BookingDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

}
