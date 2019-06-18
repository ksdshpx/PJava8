package cn.ksdshpx.jvm;

import java.io.*;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/11
 * Time: 16:38自定义类加载器
 * Description:
 */
public class MyClassLoader extends ClassLoader {
    private String classLoaderName;

    public MyClassLoader(String classLoaderName) {
        super();//使用系统类加载器作为自定义类加载器的父类
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("Use myClassLoader!!");
        byte[] data = loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream in = null;
        OutputStream baos = null;
        byte[] bytes = null;
        try {
            in = new FileInputStream("d:\\"+name.replace(".","\\") + ".class");
            baos = new ByteArrayOutputStream();
            bytes = new byte[in.available()];
            int len = 0;
            while ((len = in.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader");
        Class<?> clazz = myClassLoader.loadClass("cn.ksdshpx.jvm.MyTest1");
        System.out.println("Class:" + clazz.hashCode());
        System.out.println("ClassLoader:" + clazz.getClassLoader());
    }
}
