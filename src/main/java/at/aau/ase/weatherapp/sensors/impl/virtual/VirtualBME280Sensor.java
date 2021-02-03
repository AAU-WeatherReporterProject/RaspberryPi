package at.aau.ase.weatherapp.sensors.impl.virtual;

import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;
import org.json.simple.JSONObject;

public class VirtualBME280Sensor extends Sensor {


    public VirtualBME280Sensor(String id) throws SensorException {
        super(id);
    }

    @Override
    public void init() throws SensorException {

    }

    @Override
    public JSONObject getData() throws SensorException {
        JSONObject obj = new JSONObject();
        //obj.put("id", "Sensor1");

        obj.put("humidity", new String("" + 20));
        obj.put("temperature", new String("" + 23.4f));
        obj.put("pressure", new String("" + 900.25f));
        obj.put("skyState", new String("" + 3));

        return obj;
    }

    @Override
    public boolean destroy() {
        return false;
    }
}
