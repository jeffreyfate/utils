package com.jeffthefate.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DmbAlmanacUtil {

    private static DmbAlmanacUtil dmbAlmanacUtil;
    private static HtmlUtil htmlUtil;

    private final String YEAR_URL = "http://dmbalmanac.com/TourShow" +
            ".aspx?where=";

    public static DmbAlmanacUtil instance() {
        if (dmbAlmanacUtil == null) {
            dmbAlmanacUtil = new DmbAlmanacUtil();
        }
        if (htmlUtil == null) {
            htmlUtil = HtmlUtil.instance();
        }
        return dmbAlmanacUtil;
    }

    public Document getYear(String year) {
        if (StringUtils.isBlank(year) || year.length() != 4 ||
                !NumberUtils.isNumber(year)) {
            return null;
        }
        return htmlUtil.getPageDocument(YEAR_URL + year, false);
    }

    public ArrayList<String> getYearDates(String year) {
        ArrayList<String> showStrings = new ArrayList<>(0);
        Document yearDocument = getYear(year);
        if (yearDocument == null) {
            return showStrings;
        }
        Elements shows = yearDocument.select("a[href$=" + year + "]");
        String childText;
        char whitespace = 160;
        for (Element show : shows) {
            if (!show.childNodes().isEmpty()) {
                childText = StringUtils.strip(StringEscapeUtils.unescapeHtml4(
                            show.childNode(0).toString()),
                        Character.toString(whitespace));
                if (childText.matches("\\d\\d\\.\\d\\d\\.\\d\\d")) {
                    showStrings.add(childText);
                }
            }
        }
        return showStrings;
    }

    public boolean isThereAShowToday(String dateString, String year) {
        ArrayList<String> yearShows;
        Date date = new Date();
        if (year != null) {
            yearShows = getYearDates(year);
        }
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            String today = dateFormat.format(date.getTime());
            yearShows = getYearDates(today);
        }
        if (dateString == null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yy");
            //dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String today = dateFormat.format(date.getTime());
            return yearShows.contains(today);
        }
        else {
            return yearShows.contains(dateString);
        }
    }
}
