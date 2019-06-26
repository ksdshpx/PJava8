package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/26
 * Time: 9:36
 * Description:Stream分组与分区
 */
public class TestStream8 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhangSan", "liSi", "wangWu", "zhaoLiu");
        list1.stream().flatMap(item -> list2.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("------------------");
        Teacher teacher1 = new Teacher("zhangSan",20,100);
        Teacher teacher2 = new Teacher("liSi",20,90);
        Teacher teacher3 = new Teacher("wangWu",30,90);
        Teacher teacher4 = new Teacher("zhangSan",40,80);
        List<Teacher> list = Arrays.asList(teacher1,teacher2,teacher3,teacher4);
        //Map<String, List<Teacher>> map = list.stream().collect(Collectors.groupingBy(Teacher::getName));
        //System.out.println(map);
        //Map<Integer, List<Teacher>> map = list.stream().collect(Collectors.groupingBy(Teacher::getAge));
        //System.out.println(map);
        //Map<String, Long> map = list.stream().collect(Collectors.groupingBy(Teacher::getName, Collectors.counting()));
        //Map<String, Double> map = list.stream().collect(Collectors.groupingBy(Teacher::getName, Collectors.averagingInt(Teacher::getScore)));
        Map<Boolean, List<Teacher>> map = list.stream().collect(Collectors.partitioningBy(stu -> stu.getScore() > 90));
        System.out.println(map);
    }
}
