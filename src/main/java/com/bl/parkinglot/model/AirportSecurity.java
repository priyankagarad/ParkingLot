package com.bl.parkinglot.model;
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
