package com.airbnb.backendAirbnb.mapper;




import com.airbnb.backendAirbnb.dto.BookingDto;

import com.airbnb.backendAirbnb.entity.Booking;
import com.airbnb.backendAirbnb.entity.Property;
import com.airbnb.backendAirbnb.entity.User;
import com.airbnb.backendAirbnb.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    // Convert BookingDto → Booking entity
    public Booking toEntity(BookingDto dto, Property property, User guest) {
        if (dto == null) return null;

        return Booking.builder()
                .id(dto.getId())
                .property(property)       // ✅ Property entity
                .guest(guest)             // ✅ User entity
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .guests(dto.getGuests())
                .status(dto.getStatus() != null ? dto.getStatus() : Status.PENDING) // ✅ no conversion needed
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : java.time.LocalDateTime.now())
                .build();
    }

    // Convert Booking entity → BookingDto
    public BookingDto toDto(Booking booking) {
        if (booking == null) return null;

        return BookingDto.builder()
                .id(booking.getId())
                .property(booking.getProperty())   // ✅ direct mapping
                .guest(booking.getGuest())         // ✅ direct mapping
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .guests(booking.getGuests())
                .status(booking.getStatus())       // ✅ direct mapping
                .createdAt(booking.getCreatedAt())
                .build();
    }
}
