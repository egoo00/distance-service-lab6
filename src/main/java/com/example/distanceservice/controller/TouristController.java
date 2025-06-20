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
        requestCounter.increment();
        return touristService.getAllTourists();
    }

    @GetMapping("/{id}")
    public Optional<Tourist> getTouristById(@PathVariable Long id) {
        requestCounter.increment();
        return touristService.getTouristById(id);
    }

    @PostMapping
    public Tourist saveTourist(@RequestBody Tourist tourist) {
        requestCounter.increment();
        return touristService.saveTourist(tourist);
    }

    @DeleteMapping("/{id}")
    public void deleteTourist(@PathVariable Long id) {
        requestCounter.increment();
        touristService.deleteTourist(id);
    }
}
