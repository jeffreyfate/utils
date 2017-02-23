package com.jeffthefate.utils.json.parse;

import java.util.List;

public class MatchResults {

    private List<Match> results;

    public List<Match> getResults() { return results; }

    public void setResults(List<Match> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
