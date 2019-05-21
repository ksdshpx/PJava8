package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.List;

public class TestLambda2 {
    public static void main(String[] args) {
        MyInterface myInterface = () -> {};
        System.out.println(myInterface.getClass().getName());
        System.out.println(myInterface.getClass().getInterfaces()[0]);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda!");
            }
        }).start();
        new Thread(() -> System.out.println("Hello Lambda!!")).start();
        System.out.println("-----------------");
        List<String> list = Arrays.asList("hello","world","hello world");
        list.forEach((str) -> System.out.println(str.toUpperCase()));
    }
}

@FunctionalInterface
interface MyInterface{
    void myMethod();
}

@FunctionalInterface
interface MyInterface2{
    void myMethod2();
}
