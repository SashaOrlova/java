import ru.java.homework.Stack;

import static org.junit.Assert.*;

public class StackTest {
    @org.junit.Test
    public void push() throws Exception {
        Stack<Character> testStack = new Stack<>();
        testStack.push('a');
        assertEquals((Character) 'a', testStack.top());
        testStack.push('b');
        assertEquals((Character) 'b', testStack.top());
        testStack.push('c');
        testStack.push('d');
        assertEquals((Character) 'd', testStack.top());
        for (char i = 0; i < 105; i++) {
            testStack.push(i);
        }
        assertEquals((Character) (char) 104 ,testStack.top());
    }

    @org.junit.Test
    public void pop() throws Exception {
        Stack<Character> testStack = new Stack<>();
        testStack.push('a');
        testStack.push('b');
        testStack.push('c');
        testStack.push('d');
        testStack.pop();
        assertEquals((Character) 'c', testStack.top());
        testStack.pop();
        assertEquals((Character) 'b', testStack.top());
        testStack.pop();
        assertEquals((Character) 'a', testStack.top());
    }

    @org.junit.Test
    public void top() throws Exception {
        Stack<Character> testStack = new Stack<>();
        testStack.push('a');
        testStack.push('b');
        testStack.push('c');
        testStack.push('d');
        assertEquals((Character) 'd', testStack.top());
        testStack.push('e');
        testStack.push('f');
        assertEquals((Character) 'f', testStack.top());
    }
}