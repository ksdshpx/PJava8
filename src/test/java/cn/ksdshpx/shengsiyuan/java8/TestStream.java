package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/3
 * Time: 14:19
 * Description:Stream流
 */
public class TestStream {
    public static void main(String[] args) {
        Stream stream1 = Stream.of("Hello", "World", "Hello World");
        String[] myArray = new String[]{"Hello", "World", "Hello World"};
        Stream stream2 = Stream.of(myArray);
        Stream stream3 = Arrays.stream(myArray);
        List<String> list = Arrays.asList(myArray);
        Stream stream4 = list.stream();
        System.out.println("-----------------");
        IntStream.of(new int[]{5, 6, 7}).forEach(System.out::println);
        IntStream.range(3, 8).forEach(System.out::println);
        IntStream.rangeClosed(3, 8).forEach(System.out::println);
        System.out.println("----------------");
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(intList.stream().map(i -> i * 2).reduce(0, Integer::sum));
    }
}
