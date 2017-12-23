package ru.java.function;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {
    class IsEven implements Predicate<Integer> {
        @Override
        public Boolean apply(@NotNull Integer arg) {
            return arg % 2 == 0;
        }
    }

    class IsFive implements Predicate<Integer> {
        @Override
        public Boolean apply(@NotNull Integer arg) {
            return arg == 5;
        }
    }

    class IsTen implements Predicate<Integer> {
        @Override
        public Boolean apply(@NotNull Integer arg) {
            return arg == 10;
        }
    }
    @Test
    public void or() throws Exception {
        assertEquals(true,
                (new IsEven().or(new IsFive())).apply(5));
        assertEquals(false,
                (new IsEven().or(new IsFive())).apply(7));
    }

    @Test
    public void and() throws Exception {
        assertEquals(true,
                (new IsEven().and(new IsTen())).apply(10));
        assertEquals(false,
                (new IsEven().and(new IsTen())).apply(5));
    }

    @Test
    public void not() throws Exception {
        Predicate<Integer> p = new IsEven().not();
        assertEquals(false,
                p.apply(12));
        assertEquals(true,
                p.apply(7));
    }
}