package ru.java.homework;

import static org.junit.Assert.assertEquals;

public class HashMapTest {

    @org.junit.Test
    public void sizeSimple() throws Exception {
        HashMap test_size = new HashMap();
        assertEquals(0, test_size.size());
    }

    @org.junit.Test
    public void sizeWitPut() throws Exception {
        HashMap test_size = new HashMap();
        test_size.put("first_key", "first_value");
        test_size.put("second_key", "second_value");
        assertEquals(2, test_size.size());
    }

    @org.junit.Test
    public void sizeWithSameKey() throws Exception {
        HashMap test_size = new HashMap();
        test_size.put("first_key", "first_value");
        test_size.put("second_key", "second_value");
        test_size.put("first_key", "third_value");
        assertEquals(2, test_size.size());
    }

    @org.junit.Test
    public void containsSimple() throws Exception {
        HashMap test_contains = new HashMap();
        assertEquals(false, test_contains.contains("contain?"));
    }

    @org.junit.Test
    public void containsWithPut() throws Exception {
        HashMap test_contains = new HashMap();
        test_contains.put("contain?", "yes");
        assertEquals(true, test_contains.contains("contain?"));
    }

    @org.junit.Test
    public void containsWithPutRemove() throws Exception {
        HashMap test_contains = new HashMap();
        test_contains.put("contain?", "no");
        test_contains.remove("contain?");
        assertEquals(false, test_contains.contains("contain?"));
    }

    @org.junit.Test
    public void getEmpty() throws Exception {
        HashMap test_get = new HashMap();
        assertEquals(null, test_get.get("try_get"));
    }

    @org.junit.Test
    public void getWithPut() throws Exception {
        HashMap test_get = new HashMap();
        test_get.put("try_get", "yes");
        assertEquals("yes", test_get.get("try_get"));
    }

    @org.junit.Test
    public void putWithRemove() throws Exception {
        HashMap test_get = new HashMap();
        test_get.put("try_get", "no");
        test_get.remove("try_get");
        assertEquals(null, test_get.get("try_get"));
    }

    @org.junit.Test
    public void removeNull() throws Exception {
        HashMap test_remove = new HashMap();
        assertEquals(null, test_remove.get("try_remove"));
    }

    @org.junit.Test
    public void removeWithPut() throws Exception {
        HashMap test_remove = new HashMap();
        test_remove.put("try_remove", "yes");
        assertEquals("yes", test_remove.get("try_remove"));
    }

    @org.junit.Test
    public void clearEmpty() throws Exception {
        HashMap test_clear = new HashMap();
        test_clear.clear();
        assertEquals(0, test_clear.size());
    }

    @org.junit.Test
    public void clearWithPut() throws Exception {
        HashMap test_clear = new HashMap();
        test_clear.put("test_clear","yes");
        test_clear.clear();
        assertEquals(0, test_clear.size());
        assertEquals( null,test_clear.get("test_clear"));
    }
}