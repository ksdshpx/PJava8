package cn.ksdshpx.shengsiyuan.java8;

import java.util.function.Function;

public class TestFunctionInterface2 {
    public static void main(String[] args) {
        TestFunctionInterface2 tfi2 = new TestFunctionInterface2();
        System.out.println(tfi2.compute(2, value -> value * 3, value -> value * value));
        System.out.println(tfi2.compute2(2, value -> value * 3, value -> value * value));
    }

    public int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(a);
    }

    public int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a);
    }
}
