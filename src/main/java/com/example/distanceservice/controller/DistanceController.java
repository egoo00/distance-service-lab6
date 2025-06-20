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
        requestCounter.incrementTotal();
        try {
            DistanceResponse response = distanceService.calculateDistance(from, to);
            requestCounter.incrementSuccessful();
            return response;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @PostMapping(API_PATH_DISTANCES_BULK)
    public List<DistanceResponse> getBulkDistances(@RequestBody List<String[]> cityPairs) {
        requestCounter.incrementTotal();
        try {
            List<DistanceResponse> responses = distanceService.calculateBulkDistances(cityPairs);
            requestCounter.incrementSuccessful();
            return responses;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @GetMapping(API_PATH_COUNTER)
    public int getRequestCount() {
        requestCounter.incrementTotal();
        try {
            int count = requestCounter.getTotalRequests();
            requestCounter.incrementSuccessful();
            return count;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @GetMapping(API_PATH_RESET)
    public void resetCounter() {
        requestCounter.incrementTotal();
        try {
            requestCounter.reset();
            requestCounter.incrementSuccessful();
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }
}
