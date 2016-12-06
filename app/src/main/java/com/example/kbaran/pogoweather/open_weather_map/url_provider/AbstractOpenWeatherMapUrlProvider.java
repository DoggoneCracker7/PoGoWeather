package com.example.kbaran.pogoweather.open_weather_map.url_provider;

/**
 * Created by kbaran on 06.12.16.
 */

public abstract class AbstractOpenWeatherMapUrlProvider implements OpenWeatherMapUrlProvider {




    @Override
    public String getUrlForQueryByLocationId(long cityId) {
        return null;
    }

    @Override
    public String getUrlForQueryByLocationCoordinates(String locationName, String countryCode) {
        return null;
    }

    @Override
    public String getUrlForQueryByLocationCoordinates(double latitude, double longitude) {
        return null;
    }
}
