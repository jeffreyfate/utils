package com.jeffthefate.utils.test;

import com.jeffthefate.utils.GeocodingUtil;
import com.jeffthefate.utils.TimeZoneUtil;
import com.jeffthefate.utils.json.geocoding.LatLon;
import junit.framework.TestCase;

public class TimeZoneUtilTest extends TestCase {

    private GeocodingUtil geocodingUtil;
    private TimeZoneUtil timeZoneUtil;

    private final String CREDS = "D:\\parseCreds";

    public void setUp() throws Exception {
        super.setUp();
        geocodingUtil = GeocodingUtil.instance();
        timeZoneUtil = TimeZoneUtil.instance();
    }

    public void testGetTimeOffset() {
        LatLon latLon = geocodingUtil.getCityLatLon("George, WA");
        long offset = timeZoneUtil.getTimeOffset(latLon);
        assertEquals("Offset not correct!", -25200, offset);
        latLon = geocodingUtil.getCityLatLon("Portland, OR");
        offset = timeZoneUtil.getTimeOffset(latLon);
        assertEquals("Offset not correct!", -25200, offset);
        latLon = geocodingUtil.getCityLatLon("Elkhorn, WI");
        offset = timeZoneUtil.getTimeOffset(latLon);
        assertEquals("Offset not correct!", -18000, offset);
        latLon = geocodingUtil.getCityLatLon("London");
        offset = timeZoneUtil.getTimeOffset(latLon);
        assertEquals("Offset not correct!", 3600, offset);
        latLon = geocodingUtil.getCityLatLon("Brisbane");
        offset = timeZoneUtil.getTimeOffset(latLon);
        assertEquals("Offset not correct!", 36000, offset);
    }

}
