package com.example.kbaran.pogoweather;


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
    public long saveLocationWeather(LocationWeatherKey locationGroup);
    public List<Weather> getLocationWeathers(long id);
    public City getCity(long cityId);
    public List<City> getAllCities();
    public long saveCity(City city);
    public long updateCity(City city);
    public void deleteCity(City cityId);
}
