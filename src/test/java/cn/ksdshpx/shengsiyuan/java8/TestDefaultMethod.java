package cn.ksdshpx.shengsiyuan.java8;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/3
 * Time: 9:48
 * Description:默认方法
 */
public class TestDefaultMethod implements MyInterface1,MyInterface9{
    public static void main(String[] args) {
        TestDefaultMethod tdm = new TestDefaultMethod();
        tdm.myMethod();
    }

    @Override
    public void myMethod() {
        MyInterface9.super.myMethod();
    }
}
