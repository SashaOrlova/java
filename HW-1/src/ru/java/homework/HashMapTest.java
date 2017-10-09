package ru.java.homework;

import static java.lang.Math.abs;
import static org.junit.Assert.assertEquals;

public class HashMapTest {

    @org.junit.Test
    public void sizeSimple() throws Exception {
        HashMap testSize = new HashMap();
        assertEquals(0, testSize.size());
    }

    @org.junit.Test
    public void sizeWitPut() throws Exception {
        HashMap testSize = new HashMap();
        testSize.put("first_key", "first_value");
        testSize.put("second_key", "second_value");
        assertEquals(2, testSize.size());
    }

    @org.junit.Test
    public void sizeWithSameKey() throws Exception {
        HashMap testSize = new HashMap();
        testSize.put("first_key", "first_value");
        testSize.put("second_key", "second_value");
        testSize.put("first_key", "third_value");
        assertEquals(2, testSize.size());
    }

    @org.junit.Test
    public void containsSimple() throws Exception {
        HashMap testContains = new HashMap();
        assertEquals(false, testContains.contains("contain?"));
    }

    @org.junit.Test
    public void containsWithPut() throws Exception {
        HashMap testContains = new HashMap();
        testContains.put("contain?", "yes");
        assertEquals(true, testContains.contains("contain?"));
    }

    @org.junit.Test
    public void containsWithPutRemove() throws Exception {
        HashMap testContains = new HashMap();
        testContains.put("contain?", "no");
        testContains.remove("contain?");
        assertEquals(false, testContains.contains("contain?"));
    }

    @org.junit.Test
    public void getEmpty() throws Exception {
        HashMap testGet = new HashMap();
        assertEquals(null, testGet.get("tryGet"));
    }

    @org.junit.Test
    public void getWithPut() throws Exception {
        HashMap testGet = new HashMap();
        testGet.put("tryGet", "yes");
        assertEquals("yes", testGet.get("tryGet"));
    }

    @org.junit.Test
    public void putWithRemove() throws Exception {
        HashMap testGet = new HashMap();
        testGet.put("tryGet", "no");
        testGet.remove("tryGet");
        assertEquals(null, testGet.get("tryGet"));
    }

    @org.junit.Test
    public void removeNull() throws Exception {
        HashMap testRemove = new HashMap();
        assertEquals(null, testRemove.get("tryRemove"));
    }

    @org.junit.Test
    public void removeWithPut() throws Exception {
        HashMap testRemove = new HashMap();
        testRemove.put("tryRemove", "yes");
        assertEquals("yes", testRemove.get("tryRemove"));
    }

    @org.junit.Test
    public void clearEmpty() throws Exception {
        HashMap testClear = new HashMap();
        testClear.clear();
        assertEquals(0, testClear.size());
    }

    @org.junit.Test
    public void clearWithPut() throws Exception {
        HashMap testClear = new HashMap();
        testClear.put("testClear","yes");
        testClear.clear();
        assertEquals(0, testClear.size());
        assertEquals( null,testClear.get("testClear"));
    }

    private class CollisionsHashMap extends HashMap {
        @Override
        protected int getHashCode(String St) {
            return abs(St.hashCode() % (2));
        }
    }
    
    @org.junit.Test
    public void CollisionsTest() throws Exception {
        CollisionsHashMap testPut = new CollisionsHashMap();
        testPut.put("first_key", "first_value");
        testPut.put("second_key", "second_value");
        testPut.put("third_key", "third_value");
        assertEquals(3, testPut.size());
        assertEquals(true, testPut.contains("first_key"));
        assertEquals(true, testPut.contains("second_key"));
        assertEquals(true, testPut.contains("third_key"));
        assertEquals(false, testPut.contains("something_else"));
        assertEquals("first_value", testPut.get("first_key"));
        assertEquals("second_value", testPut.get("second_key"));
        assertEquals("third_value", testPut.get("third_key"));
    }

    private class RebuildHashMap extends HashMap {
        @Override
        protected boolean isFull() {
            return true;
        }
    }
    
    @org.junit.Test
    public void rebuildTest() throws Exception {
        RebuildHashMap testRebuild = new RebuildHashMap();
        testRebuild.put("aaaa", "bbbb");
        assertEquals(true, testRebuild.contains("aaaa"));
        assertEquals("bbbb", testRebuild.get("aaaa"));
        testRebuild.remove("aaaa");
        assertEquals(false, testRebuild.contains("aaaa"));
    }
}