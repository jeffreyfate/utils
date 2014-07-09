package com.jeffthefate.utils.json.parse;

public class Tip {

    private String tip;
    private String createdAt;
    private String updatedAt;
    private String objectId;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) { this.tip = tip; }

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
        return "tip: " + getTip() + ", createdAt: " + getCreatedAt() +
                ", updatedAt: " + getUpdatedAt() + ", objectId: " +
                getObjectId();
    }

}
