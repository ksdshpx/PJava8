package cn.ksdshpx.shengsiyuan.java8;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/3
 * Time: 9:49
 * Description:测试接口中的默认方法
 */
public interface MyInterface1 {
    default void myMethod(){
        System.out.println("MyInterface1");
    }
}
