package com.jeffthefate.utils.test;

import com.jeffthefate.utils.FileUtil;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FIleUtilTest extends TestCase {

    private FileUtil fileUtil = FileUtil.instance();

    public void testSaveReadFile() {
        List<Object> testList = new ArrayList<>();
        testList.add("test");
        assertTrue("Save failed!", fileUtil.saveListToFile("testFile",
                testList));
        List<Object> resultList = fileUtil.readListFromFile("testFile");
        assertNotNull("List read is null!", resultList);
        assertTrue("Lists are not the same!", resultList.containsAll(testList)
                && testList.containsAll(resultList));
    }

    public void testSaveStringHashMapToFile() {
        HashMap<Object, Object> testMap = new HashMap<>();
        testMap.put("test", "test");
        fileUtil.saveHashMapToFile("testSaveHashMap", testMap);
        HashMap<Object, Object> result = fileUtil.readHashMapFromFile
                ("testSaveHashMap");
        assertEquals("Maps not equal!", testMap, result);
        testMap.clear();
        fileUtil.saveHashMapToFile("testSaveHashMap", testMap);
        result = fileUtil.readHashMapFromFile("testSaveHashMap");
        assertEquals("Maps not equal!", testMap, result);
    }

    public void testSaveIntegerHashMapToFile() {
        HashMap<Object, Object> testMap = new HashMap<>();
        testMap.put(1, 2);
        fileUtil.saveHashMapToFile("testSaveHashMap", testMap);
        HashMap<Object, Object> result = fileUtil.readHashMapFromFile
                ("testSaveHashMap");
        assertEquals("Maps not equal!", testMap, result);
    }

    public void testSaveStringListToFile() {
        List<Object> testList = new ArrayList<>(0);
        testList.add("test");
        fileUtil.saveListToFile("testSaveList", testList);
        List<Object> result = fileUtil.readListFromFile("testSaveList");
        assertEquals("Lists not equal!", testList, result);
        testList.clear();
        fileUtil.saveListToFile("testSaveList", testList);
        result = fileUtil.readListFromFile("testSaveList");
        assertEquals("Lists not equal!", testList, result);
    }

    public void testSaveIntegerListToFile() {
        List<Object> testList = new ArrayList<>(0);
        testList.add(12);
        fileUtil.saveListToFile("testSaveList", testList);
        List<Object> result = fileUtil.readListFromFile("testSaveList");
        assertEquals("Lists not equal!", testList, result);
    }

    public void testWriteStringToFile() {
        fileUtil.writeStringToFile("test", "testWriteString");
        assertEquals("Strings not equal!", "test",
                fileUtil.readStringFromFile("testWriteString"));
    }

    public void testAppendListToFile() {
        List<Object> testList1 = new ArrayList<>(0);
        testList1.add("test");
        fileUtil.saveListToFile("testAppendList", testList1);
        List<Object> testList2 = new ArrayList<Object>(0);
        testList2.add(23);
        fileUtil.appendListToFile(testList2, "testAppendList");
        testList1.addAll(testList2);
        assertEquals("Lists not equal!", testList1,
                fileUtil.readListFromFile("testAppendList"));
    }

    public void testAppendStringToFile() {
        String testString = "test";
        fileUtil.writeStringToFile(testString, "testAppendString");
        fileUtil.appendStringToFile("new", "testAppendString");
        assertEquals("Strings not equal!", testString + "new\n",
                fileUtil.readStringFromFile("testAppendString"));
    }

    public void testGetListOfFiles() {
        ArrayList<String> fileList = fileUtil.getListOfFiles
                ("src/test/resources/", ".png");
        ArrayList<String> expected = new ArrayList<String>(0);
        expected.add("testCompareImagesBaseline1.png");
        expected.add("testCompareImagesNew1.png");
        expected.add("testCompareImagesOpposite1.png");
        assertEquals("File lists not equal!", expected, fileList);
    }

}
