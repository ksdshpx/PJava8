[TOC]
# 深入理解Java虚拟机

## 1.类加载器

### 1.1 类加载器深度解析与阶段分析

- **类加载**

  - 在Java代码中，类型的加载、连接与初始化过程都是在*程序运行期间*完成的
    - **加载**：常见的情景为将已经存在的字节码文件（.class文件）从磁盘加载到内存中
    - **连接**：将类与类之间的关系确定好
    - **初始化**：对静态变量初始化
  - 提供了更大的灵活性，增加了更多的可能性

- **类加载器深入剖析**

  - Java虚拟机与程序的生命周期
  - 在如下几种情况下，Java虚拟机将结束生命周期
    - 执行了System.exit()方法
    - 程序正常执行结束
    - 程序在执行过程中遇到了异常或错误而终止
    - 由于操作系统出现错误而导致Java虚拟机进程终止

- **类的加载、连接与初始化**

  - **加载**：查找并加载类的二进制数据
  
- **连接**
  
    - 验证：确保被加载的类的正确性
    
    - 准备：为类的<u>静态变量</u>分配内存，并将其初始化为<u>默认值</u>
    
    - 解析：把类中的符号引用转换为直接引用
    
  - **初始化**：为类的静态变量赋予正确的<u>初始值</u> 
  
    ![类加载原理图](C:\Users\south\AppData\Roaming\Typora\typora-user-images\1566125870429.png)
  
- **类的使用与卸载**

  - 使用
  - 卸载

- **类的加载、连接与初始化深入**

  - Java程序对类的使用方式分为两种

    - 主动使用（7种）
      1. 创建类的实例
      2. 访问某个类或接口的静态变量，或者对该静态变量赋值
      3. 调用类的静态方法
      4. 反射
      5. 初始化一个类的子类
      6. Java虚拟机启动时被标明为启动类的类
      7. JDK1.7开始提供的动态语言支持
    - 被动使用
      - 除了以上7种情况，其他使用Java类的方式都被看做是对类的被动使用，都不会导致类的初始化

    ```java
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
    ```

    > 以上代码执行结果如下：
    >
    > MyParent static block!
    > hello

    若代码改为如下：

    ```java
    public class MyTest1 {
        public static void main(String[] args) {
            System.out.println(MyChild.str2);
        }
    }
    
    class MyParent{
        public static String str = "hello";
        static {
            System.out.println("MyParent static block!");
        }
    }
    
    class MyChild extends MyParent{
        public static String str2 = "welcome";
        static {
            System.out.println("MyChild static block!");
        }
    }
    ```

    >以上代码执行结果如下：
    >
    >MyParent static block!
    >MyChild static block!
    >welcome

    > - 对于静态字段来说，只有直接定义了该字段的类才会被初始化
    > - 当一个类在进行初始化时，要求其所有父类都已经初始化完毕了
    > - -XX:+TraceClassLoading，用于追踪类的加载信息并打印出来

  - 所有的Java虚拟机实现必须在每个类或接口被Java程序**首次主动使用**时才初始化他们

- **类的加载**

  - 类的加载指的是将类的.class文件中的二进制数据读入到内存中，将其放在运行时数据区的方法区内，然后在内存中创建一个java.lang.Class对象（Java虚拟机规范并未说明Class对象位于哪里，HotSpot虚拟机将其放在了方法区中）用来封装类在方法区内的数据结构
  - 加载.class文件的方式
    - 从本地系统中直接加载
    - 通过网络下载.class文件
    - 从zip,jar等归档文件中加载.class文件
    - 从专有数据库中提取.class文件
    - 将Java源文件动态编译为.class文件
  
