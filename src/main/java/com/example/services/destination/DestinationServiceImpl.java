package com.example.services.destination;

import com.example.model.Destination;
import com.example.model.repositories.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }


    @Override
    public Destination save(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public Destination findById(Long id) {
        return destinationRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Destination> findAll() {
        return new HashSet<>(destinationRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        destinationRepository.deleteById(id);
    }

    @Override
    public void delete(Destination destination) {
        destinationRepository.delete(destination);
    }

    @Override
    public Destination findByName(String name) {
        return destinationRepository.findByName(name);
    }

    @Override
    public Destination findByCountry(String country) {
        return destinationRepository.findByCountry(country);
    }

    @Override
    public Destination findByDescription(String description) {
        return destinationRepository.findByDescription(description);
    }

    @Override
    public Destination findByCurrency(String currency) {
        return destinationRepository.findByCurrency(currency);
    }
}
