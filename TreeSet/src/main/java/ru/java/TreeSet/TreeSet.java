package ru.java.TreeSet;


import ru.java.MyTreeSet.MyTreeSet;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

public class TreeSet<E> extends AbstractSet<E> implements MyTreeSet<E> {
    protected int size = 0;
    protected Vertex root;
    protected Comparator<? super E> cmp;
    protected Vertex first;
    protected Vertex last;

    TreeSet(Comparator<? super E> cmp) {
        this.cmp = cmp;
    }

    TreeSet() {}

    @Override
    public int size() {
        return size;
    }

    private boolean find (Object  o, Vertex v) {
        return  v != null && ( o.equals(v.value) || find(o, v.left) || find(o, v.right));
    }

    @Override
    public boolean contains(Object o) {
        return find(o, root);
    }

    @Override
    public Iterator<E> iterator() {
        return new SetIterator();
    }

    @SuppressWarnings("unchecked")
    private int comp(E a, E b) {
        if (a == null || b == null)
            return 0;
        if (cmp == null) {
            Comparable<? super E> c = (Comparable<? super E>)a;
            return c.compareTo(b);
        }
        return cmp.compare(a, b);
    }

    @Override
    public boolean add(E e) {
        size++;
        if (root != null) {
            Vertex position = root;
            Vertex preposition = root;
            while (position != null) {
                preposition = position;
                if (comp(position.value, e) == 0)
                    return false;
                if (comp(position.value, e) < 0) {
                    position = position.left;
                } else {
                    position = position.right;
                }
            }
            if (comp(preposition.value, e) == 0)
                return false;
            if (comp(preposition.value, e) < 0) {
                Vertex node = new Vertex(e);
                preposition.left = node;
                node.next = preposition;
                preposition.prev = node;
                node.next = preposition.next;
                preposition.next = node;
                node.parent = preposition;
            } else {
                Vertex node = new Vertex(e);
                preposition.right = node;
                node.prev = preposition;
                preposition.prev.next = node;
                node.prev = preposition.prev;
                preposition.prev = node;
                node.parent = preposition;
            }
        }
        else {
            root = new Vertex(e);
            first = root;
            last = root;
            first.next = last;
            last.prev = first;
        }
        if (last != null && last.next != null)
            last = last.next;
        if (first != null && first.prev != null)
            first = first.prev;
        return true;
    }

    private Vertex findEqVertex(Object o, Vertex v) {
        if (v == null)
            return null;
        if (v.left != null && v.left.value.equals(o))
            return v.left;
        if (v.right != null && v.right.value.equals(o))
            return v.right;
        return v.value.equals(o) ? v : null;
    }

    private void deleteFromList(Vertex v) {
        v.prev.next = v.next;
        v.next.prev = v.prev;
        if (last.equals(v))
            last = last.prev;
        if (first.equals(v))
            first = first.next;
    }

    @Override
    public boolean remove(Object o) {
        size--;
        Vertex v = findEqVertex(o, root);
        if (v == null)
            return false;
        if (v.left == null && v.right == null) {
            if (v.parent.right.value.equals(o))
                v.parent.right = null;
            else
                v.parent.left = null;
            deleteFromList(v);
            return true;
        }
        if (v.right == null) {
            if (v.parent.left != null && v.parent.left.value.equals(v)) {
                v.parent.left = v.left;
                v.left.parent = v.parent;
            }
            else {
                v.parent.right = v.left;
                v.left.parent = v.parent;
            }
            deleteFromList(v);
            return true;
        }
        if (v.left == null) {
            if (v.parent.left != null && v.parent.left.equals(v)) {
                v.parent.left = v.right;
                v.right.parent = v.parent;
            }
            else {
                v.parent.right = v.right;
                v.right.parent = v.parent;
            }
            deleteFromList(v);
            return true;
        }
        Vertex next = v.next;
        next.right.parent = next.parent;
        if (next.parent.left.equals(next))
            next.parent.left = next.right;
        else
            next.parent.right = next.right;
        next.right = v.right;
        next.left = v.left;
        next.parent = v.parent;
        if (next.parent.left.equals(v))
            next.parent.left = next;
        else
            next.parent.right = next;
        deleteFromList(v);
        return true;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DesSetIterator();
    }

