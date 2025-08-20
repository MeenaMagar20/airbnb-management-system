package com.airbnb.backendAirbnb.service;

import com.airbnb.backendAirbnb.dto.BookingDto;
import com.airbnb.backendAirbnb.mapper.BookingMapper;
import com.airbnb.backendAirbnb.repository.BookingRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public List<BookingDto> getAllBookings() {
        System.out.println("Main Booking Service");
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toDto) // use the instance, not class reference
                .toList();

    }
    public String booking(){
        return "okay";

    }

}
