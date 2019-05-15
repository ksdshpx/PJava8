package cn.ksdshpx.java8;

import java.util.Objects;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/5/8
 * Time: 16:17
 * Description:雇员类
 */
public class Employee {
    private String name;
    private Integer age;
    private Double salary;

    public Employee() {
    }

    public Employee(String name){
        this.name = name;
    }

    public Employee(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
