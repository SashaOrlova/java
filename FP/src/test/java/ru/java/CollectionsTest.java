package ru.java;

import org.jetbrains.annotations.NotNull;
import ru.java.function.Function1;
import ru.java.function.Function2;
import ru.java.function.Predicate;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CollectionsTest {

    class Plus10 implements Function1<Integer, Integer> {
        @Override
        public Integer apply(@NotNull Integer arg) {
            return arg + 10;
        }
    }

    class Mult2 implements Function1<Integer, Integer> {
        @Override
        public Integer apply(@NotNull Integer arg) {
            return arg * 2;
        }
    }

    class IsEven implements Predicate<Integer> {
        @Override
        public Boolean apply(@NotNull Integer arg) {
            return arg % 2 == 0;
        }
    }

    class Sum implements Function2<Integer, Integer, Integer> {
        @Override
        public Integer apply(@NotNull Integer a, @NotNull Integer b) {
            return a + b;
        }
    }

    @org.junit.Test
    public void map() throws Exception {
        Function1<Integer, Integer> comp = (new Plus10()).compose(new Mult2());
        Function1<Integer, Integer> plus4 = (new Sum()).bind1(4);
        Function1<Integer, Integer> plus7 = (new Sum()).bind2(7);

        ArrayList<Integer> test = new ArrayList<>();
        ArrayList<Integer> resSum = new ArrayList<>();
        ArrayList<Integer> resMul = new ArrayList<>();
        ArrayList<Integer> resComp = new ArrayList<>();
        ArrayList<Integer> resPlus4 = new ArrayList<>();
        ArrayList<Integer> resPlus7 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            test.add(i);
            resSum.add(i + 10);
            resMul.add(i * 2);
            resComp.add((i + 10) * 2);
            resPlus4.add(i + 4);
            resPlus7.add(i + 7);
        }
        ArrayList ans1 = Collections.map(test, new Plus10());
        ArrayList ans2 = Collections.map(test, new Mult2());
        ArrayList ans3 = Collections.map(test, comp);
        ArrayList ans4 = Collections.map(test, plus4);
        ArrayList ans7 = Collections.map(test, plus7);
        for (int i = 0; i < 15; i++) {
            assertEquals(resSum.get(i), ans1.get(i));
            assertEquals(resMul.get(i), ans2.get(i));
            assertEquals(resComp.get(i), ans3.get(i));
            assertEquals(resPlus4.get(i), ans4.get(i));
            assertEquals(resPlus7.get(i), ans7.get(i));
        }
    }

    @org.junit.Test
    public void filter() throws Exception {
        ArrayList<Integer> test = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            test.add(i);
            if (i % 2 == 0)
                res.add(i);
        }
        ArrayList ans = Collections.filter(test, new IsEven());
        for (int i = 0; i < 8; i++) {
            assertEquals(res.get(i), ans.get(i));
        }
    }

    @org.junit.Test
    public void takeWhile() throws Exception {
        ArrayList<Integer> test = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Integer> res2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            test.add(i * 2);
            res.add(i * 2);
            res2.add(i * 2);
        }
        for (int i = 0; i < 5; i++) {
            test.add(i * 2 - 1);
            res2.add(i * 2 - 1);
        }
        ArrayList ans = Collections.takeWhile(test, new IsEven());
        ArrayList ans2 = Collections.takeWhile(test, Predicate.ALWAYS_TRUE());
        assertEquals(res.size(), ans.size());
        assertEquals(res2.size(), ans2.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(res.get(i), ans.get(i));
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(res2.get(i), ans2.get(i));
        }
    }

    @org.junit.Test
    public void takeUnless() throws Exception {
        ArrayList<Integer> test = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            test.add(i * 2 - 1);
            res.add(i * 2 - 1);
        }
        for (int i = 0; i < 5; i++) {
            test.add(i * 2);
        }
        ArrayList ans = Collections.takeUnless(test, new IsEven());
        ArrayList ans2 = Collections.takeUnless(test, (new IsEven()).not());
        assertEquals(0, ans2.size());
        assertEquals(res.size(), ans.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(res.get(i), ans.get(i));
        }
    }

    @org.junit.Test
    public void foldr() throws Exception {
        ArrayList<Integer> test = new ArrayList<>();
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
            test.add(i);
            sum += i;
        }
        Integer ans = Collections.foldr(test, new Sum(), 0);
        assertEquals(sum, (int) ans);
    }

    @org.junit.Test
    public void foldl() throws Exception {
        ArrayList<Integer> test = new ArrayList<>();
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
            test.add(i);
            sum += i;
        }
        Integer ans = Collections.foldl(test, new Sum(), 0);
        assertEquals(sum, (int) ans);
    }

}