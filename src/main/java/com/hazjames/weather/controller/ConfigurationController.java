package com.hazjames.weather.controller;

import com.hazjames.weather.helper.StringHelper;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Map;
import javax.annotation.PostConstruct;

@Controller
public class ConfigurationController {

  private static Map<String, String> config;

  @PostConstruct
  private void init() {
    try {
      config = StringHelper.getConfigSettings("./src/main/resources/config.txt");
    } catch (IOException e) {
      System.out.println("Unable to find config file in path: " + e.getMessage());
      System.out.println("\n" + "Exiting Application...");
      System.exit(0);
    }
  }

  public static Map<String, String> getConfig() {
    return config;
  }
}
