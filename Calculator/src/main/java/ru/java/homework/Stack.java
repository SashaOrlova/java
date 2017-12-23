package ru.java.homework;

public class Stack<E> {
    @SuppressWarnings("unchecked")
    private E[] stack = (E[]) new Object[100];
    private int size = 0;
    private int reserve = 100;

    @SuppressWarnings("unchecked")
    private void rebuild() {
        E[] newStack = (E[]) new Object[reserve * 2];
        System.arraycopy(stack, 0, newStack, 0, reserve);
        stack = newStack;
        reserve *= 2;
    }

    /**
     * Pushes an item onto the top of this stack.
     */
    public void push(E c) {
        if (size >= reserve)
            rebuild();
        stack[size++] = c;
    }

    /**
     * Removes the object at the top of this stack
     *
     * @throws EmptyStackException if stack was empty
     */
    public void pop() throws EmptyStackException {
        size--;
        if (size < 0)
            throw new EmptyStackException();
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     *
     * @return object on the top
     */
    public E top() {
        if (size == 0)
            throw new EmptyStackException();
        return stack[size - 1];
    }

    /**
     * Tests if this stack is empty.
     *
     * @return true if stack empty, false otherwise
     */
    public boolean empty() {
        return size == 0;
    }

    public static class EmptyStackException extends RuntimeException {}
}
