package com.jeffthefate.utils.test;

import com.jeffthefate.utils.GeocodingUtil;
import com.jeffthefate.utils.json.geocoding.LatLon;
import junit.framework.TestCase;

public class GeocodingUtilTest extends TestCase {

    private GeocodingUtil geocodingUtil;

    private final String CREDS = "D:\\parseCreds";

    public void setUp() throws Exception {
        super.setUp();
        geocodingUtil = GeocodingUtil.instance();
    }

    public void testGetCityLatLon() {
        LatLon latLon = geocodingUtil.getCityLatLon("George, WA");
        assertEquals("Latitude not correct!", 47.07902f, latLon.getLat());
        assertEquals("Longitude not correct!", -119.85588f, latLon.getLng());
        latLon = geocodingUtil.getCityLatLon("");
        assertNull("Latlon object not null!", latLon);
        latLon = geocodingUtil.getCityLatLon(null);
        assertNull("Latlon object not null!", latLon);
        latLon = geocodingUtil.getCityLatLon("Sao Paulo, Brazil");
        assertNull("Latlon object not null!", latLon);
        latLon = geocodingUtil.getCityLatLon("São Paulo, Brazil");
        assertNotNull("Latlon object is null!", latLon);
    }

}
