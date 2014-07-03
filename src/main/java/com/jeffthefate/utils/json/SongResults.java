package com.jeffthefate.utils.json;

import java.util.List;

public class SongResults {

    private List<Song> results;

    public List<Song> getResults() {
        return results;
    }

    public void setResults(List<Song> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }
}
