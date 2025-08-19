package com.airbnb.backendAirbnb.service;

import com.airbnb.backendAirbnb.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
}
