package at.aau.ase.weatherapp;

import at.aau.ase.weatherapp.sensors.Sensor;
import at.aau.ase.weatherapp.sensors.SensorException;

public class WeatherAppWorker implements Runnable{


    private WeatherApp app = null;
    private Thread thread;
    private boolean running = false;

    public WeatherAppWorker(WeatherApp app)
    {
        this.app = app;
    }

    public boolean start()
    {
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
        return true;
    }

    public boolean isRunning()
    {
        return running;
    }

    @Override
    public void run() {
        System.out.println("ASDFASDFASDFASDF");
        running =true;
        while(running)
        {

            for(Sensor s : app.getSensors())
            {
                try {
                    s.getData();
                } catch (SensorException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(app.getSensorPollInterval());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
