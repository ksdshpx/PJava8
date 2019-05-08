package cn.ksdshpx.java8;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/5/8
 * Time: 16:47
 * Description:
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 5000;
    }
}
