package com.jeffthefate.utils.json.geocoding;

import java.util.List;

public class GeocodingResponse {

    private String exclude_from_slo;
    private List<Geocode> results;
    private String status;

    public List<Geocode> getResults() { return results; }

    public void setResults(List<Geocode> results) { this.results = results; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getExclude_from_slo() { return exclude_from_slo; }

    public void setExclude_from_slo(String exclude_from_slo) { this.exclude_from_slo = exclude_from_slo; }

    @Override
    public String toString() {
        return "results: " + getResults() + ", status: " + getStatus();
    }
}
