package sp;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        ArrayList<String> files = new ArrayList<>();
        files.add("src/test/resources/test");
        assertEquals(
                Arrays.asList("try (found)"),
                SecondPartTasks.findQuotes(files, "try")
        );
    }

    @Test
    public void testPiDividedBy4() {
        assertTrue(
                Math.abs(Math.PI/4 -
                SecondPartTasks.piDividedBy4() ) < 0.001
        );
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> writers = new HashMap<>();
        writers.put("William Shakespeare", Arrays.asList("Romeo and Juliet", "Much Ado About Nothing"));
        writers.put("Charles Dickens", Arrays.asList("A Christmas Carol"));
        assertEquals(
                "William Shakespeare",
                SecondPartTasks.findPrinter(writers)
        );
    }

    @Test
    public void testCalculateGlobalOrder() {
        List<Map<String, Integer>> list = new ArrayList<>();
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        map1.put("Milk", 3);
        map1.put("Apple", 5);
        map2.put("Orange", 4);
        map2.put("Milk", 5);
        list.add(map1);
        list.add(map2);
        Map<String, Integer> answer = SecondPartTasks.calculateGlobalOrder(list);
        assertEquals((Integer) 8, answer.get("Milk"));
        assertEquals((Integer) 5, answer.get("Apple"));
        assertEquals((Integer) 4, answer.get("Orange"));
    }
}