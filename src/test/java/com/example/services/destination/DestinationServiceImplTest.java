package com.example.services.destination;

import com.example.model.Destination;
import com.example.model.repositories.DestinationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DestinationServiceImplTest {

    @Mock
    DestinationRepository destinationRepository;

    @InjectMocks
    DestinationServiceImpl destinationService;

    private static final String NAME = "TEST_NAME";
    private static final Long ID = 1L;
    private static final String COUNTRY = "TEST_COUNTRY";
    private static final String DESCRIPTION = "TEST_DESCRIPTION";
    private static final String CURRENCY = "TEST_CURRENCY";

    Destination returnDestination;

    @BeforeEach
    void setUp() {
        returnDestination = Destination.builder()
                .id(ID)
                .name(NAME)
                .country(COUNTRY)
                .description(DESCRIPTION)
                .currency(CURRENCY)
                .build();
    }

    @Test
    void save() {
        //when
        destinationService.save(returnDestination);
        //then
        verify(destinationRepository,times(1)).save(any(Destination.class));
    }

    @Test
    void findById() {
        when(destinationRepository.findById(anyLong())).thenReturn(Optional.ofNullable(returnDestination));
        Destination destination = destinationService.findById(ID);
        //then
        assertNotNull(destination);
        assertEquals(returnDestination,destination);
    }

    @Test
    void findAll() {
        //given
        Destination destination1 =  Destination.builder().name("destination1").id(1L).build();
        Destination destination2 = Destination.builder().name("destination2").id(2L).build();
        //when
        when(destinationRepository.findAll()).thenReturn(List.of(destination1,destination2));
        List<Destination> destinations = destinationService.findAll().stream().toList();
        //then
        assertNotNull(destinations);
        assertEquals(2,destinations.size());
        assertTrue(destinations.containsAll(List.of(destination1,destination2)));
    }

    @Test
    void deleteById() {
        //when
        destinationService.deleteById(ID);
        //then
        verify(destinationRepository,times(1)).deleteById(ID);
    }

    @Test
    void delete() {
        //when
        destinationService.delete(returnDestination);
        //then
        verify(destinationRepository,times(1)).delete(returnDestination);
    }

    @Test
    void findByName() {
        when(destinationRepository.findByName(anyString())).thenReturn(returnDestination);
        Destination destination = destinationService.findByName("Name");
        //then
        assertNotNull(destination);
        assertEquals(returnDestination,destination);
    }

    @Test
    void findByCountry() {
        when(destinationRepository.findByCountry(anyString())).thenReturn(returnDestination);
        Destination destination = destinationService.findByCountry("Country");
        //then
        assertNotNull(destination);
        assertEquals(returnDestination,destination);
    }

    @Test
    void findByDescription() {
        when(destinationRepository.findByDescription(anyString())).thenReturn(returnDestination);
        Destination destination = destinationService.findByDescription("Description");
        //then
        assertNotNull(destination);
        assertEquals(returnDestination,destination);
    }

    @Test
    void findByCurrency() {
        when(destinationRepository.findByCurrency(anyString())).thenReturn(returnDestination);
        Destination destination = destinationService.findByCurrency("Currency");
        //then
        assertNotNull(destination);
        assertEquals(returnDestination,destination);
    }
}