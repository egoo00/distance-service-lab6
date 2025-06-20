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
        requestCounter.incrementTotal();
        try {
            List<Country> countries = countryService.getAllCountries();
            requestCounter.incrementSuccessful();
            return countries;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Optional<Country> getCountryById(@PathVariable Long id) {
        requestCounter.incrementTotal();
        try {
            Optional<Country> country = countryService.getCountryById(id);
            requestCounter.incrementSuccessful();
            return country;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @PostMapping
    public Country saveCountry(@RequestBody Country country) {
        requestCounter.incrementTotal();
        try {
            Country savedCountry = countryService.saveCountry(country);
            requestCounter.incrementSuccessful();
            return savedCountry;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        requestCounter.incrementTotal();
        try {
            countryService.deleteCountry(id);
            requestCounter.incrementSuccessful();
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }
}
