package com.hazjames.weather.service;

import com.locationiq.client.api.SearchApi;
import com.locationiq.client.model.Location;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import LocationIq.ApiClient;
import LocationIq.ApiException;
import LocationIq.Configuration;
import LocationIq.auth.ApiKeyAuth;

@Component
public class LocationService {

  public static Map<String, String> getCoordinates(String apiKey, String userLocation) {
    Map<String, String> location = new HashMap();

    ApiClient defaultClient = Configuration.getDefaultApiClient();

    ApiKeyAuth key = (ApiKeyAuth) defaultClient.getAuthentication("key");
    key.setApiKey(apiKey);

    SearchApi apiInstance = new SearchApi();
    String q = userLocation;
    String format = "json";
    Integer normaliseCity = 1;
    Integer limit = 1;

    try {
      List<Location>
          result =
          apiInstance
              .search(q, format, normaliseCity, null, null, null, limit, null, null, null, null,
                  null, null);

      location.put("name", getName(result.get(0).getDisplayName()));
      location.put("lon", result.get(0).getLon());
      location.put("lat", result.get(0).getLat());
    } catch (ApiException e) {
      System.out.println("Exception when calling SearchApi#search");
    }

    return location;
  }

  private static String getName(String displayName) {
    StringBuilder name = new StringBuilder();
    String[] s = displayName.split(", ");

    name.append(s[0]);
    name.append(", ");
    name.append(s[s.length - 1]);

    return name.toString();
  }
}
