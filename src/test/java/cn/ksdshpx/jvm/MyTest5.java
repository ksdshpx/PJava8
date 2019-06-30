package cn.ksdshpx.jvm;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/3
 * Time: 15:33
 * Description:接口初始化规则
 *      当一个接口在初始化时，并不要求其父接口完成了初始化，
 *      只有在真正使用父接口的时候（如引用接口中所使用的常量），才会初始化
 */
public class MyTest5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}

interface MyParent5{
    public static Thread thread = new Thread(){
        {
            System.out.println("MyParent5 invoked!");
        }
    };
}

class MyChild5 implements MyParent5{
    public static int b = 6;
}
