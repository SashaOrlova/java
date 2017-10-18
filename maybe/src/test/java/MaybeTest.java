import ru.java.homework.Maybe;
import static org.junit.Assert.*;

import java.io.*;

public class MaybeTest {
    @org.junit.Test
    public void just() throws Exception {
        assertEquals(5, (int)Maybe.just(5).get());
        assertEquals("smth", (String)Maybe.just("smth").get());
    }

    @org.junit.Test(expected = Maybe.MaybeException.class)
    public void nothing() throws Exception {
        Maybe.nothing().get();
    }

    @org.junit.Test(expected = Maybe.MaybeException.class)
    public void get() throws Exception {
        assertEquals(5, (int)Maybe.just(5).get());
        assertEquals("smth", (String)Maybe.just("smth").get());
        Maybe.nothing().get();
    }

    @org.junit.Test
    public void isPresent() throws Exception {
        assertEquals(true,  Maybe.just(5).isPresent());
        assertEquals(false,  Maybe.nothing().isPresent());

    }

    @org.junit.Test
    public void readFromFile() {
        File file = new File("src/test/resources/test.txt");
        StringBuilder stringBuffer = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    Integer i = Integer.parseInt(line);
                    stringBuffer.append(Maybe.just(i).map(x-> x * x).get().toString());
                    stringBuffer.append('\n');
                } catch (NumberFormatException e) {
                    stringBuffer.append("null\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Problem with input file in test");
        }
        File fileResult = new File("src/test/resources/testResult.txt");
        try (FileWriter fileWriter = new FileWriter(fileResult)) {
            fileWriter.write(stringBuffer.toString());
        } catch (Exception e) {
            System.out.println("Problem with output file in test");
        }
    }
}