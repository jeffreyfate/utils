package com.jeffthefate.utils.json;

import java.util.List;

public class VenueResults {

    private List<Venue> results;

    public List<Venue> getResults() {
        return results;
    }

    public void setResults(List<Venue> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
