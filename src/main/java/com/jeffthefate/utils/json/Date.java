package com.jeffthefate.utils.json;

public class Date {

    private String __type;
    private String iso;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    @Override
    public String toString() {
        return "__type: " + get__type() + ", iso: " + getIso();
    }

}
