package com.bl.parkinglot;
public class ParkingLotSystem {
    Object vehicle;

    public ParkingLotSystem(){
    }

    public boolean parked(Object vehicle){
        if (this.vehicle!=null)
            return false;
        this.vehicle=vehicle;
        return true;
    }

    public boolean UnPark(Object vehicle) {
        if (vehicle==null)
            return false;
        if (this.vehicle .equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
