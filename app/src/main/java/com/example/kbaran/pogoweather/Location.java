package com.example.kbaran.pogoweather;


import java.util.Set;

public class Location extends ModelBase {
    private String cityName;
    private float cityCoordLatitude;
    private float cityCoordLongitude;
    private String cityCountry;
    private Set<Weather> weather;

    public Location(){}

    public Location(String cityName, float cityCoordLatitude, float cityCoordLongitude, String cityCountry){
        this.cityName = cityName;
        this.cityCoordLatitude = cityCoordLatitude;
        this.cityCoordLongitude = cityCoordLongitude;
        this.cityCountry = cityCountry;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getCityCoordLatitude() {
        return cityCoordLatitude;
    }

    public void setCityCoordLatitude(float cityCoordLatitude) {
        this.cityCoordLatitude = cityCoordLatitude;
    }

    public float getCityCoordLongitude() {
        return cityCoordLongitude;
    }

    public void setCityCoordLongitude(float cityCoordLongitude) {
        this.cityCoordLongitude = cityCoordLongitude;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }

    public Set<Weather> getWeather() {
        return weather;
    }

    public void setWeather(Set<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return this.cityName;
    }
}
