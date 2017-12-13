import org.junit.Test;
import ru.java.test.*;

import static org.junit.Assert.*;

public class InjectorTest {
    @Test
    public void initializeSimple() throws Exception {
        assertTrue(Injector.initialize(Test1.class, new Class[0]) instanceof Test1);
    }

    @Test
    public void initializeTwo() throws Exception {
        Class<?>[] dep = new Class<?>[1];
        dep[0] = Test1.class;
        assertTrue(Injector.initialize(Test2.class, dep) instanceof Test2);
    }

    @Test(expected = Injector.ImplementationNotFoundException.class)
    public void initializeWithoutDependencies() throws Exception {
        Injector.initialize(Test2.class, new Class[0]);
    }

    @Test(expected = Injector.InjectionCycleException.class)
    public void initializeWithCycle() throws Exception {
        Injector.initialize(Test3.class, new Class[0]);
    }

    @Test
    public void initializeInterface() throws Exception {
        Class<?>[] dep = new Class<?>[1];
        dep[0] = Test1.class;
        assertTrue(Injector.initialize(Test5.class, dep) instanceof Test5);
    }

    @Test(expected = Injector.AmbiguousImplementationException.class)
    public void initializeInterfaceFalse() throws Exception {
        Class<?>[] dep = new Class<?>[2];
        dep[0] = Test1.class;
        dep[1] = Test4.class;
        assertTrue(Injector.initialize(Test5.class, dep) instanceof Test5);
    }

    @Test
    public void initialLong() throws Exception {
        Class<?>[] dep = new Class<?>[2];
        dep[0] = Test1.class;
        dep[1] = Test2.class;
        assertTrue(Injector.initialize(Test6.class, dep) instanceof Test6);
    }

    @Test(expected = Injector.InjectionCycleException.class)
    public void initializeWithCycleLong() throws Exception {
        Class<?>[] dep = new Class<?>[1];
        dep[0] = Test3.class;
        assertTrue(Injector.initialize(Test7.class, dep) instanceof Test7);
    }

    @Test
    public void initialLongInterface() throws Exception {
        Class<?>[] dep = new Class<?>[3];
        dep[0] = Test1.class;
        dep[1] = Test2.class;
        dep[2] = Test6.class;
        assertTrue(Injector.initialize(Test8.class, dep) instanceof Test8);
    }

    @Test
    public void initialTwoInterface() throws Exception {
        Class<?>[] dep = new Class<?>[1];
        dep[0] = Test4.class;
        assertTrue(Injector.initialize(Test8.class, dep) instanceof Test8);
    }

    @Test
    public void initialTwoInterfaceLong() throws Exception {
        Class<?>[] dep = new Class<?>[2];
        dep[0] = Test4.class;
        dep[1] = Test8.class;
        assertTrue(Injector.initialize(Test9.class, dep) instanceof Test9);
    }

    @Test
    public void initialLongInterface1() throws Exception {
        Class<?>[] dep = new Class<?>[2];
        dep[0] = Test1.class;
        dep[1] = Test2.class;
        assertTrue(Injector.initialize(Test6.class, dep) instanceof IntTest2);
    }

    @Test
    public void initialNotInstance() throws Exception {
        Class<?>[] dep = new Class<?>[2];
        dep[0] = Test1.class;
        dep[1] = Test2.class;
        assertFalse(Injector.initialize(Test6.class, dep) instanceof Test2);
    }

    @Test
    public void initialTimes() throws Exception {
        Class<?>[] dep = new Class<?>[2];
        dep[0] = TestTimes1.class;
        dep[1] = TestTimes2.class;
        assertTrue(Injector.initialize(TestTimes3.class, dep) instanceof TestTimes3);
        assertEquals(1, TestTimes1.count);
        assertEquals(1, TestTimes2.count);
        assertEquals(1, TestTimes3.count);
    }
}