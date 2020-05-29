package com.bl.parkinglot;
import com.bl.parkinglot.exception.ParkingLotException;
import com.bl.parkinglot.model.*;
import com.bl.parkinglot.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.text.ParseException;
public class ParkingLotTest {
    AirportSecurity airportSecurity;
    ParkingLotSystem parkingLot;
    PoliceDepartment police ;
    Owner owner;
    Integer capacity=3;
    int slot=3;

    @Before
    public void setUp(){
        parkingLot = new ParkingLotSystem(capacity,slot);
        airportSecurity=new AirportSecurity();
        police = new PoliceDepartment();
        owner=new Owner();
        }

    @Test
    public void givenVehicle_WhenPark_shouldReturnResult()  throws ParkingLotException{
        try {
            Vehicle vehicle = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.NORMAL), "WHITE");
            String result = parkingLot.park(vehicle);
            Assert.assertEquals("park vehicle", result);
        } catch (ParkingLotException e) { }
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_shouldReturnException() {
        try {
            Vehicle vehicle = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle);
            parkingLot.park(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("This vehicle already park",e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenUnPark_shouldReturnResult() {
        try {
            Vehicle vehicle = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle);
            String result = parkingLot.unPark(vehicle);
            Assert.assertEquals("unpark", result);
        } catch (ParkingLotException e) { }
    }


    @Test
    public void givenParkingLot_WhenFull_shouldInformOwner() {
        parkingLot.addObserver(owner);
        try {
            parkingLot.addObserver(owner);
            Vehicle vehicle1 = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle2);
            Vehicle vehicle = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle);
            Assert.assertEquals("Full Lot 1", owner.getParkingFull());
        } catch (Exception e) { }
    }

    @Test
    public void givenParkingLotCapacityIs2_whenFull_ShouldInformOwner(){
        try{
            parkingLot.addObserver(owner);
            Vehicle vehicle1 = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle2);
            String parkingFull=owner.getParkingFull();
             Assert.assertTrue(parkingFull,true);
        } catch (ParkingLotException e) { }
    }

    @Test
    public void givenParkingLot_whenFull_shouldInformAirportSecurity() {
        try {
            Vehicle vehicle1 = new Vehicle("suzuki", "" + "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle2);
            Vehicle vehicle = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle);
            Assert.assertEquals("Full Lot 1", airportSecurity.getParkingSlotFullOrNot());
        } catch (Exception e) { }
    }

    @Test
    public void givenVehicleNotPresentInParkingLot_WhenUnPark_shouldThrowException() {
        try {
            Vehicle vehicle1 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.unPark(vehicle1);
            Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.unPark(vehicle2);
        } catch (ParkingLotException e) {
            Assert.assertEquals("This vehicle not park in my parking lot", e.getMessage());
        }
    }

    @Test
    public void givenAgainParkingLot_whenSpaceAvailable_shouldInformOwner() {
        try{
            parkingLot.addObserver(owner);
            Vehicle vehicle1 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle2);
            Vehicle vehicle = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle);
            parkingLot.unPark(vehicle);
            Assert.assertEquals("Have Space lot number 3", owner.getParkingSpace());
        } catch (ParkingLotException e) { }
    }

    @Test
    public void givenCar_WhenPark_shouldReturnCarPosition() {
        try {
            Vehicle vehicle = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLot.park(vehicle);
            Vehicle vehicle1 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL),"WHITE");
            parkingLot.park(vehicle1);
            String result = parkingLot.isVehiclePark(vehicle);
            Assert.assertEquals("vehicle park in lot number 1", result);
        } catch (ParkingLotException e) { }
    }

     @Test
     public void givenVehicleParkInLot_WhenCharge_shouldReturnParkingCharge() {
        try {
             parkingLot.addObserver(owner);
             Vehicle vehicle = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL),"WHITE");
             parkingLot.park(vehicle);
             Assert.assertEquals("this vehicle charge Rs.10", owner.getParkingCharge());
         } catch (ParkingLotException e) { }
    }

    @Test
    public void givenParkingAttendant_WhenEvenlyDistribution_shouldReturnMessage () {
        try {
             this.capacity = 4;
             this.slot = 2;
             parkingLot = new ParkingLotSystem(capacity, slot);
             parkingLot.addObserver(owner);
             Vehicle vehicle = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
             parkingLot.park(vehicle);
             Vehicle vehicle1 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
             parkingLot.park(vehicle1);
             Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
             parkingLot.park(vehicle2);
             Assert.assertEquals("Full Lot 1", owner.getParkingFull());
             Vehicle vehicle3 = new Vehicle("suzuki", "MH4R4548", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
             parkingLot.park(vehicle3);
             Assert.assertEquals("Full Lot 2", owner.getParkingFull());
        } catch (ParkingLotException e) { }
    }
    @Test
     public void givenHandicapDriver_WhenNearestFreeSpace_shouldParkCar () {
        this.capacity = 4;
        this.slot = 2;
        parkingLot.addObserver(owner);
        Vehicle vehicle1 = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
        parkingLot.park(vehicle1);
        Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.HANDICAP), "WHITE");
        parkingLot.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
        parkingLot.park(vehicle3);
        Vehicle vehicle = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
        String result = parkingLot.park(vehicle);
        Assert.assertEquals("park vehicle", result);
    }

    @Test
    public void givenParkingLot_WhenLargeVehicleParked_shouldReturnMessage() {
        try {
            this.capacity = 4;
            this.slot = 2;
            parkingLot = new ParkingLotSystem(capacity, slot);
            parkingLot.addObserver(owner);
            Vehicle vehicle1 = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.HANDICAP), "WHITE");
            parkingLot.park(vehicle2);
            Vehicle vehicle3 = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle3);
            Vehicle vehicle = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Lot is full", e.getMessage());
        }
    }

    @Test
    public void givenParkingLotSystem_WhenWhiteCarColorPark_shouldReturnCarsLocation() throws ParkingLotException, ParseException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(police);
        Vehicle vehicle1 = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
        parkingLot.park(vehicle1);
        Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
        parkingLot.park(vehicle2);
        Vehicle vehicle = new Vehicle("suzuki", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
        String result = parkingLot.park(vehicle);
        parkingLot.serching("WHITE");
        Assert.assertEquals("1,2,3,", police.getVehicleLocation());
    }

    @Test
    public void givenParkingLotSystem_WhenCarColorAndBrandGiven_ThenReturnCarsLocation() throws ParkingLotException, ParseException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(police);
        Vehicle vehicle1 = new Vehicle("Toyota", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "BLUE");
        parkingLot.park(vehicle1);
        Vehicle vehicle2 = new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
        parkingLot.park(vehicle2);
        Vehicle vehicle = new Vehicle("Toyota", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "BLUE");
        String result = parkingLot.park(vehicle);
        parkingLot.serching("Toyota", "BLUE");
        Assert.assertEquals("1,3,", police.getVehicleLocation());
    }

    @Test
    public void givenParkingLotSystem_WhenCarBrandGiven_ThenReturnCarsLocation() throws ParkingLotException, ParseException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(police);
        Vehicle vehicle1 = new Vehicle("Toyota", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL, "BLUE");
        parkingLot.park(vehicle1);
        Vehicle vehicle2 = new Vehicle("BMW", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
        parkingLot.park(vehicle2);
        Vehicle vehicle = new Vehicle("Toyota", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "BLUE");
        String result = parkingLot.park(vehicle);
        parkingLot.serching("BMW");
        Assert.assertEquals("2,", police.getVehicleLocation());
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleParkLast30Minuts_shouldReturnCarsLocation() throws ParseException {
        try {
            parkingLot.addObserver(owner);
            parkingLot.addObserver(police);
            Vehicle vehicle1 = new Vehicle("Toyota", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "BLUE");
            parkingLot.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("BMW", "MH4R4546", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            parkingLot.park(vehicle2);
            Vehicle vehicle = new Vehicle("Toyota", "MH4R4547", "SMALL", new Driver(Driver.DriverType.NORMAL), "BLUE");
            parkingLot.park(vehicle);
            parkingLot.serching("01:00", "24:50");
            Assert.assertEquals("1,2,3,", police.getVehicleLocation());
        } catch(ParkingLotException e){ }
    }

    @Test
    public void givenParkingLotSystem_WhenParkedSmallVehicleAndDriverIsHandicap_ShouldReturnDetailsOfVehicle(){
        try {
            this.capacity = 20;
            this.slot = 5;
            parkingLot = new ParkingLotSystem(capacity, slot);
            parkingLot.addObserver(police);
            for (int i = 1; i <= 10; i++) {
            parkingLot.park(new Vehicle("Toyota", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "BLUE"));
            parkingLot.park(new Vehicle("suzuki", "MH4R4546", "SMALL", new Driver(Driver.DriverType.HANDICAP), "WHITE"));
           }
            parkingLot.searchInSlot(Driver.DriverType.HANDICAP,"B","SMALL");
            Assert.assertEquals("8,9,10,", police.getVehicleLocation());
            parkingLot.searchInSlot(Driver.DriverType.HANDICAP,"D","SMALL");
            Assert.assertEquals("20,", police.getVehicleLocation());
        } catch (ParkingLotException e) { }
    }

    @Test
    public void givenParkingLotSystem_WhenParkedLargeVehicleAndDriverIsHandicap_ShouldReturnDetailsOfVehicle() {
        try {
            this.capacity = 20;
            this.slot = 5;
            parkingLot = new ParkingLotSystem(capacity, slot);
            parkingLot.addObserver(police);
            for (int i = 1; i <= 10; i++) {
                parkingLot.park(new Vehicle("Toyota", "MH4R4545", "LARGE", new Driver(Driver.DriverType.NORMAL), "BLUE"));
                parkingLot.park(new Vehicle("suzuki", "MH4R4546", "LARGE", new Driver(Driver.DriverType.HANDICAP), "WHITE"));
            }
             parkingLot.searchInSlot(Driver.DriverType.HANDICAP,"B","LARGE");
             Assert.assertEquals("8,9,10,", police.getVehicleLocation());
             parkingLot.searchInSlot(Driver.DriverType.HANDICAP,"D","LARGE");
             Assert.assertEquals("20,", police.getVehicleLocation());
        } catch (ParkingLotException e) { }
    }
}


