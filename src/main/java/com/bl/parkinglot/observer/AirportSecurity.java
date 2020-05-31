/***********************************************************************************************************************
 * @Purpose: Inform To Airport Security when Parking Lot is full
 * @Author: priyanka Garad
 * Date: 24/5/2020
 ***********************************************************************************************************************/
package com.bl.parkinglot.observer;
public class AirportSecurity implements Observer {
    private String parkingSlotFullOrNot;

    public void setParkingSlotFullOrNot(String parkingSlotFullOrNot) {
        this.parkingSlotFullOrNot = parkingSlotFullOrNot;
    }

    public String getParkingSlotFullOrNot() {
        return parkingSlotFullOrNot;
    }

    /**
     * @purpose:method given update of parking lot is full or not
     */
     @Override
    public void update(Object status) {
        this.setParkingSlotFullOrNot((String) status);
    }
}
