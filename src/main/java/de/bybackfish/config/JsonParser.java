package de.bybackfish.config;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JsonParser {
  private final static JSONParser jsonParser = new JSONParser();

  public static JSONObject parse(InputStream inputStream) throws IOException, ParseException {
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    final JSONObject parse = (JSONObject) jsonParser.parse(inputStreamReader);
    return parse;
  }

}
