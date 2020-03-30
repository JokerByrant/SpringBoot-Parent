import org.junit.Test;

import java.util.*;

/**
 * @author sxh
 * @date 2020/3/30
 */
public class AppTest {
    @Test
    public void fun() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("a", Arrays.asList("a"));
        List<String> a = map.getOrDefault("b", Collections.emptyList());
        List<String> b = map.get("b");
        System.out.println(a);
        System.out.println(b);
    }
}
