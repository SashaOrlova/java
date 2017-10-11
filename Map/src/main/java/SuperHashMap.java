import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static java.lang.StrictMath.abs;

public class SuperHashMap<Q, R> implements Map<Q, R> {
    static public class Entry<K, V> {
        public K key;
        public V value;

        public Entry(K key, V val) {
            this.key = key;
            value = val;
        }
    }

    public class SetEntry<E> implements Set<E> {
        public int size() {
            return hashMap.size;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean contains(Object o) {
            return false;
        }

        public Iter<E> iterator() {
            return new Iter<E>();
        }

        public Object[] toArray() {
            return new Object[0];
        }

        public <T> T[] toArray(T[] a) {
            return null;
        }

        public boolean add(E e) {
            return false;
        }

        public boolean remove(Object o) {
            return false;
        }

        public boolean containsAll(Collection<?> c) {
            return false;
        }

        public boolean addAll(Collection<? extends E> c) {
            return false;
        }

        public boolean retainAll(Collection<?> c) {
            return false;
        }

        public boolean removeAll(Collection<?> c) {
            return false;
        }

        public void clear() {

        }

        public class Iter<E> implements Iterator<E>{
            private int pos = 0;
            public E next(){
                E p = (E)forSet.get(pos ++);
                while (p == null && pos <= cnt) {
                    p = (E)forSet.get(pos ++);
                }
                return p;
            }
            public boolean hasNext(){
                return pos <= cnt;
            }
        }
    }

    private int cnt = 0;
    private HashMap<Q, R> hashMap;
    private HashMap<Integer, R> forIterate;
    private HashMap<R, Integer> forIterateV;
    private HashMap<Q, Q> forKeys;
    private HashMap<Integer, Entry<Q, R>> forSet;

    public SuperHashMap() {
        hashMap = new HashMap<Q, R>();
        forIterate = new HashMap<Integer, R>();
        forKeys = new HashMap<Q, Q>();
        forSet = new HashMap<Integer, Entry<Q, R>>();
    }

    public boolean containsKey(Object key) {
        return forKeys.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return hashMap.containsKey(value);
    }

    public R get(Object key) {
        return hashMap.get(key);
    }

    public int size() {
        return hashMap.size;
    }

    public boolean isEmpty() {
        return hashMap.size == 0;
    }

    public R put(Q key, R val) {
        if (forKeys.put(key,key) != null) {
            Integer t = forIterateV.get(val);
            forIterate.remove(t);
        }
        forSet.put(cnt, new Entry<Q, R>(key, val));
        forIterateV.put(val, cnt);
        forIterate.put(cnt++, val);
        return hashMap.put(key, val);
    }

    @Override
    public R remove(Object key) {
        R r = hashMap.remove((Q)key);
        Integer t = forIterateV.remove(r);
        forIterate.remove(t);
        forSet.remove(t);
        forKeys.remove((Q)key);
        return r;
    }

    @Override
    public void putAll(Map<? extends Q, ? extends R> m) {

    }

    public Set<Map.Entry<Q, R>> entrySet() {
        return new SetEntry<Map.Entry<Q, R>>();
    }

    public void clear() {
        hashMap.clear();
        forIterate.clear();
        forIterateV.clear();
        forKeys.clear();
        forSet.clear();
    }

    @Override
    public Set<Q> keySet() {
        return null;
    }

    @Override
    public Collection<R> values() {
        return null;
    }

    private class HashMap<K, V> {
        private int initial_capacity = 73;
        private int size = 0;
        private int capacity = initial_capacity;

        private List<K, V>[] lists;
        private List<Integer, V> vectorValues;
        private List<K, V> vectorPair;

        public HashMap() {
            lists = (List<K, V>[]) new List[capacity];
            vectorValues = new List<Integer, V>();
            vectorPair = new List<K, V>();
            for (int i = 0; i < capacity; i++) {
                lists[i] = new List<K, V>();
            }
        }

        public V put(K key, V value) {
            V stringValue = remove(key);
            size++;
            if (isFull()) {
                rebuild();
            }
            lists[getHashCode(key)].insert(value, key);
            return stringValue;
        }

        public V remove(K key) {
            V stringValue = get(key);
            lists[getHashCode(key)].delete(key);
            if (stringValue != null) size--;
            return stringValue;
        }

        protected int getHashCode(K St) {
            return abs(St.hashCode() % (capacity));
        }

        protected boolean isFull() {
            return size >= 0.75 * capacity;
        }

        private void rebuild() {
            List[] newLists = new List[capacity * 2];
            for (int i = 0; i < capacity * 2; i++) {
                newLists[i] = new List();
            }
            for (int i = 0; i < capacity; i++) {
                List.Node now = lists[i].head;
                while (now != null) {
                    newLists[abs(now.keyValue.hashCode() % (capacity * 2))].insert(now.Value, now.keyValue);
                    now = now.nextNode;
                }
            }
            lists = newLists;
            capacity *= 2;
        }

        public void clear() {
            size = 0;
            capacity = initial_capacity;
            lists = new List[capacity];
            for (int i = 0; i < capacity; i++) {
                lists[i] = new List();
            }
            vectorValues = new List<Integer, V>();
            vectorPair = new List<K, V>();
        }

        public boolean containsKey(Object key) {
            return lists[getHashCode((K) key)].find((K) key);
        }

        public V get(Object key) {
            return lists[getHashCode((K) key)].search((K) key);
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private class List<K, V> {
            private class Node<S, T> {
                private Node nextNode;
                private Node prevNode;

                private S Value;
                private T keyValue;

                private Node(S Value, T key) {
                    this.Value = Value;
                    keyValue = key;
                }
            }

            private Node head = null;
            private int size = 0;

            private void insert(V Value, K key) {
                if (size == 0) {
                    head = new Node(Value, key);
                } else {
                    Node now = head;
                    while (now.nextNode != null) {
                        now = now.nextNode;
                    }
                    now.nextNode = new Node(Value, key);
                    now.nextNode.prevNode = now;
                }
                size++;
            }

            private V search(K key) {
                Node now = head;
                while (now != null && !now.keyValue.equals(key)) {
                    now = now.nextNode;
                }
                if (now != null && now.keyValue.equals(key)) {
                    return (V) now.Value;
                }
                return null;
            }

            private boolean find(K key) {
                V try_search = search(key);
                return try_search != null;
            }

            private void delete(K key) {
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
                    } else {
                        if (now.prevNode != null) {
                            now.prevNode.nextNode = null;
                        }
                    }
                    size--;
                }
            }
        }
    }
}