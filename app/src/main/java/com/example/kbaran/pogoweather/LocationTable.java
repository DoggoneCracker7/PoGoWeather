package com.example.kbaran.pogoweather;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;


public final class LocationTable {
    public static final String TABLE_NAME = "location";
    public static class LocationColumns implements BaseColumns {
        public static final String CITY_NAME = "city_name";
        public static final String CITY_COUNTRY = "city_country";
        public static final String CITY_COORD_LATITUDE = "city_coord_latitude";
        public static final String CITY_COORD_LONGITUDE = "city_coord_longitude";
    }
    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + LocationTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(LocationColumns.CITY_NAME + " TEXT, ");
        sb.append(LocationColumns.CITY_COUNTRY + " TEXT, ");
        sb.append(LocationColumns.CITY_COORD_LATITUDE + " REAL, ");
        sb.append(LocationColumns.CITY_COORD_LONGITUDE + " REAL");
        sb.append(");");
        db.execSQL(sb.toString());
    }
    public static void onUpgrade(SQLiteDatabase db,
                                 int oldVersion,
                                 int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "
                + LocationTable.TABLE_NAME);
        LocationTable.onCreate(db);
    }
}
