/*********************************************************************************************************************
 * @purpose:ParkingLot System Class Define the the function of park,unpark the Vehicle and set The capacity of vehicle
 * @Author:priyanka garad
 * Date:24/5/2020
 ********************************************************************************************************************/
package com.bl.parkinglot.service;
import com.bl.parkinglot.exception.ParkingLotException;
import com.bl.parkinglot.model.AirportSecurity;
import com.bl.parkinglot.model.Owner;
import com.bl.parkinglot.model.VehiclePOJO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
public class ParkingLotSystem {
    LinkedHashMap<String,Object> parkingLot = new LinkedHashMap<String, Object>();
    AirportSecurity airportSecurity = new AirportSecurity();

    public String park(VehiclePOJO vehicle) throws  ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK,"This vehicle is already park");
        if (parkingLot.size()%2==0 && parkingLot.size() != 0) {
            parkingLot.put(vehicle.getVehicleNumber(),vehicle);
            airportSecurity.setParkingSlotFullOrNot("parking lot is full");
            return "parking lot is full";
        }else {
            parkingLot.put(vehicle.getVehicleNumber(),vehicle);
            return "record Insert";
        }
    }

    public boolean isVehiclePark(VehiclePOJO vehicle) {
        if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            return true;
        return false;
    }

    public String unPark(VehiclePOJO vehicle) throws ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber())) {
            Set<String> keys = parkingLot.keySet();
            List<String> listKeys = new ArrayList<String>( keys );
            int lotPosition = listKeys.indexOf(vehicle.getVehicleNumber());
            parkingLot.remove(vehicle.getVehicleNumber());
            if (parkingLot.size() < 3) {
                new Owner().setParkingFullOrNot("parking lot space available "+(lotPosition+1));
                return "space available";
            }
            return "unpark";
        }
        else{
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                    "This vehicle not park in my parking lot");
        }
    }
}
