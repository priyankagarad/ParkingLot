package com.bl.parkinglot.model;
import com.bl.parkinglot.Observer;
public class AirportSecurity implements Observer {
    private String parkingSlotFullOrNot;

    public void setParkingSlotFullOrNot(String parkingSlotFullOrNot) {
        this.parkingSlotFullOrNot = parkingSlotFullOrNot;
    }

    public String getParkingSlotFullOrNot() {
        return parkingSlotFullOrNot;
    }

    @Override
    public void update(Object status) {
        this.setParkingSlotFullOrNot((String) status);
    }
}
