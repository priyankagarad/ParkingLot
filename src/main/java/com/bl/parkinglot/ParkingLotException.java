package com.bl.parkinglot;
public class ParkingLotException extends RuntimeException {
    public enum ExceptionType  {
        LOT_IS_FULL,VEHICLE_ALREADY_PARK,VEHICLE_NOT_FOUND;
    }
    ExceptionType type;

    public ParkingLotException(ExceptionType type,String message)
    {
        super(message);
        this.type=type;
    }
}
