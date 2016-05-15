package com.jeffthefate.utils;

import com.jeffthefate.utils.json.JsonUtil;
import com.jeffthefate.utils.json.geocoding.Geocode;
import com.jeffthefate.utils.json.geocoding.GeocodingResponse;
import com.jeffthefate.utils.json.geocoding.LatLon;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Locale;

public class GeocodingUtil {

    private static GeocodingUtil geocodingUtil;
    private static CredentialUtil credentialUtil;
    private static JsonUtil jsonUtil;

    private final String GEOCODING_URL =
            "http://maps.googleapis.com/maps/api/geocode/json?address=";

    private Logger logger = Logger.getLogger(GeocodingUtil.class);

    public static GeocodingUtil instance() {
        if (geocodingUtil == null) {
            geocodingUtil = new GeocodingUtil();
        }
        if (credentialUtil == null) {
            credentialUtil = CredentialUtil.instance();
        }
        if (jsonUtil == null) {
            jsonUtil = JsonUtil.instance();
        }
        return geocodingUtil;
    }

    public LatLon getCityLatLon(String placeName) {
        if (StringUtils.isBlank(placeName)) {
            return null;
        }
        String json = get(StringUtils.replace(placeName, " ", "%20"));
        GeocodingResponse geocodingResponse = jsonUtil.getGeocodingResponse(
                json);
        List<Geocode> geocodeList = geocodingResponse.getResults();
        for (Geocode geocode : geocodeList) {
            if (geocode.getFormatted_address().toLowerCase(Locale.getDefault())
                    .contains(placeName.toLowerCase(Locale.getDefault()))) {
                return geocode.getGeometry().getLocation();
            }
        }
        if (!geocodeList.isEmpty()) {
            return geocodeList.get(0).getGeometry().getLocation();
        }
        return null;
    }

    /**
     * Perform a HTTP GET on a given class with a query string.
     *
     * @param queryString   query to base the GET on
     * @return              response string from the GET; null if error
     */
    public String get(String queryString) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(GEOCODING_URL + queryString);
        HttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("Get did not fetch successfully!");
                logger.error(httpResponse.getStatusLine().getReasonPhrase());
                return null;
            }
            String response = null;
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                try {
                    response = EntityUtils.toString(entity);
                } catch (Exception e) {
                    logger.error("Failed parsing response string from entity!", e);
                    return null;
                }
            }
            return response;
        } catch (Exception e) {
            logger.error("Failed executing get request!", e);
            return null;
        }
    }
}
