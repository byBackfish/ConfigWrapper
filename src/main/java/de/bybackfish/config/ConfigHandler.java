package de.bybackfish.config;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Setter
@Getter
public class ConfigHandler {

  private String url;
  private String apiKey;

  private HashMap<String, Config> cachedConfigs = new HashMap<>();

  public ExecutorService executorService = Executors.newSingleThreadExecutor();
  public CompletableFuture<Config> getConfig(String name) {
    CompletableFuture<Config> result = new CompletableFuture<>();
    executorService.submit(() -> {
      try {
        final InputStream inputStream = RequestHandler.sendPostRequest(url + "/get", new Pair<>("config", name));
        final JSONObject json = JsonParser.parse(inputStream);
        final JSONObject parse = (JSONObject) new JSONParser().parse(json.get("message").toString());
        final Config config = new Config(name, (JSONObject) parse.get("values"), url, apiKey);
        result.complete(config);
      }catch (Exception exception){
        exception.printStackTrace();
      }
    });
    return result;
  }

  public void flushCache(){
    cachedConfigs.clear();
  }

  public CompletableFuture<Config> getCachedConfig(String name){
    CompletableFuture<Config> result = new CompletableFuture<>();
    executorService.submit(() -> {
      if(cachedConfigs.containsKey(name))
        result.complete(cachedConfigs.get(name));
      getConfig(name).thenAccept((config) -> {
        cachedConfigs.put(name, config);
        result.complete(config);
      });
    });
    return result;
  }

}
