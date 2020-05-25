package com.bl.parkinglot.model;
public class ParkingLotOwer<count> implements ParkinLotObserver {
    private boolean isFullCapacity;
    private int count=0;

    public ParkingLotOwer() {
    }

    @Override
    public Object get(int i) {
        return null;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }

    @Override
    public void isParkingEmpty() {
    }

    @Override
    public int getParkingSlot() {
        return count++;
    }

}