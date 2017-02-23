package com.jeffthefate.utils.json.parse;

public class Replace {

    private String replace;
    private String createdAt;
    private String updatedAt;
    private String objectId;

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) { this.replace = replace; }

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
        return "replace: " + getReplace() + ", createdAt: " + getCreatedAt() +
                ", updatedAt: " + getUpdatedAt() + ", objectId: " +
                getObjectId();
    }

}
