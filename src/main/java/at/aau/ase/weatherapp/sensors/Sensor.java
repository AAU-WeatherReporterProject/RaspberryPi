package at.aau.ase.weatherapp.sensors;

import at.aau.ase.weatherapp.sensors.impl.SensorManager;

import java.io.IOException;

public abstract class Sensor extends SensorManager {

    private String id = "";
    private String description = "";

    public abstract void init();
    public abstract String getData() throws SensorException;
    public abstract boolean destroy();

    protected Sensor(String id, String description) throws SensorException
    {
        this.id = id;
        this.description = description;
        if(!addSensor(this)){
            throw new SensorException("Sensor with ID: " + id + " already exists!");
        }
        init();
    }

    protected Sensor(String id) throws SensorException
    {
        System.out.println("ASDF");
        this.id = id;
        if(!addSensor(this)){
            throw new SensorException("Sensor with ID: " + id + " already exists!");
        }
        init();
    }

    public String getId()
    {
        return id;
    }



    public String getDescription()
    {
        return description;
    }




}
