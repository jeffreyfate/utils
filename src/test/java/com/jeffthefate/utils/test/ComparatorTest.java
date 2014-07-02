package com.jeffthefate.utils.test;

import com.jeffthefate.utils.GameComparator;
import junit.framework.TestCase;

import java.util.HashMap;

/**
 * Created by Jeff on 6/18/2014.
 */
public class ComparatorTest extends TestCase {

    public void testGameComparator() {
        HashMap<String, Integer> testMap = new HashMap<String, Integer>();
        testMap.put("test1", 1);
        testMap.put("test2", 2);
        testMap.put("test3", 1);
        GameComparator gameComparator = new GameComparator(testMap);
        assertTrue("Compare not correct!", gameComparator.compare("test1",
                "test2") > 0);
        assertTrue("Compare not correct!", gameComparator.compare("test1",
                "test3") < 0);
        assertTrue("Compare not correct!", gameComparator.compare("test2",
                "test1") < 0);
    }

}
