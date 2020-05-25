package com.bl.parkinglot.model;
public interface ParkinLotObserver {
    Object get(int i);

    boolean isCapacityFull();
    void isParkingEmpty();

    int getParkingSlot();
}


