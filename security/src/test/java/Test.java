import java.util.Arrays;
import java.util.List;

/**
 * @author sxh
 * @date 2020/3/18
 */
public class Test {
    @org.junit.Test
    public void fun() {
        List<String> list = Arrays.asList("啊啊啊", "别别别");
        System.out.println(list.contains("啊啊啊"));
    }
}
