package com.jeffthefate.utils.test;

import com.jeffthefate.utils.HtmlUtil;
import com.jeffthefate.utils.WarehouseHtmlUtil;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;

public class HtmlUtilTest extends TestCase {

    private HtmlUtil htmlUtil;
    private WarehouseHtmlUtil warehouseHtmlUtil;

    public void setUp() throws Exception {
        super.setUp();
        htmlUtil = HtmlUtil.instance();
        warehouseHtmlUtil = WarehouseHtmlUtil.instance();
    }

    public void testGetPageDocument() {
        Document document = htmlUtil.getPageDocument("http://www.google.com",
                false);
        assertNotNull("Document is null!", document);
        document = htmlUtil.getPageDocument("http://www.google.com", true);
        assertNull("Document isn't null!", document);
        document = htmlUtil.getPageDocument("https://www.google.com", true);
        assertNotNull("Document is null!", document);
        document = htmlUtil.getPageDocument("src/test/resources/google", false);
        assertNotNull("Document is null!", document);
        document = htmlUtil.getPageDocument("src/test/resources/google", true);
        assertNotNull("Document is null!", document);
        document = htmlUtil.getPageDocument("", true);
        assertNull("Document isn't null!", document);
        document = htmlUtil.getPageDocument("", false);
        assertNull("Document isn't null!", document);
        document = htmlUtil.getPageDocument(null, true);
        assertNull("Document isn't null!", document);
        document = htmlUtil.getPageDocument(null, false);
        assertNull("Document isn't null!", document);

        document = warehouseHtmlUtil.getPageDocument(
                "https://whsec1.davematthewsband.com/backstage.asp", false);
        assertNotNull("Document is null!", document);
        document = warehouseHtmlUtil.getPageDocument(
                "https://whsec1.davematthewsband.com/backstage.asp", true);
        assertNotNull("Document is null!", document);
        document = warehouseHtmlUtil.getPageDocument(
                "http://whsec1.davematthewsband.com/backstage.asp", true);
        assertNull("Document isn't null!", document);
        document = warehouseHtmlUtil.getPageDocument(
                "src/test/resources/warehouse", false);
        assertNotNull("Document is null!", document);
        document = warehouseHtmlUtil.getPageDocument(
                "src/test/resources/warehouse", true);
        assertNotNull("Document is null!", document);
        document = warehouseHtmlUtil.getPageDocument("", true);
        assertNull("Document isn't null!", document);
        document = warehouseHtmlUtil.getPageDocument("", false);
        assertNull("Document isn't null!", document);
        document = warehouseHtmlUtil.getPageDocument(null, true);
        assertNull("Document isn't null!", document);
        document = warehouseHtmlUtil.getPageDocument(null, false);
        assertNull("Document isn't null!", document);
    }

}
