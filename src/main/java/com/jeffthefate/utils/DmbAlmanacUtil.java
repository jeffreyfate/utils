package com.jeffthefate.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DmbAlmanacUtil {

    private static DmbAlmanacUtil dmbAlmanacUtil;
    private static HtmlUtil htmlUtil;

    private final String YEAR_URL = "http://dmbalmanac.com/TourShow" +
            ".aspx?where=";
    private final char WHITESPACE = 160;

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
        return htmlUtil.getPageDocument(YEAR_URL + year, false, null, null);
    }

    public ArrayList<String> getYearDates(String year) {
        ArrayList<String> showStrings = new ArrayList<>(0);
        Document yearDocument = getYear(year);
        if (yearDocument == null) {
            return showStrings;
        }
        Elements shows = yearDocument.select("a[href$=" + year + "]");
        Node node;
        String childText;
        for (Element show : shows) {
            if (!show.childNodes().isEmpty()) {
                node = show.childNode(0);
                if (!node.childNodes().isEmpty()) {
                    node = node.childNode(0);
                }
                childText = StringUtils.strip(StringEscapeUtils.unescapeHtml4(
                            node.toString()), Character.toString(WHITESPACE));
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
            yearShows = getYearDates(dateFormat.format(date.getTime()));
        }
        if (dateString == null) {
            return yearShows.contains(convertDateToAlmanacFormat(date));
        }
        else {
            return yearShows.contains(dateString);
        }
    }

    public String convertDateToAlmanacFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yy");
        return dateFormat.format(date.getTime());
    }

    public String getShowCity(String showDate, String year) {
        if (showDate == null || year == null) {
            return null;
        }
        Document yearDocument = getYear(year);
        if (yearDocument == null) {
            return null;
        }
        Elements shows = yearDocument.select("tr[bgcolor=#1A2B4C]");
        shows.addAll(yearDocument.select("tr[bgcolor=#102142]"));
        Node node;
        String date;
        for (Element show : shows) {
            node = show.select("a[href$=" + year + "]").first().childNode(0);
            if (!node.childNodes().isEmpty()) {
                node = node.childNode(0);
            }
            date = StringUtils.strip(StringEscapeUtils.unescapeHtml4(
                    node.toString()), Character.toString(WHITESPACE));
            if (date.equals(showDate)){
                return show.select("span[class=detail3]").text();
            }
        }
        return "";
    }
}
