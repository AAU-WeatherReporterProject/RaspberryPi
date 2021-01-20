package at.aau.ase.weatherapp.sensors.impl;

import at.aau.ase.weatherapp.sensors.Sensor;

import java.util.ArrayList;
import java.util.List;

public class SensorManager {

    private static List<Sensor> sensors = new ArrayList<>();

    protected boolean addSensor(Sensor sensor)
    {

        for(Sensor s : sensors){

            if(s.getId().equals(sensor.getId())){
                return false;
            }
        }
        sensors.add(sensor);
        return true;
    }

    public static List<Sensor> getSensors()
    {
        return sensors;
    }

    public static boolean shutdownAllSensors(){

        for(Sensor s : sensors){
            s.destroy();
        }

        return true;
    }

}
