package ru.java.homework;

import java.io.*;

/**
 * data structure for store strings like hanging tree.
 */
public class Trie implements Serializable {
    private int size = 0;
    private Vertex root = new Vertex();

    private int getIndex(char symbol) {
        return symbol - 'a';
    }
    /**
     * add new string in trie. Work time is O(|length|)
     * @param st string that will be inserted
     * @return true if with string not exist or false otherwise.
     */
    public boolean addString(final String st) {
        Vertex ptr = root;
        if (root.next[getIndex(st.charAt(0))] == null) {
            for (char c : st.toCharArray()) {
                ptr.next[getIndex(c)] = new Vertex();
                ptr = ptr.next[getIndex(c)];
            }
            ptr.leaf = true;
            size++;
            correctStartsWith(st, 1);
            return true;
        } else {
            int i = 0;
            while (i < st.length() && ptr.next[getIndex(st.charAt(i))] != null) {
                ptr = ptr.next[getIndex(st.charAt(i))];
                i++;
            }
            if (i == st.length()) {
                if (ptr.leaf) {
                    return false;
                } else {
                    ptr.leaf = true;
                    size++;
                    correctStartsWith(st, 1);
                    return true;
                }
            } else {
                for (; i < st.length(); i++) {
                    ptr.next[getIndex(st.charAt(i))] = new Vertex();
                    ptr = ptr.next[getIndex(st.charAt(i))];
                }
                ptr.leaf = true;
                size++;
                correctStartsWith(st, 1);
                return true;
            }
        }
    }

    /**
     * return number of strings start with prefix. Work time is O(|prefix|)
     * @param st prefix
     * @return number of string starts with st
     */
    public int startWithPrefix(final String st) {
        int i = 0;
        Vertex ptr = root;
        while (i < st.length() && ptr.next[getIndex(st.charAt(i))] != null) {
            ptr = ptr.next[getIndex(st.charAt(i))];
            i++;
        }
        return i == st.length() ? ptr.cnt + (ptr.leaf ? 1 : 0) : 0;
    }

    /**
     * work time is O(1)
     * @return number of strings contains in trie
     */
    public int size() {
        return size;
    }

    /**
     * check is string in trie. Work time is O(|length|)
     * @param st string that will be found
     * @return true if found st or false otherwise
     */
    public boolean contains(final String st) {
        int i = 0;
        Vertex ptr = root;
        while (i < st.length() && ptr.next[getIndex(st.charAt(i))] != null) {
            ptr = ptr.next[getIndex(st.charAt(i))];
            i++;
        }
        return i == st.length() && ptr.leaf;
    }

    /**
     * delete string from trie. Work time is O(|length|)
     * @param st string that will be deleted.
     * @return true if st was in trie, false otherwise
     */
    public boolean remove(final String st) {
        if (contains(st)) {
            correctStartsWith(st, -1);
            int i = 0;
            Vertex ptr = root;
            while (i < st.length() && ptr.next[getIndex(st.charAt(i))] != null) {
                ptr = ptr.next[getIndex(st.charAt(i))];
                i++;
            }
            ptr.leaf = false;
            return true;
        }
        return false;
    }

    private void correctStartsWith(final String st, int k) {
        int i = 0;
        Vertex ptr = root;
        while (i < st.length() && ptr.next[getIndex(st.charAt(i))] != null) {
            ptr.cnt += k;
            ptr = ptr.next[getIndex(st.charAt(i))];
            i++;
        }
    }

    private void dfs(StringBuffer st, Vertex v, OutputStream out) throws IOException {
        if (v == null)
            return;
        if (v.leaf) {
            out.write(st.toString().getBytes());
            out.write(0);
        }
        for (int i = 0 ; i < 100; i++) {
            dfs(st.append('a' + i ), v.next[i], out);
        }

    }
    /**
     * write trie in out.
     * @param out OutputStream where trie be written
     * @throws IOException - if out is uncorrected OutputStream
     */
    public void serialize(OutputStream out) throws IOException {
        out.write(size);
        dfs(new StringBuffer(), root, out);
    }

    /**
     * read trie from in and change it
     * @param in input stream from trie will be read
     * @throws IOException - if in is uncorrected InputStream
     * @throws ClassNotFoundException - if no Trie in InputStream
     */
    public void  deserialize(InputStream in) throws IOException, ClassNotFoundException {
        size = 0;
        in.read();
        root = new Vertex();
        StringBuilder buf = new StringBuilder();
        while (in.available() > 1) {
            int first = in.read();
            if (first == 0){
                addString(buf.toString());
                buf.delete(0, buf.length() - 1);
            }
            buf.append((char)(first*10 + in.read()));
        }
        addString(buf.toString());
    }
    private class Vertex implements Serializable {
        int cnt = 0;
        Vertex[] next = new Vertex[100];
        boolean leaf = false;
    }
}
