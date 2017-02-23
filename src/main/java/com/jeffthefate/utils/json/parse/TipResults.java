package com.jeffthefate.utils.json.parse;

import java.util.List;

public class TipResults {

    private List<Tip> results;

    public List<Tip> getResults() { return results; }

    public void setResults(List<Tip> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
