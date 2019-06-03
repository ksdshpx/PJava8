package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/28
 * Time: 15:56
 * Description:方法引用详解
 *      方法引用分类：
 *          ①类名::静态方法名
 *          ②对象名::实例方法名
 *          ③类名::实例方法名
 *          ④构造方法引用  类名::new
 */
public class TestMethodRef {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.forEach(item -> System.out.println(item));
        list.forEach(System.out::println);
        System.out.println("--------------");
        //类名::静态方法名
        Student student = new Student("zhangSan", 10);
        Student student2 = new Student("liSi", 15);
        Student student3 = new Student("wangWu", 9);
        Student student4 = new Student("zhaoLiu", 20);
        List<Student> students = Arrays.asList(student, student2, student3, student4);
        students.sort((stu1, stu2) -> Student.compareStuByScore(stu1, stu2));
        students.forEach(System.out::println);
        System.out.println("-----使用方法引用----");
        students.sort(Student::compareStuByName);
        students.forEach(System.out::println);
        System.out.println("------------");
        //对象名::实例方法名
        StudentComparator studentComparator = new StudentComparator();
        students.sort(studentComparator::compareStuByScore);
        students.forEach(System.out::println);
        System.out.println("------------");
        //类名::实例方法名
        students.sort(Student::compareByName);
        students.forEach(System.out::println);
        System.out.println("--------------");
        List<String> cities = Arrays.asList("qingDao", "jiNan", "tianJin", "shangHai");
        //Collections.sort(cities, (city1, city2) -> city1.compareToIgnoreCase(city2));
        Collections.sort(cities, String::compareToIgnoreCase);
        cities.forEach(System.out::println);
        System.out.println("------------");
        System.out.println(getString(String::new));
        System.out.println(getString2("haha",String::new));
    }

    public static String getString(Supplier<String> supplier){
        return supplier.get() + "test";
    }

    public static String getString2(String str, Function<String,String> function){
        return function.apply(str);
    }
}
