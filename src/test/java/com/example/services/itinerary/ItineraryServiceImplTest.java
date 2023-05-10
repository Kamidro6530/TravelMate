package com.example.services.itinerary;

import com.example.model.Itinerary;
import com.example.model.repositories.ItineraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItineraryServiceImplTest {

    @Mock
    ItineraryRepository itineraryRepository;

    @InjectMocks
    ItineraryServiceImpl itineraryService;

    private static final LocalDate START_DATE = LocalDate.of(1000,1,1);
    private static final LocalDate END_DATE = LocalDate.of(2000,2,2);
    private static final String NOTE = "TEST";
    private static final Long ID = 1L;

    Itinerary returnItinerary;

    @BeforeEach
    void setUp() {
        returnItinerary = Itinerary.builder()
                .id(ID)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .notes(NOTE)
                .build();
    }

    @Test
    void save() {
        //when
        itineraryService.save(returnItinerary);
        //then
        verify(itineraryRepository,times(1)).save(any(Itinerary.class));
    }

    @Test
    void findById() {
        when(itineraryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(returnItinerary));
        Itinerary itinerary = itineraryService.findById(ID);
        //then
        assertNotNull(itinerary);
        assertEquals(returnItinerary,itinerary);
    }

    @Test
    void findAll() {
        //given
        Itinerary itinerary1 =  Itinerary.builder().notes("TEST1").id(1L).build();
        Itinerary itinerary2 = Itinerary.builder().notes("TEST2").id(2L).build();
        //when
        when(itineraryRepository.findAll()).thenReturn(List.of(itinerary1,itinerary2));
        List<Itinerary> itineraries = itineraryService.findAll().stream().toList();
        //then
        assertNotNull(itineraries);
        assertEquals(2,itineraries.size());
        assertTrue(itineraries.containsAll(List.of(itinerary1,itinerary2)));
    }

    @Test
    void deleteById() {
        //when
        itineraryService.deleteById(ID);
        //then
        verify(itineraryRepository,times(1)).deleteById(ID);
    }

    @Test
    void delete() {
        //when
        itineraryService.delete(returnItinerary);
        //then
        verify(itineraryRepository,times(1)).delete(returnItinerary);
    }

    @Test
    void findByDestination() {
        when(itineraryRepository.findByDestination(anyString())).thenReturn(returnItinerary);
        Itinerary itinerary = itineraryService.findByDestination("Destination");
        //then
        assertNotNull(itinerary);
        assertEquals(returnItinerary,itinerary);
    }

    @Test
    void findByStartDate() {
        when(itineraryRepository.findByStartDate(any(LocalDate.class))).thenReturn(returnItinerary);
        Itinerary itinerary = itineraryService.findByStartDate(START_DATE);
        //then
        assertNotNull(itinerary);
        assertEquals(returnItinerary,itinerary);
    }

    @Test
    void findByEndDate() {
        when(itineraryRepository.findByEndDate(any(LocalDate.class))).thenReturn(returnItinerary);
        Itinerary itinerary = itineraryService.findByEndDate(END_DATE);
        //then
        assertNotNull(itinerary);
        assertEquals(returnItinerary,itinerary);
    }
}