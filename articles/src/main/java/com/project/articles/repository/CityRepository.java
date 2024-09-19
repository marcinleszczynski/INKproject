package com.project.articles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.articles.model.City;
import com.project.articles.model.CityKey;

public interface CityRepository extends JpaRepository<City, CityKey> {
    public List<City> findByState(String state);
}
