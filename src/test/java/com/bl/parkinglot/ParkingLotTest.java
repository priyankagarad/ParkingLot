package com.bl.parkinglot;
import com.bl.parkinglot.exception.ParkingLotException;
import com.bl.parkinglot.model.AirportSecurity;
import com.bl.parkinglot.model.Owner;
import com.bl.parkinglot.model.VehiclePOJO;
import com.bl.parkinglot.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ParkingLotTest {
    AirportSecurity airportSecurity;
    ParkingLotSystem parkingLot;
    VehiclePOJO vehicle;
    Owner owner;
    Integer capacity=3;
    int slot=3;

    @Before
    public void setUp(){
        vehicle = new VehiclePOJO();
        parkingLot = new ParkingLotSystem(capacity,slot);
        airportSecurity=new AirportSecurity();
        owner=new Owner();
    }

    @Test
    public void givenVehicle_WhenParke_ThenReturnTrue() {
        try {
            vehicle.setVehicleNumber("MH4R4545");
            String result = parkingLot.park(vehicle);
            Assert.assertEquals("park vehicle",result);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_shouldReturnException() {
        try {
            vehicle.setVehicleNumber("MH4R4545");
            parkingLot.park(vehicle);
            parkingLot.park(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("This vehicle already park",e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenUnPark_shouldReturnTrue() {
        try {
            parkingLot.park(vehicle);
            String  result=parkingLot.unPark(vehicle);
            Assert.assertEquals("unpark",result);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLot_WhenFull_shouldReturnTrue() {
        parkingLot.addObserver(owner);
        try {
            VehiclePOJO vehicle1 = new VehiclePOJO();
            vehicle1.setVehicleNumber("MH4R4545");
            parkingLot.park(vehicle1);
            VehiclePOJO vehicle2 = new VehiclePOJO();
            vehicle2.setVehicleNumber("MH4R4547");
            parkingLot.park(vehicle2);
            VehiclePOJO vehicle3 = new VehiclePOJO();
            vehicle3.setVehicleNumber("MH4R4548");
            String result = parkingLot.park(vehicle3);
            Assert.assertEquals("Full Lot A", owner.getParkingFull());
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotCapacity2_ShouldBeAbleToPark2Vehicles_ThenShouldInformOwner(){
     try{
         parkingLot.setCapacity(2);
         parkingLot.addObserver(owner);
         VehiclePOJO vehicle1 = new VehiclePOJO();
         vehicle1.setVehicleNumber("MH4R4545");
         parkingLot.park(vehicle1);
         VehiclePOJO vehicle2 = new VehiclePOJO();
         vehicle2.setVehicleNumber("MH4R4547");
         parkingLot.park(vehicle2);
         String parkingFull=owner.getParkingFull();
         Assert.assertTrue(parkingFull,true);
        } catch (ParkingLotException e) {
     }
    }

    @Test
    public void givenParkingLotIsFull_WhenInformAirportSecurity_shouldReturnTrue() {
        try {
            parkingLot.addObserver(owner);
            VehiclePOJO vehicle1 = new VehiclePOJO();
            vehicle1.setVehicleNumber("MH4R4545");
            parkingLot.park(vehicle1);
            VehiclePOJO vehicle2 = new VehiclePOJO();
            vehicle2.setVehicleNumber("MH4R4547");
            parkingLot.park(vehicle2);
            VehiclePOJO vehicle3 = new VehiclePOJO();
            vehicle3.setVehicleNumber("MH4R4548");
            parkingLot.park(vehicle3);
           // String parkingLotFull = airportSecurity.getParkingSlotFullOrNot();
        } catch (ParkingLotException e) {
            Assert.assertEquals("Full Lot 1", airportSecurity.getParkingSlotFullOrNot());
        }
    }

    @Test
    public void givenVehicleNotPresentInParkingLot_WhenUnPark_shouldThrowException() {
        try {
            vehicle.setVehicleNumber("MH4R4545");
            parkingLot.park(vehicle);
            vehicle.setVehicleNumber("MH4R4547");
            String  result=parkingLot.unPark(vehicle);;
        } catch (ParkingLotException e) {
            Assert.assertEquals("This vehicle not park in my parking lot",e.getMessage());
        }
    }

    @Test
    public void givenParkingSlotSystem_WhenParkingCapacitySet_ShouldReturnCapacity() {
        int parkingLotCapacity = parkingLot.setCapacity(100);
        Assert.assertEquals(100, parkingLotCapacity);
    }

        @Test
    public void givenAgainParkingSpaceAvailable_WhenInformOwner_ThenReturnTrue() {
        try{
        parkingLot.addObserver(owner);
        VehiclePOJO vehicle1 = new VehiclePOJO();
        vehicle1.setVehicleNumber("MH4R4545");
        parkingLot.park(vehicle1);
        VehiclePOJO vehicle2 = new VehiclePOJO();
        vehicle2.setVehicleNumber("MH4R4547");
        parkingLot.park(vehicle2);
        VehiclePOJO vehicle3 = new VehiclePOJO();
        vehicle3.setVehicleNumber("MH4R4548");
        parkingLot.unPark(vehicle3);
    } catch (ParkingLotException e) {
            Assert.assertEquals("This vehicle not park in my parking lot",e.getMessage() );
        }
    }

    @Test
    public void givenfindMyCar_WhenPark_ThenReturnCarPosition() {
        try {
            VehiclePOJO vehicle1 = new VehiclePOJO();
            parkingLot.park(vehicle1);
            VehiclePOJO vehicle2 = new VehiclePOJO();
            parkingLot.park(vehicle2);
            String result = parkingLot.isVehiclePark(vehicle);
            Assert.assertEquals("vehicle park in lot number 1", result);
        } catch (ParkingLotException e) {
        }
    }

    //7
        @Test
        public void givenVehicleParkInLot_WhenCharge_ThenReturnTrue()  {
        try {
            parkingLot.addObserver(owner);
            vehicle.setVehicleNumber("MH4R4545");
            parkingLot.park(vehicle);
            Assert.assertEquals("this vehicle charge Rs.10",owner.getParkingCharge());
        } catch (ParkingLotException e) {
        }
    }

    //9
    @Test
    public void givenParkingAttendant_WhenEvenlyDistribution_ThenReturn() {
        try {
            this.capacity = 4;
            this.slot = 2;
            parkingLot=new ParkingLotSystem(capacity,slot);
            parkingLot.addObserver(owner);
            vehicle.setVehicleNumber("MH4R4545");
            parkingLot.park(vehicle);
            VehiclePOJO vehicle1 = new VehiclePOJO();
            vehicle1.setVehicleNumber("MH4R4549");
            parkingLot.park(vehicle1);
            Assert.assertEquals("Full Lot A",owner.getParkingFull());
            VehiclePOJO vehicle2 = new VehiclePOJO();
            vehicle2.setVehicleNumber("MH4R4548");
            parkingLot.park(vehicle2);
            VehiclePOJO vehicle3 = new VehiclePOJO();
            vehicle3.setVehicleNumber("MH4R4547");
            parkingLot.park(vehicle3);
            Assert.assertEquals("Full Lot B",owner.getParkingFull());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenHandicapDriver_WhenNearestFreeSpace_ThenParkCar() throws ParkingLotException {
        this.capacity = 4;
        this.slot = 2;
        parkingLot.addObserver(owner);
        VehiclePOJO vehicle1 = new VehiclePOJO();
        parkingLot.park(vehicle1);
        VehiclePOJO vehicle2 = new VehiclePOJO();
        parkingLot.park(vehicle2);
        VehiclePOJO vehicle3 = new VehiclePOJO();
        parkingLot.park(vehicle3);
        VehiclePOJO vehicle = new VehiclePOJO();
        String result =parkingLot.park(vehicle);
        Assert.assertEquals("park vehicle", result);
    }
}
