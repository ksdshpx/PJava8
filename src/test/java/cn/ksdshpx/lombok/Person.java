package cn.ksdshpx.lombok;

import lombok.Getter;
import lombok.Setter;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/6
 * Time: 10:40
 * Description:Person实体类
 */
@Setter
@Getter
public class Person {
    private String name;
    private Integer age;
    private Double salary;
    private Boolean flag;
}
