package com.example.kbaran.pogoweather.open_weather_map.connection_info;

/**
 * Created by kbaran on 06.12.16.
 */

public class OpenWeatherMapHttpConnectionInfoImpl implements OpenWeatherMapHttpConnectionInfo {

    public final String API_KEY ="2f6b8247e9ed735fcd75f68aaaa67e4b";
    public final String ENDPOINT = "http://api.openweathermap.org/data/2.5/forecast";
    public final String CITY_NAME_QUERY_PARAM = "q";
    public final String CITY_ID_QUERY_PARAM = "id";
    public final String CITY_LAT_QUERY_PARAM = "lat";
    public final String CITY_LON_QUERY_PARAM = "lon";
    public final String APPID_PARAM = "APPID";

    @Override
    public String getApiKey() {
        return API_KEY;
    }

    @Override
    public String getEndpoint() {
        return ENDPOINT;
    }

    @Override
    public String getLocationNameQueryParam() {
        return CITY_NAME_QUERY_PARAM;
    }

    @Override
    public String getLocationIdQueryParam() {
        return CITY_ID_QUERY_PARAM;
    }

    @Override
    public String getLocationLatQueryParam() {
        return CITY_LAT_QUERY_PARAM;
    }

    @Override
    public String getLocationLonQueryParam() {
        return CITY_LON_QUERY_PARAM;
    }

    @Override
    public String getAppidParam() {
        return APPID_PARAM;
    }

}
