package com.jeffthefate.utils.test;

import com.jeffthefate.utils.Backendless;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class BackendlessTest extends TestCase {

    private String testAppId = "6pJz1oVHAwZ7tfOuvHfQCRz6AVKZzg1itFVfzx2q";
    private String testRestApi = "uNZMDvDSahtRxZVRwpUVwzAG9JdLzx4cbYnhYPi7";

    public void testBackendlessUtils() {
        Backendless backendless = new Backendless(testAppId, testRestApi);
        Assert.assertEquals("App ID not set!", testAppId,
                backendless.getAppIdHeader().getValue());
        Assert.assertEquals("Rest API not set!", testRestApi,
                backendless.getRestApiHeader().getValue());
        // creates pattern layout
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "[%p] %d %c %M - %m%n";
        layout.setConversionPattern(conversionPattern);
        // creates daily rolling file appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        // configures the root logger
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(consoleAppender);
    }
    /**
    @Ignore
    public void testGetGoodClass() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        // An existing class
        String response = parse.get("Setlist", "");
        Assert.assertNotNull("Get failed!", response);
    }

    @Ignore
    public void testGetBadClass() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        // A non-existent class
        String response = parse.get("TEST", "");
        Assert.assertNotNull("Get failed!", response);
    }

    @Ignore
    public void testGetNullClass() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        // Null
        String response = parse.get(null, "");
        Assert.assertNotNull("Get failed!", response);
        // A fake app ID and REST Api combination
        parse = new Parse("testAppId", "testRestApi");
        response = parse.get("TEST", "");
        Assert.assertNull("Get succeeded!", response);
    }

    @Ignore
    public void testGetBadHeaders() {
        // A fake app ID and REST Api combination
        Parse parse = new Parse("testAppId", "testRestApi");
        String response = parse.get("TEST", "");
        Assert.assertNull("Get succeeded!", response);
    }

    @Ignore
    public void testGetObject() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        String response = parse.getObject("Setlist", "hVAIYCTXhv");
        Assert.assertNotNull("Get failed!", response);
    }

    @Ignore
    public void testPutTestData() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        // An existing object
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("test", "test");
        String response = parse.put("Setlist", "hVAIYCTXhv",
                rootNode.toString());
        Assert.assertNotNull("Put failed!", response);
    }

    @Ignore
    public void testPutEmptyData() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        // An existing object
        String response = parse.put("Setlist", "hVAIYCTXhv", "");
        Assert.assertNull("Put succeeded!", response);
    }

    public void testPutNullData() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        // An existing object
        String response = parse.put("Setlist", "hVAIYCTXhv", null);
        Assert.assertNull("Put succeeded!", response);
    }

    @Ignore
    public void testPutBadHeaders() {
        // A fake app ID and REST Api combination
        Parse parse = new Parse("testAppId", "testRestApi");
        // An existing object
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("test", "test");
        String response = parse.put("Setlist", "hVAIYCTXhv",
                rootNode.toString());
        Assert.assertNull("Put succeeded!", response);
    }

    @Ignore
    public void testPostTestData() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        // Good data
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("test", "test");
        String response = parse.post("Setlist", rootNode.toString());
        Assert.assertNotNull("Post failed!", response);
    }

    @Ignore
    public void testPostEmptyData() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        // Empty data
        String response = parse.post("Setlist", "");
        Assert.assertNull("Post succeeded!", response);
    }

    @Ignore
    public void testPostNullData() {
        // A real app ID and REST Api combination
        Parse parse = new Parse(testAppId, testRestApi);
        // Null data
        String response = parse.post("Setlist", null);
        Assert.assertNull("Post succeeded!", response);
    }

    @Ignore
    public void testPostBadHeaders() {
        // A fake app ID and REST Api combination
        Parse parse = new Parse("testAppId", "testRestApi");
        // Good data
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("test", "test");
        String response = parse.post("Setlist", rootNode.toString());
        Assert.assertNull("Put succeeded!", response);
    }
    */
}