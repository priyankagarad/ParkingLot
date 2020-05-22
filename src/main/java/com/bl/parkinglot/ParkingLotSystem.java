package com.bl.parkinglot;

import static com.bl.parkinglot.ParkingLotException.ExceptionType.LOT_IS_FULL;

public class ParkingLotSystem {

    Object car;
    int PARKING_LOT_CAPACITY=100;
    int parking_lot_size=0;

    public void park(Object car) {
        if(PARKING_LOT_CAPACITY==parking_lot_size)
            throw new ParkingLotException(LOT_IS_FULL,"parking lot is full");
        this.car = car;
        parking_lot_size++;
    }

    public boolean isVehiclePark(Object vehicle) {
        if (this.car.equals(car))
            return true;
        return false;
    }
}
