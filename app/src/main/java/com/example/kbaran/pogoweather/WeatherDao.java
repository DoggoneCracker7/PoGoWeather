package com.example.kbaran.pogoweather;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import com.example.kbaran.pogoweather.WeatherTable.WeatherColumns;

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
        insertStatement.bindString(10, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",java.util.Locale.getDefault()).format(entity.getCalcTime()));
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
        Weather location = null;
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
            location = this.buildWeatherFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return location;
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
                Weather location = this.buildWeatherFromCursor(c);
                if (location != null) {
                    list.add(location);
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
        return location;
    }
}
