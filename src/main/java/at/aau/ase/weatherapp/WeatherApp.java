package at.aau.ase.weatherapp;

import at.aau.ase.weatherapp.sensors.Sensor;

import java.util.ArrayList;
import java.util.List;

public class WeatherApp {

    private String instanceID;
    private List<Sensor> sensors = new ArrayList<>();

    public WeatherApp(String instanceID)
    {
        this.instanceID = instanceID;
    }

    public WeatherApp(String instanceID, List<Sensor> sensors)
    {
        this.instanceID = instanceID;
        this.sensors = sensors;
    }

    public List<Sensor> getSensors()
    {
        return sensors;
    }

    public boolean addSensor(Sensor newSensor)
    {
        for(Sensor s : sensors)
        {
            if(s.getId().equals(newSensor.getId())){
                return false;
            }

        }
        return sensors.add(newSensor);
    }

    public boolean addSensors(List<Sensor> newSensors)
    {
        for(Sensor s : sensors)
        {
            for(Sensor ns : newSensors)
            {
                if(ns.getId().equals(s.getId()))
                {
                    return false;
                }
            }
        }

        return sensors.addAll(newSensors);

    }

    public boolean removeSensor(Sensor sensor)
    {
        return sensors.remove(sensor);
    }

    public boolean removeSensor(String sensorId)
    {
        boolean found = false;
        int i = 0;
        for(Sensor s: sensors)
        {
            if(s.getId().equals(sensorId))
            {
                found = true;
                break;
            }
            i++;
        }

        if(found){
            sensors.remove(i);
            return true;
        }

        return false;
    }




}
