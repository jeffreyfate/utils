package com.jeffthefate.utils;

import com.jeffthefate.utils.json.JsonUtil;
import com.jeffthefate.utils.json.geocoding.LatLon;
import com.jeffthefate.utils.json.timezone.TimeZone;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.security.InvalidParameterException;
import java.util.Locale;

public class TimeZoneUtil {

    private static TimeZoneUtil timeZoneUtil;
    private static CredentialUtil credentialUtil;
    private static JsonUtil jsonUtil;

    private final String TIMEZONE_URL = "https://maps.googleapis.com/maps/api/"
            + "timezone/json?location=%s&timestamp=%d&key=" +
            "AIzaSyAaKaO0bDXWOYE9ez1Ok7MROnHjVBPDu6E";

    private Logger logger = Logger.getLogger(TimeZoneUtil.class);

    public static TimeZoneUtil instance() {
        if (timeZoneUtil == null) {
            timeZoneUtil = new TimeZoneUtil();
        }
        if (credentialUtil == null) {
            credentialUtil = CredentialUtil.instance();
        }
        if (jsonUtil == null) {
            jsonUtil = JsonUtil.instance();
        }
        return timeZoneUtil;
    }

    public long getTimeOffset(LatLon latLon) throws InvalidParameterException {
        if (latLon == null) {
            throw new InvalidParameterException("latLon can't be null!");
        }
        String json = get(String.format(Locale.getDefault(), TIMEZONE_URL,
                latLon.getLat() + "," + latLon.getLng(),
                System.currentTimeMillis() / 1000));
        TimeZone timeZone = jsonUtil.getTimeZone(json);
        long offset = timeZone.getDstOffset() + timeZone.getRawOffset();
        return offset;
    }

    /**
     * Perform a HTTP GET on a given class with a query string.
     *
     * @param url   url to use in the GET
     * @return      response string from the GET; null if error
     */
    public String get(String url) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
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
