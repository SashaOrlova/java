package ru.java.homework;

import java.io.*;

/**
 * data structure for store strings like hanging tree.
 */
public class Trie implements Serializable {
    private int size = 0;
    private vertex root;

    public Trie() {
        root = new vertex();
    }

    /**
     * add new string in trie. Work time is O(|length|)
     * @param st string that will be inserted
     * @return true if with string not exist or false otherwise.
     */
    public boolean addString(final String st) {
        vertex ptr = root;
        if (root.next[st.charAt(0) - 'a'] == null) {
            for (char c : st.toCharArray()) {
                ptr.next[c - 'a'] = new vertex();
                ptr = ptr.next[c - 'a'];
            }
            ptr.leaf = true;
            size++;
            correctStartsWith(st, 1);
            return true;
        } else {
            int i = 0;
            while (i < st.length() && ptr.next[st.charAt(i) - 'a'] != null) {
                ptr = ptr.next[st.charAt(i) - 'a'];
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
                    ptr.next[st.charAt(i) - 'a'] = new vertex();
                    ptr = ptr.next[st.charAt(i) - 'a'];
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
        vertex ptr = root;
        while (i < st.length() && ptr.next[st.charAt(i) - 'a'] != null) {
            ptr = ptr.next[st.charAt(i) - 'a'];
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
        vertex ptr = root;
        while (i < st.length() && ptr.next[st.charAt(i) - 'a'] != null) {
            ptr = ptr.next[st.charAt(i) - 'a'];
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
            vertex ptr = root;
            while (i < st.length() && ptr.next[st.charAt(i) - 'a'] != null) {
                ptr = ptr.next[st.charAt(i) - 'a'];
                i++;
            }
            ptr.leaf = false;
            return true;
        }
        return false;
    }

    private void correctStartsWith(final String st, int k) {
        int i = 0;
        vertex ptr = root;
        while (i < st.length() && ptr.next[st.charAt(i) - 'a'] != null) {
            ptr.cnt += k;
            ptr = ptr.next[st.charAt(i) - 'a'];
            i++;
        }
    }

    /**
     * write trie in out.
     * @param out OutputStream where trie be written
     * @throws IOException
     */
    public void serialize(OutputStream out) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(this);
        oos.close();
    }

    /**
     * read trie from in and change it
     * @param in input stream from trie will be read
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void  deserialize(InputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(in);
        Trie tmp = (Trie) ois.readObject();
        root  = tmp.root;
        size = tmp.size;
    }
    private class vertex implements Serializable{
        int cnt = 0;
        vertex[] next;
        boolean leaf = false;

        vertex() {
            next = new vertex[100];
        }
    }
}
