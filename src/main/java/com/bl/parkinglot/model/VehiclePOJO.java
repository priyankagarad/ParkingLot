package com.bl.parkinglot.model;
public class VehiclePOJO {
    private String vehicleNumber;

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public String toString() {
        return "VehiclePOJO{" +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';
    }
}
