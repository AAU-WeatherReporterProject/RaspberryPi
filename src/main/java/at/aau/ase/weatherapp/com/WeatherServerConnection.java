package at.aau.ase.weatherapp.com;

public interface WeatherServerConnection {
    public boolean open() throws WeatherServerConnectionException;
    public boolean sendData(String data) throws WeatherServerConnectionException;
    public boolean sendData(byte[] data) throws WeatherServerConnectionException;
    public boolean close();
}
