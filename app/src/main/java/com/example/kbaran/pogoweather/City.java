package com.example.kbaran.pogoweather;


public class City extends ModelBase {
    private long cityId;

    public City(){}

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }
}
