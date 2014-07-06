package com.jeffthefate.utils.json;

import java.util.List;

public class CredentialResults {

    private List<Credential> results;

    public List<Credential> getResults() {
        return results;
    }

    public void setResults(List<Credential> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "results: " + getResults();
    }

}
