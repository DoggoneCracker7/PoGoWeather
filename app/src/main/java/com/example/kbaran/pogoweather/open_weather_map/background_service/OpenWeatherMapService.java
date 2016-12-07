package com.example.kbaran.pogoweather.open_weather_map.background_service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.kbaran.pogoweather.MainActivity;
import com.example.kbaran.pogoweather.http_connection.GenericHttpConnectionHandler;
import com.example.kbaran.pogoweather.open_weather_map.connection_info.OpenWeatherMapHttpConnectionInfoImpl;
import com.example.kbaran.pogoweather.open_weather_map.fetcher.OpenWeatherMapFetcherImpl;
import com.example.kbaran.pogoweather.open_weather_map.url_provider.OpenWeatherMapUrlProviderImpl;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class OpenWeatherMapService extends Service {

    public static final int START_IMMEDIATELY = 0;
    public static final int THREE_HOURS_IN_MILLISECONDS = 10800000;
    public static final int PERIOD = THREE_HOURS_IN_MILLISECONDS;

    private OpenWeatherMapFetcherImpl openWeatherMapFetcher;

    public OpenWeatherMapService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        openWeatherMapFetcher = new OpenWeatherMapFetcherImpl(
                new GenericHttpConnectionHandler(),
                new OpenWeatherMapUrlProviderImpl(
                        new OpenWeatherMapHttpConnectionInfoImpl()
                )
        );
        startTimerTask();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void startTimerTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask fetchTimerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      new FetchWeatherDataAsyncTask(openWeatherMapFetcher).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(fetchTimerTask, START_IMMEDIATELY, PERIOD);
    }

}
