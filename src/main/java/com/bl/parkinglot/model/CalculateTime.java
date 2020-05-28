package com.bl.parkinglot.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalculateTime {
    public Map<Integer,Object> vehicleTime = new HashMap<>();
    String location="";

    public CalculateTime(Map<Integer, Object> vehicleTime) {
        this.vehicleTime = vehicleTime;
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
    }

    public String vehicleInTime(String... contains) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date startTime = format.parse(contains[0]);
        Date endTime = format.parse(contains[1]);
        try {
            for (int vehicleKey=1;vehicleKey<=vehicleTime.size();vehicleKey++) {
                Date userDate = format.parse(vehicleTime.get(vehicleKey).toString());
                if (userDate.after(startTime) && userDate.before(endTime))
                    location += vehicleKey +",";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }
}
