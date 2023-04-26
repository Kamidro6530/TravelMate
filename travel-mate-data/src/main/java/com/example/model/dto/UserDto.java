package com.example.model.dto;

import com.example.model.Itineraries;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDto extends BaseDto{

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String username;

    private LocalDate birthDate;

    private Set<Itineraries> itineraries;
}
