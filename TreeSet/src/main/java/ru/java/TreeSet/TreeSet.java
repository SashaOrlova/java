package ru.java.TreeSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.java.MyTreeSet.MyTreeSet;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

/**TreeSet provides an implementation of the Set interface that uses a tree for storage.
 * Objects are stored in a sorted order.
 * @param <E> - type of stored Objects
 */
public class TreeSet<E> extends AbstractSet<E> implements MyTreeSet<E> {
    protected Tree tree = new Tree();
    private Comparator<? super E> cmp;

    /**
     * Create empty TreeSet sorted according to the specified comparator.
     * @param cmp - the comparator that will be used to order this set. If null,
     *           the natural ordering of the elements will be used.
     */
    public TreeSet(@NotNull Comparator<? super E> cmp) {
        this.cmp = cmp;
    }

    /**
     * Create empty TreeSet sorted according to the natural ordering of its elements.
     */
    @SuppressWarnings("unchecked")
    public TreeSet() {
        cmp = (x, y) -> ((Comparable<? super E>)x).compareTo(y);
    }

    @NotNull
    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            Tree.Vertex current = tree.last;

            @Override
            public boolean hasNext() {
                return current != tree.first;
            }

            @Override
            public E next() {
                Tree.Vertex prev = current;
                current = current.prev;
                return prev.value;
            }
        };
    }

    @NotNull
    @Override
    public MyTreeSet<E> descendingSet() {
        return new DescendingSet(this);
    }

    @Nullable
    @Override
    public E first() {
        return tree.first == null ? null : tree.first.value;
    }

    @Nullable
    @Override
    public E last() {
        return tree.last == null ? null : tree.last.value;
    }


    @Nullable
    @Override
    public E lower(E e) {
        Tree.Vertex MaybeAns = tree.lowerBound(tree.root, e, tree.root);
        return MaybeAns == null ? null : (MaybeAns.prev == null ? null : MaybeAns.prev.value);
    }

    @Nullable
    @Override
    public E floor(E e) {
        Tree.Vertex MaybeAns = tree.upperBound(tree.root, e, tree.root);
        return MaybeAns == null ? null : (MaybeAns.prev == null ? null : MaybeAns.prev.value);
    }

    @Nullable
    @Override
    public E ceiling(E e) {
        Tree.Vertex MaybeAns = tree.lowerBound(tree.root, e, tree.root);
        return MaybeAns == null ? null : MaybeAns.value;
    }

    @Nullable
    @Override
    public E higher(E e) {
        Tree.Vertex MaybeAns = tree.upperBound(e);
        return MaybeAns == null ? null : MaybeAns.value;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Tree.Vertex current = tree.first;

            @Override
            public boolean hasNext() {
                return current != tree.last;
            }

            @Override
            public E next() {
                Tree.Vertex prev = current;
                current = current.next;
                return prev.value;
            }
        };
    }

    @Override
    public int size() {
        return tree.size;
    }

    @Override
    public void clear() {
        tree = new Tree();
    }

    @Override
    public boolean add(E e) {
        return tree.add(e);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object e) {
        return tree.remove((E) e);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        return tree.find((E) o);
    }

    private class Tree {
        private int size = 0;
        private Vertex root;
        private Vertex first;
        private Vertex last;

        private boolean add(E elem) {
            if (find(elem)) return false;
            if (size == 0) {
                root = new Vertex(elem);
                last = first = root;
                size++;
                return true;
            }
            size++;
            addInTree(root, elem);
            Vertex newVertex = find(root, elem);
            Vertex newNext = next(newVertex);
            if (newNext != null) {
                newVertex.next = newNext;
                newVertex.prev = newNext.prev;
                if (newVertex.prev != null)
                    newVertex.prev.next = newVertex;
                newNext.prev = newVertex;
                if (newNext == first)
                    first = newVertex;
            } else {
                last.next = newVertex;
                newVertex.prev = last;
                last = newVertex;
            }
            return true;
        }

        private Vertex addInTree(Vertex v, E elem) {
            if (v == null)
                return new Vertex(elem);
            if (cmp.compare(elem, v.value) < 0) {
                v.left = addInTree(v.left, elem);
                v.left.parent = v;
                return v;
            }
            v.right = addInTree(v.right, elem);
            v.right.parent = v;
            return v;
        }

        private Vertex next(Vertex v) {
            if (v.right != null) {
                while (v.left != null)
                    v = v.left;
                return v;
            } else {
                while (v.parent != null && v.parent.right == v)
                    v = v.parent;
                return v.parent;
            }
        }

        private void delFromList(Vertex vertex) {
            if (vertex.next != null)
                vertex.next.prev = vertex.prev;
            if (vertex.prev != null)
                vertex.prev.next = vertex.next;
            if (vertex == first)
                first = vertex.next;
            if (vertex == last)
                last = vertex.prev;
        }

        private void delWithoutLeft(Vertex vertex) {
            if (vertex.parent != null) {
                if (vertex.parent.left != null && vertex.parent.left == vertex)
                    vertex.parent.left = vertex.right;
                else
                    vertex.parent.right = vertex.right;
            } else
                root = vertex.right;
            if (vertex.right != null)
                vertex.right.parent = vertex.parent;
        }

        private void delWithoutRight(Vertex vertex) {
            if (vertex.parent != null) {
                if (vertex.parent.left != null && vertex.parent.left == vertex)
                    vertex.parent.left = vertex.left;
                else
                    vertex.parent.right = vertex.left;
            } else
                root = vertex.left;
            vertex.left.parent = vertex.parent;
        }

        private void delWithTwoChildren(Vertex vertex) {
            Vertex nextVertex = vertex.next;
            if (nextVertex.parent != null &&
                    nextVertex.parent.right != null && nextVertex.parent.right == nextVertex)
                nextVertex.parent.right = null;
            else if (nextVertex.parent != null) {
                nextVertex.parent.left = null;
            }
            if (vertex.parent != null) {
                if (vertex.parent.right == vertex)
                    vertex.parent.right = nextVertex;
                else
                    vertex.parent.left = nextVertex;
            } else
                root = nextVertex;
            nextVertex.right = vertex.right;
            nextVertex.left = vertex.left;
        }

        private Vertex upperBound(E elem) {
            Vertex v = upperBound(root, elem, root);
            return (cmp.compare(v.value, elem) < 0) ?
                    null : v;
        }
        private Vertex upperBound(Vertex v, E elem, Vertex ans) {
            if (v == null)
                return ans;
            if (cmp.compare(elem, v.value) < 0)
                return upperBound(v.left, elem, cmp.compare(ans.value, v.value) < 0 ? ans : v);
            else
                return upperBound(v.right, elem, ans);
        }

        private Vertex lowerBound(Vertex v, E elem, Vertex ans) {
            Vertex maybeAns = find(root, elem);
            return maybeAns == null ? upperBound(v, elem, ans) : maybeAns;
        }

        private boolean remove(E elem) {
            Vertex vertex = find(root, elem);
            if (vertex == null) return false;
            size--;
            if (vertex.left == null) {
                delWithoutLeft(vertex);
            } else if (vertex.right == null) {
                delWithoutRight(vertex);
            } else {
                delWithTwoChildren(vertex);
            }
            delFromList(vertex);
            return true;
        }

        private boolean find(E elem) {
            return find(root, elem) != null;
        }

        private Vertex find(Vertex v, E elem) {
            if (v == null)
                return null;
            if (elem.equals(v.value))
                return v;
            if (cmp.compare(elem, v.value) < 0)
                return find(v.left, elem);
            else
                return find(v.right, elem);
        }

        private class Vertex {
            Vertex right;
            Vertex left;
            Vertex parent;
            Vertex next;
            Vertex prev;
            private E value;

            Vertex(E val) {
                value = val;
            }
        }
    }
    private class DescendingSet extends TreeSet<E>{
        DescendingSet(TreeSet<E> revTree) {
            this.tree = revTree.tree;
        }
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

        @NotNull
        @Override
        public Iterator<E> iterator() {
            return super.descendingIterator();
        }

        @NotNull
        @Override
        public Iterator<E> descendingIterator() {
            return super.iterator();
        }
    }
}
