package com.jeffthefate.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

import java.util.InputMismatchException;

/**
 * <p>
 *     Utility functions that interact with the Backendless web backend.
 * </p>
 * <p>
 *     A failure is indicated to users by returning null, so checking for null is required to check that something
 *     failed.
 * </p>
 */
public class Backendless {

    private final String urlBase = "https://api.backendless.com/v1/data/";

    public Header getAppIdHeader() {
        return appIdHeader;
    }

    public void setAppIdHeader(Header appIdHeader) {
        this.appIdHeader = appIdHeader;
    }

    public Header getRestApiHeader() {
        return restApiHeader;
    }

    public void setRestApiHeader(Header restApiHeader) {
        this.restApiHeader = restApiHeader;
    }

    private Header appIdHeader;
    private Header restApiHeader;
    private Header contentTypeHeader;
    private Header applicationTypeHeader;

    private HttpClient httpClient = HttpClientBuilder.create().build();

    private Logger logger = Logger.getLogger(Backendless.class);

    /**
     * Create instance with required headers.
     *
     * @param appId     ID for the app to interact with
     * @param restApi   REST API key for the app to interact with
     */
    public Backendless(Object appId, Object restApi) throws InputMismatchException {
        final String appIdKey = "application-id";
        final String restApiKey = "secret-key";
        if (appId instanceof String) {
            appIdHeader = new BasicHeader(appIdKey, (String) appId);
        }
        else {
            throw new InputMismatchException("The appId is not a string!");
        }
        if (restApi instanceof String) {
            restApiHeader = new BasicHeader(restApiKey, (String) restApi);
        }
        else {
            throw new InputMismatchException("The restApi is not a string!");
        }
        contentTypeHeader = new BasicHeader("Content-Type", "application/json");
        applicationTypeHeader = new BasicHeader("application-type", "REST");
    }
    /**
     * Perform a HTTP GET on a given class with a query string.
     *
     * @param className     name of class to interact with
     * @param queryString   query to base the GET on
     * @return              response string from the GET; null if error
     */
    public String get(String className, String queryString) {
        HttpGet httpGet = new HttpGet(combineUrl(urlBase, className, queryString));
        httpGet.addHeader(appIdHeader);
        httpGet.addHeader(restApiHeader);
        httpGet.addHeader(contentTypeHeader);
        httpGet.addHeader(applicationTypeHeader);
        HttpResponse httpResponse = getResponse(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            logger.error("Get did not fetch successfully!");
            logger.error(httpResponse.getStatusLine().getReasonPhrase());
            return null;
        }
        return getResponseString(httpResponse);
    }
    /**
     * Perform a HTTP GET on a given class with a given object.
     *
     * @param className     name of class to interact with
     * @param objectId      ID of object to fetch
     * @return              response string from the GET; null if error
     */
    public String getObject(String className, String objectId) {
        HttpGet httpGet = new HttpGet(combineUrl(urlBase, className, "/", objectId));
        httpGet.addHeader(appIdHeader);
        httpGet.addHeader(restApiHeader);
        httpGet.addHeader(contentTypeHeader);
        httpGet.addHeader(applicationTypeHeader);
        HttpResponse httpResponse = getResponse(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            logger.error("Get did not fetch successfully!");
            logger.error(httpResponse.getStatusLine().getReasonPhrase());
            return null;
        }
        return getResponseString(httpResponse);
    }
    /**
     * Perform a HTTP PUT on a given class and object.
     *
     * @param className name of class to interact with
     * @param objectId  identifier of the object to edit
     * @param data      what to set the object value to
     * @return          response string from the PUT; null if error
     */
    public String put(String className, String objectId, String data) {
        HttpPut httpPut = new HttpPut(combineUrl(urlBase, className, "/",
                objectId));
        httpPut.addHeader(appIdHeader);
        httpPut.addHeader(restApiHeader);
        httpPut.addHeader(contentTypeHeader);
        httpPut.addHeader(applicationTypeHeader);
        try {
            httpPut.setEntity(new StringEntity(data, ContentType.APPLICATION_JSON));
        } catch (Exception e) {
            logger.error("Bad data!", e);
            return null;
        }
        HttpResponse httpResponse = getResponse(httpPut);
        if (httpResponse.getStatusLine().getStatusCode() != HttpStatus
                .SC_OK) {
            logger.error("Put did not update object!");
            logger.error(httpResponse.getStatusLine().getReasonPhrase());
            return null;
        }
        return getResponseString(httpResponse);
    }
    /**
     * Perform a HTTP POST on a given class with data to add.
     *
     * @param className  name of class to interact with
     * @param data       data to add to the new object
     * @return           response string from the POST; null if error
     */
    public String post(String className, String data) {
        HttpPost httpPost = new HttpPost(combineUrl(urlBase, className));
        httpPost.addHeader(appIdHeader);
        httpPost.addHeader(restApiHeader);
        httpPost.addHeader(contentTypeHeader);
        httpPost.addHeader(applicationTypeHeader);
        try {
            httpPost.setEntity(new StringEntity(data, ContentType.APPLICATION_JSON));
        } catch (Exception e) {
            logger.error("Bad data!");
            return null;
        }
        HttpResponse httpResponse = getResponse(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() != HttpStatus
                .SC_CREATED) {
            logger.error("Post did not create object!");
            logger.error(httpResponse.getStatusLine().getReasonPhrase());
            return null;
        }
        return getResponseString(httpResponse);
    }

