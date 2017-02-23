package com.jeffthefate.utils.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"offset", "nextPage", "totalObjects"})
public class CredentialResults {

    private List<Credential> data;

    public List<Credential> getData() {
        return data;
    }

    public void setData(List<Credential> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "data: " + getData();
    }

}
