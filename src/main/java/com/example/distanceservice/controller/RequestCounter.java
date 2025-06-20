package com.example.distanceservice.util;

public class RequestCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized void reset() {
        count = 0;
    }
}
