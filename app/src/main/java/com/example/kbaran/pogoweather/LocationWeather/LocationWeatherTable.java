package com.example.kbaran.pogoweather.LocationWeather;


import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.kbaran.pogoweather.Location.LocationTable;
import com.example.kbaran.pogoweather.Weather.WeatherTable;

public final class LocationWeatherTable {
    public static final String TABLE_NAME = "location_weather";
    public static class LocationWeatherColumns {
        public static final String LOCATION_ID = "location_id";
        public static final String WEATHER_ID = "weather_id";
        public static final String DATE = "date";
    }
    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + LocationWeatherTable.TABLE_NAME + " (");
        ;
        sb.append(LocationWeatherColumns.LOCATION_ID + " INTEGER NOT NULL, ");
        sb.append(LocationWeatherColumns.WEATHER_ID + " INTEGER NOT NULL, ");
        sb.append(LocationWeatherColumns.DATE + " TEXT DEFAULT CURRENT_TIMESTAMP, ");
        sb.append("FOREIGN KEY(" + LocationWeatherColumns.LOCATION_ID + ")" +
                " REFERENCES " + LocationTable.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append("FOREIGN KEY(" +
                LocationWeatherColumns.WEATHER_ID + ")"+
                "REFERENCES " + WeatherTable.TABLE_NAME + "("
                + BaseColumns._ID + ") , ");
        sb.append("PRIMARY KEY ( " + LocationWeatherColumns.LOCATION_ID + ", "
                + LocationWeatherColumns.WEATHER_ID + ")");
        sb.append(");");
        db.execSQL(sb.toString());
    }
    public static void onUpgrade(SQLiteDatabase db, int oldVersion,
                                 int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LocationWeatherTable.TABLE_NAME);
        LocationWeatherTable.onCreate(db);
    }
}
