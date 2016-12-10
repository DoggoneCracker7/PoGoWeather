package com.example.kbaran.pogoweather;


import com.example.kbaran.pogoweather.City.City;
import com.example.kbaran.pogoweather.Location.Location;
import com.example.kbaran.pogoweather.LocationWeather.LocationWeatherKey;
import com.example.kbaran.pogoweather.Weather.Weather;

import java.util.List;

public interface DataManager {
    public Location getLocation(long locationId);
    public List<Location> getAllLocations();
    public long saveLocation(Location location);
    public long updateLocation(Location location);
    public boolean deleteLocation(long locationId);
    public Weather getWeather(long weatherId);
    public List<Weather> getAllWeathers();
    public long saveWeather(Weather weather);
    public void deleteWeather(Weather weather);
    public List<Weather> getLocationWeathers(long id);
    public long saveLocationWeather(LocationWeatherKey locationGroup);
    public City getCity(long cityId);
    public List<City> getAllCities();
    public long saveCity(City city);
    public long updateCity(City city);
    public void deleteCity(City cityId);
}
