package com.example.distanceservice.controller;

import com.example.distanceservice.entity.City;
import com.example.distanceservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private static final String API_PATH = "/api/cities";

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAllCities() {
        try {
            return cityService.getAllCities();
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        try {
            return cityService.getCityById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping
    public City saveCity(@RequestBody City city) {
        try {
            return cityService.saveCity(city);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        try {
            cityService.deleteCity(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
