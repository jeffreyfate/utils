package com.jeffthefate.utils;

import com.jeffthefate.utils.json.geocoding.LatLon;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DmbAlmanacUtil {

    private static DmbAlmanacUtil dmbAlmanacUtil;
    private static HtmlUtil htmlUtil;
    private static GeocodingUtil geocodingUtil;
    private static TimeZoneUtil timeZoneUtil;

    private final String YEAR_URL = "http://dmbalmanac.com/TourShow" +
            ".aspx?where=";
    private final char WHITESPACE = 160;

    private static Logger logger = Logger.getLogger(DmbAlmanacUtil.class);

    public static DmbAlmanacUtil instance() {
        if (dmbAlmanacUtil == null) {
            dmbAlmanacUtil = new DmbAlmanacUtil();
        }
        if (htmlUtil == null) {
            htmlUtil = HtmlUtil.instance();
        }
        if (geocodingUtil == null) {
            geocodingUtil = GeocodingUtil.instance();
        }
        if (timeZoneUtil == null) {
            timeZoneUtil = TimeZoneUtil.instance();
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

    /**
     * Grabs all the shows known for the given year, from dmbalmanac.com
     * @param year full year number looking under
     * @return each show location, as specified by dmbalmanac.com
     */
    public ArrayList<String> getYearDates(String year, long millisStart,
                                          int hour, int max) {
        ArrayList<String> showStrings = new ArrayList<>(0);
        Document yearDocument = getYear(year);
        if (yearDocument == null) {
            return showStrings;
        }
        Elements shows = yearDocument.select("a[href$=" + year + "]");
        Node node;
        String childText;
        Date date;
        Calendar calendar;
        long twelveHoursBefore;
        long twelveHoursAfter;
        for (Element show : shows) {
            if (!show.childNodes().isEmpty()) {
                node = show.childNode(0);
                if (!node.childNodes().isEmpty()) {
                    node = node.childNode(0);
                }
                childText = StringUtils.strip(StringEscapeUtils.unescapeHtml4(
                            node.toString()), Character.toString(WHITESPACE));
                if (childText.matches("\\d\\d\\.\\d\\d\\.\\d\\d")) {
                    // If the show is 12 hours before or after current time
                    date = convertAlmanacFormatToDate(childText);
                    calendar = new GregorianCalendar(
                            TimeZone.getTimeZone("UTC"));
                    calendar.setTimeInMillis(date.getTime());
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.add(Calendar.HOUR_OF_DAY, -12);
                    twelveHoursBefore = calendar.getTimeInMillis();
                    calendar.add(Calendar.HOUR_OF_DAY, 24);
                    twelveHoursAfter = calendar.getTimeInMillis();
                    if (millisStart >= twelveHoursBefore &&
                            millisStart <= twelveHoursAfter) {
                        showStrings.add(childText);
                    }
                }
            }
            if (max > 0 && showStrings.size() >= max) {
                break;
            }
        }
        return showStrings;
    }

    /**
     * Checks with dmbalmanac.com to see if there is a show scheduled for
     * in the next 24 hours, based on UTC
     *
     * @return
     */
    public boolean isThereAShowIn24Hours(int showStartHour, Date now) {
        // Convert date to UTC
        long millisNow = now.getTime();
        if (showStartHour < 0 || showStartHour > 23) {
            showStartHour = 19;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        // Get the current year, to use on dmbalmanac
        String year = dateFormat.format(now.getTime());
        // Grab all the dates in the year
        ArrayList<String> yearShows = getYearDates(year, millisNow,
                showStartHour, 1);
        String showCity;
        LatLon latLon;
        long offset;
        Date almanacDate;
        Calendar showTime;
        boolean foundShow;
        long timeOffset;
        for (String yearShow : yearShows) {
            showCity = dmbAlmanacUtil.getShowCity(yearShow, year);
            if (showCity.contains("ESP")) {
                showCity = showCity.replace("ESP", "ES");
            }
            if (showCity.contains("ITA")) {
                showCity = showCity.replace("ITA", "IT");
            }
            if (showCity.contains("BEL")) {
                showCity = showCity.replace("BEL", "Belgium");
            }
            if (showCity.contains("IRL")) {
                showCity = showCity.replace("IRL", "Ireland");
            }
            latLon = geocodingUtil.getCityLatLon(showCity);
            try {
                offset = timeZoneUtil.getTimeOffset(latLon);
                // Now that we have all the offset, we can see if it will
                // happen within 24 hours
                almanacDate = convertAlmanacFormatToDate(yearShow);
                showTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                showTime.setTime(almanacDate);
                showTime.set(Calendar.HOUR_OF_DAY, showStartHour);
                showTime.set(Calendar.MINUTE, 0);
                showTime.set(Calendar.SECOND, 0);
                showTime.set(Calendar.MILLISECOND, 0);
                // If the difference between now and expected show time is 24
                // hours or less, we return true
                timeOffset = showTime.getTimeInMillis() - (offset * 1000) -
                        millisNow;
                foundShow = timeOffset > 0 && timeOffset <= 24*60*60*1000;
                if (foundShow) {
                    return true;
                }
            } catch (InvalidParameterException e) {
                return false;
            }
        }
        return false;
    }

    public String convertDateToAlmanacFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yy");
        return dateFormat.format(date.getTime());
    }

    public Date convertAlmanacFormatToDate(String formattedDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
