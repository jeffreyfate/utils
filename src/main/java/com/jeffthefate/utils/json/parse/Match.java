package com.jeffthefate.utils.json.parse;

import java.util.List;

public class Match {

    private List<String> match;
    private String createdAt;
    private String updatedAt;
    private String objectId;

    public List<String> getMatch() {
        return match;
    }

    public void setMatch(List<String> match) {
        this.match = match;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "match: " + getMatch() + ", createdAt: " + getCreatedAt() +
                ", updatedAt: " + getUpdatedAt() + ", objectId: " +
                getObjectId();
    }

}
