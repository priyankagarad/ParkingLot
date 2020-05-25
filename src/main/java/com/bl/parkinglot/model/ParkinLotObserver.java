package com.bl.parkinglot.model;
public interface ParkinLotObserver {
    boolean isCapacityFull();
    void isParkingEmpty();

    void parkingAvailable();
}


