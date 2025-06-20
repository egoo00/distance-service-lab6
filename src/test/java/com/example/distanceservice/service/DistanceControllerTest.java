package com.example.distanceservice.controller;

import com.example.distanceservice.dto.DistanceResponse;
import com.example.distanceservice.service.DistanceService;
import com.example.distanceservice.util.RequestCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static com.example.distanceservice.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DistanceControllerTest {

    @Mock
    private DistanceService distanceService;

    @Mock
    private RequestCounter requestCounter;

    @InjectMocks
    private DistanceController distanceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDistance_ValidCities_IncrementsCounters() {
        DistanceResponse response = new DistanceResponse(CITY_MINSK, CITY_WARSAW, EXPECTED_DISTANCE_MINSK_WARSAW, UNIT_KM);
        when(distanceService.calculateDistance(CITY_MINSK, CITY_WARSAW)).thenReturn(response);

        ResponseEntity<DistanceResponse> result = (ResponseEntity<DistanceResponse>) (Object) distanceController.getDistance(CITY_MINSK, CITY_WARSAW);

        assertNotNull(result.getBody());
        assertEquals(response, result.getBody());
        verify(requestCounter).incrementTotal();
        verify(requestCounter).incrementSuccessful();
        verify(distanceService).calculateDistance(CITY_MINSK, CITY_WARSAW);
    }

    @Test
    void testGetBulkDistances_ValidPairs_IncrementsCounters() {
        DistanceResponse response = new DistanceResponse(CITY_MINSK, CITY_WARSAW, EXPECTED_DISTANCE_MINSK_WARSAW, UNIT_KM);
        List<DistanceResponse> responses = Collections.singletonList(response);
        when(distanceService.calculateBulkDistances(anyList())).thenReturn(responses);

        ResponseEntity<List<DistanceResponse>> result = (ResponseEntity<List<DistanceResponse>>) (Object) distanceController.getBulkDistances(Collections.singletonList(new String[]{CITY_MINSK, CITY_WARSAW}));

        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        verify(requestCounter).incrementTotal();
        verify(requestCounter).incrementSuccessful();
        verify(distanceService).calculateBulkDistances(anyList());
    }

    @Test
    void testGetRequestCount_IncrementsCounters() {
        when(requestCounter.getTotalRequests()).thenReturn(10);

        ResponseEntity<Integer> result = (ResponseEntity<Integer>) (Object) distanceController.getRequestCount();

        assertNotNull(result.getBody());
        assertEquals(10, result.getBody());
        verify(requestCounter).incrementTotal();
        verify(requestCounter).incrementSuccessful();
    }

    @Test
    void testResetCounter_IncrementsAndResets() {
        distanceController.resetCounter();

        verify(requestCounter).incrementTotal();
        verify(requestCounter).incrementSuccessful();
        verify(requestCounter).reset();
    }

    @Test
    void testGetDistance_FailedRequest_IncrementsFailed() {
        when(distanceService.calculateDistance(CITY_MINSK, CITY_WARSAW)).thenThrow(new RuntimeException("Test exception"));

        assertThrows(RuntimeException.class, () -> distanceController.getDistance(CITY_MINSK, CITY_WARSAW));
        verify(requestCounter).incrementTotal();
        verify(requestCounter).incrementFailed();
        verifyNoInteractions(requestCounter, never()).incrementSuccessful();
    }
}
