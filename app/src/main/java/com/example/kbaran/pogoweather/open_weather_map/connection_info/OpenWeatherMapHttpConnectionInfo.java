package com.example.kbaran.pogoweather.open_weather_map.connection_info;

/**
 * Created by kbaran on 06.12.16.
 */

public interface OpenWeatherMapHttpConnectionInfo {
    public String getApiKey();
    public String getEndpoint();
    public String getLocationNameQueryParam();
    public String getLocationIdQueryParam();
    public String getLocationLatQueryParam();
    public String getLocationLonQueryParam();
    public String getAppidParam();
}
