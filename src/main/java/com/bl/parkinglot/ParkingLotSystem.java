package com.bl.parkinglot;
public class ParkingLotSystem {

    Object vehicle;

    public void park(Object vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isVehiclePark(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    public boolean unPark(Object vehicle){
        if(vehicle==null)
            return false;
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

}
