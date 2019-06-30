package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/28
 * Time: 9:51
 * Description:Optional容器类
 */
public class TestOptional {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("hello");
        /*if (optional.isPresent()){
            System.out.println(optional.get());
        }*/
        //推荐的Optional使用方式
        optional.ifPresent(str -> System.out.println(str));
        System.out.println("-------------------");
        optional = Optional.empty();
        System.out.println(optional.orElse("world"));
        System.out.println(optional.orElseGet(() -> "nihao"));
        System.out.println("------------");
        Employee employee1 = new Employee();
        employee1.setName("zhangSan");
        Employee employee2 = new Employee();
        employee2.setName("liSi");
        Company company = new Company();
        company.setName("sfit");
        List<Employee> emps = Arrays.asList(employee1, employee2);
        company.setEmps(emps);
        Optional<Company> optionalCompany = Optional.ofNullable(company);
        System.out.println(optionalCompany.map(theCompany -> theCompany.getEmps()).orElse(Collections.emptyList()));
    }
}
