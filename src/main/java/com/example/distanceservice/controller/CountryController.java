package com.example.distanceservice.controller;

import com.example.distanceservice.entity.Country;
import com.example.distanceservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private static final String API_PATH = "/api/countries";

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        try {
            return countryService.getAllCountries();
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Optional<Country> getCountryById(@PathVariable Long id) {
        try {
            return countryService.getCountryById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping
    public Country saveCountry(@RequestBody Country country) {
        try {
            return countryService.saveCountry(country);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        try {
            countryService.deleteCountry(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
