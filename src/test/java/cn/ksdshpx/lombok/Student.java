package cn.ksdshpx.lombok;

import lombok.Getter;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/5
 * Time: 17:10
 * Description:lombok学习
 */
public class Student {
    @Getter
    private String name;
    @Getter
    private Integer age;
    @Getter
    private String email;
}
