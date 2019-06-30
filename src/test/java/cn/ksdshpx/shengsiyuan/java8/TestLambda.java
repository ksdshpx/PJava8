package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestLambda {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("-----------------");
        for (Integer i : list) {
            System.out.println(i);
        }
        System.out.println("-----------------");
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println(i);
            }
        });
        System.out.println("------------------");
        list.forEach((i) -> System.out.println(i));
        System.out.println("------------------");
        list.forEach(System.out::println);
    }
}
