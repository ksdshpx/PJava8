package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
}
