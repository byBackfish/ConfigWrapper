package de.bybackfish.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

  public static void main(String[] args) {
   ConfigHandler configHandler = new ConfigHandler();
    configHandler.setUrl(/* REQUIRED */  "http://localhost:3333");
    configHandler.setApiKey(/* OPTIONAL */ "api-key");
    configHandler.getConfig("test").thenAccept((config -> {
      config.setValue("test", true);
      System.out.println("Completed! " + config.getValue("test"));
    }));

  }

}
