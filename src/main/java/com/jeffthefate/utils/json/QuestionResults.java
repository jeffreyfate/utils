package com.jeffthefate.utils.json;

import java.util.List;

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
