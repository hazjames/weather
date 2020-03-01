package com.hazjames.weather.service;

import com.google.gson.Gson;

import com.hazjames.weather.data.ForecastRepository;
import org.springframework.stereotype.Component;

import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.DarkSkyClient;
import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

@Component
public class WeatherService {

  public static ForecastRepository getForecasts(String apiKey, Double lon, Double lat) {
    Gson gson = new Gson();

    ForecastRepository repository = new ForecastRepository();

    ForecastRequest request = new ForecastRequestBuilder()
        .key(new APIKey(apiKey))
        .location(new GeoCoordinates(new Longitude(lon), new Latitude(lat)))
        .language(ForecastRequestBuilder.Language.en)
        .units(ForecastRequestBuilder.Units.uk2)
        .exclude(ForecastRequestBuilder.Block.flags)
        .exclude(ForecastRequestBuilder.Block.minutely)
        .build();

    try {
      DarkSkyClient client = new DarkSkyClient();
      repository = gson.fromJson(client.forecastJsonString(request), ForecastRepository.class);
    } catch (ForecastException e) {
      System.out.println("Exception when calling ForecastApi#forecast");
    }

    return repository;
  }
}
