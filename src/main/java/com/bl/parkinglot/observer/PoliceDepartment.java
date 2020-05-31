/***********************************************************************************************************************
 * @Purpose: In Police Department Check Vehicle number is Register or not
 * @Author: Priyanka Garad
 * Date: 24/5/2020
 ***********************************************************************************************************************/
package com.bl.parkinglot.observer;
import java.util.HashMap;
import java.util.Map;
public class PoliceDepartment implements Observer {
    private String vehicleLocation;
    public Map<String ,String> numberRegister = new HashMap<>();

    public PoliceDepartment() {
        numberRegister.put("MH4R4545","Toyota");
        numberRegister.put("MH4R4547","suzuki");
        numberRegister.put("MH4R4546","BMW");
    }

    public String getVehicleLocation() {
         return vehicleLocation;
    }

    public void setVehicleLocation(String vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
    }

    @Override
    public void update(Object status) {
        this.setVehicleLocation((String) status);
    }
}


