package com.example.kbaran.pogoweather.Weather;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.kbaran.pogoweather.Dao;
import com.example.kbaran.pogoweather.Weather.WeatherTable.WeatherColumns;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class WeatherDao implements Dao<Weather> {
    private static final String INSERT =
            "insert into " + WeatherTable.TABLE_NAME
                    + "(" + WeatherColumns.TEMP + ", " + WeatherColumns.TEMP_MIN + ", " + WeatherColumns.TEMP_MAX
                    + ", " + WeatherColumns.CLOUDS + ", " + WeatherColumns.RAIN + ", " + WeatherColumns.SNOW
                    + ", " + WeatherColumns.WIND + ", " + WeatherColumns.PRESSURE + ", " + WeatherColumns.HUMIDITY
                    + ", " + WeatherColumns.CALCTIME
                    + ")"+
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    public WeatherDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(WeatherDao.INSERT);
    }
    @Override
    public long save(Weather entity) {
        insertStatement.clearBindings();
        insertStatement.bindDouble(1, entity.getTemp());
        insertStatement.bindDouble(2, entity.getTempMin());
        insertStatement.bindDouble(3, entity.getTempMax());
        insertStatement.bindDouble(4, entity.getClouds());
        insertStatement.bindLong(5, entity.getRain());
        insertStatement.bindLong(6, entity.getSnow());
        insertStatement.bindLong(7, entity.getWind());
        insertStatement.bindLong(8, entity.getPressure());
        insertStatement.bindDouble(9, entity.getHumidity());
        try{
            insertStatement.bindString(10, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",java.util.Locale.getDefault()).format(entity.getCalcTime()));
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        return insertStatement.executeInsert();
    }
    public void update(Weather entity) {
        final ContentValues values = new ContentValues();
        values.put(WeatherColumns.TEMP, entity.getTemp());
        values.put(WeatherColumns.TEMP_MIN, entity.getTempMin());
        values.put(WeatherColumns.TEMP_MAX, entity.getTempMax());
        values.put(WeatherColumns.CLOUDS, entity.getClouds());
        values.put(WeatherColumns.RAIN, entity.getRain());
        values.put(WeatherColumns.SNOW, entity.getSnow());
        values.put(WeatherColumns.WIND, entity.getWind());
        values.put(WeatherColumns.PRESSURE, entity.getPressure());
        values.put(WeatherColumns.HUMIDITY, entity.getHumidity());
        values.put(WeatherColumns.CALCTIME, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(entity.getCalcTime()));
        db.update(WeatherTable.TABLE_NAME, values,
                BaseColumns._ID + " = ?", new String[] {
                        String.valueOf(entity.getId()) });
    }
    @Override
    public void delete(Weather entity) {
        if (entity.getId() > 0) {
            db.delete(WeatherTable.TABLE_NAME,
                    BaseColumns._ID + " = ?", new String[]
                            { String.valueOf(entity.getId()) });
        }
    }
    @Override
    public Weather get(long id) {
        Weather weather = null;
        Cursor c =
                db.query(WeatherTable.TABLE_NAME,
                        new String[] {
                                BaseColumns._ID, WeatherColumns.TEMP, WeatherColumns.TEMP_MIN, WeatherColumns.TEMP_MAX,
                                WeatherColumns.CLOUDS, WeatherColumns.RAIN, WeatherColumns.SNOW,
                                WeatherColumns.WIND, WeatherColumns.PRESSURE, WeatherColumns.HUMIDITY,
                                WeatherColumns.CALCTIME,
                        },
                        BaseColumns._ID + " = ?", new String[] { String.valueOf(id) },
                        null, null, null, "1");
        if (c.moveToFirst()) {
            weather = this.buildWeatherFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return weather;
    }
    @Override
    public List<Weather> getAll() {
        List<Weather> list = new ArrayList<Weather>();
        Cursor c =
                db.query(WeatherTable.TABLE_NAME,
                        new String[] {
                                BaseColumns._ID, WeatherColumns.TEMP, WeatherColumns.TEMP_MIN, WeatherColumns.TEMP_MAX,
                                WeatherColumns.CLOUDS, WeatherColumns.RAIN, WeatherColumns.SNOW,
                                WeatherColumns.WIND, WeatherColumns.PRESSURE, WeatherColumns.HUMIDITY,
                                WeatherColumns.CALCTIME,
                        },
                        null, null, null, null, BaseColumns._ID, null);
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
            try {
                weather = new Weather.Builder().temp(Float.parseFloat(c.getString(1))).tempMin(Float.parseFloat(c.getString(2))).tempMax(Float.parseFloat(c.getString(3)))
                        .clouds(Float.parseFloat(c.getString(4))).rain(c.getInt(5)).snow(c.getInt(6)).wind(c.getInt(7)).pressure(c.getInt(8)).humidity(Float.parseFloat(c.getString(9)))
                        .calcTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).parse(c.getString(10))).build();
                weather.setId(c.getLong(0));
            } catch (java.text.ParseException e){
                e.printStackTrace();
            }
        }
        return weather;
    }
}
