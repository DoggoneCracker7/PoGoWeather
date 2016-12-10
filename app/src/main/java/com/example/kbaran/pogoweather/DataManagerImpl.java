package com.example.kbaran.pogoweather;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kbaran.pogoweather.City.City;
import com.example.kbaran.pogoweather.City.CityDao;
import com.example.kbaran.pogoweather.Location.Location;
import com.example.kbaran.pogoweather.Location.LocationDao;
import com.example.kbaran.pogoweather.LocationWeather.LocationWeatherDao;
import com.example.kbaran.pogoweather.LocationWeather.LocationWeatherKey;
import com.example.kbaran.pogoweather.Weather.Weather;
import com.example.kbaran.pogoweather.Weather.WeatherDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataManagerImpl implements DataManager {
    private Context context;
    private SQLiteDatabase db;
    private WeatherDao weatherDao;
    private LocationDao locationDao;
    private CityDao cityDao;
    private static final String LOG_TAG = "LocationWeather";
    private LocationWeatherDao locationWeatherDao;

    public DataManagerImpl(Context context) {
        this.context = context;
        SQLiteOpenHelper openHelper =
                new OpenHelper(this.context);
        db = openHelper.getWritableDatabase();
        weatherDao = new WeatherDao(db);
        locationDao = new LocationDao(db);
        locationWeatherDao = new LocationWeatherDao(db);
        cityDao = new CityDao(db);
    }

    public Location getLocation(long locationId) {
        Location location = locationDao.get(locationId);
        if (location != null) {
            Set<Weather> w = new HashSet<Weather>(locationWeatherDao.getWeathers(location.getId()));
            location.setWeather(w);
        }
        return location;
    }

    public List<Location> getAllLocations() {
        return locationDao.getAll();
    }

    public long updateLocation(Location location) {
        try {
            db.beginTransaction();
            locationDao.update(location);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(LOG_TAG,
                    "Błąd przy zapisie lokalizacji (transakcję anulowano)", e);
        } finally {
            db.endTransaction();
        }
        return location.getId();
    }

    public long saveLocation(Location location) {
        long locationId = 0L;
        try {
            db.beginTransaction();
            locationId = locationDao.save(location);
            if (location.getWeather() != null && location.getWeather().size() > 0) {
                for (Weather c : location.getWeather()) {
                    long weatherId = 0L;
                    Weather dbWeather = weatherDao.get(c.getId());
                    if (dbWeather == null) {
                        weatherId = weatherDao.save(c);
                    } else {
                        weatherId = dbWeather.getId();
                    }
                    LocationWeatherKey mcKey =
                            new LocationWeatherKey.Builder().locationId(locationId).weatherId(weatherId).build();
                    if (!locationWeatherDao.exists(mcKey)) {
                        locationWeatherDao.save(mcKey);
                    }
                }
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(LOG_TAG,
                    "Błąd przy zapisie lokalizacji (transakcję anulowano)", e);
            locationId = 0L;
        } finally {
            db.endTransaction();
        }
        return locationId;
    }

    public boolean deleteLocation(long locationId) {
        boolean result = false;
        try {
            db.beginTransaction();
            Location location = getLocation(locationId);
            if (location != null) {
                if(location.getWeather() != null) {
                    for (Weather c : location.getWeather()) {
                        locationWeatherDao.delete(
                                new LocationWeatherKey.Builder().locationId(location.getId()).weatherId(c.getId()).build());
                    }
                }
                locationDao.delete(location);
            }
            db.setTransactionSuccessful();
            result = true;
        } catch (SQLException e) {
            Log.e(LOG_TAG,
                    "Błąd przy usuwaniu lokalizacji (transakcję anulowano)", e);
        } finally {
            db.endTransaction();
        }
        return result;
    }

    public Weather getWeather(long weatherId) {
        return weatherDao.get(weatherId);
    }

    public List<Weather> getAllWeathers() {
        return weatherDao.getAll();
    }

    public long saveWeather(Weather weather) {
        return weatherDao.save(weather);
    }

    public void deleteWeather(Weather weather) {
        weatherDao.delete(weather);
    }

    public long saveLocationWeather(LocationWeatherKey locationWeather) {
        return locationWeatherDao.save(locationWeather);
    }

    public List<Weather> getLocationWeathers(long id) {
        return locationWeatherDao.getWeathers(id);
    }

    public City getCity(long cityId) {
        return cityDao.get(cityId);
    }

    public List<City> getAllCities() {
        return cityDao.getAll();
    }

    public long saveCity(City city) {
        return cityDao.save(city);
    }

    public void deleteCity(City city) {
        cityDao.delete(city);
    }

    public long updateCity(City city) {
        try {
            db.beginTransaction();
            cityDao.update(city);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(LOG_TAG,
                    "Błąd przy zapisie miasta (transakcję anulowano)", e);
        } finally {
            db.endTransaction();
        }
        return city.getId();
    }
}
