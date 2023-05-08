package com.example.services.itinerary;

import com.example.model.Itinerary;
import com.example.model.repositories.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class ItineraryServiceImpl implements ItineraryService{

    private final ItineraryRepository itineraryRepository;

    public ItineraryServiceImpl(ItineraryRepository itineraryRepository) {
        this.itineraryRepository = itineraryRepository;
    }

    @Override
    public Itinerary save(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    @Override
    public Itinerary findById(Long id) {
        return itineraryRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Itinerary> findAll() {
        return new HashSet<>(itineraryRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        itineraryRepository.deleteById(id);
    }

    @Override
    public void delete(Itinerary itinerary) {
        itineraryRepository.delete(itinerary);

    }

    @Override
    public Itinerary findByDestination(String destination) {
        return itineraryRepository.findByDestination(destination);
    }

    @Override
    public Itinerary findByStartDate(LocalDate startDate) {
        return itineraryRepository.findByStartDate(startDate);
    }

    @Override
    public Itinerary findByEndDate(LocalDate endDate) {
        return itineraryRepository.findByEndDate(endDate);
    }
}
