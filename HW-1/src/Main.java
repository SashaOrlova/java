import ru.java.homework.HashMap;
import static java.lang.System.exit;

public class Main {
    static void is_success(boolean condition, String message) {
        if (!condition) {
            System.out.println(message);
            exit(1);
        }
    }

    public static void main(String[] args) {
        HashMap test = new HashMap();
        test.put("aa", "bb");
        test.put("cc","dd");
        is_success(test.get("aa").equals("bb"), "Wrong get");
        is_success(test.get("cc").equals("dd"), "Wrong get");
        is_success(test.size() == 2, "Wrong size");
        is_success(test.contains("cc"), "Wrong contains");
        is_success(test.put("aa","ss").equals("bb"), "Wrong put");
        is_success(test.remove("cc").equals("dd"), "Wrong remove");
        is_success(test.get("aa").equals("ss"), "Wrong contains");
        is_success(test.get("cc") == null, "Wrong contains");
    }
}
