package com.jeffthefate.utils.json.geocoding;

public class Geometry {

    private Bounds bounds;
    private LatLon location;
    private String location_type;
    private Viewport viewport;

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public LatLon getLocation() {
        return location;
    }

    public void setLocation(LatLon location) {
        this.location = location;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public String toString() {
        return "bounds: " + getBounds() + ", location: " + getLocation() +
                ", location_type: " + getLocation_type() + ", viewport: " +
                getViewport();
    }

}
