package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/5/10
 * Time: 9:44
 * Description:Lambda表达式练习
 */
public class TestLambda3 {
    List<Employee> emps = Arrays.asList(
            new Employee("zhangSan", 30, 6666.88),
            new Employee("liSi", 45, 7943.99),
            new Employee("wangWu", 18, 2333.90),
            new Employee("zhaoLiu", 88, 1900.88),
            new Employee("tianQi", 38, 3440.85)
    );

    //练习一：调用Collections的sort()方法，通过定制排序比较两个Employee(先按
    //年龄比，年龄相同按姓名比），使用Lambda作为参数传递
    @Test
    public void test() {
        Collections.sort(emps, (e1, e2) -> {
            return e1.getAge() != e2.getAge() ? e1.getAge() - e2.getAge() : e1.getName().compareTo(e2.getName());
        });
        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    //练习二：①声明函数式接口，接口中声明抽象方法public String getValue(String str);
    //②声明类TestLambda,类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值
    //③再将字符串的第二个和第四个索引位置进行截取子串
    @Test
    public void test2() {
        String returnStr = strHandle("SdfFg", (str) -> str.toUpperCase());
        System.out.println(returnStr);
        returnStr = strHandle(returnStr, (str) -> str.substring(2, 4));
        System.out.println(returnStr);
    }

    public String strHandle(String str, MyFunction mf) {
        return mf.getValue(str);
    }

    //练习三：①声明一个带两个泛型的函数式接口，泛型类型为<T,R> T为参数 R为返回值
    //②接口中声明对应抽象方法
    //③在TestLambda类中声明方法，使用接口作为参数，计算两个long型参数的和
    //④再计算两个long型参数的乘积

    @Test
    public void test3() {
        Long result = operate(342, 3, (x, y) -> x + y);
        System.out.println(result);
        result = operate(3, 3, (x, y) -> x * y);
        System.out.println(result);
    }

    public long operate(long t1, long t2, MyFunction2<Long, Long> mf) {
        return mf.getValue(t1, t2);
    }
}
