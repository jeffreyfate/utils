package com.jeffthefate.utils.test;

import com.jeffthefate.utils.DmbAlmanacUtil;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

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
        ArrayList<String> yearDates = dmbAlmanacUtil.getYearDates("2000");
        assertFalse("2000 dates list is empty!", yearDates.isEmpty());
        assertTrue("2000 dates doesn't contain 08.06.00!",
                yearDates.contains("08.06.00"));
        assertTrue("2000 dates size is wrong!", yearDates.size() == 67);
        yearDates = dmbAlmanacUtil.getYearDates("2014");
        assertFalse("2014 dates list is empty!", yearDates.isEmpty());
        assertTrue("2014 dates doesn't contain 08.31.14!",
                yearDates.contains("08.31.14"));
        assertTrue("2014 dates size is wrong!", yearDates.size() == 53);
    }

    public void testIsThereAShowToday() {
        assertFalse("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowToday(null, null));
        assertTrue("There is a show today!",
                dmbAlmanacUtil.isThereAShowToday("08.06.00", "2000"));
        assertFalse("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowToday("01.01.00", "2000"));
        assertFalse("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowToday("", ""));
        assertFalse("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowToday("", "2000"));
        assertFalse("There isn't a show today!",
                dmbAlmanacUtil.isThereAShowToday("08.06.00", ""));
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
