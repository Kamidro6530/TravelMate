package com.example.model.dto;

import com.example.model.Itinerary;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto{

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private Set<Itinerary> itineraries;


    @Builder
    public UserDto(Long id,String username,String firstName,String lastName,
                String email,LocalDate birthDate,Set<Itinerary> itineraries){
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.itineraries = itineraries;
    }
}
