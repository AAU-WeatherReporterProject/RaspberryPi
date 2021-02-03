package at.aau.ase.weatherapp.com;

import java.io.IOException;

public class WeatherServerConnectionException extends IOException {
    public WeatherServerConnectionException(String msg){
        super(msg);
    }
}
