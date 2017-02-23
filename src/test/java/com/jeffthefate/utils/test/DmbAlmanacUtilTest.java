package com.jeffthefate.utils.test;

import com.jeffthefate.utils.DmbAlmanacUtil;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DmbAlmanacUtilTest extends TestCase {

    private DmbAlmanacUtil dmbAlmanacUtil;

    public void setUp() throws Exception {
        super.setUp();
        dmbAlmanacUtil = DmbAlmanacUtil.instance();
    }

    public void testDmbAlmanacUtil() {
        assertNotNull("DmbAlmanacUtil is null!", dmbAlmanacUtil);
    }

    public void testGetYear() {
        Document year = dmbAlmanacUtil.getYear("2000");
        assertNotNull("2000 is null!", year);
        year = dmbAlmanacUtil.getYear("");
        assertNull("Empty isn't null!", year);
        year = dmbAlmanacUtil.getYear("00");
        assertNull("Empty isn't null!", year);
        year = dmbAlmanacUtil.getYear("yo");
        assertNull("Empty isn't null!", year);
        year = dmbAlmanacUtil.getYear(null);
        assertNull("Null isn't null!", year);
    }

    public void testGetYearDates() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(2000, Calendar.AUGUST, 6, 19, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        ArrayList<String> yearDates = dmbAlmanacUtil.getYearDates("2000",
                calendar.getTime().getTime(), 19, -1);
        assertFalse("2000 dates list isn't empty!", yearDates.isEmpty());
    }

    public void testIsThereAShowToday() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(2000, Calendar.AUGUST, 6, 12, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        assertFalse("There is a show today!",
                dmbAlmanacUtil.isThereAShowIn24Hours(0, calendar.getTime()));
        assertTrue("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowIn24Hours(19, calendar.getTime()));
        assertTrue("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowIn24Hours(-1, calendar.getTime()));
        assertTrue("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowIn24Hours(100, calendar.getTime()));
        assertTrue("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowIn24Hours(12, calendar.getTime()));
        calendar.set(2016, Calendar.MAY, 4);
        assertFalse("There is a show today!",
                dmbAlmanacUtil.isThereAShowIn24Hours(18, calendar.getTime()));
        calendar.set(2016, Calendar.MAY, 7);
        assertTrue("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowIn24Hours(19, calendar.getTime()));
        assertTrue("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowIn24Hours(20, calendar.getTime()));
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        assertTrue("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowIn24Hours(19, calendar.getTime()));
    }

    public void testGetShowCity() {
        assertEquals("Didn't get the correct city!", "George, WA",
                dmbAlmanacUtil.getShowCity("08.06.00", "2000"));
        assertEquals("Didn't get the correct city!", "West Palm Beach, FL",
                dmbAlmanacUtil.getShowCity("09.08.00", "2000"));
        assertEquals("Didn't get blank!", "",
                dmbAlmanacUtil.getShowCity("08.06.0", "2000"));
        assertEquals("Didn't get blank!", "",
                dmbAlmanacUtil.getShowCity("08.06.00", "2001"));
        assertEquals("Didn't get blank!", "",
                dmbAlmanacUtil.getShowCity("", "2000"));
        assertEquals("Didn't get null!", null,
                dmbAlmanacUtil.getShowCity(null, "2000"));
        assertEquals("Didn't get null!", null,
                dmbAlmanacUtil.getShowCity("08.06.00", null));
    }

}
