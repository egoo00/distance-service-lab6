package com.example.distanceservice.controller;

import com.example.distanceservice.entity.Country;
import com.example.distanceservice.service.CountryService;
import com.example.distanceservice.util.RequestCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private static final String API_PATH = "/api/countries";

    private final CountryService countryService;
    private final RequestCounter requestCounter;

    @Autowired
    public CountryController(CountryService countryService, RequestCounter requestCounter) {
        this.countryService = countryService;
        this.requestCounter = requestCounter;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        requestCounter.increment();
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Optional<Country> getCountryById(@PathVariable Long id) {
        requestCounter.increment();
        return countryService.getCountryById(id);
    }

    @PostMapping
    public Country saveCountry(@RequestBody Country country) {
        requestCounter.increment();
        return countryService.saveCountry(country);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        requestCounter.increment();
        countryService.deleteCountry(id);
    }
}