    @Override
    public MyTreeSet<E> descendingSet() {
        return new descendingSet(this);
    }

    @Override
    public E first() {
        return iterator().next();
    }

    @Override
    public E last() {
        return last == null ? null : last.value;
    }

    private E descendLower(E e, Vertex v, E ans) {
        if (v == null) return ans;
        if (comp(v.value, e) < 0) {
            return descendLower(e, v.right, comp(v.value, ans) < 0 ? ans : v.value);
        }
        if (comp(v.value, e) >= 0) {
            return descendLower(e, v.left, ans);
        }
        return null;
    }

    @Override
    public E lower(E e) {
        return descendLower(e, root, null);
    }

    private E descendFloor(E e, Vertex v, E ans) {
        if (v == null) return ans;
        if (comp(v.value, e) <= 0) {
            return descendFloor(e, v.right, comp(v.value, ans) <= 0 ? ans : v.value);
        }
        if (comp(v.value, e) > 0) {
            return descendFloor(e, v.left, ans);
        }
        return null;
    }

    @Override
    public E floor(E e) {
        return descendFloor(e, root, null);
    }

    private E descendCeiling(E e, Vertex v, E ans) {
        if (v == null) return ans;
        if (comp(v.value, e) < 0) {
            return descendCeiling(e, v.right, ans);
        }
        if (comp(v.value, e) >= 0) {
            return descendCeiling(e, v.left, comp(v.value, ans) < 0 ? v.value : ans);
        }
        return null;
    }

    @Override
    public E ceiling(E e) {
        return descendCeiling(e, root, null);
    }

    private E descendHigher(E e, Vertex v, E ans) {
        if (v == null) return ans;
        if (comp(v.value, e) < 0) {
            return descendHigher(e, v.right, ans);
        }
        if (comp(v.value, e) >= 0) {
            return descendHigher(e, v.left, comp(v.value, ans) < 0 ? v.value : ans);
        }
        return null;
    }

    @Override
    public E higher(E e) {
        return descendHigher(e, root, null);
    }

    private class Vertex {
        Vertex right;
        Vertex left;
        Vertex parent;
        Vertex next;
        Vertex prev;
        E value;
        Vertex(E val) {
            value = val;
        }
    }

    class descendingSet extends TreeSet<E> {
        @Override
        public E last() {
            return super.first();
        }

        @Override
        public E first() {
            return super.last();
        }

        @Override
        public E lower(E e) {
            return super.higher(e);
        }

        @Override
        public E higher(E e) {
            return super.lower(e);
        }

        @Override
        public E floor(E e) {
            return super.ceiling(e);
        }

        @Override
        public E ceiling(E e) {
            return super.floor(e);
        }

        private descendingSet(TreeSet<E> a) {
            root = a.root;
            size = a.size;
            cmp = a.cmp;
            last = a.last;
            first = a.first;
        }

        @Override
        public Iterator<E> iterator() {
            return super.descendingIterator();
        }

        @Override
        public Iterator<E> descendingIterator() {
            return super.iterator();
        }
    }

    private class SetIterator implements Iterator<E> {
        private Vertex current;

        private SetIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != last;
        }

        @Override
        public E next() {
            current = current.next;
            return current.value;
        }
    }
    private class DesSetIterator implements Iterator<E> {

        private Vertex current;

        private DesSetIterator() {
            current = last;
        }

        @Override
        public boolean hasNext() {
            return current != first;
        }

        @Override
        public E next() {
            current = current.prev;
            return current.next.value;
        }
    }
}
