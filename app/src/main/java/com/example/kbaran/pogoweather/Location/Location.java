package com.example.kbaran.pogoweather.Location;


import com.example.kbaran.pogoweather.ModelBase;
import com.example.kbaran.pogoweather.Weather.Weather;

import java.util.Set;

public class Location extends ModelBase {
    private String cityName;
    private float cityCoordLatitude;
    private float cityCoordLongitude;
    private String cityCountry;
    private Set<Weather> weather;

    private Location(Builder builder) {
        setCityName(builder.cityName);
        setCityCoordLatitude(builder.cityCoordLatitude);
        setCityCoordLongitude(builder.cityCoordLongitude);
        setCityCountry(builder.cityCountry);
        setWeather(builder.weather);
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

    public static final class Builder {
        private String cityName;
        private float cityCoordLatitude;
        private float cityCoordLongitude;
        private String cityCountry;
        private Set<Weather> weather;

        public Builder() {
        }

        public Builder cityName(String val) {
            cityName = val;
            return this;
        }

        public Builder cityCoordLatitude(float val) {
            cityCoordLatitude = val;
            return this;
        }

        public Builder cityCoordLongitude(float val) {
            cityCoordLongitude = val;
            return this;
        }

        public Builder cityCountry(String val) {
            cityCountry = val;
            return this;
        }

        public Builder weather(Set<Weather> val) {
            weather = val;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}
