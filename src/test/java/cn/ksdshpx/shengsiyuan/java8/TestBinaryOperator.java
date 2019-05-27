package cn.ksdshpx.shengsiyuan.java8;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/27
 * Time: 10:03
 * Description:BinaryOperator函数式接口
 */
public class TestBinaryOperator {
    public static void main(String[] args) {
        TestBinaryOperator testBinaryOperator = new TestBinaryOperator();
        System.out.println(testBinaryOperator.compute(1, 2, (a, b) -> a + b));
        System.out.println(testBinaryOperator.compute(1, 2, (a, b) -> a * b));
        System.out.println("--------------");
        System.out.println(testBinaryOperator.getShort("hello123", "world", (a, b) -> a.length() - b.length()));
        System.out.println(testBinaryOperator.getShort("hello123", "world", (a, b) -> a.compareTo(b)));
    }

    public int compute(int a, int b, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(a, b);
    }

    public String getShort(String a, String b, Comparator<String> comparator) {
        return BinaryOperator.minBy(comparator).apply(a, b);
    }
}
