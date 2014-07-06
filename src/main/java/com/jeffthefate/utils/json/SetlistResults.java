package com.jeffthefate.utils.json;

import java.util.List;

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
