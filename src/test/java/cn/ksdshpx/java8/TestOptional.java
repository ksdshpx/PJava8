package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/6/30
 * Time: 21:58
 * Description:Optional容器类
 */
public class TestOptional {
    @Test
    public void test(){
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(emp);
    }

    @Test
    public void test2(){
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }

    @Test
    public void test3(){
        //Optional<Employee> op = Optional.ofNullable(new Employee());
        Optional<Employee> op = Optional.ofNullable(new Employee());
        /*if(op.isPresent()){
            System.out.println(op.get());
        }*/
        /*Employee emp = op.orElse(new Employee("zhangSan",20,999.99, Employee.Status.BUSY));
        System.out.println(emp);*/
        Employee emp = op.orElseGet(() -> new Employee("liSi", 20, 987.89, Employee.Status.BUSY));
        System.out.println(emp);
    }

    @Test
    public void test4(){
        Optional<Employee> op = Optional.of(new Employee("liSi", 20, 987.89, Employee.Status.BUSY));
        Optional<String> name = op.map((e) -> e.getName());
        System.out.println(name.get());
        System.out.println();
        Optional<String> name2 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(name2.get());
    }
}
