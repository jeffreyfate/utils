package com.jeffthefate.utils.json;

/**
 * Created by Jeff on 6/19/2014.
 */
public class Song {

    private String title;
    private String createdAt;
    private String updatedAt;
    private String objectId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "title: " + getTitle() + ", createdAt: " + getCreatedAt() +
                ", updatedAt: " + getUpdatedAt() + ", objectId: " +
                getObjectId();
    }

}
