package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class TestFunctionInterface {
    public static void main(String[] args) {
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0]);
        System.out.println("----------------");
        List<String> names = Arrays.asList("zhangSan", "liSi", "wangWu", "zhaoLiu", "tianQi");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(names);
        System.out.println("-----------------");
        Collections.sort(names,(o1,o2) -> o1.compareTo(o2));
        System.out.println(names);
        System.out.println("-----------------");
        TestFunctionInterface tfi = new TestFunctionInterface();
        System.out.println(tfi.compute(1,(value) -> value * 2));
    }

    public int compute(int a,Function<Integer,Integer> function){
        int result = function.apply(a);
        return result;
    }
}
