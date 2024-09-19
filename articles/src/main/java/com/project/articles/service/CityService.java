package com.project.articles.service;

import java.util.List;
import java.util.Set;

import com.project.articles.model.City;

public interface CityService {
    public List<City> findAll();
    public Set<String> getAllStates();
    public List<City> findByState(String state);
}
