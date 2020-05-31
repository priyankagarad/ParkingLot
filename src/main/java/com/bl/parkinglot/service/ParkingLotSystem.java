/*********************************************************************************************************************
 * @purpose:To park and Unpark vehicle And Informing Parking Lot full,SpaceAvailable To Observer
 * @Author:priyanka Garad
 * Date:24/5/2020
 ********************************************************************************************************************/
package com.bl.parkinglot.service;
import com.bl.parkinglot.enums.Driver;
import com.bl.parkinglot.exception.ParkingLotException;
import com.bl.parkinglot.model.*;
import com.bl.parkinglot.observer.Observer;
import com.bl.parkinglot.observer.PoliceDepartment;
import java.text.ParseException;
import java.util.*;
public class ParkingLotSystem {
    public LinkedHashMap<Integer, Vehicle> parkingLot = new LinkedHashMap<>();
    public Map<Integer, Object> vehicleTime = new HashMap<>();
    private List<Observer> observableList = new ArrayList<>();
    ParkingLotAttendant attendant;
    PoliceDepartment police;
    private String isFull;
    ParkingSlotTime time;
    int slot = 3;
    String location = "";
    int fraudNumberplate = 0;

    public ParkingLotSystem(Integer capacity, int slot) {
        this.slot = slot;
        attendant = new ParkingLotAttendant(parkingLot, capacity, slot);
        time = new ParkingSlotTime(vehicleTime);
        police = new PoliceDepartment();
        for (Integer key = 1; key <= capacity; key++) {
            parkingLot.put(key, null);
            vehicleTime.put(key, null);
        }
    }

    /**
     *@purpose: add object to observableList
     */
    public void addObserver(Observer observable) {
        this.observableList.add(observable);
    }

    /**
     * @purpose:Send notification to observer
     * @param isFull
     */
    public void setStatus(String isFull) {
        this.isFull = isFull;
        for (Observer observable : this.observableList) {
            observable.update(this.isFull);
        }
    }

    /**
     * @purpose : Park Vehicle in Paring Lot
     * @param vehicle
     * @return : return message vehicle Park
     * @throws ParkingLotException : Vehicle Already Park the throw Exception
     */
    public String park(Vehicle vehicle) throws ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_ALREADY_PARK, "This vehicle already park");
        Integer key = attendant.vehicleParkLotNumber(vehicle);
        parkingLot.replace(key, vehicle);
        vehicleTime.replace(key, ParkingSlotTime.getCurrentTime());
        setStatus("this vehicle charge Rs.10");
        String lotStatus = attendant.isLotFull();
        setStatus(lotStatus);
        return "park vehicle";
    }

    /**
     * @Purpose: Check Vehicle is Park or not in Parking Lot
     * @param vehicle
     * @return : return vehicle Lot Number
     * @throws ParkingLotException :Vehicle is not present in parking Lot then throw exception
     */
    public String isVehiclePark(Vehicle vehicle) throws ParkingLotException {
        if (parkingLot.containsValue(vehicle))
            return "vehicle park in lot number " + attendant.occupiedParkingLot(vehicle);
        else
            throw new ParkingLotException(ParkingLotException.MyexceptionType.VEHICLE_NOT_PARK,
                    "This vehicle not park in my parking lot");
    }

    /**
     * @purpose:check vehicle is unPark from Parking lot
     * @param: vehicle
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
     * @purpose: Search Method Check Position of vehicle in parking Lot,vehicle present ot not ;
     * @param arg
     * @return : FraduPlateNumber
     * @throws ParseException
     */
    public int searching(String... arg) throws ParseException {
        if (arg.length == 2 && arg[0].contains(":") && arg[1].contains(":"))
            location = time.vehicleInTime(arg);
        else {
            for (Object vehicle : parkingLot.values()) {
                int count = 0;
                for (int index = 0; index < arg.length; index++)
                    if (vehicle.toString().contains(arg[index]))
                        count++;
                if (count == arg.length)
                    location += attendant.occupiedParkingLot(vehicle) + ",";
                else fraudNumberplate++;
            }
        }
        setStatus(location);
        return fraudNumberplate;
    }

    /**
     * @purpose:Fraudu Plate Number search
     * @return:return Status of fraudPlateNumber
     */
    public void fraudulentPlate() throws ParseException {
        String fraudPlate = "";
        int i = 1;
        for (Object vehicle : police.numberRegister.values()) {
            String keyVehicle = police.numberRegister.keySet().stream().filter(key -> vehicle.equals(police.numberRegister.get(key))).findFirst().get();
            String value = vehicle.toString();
            searching(keyVehicle, value);
            if (fraudNumberplate == parkingLot.size())
                fraudPlate += i + ",";
            fraudNumberplate = 0;
            i++;
        }
        setStatus(fraudPlate);
    }

    /**
     * @purpose: search vehicle slot number
     * @param handicap: driver type
     * @param arg
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

