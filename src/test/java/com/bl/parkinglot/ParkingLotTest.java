package com.bl.parkinglot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ParkingLotTest {
    ParkingLotSystem parkingLot;
    Object vehicle;

    @Before
    public void setUp() {
        parkingLot = new ParkingLotSystem(1);
        vehicle = new Object();
    }

    @Test
    public void givenVehicle_whenParked_shouldReturnTrue() {
        try {
            parkingLot.parked(vehicle);
            boolean isParked = parkingLot.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
        public void givenVehicle_whenAlReadyParked_shouldReturnTrue(){
          try{
              parkingLot.parked(vehicle);
              boolean isParked=parkingLot.UnPark(vehicle);
              Assert.assertTrue(isParked);
          } catch (Exception e) {
              e.printStackTrace();
          }
    }

    @Test
    public void givenVehicle_whenUnparked_shouldReturnTrue() {
        try {
            parkingLot.parked(vehicle);
            boolean isUnparked = parkingLot.UnPark(vehicle);
            Assert.assertEquals(true, isUnparked);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveParkingLot_whenFull_shouldInformOwner() {
        ParkingLotOwer owner = new ParkingLotOwer();
        parkingLot.registerParkinLotObserver(owner);
        try {
            parkingLot.parked(vehicle);
            parkingLot.parked(new Object());
        } catch (Exception e) {
        }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenParkingLot_whenCapacityIS2_shouldAbleToPark2Vehicle(){
        Object vehicle2=new Object();
        parkingLot.setCapacity(2);
        try
        {
            parkingLot.parked(vehicle);
            parkingLot.parked(vehicle2);
            boolean isParked=parkingLot.isVehicleParked(vehicle2);
            boolean isParked1=parkingLot.isVehicleParked(vehicle2);
            Assert.assertTrue(isParked && isParked1);
        } catch (Exception e) {
        }
    }

    @Test
    public void givenParkingLot_whenFull_shouldInformSecurity(){
        AirportSecurity airportSecurity=new AirportSecurity();
        parkingLot.registerSecurity(airportSecurity);
        try
        {
            parkingLot.parked(vehicle);
            parkingLot.parked(new Object());
        } catch (Exception e) {
            boolean capacityFull=airportSecurity.isCapacityFull();
            Assert.assertTrue(capacityFull);
        }
    }
}

