import ru.java.homework.HashMap;
import ru.java.homework.HashMapTest;

import static java.lang.System.exit;

public class Main {
    static boolean isSuccess(boolean condition, String message) {
        if (!condition) {
            System.out.println(message);
           return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HashMap test = new HashMap();
        test.put("aa", "bb");
        test.put("cc","dd");
        isSuccess(test.get("aa").equals("bb"), "Wrong get");
        isSuccess(test.get("cc").equals("dd"), "Wrong get");
        isSuccess(test.size() == 2, "Wrong size");
        isSuccess(test.contains("cc"), "Wrong contains");
        isSuccess(test.put("aa","ss").equals("bb"), "Wrong put");
        isSuccess(test.remove("cc").equals("dd"), "Wrong remove");
        isSuccess(test.get("aa").equals("ss"), "Wrong get");
        isSuccess(test.get("cc") == null, "Wrong get");
    }
}
