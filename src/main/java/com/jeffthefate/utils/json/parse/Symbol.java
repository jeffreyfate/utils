package com.jeffthefate.utils.json.parse;

public class Symbol {

    private String symbol;
    private String createdAt;
    private String updatedAt;
    private String objectId;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
        return "symbol: " + getSymbol() + ", createdAt: " + getCreatedAt() +
                ", updatedAt: " + getUpdatedAt() + ", objectId: " +
                getObjectId();
    }
}
