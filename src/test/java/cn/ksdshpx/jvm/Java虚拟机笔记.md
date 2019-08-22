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
    
- **类加载器深入解析与重要特性分析**

    - 类加载原理图

      ![1566393725012](C:\Users\south\AppData\Roaming\Typora\typora-user-images\1566393725012.png)

![1566394386095](C:\Users\south\AppData\Roaming\Typora\typora-user-images\1566394386095.png)

- 类的加载

  - 类的加载的最终产品是位于内存中的Class对象

  - Class对象封装了类在方法区内的数据结构，并且向Java程序员提供了访问方法区内的数据结构的接口
  - 有两种类型的类加载器
    - Java虚拟机自带的类加载器
      1. 根类加载器（BootStrap）
      2. 扩展类加载器（Extension）
      3. 系统(应用)类加载器（System/Application）
    - 用户自定义的类加载器
      1. java.lang.ClassLoader的子类
      2. 用户可以定制类的加载方式
  - 类加载器并不需要等到某个类被“首次主动使用”时再加载它
  - JVM规范允许类加载器在预料某个类将要被使用时就预先加载它，如果在预先加载的过程中遇到了.class文件缺失或存在错误，类加载器必须在<u>程序首次主动</u>使用该类时才报告错误（LinkageError错误），如果这个类一直没有被程序主动使用，那么类加载器就不会报告错误

- 类的验证

  - 类被加载后，就进入到了连接阶段。连接就是将已经读入到内存的类的二进制数据合并到虚拟机的运行时环境中去
  - 类的验证内容
    - 类文件的结构检查
    - 语义检查
    - 字节码验证
    - 二进制兼容性的验证

- 类的准备

  - 在准备阶段，Java虚拟机为类的静态变量分配内存，并设置默认的初始值。例如对以下的Sample类，在准备阶段，将为int类型的静态变量a分配4个字节的内存空间，并且赋予默认值0，为long类型的静态变量分配8个字节的内存空间，并赋予默认追0。

  ```java
  public class Sample{
  	private static int a = 1;
      public static long b;
      static {
          b = 2;
      }
      ...
  }
  ```

- 类的初始化

  - 在初始化阶段，Java虚拟机执行类的初始化语句，为类的静态变量赋予初始值。在程序中，静态变量的初始化有两种途径:(1)在静态变量的声明处进行初始化;(2)在静态代码块中进行初始化。例如在以下代码中，静态变量a和b都被显示初始化，而静态变量c没有被显示初始化，它将保持默认值0。

  ```java
  public class Sample{
      private static int a = 1;//在静态变量声明处进行初始化
      public static long b;
      public static long c;
      static {
          b = 2;//在静态代码块中进行初始化
      }
      ...
  }
  ```

  - 静态变量的声明语句，以及静态代码块都被看做类的初始化语句，Java虚拟机会按照初始化语句在类文件中的先后顺序来依次执行它们。例如，当以下Sample类被初始化后，它的静态变量a的取值为4

  ```java
  public class Sample{
  	static int a = 1;
      static{
          a = 2;
      }
      static{
          a = 4;
      }
      public static void main(String[] args){
          System.out.println("a="+a);//4
      }
  }
  ```

  - 类的初始化步骤
  
    - 假如这个类还没有加载和连接，那就先进行加载和连接
    - 假如类存在直接父类，并且这个父类还没有被初始化，那就先初始化直接父类
    - 假如类中存在初始化语句，那就依次执行这些初始化语句
  
  - 类的初始化时机
  
    - 当Java虚拟机初始化一个类的时候，要求它的所有父类都已经被初始化，但是这条规则并不适用于接口
      1. 在初始化一个类时，并不会先初始化它所实现的接口
      2. 在初始化一个接口时，并不会初始化它的父接口
  
    - 因此，一个父接口并不会因为它的子接口或实现类的初始化而初始化，只有当程序首次使用特定接口的静态变量时，才会导致该接口的初始化
    - 只有当程序访问的静态变量或静态方法确实在当前类或当前接口中定义时，才可以认为是对类或接口的主动使用
    - 调用ClassLoader的loadClass方法加载一个类，并不是对一个类的主动使用，不会导致类的初始化
  
  - 类加载器
    - 类加载器用来把类加载到Java虚拟机中。从JDK1.2版本开始，类的加载过程采用双亲委托机制，这种机制能更好地保证Java平台的安全。在此委托机制中，除了Java虚拟机自带的根类加载器以外，其余的类加载器都有且只有一个父加载器。当Java程序请求加载器loader1加载Sample类时，loader1首先委托自己的父加载器去加载Sample类，若父加载器能加载，则由父加载器完成加载任务，否则才由加载器loader1本身加载Sample类
    - Java虚拟机自带了以下几种加载器
      1. **根（BootStrap）类加载器**：该加载器没有父加载器。它负责加载虚拟机的核心类库，如java.lang.*等。例如从Sample.java可以看出，java.lang.Object就是由根类加载器加载的。根类加载器从系统属性sun.boot.class.path所指定的目录中加载类库。根类加载器的实现依赖于底层操作系统，属于虚拟机的实现的一部分，它并没有继承java.lang.ClassLoader类
      2. **扩展（Extension)类加载器**：它的父加载器为根类加载器。它从java.ext.dirs系统属性所指定的目录中加载类库，或者从JDK的安装目录的jre\lib\ext子目录（扩展目录）下加载类库，如果把用户创建的JAR文件放在这个目录下，也会自动由扩展类加载器加载。扩展类加载器是纯Java类，是java.lang.ClassLoader类的子类
      3. **系统（System)类加载器**：也称为应用类加载器，它的父加载器为扩展类加载器。它从环境变量classpath或者系统属性java.class.path所指定的目录中加载类，它是用户自定义的类加载器的默认父加载器。系统类加载器是纯Java类，是java.lang.ClassLoader类的子类
      4. 除了以上虚拟机自带的加载器外，用户还可以定制自己的类加载器。Java提供了抽象类java.lang.ClassLoader,所有用户自定义的类加载器都应该继承ClassLoader类