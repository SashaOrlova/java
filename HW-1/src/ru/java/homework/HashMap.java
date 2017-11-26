package ru.java.homework;

import static java.lang.Math.abs;

/**
 * is a data structure which implements an associative array abstract data type,
 * a structure that can map keys to values.
 */
public class HashMap {

    private final int INITIAL_CAPACITY = 73;
    private int size = 0;
    private int capacity = INITIAL_CAPACITY;

    private List[] lists;

    /**
     * constructs an empty HashMap with the default initial capacity (73) and the default load factor (0.75).
     */
    public HashMap() {
        lists = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            lists[i] = new List();
        }
    }

    private int getHashCode(String string) {
        return abs(string.hashCode() % (capacity));
    }

    private boolean isFull() {
        return size >= 0.75 * capacity;
    }

    private void rebuild() {
        List[] newLists = new List[capacity * 2];
        for (int i = 0; i < capacity * 2; i++) {
            newLists[i] = new List();
        }
        for (int i = 0; i < capacity; i++) {
            List.Node currentNode = lists[i].head;
            while (currentNode != null) {
                newLists[abs(currentNode.keyValue.hashCode() % (capacity * 2))].insert(
                        currentNode.stringValue, currentNode.keyValue);
                currentNode = currentNode.nextNode;
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
     * returns true if key was found, or false if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be found
     * @return true if the key was found else false
     */
    public boolean contains(String key) {
        return lists[getHashCode(key)].find(key);
    }

    /**
     * returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value mapping with key
     */
    public String get(String key) {
        return lists[getHashCode(key)].search(key);
    }

    /**
     * associates the value with the key in this map.
     * If the map previously contained a mapping for the key, the old value will be replaced.
     *
     * @param key   key with which the value to be associated
     * @param value value to be associated with key
     * @return old value associated with key
     */
    public String put(String key, String value) {
        String stringValue = remove(key);
        size++;
        if (isFull()) {
            rebuild();
        }
        lists[getHashCode(key)].insert(value, key);
        return stringValue;
    }

    /**
     * removes the mapping for the key from this map
     *
     * @param key key with which the value to be associated
     * @return value associated with key
     */
    public String remove(String key) {
        String stringValue = get(key);
        lists[getHashCode(key)].delete(key);
        if (stringValue != null)
            size--;
        return stringValue;
    }

    /**
     * removes all keys and values from the map
     */
    public void clear() {
        size = 0;
        capacity = INITIAL_CAPACITY;
        lists = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            lists[i] = new List();
        }
    }

    /**
     * additional class for keep collisions in hashmap. Store keys with value.
     */
    private class List {
        private Node head = null;
        private int size = 0;

        private void insert(String stringValue, String key) {
            if (size == 0) {
                head = new Node(stringValue, key);
            } else {
                Node currentNode = head;
                while (currentNode.nextNode != null) {
                    currentNode = currentNode.nextNode;
                }
                currentNode.nextNode = new Node(stringValue, key);
                currentNode.nextNode.prevNode = currentNode;
            }
            size++;
        }

        /**
         * check key in list
         *
         * @param key string that wont be found
         * @return value stored with key or null if key not in list
         */
        private String search(String key) {
            Node currentNode = head;
            while (currentNode != null && !currentNode.keyValue.equals(key)) {
                currentNode = currentNode.nextNode;
            }
            if (currentNode != null && currentNode.keyValue.equals(key)) {
                return currentNode.stringValue;
            }
            return null;
        }

        /**
         * check existence of key in list
         *
         * @param key string
         * @return true if key in list, no otherwise
         */
        private boolean find(String key) {
            String trySearch = search(key);
            return trySearch != null;
        }

        /**
         * delete key and value from list
         *
         * @param key value with key will be deleted
         */
        private void delete(String key) {
            Node currentNode = head;
            while (currentNode != null && !currentNode.keyValue.equals(key)) {
                currentNode = currentNode.nextNode;
            }
            if (currentNode != null && currentNode.keyValue.equals(key)) {
                if (currentNode.prevNode == null) {
                    head = currentNode.nextNode;
                }
                if (currentNode.nextNode != null) {
                    if (currentNode.prevNode != null) {
                        currentNode.prevNode.nextNode = currentNode.nextNode;
                    }
                    currentNode.nextNode.prevNode = currentNode.prevNode;
                } else {
                    if (currentNode.prevNode != null) {
                        currentNode.prevNode.nextNode = null;
                    }
                }
                size--;
            }
        }

        /**
         * class describe one element of list
         */
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
    }
}
