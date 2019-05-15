package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Stream API的筛选与切片
 * 1.filter:接收一个Lambda，从流中排除某些元素
 * 2.limit:截断流，使其元素不超过给定数量
 * 3.skip(n):跳过元素，返回一个扔掉了前n个元素的流，若流中元素不满n个，则返回一个空流，与limit(n)互补
 * 4.distinct:筛选，通过流所生成的元素hashCode()和equals()方法去除重复元素
 */
public class TestStreamAPI2 {
    List<Employee> emps = Arrays.asList(
            new Employee("zhangSan", 30, 6666.88),
            new Employee("liSi", 45, 7943.99),
            new Employee("wangWu", 18, 2333.90),
            new Employee("zhaoLiu", 88, 1900.88),
            new Employee("tianQi", 38, 3440.85),
            new Employee("tianQi", 38, 3440.85),
            new Employee("tianQi", 38, 3440.85)
    );

    @Test
    public void test() {
        emps.stream()
                .filter((e) -> {
                    System.out.println("Stream的中间操作");
                    return e.getAge() > 30;
                })
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        emps.stream()
                .filter((e) -> e.getSalary() > 2000)
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test3(){
        emps.stream()
                .filter((e -> e.getSalary() > 2000))
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }
}
