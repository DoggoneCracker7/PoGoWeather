package com.example.kbaran.pogoweather;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kbaran.pogoweather.City.City;
import com.example.kbaran.pogoweather.Location.Location;
import com.example.kbaran.pogoweather.LocationWeather.LocationWeatherKey;
import com.example.kbaran.pogoweather.Weather.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DataManager dm;
    List<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        SQLiteOpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getWritableDatabase();

        dm = new DataManagerImpl(this);

        locations = dm.getAllLocations();

        ListView list = (ListView) findViewById(R.id.listView1);

        ArrayAdapter<String> listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, locations);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Toast.makeText(MainActivity.this, String.valueOf(locations.get(pos).getId()), Toast.LENGTH_SHORT).show();
            }
        });
        list.setAdapter(listAdapter);
*/
    }
}
