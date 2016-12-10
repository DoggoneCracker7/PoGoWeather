package com.example.kbaran.pogoweather.City;


import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class CityTable {
    public static final String TABLE_NAME = "city";
    public static class CityColumns implements BaseColumns {
        public static final String CITY_NAME = "city_name";
        public static final String COUNTRY = "country";
    }
    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + CityTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(CityColumns.CITY_NAME + " TEXT, ");
        sb.append(CityColumns.COUNTRY + " TEXT");
        sb.append(");");
        db.execSQL(sb.toString());
    }
    public static void onUpgrade(SQLiteDatabase db,
                                 int oldVersion,
                                 int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "
                + CityTable.TABLE_NAME);
        CityTable.onCreate(db);
    }
}
