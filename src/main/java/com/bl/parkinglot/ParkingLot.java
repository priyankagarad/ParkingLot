package com.bl.parkinglot;
public class ParkingLot {

    Object vehicle;

    public void park(Object vehicle) {
        this.vehicle = vehicle;

    }

    public boolean isVehiclePark(Object vehicle){
        if(this.vehicle.equals(vehicle))
            return true;
        return false;
    }
}
