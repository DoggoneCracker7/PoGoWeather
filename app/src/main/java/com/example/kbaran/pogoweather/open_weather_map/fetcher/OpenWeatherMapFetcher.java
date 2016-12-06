package com.example.kbaran.pogoweather.open_weather_map.fetcher;

import org.json.JSONObject;

/**
 * Created by kbaran on 06.12.16.
 */

public interface OpenWeatherMapFetcher {

    public JSONObject getWeatherByLocationId(long cityId);
    public JSONObject getWeatherByLocationName(String locationName, String cityCode);
    public JSONObject getWeatherByLocationCoordinates(double latitude, double longitude);

}
