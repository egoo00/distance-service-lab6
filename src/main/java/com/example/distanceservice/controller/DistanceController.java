package com.example.distanceservice.controller;

import com.example.distanceservice.dto.DistanceResponse;
import com.example.distanceservice.service.DistanceService;
import com.example.distanceservice.util.RequestCounter;
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
    private final RequestCounter requestCounter;

    @Autowired
    public DistanceController(DistanceService distanceService, RequestCounter requestCounter) {
        this.distanceService = distanceService;
        this.requestCounter = requestCounter;
    }

    @GetMapping(API_PATH_DISTANCE)
    public DistanceResponse getDistance(@RequestParam String from, @RequestParam String to) {
        requestCounter.increment();
        return distanceService.calculateDistance(from, to);
    }

    @PostMapping(API_PATH_DISTANCES_BULK)
    public List<DistanceResponse> getBulkDistances(@RequestBody List<String[]> cityPairs) {
        requestCounter.increment();
        return distanceService.calculateBulkDistances(cityPairs);
    }

    @GetMapping(API_PATH_COUNTER)
    public int getRequestCount() {
        requestCounter.increment();
        return requestCounter.getCount();
    }

    @GetMapping(API_PATH_RESET)
    public void resetCounter() {
        requestCounter.increment();
        requestCounter.reset();
    }
}
