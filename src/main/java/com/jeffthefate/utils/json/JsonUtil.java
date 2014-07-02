package com.jeffthefate.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;

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

    public QuestionResults getQuestionResults(String json) {
        try {
            return objectMapper.readValue(json, QuestionResults.class);
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

    public VenueResults getVenueResults(String json) {
        try {
            return objectMapper.readValue(json, VenueResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Results!");
            e.printStackTrace();
        }
        return null;
    }

    public Venue getVenue(String json) {
        try {
            return objectMapper.readValue(json, Venue.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Results!");
            e.printStackTrace();
        }
        return null;
    }

    public CountResults getCountResults(String json) {
        try {
            return objectMapper.readValue(json, CountResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Results!");
            e.printStackTrace();
        }
        return null;
    }

    public Count getCount(String json) {
        try {
            return objectMapper.readValue(json, Count.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

}
