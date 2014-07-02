package com.jeffthefate.utils.json;

import java.util.List;

/**
 * Created by Jeff on 6/30/2014.
 */
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
