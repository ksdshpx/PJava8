package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/5/10
 * Time: 9:44
 * Description:内置的四大核心函数式接口
 * ①Consumer<T>:消费型接口
 * void accept(T t);
 * <p>
 * ②Supplier<T>:供给型接口
 * T  get();
 * ③Function<T,R>:函数型接口
 * R apply(T t);
 * ④Predicate<T>:断言型接口
 * boolean test(T t);
 */
public class TestLambda4 {
    //Consumer<T>:消费型
    @Test
    public void test() {
        happy(300.00, (m) -> System.out.println("买电脑消费" + m + "元"));
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    //Supplier<T>:供给型接口

    @Test
    public void test2() {
        List<Integer> list = getNumber(10, () -> (int) (Math.random() * 100));
        System.out.println(list);
    }

    //产生指定个数的整数，并添加到List中
    public List<Integer> getNumber(int count, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    //Function<T,R>:函数型接口
    @Test
    public void test3() {
        String returnStr = getValue("heLlo,AtguiGU!", (str) -> str.toUpperCase());
        System.out.println(returnStr);
    }

    public String getValue(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    //Predicate<T>:断言型接口
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "Atguigu", "ok", "www", "Friend");
        list = filterStr(list, (str) -> str.length() > 3);
        System.out.println(list);
    }

    public List<String> filterStr(List<String> strList, Predicate<String> predicate) {
        List<String> list = new ArrayList<>();
        for (String str : strList) {
            if (predicate.test(str)) {
                list.add(str);
            }
        }
        return list;
    }
}
