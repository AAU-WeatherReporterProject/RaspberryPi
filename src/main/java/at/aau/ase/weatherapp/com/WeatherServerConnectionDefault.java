package at.aau.ase.weatherapp.com;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherServerConnectionDefault implements WeatherServerConnection{

    private String url = "http://192.168.178.21:8098/api/v1/ingest";

    private HttpURLConnection connection = null;
    private OutputStream os = null;

    public WeatherServerConnectionDefault(String url)
    {
        this.url = url;


    }


    @Override
    public boolean open() throws WeatherServerConnectionException {


        return true;
    }

    @Override
    public boolean sendData(String data) throws WeatherServerConnectionException {


        return false;
    }

    @Override
    public boolean sendData(byte[] data) throws WeatherServerConnectionException {
        boolean returnState = false;
        try {

            connection = (HttpURLConnection) new URL(url).openConnection();
            //connection.setRequestProperty("Content-Type", "application/json");
            //connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //connection.setRequestProperty("Accept", "application/json");
            //connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            os = connection.getOutputStream();

            //System.out.println("Sending data: " + new String(data));

            os.write(data);
            os.flush();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code = " + responseCode);
            if (responseCode == 200) {
                returnState = true;
                System.out.println("POST was successful.");
            } else if (responseCode == 401) {
                System.out.println("Wrong password.");
            }


            os.close();

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean close(){

        return true;
    }
}
