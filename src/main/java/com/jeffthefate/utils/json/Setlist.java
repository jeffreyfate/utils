package com.jeffthefate.utils.json;

public class Setlist {

    private Pointer venue;
    private String set;
    private Date setDate;
    private Relation plays;
    private String createdAt;
    private String updatedAt;
    private String objectId;

    public Pointer getVenue() {
        return venue;
    }

    public void setVenue(Pointer venue) {
        this.venue = venue;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public Date getSetDate() {
        return setDate;
    }

    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    public Relation getPlays() {
        return plays;
    }

    public void setPlays(Relation plays) {
        this.plays = plays;
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
    public String toString(){
        return "venue: " + getVenue() + ", set: " + getSet() + ", setDate: " +
                getSetDate() + ", plays: " + getPlays() + ", createdAt: " +
                getCreatedAt() + ", updatedAt: " + getUpdatedAt() + ", " +
                "objectId: " + getObjectId();
    }
}
