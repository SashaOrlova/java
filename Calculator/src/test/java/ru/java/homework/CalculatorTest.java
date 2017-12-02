package ru.java.homework;

import org.junit.Test;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CalculatorTest {
    @Test
    @SuppressWarnings("unchecked")
    public void testWithMockitoPolishNotation() throws Exception {
        Stack<Character> stack = mock(Stack.class);
        when(stack.top()).thenReturn('+').thenReturn('+');
        when(stack.empty()).thenReturn(true).thenReturn(false).thenReturn(true);
        ArrayList<Integer> test = Calculator.toReversePolishNotation("3+4", stack);
        assertEquals((Integer) 3, test.get(0));
        assertEquals((Integer) 4, test.get(1));
        assertEquals((Integer) (int) '+', test.get(2));
    }
    @Test
    @SuppressWarnings("unchecked")
    public void testWithMockEval() throws Exception {
        Stack<Integer> stack = mock(Stack.class);
        ArrayList<Integer> test = new ArrayList<>(3);
        test.add(2);
        test.add(5);
        test.add((int) '+');
        when(stack.top()).thenReturn(5).thenReturn(2).thenReturn(7);
        assertEquals(7, Calculator.EvaluateExpression(test, stack));
        verify(stack, times(3)).top();
        verify(stack, times(2)).pop();
        verify(stack, times(0)).push((int)'+');
    }
    @Test
    @SuppressWarnings("unchecked")
    public void test() throws Exception {
        Stack<Integer> stack = mock(Stack.class);
        ArrayList<Integer> test = new ArrayList<>(3);
        test.add(7);
        test.add(5);
        test.add((int) '-');
        when(stack.top()).thenReturn(7).thenReturn(5).thenReturn(2);
        assertEquals(2, Calculator.EvaluateExpression(test, stack));
        verify(stack, times(3)).top();
        verify(stack, times(2)).pop();
        verify(stack, times(0)).push((int)'-');
    }
    @Test
    public void toReversePolishNotationSimple() throws Exception {
        ArrayList<Integer> test = Calculator.toReversePolishNotation("3+4", new Stack<>());
        assertEquals((Integer) 3, test.get(0));
        assertEquals((Integer) 4, test.get(1));
        assertEquals((Integer) (int) '+', test.get(2));
    }
    @Test
    public void toReversePolishNotationLongNumber() throws Exception {
        ArrayList<Integer> test = Calculator.toReversePolishNotation("3210+4345", new Stack<>());
        assertEquals((Integer) 3210 , test.get(0));
        assertEquals((Integer) 4345, test.get(1));
        assertEquals((Integer) (int) '+', test.get(2));
    }
    @Test
    public void toReversePolishNotationDifferentPriority() throws Exception {
        ArrayList<Integer> test = Calculator.toReversePolishNotation("2+3*3", new Stack<>());
        assertEquals((Integer) 2     , test.get(0));
        assertEquals((Integer) 3, test.get(1));
        assertEquals((Integer) 3, test.get(2));
        assertEquals((Integer) (int) '*', test.get(3));
        assertEquals((Integer) (int) '+', test.get(4));
    }
    @Test
    public void toReversePolishNotationWithBrackets() throws Exception {
        ArrayList<Integer> test = Calculator.toReversePolishNotation("222*(323+101)", new Stack<>());
        assertEquals((Integer) 222, test.get(0));
        assertEquals((Integer) 323, test.get(1));
        assertEquals((Integer) 101, test.get(2));
        assertEquals((Integer) (int) '+', test.get(3));
        assertEquals((Integer) (int) '*', test.get(4));
    }

    @Test
    public void evaluateExpression() throws Exception {
        ArrayList<Integer> test = new ArrayList<>(3);
        test.add(1);
        test.add(2);
        test.add((int) '+');
        assertEquals(3, Calculator.EvaluateExpression(test, new Stack<>()));
    }
    @Test
    public void evaluateAllSimple() throws Exception {
        String expr = "(2+3)*5";
        assertEquals(25, Calculator.EvaluateExpression(
                Calculator.toReversePolishNotation(expr, new Stack<>()), new Stack<>()));
    }
    @Test
    public void evaluateAll() throws Exception {
        String expr = "(2+3)*(1+4)";
        assertEquals(25, Calculator.EvaluateExpression(
                Calculator.toReversePolishNotation(expr, new Stack<>()), new Stack<>()));
    }
}