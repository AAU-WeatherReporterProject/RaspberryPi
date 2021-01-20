package at.aau.ase.application;

import at.aau.ase.weatherapp.WeatherApp;
import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;
import at.aau.ase.weatherapp.sensors.impl.VirtualTempSensor;
import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args){

        System.out.println("ASDF");
        W1Master w1Master = new W1Master();
        for (TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)) {
            System.out.printf("%-20s %3.1f°C %3.1f°F\n", device.getName(), device.getTemperature(),
                    device.getTemperature(TemperatureScale.CELSIUS));
        }
        System.out.println("END");
        /*
        //________Create sensors________
        System.out.print("Create sensors... ");
        List<Sensor> wAppSensors = new ArrayList<>();
        try {
            wAppSensors.add(new VirtualTempSensor("Dummy1"));

        } catch (SensorException e) {
            e.printStackTrace();
        }
        System.out.println("Done");

        //_______Setup weather app_______
        System.out.println("Create weather app... ");
        WeatherApp wApp = new WeatherApp("WeatherApp1");
        wApp.addSensors(wAppSensors);
        System.out.println("Done");

        //______
        System.out.println("Run application!");


        System.out.print("Shutting down application... ");
        System.out.println("Done");
        */
    }
}
