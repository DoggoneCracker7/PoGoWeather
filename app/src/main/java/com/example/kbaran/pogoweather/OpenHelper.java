package com.example.kbaran.pogoweather;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kbaran.pogoweather.City.City;
import com.example.kbaran.pogoweather.City.CityDao;
import com.example.kbaran.pogoweather.City.CityTable;
import com.example.kbaran.pogoweather.Location.Location;
import com.example.kbaran.pogoweather.Location.LocationDao;
import com.example.kbaran.pogoweather.Location.LocationTable;
import com.example.kbaran.pogoweather.LocationWeather.LocationWeatherDao;
import com.example.kbaran.pogoweather.LocationWeather.LocationWeatherKey;
import com.example.kbaran.pogoweather.LocationWeather.LocationWeatherTable;
import com.example.kbaran.pogoweather.Weather.Weather;
import com.example.kbaran.pogoweather.Weather.WeatherDao;
import com.example.kbaran.pogoweather.Weather.WeatherTable;

import java.util.Date;

public class OpenHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "baza.db";
    private static final int DATABASE_VERSION = 1;

    OpenHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onOpen(final SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(final SQLiteDatabase db) {
        LocationTable.onCreate(db);
        WeatherTable.onCreate(db);
        LocationWeatherTable.onCreate(db);
        CityTable.onCreate(db);
    }
    @Override
    public void onUpgrade(final SQLiteDatabase db,
                          final int oldVersion, final int newVersion) {
        LocationWeatherTable.onUpgrade(db, oldVersion, newVersion);
        LocationTable.onUpgrade(db, oldVersion, newVersion);
        WeatherTable.onUpgrade(db, oldVersion, newVersion);
        CityTable.onUpgrade(db, oldVersion, newVersion);
    }
}