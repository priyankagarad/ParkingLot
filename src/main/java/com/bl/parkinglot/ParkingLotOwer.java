package com.bl.parkinglot;
public class ParkingLotOwer implements ParkinLotObserver {
    private boolean isFullCapacity;

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }

    @Override
    public void capacityFull() {
        isFullCapacity=true;
    }

}
