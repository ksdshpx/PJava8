package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/5/16
 * Time: 9:54
 * Description:StreamAPI的终止操作
 */
public class TestStreamAPI5 {
    List<Employee> emps = Arrays.asList(
            new Employee("zhangSan", 30, 6666.88, Employee.Status.FREE),
            new Employee("liSi", 45, 7943.99, Employee.Status.BUSY),
            new Employee("wangWu", 18, 2333.90, Employee.Status.VOCATION),
            new Employee("zhaoLiu", 88, 1900.88, Employee.Status.BUSY),
            new Employee("zhaoLiu", 88, 1900.88, Employee.Status.BUSY),
            new Employee("zhaoLiu", 88, 1900.88, Employee.Status.BUSY),
            new Employee("tianQi", 38, 3440.85, Employee.Status.FREE)
    );

    /**
     * 1.查找与匹配
     * ①allMatch:检查是否匹配所有元素
     * ②anyMatch:检查是否至少匹配一个元素
     * ③noneMatch:检查是否没有匹配所有元素
     * ④findFirst:返回第一个元素
     * ⑤findAny:返回流中的任意元素
     * ⑥count:返回流中的元素总个数
     * ⑦max:返回流中的最大值
     * ⑧min:返回流中的最小值
     */
    @Test
    public void test() {
        boolean b = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
        boolean b2 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
        boolean b3 = emps.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);
        Optional<Employee> op = emps.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());
        Optional<Employee> op2 = emps.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(op2.get());
        long count = emps.stream()
                .count();
        System.out.println(count);
        Optional<Employee> op3 = emps.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op3.get());
        Optional<Double> op4 = emps.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(op4.get());
    }

    /**
     * 2.归约
     * reduce(T identity,BinaryOperator)/reduce(BinaryOperator):可以
     * 将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("--------------------------");
        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    /**
     * 3.收集
     * collect:将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test3() {
        emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------");
        emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        System.out.println("--------------");
        emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);
        System.out.println("--------------");
        //总数
        Long count = emps.stream()
                .collect(Collectors.counting());
        System.out.println("总员工数：" + count);

        //平均工资
        Double avgSalary = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("平均工资：" + avgSalary);

        //工资总和
        Double sumSalary = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("工资总和：" + sumSalary);

        //工资最高的员工
        Optional<Employee> max = emps.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        //工资最低的员工
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());

        //按状态分组
        Map<Employee.Status, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);

        //多级分组
        Map<Employee.Status, Map<String, List<Employee>>> map2 = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getSalary() < 20) {
                        return "青年";
                    } else if (e.getAge() < 40) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map2);

        //分区
        Map<Boolean, List<Employee>> map3 = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 3000));
        System.out.println(map3);

        //连接
        String nameStr = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(nameStr);
    }
}
