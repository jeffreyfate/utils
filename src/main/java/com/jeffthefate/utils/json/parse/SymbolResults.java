package com.jeffthefate.utils.json.parse;

import java.util.List;

public class SymbolResults {

    private List<Symbol> results;

    public List<Symbol> getResults() { return results; }

    public void setResults(List<Symbol> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
