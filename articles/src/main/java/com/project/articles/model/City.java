package com.project.articles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity
@IdClass(CityKey.class)
public class City {
    @Id
    private String name;
    @Id
    private String state;
}
