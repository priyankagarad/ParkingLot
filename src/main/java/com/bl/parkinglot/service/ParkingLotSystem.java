/*********************************************************************************************************************
 * @purpose:ParkingLot System Class Define the the function of park,unpark the Vehicle and set The capacity of vehicle
 * @Author:priyanka garad
 * Date:24/5/2020
 ********************************************************************************************************************/
package com.bl.parkinglot.service;
import com.bl.parkinglot.Observer;
import com.bl.parkinglot.exception.ParkingLotException;
import com.bl.parkinglot.model.VehiclePOJO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ParkingLotSystem {
    LinkedHashMap<Integer, Object> parkingLot = new LinkedHashMap<Integer, Object>();
    private List<Observer> observableList = new ArrayList<>();
    private String isFull;
    Integer capacity;
    int slot = 2;
    char sloatName = 'A';
    Integer key = 0;

    public ParkingLotSystem(Integer capacity, int slot) {
        this.capacity = capacity;
        this.slot = slot;
        for (Integer key = 1; key <= capacity; key++) {
            parkingLot.put(key, null);
        }
    }

    public ParkingLotSystem() {
    }

    public void addObserver(Observer observable) {
        this.observableList.add(observable);
    }

    public void setStatus(String isFull) {
        this.isFull = isFull;
        for (Observer observable : this.observableList) {
            observable.update(this.isFull);
        }
    }

    public String park(VehiclePOJO vehicle) throws ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK, "This vehicle already park");
        key = vehicleParkLotNumber();
        parkingLot.replace(key, vehicle);
        setStatus("this vehicle charge Rs.10");
        if (key % slot == 0 || key == capacity) {
            setStatus("Full Lot " + sloatName);
            sloatName++;
        }
        return "park vehicle";
    }

    public int vehicleParkLotNumber() {
        Integer k = 1;
        for (; k <= capacity; k++)
            if (parkingLot.get(k) == null)
                return k;
        return k + 1;
    }

    public int occupiedParkingLot(VehiclePOJO vehicle) {
        int k = 0;
        for (Object o : parkingLot.values()) {
            k++;
            if (o == vehicle)
                return k;
        }
        return k + 1;
    }

    public String unPark(VehiclePOJO vehicle) throws ParkingLotException {
        int key = occupiedParkingLot(vehicle);
        if (parkingLot.containsValue(vehicle)) {
            parkingLot.replace(key, null);
            setStatus("Have Space lot number " + key);
            return "unpark";
        } else
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                    "This vehicle not park in my parking lot");
    }
}