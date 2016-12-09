package com.example.kbaran.pogoweather;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;


public final class WeatherTable {
    public static final String TABLE_NAME = "weather";
    public static class WeatherColumns implements BaseColumns {
        public static final String TEMP = "temp";
        public static final String TEMP_MIN = "temp_min";
        public static final String TEMP_MAX = "temp_max";
        public static final String CLOUDS = "clouds";
        public static final String RAIN = "rain";
        public static final String SNOW = "snow";
        public static final String WIND = "wind";
        public static final String PRESSURE = "pressure";
        public static final String HUMIDITY = "humidity";
        public static final String CALCTIME = "calctime";
    }
    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + WeatherTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(WeatherTable.WeatherColumns.TEMP + " REAL, ");
        sb.append(WeatherTable.WeatherColumns.TEMP_MIN + " REAL, ");
        sb.append(WeatherTable.WeatherColumns.TEMP_MAX + " REAL, ");
        sb.append(WeatherTable.WeatherColumns.CLOUDS + " REAL, ");
        sb.append(WeatherTable.WeatherColumns.RAIN + " INTEGER, ");
        sb.append(WeatherTable.WeatherColumns.SNOW + " INTEGER, ");
        sb.append(WeatherTable.WeatherColumns.WIND + " INTEGER, ");
        sb.append(WeatherTable.WeatherColumns.PRESSURE + " INTEGER, ");
        sb.append(WeatherTable.WeatherColumns.HUMIDITY + " REAL, ");
        sb.append(WeatherTable.WeatherColumns.CALCTIME + " TEXT");
        sb.append(");");
        db.execSQL(sb.toString());
    }
    public static void onUpgrade(SQLiteDatabase db,
                                 int oldVersion,
                                 int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "
                + WeatherTable.TABLE_NAME);
        WeatherTable.onCreate(db);
    }
}
