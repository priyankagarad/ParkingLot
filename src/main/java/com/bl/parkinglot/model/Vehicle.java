package com.bl.parkinglot.model;
import java.sql.Driver;
public class Vehicle {
    private String vehicleName;
    private String vehicleNumber;
    private String vehicleType;
    public Driver driver;
    private String vehicleColore;

    public Vehicle( String vehicleName, String vehicleNumber, String vehicleType,Driver driver,String vehicleColore) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName=vehicleName;
        this.vehicleType=vehicleType;
        this.driver = driver;
        this.vehicleColore=vehicleColore;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleName='" + vehicleName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", driver=" + driver +
                ", vehicleColore=" + vehicleColore +
                '}';
    }
}
