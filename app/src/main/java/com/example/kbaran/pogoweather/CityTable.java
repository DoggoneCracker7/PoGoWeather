package com.example.kbaran.pogoweather;


import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class CityTable {
    public static final String TABLE_NAME = "city";
    public static class CityColumns implements BaseColumns {
        public static final String NAME = "city_id";
    }
    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + CityTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(CityColumns.NAME + " TEXT UNIQUE NOT NULL");
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