- **常量的本质含义与反编译及助记符详解**

    - 常量在编译阶段会存入调用这个常量的方法所在的类的常量池中

    - 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化

      ```java
      public class MyTest2 {
          public static void main(String[] args) {
              System.out.println(MyParent2.str);
              System.out.println(MyParent2.s);
              System.out.println(MyParent2.i);
              System.out.println(MyParent2.m);
          }
      }
      
      class MyParent2 {
          public static final String str = "hello";
          public static final short s = 7;
          public static final int i = 128;
          public static final int m = 1;
          static {
              System.out.println("MyParent2 static block!");
          }
      }
      ```

      > 以上代码的运行结果：
      >
      > hello
      > 7
      > 128
      > 1

      反编译以上代码：**javap -c MyTest2.class**

      ```java
      Compiled from "MyTest2.java"
      public class cn.ksdshpx.jvm.MyTest2 {
        public cn.ksdshpx.jvm.MyTest2();
          Code:
             0: aload_0
             1: invokespecial #1                  // Method java/lang/Object."<init>":()V
             4: return
      
        public static void main(java.lang.String[]);
          Code:
             0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
             3: ldc           #4                  // String hello
             5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
             8: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
            11: bipush        7
            13: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
            16: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
            19: sipush        128
            22: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
            25: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
            28: iconst_1
            29: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
            32: return
      }
      ```

      > 助记符
      >
      > 1. **ldc**:将int,float或者是String类型的常量值从常量池中推送至栈顶
      > 2. **bipush**:将单字节（-128~127）的常量推送至栈顶
      > 3. **sipush**:将短整型（-32768~32767）的常量推送至栈顶
      > 4. **iconst_1~ iconst_5**:将int类型的1~5推送至栈顶

- 编译期常量与运行期常量的区别及数组创建本质分析
  
  - 当一个常量的值并非编译期间可以确定的，那么其值就不会放到调用类的常量池中，这时在程序运行时，会导致主动使用这个常量所在的类，显然会导致这个类被初始化
  
  ```java
  public class MyTest3 {
      public static void main(String[] args) {
          System.out.println(MyParent3.str);
      }
  }
  
  class MyParent3{
      public static final String str = UUID.randomUUID().toString();
  
      static {
          System.out.println("MyParent3 static block!");
      }
  }
  ```
  
  > 以上代码的运行结果如下：
  >
  > MyParent3 static block!
  > 13af1477-a635-43d9-9fa4-105153d3dcef
  
- 对于数组实例来说，其类型是由JVM在运行期动态生成的，表示为[Lcn.ksdshpx.jvm.MyParent4这种形式，其父类型为java.lang.Object
  
  ```java
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
  ```
  
  >以上代码的运行结果如下：
  >
  >class [Lcn.ksdshpx.jvm.MyParent4;
  >class [I
  >class java.lang.Object
  
    反编译以上代码：**javap -c MyTest4.class**
  
  ```java
  Compiled from "MyTest4.java"
  public class cn.ksdshpx.jvm.MyTest4 {
    public cn.ksdshpx.jvm.MyTest4();
      Code:
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
  
    public static void main(java.lang.String[]);
      Code:
         0: iconst_1
         1: anewarray     #2                  // class cn/ksdshpx/jvm/MyParent4
         4: astore_1
         5: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
         8: aload_1
         9: invokevirtual #4                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
        12: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/Object;
  )V
        15: iconst_1
        16: newarray       int
        18: astore_2
        19: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        22: aload_2
        23: invokevirtual #4                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
        26: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/Object;
  )V
        29: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        32: aload_2
        33: invokevirtual #4                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
        36: invokevirtual #6                  // Method java/lang/Class.getSuperclass:()Ljava/lang/Clas
  s;
        39: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/Object;
  )V
        42: return
  }
  
  ```
  
  > 助记符
  >
  > 1. **anewarray**:表示创建一个引用类型（类、接口、数组）的数组，并将其引用值压入栈顶
  > 2. **newarray**:表示创建一个指定原始类型（int、float等）的数组，并将其引用值压入栈顶
  
- **接口初始化规则与类加载准备阶段和初始化阶段的重要意义分析**

    - 当一个接口在初始化时，并不要求其父接口都完成了初始化
    - 只有在真正使用父接口的时候（如引用接口中所使用的常量），才会初始化

    ```java
    public class MyTest5 {
        public static void main(String[] args) {
            System.out.println(MyChild5.b);
        }
    }
    
    interface MyParent5{
        public static int a = 5;
    }
    
    interface MyChild5 extends MyParent5{
        public static int b = 6;
    }
    ```

    > 删除MyParent5.class以及MyChild5.class,以上代码运行正常

    若程序改为如下

    ```java
    public class MyTest5 {
        public static void main(String[] args) {
            System.out.println(MyChild5.b);
        }
    }
    
    interface MyParent5{
        public static int a = 5;
    }
    
    interface MyChild5 extends MyParent5{
        public static int b = new Random().nextInt(2);
    }
    ```

    > 以上代码删除MyChild5.class会报错

    ```java
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
    ```

    > 以上代码的运行结果如下：
    >
    > 1
    > 1
    > count1:1
    > count2:0


