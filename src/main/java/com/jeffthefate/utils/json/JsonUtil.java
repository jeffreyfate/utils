package com.jeffthefate.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffthefate.utils.json.geocoding.GeocodingResponse;
import com.jeffthefate.utils.json.parse.*;
import com.jeffthefate.utils.json.timezone.TimeZone;
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

    public CredentialResults getCredentialResults(String json) {
        try {
            return objectMapper.readValue(json, CredentialResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public Credential getCredential(String json) {
        try {
            return objectMapper.readValue(json, Credential.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public SymbolResults getSymbolResults(String json) {
        try {
            return objectMapper.readValue(json, SymbolResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public Symbol getSymbol(String json) {
        try {
            return objectMapper.readValue(json, Symbol.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public MatchResults getMatchResults(String json) {
        try {
            return objectMapper.readValue(json, MatchResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public Match getMatch(String json) {
        try {
            return objectMapper.readValue(json, Match.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public ReplaceResults getReplaceResults(String json) {
        try {
            return objectMapper.readValue(json, ReplaceResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public Replace getReplace(String json) {
        try {
            return objectMapper.readValue(json, Replace.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public TipResults getTipResults(String json) {
        try {
            return objectMapper.readValue(json, TipResults.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public Tip getTip(String json) {
        try {
            return objectMapper.readValue(json, Tip.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public GeocodingResponse getGeocodingResponse(String json) {
        try {
            return objectMapper.readValue(json, GeocodingResponse.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

    public TimeZone getTimeZone(String json) {
        try {
            return objectMapper.readValue(json, TimeZone.class);
        } catch (IOException e) {
            logger.error("Unable to parse JSON to Count!", e);
            e.printStackTrace();
        }
        return null;
    }

}
