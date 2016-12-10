package com.example.kbaran.pogoweather.Weather;


import com.example.kbaran.pogoweather.ModelBase;

import java.util.Date;

public class Weather extends ModelBase {
    private float temp;
    private float tempMin;
    private float tempMax;
    private float clouds;
    private int rain;
    private int snow;
    private int wind;
    private int pressure;
    private float humidity;
    private Date calcTime;

    private Weather(Builder builder) {
        setTemp(builder.temp);
        setTempMin(builder.tempMin);
        setTempMax(builder.tempMax);
        setClouds(builder.clouds);
        setRain(builder.rain);
        setSnow(builder.snow);
        setWind(builder.wind);
        setPressure(builder.pressure);
        setHumidity(builder.humidity);
        setCalcTime(builder.calcTime);
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public float getClouds() {
        return clouds;
    }

    public void setClouds(float clouds) {
        this.clouds = clouds;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public int getSnow() {
        return snow;
    }

    public void setSnow(int snow) {
        this.snow = snow;
    }

    public int getWind() {
        return wind;
    }

    public void setWind(int wind) {
        this.wind = wind;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public Date getCalcTime() {
        return calcTime;
    }

    public void setCalcTime(Date calcTime) {
        this.calcTime = calcTime;
    }

    public static final class Builder {
        private float temp;
        private float tempMin;
        private float tempMax;
        private float clouds;
        private int rain;
        private int snow;
        private int wind;
        private int pressure;
        private float humidity;
        private Date calcTime;

        public Builder() {
        }

        public Builder temp(float val) {
            temp = val;
            return this;
        }

        public Builder tempMin(float val) {
            tempMin = val;
            return this;
        }

        public Builder tempMax(float val) {
            tempMax = val;
            return this;
        }

        public Builder clouds(float val) {
            clouds = val;
            return this;
        }

        public Builder rain(int val) {
            rain = val;
            return this;
        }

        public Builder snow(int val) {
            snow = val;
            return this;
        }

        public Builder wind(int val) {
            wind = val;
            return this;
        }

        public Builder pressure(int val) {
            pressure = val;
            return this;
        }

        public Builder humidity(float val) {
            humidity = val;
            return this;
        }

        public Builder calcTime(Date val) {
            calcTime = val;
            return this;
        }

        public Weather build() {
            return new Weather(this);
        }
    }
}
