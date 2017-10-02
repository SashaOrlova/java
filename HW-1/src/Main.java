import ru.java.homework.HashMap;
//import ru.java.homework.HashMapTest;


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
        boolean isTestsPass = true;
        test.put("aa", "bb");
        test.put("cc","dd");
        isTestsPass &= isSuccess(test.get("aa").equals("bb"), "Wrong get");
        isTestsPass &= isSuccess(test.get("cc").equals("dd"), "Wrong get");
        isTestsPass &= isSuccess(test.size() == 2, "Wrong size");
        isTestsPass &= isSuccess(test.contains("cc"), "Wrong contains");
        isTestsPass &= isSuccess(test.put("aa","ss").equals("bb"), "Wrong put");
        isTestsPass &= isSuccess(test.remove("cc").equals("dd"), "Wrong remove");
        isTestsPass &= isSuccess(test.get("aa").equals("ss"), "Wrong get");
        isTestsPass &= isSuccess(test.get("cc") == null, "Wrong get");
        test.clear();
        isTestsPass &= isSuccess(test.size() == 0, "Wrong get");
        if (isTestsPass)
            System.out.println("All tests pass!");
    }
}
