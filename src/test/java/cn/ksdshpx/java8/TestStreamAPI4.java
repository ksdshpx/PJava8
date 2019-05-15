package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Stream API的排序
 * 1.sort:自然排序
 * 2.sort(Comparator):定制排序
 */
public class TestStreamAPI4 {
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
        List<String> list = Arrays.asList("ccc", "aaa", "bbb", "eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("---------------------");
        emps.stream()
                .sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()))
                .forEach(System.out::println);
    }
}
