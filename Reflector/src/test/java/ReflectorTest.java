import org.junit.Test;
import ru.java.spbau.homework.Reflector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

public class ReflectorTest {
    private static ArrayList<String> getArrayListFromFile(String fileName) throws IOException {
        ArrayList<String> bufferArray = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            bufferArray.add(currentLine);
        }
        bufferedReader.close();
        return bufferArray;
    }

    @Test
    public void SimpleTest() throws Exception {
        Reflector.printStructure(TestFirst.class);
        ArrayList<String> file1 = getArrayListFromFile("TestFirst.java");
        ArrayList<String> file2 = getArrayListFromFile("src/test/resources/TestFirst.java");
        assertArrayEquals(file2.toArray(), file1.toArray());
    }

    @Test
    public void TestFields() throws Exception {
        Reflector.printStructure(TestFields.class);
        ArrayList<String> file1 = getArrayListFromFile("TestFields.java");
        ArrayList<String> file2 = getArrayListFromFile("src/test/resources/TestFields.java");
        assertArrayEquals(file2.toArray(), file1.toArray());
    }

    @Test
    public void TestMethods() throws Exception {
        Reflector.printStructure(TestMethods.class);
        ArrayList<String> file1 = getArrayListFromFile("TestMethods.java");
        ArrayList<String> file2 = getArrayListFromFile("src/test/resources/TestMethods.java");
        assertArrayEquals(file2.toArray(), file1.toArray());
    }

    @Test
    public void TestInner() throws Exception {
        Reflector.printStructure(TestInnerClass.class);
        ArrayList<String> file1 = getArrayListFromFile("TestInnerClass.java");
        ArrayList<String> file2 = getArrayListFromFile("src/test/resources/TestInnerClass.java");
        assertArrayEquals(file2.toArray(), file1.toArray());
    }
}