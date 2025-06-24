package com.example.distanceservice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestCounterTest {

    private static final int INITIAL_COUNT = 0;
    private static final int INCREMENT_VALUE = 1;

    private RequestCounter requestCounter;

    @BeforeEach
    void setUp() {
        requestCounter = new RequestCounter();
    }

    @Test
    void testIncrementTotal_IncreasesTotalCount() {
        requestCounter.incrementTotal();
        assertEquals(INITIAL_COUNT + INCREMENT_VALUE, requestCounter.getTotalRequests());
        requestCounter.incrementTotal();
        assertEquals(INITIAL_COUNT + 2 * INCREMENT_VALUE, requestCounter.getTotalRequests());
    }

    @Test
    void testIncrementSuccessful_IncreasesSuccessfulCount() {
        requestCounter.incrementSuccessful();
        assertEquals(INITIAL_COUNT + INCREMENT_VALUE, requestCounter.getSuccessfulRequests());
        requestCounter.incrementSuccessful();
        assertEquals(INITIAL_COUNT + 2 * INCREMENT_VALUE, requestCounter.getSuccessfulRequests());
    }

    @Test
    void testIncrementFailed_IncreasesFailedCount() {
        requestCounter.incrementFailed();
        assertEquals(INITIAL_COUNT + INCREMENT_VALUE, requestCounter.getFailedRequests());
        requestCounter.incrementFailed();
        assertEquals(INITIAL_COUNT + 2 * INCREMENT_VALUE, requestCounter.getFailedRequests());
    }

    @Test
    void testReset_SetsAllCountsToZero() {
        requestCounter.incrementTotal();
        requestCounter.incrementSuccessful();
        requestCounter.incrementFailed();
        requestCounter.reset();
        assertEquals(INITIAL_COUNT, requestCounter.getTotalRequests());
        assertEquals(INITIAL_COUNT, requestCounter.getSuccessfulRequests());
        assertEquals(INITIAL_COUNT, requestCounter.getFailedRequests());
    }
}
