package com.jeffthefate.utils.test;

import com.jeffthefate.utils.CredentialUtil;
import com.jeffthefate.utils.HtmlUtil;
import com.jeffthefate.utils.Parse;
import com.jeffthefate.utils.WarehouseHtmlUtil;
import com.jeffthefate.utils.json.JsonUtil;
import com.jeffthefate.utils.json.parse.Credential;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;

import java.util.List;

public class HtmlUtilTest extends TestCase {

    private HtmlUtil htmlUtil;
    private WarehouseHtmlUtil warehouseHtmlUtil;

    private String warehouseUser;
    private String warehousePass;

    public void setUp() throws Exception {
        super.setUp();
        htmlUtil = HtmlUtil.instance();
        warehouseHtmlUtil = WarehouseHtmlUtil.instance();
        CredentialUtil credentialUtil = CredentialUtil.instance();
        Parse parse = credentialUtil.getCredentialedParse(true,
                "parseCreds");
        JsonUtil jsonUtil = JsonUtil.instance();
        List<Credential> credentialList = jsonUtil.getCredentialResults(
                parse.get("Credential", "")).getResults();
        for (Credential credential : credentialList) {
            switch(credential.getName()) {
                case "warehouseUser":
                    warehouseUser = credential.getValue();
                    break;
                case "warehousePass":
                    warehousePass = credential.getValue();
                    break;
            }
        }
    }

    public void testGetPageDocument() {
        Document document = htmlUtil.getPageDocument("http://www.google.com",
                false, warehouseUser, warehousePass);
        assertNotNull("Document is null!", document);
        document = htmlUtil.getPageDocument("http://www.google.com", true,
                warehouseUser, warehousePass);
        assertNull("Document isn't null!", document);
        document = htmlUtil.getPageDocument("https://www.google.com", true,
                warehouseUser, warehousePass);
        assertNotNull("Document is null!", document);
        document = htmlUtil.getPageDocument("src/test/resources/google", false,
                warehouseUser, warehousePass);
        assertNotNull("Document is null!", document);
        document = htmlUtil.getPageDocument("src/test/resources/google", true,
                warehouseUser, warehousePass);
        assertNotNull("Document is null!", document);
        document = htmlUtil.getPageDocument("", true, null, null);
        assertNull("Document isn't null!", document);
        document = htmlUtil.getPageDocument("", false, null, null);
        assertNull("Document isn't null!", document);
        document = htmlUtil.getPageDocument(null, true, null, null);
        assertNull("Document isn't null!", document);
        document = htmlUtil.getPageDocument(null, false, null, null);
        assertNull("Document isn't null!", document);

        document = warehouseHtmlUtil.getPageDocument(
                "https://whsec1.davematthewsband.com/backstage.asp", false,
                warehouseUser, warehousePass);
        assertNotNull("Document is null!", document);
        document = warehouseHtmlUtil.getPageDocument(
                "https://whsec1.davematthewsband.com/backstage.asp", true,
                warehouseUser, warehousePass);
        assertNotNull("Document is null!", document);
        document = warehouseHtmlUtil.getPageDocument(
                "http://whsec1.davematthewsband.com/backstage.asp", true,
                warehouseUser, warehousePass);
        assertNull("Document isn't null!", document);
        document = warehouseHtmlUtil.getPageDocument(
                "src/test/resources/warehouse", false,
                warehouseUser, warehousePass);
        assertNotNull("Document is null!", document);
        document = warehouseHtmlUtil.getPageDocument(
                "src/test/resources/warehouse", true,
                warehouseUser, warehousePass);
        assertNotNull("Document is null!", document);
        document = warehouseHtmlUtil.getPageDocument("", true,
                warehouseUser, warehousePass);
        assertNull("Document isn't null!", document);
        document = warehouseHtmlUtil.getPageDocument("", false,
                warehouseUser, warehousePass);
        assertNull("Document isn't null!", document);
        document = warehouseHtmlUtil.getPageDocument(null, true,
                warehouseUser, warehousePass);
        assertNull("Document isn't null!", document);
        document = warehouseHtmlUtil.getPageDocument(null, false,
                warehouseUser, warehousePass);
        assertNull("Document isn't null!", document);
    }

}
