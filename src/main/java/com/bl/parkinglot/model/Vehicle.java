package com.bl.parkinglot.model;

import java.sql.Driver;

public class Vehicle {
    private String vehicleNumber;
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

    @Override
    public String toString() {
        return "VehiclePOJO{" +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", driver='" + driver + '\'' +
                '}';
    }

}
