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
        requestCounter.incrementTotal();
        try {
            List<City> cities = cityService.getAllCities();
            requestCounter.incrementSuccessful();
            return cities;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        requestCounter.incrementTotal();
        try {
            Optional<City> city = cityService.getCityById(id);
            requestCounter.incrementSuccessful();
            return city;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @PostMapping
    public City saveCity(@RequestBody City city) {
        requestCounter.incrementTotal();
        try {
            City savedCity = cityService.saveCity(city);
            requestCounter.incrementSuccessful();
            return savedCity;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        requestCounter.incrementTotal();
        try {
            cityService.deleteCity(id);
            requestCounter.incrementSuccessful();
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }
}
