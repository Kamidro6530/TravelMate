package com.example.model.repositories;


import com.example.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ItineraryRepository extends JpaRepository<Itinerary,Long> {

    Itinerary findByDestination(String destination);
    Itinerary findByStartDate(LocalDate startDate);
    Itinerary findByEndDate(LocalDate endDate);
}
