package com.example.kbaran.pogoweather.LocationWeather;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.kbaran.pogoweather.LocationWeather.LocationWeatherTable.LocationWeatherColumns;
import com.example.kbaran.pogoweather.Weather.Weather;
import com.example.kbaran.pogoweather.Weather.WeatherTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LocationWeatherDao {
    private static final String INSERT =
            "insert into " + LocationWeatherTable.TABLE_NAME
                    + "(" + LocationWeatherColumns.LOCATION_ID + ", " + LocationWeatherColumns.WEATHER_ID + ")"+
                    "values (?, ?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    public LocationWeatherDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(LocationWeatherDao.INSERT);
    }

    public List<Weather> getWeathers(long id) {
        List<Weather> list = new ArrayList<Weather>();
        Cursor c = db.query(LocationWeatherTable.TABLE_NAME, new String[] {LocationWeatherTable.LocationWeatherColumns.WEATHER_ID},
                "location_id = ?", new String[]{  String.valueOf(id) }, null, null, LocationWeatherTable.LocationWeatherColumns.WEATHER_ID, null);

        if (c.moveToFirst()) {
            do {
                Weather weather = this.buildWeatherFromCursor(c);
                if (weather != null) {
                    list.add(weather);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }
    private Weather buildWeatherFromCursor(Cursor c) {
        Weather weather = null;
        if (c != null) {
            Cursor c1 =
                    db.query(WeatherTable.TABLE_NAME,
                                    new String[] {
                                            BaseColumns._ID, WeatherTable.WeatherColumns.TEMP, WeatherTable.WeatherColumns.TEMP_MIN, WeatherTable.WeatherColumns.TEMP_MAX,
                                            WeatherTable.WeatherColumns.CLOUDS, WeatherTable.WeatherColumns.RAIN, WeatherTable.WeatherColumns.SNOW,
                                            WeatherTable.WeatherColumns.WIND, WeatherTable.WeatherColumns.PRESSURE, WeatherTable.WeatherColumns.HUMIDITY,
                                            WeatherTable.WeatherColumns.CALCTIME,
                                    },
                            "_id = ?", new String[]{  String.valueOf(c.getLong(0)) }, null, null, null, "1");
            if (c1.getCount() != 0) {
                if (c1.moveToFirst()) {
                    try {
                        weather = new Weather.Builder().temp(Float.parseFloat(c1.getString(1))).tempMin(Float.parseFloat(c1.getString(2))).tempMax(Float.parseFloat(c1.getString(3)))
                                .clouds(Float.parseFloat(c1.getString(4))).rain(c1.getInt(5)).snow(c1.getInt(6)).wind(c1.getInt(7)).pressure(c1.getInt(8)).humidity(Float.parseFloat(c1.getString(9)))
                                .calcTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).parse(c1.getString(10))).build();
                        weather.setId(c1.getLong(0));
                    } catch (java.text.ParseException e){
                        e.printStackTrace();
                    }
                }
            }
            if (!c1.isClosed()) {
                c1.close();
            }
        }
        return weather;
    }

    public long save(LocationWeatherKey entity) {
        if(!exists(entity)) {
            insertStatement.clearBindings();
            insertStatement.bindLong(1, entity.getLocationId());
            insertStatement.bindLong(2, entity.getWeatherId());
            return insertStatement.executeInsert();
        }else{
            return 0;
        }
    }

    public boolean exists(LocationWeatherKey entity) {
        boolean result = false;

        Cursor c =
                db.query(LocationWeatherTable.TABLE_NAME, new String[] {LocationWeatherTable.LocationWeatherColumns.LOCATION_ID, LocationWeatherTable.LocationWeatherColumns.WEATHER_ID},
                        "weather_id = ? and location_id = ?", new String[]{  String.valueOf(entity.getWeatherId()), String.valueOf(entity.getLocationId()) }, null, null, null, null);
        if (c.getCount() != 0) {
            result = true;
        }
        if (!c.isClosed()) {
            c.close();
        }
        return result;
    }

    public void delete(LocationWeatherKey entity) {
        Cursor c =
                db.query(LocationWeatherTable.TABLE_NAME, new String[] {LocationWeatherTable.LocationWeatherColumns.LOCATION_ID, LocationWeatherTable.LocationWeatherColumns.WEATHER_ID},
                        "weather_id = ? and location_id = ?", new String[]{  String.valueOf(entity.getWeatherId()), String.valueOf(entity.getLocationId()) }, null, null, null, null);

        if (c.getCount() != 0) {
            if (c.moveToFirst()) {
                db.delete(LocationWeatherTable.TABLE_NAME,
                        "weather_id = ? and location_id = ?", new String[]{String.valueOf(entity.getWeatherId()), String.valueOf(entity.getLocationId())});
            }
        }
        if (!c.isClosed()) {
            c.close();
        }
    }
}
