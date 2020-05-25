package com.bl.parkinglot.exception;
public class ParkinLotException extends RuntimeException {
    public enum ExceptionType {
        VEHICLE_NOT_FOUND, PARKING_IS_FULL,ALREADY_PARKED;
    }

    public ExceptionType type;
    public ParkinLotException(ExceptionType type, String message) {
        super((message));
        this.type = type;
    }
}