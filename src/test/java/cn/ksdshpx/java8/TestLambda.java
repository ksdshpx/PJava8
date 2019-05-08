package cn.ksdshpx.java8;

import org.junit.Test;

import java.util.*;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/5/8
 * Time: 16:07
 * Description:测试Lambda表达式
 */
public class TestLambda {
    /**
     * 使用匿名内部类
     */
    @Test
    public void test(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    //Lambda表达式
    @Test
    public void test2(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    List<Employee> emps = Arrays.asList(
            new Employee("zhangSan",30,6666.88),
            new Employee("liSi",45,7943.99),
            new Employee("wangWu",18,2333.90),
            new Employee("zhaoLiu",88,1900.88),
            new Employee("tianQi",38,3440.85)
    );
    //需求:获取当前公司中员工年龄大于35的员工信息
    public List<Employee> filterEmployeeByAge(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if(employee.getAge() > 35){
                emps.add(employee);
            }
        }
        return emps;
    }

    //需求:获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployeeBySalary(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if(employee.getSalary() > 5000){
                emps.add(employee);
            }
        }
        return emps;
    }

    @Test
    public void test3(){
        List<Employee> employees = filterEmployeeByAge(emps);
        System.out.println(employees);
    }

    //优化方式一
    public List<Employee> filterEmployee(List<Employee> list,MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if(mp.test(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }

    @Test
    public void test4(){
        List<Employee> employees = filterEmployee(emps,new FilterEmployeeByAge());
        System.out.println(employees);
        employees = filterEmployee(emps,new FilterEmployeeBySalary());
        System.out.println(employees);
    }
}
