package com.jeffthefate.utils.json.parse;

import java.util.List;

public class CountResults {

    private List<Count> results;

    public List<Count> getResults() {
        return results;
    }

    public void setResults(List<Count> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
