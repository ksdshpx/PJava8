package cn.ksdshpx.shengsiyuan.java8;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/4
 * Time: 14:20
 * Description:Stream实例剖析
 */
public class TestStream3 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "hello world");
        //List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
        //list.forEach(System.out::println);
        //Set<String> set = stream.collect(Collectors.toSet());
        Set<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set.getClass());
        set.forEach(System.out::println);
        System.out.println("-------------");
        stream = Stream.of("hello", "world", "hello world");
        String str = stream.collect(Collectors.joining());
        System.out.println(str);
    }
}
