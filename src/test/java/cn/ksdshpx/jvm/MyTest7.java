package cn.ksdshpx.jvm;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/10
 * Time: 14:12
 * Description:类加载器的层级结构
 *      结论：1.数组不是有classloader加载的，而是由java虚拟机动态加载的
 *           2.数组的classloader与数组中元素的classloader是一致的
 *           3.原生类型的classloader为null
 */
public class MyTest7 {
    public static void main(String[] args) throws IOException {
        // ClassLoader classLoader = MyTest7.class.getClassLoader();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        while (null != classLoader) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
        System.out.println("-------------------");
        classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> urls = classLoader.getResources("cn/ksdshpx/jvm/MyTest7.class");
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            System.out.println(url);
        }
        System.out.println("-------------");
        int[] intArray = new int[]{};
        System.out.println(intArray.getClass().getClassLoader());
        System.out.println("-------------");
        MyTest7[] myTest7s = new MyTest7[3];
        System.out.println(myTest7s.getClass().getClassLoader());
    }
}
