package cn.ksdshpx.shengsiyuan.java8;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TestBiFunction {
    public static void main(String[] args) {
        TestBiFunction tbf = new TestBiFunction();
        System.out.println(tbf.compute(4, 2, (value1, value2) -> value1 + value2));
        System.out.println(tbf.compute(4, 2, (value1, value2) -> value1 - value2));
        System.out.println(tbf.compute(4, 2, (value1, value2) -> value1 * value2));
        System.out.println(tbf.compute(4, 2, (value1, value2) -> value1 / value2));
        System.out.println(tbf.compute2(2, 3, (value1, value2) -> value1 + value2, value -> value * value));
    }

    public int compute(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    public int compute2(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function) {
        return biFunction.andThen(function).apply(a, b);
    }
}
