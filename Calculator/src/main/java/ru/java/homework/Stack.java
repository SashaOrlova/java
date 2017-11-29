package ru.java.homework;

public class Stack<E> {
    @SuppressWarnings("unchecked")
    private E[] stack = (E[]) new Object[100];
    private int size = 0;
    private int reserve = 100;

    @SuppressWarnings("unchecked")
    private void rebuild() {
        E[] newStack = (E[]) new Object[reserve*2];
        for (int i = 0 ; i < reserve; i++)
            newStack[i] = stack[i];
        stack = newStack;
        reserve *= 2;
    }

    public void push (E c) {
        if (size >= reserve)
            rebuild();
        stack[size++] = c;
    }

    public void pop() throws RuntimeException {
        size--;
        if (size < 0)
            throw new IndexOutOfBoundsException();
    }

    public E top() {
        return stack[size - 1];
    }

    public boolean empty() {
        return size == 0;
    }
}
