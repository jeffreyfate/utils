package com.jeffthefate.utils.test;

import com.jeffthefate.utils.GameComparator;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ComparatorTest extends TestCase {

    public void testGameComparator() {
        HashMap<Object, Object> testMap = new HashMap<>();
        testMap.put("test1", 1);
        testMap.put("test2", 2);
        testMap.put("test3", 1);
        GameComparator gameComparator = new GameComparator(testMap);
        TreeMap<Object, Object> sortedMap = new TreeMap<>(
                gameComparator);
        int currValue = Integer.MAX_VALUE;
        for (Map.Entry<Object, Object> test : sortedMap.entrySet()) {
            assertTrue("Compare not correct!", currValue >
                    (Integer) test.getValue());
            currValue = (Integer) test.getValue();
        }
    }

}
