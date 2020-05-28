/*********************************************************************************************************************
 * @purpose:ParkingLot System Class Define the the function of park,unpark the Vehicle and set The capacity of vehicle
 * @Author:priyanka garad
 * Date:24/5/2020
 ********************************************************************************************************************/
package com.bl.parkinglot.service;
import com.bl.parkinglot.model.CalculateTime;
import com.bl.parkinglot.model.Driver;
import com.bl.parkinglot.model.ParkingLotAttendant;
import com.bl.parkinglot.model.PoliceDepartment;
import com.bl.parkinglot.model.Observer;
import com.bl.parkinglot.exception.ParkingLotException;
import com.bl.parkinglot.model.Vehicle;
import java.text.ParseException;
import java.util.*;

public class ParkingLotSystem {
    public LinkedHashMap<Integer, Vehicle> parkingLot = new LinkedHashMap<>();
    public Map<Integer, Object> vehicleTime = new HashMap<>();
    private List<Observer> observableList = new ArrayList<>();
    ParkingLotAttendant attendant;
    PoliceDepartment police;
    private String isFull;
    CalculateTime time;
    int slot = 3;
    String location = "";
    int froudNumberplate = 0;

    public int setCapacity(int capacity) {
        return capacity;
    }

    /**
     * @param capacity:-capacity of parking lot
     * @param slot
     * @param capacity
     */

    public ParkingLotSystem(Integer capacity, int slot) {
        this.slot = slot;
        attendant = new ParkingLotAttendant(parkingLot, capacity, slot);
        time = new CalculateTime(vehicleTime);
        police = new PoliceDepartment();
        for (Integer key = 1; key <= capacity; key++) {
            parkingLot.put(key, null);
            vehicleTime.put(key, null);
        }
    }
    public ParkingLotSystem(){}

    /**
     * @param observable:observer
     */
    public void addObserver(Observer observable) {
        this.observableList.add(observable);
    }

    /**
     * send notification
     */
    public void setStatus(String isFull) {
        this.isFull = isFull;
        for (Observer observable : this.observableList) {
            observable.update(this.isFull);
        }
    }

    /**
     * park vehicle and check parking lot
     */
    public String park(Vehicle vehicle) throws ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK, "This vehicle already park");
        Integer key = attendant.vehicleParkLotNumber(vehicle);
        parkingLot.replace(key, vehicle);
        vehicleTime.replace(key, CalculateTime.getCurrentTime());
        setStatus("this vehicle charge Rs.10");
        String lotStatus = attendant.isLotFull();
        setStatus(lotStatus);
        return "park vehicle";
    }

    /**
     * Check Vehicle is present or not
     */
    public String isVehiclePark(Vehicle vehicle) throws ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            return "vehicle park in lot number " + attendant.occupiedParkingLot(vehicle);
        else
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                    "This vehicle not park in my parking lot");
    }

    /**
     * unpark vehicle
     */
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

    public int serching(String... contains) throws ParseException {
        if (contains.length == 2 && contains[0].contains(":") && contains[1].contains(":"))
            location = time.vehicleIntime(contains);
        else {
            for (Object o : parkingLot.values()) {
                int count = 0;
                for (int index = 0; index < contains.length; index++)
                    if (o.toString().contains(contains[index]))
                        count++;
                if (count == contains.length)
                    location += attendant.occupiedParkingLot(o) + ",";
                else froudNumberplate++;
            }
        }
        setStatus(location);
        return froudNumberplate;
    }

    public void fraudulentPlate() throws ParseException {
        String fraudPlate = "";
        int i = 1;
        for (Object o : police.numberRegister.values()) {
            String keyv = police.numberRegister.keySet().stream().filter(key -> o.equals(police.numberRegister.get(key))).findFirst().get();
            String value = o.toString();
            serching(keyv, value);
            if (froudNumberplate == parkingLot.size())
                fraudPlate += i + ",";
            froudNumberplate = 0;
            i++;
        }
        setStatus(fraudPlate);
    }
    
    public void serchInSlot (Driver.DriverType handicap, String... contain){
        location = "";
        int upTo = (((contain[0].charAt(0) - 64) - 1) * slot) + 1 + slot;
        for (int key = (((contain[0].charAt(0) - 64) - 1) * slot) + 1; key < upTo; key++) {
            Vehicle vehicle = parkingLot.get(key);
            String vehicleToString = vehicle.toString();
            int count = 0;
            for (int index = 1; index < contain.length; index++)
                if (vehicleToString.contains(contain[index]) && vehicle.getDriver().getDriverType().equals(handicap))
                    count++;
                if (count == contain.length - 1)
                    location += attendant.occupiedParkingLot(vehicle) + ",";
                }
                setStatus(location);
            }
        }

