package at.aau.ase.weatherapp.sensors.impl;

import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;

public class VirtualTempSensor extends Sensor {

    private static final String ID = "VirtualSensor";

    public VirtualTempSensor(String name) throws SensorException{
        super(ID + name);
    }

    @Override
    public void init() {

    }

    @Override
    public String getData() throws SensorException {
        return null;
    }

    @Override
    public boolean destroy() {
        return false;
    }
}
