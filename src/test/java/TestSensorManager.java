import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;
import at.aau.ase.weatherapp.sensors.impl.SensorManager;
import at.aau.ase.weatherapp.sensors.impl.virtual.VirtualBME280Sensor;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSensorManager {

    @Test
    public void testGetSensors() throws SensorException {
        Sensor sensor1 = new VirtualBME280Sensor("BME280");
        Sensor sensor2 = new VirtualBME280Sensor("BME280_2");

        List<Sensor> sensor3 = SensorManager.getSensors();

        assertEquals(sensor3.size(),2);
    }
}
