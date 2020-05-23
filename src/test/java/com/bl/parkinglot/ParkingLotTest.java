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
        vehicle=new Object();
    }

    @Test
    public void givenVehicle_whenParked_shouldReturnTrue() {
        boolean isParked = parkingLot.parked(new Object());
        Assert.assertTrue(isParked);
    }
    
    @Test
    public void givenVehicle_whenAlReadyParked_shouldReturnFalse(){
        parkingLot.parked(vehicle);
        boolean isParked=parkingLot.parked(vehicle);
        Assert.assertFalse(isParked);
    }

    @Test
    public void givenVehicle_whenUnparked_shouldReturnTrue(){
            parkingLot.parked(vehicle);
            boolean isUnparked=parkingLot.UnPark(vehicle);
            Assert.assertEquals(true,isUnparked);
    }
 }

