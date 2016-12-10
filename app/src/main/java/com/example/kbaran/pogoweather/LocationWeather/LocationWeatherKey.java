package com.example.kbaran.pogoweather.LocationWeather;


public class LocationWeatherKey {
    private long locationId;
    private long weatherId;

    private LocationWeatherKey(Builder builder) {
        setLocationId(builder.locationId);
        setWeatherId(builder.weatherId);
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

    public static final class Builder {
        private long locationId;
        private long weatherId;

        public Builder() {
        }

        public Builder locationId(long val) {
            locationId = val;
            return this;
        }

        public Builder weatherId(long val) {
            weatherId = val;
            return this;
        }

        public LocationWeatherKey build() {
            return new LocationWeatherKey(this);
        }
    }
}
