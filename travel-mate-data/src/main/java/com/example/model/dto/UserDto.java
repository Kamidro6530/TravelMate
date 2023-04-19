package com.example.model.dto;

import com.example.model.Itineraries;

import java.time.LocalDate;
import java.util.Set;

public class UserDto extends BaseDto{

    private String firstName;

    private String lastName;

    private String eMail;

    private String password;

    private LocalDate birthDate;

    private Set<Itineraries> itineraries;
}
