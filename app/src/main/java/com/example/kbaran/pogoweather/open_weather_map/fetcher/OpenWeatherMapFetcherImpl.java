package com.example.kbaran.pogoweather.open_weather_map.fetcher;

import android.util.Log;

import com.example.kbaran.pogoweather.http_connection.HttpConnectionHandler;
import com.example.kbaran.pogoweather.open_weather_map.url_provider.OpenWeatherMapUrlProvider;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kbaran on 03.12.16.
 */

public class OpenWeatherMapFetcherImpl implements OpenWeatherMapFetcher {

    private HttpConnectionHandler httpConnectionHandler;
    private OpenWeatherMapUrlProvider urlProvider;

    public OpenWeatherMapFetcherImpl(HttpConnectionHandler httpConnectionHandler, OpenWeatherMapUrlProvider urlProvider) {
        this.httpConnectionHandler = httpConnectionHandler;
        this.urlProvider = urlProvider;
    }

    private JSONObject convertStringResponseToJSON(String response) {
        try {
            return new JSONObject(response);
        } catch (JSONException e) {
            Log.e(this.getClass().getName(),"JSONException while parsing response",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject getWeatherByLocationId(long cityId) {
        return convertStringResponseToJSON(
                httpConnectionHandler
                    .connectAndReceiveData(
                            urlProvider.getUrlForQueryByLocationId(cityId)
                    )
        );
    }

    @Override
    public JSONObject getWeatherByLocationName(String locationName, String cityCode) {
        return convertStringResponseToJSON(
                httpConnectionHandler
                        .connectAndReceiveData(
                                urlProvider.getUrlForQueryByLocationCoordinates(locationName,cityCode)
                        )
        );
    }

    @Override
    public JSONObject getWeatherByLocationCoordinates(double latitude, double longitude) {
        return convertStringResponseToJSON(
                httpConnectionHandler
                        .connectAndReceiveData(
                                urlProvider.getUrlForQueryByLocationCoordinates(latitude,longitude)
                        )
        );
    }
}
