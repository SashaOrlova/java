import ru.java.homework.ZipFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.min;
import static org.junit.Assert.assertEquals;

public class ZipFileTest {

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

    @org.junit.Test
    public void mainSimple() throws Exception {
        ZipFile.findZip("src/test/resources/test1", ".*");
        ArrayList<String> resultFile = getArrayListFromFile("src/test/resources/result/abcd.txt");
        ArrayList<String> unzipFile = getArrayListFromFile("src/test/resources/testunzip/abcd.txt");
        for (int i = 0; i < min(resultFile.size(), unzipFile.size()); i++) {
            assertEquals(true, resultFile.get(i).equals(unzipFile.get(i)));
        }
        resultFile = getArrayListFromFile("src/test/resources/result/bcd.txt");
        unzipFile = getArrayListFromFile("src/test/resources/testunzip/bcd.txt");
        for (int i = 0; i < min(resultFile.size(), unzipFile.size()); i++) {
            assertEquals(true, resultFile.get(i).equals(unzipFile.get(i)));
        }
    }

    @org.junit.Test
    public void mainWithRegex() throws Exception {
        ZipFile.findZip("src/test/resources", "a.*");
        File f2 = new File("src/test/resources/result/notFound.txt");
        assertEquals(false, f2.exists());
        ArrayList<String> resultFile = getArrayListFromFile("src/test/resources/result/abcde.txt");
        ArrayList<String> unzipFile = getArrayListFromFile("src/test/resources/testunzip/abcde.txt");
        for (int i = 0; i < min(resultFile.size(), unzipFile.size()); i++) {
            assertEquals(true, resultFile.get(i).equals(unzipFile.get(i)));
        }
    }
}