package ru.java.homework;

/** Simple binary search tree
 * @param <T> type of contains elements
 */
public class Set<T extends Comparable<? super T>> {
    private Node<T> root = null;
    private int size = 0;

    /** Add element in set
     * @param elem inserted element
     */
    public void add(T elem) {
        size++;
        if (root != null) {
            Node<T> position = root;
            Node<T> preposition = root;
            while (position != null) {
                preposition = position;
                if (position.element.compareTo(elem) < 0) {
                    position = position.left;
                } else {
                    position = position.right;
                }
            }
            if (preposition.element.compareTo(elem) < 0) {
                preposition.left = new Node<>(elem);
            } else {
                preposition.right = new Node<>(elem);
            }
        }
        else {
            root = new Node<>(elem);
        }
    }

    /** check element in set
     * @param elem checked element
     * @return true if element in set, false otherwise
     */
    public boolean contains(T elem) {
        Node<T> position = root;
        while (position != null) {
            int res = position.element.compareTo(elem);
            if (res == 0) return true;
            if (res < 0) {
                position = position.left;
            } else {
                position = position.right;
            }
        }
        return false;
    }

    public int size(){
        return size;
    }

    private class Node<S extends Comparable<? super S>> {
        S element;
        Node<S> left;
        Node<S> right;
        Node(S elem) {
            element = elem;
        }
    }
}
