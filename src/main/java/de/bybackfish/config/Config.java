package de.bybackfish.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
public class Config {
  @Getter
  private String name;
  @Getter
  private JSONObject values;
  
  private String url;
  private String api;

  public Object getValue(String key){
    return values.get(key);
  }

  public InputStream setValue(String key, Object value)  {
    if(api == null){
      throw new NullPointerException("API Key cannot be null!");
    }
    values.put(key, value);
   try{
     return RequestHandler
         .sendPostRequest(url + "/set", new Pair<>("api", api), new Pair<>("config", name), new Pair<>("value", value),
             new Pair<>("key", key));
   }catch (Exception e) {
     e.printStackTrace();
   }

   return null;
  }

}
