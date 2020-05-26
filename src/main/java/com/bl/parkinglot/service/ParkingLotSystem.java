/*********************************************************************************************************************
 * @purpose:ParkingLot System Class Define the the function of park,unpark the Vehicle and set The capacity of vehicle
 * @Author:priyanka garad
 * Date:24/5/2020
 ********************************************************************************************************************/
package com.bl.parkinglot.service;
import com.bl.parkinglot.Observer;
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
    private List<Observer> observableList = new ArrayList<>();
    AirportSecurity airportSecurity = new AirportSecurity();
    Owner owner = new Owner();
    private String isFull;

    public void addObserver(Observer observable) {
        this.observableList.add(observable);
    }

    public void setStatus(String isFull) {
        this.isFull = isFull;
        for (Observer observable : this.observableList) {
            observable.update(this.isFull);
        }
    }

    public String park(VehiclePOJO vehicle) throws  ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK,"This vehicle already park");
        parkingLot.put(vehicle.getVehicleNumber(),vehicle);
        setStatus("this vehicle charge Rs.10");
        if (parkingLot.size()%3==0 && parkingLot.size() != 0)
            setStatus("Full");
        return "park vehicle";
    }

    // Check Vehicle is present or not
    public String  isVehiclePark(VehiclePOJO vehicle) throws ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber())) {
            int lotPosition = vehicleParkLotNumber(vehicle);
            return "vehicle park in lot number "+(lotPosition+1);
        }
        else {
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                    "This vehicle not park in my parking lot");
        }
    }
    public int vehicleParkLotNumber(VehiclePOJO vehicle){
        Set<String> keys = parkingLot.keySet();
        List<String> listKeys = new ArrayList<String>( keys );
        return listKeys.indexOf(vehicle.getVehicleNumber());
    }
    // un park vehicle
    public String unPark(VehiclePOJO vehicle) throws ParkingLotException {
        if (parkingLot.containsKey(vehicle.getVehicleNumber())) {
            int lotPosition = vehicleParkLotNumber(vehicle);
            parkingLot.remove(vehicle.getVehicleNumber());
            if (parkingLot.size() < 3)
                setStatus("Have Space lot number "+(lotPosition+1));
            return "unpark";
        }
        else{
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                    "This vehicle not park in my parking lot");
        }
    }
}