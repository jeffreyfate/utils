package com.jeffthefate.utils.json;

import java.util.List;

/**
 * Created by Jeff on 6/19/2014.
 */
public class SetlistResults {

    private List<Setlist> results;

    public List<Setlist> getResults() {
        return results;
    }

    public void setResults(List<Setlist> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
