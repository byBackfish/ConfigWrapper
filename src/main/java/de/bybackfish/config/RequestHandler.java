package de.bybackfish.config;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.StandardProtocolFamily;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class RequestHandler {

  public static InputStream sendPostRequest(String uri, Map<String, Object> body) throws IOException {
    String json = parseBody(body).toJSONString();
    URLConnection connection = new URL(uri).openConnection();
    connection.setDoOutput(true); // Triggers POST.
    connection.setRequestProperty("Accept-Charset", "UTF-8");
    connection.setRequestProperty("Content-Type", "application/json;charset=" + "UTF-8");

    try (OutputStream output = connection.getOutputStream()) {
      output.write(json.getBytes("UTF-8"));
    }
    InputStream response = connection.getInputStream();
    return response;
  }

  public static InputStream sendPostRequest(String uri, Pair<String, Object>... body) throws IOException {
    return sendPostRequest(uri, Arrays.stream(body).collect(Collectors.toMap(Pair::getKey, Pair::getValue)));
  }

  private static JSONObject parseBody(Map<String, Object> body){
    JSONObject json = new JSONObject();
    json.putAll( body );
    return json;
  }

}
