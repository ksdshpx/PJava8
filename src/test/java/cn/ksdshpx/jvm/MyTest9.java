package cn.ksdshpx.jvm;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/19
 * Time: 10:32
 * Description:扩展类加载器
 */
public class MyTest9 {
    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(MyTest9.class.getClassLoader());
    }
}
