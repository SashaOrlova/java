package ru.java.homework;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void toReversePolishNotationSimple() throws Exception {
        ArrayList<Integer> test = Calculator.toReversePolishNotation("3+4");
        assertEquals((Integer) 3, test.get(0));
        assertEquals((Integer) 4, test.get(1));
        assertEquals((Integer) (int) '+', test.get(2));
    }
    @Test
    public void toReversePolishNotationLongNumber() throws Exception {
        ArrayList<Integer> test = Calculator.toReversePolishNotation("3210+4345");
        assertEquals((Integer) 3210 , test.get(0));
        assertEquals((Integer) 4345, test.get(1));
        assertEquals((Integer) (int) '+', test.get(2));
    }
    @Test
    public void toReversePolishNotationDifferentPriority() throws Exception {
        ArrayList<Integer> test = Calculator.toReversePolishNotation("2+3*3");
        assertEquals((Integer) 2     , test.get(0));
        assertEquals((Integer) 3, test.get(1));
        assertEquals((Integer) 3, test.get(2));
        assertEquals((Integer) (int) '*', test.get(3));
        assertEquals((Integer) (int) '+', test.get(4));
    }
    @Test
    public void toReversePolishNotationWithBrackets() throws Exception {
        ArrayList<Integer> test = Calculator.toReversePolishNotation("222*(323+101)");
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
        assertEquals(3, Calculator.EvaluateExpression(test));
    }
    @Test
    public void evaluateAllSimple() throws Exception {
        String expr = "(2+3)*5";
        assertEquals(25, Calculator.EvaluateExpression(Calculator.toReversePolishNotation(expr)));
    }
    @Test
    public void evaluateAll() throws Exception {
        String expr = "(2+3)*(1+4)";
        assertEquals(25, Calculator.EvaluateExpression(Calculator.toReversePolishNotation(expr)));
    }


}