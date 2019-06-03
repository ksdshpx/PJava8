package cn.ksdshpx.jvm;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/3
 * Time: 16:04
 * Description:类加载器准备阶段以及初始化阶段
 */
public class MyTest6 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println("count1:"+Singleton.count1);
        System.out.println("count2:"+Singleton.count2);
    }
}

class Singleton {
    public static int count1;

    private static Singleton instance = new Singleton();

    private Singleton() {
        count1++;
        count2++;
        System.out.println(count1);
        System.out.println(count2);
    }

    public static int count2 = 0;

    public static Singleton getInstance() {
        return instance;
    }
}
