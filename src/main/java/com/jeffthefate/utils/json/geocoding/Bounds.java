package com.jeffthefate.utils.json.geocoding;

public class Bounds {

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
