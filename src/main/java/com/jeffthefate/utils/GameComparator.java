package com.jeffthefate.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Reverses order, putting the highest values at the top of the TreeMap.
 */
public class GameComparator implements Comparator<String> {

    Map<String, Integer> base;
    public GameComparator(Map<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        if (base.get(a) < base.get(b)) {
            return 1;
        } else if (base.get(a) == base.get(b)) {
            return a.compareToIgnoreCase(b);
        } else {
            return -1;
        }
    }
}
