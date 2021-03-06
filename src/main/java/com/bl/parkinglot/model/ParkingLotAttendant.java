 /**********************************************************************************************************************
 * @purpose: Store the Record of vehicle slot ,vehicle slot number
 * @Author:priyanka Garad
 * @Data:24/5/2020
 **********************************************************************************************************************/
 package com.bl.parkinglot.model;
import com.bl.parkinglot.enums.Driver;
import com.bl.parkinglot.exception.ParkingLotException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
public class ParkingLotAttendant {
    Integer capacity;
    int slot;
    Map<Integer,Integer> lots = new HashMap<>();
    LinkedHashMap parkingLot;

    public ParkingLotAttendant(LinkedHashMap<Integer, Vehicle> parkingLot, Integer capacity, int slot) {
        this.parkingLot = parkingLot;
        this.capacity = capacity;
        this.slot = slot;
    }

    /**
     *@purpose:Find Empty parking slot
     * @return: the key of vehicle
     */
    public int vehicleParkLotNumber(Vehicle vehicle) throws ParkingLotException {
        Integer key=0;
        if (vehicle.getDriver().getDriverType().equals(Driver.DriverType.HANDICAP))
            key=1;
        else if (vehicle.getVehicleType().equals("LARGE")){
            key=((checkLot()-1)*slot)+1;
            int lotCapacity=(key+slot)-1;
            for (; key<lotCapacity;key++)
                if (parkingLot.get(key)==null && parkingLot.get(key+1)==null)
                    return key;
                throw new ParkingLotException(ParkingLotException.MyexceptionType.LOT_IS_FULL,"Lot is full");
        }
        else if (vehicle.getVehicleType().equals("SMALL"))
            key=((checkLot()-1)*slot)+1;
            for (; key<=capacity ; key++)
            if (parkingLot.get(key) == null)
                return key;
                return key+1;
    }

    /**+
     *@purpose:Find Object key
     */
    public int occupiedParkingLot(Object vehicle){
        int k = 0;
        for (Object vehicleVariable: parkingLot.values()) {
            k++;
            if (vehicleVariable==vehicle)
                return k;
        }
        return k+1;
    }

    /**+
     *@purpose:To check Empty space in Parking lot
     */
    public int checkLot(){
        int i=0,count=0,key=0;
        for (Object vehicle:parkingLot.values()) {
            if (vehicle==null)
                count++;
            i++;
            if (i%slot==0){
                lots.put(++key,count);
                count = 0;
            }
        }
        return minimumCarsLots();
    }


    public int minimumCarsLots(){
        return lots.keySet().stream().filter(key -> Collections.max(lots.values()).equals(lots.get(key)))
                .findFirst().get();
    }

    /**
     *@purpose: To check Parking lot is full or not
     */
    public String isLotFull(){
        checkLot();
        Integer value = 0, lotNumber = 0;
        String lot = "";
        for (Object vehicle:lots.values()){
            lotNumber++;
            if (vehicle==value)
                lot = "Full Lot "+lotNumber;
        }
        return lot;
    }
}
