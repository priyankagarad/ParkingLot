package com.bl.parkinglot;
import com.bl.parkinglot.exception.ParkinLotException;
import com.bl.parkinglot.model.AirportSecurity;
import com.bl.parkinglot.model.ParkingLotOwer;
import com.bl.parkinglot.service.ParkingLotAttender;
import com.bl.parkinglot.service.ParkingLotSystem;
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
    public void givenVehicle_whenParked_shouldReturnTrue(){
            parkingLot.parked(vehicle);
            boolean isParked = parkingLot.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
    }

    @Test
    public void givenVehicle_whenAlReadyParked_shouldReturnTrue() {
            parkingLot.parked(vehicle);
            boolean isParked = parkingLot.UnPark(vehicle);
            Assert.assertTrue(isParked);////
    }

    @Test
    public void givenVehicle_whenUnparked_shouldReturnTrue() {
            parkingLot.parked(vehicle);
            boolean isUnparked = parkingLot.UnPark(vehicle);
    }

    @Test
    public void givenVehicleNotPresentInParkingLot_WhenUnPark_ThenThrowException() {
        Object vehicle1 = null;
        try {
            parkingLot.parked(vehicle);
            boolean result=parkingLot.UnPark(vehicle1);
            Assert.assertFalse("vehicle is not Park in parking lot",false);
        } catch (ParkinLotException e) {
        }
    }

    @Test
    public void giveParkingLot_whenFull_shouldInformOwner() {
        ParkingLotOwer owner = new ParkingLotOwer();
        parkingLot.registerParkingLotObserver(owner);
        try {
            parkingLot.parked(vehicle);
            parkingLot.parked(new Object());
        } catch (ParkinLotException e) {
            boolean capacityFull = owner.isCapacityFull();
            Assert.assertFalse(capacityFull);
        }
    }

    @Test
    public void givenParkingLot_whenCapacityIS2_shouldAbleToPark2Vehicle() {
        Object vehicle2 = new Object();
        parkingLot.setCapacity(2);
        try {
            parkingLot.parked(vehicle);
            parkingLot.parked(vehicle2);
            boolean isParked = parkingLot.isVehicleParked(vehicle2);
            boolean isParked1 = parkingLot.isVehicleParked(vehicle2);
            Assert.assertTrue(isParked && isParked1);
        } catch (ParkinLotException e) {
        }
    }

    @Test
    public void givenParkingLot_whenFull_shouldInformSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLot.registerSecurity(airportSecurity);
        try {
            parkingLot.parked(vehicle);
            parkingLot.parked(new Object());
        } catch (ParkinLotException e) {
            boolean capacityFull = airportSecurity.capacityFull();
            Assert.assertFalse(capacityFull);
        }
    }

    @Test
    public void givenParkingLot_whenSpaceIsAvailableAfterFull_shouldReturn() {
        ParkingLotOwer owner = new ParkingLotOwer();
        Object vehicle1 = new Object();
        parkingLot.registerParkingLotObserver(owner);
        try {
            parkingLot.parked(vehicle);
            parkingLot.parked(vehicle1);
            parkingLot.UnPark(vehicle);
        } catch (ParkinLotException e) {
            boolean capacityFull = owner.isCapacityFull();
            Assert.assertFalse(capacityFull);
        }
    }

    @Test
    public void givenParkingLotSlot_WhenCarCome_ShouldAttendermarkCar() {
        ParkingLotOwer owner = new ParkingLotOwer();
        try {
            parkingLot.registerParkingLotObserver(owner);
            ParkingLotAttender parkingLotAttender=new ParkingLotAttender(vehicle);
            ParkingLotAttender attender=parkingLot.getParkingLotAttendant(parkingLotAttender);
            Assert.assertEquals(attender,parkingLotAttender);
        } catch (ParkinLotException e) {
        }
    }

    @Test
    public void givenParkingLot_WhenParkingFullthenAttender_ShouldThrowException() {
        ParkingLotOwer owner = new ParkingLotOwer();
        parkingLot.setCapacity(3);
        try {
            parkingLot.registerParkingLotObserver(owner);
            ParkingLotAttender parkingLotAttender1=new ParkingLotAttender(vehicle);
            ParkingLotAttender parkingLotAttender2=new ParkingLotAttender(vehicle);
            ParkingLotAttender parkingLotAttender3=new ParkingLotAttender(vehicle);
            parkingLot.getParkingLotAttendant(parkingLotAttender1);
            parkingLot.getParkingLotAttendant(parkingLotAttender2);
            parkingLot.getParkingLotAttendant(parkingLotAttender3);
        } catch (ParkinLotException e) {
            Assert.assertEquals("PARKING_IS_FULL",e.getMessage());
        }
    }

    @Test
    public void givenParkingLot_ShouldReturnAttendant()
    {
        ParkingLotOwer owner = new ParkingLotOwer();
        try {
            parkingLot.registerParkingLotObserver(owner);
            ParkingLotAttender parkingLotAttender=new ParkingLotAttender(vehicle);
            parkingLot.getParkingLotAttendant(parkingLotAttender);
            ParkingLotAttender myAttendant= parkingLot.getMyVehicle(parkingLotAttender);
            Assert.assertEquals(parkingLotAttender,myAttendant);
        } catch (ParkinLotException e) {
            e.printStackTrace();
        }
    }
}
