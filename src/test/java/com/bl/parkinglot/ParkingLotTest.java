package com.bl.parkinglot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLot;
    Object vehicle;

    @Before
    public void setUp() {
        parkingLot = new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    public void givenVehicle_whenPark_shouldReturnTrue() {
        parkingLot.park(vehicle);
        boolean result = parkingLot.isVehiclePark(vehicle);
        Assert.assertTrue(result);
    }

    @Test
    public void givenVehicle_WhenAllReadyParked_shouldReturnException() {
        try {
            parkingLot.park(vehicle);
            parkingLot.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking lot is full",e.getMessage());
        }
    }

    @Test
    public void givenParkingLot_WhenFull_ThenReturnExceptionMessage(){
        try {
            parkingLot.park(vehicle);
            parkingLot.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking lot is full",e.getMessage());
        }
    }
 }

