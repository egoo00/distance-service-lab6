package com.example.distanceservice.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestCounterTest {

    private RequestCounter requestCounter;

    @BeforeEach
    void setUp() {
        requestCounter = new RequestCounter();
    }

    @Test
    void testIncrementTotal_IncreasesTotalCount() {
        requestCounter.incrementTotal();
        assertEquals(1, requestCounter.getTotalRequests());
        requestCounter.incrementTotal();
        assertEquals(2, requestCounter.getTotalRequests());
    }

    @Test
    void testIncrementSuccessful_IncreasesSuccessfulCount() {
        requestCounter.incrementSuccessful();
        assertEquals(1, requestCounter.getSuccessfulRequests());
        requestCounter.incrementSuccessful();
        assertEquals(2, requestCounter.getSuccessfulRequests());
    }

    @Test
    void testIncrementFailed_IncreasesFailedCount() {
        requestCounter.incrementFailed();
        assertEquals(1, requestCounter.getFailedRequests());
        requestCounter.incrementFailed();
        assertEquals(2, requestCounter.getFailedRequests());
    }

    @Test
    void testReset_SetsAllCountsToZero() {
        requestCounter.incrementTotal();
        requestCounter.incrementSuccessful();
        requestCounter.incrementFailed();
        requestCounter.reset();
        assertEquals(0, requestCounter.getTotalRequests());
        assertEquals(0, requestCounter.getSuccessfulRequests());
        assertEquals(0, requestCounter.getFailedRequests());
    }
}
