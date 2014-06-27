package com.jeffthefate.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Jeff on 6/19/2014.
 */
public class JsonUtil {

    private static JsonUtil jsonUtil;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger logger = Logger.getLogger(JsonUtil.class);

    public static JsonUtil instance() {
        if (jsonUtil == null) {
            jsonUtil = new JsonUtil();
        }
        return jsonUtil;
    }

    public SetlistResults getSetlistResults(String json) {
        try {
            return objectMapper.readValue(json, SetlistResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Results!");
            e.printStackTrace();
        }
        return null;
    }

    public SongResults getSongResults(String json) {
        try {
            return objectMapper.readValue(json, SongResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Results!");
            e.printStackTrace();
        }
        return null;
    }

    public PlayResults getPlayResults(String json) {
        try {
            return objectMapper.readValue(json, PlayResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Results!");
            e.printStackTrace();
        }
        return null;
    }

    public Song getSong(String json) {
        try {
            return objectMapper.readValue(json, Song.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Results!");
            e.printStackTrace();
        }
        return null;
    }

    public Setlist getSetlist(String json) {
        try {
            return objectMapper.readValue(json, Setlist.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Results!");
            e.printStackTrace();
        }
        return null;
    }

    public Created getCreated(String json) {
        try {
            return objectMapper.readValue(json, Created.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Results!");
            e.printStackTrace();
        }
        return null;
    }
    /*
    public ArrayNode getArray(String key, String json) {
        if (key == null || json == null || key.isEmpty() || json.isEmpty()) {
            return null;
        }
        JsonFactory f = new JsonFactory();
        JsonParser jp;
        String fieldName;
        String venue = null;
        try {
            jp = f.createJsonParser(json);
            JsonToken token;
            if (jp.nextToken() == JsonToken.START_OBJECT) {
                if (jp.nextToken() == JsonToken.FIELD_NAME &&
                        key.equals(jp.getCurrentName())) {
                    if (jp.nextToken() == JsonToken.START_ARRAY) {
                        jp.
                        while ((token = jp.nextToken()) !=
                                JsonToken.END_ARRAY) {
                            if (token == JsonToken.FIELD_NAME) {
                                fieldName = jp.getCurrentName();
                                if ("venue".equals(fieldName)) {
                                    while ((token = jp.nextToken()) !=
                                            JsonToken.END_OBJECT) {
                                        if (token == JsonToken.FIELD_NAME &&
                                                jp.getCurrentName().equals("objectId")) {
                                            jp.nextToken();
                                            venue = jp.getText();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            jp.close();
        } catch (JsonParseException e) {
            logger.info("Failed to parse " + responseString);
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("Failed to parse " + responseString);
            e.printStackTrace();
        }
    }
    */

}
