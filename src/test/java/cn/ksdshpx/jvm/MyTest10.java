package cn.ksdshpx.jvm;

import java.lang.reflect.Method;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/19
 * Time: 10:51
 * Description:类的命名空间深度解析
 */
public class MyTest10 {
    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader1 = new MyClassLoader("loader1");
        MyClassLoader myClassLoader2 = new MyClassLoader("loader2");
        myClassLoader1.setPath("d:\\");
        myClassLoader2.setPath("d:\\");
        Class<?> clazz1 = myClassLoader1.loadClass("cn.ksdshpx.jvm.Person");
        Class<?> clazz2 = myClassLoader2.loadClass("cn.ksdshpx.jvm.Person");
        System.out.println(clazz1 == clazz2);
        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();
        Method method = clazz1.getMethod("setPerson",Object.class);
        method.invoke(object1,object2);
    }
}