    /**
     * <p>
     *     Data format is likely similar to
     *     {
     *         "message": "Message",
     *         "headers": {"key1": "value1", "key2": "value2"}
     *     }
     *     https://backendless.com/documentation/messaging/rest/messaging_message_publishing.htm
     * </p>
     * @param json  data sent as a message (default is both push and pub/sub)
     * @return      response string
     */
    public String postPush(String json) {
        final String pushBase = "https://api.backendless.com/v1/messaging/setlist";
        HttpPost httpPost = new HttpPost(combineUrl(pushBase));
        httpPost.addHeader(appIdHeader);
        httpPost.addHeader(restApiHeader);
        httpPost.addHeader(contentTypeHeader);
        httpPost.addHeader(applicationTypeHeader);
        try {
            httpPost.setEntity(new StringEntity(json,
                    ContentType.APPLICATION_JSON));
        } catch (Exception e) {
            logger.error("Bad data!");
            return null;
        }
        HttpResponse httpResponse = getResponse(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() != HttpStatus
                .SC_OK) {
            logger.error("Post did not create object!");
            logger.error(httpResponse.getStatusLine().getReasonPhrase());
            return null;
        }
        return getResponseString(httpResponse);
    }

    /**
     * Combine the parameters to one URL string.
     *
     * @param pieces    individual strings to concatenate into one
     * @return          the combined URL
     */
    private String combineUrl(String... pieces) {
        StringBuilder sb = new StringBuilder();
        for (String piece : pieces) {
            sb.append(piece);
        }
        return sb.toString();
    }

    /**
     * Execute the request.
     *
     * @param request   request to execute
     * @return          the response
     */
    private HttpResponse getResponse(HttpRequestBase request) {
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (Exception e) {
            logger.error("Failed executing get request!", e);
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Extract the response string from the response.
     *
     * @param httpResponse  response object holding the string
     * @return              string extracted from response
     */
    private String getResponseString(HttpResponse httpResponse) {
        String response = null;
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            try {
                response = EntityUtils.toString(entity);
            } catch (Exception e) {
                logger.error("Failed parsing response string from entity!", e);
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * Mark a single trivia question with a given trivia level.
     *
     * @param objectId      the ID of the question to mark
     * @param triviaLevel   level to set it to
     * @return              true if successful
     */
    public boolean markAsTrivia(String objectId, int triviaLevel) {
        logger.info("Marking question " + objectId + " to " + triviaLevel);
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("trivia", triviaLevel);
        return put("Question", objectId, rootNode.toString()) != null;
    }

}
