package com.hazjames.weather.model;

import java.util.Date;
import java.time.Instant;

public class Forecast {
    private int time;

    private String summary;

    private String icon;

    private Double precipProbability;

    private Double temperature;

    private Double apparentTemperature;

    private Double temperatureHigh;

    private Double temperatureLow;

    @Override
    public String toString() {
        return "Forecast{" +
            "time=" + time +
            ", summary='" + summary + '\'' +
            ", icon='" + icon + '\'' +
            ", precipProbability=" + precipProbability +
            ", temperature=" + temperature +
            ", apparentTemperature=" + apparentTemperature +
            ", temperatureHigh=" + temperatureHigh +
            ", temperatureLow=" + temperatureLow +
            '}';
    }

    public Date getTime() {
        Instant instant = Instant.ofEpochSecond(time);
        return Date.from(instant);
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public int getPrecipProbability() {
        return (int) (precipProbability * 100);
    }

    public long getTemperature() {
        return Math.round(temperature);
    }

    public long getApparentTemperature() {
        return Math.round(apparentTemperature);
    }

    public long getTemperatureHigh() {
        return Math.round(temperatureHigh);
    }

    public long getTemperatureLow() {
        return Math.round(temperatureLow);
    }
}
