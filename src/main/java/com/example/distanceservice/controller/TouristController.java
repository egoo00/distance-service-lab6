package com.example.distanceservice.controller;

import com.example.distanceservice.entity.Tourist;
import com.example.distanceservice.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {
    private static final String API_PATH = "/api/tourists";

    private final TouristService touristService;

    @Autowired
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    public List<Tourist> getAllTourists() {
        try {
            return touristService.getAllTourists();
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Optional<Tourist> getTouristById(@PathVariable Long id) {
        try {
            return touristService.getTouristById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping
    public Tourist saveTourist(@RequestBody Tourist tourist) {
        try {
            return touristService.saveTourist(tourist);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTourist(@PathVariable Long id) {
        try {
            touristService.deleteTourist(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
