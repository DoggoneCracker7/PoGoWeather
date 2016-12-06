package com.example.kbaran.pogoweather.http_connection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kbaran on 06.12.16.
 */

public class GenericHttpConnectionHandler implements HttpConnectionHandler {

    @Override
    public String connectAndReceiveData(String requestUrl) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(requestUrl);

            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            if (buffer.length() == 0) {
                return "";
            }
            return buffer.toString();
        } catch (MalformedURLException e) {
            Log.e(this.getClass().getName(),"MalformedURLException during URL creation",e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            Log.e(this.getClass().getName(),"IOException while reading from HttpURLConnection",e);
            throw new RuntimeException(e);
        }
        finally {
            connection.disconnect();
        }
    }
}
