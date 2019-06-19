package cn.ksdshpx.jvm;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/19
 * Time: 9:52
 * Description:类加载器实战剖析与疑难点解析
 */
public class MyTest8 {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("----------------------");
        MyClassLoader myClassLoader = new MyClassLoader("loader1");
        myClassLoader.setPath("d:\\");
        Class<?> clazz = myClassLoader.loadClass("cn.ksdshpx.jvm.MyTest1");
        System.out.println("class:" + clazz.hashCode());
        System.out.println("classloader:" + clazz.getClassLoader());
    }
}
