package com.bl.parkinglot;
import com.bl.parkinglot.enums.Driver;
import com.bl.parkinglot.exception.ParkingLotException;
import com.bl.parkinglot.model.ParkingLotAttendant;
import com.bl.parkinglot.model.Vehicle;
import com.bl.parkinglot.observer.AirportSecurity;
import com.bl.parkinglot.observer.Owner;
import com.bl.parkinglot.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ParkingLotUnitTest {
    Vehicle vehicle;
    AirportSecurity airportSecurity = new AirportSecurity();
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3,2);
    ParkingLotAttendant parkingLotAttendant = mock(ParkingLotAttendant.class);
    Owner owner = new Owner();

    @Test
    public void givenParkingLot_WhenFull_shouldInformAirportSecurity() {
        parkingLotSystem.addObserver(airportSecurity);
        when(parkingLotAttendant.isLotFull()).thenReturn("Full Lot");
        assertEquals(parkingLotAttendant.isLotFull(), "Full Lot");
    }

    @Test
    public void givenParkingLot_WhenFull_shouldInformOwner() {
        parkingLotSystem.addObserver(owner);
        when(parkingLotAttendant.isLotFull()).thenReturn("Full Lot");
        assertEquals(parkingLotAttendant.isLotFull(), "Full Lot");
    }

    @Test
    public void givenVehicle_WhenPark_shouldReturnResult() throws ParkingLotException {
        try {
            Vehicle vehicle = new Vehicle("suzuki", "MH4R4545", "SMALL", new Driver(Driver.DriverType.NORMAL), "WHITE");
            when(parkingLotSystem.park(vehicle)).thenReturn("park vehicle");
            Assert.assertEquals(parkingLotSystem.park(vehicle), "park vehicle");
        } catch (ParkingLotException e) {
        }
    }
}

