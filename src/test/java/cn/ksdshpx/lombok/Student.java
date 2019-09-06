package cn.ksdshpx.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/5
 * Time: 17:10
 * Description:lombok学习
 */
public class Student {
    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)
    private String name;
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private Integer age;
    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.PROTECTED)
    private String email;
    @Setter(AccessLevel.PACKAGE)
    @Getter(AccessLevel.PACKAGE)
    private Integer status;
}
