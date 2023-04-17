package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "destinations")
public class Destination extends BaseEntity{

    private String name;
    private String country;
    private String description;
    private String image;
    private String currency;
}
