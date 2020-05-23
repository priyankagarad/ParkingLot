package com.bl.parkinglot;
public class ParkingLotSystem {
    Object car;
    int PARKING_LOT_CAPACITY=100;
    int parking_lot_size=0;

    public void park(Object car) throws ParkingLotException{
        if(PARKING_LOT_CAPACITY==parking_lot_size)
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOT_IS_FULL,"parking lot is full");
        this.car = car;
        parking_lot_size++;
    }

    public boolean isVehiclePark(Object car) {
        if (this.car.equals(car))
            return true;
        return false;
    }

    public boolean isUnPark(Object car) {
        if (this.car != null && this.car.equals(car)) {
            this.car = null;
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "Vehicle Is Not Not Parked");
    }
}
