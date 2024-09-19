package com.project.articles.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.articles.model.City;
import com.project.articles.service.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
@CrossOrigin(origins = "http://localhost:3000")
public class CityController {
    private final CityService cityService;

    @GetMapping("/all")
    public List<City> getAllCities() {
        return cityService.findAll();
    }

    @GetMapping("/allStates")
    public Set<String> allStates() {
        return cityService.getAllStates();
    }

    @GetMapping("")
    public List<City> getByState(@RequestParam String state) {
        return cityService.findByState(state);
    }
}
