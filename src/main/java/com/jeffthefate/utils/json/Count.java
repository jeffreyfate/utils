package com.jeffthefate.utils.json;

/**
 * Created by Jeff on 6/28/2014.
 */
public class Count {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "count: " + getCount();
    }
}
