package com.bl.parkinglot;
public class ParkingLotOwer implements ParkinLotObserver {
    private boolean isFullCapacity;

    public void capacityFull() {
        isFullCapacity=true;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
