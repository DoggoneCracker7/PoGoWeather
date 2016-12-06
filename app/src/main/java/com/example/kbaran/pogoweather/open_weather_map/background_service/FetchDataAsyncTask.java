package com.example.kbaran.pogoweather.open_weather_map.background_service;

import android.os.AsyncTask;
import android.util.Log;

import com.example.kbaran.pogoweather.MainActivity;
import com.example.kbaran.pogoweather.open_weather_map.fetcher.OpenWeatherMapFetcher;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by kbaran on 07.12.16.
 */

public class FetchDataAsyncTask extends AsyncTask<Void,Void,Void> {

    private OpenWeatherMapFetcher openWeatherMapFetcher;

    public FetchDataAsyncTask(OpenWeatherMapFetcher openWeatherMapFetcher) {
        this.openWeatherMapFetcher = openWeatherMapFetcher;
    }

    @Override
    protected Void doInBackground(Void... params) {
        JSONObject result = null;

            result = openWeatherMapFetcher.getWeatherByLocationId(2648110);
            Log.i(this.getClass().getName(),"Fetched data: " + result);
            result = openWeatherMapFetcher.getWeatherByLocationName("Greater London","GB");
            Log.i(this.getClass().getName(),"Fetched data: " + result);
            result = openWeatherMapFetcher.getWeatherByLocationCoordinates(-0.16667,51.5);
            Log.i(this.getClass().getName(),"Fetched data: " + result);

        return null;
    }
}
