package com.airbnb.backendAirbnb.repository;

import com.airbnb.backendAirbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    // You can add custom queries here if needed in the future
}
