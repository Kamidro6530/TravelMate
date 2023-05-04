package com.example.model.repositories;

import com.example.model.Itineraries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ItinerariesRepository extends JpaRepository<Itineraries,Long> {

    Itineraries findByDestination(String destination);
    Itineraries findByStartDate(LocalDate startDate);
    Itineraries findByEndDate(LocalDate endDate);
}
