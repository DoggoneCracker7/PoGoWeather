package com.example.kbaran.pogoweather;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import com.example.kbaran.pogoweather.LocationWeatherTable.LocationWeatherColumns;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LocationWeatherDao {
    private static final String INSERT =
            "insert into " + LocationWeatherTable.TABLE_NAME
                    + "(" + LocationWeatherColumns.LOCATION_ID + ", " + LocationWeatherColumns.WEATHER_ID +")"+
                    "values (?, ?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    public LocationWeatherDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(LocationWeatherDao.INSERT);
    }

    public List<Weather> getWeathers(long id) {
        List<Weather> list = new ArrayList<Weather>();
        Cursor c = db.query(LocationWeatherTable.TABLE_NAME, new String[] {LocationWeatherTable.LocationWeatherColumns.LOCATION_ID},
                "location_id = ?", new String[]{  String.valueOf(id) }, null, null, LocationWeatherTable.LocationWeatherColumns.LOCATION_ID, null);

        if (c.moveToFirst()) {
            do {
                Weather group = this.buildWeatherFromCursor(c);
                if (group != null) {
                    list.add(group);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }
    private Weather buildWeatherFromCursor(Cursor c) {
        Weather location = null;
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

            if (c1.moveToFirst()) {
                System.out.println(c1.getLong(0));
                if (c1 != null) {
                    location = new Weather();
                    location.setId(c.getLong(0));
                    location.setTemp(Float.parseFloat(c.getString(1)));
                    location.setTempMin(Float.parseFloat(c.getString(2)));
                    location.setTempMax(Float.parseFloat(c.getString(3)));
                    location.setClouds(Float.parseFloat(c.getString(4)));
                    location.setRain(c.getInt(5));
                    location.setSnow(c.getInt(6));
                    location.setWind(c.getInt(7));
                    location.setPressure(c.getInt(8));
                    location.setHumidity(c.getLong(9));
                    try {
                        location.setCalcTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).parse(c.getString(10)));
                    } catch (java.text.ParseException e){
                        e.printStackTrace();
                    }
                }
            }
            if (!c1.isClosed()) {
                c1.close();
            }
        }
        return location;
    }

    public long save(LocationWeatherKey entity) {
        if(exists(entity)) {
            insertStatement.clearBindings();
            insertStatement.bindLong(1, entity.getLocationId());
            insertStatement.bindLong(2, entity.getWeatherId());
            return insertStatement.executeInsert();
        }
        return 0;
    }

    public boolean exists(LocationWeatherKey entity) {
        boolean result = false;

        Cursor c =
                db.query(LocationWeatherTable.TABLE_NAME, new String[] {LocationWeatherTable.LocationWeatherColumns.LOCATION_ID, LocationWeatherTable.LocationWeatherColumns.WEATHER_ID},
                        "weather_id = ? and location_id = ?", new String[]{  String.valueOf(entity.getWeatherId()), String.valueOf(entity.getLocationId()) }, null, null, null, null);

        if (c.moveToFirst()) {
            if (c != null) {
                result = true;
            }
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


        if (c.moveToFirst()) {
            if (c != null) {
                db.delete(LocationWeatherTable.TABLE_NAME,
                        "weather_id = ? and location_id = ?", new String[]{  String.valueOf(entity.getWeatherId()), String.valueOf(entity.getLocationId()) });
            }
        }
        if (!c.isClosed()) {
            c.close();
        }
    }
}
