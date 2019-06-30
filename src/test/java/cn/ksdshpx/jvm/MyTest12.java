package cn.ksdshpx.jvm;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/25
 * Time: 11:20
 * Description:ServiceLoader在SPI中的重要作用
 */
public class MyTest12 {
    public static void main(String[] args) {
        //Thread.currentThread().setContextClassLoader(MyTest12.class.getClassLoader().getParent());
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("class:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("线程上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader:" + ServiceLoader.class.getClassLoader());
    }
}
