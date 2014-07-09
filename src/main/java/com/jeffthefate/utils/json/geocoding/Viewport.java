package com.jeffthefate.utils.json.geocoding;

/**
 * Created by Jeff on 7/8/2014.
 */
public class Viewport {

    private LatLon northeast;
    private LatLon southwest;

    public LatLon getNortheast() {
        return northeast;
    }

    public void setNortheast(LatLon northeast) {
        this.northeast = northeast;
    }

    public LatLon getSouthwest() {
        return southwest;
    }

    public void setSouthwest(LatLon southwest) {
        this.southwest = southwest;
    }

    @Override
    public String toString() {
        return "northeast: " + getNortheast() + ", southwest: " +
                getSouthwest();
    }

}
