package com.jeffthefate.utils.json;

public class Venue {

    private String createdAt;
    private String updatedAt;
    private String objectId;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    private String name;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "city: " + getCity() + ", name: " + getName() + ", objectId: " +
                getObjectId() + ", createdAt: " + getCreatedAt() +
                ", updatedAt: " + getUpdatedAt();
    }

}
