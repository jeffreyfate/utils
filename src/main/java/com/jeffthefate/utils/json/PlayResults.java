package com.jeffthefate.utils.json;

import java.util.List;

/**
 * Created by Jeff on 6/19/2014.
 */
public class PlayResults {

    private List<Play> results;

    public List<Play> getResults() {
        return results;
    }

    public void setResults(List<Play> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
