package com.bl.parkinglot;
import com.bl.parkinglot.model.Observer;
public class PoliceDepartment implements Observer {
    private String vehicleLocation;
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


