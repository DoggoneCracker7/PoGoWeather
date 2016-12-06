package com.example.kbaran.pogoweather.open_weather_map.url_provider;

import android.net.Uri;

import com.example.kbaran.pogoweather.open_weather_map.connection_info.OpenWeatherMapHttpConnectionInfo;

/**
 * Created by kbaran on 06.12.16.
 */

public class OpenWeatherMapUrlProviderImpl extends AbstractOpenWeatherMapUrlProvider {

    private OpenWeatherMapHttpConnectionInfo openWeatherMapHttpConnectionInfo;

    public OpenWeatherMapUrlProviderImpl(OpenWeatherMapHttpConnectionInfo openWeatherMapHttpConnectionInfo) {
        this.openWeatherMapHttpConnectionInfo = openWeatherMapHttpConnectionInfo;
    }

    @Override
    public String getUrlForQueryByLocationId(long cityId) {
        return Uri.parse(openWeatherMapHttpConnectionInfo.getEndpoint())
                .buildUpon()
                .appendQueryParameter(openWeatherMapHttpConnectionInfo.getLocationIdQueryParam(),Double.toString(cityId))
                .appendQueryParameter(openWeatherMapHttpConnectionInfo.getAppidParam(), openWeatherMapHttpConnectionInfo.getApiKey())
                .build()
                .toString();
    }

    @Override
    public String getUrlForQueryByLocationCoordinates(String locationName, String countryCode) {
        return Uri.parse(openWeatherMapHttpConnectionInfo.getEndpoint())
                .buildUpon()
                .appendQueryParameter(openWeatherMapHttpConnectionInfo.getLocationNameQueryParam(),locationName + COMMA_ENCODING_VALUE + countryCode)
                .appendQueryParameter(openWeatherMapHttpConnectionInfo.getAppidParam(), openWeatherMapHttpConnectionInfo.getApiKey())
                .build()
                .toString();
    }

    @Override
    public String getUrlForQueryByLocationCoordinates(double latitude, double longitude) {
        return Uri.parse(openWeatherMapHttpConnectionInfo.getEndpoint())
                .buildUpon()
                .appendQueryParameter(openWeatherMapHttpConnectionInfo.getLocationLatQueryParam(), Double.toString(latitude))
                .appendQueryParameter(openWeatherMapHttpConnectionInfo.getLocationLonQueryParam(), Double.toString(longitude))
                .appendQueryParameter(openWeatherMapHttpConnectionInfo.getAppidParam(), openWeatherMapHttpConnectionInfo.getApiKey())
                .build()
                .toString();
    }
}
