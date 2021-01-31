package at.aau.ase.weatherapp.sensors.impl.virtual;

import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;
import org.json.simple.JSONObject;

public class VirtualTempSensor extends Sensor {

    private static final String ID = "VirtualSensor";

    public VirtualTempSensor(String name) throws SensorException{
        super(ID + name);
    }

    @Override
    public void init() throws SensorException{

    }

    @Override
    public JSONObject getData() throws SensorException {
        return null;
    }

    @Override
    public boolean destroy() {
        return false;
    }
}
