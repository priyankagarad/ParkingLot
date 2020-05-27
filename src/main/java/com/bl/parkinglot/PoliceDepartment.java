package com.bl.parkinglot;
import com.bl.parkinglot.model.Observer;
public class PoliceDepartment implements Observer {
    private String vehicleColore;

    public String getVehicleColore() {
        return vehicleColore;
    }

    public void setVehicleColore(String vehicleColore) {
        this.vehicleColore = vehicleColore;
    }

    @Override
    public void update(Object status) {
        this.setVehicleColore((String) status);
    }
}

