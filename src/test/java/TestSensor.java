import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;
import at.aau.ase.weatherapp.sensors.impl.virtual.VirtualBME280Sensor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class TestSensor {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void test2SameSensors() throws SensorException {

        exceptionRule.expect(SensorException.class);
        exceptionRule.expectMessage("Sensor with ID: BME280 already exists!");
        Sensor sensor1 = new VirtualBME280Sensor("BME280");
        Sensor sensor2 = new VirtualBME280Sensor("BME280");

    }



}
