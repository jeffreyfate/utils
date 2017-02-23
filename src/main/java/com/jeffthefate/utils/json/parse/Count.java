package com.jeffthefate.utils.json.parse;

import java.util.List;

public class Count {

    private int count;

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    private List<String> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "count: " + getCount();
    }
}
