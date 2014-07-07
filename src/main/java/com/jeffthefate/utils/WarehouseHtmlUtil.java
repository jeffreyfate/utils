package com.jeffthefate.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseHtmlUtil extends HtmlUtil {

    private static WarehouseHtmlUtil warehouseHtmlUtil;
    private static FileUtil fileUtil;

    private Logger logger = Logger.getLogger(WarehouseHtmlUtil.class);

    public static WarehouseHtmlUtil instance() {
        if (warehouseHtmlUtil == null) {
            warehouseHtmlUtil = new WarehouseHtmlUtil();
        }
        if (fileUtil == null) {
            fileUtil = FileUtil.instance();
        }
        return warehouseHtmlUtil;
    }

    @Override
    public Document getPageDocument(String url, boolean secure) {
        if (url == null || StringUtils.isBlank(url)) {
            return null;
        }
        if (url.startsWith("http")) {
            HttpPost postMethod = new HttpPost(
                    "https://whsec1.davematthewsband.com/login.asp");
            postMethod.addHeader("Accept",
                    "text/html, application/xhtml+xml, */*");
            postMethod.addHeader("Referer",
                    "https://whsec1.davematthewsband.com/login.asp");
            postMethod.addHeader("Accept-Language", "en-US");
            postMethod.addHeader("User-Agent",
                    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; " +
                            "WOW64; Trident/5.0)");
            postMethod.addHeader("Content-Type",
                    "application/x-www-form-urlencoded");
            postMethod.addHeader("Accept-Encoding", "gzip, deflate");
            postMethod.addHeader("Host", "whsec1.davematthewsband.com");
            postMethod.addHeader("Connection", "Keep-Alive");
            postMethod.addHeader("Cache-Control", "no-cache");
            postMethod.addHeader("Cookie",
                    "MemberInfo=isInternational=&MemberID=&UsrCount=" +
                            "04723365306&ExpDate=&Username=; ASPSESSIONIDQQTDRTTC=" +
                            "PKEGDEFCJBLAIKFCLAHODBHN; __utma=10963442.556285711." +
                            "1366154882.1366154882.1366154882.1; __utmb=10963442.2." +
                            "10.1366154882; __utmc=10963442; __utmz=10963442." +
                            "1366154882.1.1.utmcsr=warehouse.dmband.com|utmccn=" +
                            "(referral)|utmcmd=referral|utmcct=/; " +
                            "ASPSESSIONIDSSDRTSRA=HJBPPKFCJGEJKGNEMJJMAIPN");

            List<NameValuePair> nameValuePairs =
                    new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("the_url", ""));
            nameValuePairs.add(new BasicNameValuePair("form_action", "login"));
            nameValuePairs.add(new BasicNameValuePair("Username", "fateman"));
            nameValuePairs.add(new BasicNameValuePair("Password", "nintendo"));
            nameValuePairs.add(new BasicNameValuePair("x", "0"));
            nameValuePairs.add(new BasicNameValuePair("y", "0"));
            try {
                postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (UnsupportedEncodingException e) {
                logger.error("Unsupported encoding for " + nameValuePairs);
                e.printStackTrace();
            }
            HttpResponse response = null;
            HttpClient client = createSecureConnection();
            try {
                response = client.execute(postMethod);
            } catch (IOException e) {
                logger.error("Unable to connect to " +
                        postMethod.getURI().toASCIIString());
                e.printStackTrace();
            }
            if (response == null || (response.getStatusLine().getStatusCode() !=
                    200 && response.getStatusLine().getStatusCode() != 302)) {
                logger.info("Failed to get response from to " +
                        postMethod.getURI().toASCIIString());
            }
            HttpGet getMethod = new HttpGet(url);
            String html = null;
            if (!secure) {
                client = HttpClientBuilder.create().build();
            }
            try {
                response = client.execute(getMethod);
                html = EntityUtils.toString(response.getEntity(), "UTF-8");
                html = StringEscapeUtils.unescapeHtml4(html);
            } catch (ClientProtocolException e) {
                logger.info("Failed to connect to " +
                        getMethod.getURI().toASCIIString(), e);
            } catch (IOException e) {
                logger.info("Failed to get setlist from " +
                        getMethod.getURI().toASCIIString(), e);
            }
            if (html == null) {
                return null;
            }
            return Jsoup.parse(html);
        } else {
            return Jsoup.parse(StringEscapeUtils.unescapeHtml4(
                    fileUtil.readStringFromFile(url)));
        }
    }

}
