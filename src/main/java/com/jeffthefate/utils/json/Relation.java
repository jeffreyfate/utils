package com.jeffthefate.utils.json;

/**
 * Created by Jeff on 6/19/2014.
 */
public class Relation {

    private String __type;
    private String className;

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

    @Override
    public String toString() {
        return "__type: " + get__type() + ", className: " + getClassName();
    }
}
