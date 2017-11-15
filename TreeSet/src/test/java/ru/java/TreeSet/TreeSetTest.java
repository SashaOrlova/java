package ru.java.TreeSet;

import org.junit.Test;
import ru.java.MyTreeSet.MyTreeSet;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TreeSetTest {
    @Test
    public void size() throws Exception {
        TreeSet<String> s = new TreeSet<>();
        s.add("aa");
        s.add("bb");
        assertEquals(2, s.size());
    }

    @Test
    public void contains() throws Exception {
        TreeSet<Integer> t = new TreeSet<>();
        t.add(1);
        t.add(2);
        t.add(4);
        assertTrue(t.contains(1));
        assertTrue(t.contains(2));
        assertTrue(t.contains(4));
        assertFalse(t.contains(5));
        assertFalse(t.contains(3));
        assertFalse(t.contains(-1));
    }

    @Test
    public void iterator() throws Exception {
        TreeSet<Integer> t = new TreeSet<>();
        t.add(1);
        t.add(2);
        t.add(4);
        Iterator<Integer> it = t.iterator();
        assertEquals((Integer) 1, it.next());
        assertEquals((Integer) 2, it.next());
        assertEquals((Integer) 4, it.next());
    }

    @Test
    public void add() throws Exception {
        TreeSet<Integer> t = new TreeSet<>();
        t.add(6);
        t.add(3);
        t.add(4);
        t.add(5);
        assertTrue(t.contains(3));
        assertTrue(t.contains(4));
        assertTrue(t.contains(6));
        assertTrue(t.contains(5));
        assertFalse(t.contains(9));
        TreeSet<String> s = new TreeSet<>();
        s.add("aa");
        s.add("bb");
        assertTrue(s.contains("aa"));
        assertTrue(s.contains("bb"));
        assertFalse(s.contains("cc"));
    }

    @Test
    public void remove() throws Exception {
        TreeSet<Integer> t = new TreeSet<>();
        t.add(6);
        t.add(3);
        t.add(4);
        t.add(5);
        assertTrue(t.contains(3));
        assertTrue(t.contains(4));
        assertTrue(t.contains(6));
        assertTrue(t.contains(5));
        assertFalse(t.contains(9));
        t.remove(3);
        t.remove(4);
        t.remove(9);
        assertFalse(t.contains(3));
        assertFalse(t.contains(4));
        assertFalse(t.contains(9));
    }

    @Test
    public void descendingSet() throws Exception {
        TreeSet<Integer> t = new TreeSet<>();
        t.add(1);
        t.add(2);
        t.add(4);
        MyTreeSet<Integer> t1 = t.descendingSet();
        assertTrue(t1.contains(1));
        assertTrue(t.contains(2));
        assertTrue(t.contains(4));
        assertFalse(t.contains(5));
        assertFalse(t.contains(3));
        assertFalse(t.contains(-1));
    }

    @Test
    public void first() throws Exception {
        TreeSet<Integer> t = new TreeSet<>();
        t.add(1);
        t.add(2);
        t.add(4);
        assertEquals((Integer) 1, t.first());
    }

    @Test
    public void last() throws Exception {
        TreeSet<Integer> t = new TreeSet<>();
        t.add(1);
        t.add(2);
        t.add(4);
        assertEquals((Integer) 4, t.last());
    }

    @Test
    public void lower() throws Exception {
        TreeSet<Integer> t = new TreeSet<>();
        t.add(1);
        t.add(2);
        t.add(3);
        t.add(4);
      //  assertEquals((Integer) 2, t.lower(4));
    }
}