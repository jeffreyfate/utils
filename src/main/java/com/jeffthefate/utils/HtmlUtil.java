package com.jeffthefate.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.SSLContext;
import java.io.IOException;

public class HtmlUtil {

    private static HtmlUtil htmlUtil;
    private static FileUtil fileUtil;

    private Logger logger = Logger.getLogger(HtmlUtil.class);

    public static HtmlUtil instance() {
        if (htmlUtil == null) {
            htmlUtil = new HtmlUtil();
        }
        if (fileUtil == null) {
            fileUtil = FileUtil.instance();
        }
        return htmlUtil;
    }

    /**************************************************************************/
    /*                              DOM Fetching                              */
    /**************************************************************************/
    public HttpClient createSecureConnection() {
        // SSL context for secure connections can be created either based on
        // system or application specific properties.
        SSLContext sslcontext = SSLContexts.createSystemDefault();
        // Use custom hostname verifier to customize SSL hostname verification.
        X509HostnameVerifier hostnameVerifier =
                new BrowserCompatHostnameVerifier();
        Registry<ConnectionSocketFactory> socketFactoryRegistry =
                RegistryBuilder.<ConnectionSocketFactory>create().register("https",
                        new SSLConnectionSocketFactory(sslcontext,
                                hostnameVerifier))
                .build();

        PoolingHttpClientConnectionManager mgr =
                new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        return HttpClientBuilder.create().setConnectionManager(mgr).build();
    }

    public Document getPageDocument(String url, boolean secure) {
        if (url == null || StringUtils.isBlank(url)) {
            return null;
        }
        if (url.startsWith("http")) {
            HttpResponse response = null;
            HttpClient httpClient;
            HttpGet getMethod = new HttpGet(url);
            String html = null;
            if (secure) {
                httpClient = createSecureConnection();
            }
            else {
                httpClient = HttpClientBuilder.create().build();
            }
            try {
                response = httpClient.execute(getMethod);
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
        }
        else {
            return Jsoup.parse(StringEscapeUtils.unescapeHtml4(
                    fileUtil.readStringFromFile(url)));
        }
    }
}
