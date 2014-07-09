package com.jeffthefate.utils.json.geocoding;

import java.util.List;

public class GeocodingResponse {

    private List<Geocode> results;
    private String status;

    public List<Geocode> getResults() { return results; }

    public void setResults(List<Geocode> results) { this.results = results; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "results: " + getResults() + ", status: " + getStatus();
    }
}
