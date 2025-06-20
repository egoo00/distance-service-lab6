package com.example.distanceservice.controller;

import com.example.distanceservice.entity.Tourist;
import com.example.distanceservice.service.TouristService;
import com.example.distanceservice.util.RequestCounter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {
    private static final String API_PATH = "/api/tourists";

    private final TouristService touristService;
    private final RequestCounter requestCounter;

    public TouristController(TouristService touristService, RequestCounter requestCounter) {
        this.touristService = touristService;
        this.requestCounter = requestCounter;
    }

    @GetMapping
    public List<Tourist> getAllTourists() {
        requestCounter.incrementTotal();
        try {
            List<Tourist> tourists = touristService.getAllTourists();
            requestCounter.incrementSuccessful();
            return tourists;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Optional<Tourist> getTouristById(@PathVariable Long id) {
        requestCounter.incrementTotal();
        try {
            Optional<Tourist> tourist = touristService.getTouristById(id);
            requestCounter.incrementSuccessful();
            return tourist;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @PostMapping
    public Tourist saveTourist(@RequestBody Tourist tourist) {
        requestCounter.incrementTotal();
        try {
            Tourist savedTourist = touristService.saveTourist(tourist);
            requestCounter.incrementSuccessful();
            return savedTourist;
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTourist(@PathVariable Long id) {
        requestCounter.incrementTotal();
        try {
            touristService.deleteTourist(id);
            requestCounter.incrementSuccessful();
        } catch (Exception e) {
            requestCounter.incrementFailed();
            throw e;
        }
    }
}
