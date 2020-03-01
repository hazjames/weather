package com.hazjames.weather.model;

import java.util.List;

public class TimeBlock {
    private List<Forecast> data;

    public List<Forecast> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "TimeFrame{" +
                "forecasts=" + data +
                '}';
    }
}
