package com.bl.parkinglot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ParkingLotTest {
    ParkingLotSystem parkingLot;
    Object car;

    @Before
    public void setUp() {
        parkingLot = new ParkingLotSystem();
        car= new Object();
    }

    @Test
    public void givenVehicle_whenPark_shouldReturnTrue() {
        parkingLot.park(car);
        boolean result = parkingLot.isVehiclePark(car);
        Assert.assertTrue(result);
    }

    @Test
    public void givenVehicle_WhenAllReadyParked_shouldReturnException() {
        try {
            parkingLot.park(car);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARK,e.getMessage());
        }
    }

    @Test
    public void givenVehicle_whenUnpark_shouldReturnTrue(){
        try {
            parkingLot.park(car);
            boolean result=parkingLot.isUnPark(car);
            Assert.assertEquals(true,result);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
 }

