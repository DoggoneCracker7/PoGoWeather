package com.example.kbaran.pogoweather.open_weather_map;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.kbaran.pogoweather.MainActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class OpenWeatherMapService extends Service {

    public static final int START_IMMEDIATELY = 0;
    public static final int THREE_HOURS_IN_MILLISECONDS = 10800000;
    public static final int PERIOD = THREE_HOURS_IN_MILLISECONDS;

    private OpenWeatherMapFetcher openWeatherMapFetcher;

    public OpenWeatherMapService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTimerTask();
        //startTimerTask2();
      //  startTimerTask3();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        openWeatherMapFetcher = new OpenWeatherMapFetcher();
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
                      new FetchDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);

                    }
                });
            }
        };
        timer.scheduleAtFixedRate(fetchTimerTask, START_IMMEDIATELY, PERIOD);
    }
/*
    private void startTimerTask2() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask fetchTimerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //new FetchDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);
                        new AsyncTask<Void,Void,Void>(){

                            @Override
                            protected Void doInBackground(Void... params) {
                                String result;
                                try {
                                    result = openWeatherMapFetcher.getWeatherForCityByCityId(2840270);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(fetchTimerTask, 0, PERIOD);
    }

    private void startTimerTask3() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask fetchTimerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      //  new FetchDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);
                        AsyncTask test = new AsyncTask<Void,Void,Void>(){

                            @Override
                            protected Void doInBackground(Void... params) {
                                String result;
                                try {
                                    result = openWeatherMapFetcher.getWeatherForCityByCityId(3247901);
                                    Log.i(this.getClass().getName(),"Fetched data: " + result);
                                } catch (IOException e) {
                                    Log.e(MainActivity.class.getName(),"IO exception why fetching the data", e);
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        };
                        test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(fetchTimerTask, 0, PERIOD);
    }
*/
    private class FetchDataTask extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            String result = null;

            try {
                result = openWeatherMapFetcher.getWeatherForCityByCityId(2648110);
                Log.i(this.getClass().getName(),"Fetched data: " + result);
                result = openWeatherMapFetcher.getWheatherForCityByCityName("Greater London","GB");
                Log.i(this.getClass().getName(),"Fetched data: " + result);
                result = openWeatherMapFetcher.getWeatherForCityByCoordinates("-0.16667","51.5");
                Log.i(this.getClass().getName(),"Fetched data: " + result);

            } catch (IOException e) {
                Log.e(MainActivity.class.getName(),"IO exception why fetching the data", e);
                e.printStackTrace();
            }

            return null;
        }
    }

}
