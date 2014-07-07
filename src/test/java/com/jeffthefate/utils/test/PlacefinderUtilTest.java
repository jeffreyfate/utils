package com.jeffthefate.utils.test;

import com.jeffthefate.utils.PlacefinderUtil;
import junit.framework.TestCase;

public class PlacefinderUtilTest extends TestCase {

    private PlacefinderUtil placefinderUtil;

    private final String CREDS = "D:\\parseCreds";

    public void setUp() throws Exception {
        super.setUp();
        placefinderUtil = PlacefinderUtil.instance();
    }
    public void testGetPlaceTimeZone() {
        assertEquals("Time zone incorrect!", "America/Los_Angeles",
                placefinderUtil.getPlaceTimeZone(true, "George, WA", CREDS));
        assertEquals("Time zone incorrect!", "America/New_York",
                placefinderUtil.getPlaceTimeZone(true, "Philadelphia, PA",
                        CREDS));
        assertNull("Time zone not null!", placefinderUtil.getPlaceTimeZone(
                true, null, CREDS));
        assertNull("Time zone not null!", placefinderUtil.getPlaceTimeZone(true,
                "", CREDS));
    }

}
