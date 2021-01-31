package at.aau.ase.application;

/*import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;
*/
import at.aau.ase.weatherapp.WeatherApp;
import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;
import at.aau.ase.weatherapp.sensors.impl.SensorManager;
import at.aau.ase.weatherapp.sensors.impl.bme280.BME280Address;
import at.aau.ase.weatherapp.sensors.impl.bme280.BME280Driver;
import at.aau.ase.weatherapp.sensors.impl.bme280.BME280Sensor;
import com.pi4j.io.i2c.I2CBus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args){
        /*
        {
    "metadata": {
        "key": "TestLocation"
    },
    "measurements": [
        {
            "temperature": "12.11",
             "humidity":"%"
            "pressure":"hp"
            "skyState": "2"
        }
    ]
}
         */
/*
        JSONObject obj = new JSONObject();
        obj.put("id", "Sensor1");
        obj.put("humidity", 50.4f);
        obj.put("temperature", 23.4f);
        obj.put("pressure", 1013.25f);

        JSONObject obj2 = new JSONObject();
        obj2.put("key","testlocation");

        JSONObject obj3 = new JSONObject();
        obj3.put("metadata", obj2);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(obj);

        obj3.put("measurement",jsonArray);

        System.out.println(obj3.toJSONString());
        */

        //________Create sensors________
        System.out.print("Create sensors... ");

        List<Sensor> wAppSensors = new ArrayList<>();
        try {
            //wAppSensors.add(new VirtualTempSensor("Dummy1"));
            //BME280Sensor sensor = new BME280Sensor(BME280Address.I2C_0x76);
            wAppSensors.add(new BME280Sensor(BME280Address.I2C_0x76));

        } catch (SensorException e) {
            e.printStackTrace();
        }
        System.out.println("Done");

        //_______Setup weather app_______
        System.out.println("Create weather app... ");
        WeatherApp wApp = new WeatherApp("WeatherApp_Location1", 1000);
        wApp.addSensors(wAppSensors);
        System.out.println("Done");


        wApp.start();
        System.out.println("Run application!");
        boolean running = true;
        Scanner scn = new Scanner(System.in);
        while(running)
        {
            String s = scn.nextLine();
            if(s.equals("close")){
                running = false;
            }

        }
        wApp.stop();


        //______



        System.out.print("Shutting down application... ");
        SensorManager.shutdownAllSensors();
        System.out.println("Done");

    }
}
