package com.hazjames.weather.data;

import com.hazjames.weather.model.Forecast;
import com.hazjames.weather.model.TimeBlock;
import org.springframework.stereotype.Component;

@Component
public class ForecastRepository {
  private String location;

  private Forecast currently;

  private TimeBlock hourly;

  private TimeBlock daily;

  @Override
  public String toString() {
    return "ForecastRepository{" +
        "location='" + location + '\'' +
        ", currently=" + currently +
        ", hourly=" + hourly +
        ", daily=" + daily +
        '}';
  }

  public Forecast getCurrently() {
    return currently;
  }

  public TimeBlock getHourly() {
    return hourly;
  }

  public TimeBlock getDaily() {
    return daily;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
