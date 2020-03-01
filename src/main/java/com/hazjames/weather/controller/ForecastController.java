package com.hazjames.weather.controller;

import com.hazjames.weather.data.ForecastRepository;
import com.hazjames.weather.helper.StringHelper;
import com.hazjames.weather.service.LocationService;
import com.hazjames.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import javax.validation.Valid;

@Controller
public class ForecastController {

  private ForecastRepository forecastRepository;

  @Autowired
  private WeatherService weatherService;

  @RequestMapping("/")
  public String getHome(ModelMap modelMap) {
    return "home";
  }

  @RequestMapping("/weather")
  public String redirectToHome(ModelMap modelMap) {
    return "redirect:/";
  }

  @PostMapping("weather")
  public String setForecast(@Valid String userLocation) {
    Map<String, String>
        location =
        LocationService
            .getCoordinates(ConfigurationController.getConfig().get("LOCATIONAPIKEY"),
                userLocation);

    forecastRepository =
        WeatherService
            .getForecasts(ConfigurationController.getConfig().get("WEATHERAPIKEY"),
                Double.valueOf(location.get("lon")), Double.valueOf(location.get("lat")));
    forecastRepository.setLocation(StringHelper.toTitleCase(location.get("name")));

    return "redirect:forecast";
  }

  @RequestMapping("/forecast")
  public String forecastDetails(ModelMap modelMap) {
    if(forecastRepository == null){
      return "redirect:/";
    } else {
      modelMap.put("forecasts", forecastRepository);
      return "weather";
    }
  }
}
