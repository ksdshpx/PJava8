package cn.ksdshpx.shengsiyuan.java8;

import java.util.function.Supplier;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/27
 * Time: 9:46
 * Description:供给型函数式接口Supplier
 */
public class TestSupplier {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "hello supplier!";
        System.out.println(supplier.get());
        System.out.println("--------------");
        Supplier<Person> supplier2 = () -> new Person("zhangSan", 20);
        System.out.println(supplier2.get());
        System.out.println("--------------");
        Supplier<Person> supplier3 = Person::new;
        System.out.println(supplier3.get());
    }
}
