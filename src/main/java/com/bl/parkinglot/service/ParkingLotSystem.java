/*********************************************************************************************************************
 * @purpose:ParkingLot System Class Define the the function of park,unpark the Vehicle and set The capacity of vehicle
 * @Author:priyanka garad
 * Date:24/5/2020
 ********************************************************************************************************************/
package com.bl.parkinglot.service;
import com.bl.parkinglot.exception.ParkingLotException;
import com.bl.parkinglot.model.*;
import com.bl.parkinglot.model.Observer;
import java.text.ParseException;
import java.util.*;
public class ParkingLotSystem {
    public LinkedHashMap<Integer, Vehicle> parkingLot = new LinkedHashMap<>();
    public Map<Integer, Object> vehicleTime = new HashMap<>();
    private List<com.bl.parkinglot.model.Observer> observableList = new ArrayList<>();
    ParkingLotAttendant attendant;
    PoliceDepartment police;
    private String isFull;
    CalculateTime time;
    int slot = 3;
    String location = "";
    int froudNumberplate = 0;

    /**
     * constructor to put key and null value
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
     *add object to observableList
     */

    public void addObserver(com.bl.parkinglot.model.Observer observable) {
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
     * @purpose:Check vehicle is park in parking lot
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
     * @purpose:Check Vehicle is present or not
     */

    public String isVehiclePark(Vehicle vehicle) throws ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            return "vehicle park in lot number " + attendant.occupiedParkingLot(vehicle);
        else
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                    "This vehicle not park in my parking lot");
    }

    /**
     * @purpose:check vehicle unPark Information
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

    /**
     * @purpose:Check vehicle Information is present in ParkingLotAttendant
     **/

    public int serching(String... arg) throws ParseException {
        if (arg.length == 2 && arg[0].contains(":") && arg[1].contains(":"))
            location = time.vehicleInTime(arg);
        else {
            for (Object o : parkingLot.values()) {
                int count = 0;
                for (int index = 0; index < arg.length; index++)
                    if (o.toString().contains(arg[index]))
                        count++;
                if (count == arg.length)
                    location += attendant.occupiedParkingLot(o) + ",";
                else froudNumberplate++;
            }
        }
        setStatus(location);
        return froudNumberplate;
    }

    /**
     * @purpose:Check vehicle number is Register in Police Department
     */

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

    /**
     * @purpose:Search Vehicle related Information is in parking lot
     */

    public void searchInSlot (Driver.DriverType handicap, String... arg){
        location = "";
        int upTo = (((arg[0].charAt(0) - 64) - 1) * slot) + 1 + slot;
        for (int key = (((arg[0].charAt(0) - 64) - 1) * slot) + 1; key < upTo; key++) {
            Vehicle vehicle = parkingLot.get(key);
            String vehicleToString = vehicle.toString();
            int count = 0;
            for (int index = 1; index < arg.length; index++)
                if (vehicleToString.contains(arg[index]) && vehicle.getDriver().getDriverType().equals(handicap))
                    count++;
                if (count == arg.length - 1)
                    location += attendant.occupiedParkingLot(vehicle) + ",";
                }
        setStatus(location);
    }
}

