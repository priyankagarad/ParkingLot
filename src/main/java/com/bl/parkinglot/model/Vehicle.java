package com.bl.parkinglot.model;
import java.sql.Driver;
public class Vehicle {
    private String vehicleName;
    private String vehicleNumber;
    private Driver driver;

    public Vehicle( String vehicleName, String vehicleNumber, Driver driver) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName=vehicleName;
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleName='" + vehicleName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", driver=" + driver +
                '}';
    }
}
