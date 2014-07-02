package com.jeffthefate.utils.json;

public class Created {

    private String objectId;
    private String createdAt;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "objectId: " + getObjectId() + ", createdAt: " + getCreatedAt();
    }

}
