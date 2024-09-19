package com.project.articles.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.project.articles.model.City;
import com.project.articles.repository.CityRepository;
import com.project.articles.service.CityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImplementation implements CityService {
    private final CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Set<String> getAllStates() {
        List<String> states = findAll().stream().map(city -> city.getState()).toList();
        return new HashSet<>(states);
    }

    @Override
    public List<City> findByState(String state) {
        return cityRepository.findByState(state);
    }

}
