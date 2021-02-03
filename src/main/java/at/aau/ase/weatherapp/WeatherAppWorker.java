package at.aau.ase.weatherapp;

import at.aau.ase.weatherapp.com.WeatherServerConnection;
import at.aau.ase.weatherapp.com.WeatherServerConnectionException;
import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;

public class WeatherAppWorker implements Runnable{


    private WeatherApp app = null;
    private WeatherServerConnection com = null;
    private Thread thread;
    private boolean running = false;

    public WeatherAppWorker(WeatherApp app,WeatherServerConnection com)
    {
        this.app = app;
        this.com = com;

    }

    public boolean start()
    {
        try {
            com.open();
        } catch (WeatherServerConnectionException e) {
            e.printStackTrace();
        }

        thread = new Thread(this);
        thread.start();
        return true;
    }

    public boolean stop()
    {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            return false;
        }
        com.close();
        return true;
    }

    public boolean isRunning()
    {
        return running;
    }

    @Override
    public void run() {

        running =true;


        while(running)
        {
            JSONObject obj2 = new JSONObject();
            obj2.put("key","testlocation");

            JSONObject obj3 = new JSONObject();
            obj3.put("metadata", obj2);

            JSONArray jsonArray = new JSONArray();


            //collect values
            for(Sensor s : app.getSensors())
            {
                try {
                    jsonArray.add(s.getData());
                } catch (SensorException e) {
                    e.printStackTrace();
                }
            }
            obj3.put("measurements",jsonArray);
            try {

                com.sendData(obj3.toString().getBytes("utf-8"));

            } catch (WeatherServerConnectionException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(app.getSensorPollInterval());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
