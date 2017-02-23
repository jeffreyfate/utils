package com.jeffthefate.utils.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"__updated__meta", "___class", "ownerId", "__meta"})
public class Credential {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
    private String created;
    private String updated;
    private String objectId;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString(){
        return "name: " + getName() + ", value: " + getValue() +
                ", created: " + getCreated() + ", updated: " +
                getUpdated() + ", objectId: " + getObjectId();
    }
}
