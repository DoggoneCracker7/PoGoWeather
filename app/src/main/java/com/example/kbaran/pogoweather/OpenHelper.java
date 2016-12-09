package com.example.kbaran.pogoweather;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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