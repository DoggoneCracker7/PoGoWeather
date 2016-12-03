package com.example.kbaran.pogoweather.open_weather_map;

import android.net.Uri;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by kbaran on 03.12.16.
 */

public class OpenWeatherMapFetcher {


    public static final String TAG = "OpenWeatherMapFetcher";
    public static final String API_KEY ="2f6b8247e9ed735fcd75f68aaaa67e4b";
    public static final String ENDPOINT = "http://api.openweathermap.org/data/2.5/forecast";
    public static final String CITY_NAME_QUERY_PARAM = "q";
    public static final String CITY_ID_QUERY_PARAM = "id";
    public static final String CITY_LAT_QUERY_PARAM = "lat";
    public static final String CITY_LON_QUERY_PARAM = "lon";
    public static final String APPID_PARAM = "APPID";
    public static final String COMMA_ENCODING_VALUE = "%2C";

    public OpenWeatherMapFetcher() {
    }

    public String getWeatherForCityByCityId(long cityId) throws IOException {
        return getUrl(prepareQueryByIdCity(cityId));
    }

    public String getWheatherForCityByCityName(String cityName, String code) throws IOException {
        return getUrl(prepareQueryByCityName(cityName,code));
    }

    public String getUrl(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            InputStream inputStream = connection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            if(buffer.length() == 0 ) {
                return "";
            }
            return buffer.toString();
        }
        finally {
            connection.disconnect();
        }
    }

    public String getWeatherForCityByCoordinates(String lon, String lat) throws IOException {
        return getUrl(prepareQueryByCityCoordinates(lon,lat));
    }

    private String prepareQueryByCityName(String cityName, String countryCode) {
        return Uri.parse(ENDPOINT)
                .buildUpon()
                .appendQueryParameter(CITY_NAME_QUERY_PARAM, cityName + COMMA_ENCODING_VALUE + countryCode)
                .appendQueryParameter(APPID_PARAM, API_KEY)
                .build()
                .toString();
    }

    private String prepareQueryByIdCity(long cityId) {
        return Uri.parse(ENDPOINT)
                .buildUpon()
                .appendQueryParameter(CITY_ID_QUERY_PARAM, Long.toString(cityId))
                .appendQueryParameter(APPID_PARAM, API_KEY)
                .build()
                .toString();
    }

    private String prepareQueryByCityCoordinates(String lat, String lon) {
        return Uri.parse(ENDPOINT)
                .buildUpon()
                .appendQueryParameter(CITY_LAT_QUERY_PARAM, lat)
                .appendQueryParameter(CITY_LON_QUERY_PARAM, lon)
                .appendQueryParameter(APPID_PARAM, API_KEY)
                .build()
                .toString();
    }
}
