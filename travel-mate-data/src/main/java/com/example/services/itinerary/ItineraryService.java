package com.example.services.itinerary;


import com.example.model.Itinerary;
import com.example.services.CrudService;

import java.time.LocalDate;

public interface ItineraryService extends CrudService<Itinerary,Long> {

    Itinerary findByDestination(String destination);

    Itinerary findByStartDate(LocalDate startDate);

    Itinerary findByEndDate(LocalDate endDate);
}
