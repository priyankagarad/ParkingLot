package com.bl.parkinglot.exception;
public class ParkingLotException extends RuntimeException {
    public enum MyexceptionType {
        LOT_IS_FULL, VEHICLE_ALREADY_PARK, VEHICLE_NOT_PARK;
    }

    MyexceptionType type;

    public ParkingLotException(MyexceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
