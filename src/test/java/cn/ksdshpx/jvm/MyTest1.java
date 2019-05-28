package cn.ksdshpx.jvm;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/28
 * Time: 10:52
 * Description:测试类的主动使用
 *      注意：对于静态字段来说，只有直接定义了该字段的类才会被初始化
 *           当一个类在进行初始化时，要求其所有父类都已经初始化完毕了
 *
 *      主动使用：
 *      ①创建类的实例
 *      ②访问某个类或接口的静态变量，或者对该静态变量赋值
 *      ③调用类的静态方法
 *      ④反射
 *      ⑤初始化一个类的子类
 *      ⑥Java虚拟机启动时被标识为启动类的类
 *      ⑦JDK1.7提供的动态语言支持
 *
 *      -XX:+TraceClassLoading,用于追踪类的加载信息并打印
 */
public class MyTest1 {
    public static void main(String[] args) {
        System.out.println(MyChild.str);
    }
}

class MyParent{
    public static String str = "hello";

    static {
        System.out.println("MyParent static block!");
    }
}

class MyChild extends MyParent{
    static {
        System.out.println("MyChild static block!");
    }
}
