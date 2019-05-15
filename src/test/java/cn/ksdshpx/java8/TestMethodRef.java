package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用"方法引用"
 *         (可以理解为方法引用是Lambda表达式的另外一种表现形式)
 *
 *  语法格式一：
 *      对象::实例方法名
 *  语法格式二：
 *      类::静态方法名
 *  语法格式三：
 *      类::实例方法名
 *
 *  注意：①Lambda体中调用方法的参数列表与返回值类型要与函数式接口中抽象方法的参数列表
 *         和返回值类型一致
 *       ②若Lambda参数列表的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，
 *         可以使用类::实例方法名
 *
 *
 *  构造器引用
 *      类名::new
 *   注意：需要调用的构造器的参数列表需要与函数式接口的抽象方法的参数列表一致
 *
 *
 *  数组引用：
 *      Type::new
 */
public class TestMethodRef {

    //对象::实例方法名
    @Test
    public void test(){
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("haha");

        Consumer<String> con2 = System.out::println;
        con2.accept("heihei");
    }

    @Test
    public void test2(){
        Employee emp = new Employee();
        emp.setName("zhangSan");
        Supplier<String> sup = () -> emp.getName();
        System.out.println(sup.get());
        Supplier<String> sup2 = emp::getName;
        System.out.println(sup2.get());
    }

    //类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        Comparator<Integer> com2 = Integer::compare;
    }

    //类::实例方法名
    @Test
    public void test4(){
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);
        BiPredicate<String,String> bp2 = String::equals;
    }

    //构造器引用（类名::new）
    @Test
    public void test5(){
        Supplier<Employee> sup = () -> new Employee();
        System.out.println(sup.get());
        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());
    }

    @Test
    public void test6(){
        Function<String,Employee> fun = (x) -> new Employee(x);
        Employee emp = fun.apply("liSi");
        System.out.println(emp);

        Function<String,Employee> fun2 = Employee::new;
        Employee emp2 = fun2.apply("wangWu");
        System.out.println(emp2);
    }

    //数组引用（Type::new）
    @Test
    public void test7(){
        Function<Integer,String[]> fun = (x) -> new String[x];
        System.out.println(fun.apply(10).length);
        Function<Integer,String[]> fun2 = String[]::new;
        System.out.println(fun2.apply(20).length);
    }
}
