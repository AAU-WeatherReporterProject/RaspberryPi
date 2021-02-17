package at.aau.ase.application;

/*import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;
*/
import at.aau.ase.weatherapp.WeatherApp;
import at.aau.ase.weatherapp.com.WeatherServerConnectionDefault;
import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;
import at.aau.ase.weatherapp.sensors.impl.SensorManager;
import at.aau.ase.weatherapp.sensors.impl.bme280.BME280Address;
import at.aau.ase.weatherapp.sensors.impl.bme280.BME280Driver;
import at.aau.ase.weatherapp.sensors.impl.bme280.BME280Sensor;
import at.aau.ase.weatherapp.sensors.impl.virtual.VirtualBME280Sensor;
import com.pi4j.io.i2c.I2CBus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    //private static final String URL = "http://192.168.178.21:8098/api/v1/ingest";
    //private static final String URL = "http://35.157.65.50:8098/api/v1/ingest";
    private static final String  URL = "http://laubi.at:8098/api/v1/ingest";

    public static void main(String[] args){


        //________Create sensors________
        System.out.print("Create sensors... ");

        List<Sensor> wAppSensors = new ArrayList<>();
        try {
            //wAppSensors.add(new VirtualTempSensor("Dummy1"));

            //use this for raspberry pi!
            //wAppSensors.add(new BME280Sensor(BME280Address.I2C_0x76));

            //use this on your computer
            wAppSensors.add(new VirtualBME280Sensor("BME280"));
            //wAppSensors.add(new VirtualBME280Sensor("BME280"));

        } catch (SensorException e) {
            e.printStackTrace();
        }
        System.out.println("Done");

        //_______Setup weather app_______

        System.out.println("Create weather app... ");
        WeatherApp wApp = new WeatherApp("WeatherApp_Location1", 10000, new WeatherServerConnectionDefault(URL));
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


        System.out.print("Shutting down application... ");
        SensorManager.shutdownAllSensors();
        System.out.println("Done");

    }
}
