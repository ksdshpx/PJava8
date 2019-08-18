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

  - 所有的Java虚拟机实现必须在每个类或接口被Java程序**首次主动使用**时才初始化他们

- **类的加载**

  - 类的加载指的是将类的.class文件中的二进制数据读入到内存中，将其放在运行时数据区的方法区内，然后在内存中创建一个java.lang.Class对象（Java虚拟机规范并未说明Class对象位于哪里，HotSpot虚拟机将其放在了方法区中）用来封装类在方法区内的数据结构
  - 加载.class文件的方式
    - 从本地系统中直接加载
    - 通过网络下载.class文件
    - 从zip,jar等归档文件中加载.class文件
    - 从专有数据库中提取.class文件
    - 将Java源文件动态编译为.class文件


