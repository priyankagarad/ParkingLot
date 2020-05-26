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
    private int capacity;
    private int slot;

    @Before
    public void setUp(){
        vehicle = new VehiclePOJO();
        parkingLot = new ParkingLotSystem();
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
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_shouldReturnException() {
        try {
            vehicle.setVehicleNumber("MH4R4545");
            parkingLot.park(vehicle);
            parkingLot.park(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("This vehicle is already park",e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenUnPark_shouldReturnTrue() {
        try {
            parkingLot.park(vehicle);
            String  result=parkingLot.unPark(vehicle);
            Assert.assertEquals("space available",result);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLot_WhenFull_shouldReturnTrue() {
        try {
            VehiclePOJO vehicle = new VehiclePOJO();
            vehicle.setVehicleNumber("MH4R4545");
            parkingLot.park(vehicle);
            vehicle.setVehicleNumber("MH4R4547");
            parkingLot.park(vehicle);
            vehicle.setVehicleNumber("MH4R4548");
            String result = parkingLot.park(vehicle);
            Assert.assertEquals("parking lot is full",result);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotIsFull_WhenInformAirportSecurity_shouldReturnTrue() {
        try {
            VehiclePOJO vehicle1 = new VehiclePOJO();
            vehicle.setVehicleNumber("MH4R4545");
            VehiclePOJO vehicle2 = new VehiclePOJO();
            parkingLot.park(vehicle2);
            vehicle.setVehicleNumber("MH4R4547");
            VehiclePOJO vehicle3 = new VehiclePOJO();
            parkingLot.park(vehicle3);
            vehicle.setVehicleNumber("MH4R4548");
            String parkingLotFull = parkingLot.park(vehicle);
            Assert.assertEquals("full",airportSecurity.getParkingSlotFullOrNot());
        } catch (ParkingLotException e) {
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
    public void givenAgainParkingSpaceAvailable_WhenInformOwner_ThenReturnTrue() throws ParkingLotException {
        VehiclePOJO vehicle = new VehiclePOJO();
        vehicle.setVehicleNumber("MH4R4545");
        parkingLot.park(vehicle);
        vehicle.setVehicleNumber("MH4R4547");
        parkingLot.park(vehicle);
        vehicle.setVehicleNumber("MH4R4548");
        parkingLot.park(vehicle);
        String result=parkingLot.unPark(vehicle);
        Assert.assertEquals("space available",result);
    }

    @Test
    public void givenFindCar_WhenPark_shouldReturnCarPosition() {
        try {
            parkingLot.addObserver(owner);
            vehicle.setVehicleNumber("MH4R4545");
            parkingLot.park(vehicle);
            Assert.assertEquals("this vehicle charge Rs.10",owner.getParkingCharge());
        } catch (ParkingLotException e) {
        }
    }

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
}
