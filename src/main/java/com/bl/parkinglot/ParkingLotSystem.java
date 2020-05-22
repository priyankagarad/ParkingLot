package com.bl.parkinglot;
public class ParkingLotSystem {

    Object vehicle;
    int PARKING_LOT_CAPACITY=100;
    int parking_lot_size=0;

    public void park(Object vehicle) {
        if(PARKING_LOT_CAPACITY==parking_lot_size)
            throw new ParkingLotException("Parking lot is full");
        this.vehicle = vehicle;
        parking_lot_size++;
    }

    public boolean isVehiclePark(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
}
