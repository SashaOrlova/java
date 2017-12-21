import java.util.Comparator;
import java.util.Set;

public class TestFields {
    int x = 0;
    double z = 0;
    Object y = 0;
    Comparator<? extends Set> cmp = null;
    TestFields(int t) {}
    private TestFields(String st, Set<Integer> s) {}
}
