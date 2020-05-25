package com.bl.parkinglot;
import com.bl.parkinglot.exception.ParkinLotException;
import com.bl.parkinglot.model.AirportSecurity;
import com.bl.parkinglot.model.ParkingLotOwer;
import com.bl.parkinglot.service.ParkingLotAttender;
import com.bl.parkinglot.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ParkingLotTest {
    ParkingLotSystem parkingLot;
    AirportSecurity airportSecurity;
    ParkingLotOwer owner;
    Object car;
    Object car1;

    @Before
    public void setUp() {
        parkingLot = new ParkingLotSystem(1);
        AirportSecurity airportSecurity = new AirportSecurity();
        owner = new ParkingLotOwer();
        car = new Object();
        car1 = new Object();
    }

    @Test
    public void givenVehicle_whenParked_shouldReturnTrue() {
        parkingLot.parked(car);
        boolean isParked = parkingLot.isVehicleParked(car);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenVehicle_whenAlReadyParked_shouldReturnTrue() {
        parkingLot.parked(car);
        boolean isParked = parkingLot.UnPark(car);
        Assert.assertTrue(isParked);////
    }

    @Test
    public void givenVehicle_whenUnparked_shouldReturnTrue() {
        parkingLot.parked(car);
        boolean isUnparked = parkingLot.UnPark(car);
        Assert.assertTrue(isUnparked);
    }

    @Test
    public void givenVehicleNotPresentInParkingLot_WhenUnPark_ThenThrowException() {
        try {
            parkingLot.parked(car);
            boolean result = parkingLot.UnPark(car);
            Assert.assertFalse("vehicle is not Park in parking lot", false);
        } catch (ParkinLotException e) {
        }
    }

    @Test
    public void giveParkingLot_whenFull_shouldInformOwner() {
        parkingLot.registerParkingLotObserver(owner);
        try {
            parkingLot.parked(car);
            parkingLot.parked(new Object());
        } catch (ParkinLotException e) {
            boolean capacityFull = owner.isCapacityFull();
            Assert.assertFalse(capacityFull);
        }
    }

    @Test
    public void givenParkingLot_whenCapacityIS2_shouldAbleToPark2Vehicle() {
        parkingLot.setCapacity(2);
        try {
            parkingLot.parked(car);
            parkingLot.parked(car1);
            boolean isParked = parkingLot.isVehicleParked(car1);
            boolean isParked1 = parkingLot.isVehicleParked(car1);
            Assert.assertTrue(isParked && isParked1);
        } catch (ParkinLotException e) {
        }
    }

    @Test
    public void givenParkingLot_whenFull_shouldInformSecurity() {
        parkingLot.registerSecurity(airportSecurity);
        try {
            parkingLot.parked(car);
            parkingLot.parked(new Object());
        } catch (ParkinLotException e) {
            boolean capacityFull = airportSecurity.capacityFull();
            Assert.assertTrue(capacityFull);
        }
    }

    @Test
    public void givenParkingLot_whenSpaceIsAvailableAfterFull_shouldReturn() {
        parkingLot.registerParkingLotObserver(owner);
        try {
            parkingLot.parked(car);
            parkingLot.parked(car1);
            parkingLot.UnPark(car);
        } catch (ParkinLotException e) {
            boolean capacityFull = owner.isCapacityFull();
            Assert.assertFalse(capacityFull);
        }
    }

    @Test
    public void givenParkingLotSlot_WhenVehicleCome_ShouldAttenderVehicle() {
        try {
            parkingLot.registerParkingLotObserver(owner);
            ParkingLotAttender parkingLotAttender = new ParkingLotAttender(car);
            ParkingLotAttender attender = parkingLot.getParkingLotAttendant(parkingLotAttender);
            Assert.assertEquals(attender, parkingLotAttender);
        } catch (ParkinLotException e) {
        }
    }

    @Test
    public void givenParkingLot_WhenParkingFullThenAttender_ShouldThrowException() {
        parkingLot.setCapacity(2);
        try {
            parkingLot.registerParkingLotObserver(owner);
            ParkingLotAttender attender1 = new ParkingLotAttender(car);
            ParkingLotAttender attender2 = new ParkingLotAttender(car);
            parkingLot.getParkingLotAttendant(attender1);
            parkingLot.getParkingLotAttendant(attender2);
        } catch (ParkinLotException e) {
            Assert.assertEquals("PARKING_IS_FULL", e.getMessage());
        }
    }

    @Test
    public void givenParkingLot_WhenAttenderNotAvailable_shouldThrowException() {
        try {
            parkingLot.registerParkingLotObserver(owner);
            ParkingLotAttender parkingLotAttender = new ParkingLotAttender(car);
            parkingLot.getParkingLotAttendant(parkingLotAttender);
            ParkingLotAttender myAttendant = parkingLot.getVehicle(new ParkingLotAttender(new Object()));
            Assert.assertEquals(parkingLotAttender, myAttendant);
        } catch (ParkinLotException e) {
            Assert.assertEquals("No Attendant Found", e.getMessage());
        }
    }

    @Test
    public void givenParkingLot_whenVehicleCome_ShouldReturnAttendant() {
        try {
            parkingLot.registerParkingLotObserver(owner);
            ParkingLotAttender parkingLotAttender = new ParkingLotAttender(car);
            parkingLot.getParkingLotAttendant(parkingLotAttender);
            ParkingLotAttender myAttendant = parkingLot.getVehicle(parkingLotAttender);
            Assert.assertEquals(parkingLotAttender, myAttendant);
        } catch (ParkinLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotSystem_WhenCarFound_ShouldReturnCarSlot() {
        try {
            parkingLot.setCapacity(5);
            parkingLot.initializeParkingSlot();
            ArrayList<Integer> slot = parkingLot.getSlot();
            parkingLot.isParkVehicles(slot.get(0), new Object());
            parkingLot.isParkVehicles(slot.get(1), car);
            int slotNumber = parkingLot.findVehicle(this.car);
            Assert.assertEquals(1, slotNumber);
        } catch (ParkinLotException e) {
            e.printStackTrace();
        }

    }
}