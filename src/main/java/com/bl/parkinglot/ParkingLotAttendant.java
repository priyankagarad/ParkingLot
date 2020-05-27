package com.bl.parkinglot;
import com.bl.parkinglot.service.ParkingLotSystem;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem;
    Integer capacity;
    int slot;
    Map<Integer,Integer> lots = new HashMap<>();
    LinkedHashMap parkingLot;

    public ParkingLotAttendant(LinkedHashMap<Integer, Object> parkingLot, Integer capacity, int slot) {
        this.parkingLot = parkingLot;
        this.capacity = capacity;
        this.slot = slot;
    }

    public int vehicleParkLotNumber(){
        Integer k=(checkLot()-1)*slot;
        k++;

        for (; k<=capacity ; k++)
            if (parkingLot.get(k) == null)
                return k;
        return k+1;
    }

    public int occupiedParkingLot(Object vehicle){
        int k = 0;
        for (Object o: parkingLot.values()){
            k++;
            if (o==vehicle)
                return k;
        }
        return k+1;
    }

    public int checkLot(){
        int i=0,count=0,key=0;
        for (Object o:parkingLot.values()) {
            if (o==null)
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
        int minCar = lots.keySet().stream().filter(key -> Collections.max(lots.values()).equals(lots.get(key)))
                .findFirst().get();
        return minCar;
    }

    public String isLotFull(){
        checkLot();
        Integer value = 0, lotNumber = 0;
        String lot = "";
        for (Object o:lots.values()){
            lotNumber++;
            if (o==value)
                lot = "Full Lot "+lotNumber;
        }
        return lot;
    }
}
