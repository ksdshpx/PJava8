package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/25
 * Time: 10:07
 * Description:流的短路
 */
public class TestStream7 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.stream().filter(
                e -> {
                    int length = e.length();
                    System.out.println(e);
                    return length == 5;
                })
                .limit(1).forEach(e -> System.out.println(e.length()));
    }
}
