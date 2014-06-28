package com.jeffthefate.utils.json;

import java.util.List;

/**
 * Created by Jeff on 6/28/2014.
 */
public class QuestionResults {

    private List<Question> results;

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
