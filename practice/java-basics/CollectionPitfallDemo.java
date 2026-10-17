import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 集合常见坑：javac CollectionPitfallDemo.java && java CollectionPitfallDemo
public class CollectionPitfallDemo {

    public static void main(String[] args) {
        // Integer 缓存 -128~127
        Integer a = 127, b = 127;
        Integer c = 128, d = 128;
        System.out.println("127 == 127: " + (a == b));      // true
        System.out.println("128 == 128: " + (c == d));      // false
        System.out.println("128 equals 128: " + c.equals(d)); // true

        // Arrays.asList 不能 add
        List<String> fixed = Arrays.asList("a", "b");
        try {
            fixed.add("c");
        } catch (UnsupportedOperationException e) {
            System.out.println("Arrays.asList 不支持 add");
        }
        List<String> mutable = new ArrayList<>(fixed);
        mutable.add("c");
        System.out.println(mutable);
    }
}
