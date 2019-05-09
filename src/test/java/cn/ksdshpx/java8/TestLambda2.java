package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/5/9
 * Time: 8:19
 * Description:Lambda表达式语法
 */
public class TestLambda2 {
    //语法格式一:无参无返回值
    @Test
    public void test() {
        int num = 0;//jdk8之后不用加final，但是还是不能改变，只是jdk默认帮我们加上了
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda!" + num);
            }
        };
        r.run();
        System.out.println("-------------------");
        Runnable r2 = () -> System.out.println("Hello Lambda!" + num);
        r2.run();
    }

    //语法格式二:有一个参数，并且无返回值
    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("Hello Consumer!");
    }

    //语法格式三:若只有一个参数，那么参数的小括号可以不写
    @Test
    public void test3() {
        Consumer<String> con = x -> System.out.println(x);
        con.accept("Hello Consumer!");
    }

    //语法格式四:有两个以上的参数，并且Lambda体中有多条语句
    @Test
    public void test4() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口！");
            return Integer.compare(x, y);
        };
    }

    //语法格式五:若Lambda体中只有一条语句，那么return和大括号都可以不写
    @Test
    public void test5() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    //语法格式六:Lambda表达式的参数列表的类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，称为类型推断
    
}
