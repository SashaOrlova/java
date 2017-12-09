package ru.java.function;

import org.junit.Test;

import static org.junit.Assert.*;

public class Function2Test {
    private Function2<Integer, Integer, Integer>  summator = (x, y) -> x+y;
    private Function2<Integer, Integer, Integer> multiplier = (x, y) -> x*y;
    private Function2<Integer, Integer, Integer> minus = (x, y) -> x - y;
    private Function1<Integer, Integer> plus10 = (x -> x + 10);

    @Test
    public void apply() throws Exception {
        assertEquals((Integer)10,
                summator.apply(3, 7));
        assertEquals((Integer) 12,
                multiplier.apply(3,4));
    }

    @Test
    public void compose() throws Exception {
        assertEquals((Integer) 20,
                summator.compose(plus10).apply(5, 5));
        assertEquals((Integer) 22,
                multiplier.compose(plus10).apply(3, 4));
    }

    @Test
    public void bind1() throws Exception {
        assertEquals((Integer) 20,
                summator.bind1(10).apply(10));
        assertEquals((Integer) 7,
                minus.bind1(15).apply(8));
    }

    @Test
    public void bind2() throws Exception {
        assertEquals((Integer) 20,
                summator.bind2(10).apply(10));
        assertEquals((Integer) 7,
                minus.bind2(8).apply(15));
    }

    @Test
    public void curry() throws Exception {
        assertEquals((Integer) 7,
                minus.curry().apply(15).apply(8));
    }

}