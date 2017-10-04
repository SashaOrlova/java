import ru.java.homework.Trie;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class TrieTest {
    @org.junit.Test
    public void addStringSimple() throws Exception {
        Trie t = new Trie();
        assertEquals(true, t.addString("a"));
    }

    @org.junit.Test
    public void addStringWithEqualPrefix() throws Exception {
        Trie t = new Trie();
        assertEquals(true, t.addString("a"));
        assertEquals(true, t.addString("ab"));
        assertEquals(true, t.addString("abc"));
        assertEquals(true, t.contains("a"));
        assertEquals(true, t.contains("ab"));
        assertEquals(true, t.contains("abc"));
    }

    @org.junit.Test
    public void addStringEquals() throws Exception {
        Trie t = new Trie();
        t.addString("abc");
        assertEquals(false, t.addString("abc"));
        assertEquals(true, t.contains("abc"));
    }

    @org.junit.Test
    public void sizeEmpty() throws Exception {
        Trie t = new Trie();
        assertEquals(t.size(), 0);
    }

    @org.junit.Test
    public void sizeNotEmpty() throws Exception {
        Trie t = new Trie();
        t.addString("abc");
        t.addString("abcd");
        t.addString("abcde");
        assertEquals(t.size(), 3);
    }

    @org.junit.Test
    public void sizeWithEquals() throws Exception {
        Trie t = new Trie();
        t.addString("abc");
        t.addString("abc");
        t.addString("abcd");
        assertEquals(2, t.size());
    }

    @org.junit.Test
    public void startWithPrefix() throws Exception {
        Trie t = new Trie();
        t.addString("abc");
        t.addString("abcd");
        assertEquals(2, t.startWithPrefix("a"));
    }

    @org.junit.Test
    public void startWithPrefixZero() throws Exception {
        Trie t = new Trie();
        t.addString("abc");
        t.addString("abcd");
        assertEquals(0, t.startWithPrefix("b"));
    }

    @org.junit.Test
    public void removeSimple() throws Exception {
        Trie t = new Trie();
        t.addString("abc");
        assertEquals(true, t.remove("abc"));
        assertEquals(false, t.contains("abc"));
    }

    @org.junit.Test
    public void removeNull() throws Exception {
        Trie t = new Trie();
        t.addString("abc");
        assertEquals(false, t.remove("abcd"));
        assertEquals(true, t.contains("abc"));
    }

    @org.junit.Test
    public void contains() throws Exception {
        Trie t = new Trie();
        t.addString("abc");
        assertEquals(true, t.contains("abc"));
    }

    @org.junit.Test
    public void containsNull() throws Exception {
        Trie t = new Trie();
        t.addString("abc");
        assertEquals(false, t.contains("abcd"));
    }
    @org.junit.Test
    public void serialize() throws Exception {
        Trie t = new Trie();
        t.addString("aaa");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        t.serialize(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        Trie t1 = new Trie();
        t1.deserialize(in);
        assertEquals(true, t1.contains("aaa"));
    }
}