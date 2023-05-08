package com.example.services.destination;

import com.example.model.Destination;
import com.example.services.CrudService;

public interface DestinationService extends CrudService<Destination,Long> {

    Destination findByName(String name);

    Destination findByCountry(String country);

    Destination findByDescription(String description);

    Destination findByCurrency(String currency);
}
