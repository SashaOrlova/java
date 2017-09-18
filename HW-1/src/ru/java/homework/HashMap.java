package ru.java.homework;

/**
 * Hashmap is a data structure which implements an associative array abstract data type,
 * a structure that can map keys to values.
 */
public class HashMap {

    private int initial_capacity = 73;
    private int size = 0;
    private int capacity = initial_capacity;

    private List[] lists;

    /**
     * Constructs an empty HashMap with the default initial capacity (73) and the default load factor (0.75).
     */
    public HashMap() {
        lists = new List[capacity];
        for (int i = 0 ; i < capacity; i++) {
            lists[i] = new List();
        }
    }

    private boolean isFull() {
        return size >= 0.75*capacity;
    }

    private void rebuild() {
        List[] newLists = new List[capacity*2];
        for (int i = 0 ; i < capacity*2; i++) {
            newLists[i] = new List();
        }
        for (int i = 0; i < capacity; i++) {
            List.Node now = lists[i].head;
            while (now != null) {
                newLists[now.keyValue.hashCode() % (capacity*2)].insert(now.stringValue, now.keyValue);
                now = now.nextNode;
            }
        }
        lists = newLists;
        capacity *= 2;
    }

    /**
     * @return the number of key-value mappings in this map.
     */
    public int size() {
        return size;
    }
    /**
     * Returns true if key was found, or false if this map contains no mapping for the key.
     * @param key the key whose associated value is to be found
     * @return true if the key was found else false
     */
    public boolean contains(String key) {
        return lists[key.hashCode() % capacity].find(key);
    }

    /**
     * Returns the value to which the specified key is mapped,
     or null if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return the value mapping with key
     */
    public String get(String key) {
        return lists[key.hashCode() % capacity].search(key);
    }

    /**
     * Associates the value with the key in this map. 
     If the map previously contained a mapping for the key, the old value will be replaced.
     * @param key  key with which the value to be associated
     * @param value value to be associated with key
     * @return old value associated with key
     */
    public String put(String key, String value) {
        String stringValue = remove(key);
        if (stringValue == null) {
            size++;
        }
        if (isFull()) {
            rebuild();
        }
        lists[key.hashCode() % capacity].insert(value, key);
        return stringValue;
    }

    /**
     * Removes the mapping for the key from this map
     * @param key key with which the value to be associated
     * @return value associated with key
     */
    public String remove(String key) {
        String stringValue = get(key);
        lists[key.hashCode() % capacity].delete(key);
        if (stringValue != null) size--;
        return stringValue;
    }

    /**
     * Removes all keys and values from the map
     */
    public void clear() {
        size = 0;
        capacity = initial_capacity;
        lists = new List[capacity];
        for (int i = 0 ; i < capacity; i++) {
            lists[i] = new List();
        }
    }
    private class List {
        private class Node {
            private Node nextNode;
            private Node prevNode;

            private String stringValue;
            private String keyValue;

            private Node(String stringValue, String key) {
                this.stringValue = stringValue;
                keyValue = key;
            }
        }
        private Node head = null;
        private int size = 0;
        private void insert(String stringValue, String key) {
            if (size == 0) {
                head = new Node(stringValue, key);
            }
            else {
                Node now = head;
                while (now.nextNode != null) {
                    now = now.nextNode;
                }
                now.nextNode = new Node(stringValue, key);
                now.nextNode.prevNode = now;
            }
            size++;
        }
        private String search(String key) {
            Node now = head;
            while (now != null && !now.keyValue.equals(key)) {
                now = now.nextNode;
            }
            if (now != null && now.keyValue.equals(key)) {
                return now.stringValue;
            }
            return null;
        }
        private boolean find(String key) {
            String try_search = search(key);
            return  try_search != null;
        }
        private void delete(String key) {
            Node now = head;
            while (now != null && !now.keyValue.equals(key)) {
                now = now.nextNode;
            }
            if (now != null && now.keyValue.equals(key)) {
                if (now.prevNode == null) {
                    head = now.nextNode;
                }
                if (now.nextNode != null) {
                    if (now.prevNode != null) {
                        now.prevNode.nextNode = now.nextNode;
                    }
                    now.nextNode.prevNode = now.prevNode;
                }
                else{
                    if (now.prevNode != null) {
                        now.prevNode.nextNode = null;
                    }
                }
                size--;
            }
        }
    }
}
