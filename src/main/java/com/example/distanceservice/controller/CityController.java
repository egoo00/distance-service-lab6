package com.example.distanceservice.controller;

import com.example.distanceservice.entity.City;
import com.example.distanceservice.service.CityService;
import com.example.distanceservice.util.RequestCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private static final String API_PATH = "/api/cities";

    private final CityService cityService;
    private final RequestCounter requestCounter;

    @Autowired
    public CityController(CityService cityService, RequestCounter requestCounter) {
        this.cityService = cityService;
        this.requestCounter = requestCounter;
    }

    @GetMapping
    public List<City> getAllCities() {
        requestCounter.increment();
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        requestCounter.increment();
        return cityService.getCityById(id);
    }

    @PostMapping
    public City saveCity(@RequestBody City city) {
        requestCounter.increment();
        return cityService.saveCity(city);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        requestCounter.increment();
        cityService.deleteCity(id);
    }
}
