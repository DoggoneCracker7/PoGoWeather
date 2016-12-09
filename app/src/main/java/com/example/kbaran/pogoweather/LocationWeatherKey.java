package com.example.kbaran.pogoweather;


public class LocationWeatherKey {
    private long locationId;
    private long weatherId;

    public LocationWeatherKey(){}

    public LocationWeatherKey(long locationId, long weatherId){
        this.setLocationId(locationId);
        this.setWeatherId(weatherId);
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(long weatherId) {
        this.weatherId = weatherId;
    }
}
