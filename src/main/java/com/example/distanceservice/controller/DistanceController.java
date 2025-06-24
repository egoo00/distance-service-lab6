package com.example.distanceservice.controller;

import com.example.distanceservice.dto.DistanceResponse;
import com.example.distanceservice.entity.CityPair;
import com.example.distanceservice.service.DistanceService;
import com.example.distanceservice.aspect.RequestCounterAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DistanceController {
    private static final String API_PATH_DISTANCE = "/distance";
    private static final String API_PATH_DISTANCES_BULK = "/distances/bulk";
    private static final String API_PATH_COUNTER = "/counter";
    private static final String API_PATH_RESET = "/reset";

    private final DistanceService distanceService;
    private final RequestCounterAspect requestCounterAspect;

    @Autowired
    public DistanceController(DistanceService distanceService, RequestCounterAspect requestCounterAspect) {
        this.distanceService = distanceService;
        this.requestCounterAspect = requestCounterAspect;
    }

    @GetMapping(API_PATH_DISTANCE)
    public DistanceResponse getDistance(@RequestParam String from, @RequestParam String to) {
        try {
            DistanceResponse response = distanceService.calculateDistance(from, to);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping(API_PATH_DISTANCES_BULK)
    public List<DistanceResponse> getBulkDistances(@RequestBody List<CityPair> cityPairs) {
        try {
            List<DistanceResponse> responses = distanceService.calculateBulkDistances(cityPairs);
            return responses;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping(API_PATH_COUNTER)
    public int getRequestCount() {
        return requestCounterAspect.getTotalRequests();
    }

    @GetMapping(API_PATH_RESET)
    public void resetCounter() {
        requestCounterAspect.reset();
    }
}
