package com.bl.parkinglot.model;
import java.sql.Driver;
public class Vehicle {
    private String vehicleNumber;
    public String vehicleType;
    private Driver driver;

    public Vehicle( String vehicleNumber, String vehicleType, Driver driver) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType=vehicleType;
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
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
