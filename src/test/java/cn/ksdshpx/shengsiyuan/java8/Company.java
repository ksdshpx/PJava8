package cn.ksdshpx.shengsiyuan.java8;

import java.util.List;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/28
 * Time: 10:21
 * Description:公司类
 */
public class Company {
    private String name;
    private List<Employee> emps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", emps=" + emps +
                '}';
    }
}
