package com.example.kbaran.pogoweather.City;


import com.example.kbaran.pogoweather.ModelBase;

public class City extends ModelBase {
    private String cityName;
    private String country;

    private City(Builder builder) {
        setCityName(builder.cityName);
        setCountry(builder.country);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public static final class Builder {
        private String cityName;
        private String country;

        public Builder() {
        }

        public Builder cityName(String val) {
            cityName = val;
            return this;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public City build() {
            return new City(this);
        }
    }
}
