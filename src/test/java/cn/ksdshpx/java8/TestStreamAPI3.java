package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream API的映射
 * 1.map:接收Lambda,将元素转换成其他形式或提取信息。接收一个函数作为参数，
 * 该函数会被应用到每个元素上，并将其映射成一个新的元素
 * <p>
 * 2.flatMap:接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
 */
public class TestStreamAPI3 {
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
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("-----------------------");
        emps.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        Stream<Stream<Character>> stream = list.stream()
                .map(TestStreamAPI3::filterCharacter);
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });
        Stream<Character> stream2 = list.stream()
                .flatMap(TestStreamAPI3::filterCharacter);
        stream2.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
}
