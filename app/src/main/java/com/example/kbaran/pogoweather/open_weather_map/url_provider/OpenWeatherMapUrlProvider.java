package com.example.kbaran.pogoweather.open_weather_map.url_provider;

/**
 * Created by kbaran on 06.12.16.
 */

public interface OpenWeatherMapUrlProvider {

    public final String COMMA_ENCODING_VALUE = "%2C";

    public String getUrlForQueryByLocationId(long cityId);
    public String getUrlForQueryByLocationCoordinates(String locationName, String countryCode);
    public String getUrlForQueryByLocationCoordinates(double latitude, double longitude);

}
