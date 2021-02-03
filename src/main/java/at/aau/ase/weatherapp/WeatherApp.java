package at.aau.ase.weatherapp;

import at.aau.ase.weatherapp.com.WeatherServerConnection;
import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;

import java.util.ArrayList;
import java.util.List;

public class WeatherApp {

    private String instanceID;
    private List<Sensor> sensors = new ArrayList<>();
    private int sensorPollIntervalMS = 0;
    private WeatherAppWorker worker;

    public WeatherApp(String instanceID, int sensorPollIntervalMS, WeatherServerConnection com)
    {
        this.instanceID = instanceID;
        this.sensorPollIntervalMS = sensorPollIntervalMS;
        worker = new WeatherAppWorker(this,com);
    }

    public WeatherApp(String instanceID, List<Sensor> sensors, int sensorPollIntervalMS, WeatherServerConnection com)
    {
        this.instanceID = instanceID;
        this.sensors = sensors;
        this.sensorPollIntervalMS = sensorPollIntervalMS;
        worker = new WeatherAppWorker(this,com);
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

    public int getSensorPollInterval()
    {
        return sensorPollIntervalMS;
    }

    public boolean start()
    {
        for(Sensor s:sensors)
        {
            try {
                s.init();
            } catch (SensorException e) {
                e.printStackTrace();
                return false;
            }
        }

        return worker.start();
    }

    public boolean isRunning()
    {
        return worker.isRunning();
    }

    public boolean stop()
    {
        return worker.stop();
    }


}
