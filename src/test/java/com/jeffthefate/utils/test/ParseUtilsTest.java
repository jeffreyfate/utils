package com.jeffthefate.utils.test;

import com.jeffthefate.utils.ParseUtils;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

public class ParseUtilsTest extends TestCase {

    private String testAppId = "6pJz1oVHAwZ7tfOuvHfQCRz6AVKZzg1itFVfzx2q";
    private String testRestApi = "uNZMDvDSahtRxZVRwpUVwzAG9JdLzx4cbYnhYPi7";

    public void testParseUtils() {
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        Assert.assertEquals("App ID not set!", testAppId,
                parseUtils.getAppIdHeader().getValue());
        Assert.assertEquals("Rest API not set!", testRestApi,
                parseUtils.getRestApiHeader().getValue());
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

    public void testGetGoodClass() {
        // A real app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        // An existing class
        String response = parseUtils.get("Setlist", "");
        Assert.assertNotNull("Get failed!", response);
    }

    public void testGetBadClass() {
        // A real app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        // A non-existent class
        String response = parseUtils.get("TEST", "");
        Assert.assertNotNull("Get failed!", response);
    }

    public void testGetNullClass() {
        // A real app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        // Null
        String response = parseUtils.get(null, "");
        Assert.assertNotNull("Get failed!", response);
        // A fake app ID and REST Api combination
        parseUtils = new ParseUtils("testAppId", "testRestApi");
        response = parseUtils.get("TEST", "");
        Assert.assertNull("Get succeeded!", response);
    }

    public void testGetBadHeaders() {
        // A fake app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils("testAppId", "testRestApi");
        String response = parseUtils.get("TEST", "");
        Assert.assertNull("Get succeeded!", response);
    }

    public void testPutTestData() {
        // A real app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        // An existing object
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("test", "test");
        String response = parseUtils.put("Setlist", "JB6fOawDj9",
                rootNode.toString());
        Assert.assertNotNull("Put failed!", response);
    }

    public void testPutEmptyData() {
        // A real app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        // An existing object
        String response = parseUtils.put("Setlist", "JB6fOawDj9", "");
        Assert.assertNull("Put succeeded!", response);
    }

    public void testPutNullData() {
        // A real app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        // An existing object
        String response = parseUtils.put("Setlist", "JB6fOawDj9", null);
        Assert.assertNull("Put succeeded!", response);
    }

    public void testPutBadHeaders() {
        // A fake app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils("testAppId", "testRestApi");
        // An existing object
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("test", "test");
        String response = parseUtils.put("Setlist", "JB6fOawDj9",
                rootNode.toString());
        Assert.assertNull("Put succeeded!", response);
    }

    public void testPostTestData() {
        // A real app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        // Good data
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("test", "test");
        String response = parseUtils.post("Setlist", rootNode.toString());
        Assert.assertNotNull("Post failed!", response);
    }

    public void testPostEmptyData() {
        // A real app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        // Empty data
        String response = parseUtils.post("Setlist", "");
        Assert.assertNull("Post succeeded!", response);
    }

    public void testPostNullData() {
        // A real app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils(testAppId, testRestApi);
        // Null data
        String response = parseUtils.post("Setlist", null);
        Assert.assertNull("Post succeeded!", response);
    }

    public void testPostBadHeaders() {
        // A fake app ID and REST Api combination
        ParseUtils parseUtils = new ParseUtils("testAppId", "testRestApi");
        // Good data
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("test", "test");
        String response = parseUtils.post("Setlist", rootNode.toString());
        Assert.assertNull("Put succeeded!", response);
    }

}