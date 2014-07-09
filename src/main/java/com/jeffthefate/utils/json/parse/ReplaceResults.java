package com.jeffthefate.utils.json.parse;

import java.util.List;

public class ReplaceResults {

    private List<Replace> results;

    public List<Replace> getResults() { return results; }

    public void setResults(List<Replace> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
