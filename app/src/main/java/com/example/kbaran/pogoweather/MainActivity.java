package com.example.kbaran.pogoweather;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        SQLiteOpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getWritableDatabase();

        DataManager dm = new DataManagerImpl(this);
        dm.saveLocation(new Location("Kraków", 12.34F, 56.78F, "PL"));
        dm.getAllLocations();

        List<Location> locations = dm.getAllLocations();
        ListView list = (ListView) findViewById(R.id.listView1);

        ArrayAdapter<String> listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, locations);

        list.setAdapter(listAdapter);
*/
    }
}
