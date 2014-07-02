package com.jeffthefate.utils.json;

import java.util.List;

/**
 * Created by Jeff on 6/19/2014.
 */
public class SongResults {

    private List<Song> results;

    public List<Song> getResults() {
        return results;
    }

    public void setResults(List<Setlist> Song) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }
}
