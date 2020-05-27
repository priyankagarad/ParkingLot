/*********************************************************************************************************************
 * @purpose:ParkingLot System Class Define the the function of park,unpark the Vehicle and set The capacity of vehicle
 * @Author:priyanka garad
 * Date:24/5/2020
 ********************************************************************************************************************/
package com.bl.parkinglot.service;
import com.bl.parkinglot.ParkingLotAttendant;
import com.bl.parkinglot.model.Observer;
import com.bl.parkinglot.exception.ParkingLotException;
import com.bl.parkinglot.model.Vehicle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ParkingLotSystem {
    public LinkedHashMap<Integer, Object> parkingLot = new LinkedHashMap<>();
    private List<Observer> observableList = new ArrayList<>();
    ParkingLotAttendant attendant;
    private String isFull;
    Integer key = 0;

    public int setCapacity(int capacity){
        return capacity;
    }

    /**+
     * @purpose:constructor to put key and null value
     * key is number and value is object means vehicle
     * @param capacity:-capacity of parking lot
     * @param slot:slot of parking lot
     */
    public ParkingLotSystem(Integer capacity, int slot) {
        attendant = new ParkingLotAttendant(parkingLot,capacity,slot);
        for (Integer key = 1; key <= capacity; key++) {
            parkingLot.put(key, null);
        }
    }

    /**+
     * @param observable:observer
     */
    public void addObserver(Observer observable) {
        this.observableList.add(observable);
    }

    /** send notification */
    public void setStatus(String isFull) {
        this.isFull = isFull;
        for (Observer observable : this.observableList) {
            observable.update(this.isFull);
        }
    }

    /** park vehicle and check parking lot */
    public String park(Vehicle vehicle) throws ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK, "This vehicle already park");
            key = attendant.vehicleParkLotNumber();
            parkingLot.replace(key, vehicle);
            setStatus("this vehicle charge Rs.10");
            String lotStatus = attendant.isLotFull();
            setStatus(lotStatus);
            return "park vehicle";
         }

    /**Check Vehicle is present or not*/
    public String  isVehiclePark(Vehicle vehicle) throws ParkingLotException {
            if (parkingLot.containsValue(vehicle))
                return "vehicle park in lot number "+attendant.occupiedParkingLot(vehicle);
            else
                throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                        "This vehicle not park in my parking lot");
        }

    /** unpark vehicle */
    public String unPark(Vehicle vehicle) throws ParkingLotException {
    int key = attendant.occupiedParkingLot(vehicle);
        if (parkingLot.containsValue(vehicle)) {
            parkingLot.replace(key, null);
            setStatus("Have Space lot number " + key);
            return "unpark";
        } else
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                    "This vehicle not park in my parking lot");
    }
}