package cn.ksdshpx.lombok;

import lombok.Data;
import lombok.NonNull;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/6
 * Time: 11:00
 * Description:Teacher实体类
 */
@Data
public class Teacher {
    @NonNull
    private String name;
    private Integer age;
    private String email;
    private Boolean flag;
}
