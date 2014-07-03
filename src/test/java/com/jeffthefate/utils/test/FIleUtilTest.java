package com.jeffthefate.utils.test;

import com.jeffthefate.utils.FileUtil;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class FIleUtilTest extends TestCase {

    private FileUtil fileUtil = FileUtil.instance();

    public void testSaveReadFile() {
        List<Object> testList = new ArrayList<>();
        testList.add("test");
        assertTrue("Save failed!", fileUtil.saveListToFile("testFile", testList));
        List<Object> resultList = fileUtil.readListFromFile("testFile");
        assertNotNull("List read is null!", resultList);
        assertTrue("Lists are not the same!", resultList.containsAll(testList)
                && testList.containsAll(resultList));
    }

}
