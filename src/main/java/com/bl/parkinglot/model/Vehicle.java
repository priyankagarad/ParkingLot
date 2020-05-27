package com.bl.parkinglot.model;
public class Vehicle {
    private String vehicleNumber;

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String toString() {
        return "VehiclePOJO{" +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';
    }

}
