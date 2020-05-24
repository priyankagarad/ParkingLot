package com.bl.parkinglot;

public class AirportSecurity {
    private boolean isFullCapacity;

    public void capacityFull() {
        isFullCapacity=true;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}

