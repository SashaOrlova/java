    import ru.java.homework.Set;

import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class SetTest {
    @org.junit.Test
    public void addInteger() {
        Set<Integer> t = new Set<>();
        t.add(6);
        t.add(3);
        t.add(4);
        t.add(5);
        assertTrue(t.contains(3));
        assertTrue(t.contains(4));
        assertTrue(t.contains(6));
        assertTrue(t.contains(5));
    }

    @org.junit.Test
    public void addString() {
        Set<String> s = new Set<>();
        s.add("aa");
        s.add("bb");
        assertTrue(s.contains("aa"));
        assertTrue(s.contains("bb"));
        assertFalse(s.contains("cc"));
    }

    @org.junit.Test
    public void size() {
        Set<String> s = new Set<>();
        s.add("aa");
        s.add("bb");
        assertEquals(2, s.size());
    }

    @org.junit.Test
    public void contains() {
        Set<Integer> t = new Set<>();
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
}