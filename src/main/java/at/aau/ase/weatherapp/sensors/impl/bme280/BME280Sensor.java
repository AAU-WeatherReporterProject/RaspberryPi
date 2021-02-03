package at.aau.ase.weatherapp.sensors.impl.bme280;

import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;
import com.pi4j.io.i2c.I2CBus;
import org.json.simple.JSONObject;

import java.io.IOException;

public class BME280Sensor extends Sensor {

    private static final String ID_PREFIX = "BME280_";
    private byte i2cAddress = 0x00;
    private BME280Driver bme280 = null;

    public BME280Sensor(BME280Address i2cAddress) throws SensorException {
        super(ID_PREFIX + i2cAddress);
        this.i2cAddress = i2cAddress.getValue();
        bme280 = BME280Driver.getInstance(I2CBus.BUS_1, this.i2cAddress);
        try {
            bme280.open();
        } catch (IOException e) {
            throw new SensorException("Could not init bme280 on address " + i2cAddress);
        }
    }

    @Override
    public void init() throws SensorException{


    }

    @Override
    public JSONObject getData() throws SensorException {

        float[] values = new float[0];
        try {
            values = bme280.getSensorValues();
        } catch (IOException e) {
            //e.printStackTrace();
            throw new SensorException("Could not read sensor");
        }
        //LOG.info("temperature:" + values[0]);
        //LOG.info("humidity:" + values[1]);
        //LOG.info("pressure:" + values[2]);
        /*System.out.println("temperature:" + values[0]);
        System.out.println("humidity:" + values[1]);
        System.out.println("pressure:" + values[2]);
        System.out.println("______________________");
        */
        //JSON
        JSONObject obj = new JSONObject();
        //obj.put("id", "Sensor1");



        obj.put("humidity", new String(""+(int)values[1]));
        obj.put("temperature", new String("" + values[0]));
        obj.put("pressure", new String("" + values[2]));
        //just a fake value
        obj.put("skyState",new String("" + 3));

        /*
        obj.put("humidity", new String("" + 20));
        obj.put("temperature", new String("" + 23.4f));
        obj.put("pressure", new String("" + 900.25f));
        obj.put("skyState", new String("" + 3));
        */
        return obj;

    }

    @Override
    public boolean destroy() {
        return false;
    }
}
