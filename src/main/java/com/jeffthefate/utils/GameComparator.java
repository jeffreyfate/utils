package com.jeffthefate.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Reverses order, putting the highest values at the top of the TreeMap.
 */
public class GameComparator implements Comparator<Object> {

    Map<Object, Object> base;

    public GameComparator(Map<Object, Object> base) {
        this.base = base;
    }

    public int compare(Object a, Object b) {
        if ((Integer) base.get(a) < (Integer) base.get(b)) {
            return 1;
        } else if (base.get(a).equals(base.get(b))) {
            return ((String)a).compareToIgnoreCase((String)b);
        } else {
            return -1;
        }
    }
}
