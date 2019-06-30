package cn.ksdshpx.jvm;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/30
 * Time: 10:02
 * Description:数组创建本质分析
 *      对于数组实例来说，其类型是有jvm在运行期间动态生成的，
 *      表示为[Lcn.ksdshpx.jvm.MyParent4这种形式，其父类型
 *      为java.lang.Object
 *
 *      助记符：
 *          anewarray:表示创建一个引用类型（类、接口、数组）的数组，并将其压入栈顶
 *          newarray:表示创建一个指定原始类型（int、float等）的数组，并将其压入栈顶
 */
public class MyTest4 {
    public static void main(String[] args) {
        //MyParent4 myParent4 = new MyParent4();
        MyParent4[] myParent4s = new MyParent4[1];
        System.out.println(myParent4s.getClass());
        int[] ints = new int[1];
        System.out.println(ints.getClass());//[I
        System.out.println(ints.getClass().getSuperclass());//java.lang.Object
    }
}

class MyParent4{
    static {
        System.out.println("MyParent4 static block!");
    }
}
