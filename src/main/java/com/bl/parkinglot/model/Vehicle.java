package com.bl.parkinglot.model;
import java.sql.Driver;
public class Vehicle {
    private String vehicleName;
    private String vehicleNumber;
    private Driver driver;
    private String vehicleColore;

    public Vehicle( String vehicleName, String vehicleNumber, Driver driver,String vehicleColore) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName=vehicleName;
        this.driver = driver;
        this.vehicleColore=vehicleColore;
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
                ", vehicleColore=" + vehicleColore +
                '}';
    }
}
