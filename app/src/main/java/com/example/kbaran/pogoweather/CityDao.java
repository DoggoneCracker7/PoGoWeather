package com.example.kbaran.pogoweather;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import com.example.kbaran.pogoweather.CityTable.CityColumns;

import java.util.ArrayList;
import java.util.List;


public class CityDao implements Dao<City> {
    private static final String INSERT =
            "insert into " + CityTable.TABLE_NAME
                    + "(" + CityColumns.CITY_ID + ")"+
                    "values (?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    public CityDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(CityDao.INSERT);
    }
    @Override
    public long save(City entity) {
        insertStatement.clearBindings();
        insertStatement.bindLong(1, entity.getCityId());
        return insertStatement.executeInsert();
    }
    public void update(City entity) {
        final ContentValues values = new ContentValues();
        values.put(CityColumns.CITY_ID, entity.getCityId());
        db.update(CityTable.TABLE_NAME, values,
                BaseColumns._ID + " = ?", new String[] {
                        String.valueOf(entity.getId()) });
    }
    @Override
    public void delete(City entity) {
        if (entity.getId() > 0) {
            db.delete(CityTable.TABLE_NAME,
                    BaseColumns._ID + " = ?", new String[]
                            { String.valueOf(entity.getId()) });
        }
    }
    @Override
    public City get(long id) {
        City city = null;
        Cursor c =
                db.query(CityTable.TABLE_NAME,
                        new String[] { BaseColumns._ID, CityColumns.CITY_ID },
                        BaseColumns._ID + " = ?", new String[] { String.valueOf(id) },
                        null, null, null, "1");
        if (c.moveToFirst()) {
            city = this.buildCityFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return city;
    }
    @Override
    public List<City> getAll() {
        List<City> list = new ArrayList<City>();
        Cursor c =
                db.query(CityTable.TABLE_NAME,
                        new String[] { BaseColumns._ID, CityColumns.CITY_ID },
                        null, null, null, null, CityColumns.CITY_ID, null);
        if (c.moveToFirst()) {
            do {
                City city = this.buildCityFromCursor(c);
                if (city != null) {
                    list.add(city);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }
    private City buildCityFromCursor(Cursor c) {
        City city = null;
        if (c != null) {
            city = new City();
            city.setId(c.getLong(0));
            city.setCityId(c.getLong(1));
        }
        return city;
    }
}
