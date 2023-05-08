package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "destinations")
public class Destination extends BaseEntity{

    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "country is required")
    private String country;
    private String description;
    private String image;
    private String currency;
}