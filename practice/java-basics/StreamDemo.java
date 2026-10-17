import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Stream 练习：javac StreamDemo.java && java StreamDemo
public class StreamDemo {

    record Employee(String name, String dept, double salary) {}

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("张三", "研发部", 15000),
                new Employee("李四", "研发部", 18000),
                new Employee("王五", "测试部", 12000),
                new Employee("钱七", "产品部", 16000)
        );

        List<String> highPaid = employees.stream()
                .filter(e -> e.salary() > 14000)
                .map(Employee::name)
                .sorted()
                .toList();
        System.out.println("薪资大于14000的：" + highPaid);

        Map<String, Double> avgByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.averagingDouble(Employee::salary)));
        System.out.println("部门平均薪资：" + avgByDept);
    }
}
