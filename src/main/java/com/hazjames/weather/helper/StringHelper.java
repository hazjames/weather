package com.hazjames.weather.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringHelper {

  public static String toTitleCase(String string) {
    String[] arr = string.split(" ");
    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < arr.length; i++) {
      sb.append(Character.toUpperCase(arr[i].charAt(0)))
          .append(arr[i].substring(1)).append(" ");
    }

    return sb.toString().trim();
  }

  public static String getConfigSetting(String string) {
    String[] arr = string.split(": ");

    return arr[1];
  }

  public static Map<String, String> getConfigSettings(String file) throws IOException {

    List<String> lines = Files.readAllLines(Paths.get(file));

    return lines.stream()
        .filter(line -> line.matches("^\\w+:: \\w+"))
        .collect(Collectors.toMap(k -> k.split(":: ")[0], v -> v.split(":: ")[1]));
  }

}
