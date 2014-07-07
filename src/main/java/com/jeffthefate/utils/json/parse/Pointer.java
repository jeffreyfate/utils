package com.jeffthefate.utils.json.parse;

public class Pointer {

    private String __type;
    private String className;
    private String objectId;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "__type: " + get__type() + ", className: " + getClassName() +
                ", objectId: " + getObjectId();
    }

}
