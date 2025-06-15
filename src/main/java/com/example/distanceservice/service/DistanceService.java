package com.example.distanceservice.service;

import com.example.distanceservice.dto.DistanceResponse;
import com.example.distanceservice.entity.City;
import com.example.distanceservice.exception.CityNotFoundException;
import com.example.distanceservice.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistanceService {
    private final CityRepository cityRepository;
    private final GeocodingService geocodingService;
    private final RequestCounter requestCounter;

    @Autowired
    public DistanceService(CityRepository cityRepository, GeocodingService geocodingService, RequestCounter requestCounter) {
        this.cityRepository = cityRepository;
        this.geocodingService = geocodingService;
        this.requestCounter = requestCounter;
    }

    public DistanceResponse calculateDistance(String city1, String city2) {
        requestCounter.increment();
        City c1 = cityRepository.findByName(city1).orElseGet(() -> geocodingService.getCity(city1));
        if (c1 == null) {
            throw new CityNotFoundException("City " + city1 + " not found");
        }
        City c2 = cityRepository.findByName(city2).orElseGet(() -> geocodingService.getCity(city2));
        if (c2 == null) {
            throw new CityNotFoundException("City " + city2 + " not found");
        }
        double distance = calculateHaversine(c1.getLatitude(), c1.getLongitude(), c2.getLatitude(), c2.getLongitude());
        return new DistanceResponse(city1, city2, distance, "km");
    }

    public List<DistanceResponse> calculateBulkDistances(List<String[]> cityPairs) {
        return cityPairs.stream()
                .map(pair -> {
                    String city1 = pair[0];
                    String city2 = pair[1];
                    try {
                        return calculateDistance(city1, city2);
                    } catch (CityNotFoundException e) {
                        return new DistanceResponse(city1, city2, -1, "km");
                    }
                })
                .collect(Collectors.toList());
    }

    private double calculateHaversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the Earth in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}