package ru.java.function;

import static org.junit.Assert.*;

public class Function1Test {
    private Function1<Integer, Integer> id = (x -> x);
    private Function1<Integer, Integer> plus10 = (x -> x + 10);
    private Function1<Integer, Integer> mult2 = (x -> x*2);
    private Function1<String, String> addA = (s -> (s + 'a'));

    @org.junit.Test
    public void apply() throws Exception {
        assertEquals((Integer) 10, id.apply(10));
        assertEquals((Integer)20, plus10.apply(10));
        assertEquals((Integer)30, mult2.apply(15));
        assertEquals("abca", addA.apply("abc"));
    }

    @org.junit.Test
    public void compose() throws Exception {
        Function1<Integer, Integer> comp1 = plus10.compose(mult2);
        Function1<Integer, Integer> comp2 = mult2.compose(plus10);
        assertEquals((Integer) 40, comp1.apply(10));
        assertEquals((Integer) 30, comp2.apply(10));
    }
}