package com.jeffthefate.utils;

import com.jeffthefate.utils.json.JsonUtil;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class PlacefinderUtil {

    private static PlacefinderUtil placefinderUtil;
    private static CredentialUtil credentialUtil;
    private static JsonUtil jsonUtil;

    private final String PLACEFINDER_URL =
            "https://yboss.yahooapis.com/geo/placefinder?";

    private Logger logger = Logger.getLogger(PlacefinderUtil.class);

    public static PlacefinderUtil instance() {
        if (placefinderUtil == null) {
            placefinderUtil = new PlacefinderUtil();
        }
        if (credentialUtil == null) {
            credentialUtil = CredentialUtil.instance();
        }
        if (jsonUtil == null) {
            jsonUtil = JsonUtil.instance();
        }
        return placefinderUtil;
    }

    /**
     * Execute the OAuth powered request and get the string response.
     *
     * @param params    encoded parameters to give the Placefinder service
     * @param consumer  OAuth consumer used in this request
     * @return          the response string
     */
    private String getResponse(String params, OAuthConsumer consumer) {
        System.out.println(consumer.getConsumerKey());
        System.out.println(consumer.getConsumerSecret());
        System.out.println(consumer.getToken());
        System.out.println(consumer.getTokenSecret());
        HttpURLConnection uc = null;
        String url = PLACEFINDER_URL.concat(params);
        try {
            URL u = new URL(url);

            uc = (HttpURLConnection) u.openConnection();

            if (consumer != null) {
                try {
                    logger.info("Signing the oAuth consumer");
                    consumer.sign(uc);

                } catch (OAuthMessageSignerException e) {
                    logger.error("Error signing the consumer", e);

                } catch (OAuthExpectationFailedException e) {
                    logger.error("Error signing the consumer", e);

                } catch (OAuthCommunicationException e) {
                    logger.error("Error signing the consumer", e);
                }
                uc.connect();
            }
        } catch (MalformedURLException e) {
            logger.error("Bad URL: " + url + "!", e);
        } catch (IOException e) {
            logger.error("Error signing the consumer!", e);
        }
        if (uc == null) {
            return null;
        }
        try {
            int responseCode = uc.getResponseCode();
            if (200 == responseCode || 401 == responseCode ||
                    404 == responseCode) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        responseCode == 200 ? uc.getInputStream() : uc
                                .getErrorStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                return sb.toString();
            }
        } catch (MalformedURLException e) {
            logger.error(url + " is not valid!", e);
        } catch (IOException e) {
            logger.error("", e);
        }
        return null;
    }

    public String getPlaceTimeZone(boolean isDev, String placeName,
            String credFile) {
        String params = "location=" + placeName +
                "&appid=6Pi96B56&flags=TJ";
        try {
            params = URLEncoder.encode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Unable to encode parameters " + params, e);
            return null;
        }
        String json = getResponse(params, credentialUtil.getCredentialedOAuth(
                isDev, credFile));
        return null;
    }
}
