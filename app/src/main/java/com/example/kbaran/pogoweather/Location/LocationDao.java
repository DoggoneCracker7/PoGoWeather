package com.example.kbaran.pogoweather.Location;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.kbaran.pogoweather.Dao;
import com.example.kbaran.pogoweather.Location.LocationTable.LocationColumns;

import java.util.ArrayList;
import java.util.List;


public class LocationDao implements Dao<Location> {
    private static final String INSERT =
            "insert into " + LocationTable.TABLE_NAME
                    + "(" + LocationColumns.CITY_NAME + ", " + LocationColumns.CITY_COUNTRY
                    + ", " + LocationColumns.CITY_COORD_LATITUDE + ", " + LocationColumns.CITY_COORD_LONGITUDE
                    + ")"+
                    "values (?, ?, ?, ?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    public LocationDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(LocationDao.INSERT);
    }
    @Override
    public long save(Location entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getCityName());
        insertStatement.bindString(2, entity.getCityCountry());
        insertStatement.bindDouble(3, entity.getCityCoordLatitude());
        insertStatement.bindDouble(4, entity.getCityCoordLongitude());
        return insertStatement.executeInsert();
    }
    public void update(Location entity) {
        final ContentValues values = new ContentValues();
        values.put(LocationColumns.CITY_NAME, entity.getCityName());
        values.put(LocationColumns.CITY_COUNTRY, entity.getCityCountry());
        values.put(LocationColumns.CITY_COORD_LATITUDE, entity.getCityCoordLatitude());
        values.put(LocationColumns.CITY_COORD_LONGITUDE, entity.getCityCoordLongitude());
        db.update(LocationTable.TABLE_NAME, values,
                BaseColumns._ID + " = ?", new String[] {
                        String.valueOf(entity.getId()) });
    }
    @Override
    public void delete(Location entity) {
        if (entity.getId() > 0) {
            db.delete(LocationTable.TABLE_NAME,
                    BaseColumns._ID + " = ?", new String[]
                            { String.valueOf(entity.getId()) });
        }
    }
    @Override
    public Location get(long id) {
        Location location = null;
        Cursor c =
                db.query(LocationTable.TABLE_NAME,
                        new String[] {
                                BaseColumns._ID, LocationColumns.CITY_NAME, LocationColumns.CITY_COUNTRY,
                                LocationColumns.CITY_COORD_LATITUDE, LocationColumns.CITY_COORD_LONGITUDE },
                        BaseColumns._ID + " = ?", new String[] { String.valueOf(id) },
                        null, null, null, "1");
        if (c.moveToFirst()) {
            location = this.buildLocationFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return location;
    }
    @Override
    public List<Location> getAll() {
        List<Location> list = new ArrayList<Location>();
        Cursor c =
                db.query(LocationTable.TABLE_NAME,
                        new String[] {
                                BaseColumns._ID, LocationColumns.CITY_NAME, LocationColumns.CITY_COUNTRY,
                                LocationColumns.CITY_COORD_LATITUDE, LocationColumns.CITY_COORD_LONGITUDE },
                        null, null, null, null, LocationColumns.CITY_NAME, null);
        if (c.moveToFirst()) {
            do {
                Location location = this.buildLocationFromCursor(c);
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
    private Location buildLocationFromCursor(Cursor c) {
        Location location = null;
        if (c != null) {
            location = new Location.Builder().cityName(c.getString(1)).cityCountry(c.getString(2)).cityCoordLatitude(Float.parseFloat(c.getString(3))).cityCoordLongitude(Float.parseFloat(c.getString(4))).build();
            location.setId(c.getLong(0));
        }
        return location;
    }
}
