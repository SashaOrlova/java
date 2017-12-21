package ru.java.TreeSet;

import org.junit.Test;
import ru.java.MyTreeSet.MyTreeSet;
import java.util.Iterator;

import static org.junit.Assert.*;

public class TreeSetTest {
    @Test
    public void descendingIterator() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(8);
        test1.add(-4);
        test1.add(3);
        Iterator i = test1.descendingIterator();
        assertEquals(8, i.next());
        assertEquals(3, i.next());
        assertEquals(1, i.next());
        assertEquals(-4, i.next());
    }

    @Test
    public void descendingSet() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(8);
        test1.add(-4);
        test1.add(3);
        MyTreeSet<Integer> test2 = test1.descendingSet();
        assertTrue(test2.contains(1));
        assertTrue(test2.contains(-4));
        assertTrue(test2.contains(3));
        assertTrue(test2.contains(8));
        assertFalse(test2.contains(-9));
        assertEquals((Integer)(-4), test2.last());
        assertEquals((Integer)(8), test2.first());
        Iterator i = test2.iterator();
        assertEquals(8, i.next());
        assertEquals(3, i.next());
        assertEquals(1, i.next());
        assertEquals(-4, i.next());
        assertEquals((Integer)(-4), test2.higher(1));
        assertEquals((Integer)(1), test2.floor(1));
        assertEquals((Integer)(-4), test2.lower(-100));
        assertTrue(null == test2.ceiling(-5));
    }

    @Test
    public void first() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(8);
        test1.add(-4);
        test1.add(3);
        assertEquals((Integer)(-4), test1.first());
        test1.remove(-4);
        assertEquals((Integer)1, test1.first());
    }

    @Test
    public void last() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(8);
        test1.add(-4);
        test1.add(3);
        assertEquals((Integer)8, test1.last());
        test1.remove(8);
        assertEquals((Integer)3, test1.last());
    }

    @Test
    public void lower() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(3);
        test1.add(-4);
        test1.add(10);
        assertEquals((Integer)(-4), test1.lower(1));
        assertEquals((Integer)(-4), test1.lower(2));
        assertTrue(null == test1.lower(-4));
    }

    @Test
    public void floor() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(3);
        test1.add(-4);
        test1.add(10);
        assertEquals((Integer)(-4), test1.floor(-4));
        assertEquals((Integer)(-4), test1.floor(-1));
        assertTrue(null == test1.floor(-5));
    }

    @Test
    public void ceiling() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(3);
        test1.add(-4);
        test1.add(10);
        assertEquals((Integer)(-4), test1.higher(-100));
        assertEquals((Integer)(1), test1.higher(1));
        assertTrue(null == test1.higher(11));
    }

    @Test
    public void higher() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(3);
        test1.add(-4);
        test1.add(10);
        assertEquals((Integer)(-4), test1.higher(-100));
        assertEquals((Integer)(1), test1.higher(-4));
        assertTrue(null == test1.higher(10));
    }

    @Test
    public void iterator() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(2);
        test1.add(7);
        test1.add(5);
        Iterator i = test1.iterator();
        assertEquals(1, i.next());
        assertEquals(2, i.next());
        assertEquals(5, i.next());
        assertEquals(7, i.next());
    }

    @Test
    public void size() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(2);
        test1.add(4);
        test1.add(5);
        assertEquals(4, test1.size());
        test1.add(2);
        assertEquals(4, test1.size());
        test1.remove(2);
        assertEquals(3, test1.size());
    }

    @Test
    public void clear() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(2);
        test1.add(4);
        test1.add(5);
        test1.clear();
        assertFalse(test1.contains(1));
        assertFalse(test1.contains(2));
        assertFalse(test1.contains(4));
        assertFalse(test1.contains(5));
        assertEquals(0, test1.size());
    }

    @Test
    public void add() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(8);
        test1.add(-4);
        test1.add(3);
        assertTrue(test1.contains(1));
        assertTrue(test1.contains(-4));
        assertTrue(test1.contains(3));
        assertTrue(test1.contains(8));
        assertFalse(test1.contains(-9));
        TreeSet<String> test2 = new TreeSet<>(String::compareTo);
        test2.add("aab");
        test2.add("bbb");
        test2.add("aaa");
        assertTrue(test2.contains("aaa"));
        assertTrue(test2.contains("bbb"));
        assertTrue(test2.contains("aab"));
        assertFalse(test2.contains("ccc"));
    }

    @Test
    public void remove() throws Exception {
        TreeSet<Integer> test1 = new TreeSet<>();
        test1.add(1);
        test1.add(8);
        test1.add(-4);
        test1.add(3);
        assertTrue(test1.contains(1));
        assertTrue(test1.contains(-4));
        assertTrue(test1.contains(3));
        assertTrue(test1.contains(8));
        assertFalse(test1.contains(-9));
        test1.remove(-4);
        test1.remove(1);
        assertFalse(test1.contains(1));
        assertFalse(test1.contains(-4));
        assertTrue(test1.contains(8));
        assertTrue(test1.contains(3));
    }

    @Test
    public void contains() throws Exception {
        TreeSet<String> test1 = new TreeSet<>(String::compareTo);
        test1.add("aab");
        test1.add("bbb");
        test1.add("aaa");
        assertTrue(test1.contains("aaa"));
        assertTrue(test1.contains("bbb"));
        assertTrue(test1.contains("aab"));
        assertFalse(test1.contains("ccc"));
        test1.remove("aaa");
        assertFalse(test1.contains("aaa"));
        assertTrue(test1.contains("bbb"));
        assertTrue(test1.contains("aab"));
    }

}