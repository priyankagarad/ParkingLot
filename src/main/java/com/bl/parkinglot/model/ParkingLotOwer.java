package com.bl.parkinglot.model;
public class ParkingLotOwer implements ParkinLotObserver {
    private boolean isFullCapacity;

    public ParkingLotOwer() {
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }

    @Override
    public void isParkingEmpty() {
    }

    @Override
    public void parkingAvailable() {
    }

}
