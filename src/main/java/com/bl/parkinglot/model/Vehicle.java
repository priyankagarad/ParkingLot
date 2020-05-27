package com.bl.parkinglot.model;

import java.sql.Driver;

public class Vehicle {
    private String vehicleNumber;
    public String vehicleType;
    private Driver driver;

    public Vehicle(String vehicleNumber, Driver driver) {
        this.vehicleNumber = vehicleNumber;
        this.driver = driver;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", driver=" + driver +
                '}';
    }
}
