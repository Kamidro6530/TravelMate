package com.example.model.repositories;

import com.example.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination,Long> {

    Destination findByName(String name);
    Destination findByCountry(String country);
    Destination findByDescription(String description);
    Destination findByCurrency(String currency);
}
