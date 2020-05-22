package com.bl.parkinglot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLot;
    Object vehicle;

    @Before
    public void setUp(){
        parkingLot=new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    public void givenVehicle_whenPark_shouldReturnTrue(){
        parkingLot.park(vehicle);
        boolean result=parkingLot.isVehiclePark(vehicle);
        Assert.assertTrue(result);
    }
}
