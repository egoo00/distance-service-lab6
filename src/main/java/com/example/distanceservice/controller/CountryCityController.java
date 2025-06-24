package com.example.distanceservice.controller;

import com.example.distanceservice.entity.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryCityController {
    private static final String API_PATH_CITIES_BY_COUNTRY = "/api/cities-by-country";
    private static final String API_PATH_CITIES_BY_COUNTRY_NATIVE = "/api/cities-by-country-native";

    @GetMapping(API_PATH_CITIES_BY_COUNTRY)
    public List<City> getCitiesByCountry(@RequestParam String countryName) {
        try {
            return null; // Логика обработки в сервисе
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping(API_PATH_CITIES_BY_COUNTRY_NATIVE)
    public List<City> getCitiesByCountryNative(@RequestParam String countryName) {
        try {
            return null; // Логика обработки в сервисе
        } catch (Exception e) {
            throw e;
        }
    }
}
