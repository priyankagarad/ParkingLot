package com.bl.parkinglot;
public class ParkingLotSystem {

    Object vehicle;

    public void park(Object vehicle) {
        if(this.vehicle!=null)
            throw new ParkingLotException("Parking lot is full");
        this.vehicle = vehicle;
    }

    public boolean isVehiclePark(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
}
