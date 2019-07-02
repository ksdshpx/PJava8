package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/7/2
 * Time: 9:38
 * Description:Collector源码分析与收集器核心
 */
public class TestStream9 {
    public static void main(String[] args) {
        Human human1 = new Human("zhangSan",80);
        Human human2 = new Human("liSi",90);
        Human human3 = new Human("wangWu",100);
        Human human4 = new Human("zhaoLiu",90);
        List<Human> humans = Arrays.asList(human1,human2,human3,human4);
        List<Human> humans1 = humans.stream().collect(Collectors.toList());
        humans1.forEach(System.out::println);
        System.out.println("------------------");
        System.out.println("count:"+humans.stream().collect(Collectors.counting()));
        System.out.println("count:"+humans.stream().count());
    }
}
