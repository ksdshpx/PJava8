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
 */
public class MyTest7 {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = MyTest7.class.getClassLoader();
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
    }
}
