## Lambda表达式简介

### 一、前言

- java8中，Lambda表达式是匿名函数的一种语法糖，简化了匿名内部类的冗杂代码

- java8中，每一个Lambda表达式必须有一个函数式接口与之对应

### 二、语法

|参数列表|操作符|代码体(表达式/代码块)|
|:---:|:----:|:----:|
|(int x,int y)|->|x+y|
|(x,y)|->|x+y|
|(int x,int y)|->|{return x+y;}|
|(x,y)|->|{return x+y;}|




### 三、使用举例

前置：函数式接口

```java
//数学操作
@FunctionalInterface
public interface MathOperation {
    int operation(int a, int b);
    //默认实现
    default int addition(int a, int b) {
        return a + b;
    }
}
```

- 声明参数类型

```java
MathOperation addition = (int a, int b) -> a + b;
```

- 不声明参数类型
  ```java
  MathOperation subtraction = (a, b) -> a - b;
  ```
- 返回语句块
```java
MathOperation multiplication = (a, b) -> {
            System.out.println("this is multiplication");
            return a * b;
        };
```
- 配合Predicate
```java
//筛选符合predicate的值
private static void filter(List<Integer> list, Predicate<Integer> predicate) {
    for (Integer n : list) {
        if (predicate.apply(n)) {
            System.out.print(n + " ");
        }
    }
}
//筛选出1到5之间的偶数
filter(ImmutableList.of(1, 2, 3, 4, 5), (n) -> n != null && n % 2 == 0)
//输出：2 4
```


### 四、注意事项：
- 不能在lambda内部修改定义在域外的变量，可以读取
```java
    int a = 0;
    new Thread(
           () -> a++
           //Variable used in lambda expression should be final or effectively final
    ).start();
```
- 当代码体不修改Lambda表达式提供的参数时候，代码体可以替换为方法引用，如果修改参数的时候只能使用Lambda表达式
```java
    List<String> aList = ImmutableList.of("1", "2", "3");
    aList.forEach(string -> System.out.println(string));
    aList.forEach(System.out::println);//可以使用方法引用
    aList.forEach(string -> System.out.println("value:" + string))//只能使用Lambda表达式
```

### 五、原理介绍

##### 1、测试源码

```java
public class LambdaTest1 {
    public static void main(String[] args) {
        new Thread(
                () -> System.out.println("this is lambda")
        );
    }
}
```

##### 2、反编译字节码私有成员
结果如下：
```java
public class com.xin.lambda.LambdaTest1 {
  public com.xin.lambda.LambdaTest1();
  public static void main(java.lang.String[]);
  private static void lambda$main$0();
}
```

从反编译结果中可以看到，测试类中增加了一个私有静态方法lambda\$main\$0()。这一私有静态方法提供给main方法调用，这种实现方式和匿名内部类差别较大，后者编译后生成两个class文件。

##### 3、查看更详细的字节码信息

```java
Classfile /C:/Users/69040/Desktop/LambdaTest1.class
  Last modified 2018-8-23; size 1184 bytes
  MD5 checksum ea1e2b1d14787a49549f6984174f1b37
  Compiled from "LambdaTest1.java"
public class com.xin.lambda.LambdaTest1
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #9.#24         // java/lang/Object."<init>":()V
   #2 = Class              #25            // java/lang/Thread
   #3 = InvokeDynamic      #0:#30         // #0:run:()Ljava/lang/Runnable;
   #4 = Methodref          #2.#31         // java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
   #5 = Fieldref           #32.#33        // java/lang/System.out:Ljava/io/PrintStream;
   #6 = String             #34            // this is lambda
   #7 = Methodref          #35.#36        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #8 = Class              #37            // com/xin/lambda/LambdaTest1
   #9 = Class              #38            // java/lang/Object
  #10 = Utf8               <init>
  #11 = Utf8               ()V
  #12 = Utf8               Code
  #13 = Utf8               LineNumberTable
  #14 = Utf8               LocalVariableTable
  #15 = Utf8               this
  #16 = Utf8               Lcom/xin/lambda/LambdaTest1;
  #17 = Utf8               main
  #18 = Utf8               ([Ljava/lang/String;)V
  #19 = Utf8               args
  #20 = Utf8               [Ljava/lang/String;
  #21 = Utf8               lambda$main$0
  #22 = Utf8               SourceFile
  #23 = Utf8               LambdaTest1.java
  #24 = NameAndType        #10:#11        // "<init>":()V
  #25 = Utf8               java/lang/Thread
  #26 = Utf8               BootstrapMethods
  #27 = MethodHandle       #6:#39         // invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #28 = MethodType         #11            //  ()V
  #29 = MethodHandle       #6:#40         // invokestatic com/xin/lambda/LambdaTest1.lambda$main$0:()V
  #30 = NameAndType        #41:#42        // run:()Ljava/lang/Runnable;
  #31 = NameAndType        #10:#43        // "<init>":(Ljava/lang/Runnable;)V
  #32 = Class              #44            // java/lang/System
  #33 = NameAndType        #45:#46        // out:Ljava/io/PrintStream;
  #34 = Utf8               this is lambda
  #35 = Class              #47            // java/io/PrintStream
  #36 = NameAndType        #48:#49        // println:(Ljava/lang/String;)V
  #37 = Utf8               com/xin/lambda/LambdaTest1
  #38 = Utf8               java/lang/Object
  #39 = Methodref          #50.#51        // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #40 = Methodref          #8.#52         // com/xin/lambda/LambdaTest1.lambda$main$0:()V
  #41 = Utf8               run
  #42 = Utf8               ()Ljava/lang/Runnable;
  #43 = Utf8               (Ljava/lang/Runnable;)V
  #44 = Utf8               java/lang/System
  #45 = Utf8               out
  #46 = Utf8               Ljava/io/PrintStream;
  #47 = Utf8               java/io/PrintStream
  #48 = Utf8               println
  #49 = Utf8               (Ljava/lang/String;)V
  #50 = Class              #53            // java/lang/invoke/LambdaMetafactory
  #51 = NameAndType        #54:#58        // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #52 = NameAndType        #21:#11        // lambda$main$0:()V
  #53 = Utf8               java/lang/invoke/LambdaMetafactory
  #54 = Utf8               metafactory
  #55 = Class              #60            // java/lang/invoke/MethodHandles$Lookup
  #56 = Utf8               Lookup
  #57 = Utf8               InnerClasses
  #58 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #59 = Class              #61            // java/lang/invoke/MethodHandles
  #60 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #61 = Utf8               java/lang/invoke/MethodHandles
{
  public com.xin.lambda.LambdaTest1();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 11: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/xin/lambda/LambdaTest1;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=1, args_size=1
         0: new           #2                  // class java/lang/Thread
         3: dup
         4: invokedynamic #3,  0              // InvokeDynamic #0:run:()Ljava/lang/Runnable;
         9: invokespecial #4                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
        12: pop
        13: return
      LineNumberTable:
        line 13: 0
        line 16: 13
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      14     0  args   [Ljava/lang/String;
}
SourceFile: "LambdaTest1.java"
InnerClasses:
     public static final #56= #55 of #59; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #27 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #28 ()V
      #29 invokestatic com/xin/lambda/LambdaTest1.lambda$main$0:()V
      #28 ()V
```

- 代码中使用Lambda表达式的语句被编译成“ 4: invokedynamic #3,  0”，指向常量池#3位置
- invokedynamic在JVM规范中有如下规定

```java
1、操作码：
invokedynamic = 186 (0xba)
2、参数：
indexbyte1 indexbyte2 0 0,前两个参数构造索引（(indexbyte1 << 8) | indexbyte2），后两个参数必须为0。（the unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class (§2.6), where the value of the index is (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at that index must be a symbolic reference to a call site specifier (§5.1). The values of the third and fourth operand bytes must always be zero. ）
3、索引指向的常量池项的类型为CONSTANT_InvokeDynamic_info
```
- CONSTANT_InvokeDynamic_info
```java
1、定义：
    CONSTANT_InvokeDynamic_info {
    u1 tag;
    u2 bootstrap_method_attr_index;
    u2 name_and_type_index;
}
2、参数：
bootstrap_method_attr_index：索引bootstrap_methods
name_and_type_index：索引类常量池里的CONSTANT_NameAndType_info
本例中常量池#3为：" #3 = InvokeDynamic      #0:#30 "
    即
    bootstrap_method_attr_index为#0
    name_and_type_index为#30，其内容为"NameAndType        #41:#42",跟下去后可以得出结果，这里描述的是Lambda表达式需要调用的函数："java/lang/Runnable.run()"
3、JVM规定：
如果类常量池中存在CONSTANT_InvokeDynamic_info，那么必须有且仅有一个BootstrapMethods_attribute。（There must be exactly one BootstrapMethods attribute in the attributes table of a given ClassFile structure if the constant_pool table of the ClassFile structure has at least one CONSTANT_InvokeDynamic_info entry (§4.4.10). There can be no more than one BootstrapMethods attribute in the attributes table of a given ClassFile structure. ）在反编译文件末尾可以看到的确有且仅有一个BootstrapMethods。
4、作用：
在java7中引入，主要作用给invokedynamic指令制定启动方法
```

- BootstrapMethods_attribute

```java
1、定义：
BootstrapMethods_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 num_bootstrap_methods;
    {   u2 bootstrap_method_ref;
        u2 num_bootstrap_arguments;
        u2 bootstrap_arguments[num_bootstrap_arguments];
    } bootstrap_methods[num_bootstrap_methods];
}
2、参数：
每一个BootstrapMethod都包含一个bootstrap_method_ref和n个bootstrap_arguments
本例中：
    bootstrap_method_ref指向常量池#27，值为“MethodHandle       #6:#39”，继续查看jvm规范，
MethodHandle含有两个参数reference_kind、reference_index，reference_kind值为6，意思是REF_invokeStatic，reference_index为39，即常量池#39位置，内容为“Methodref          #50.#51”，表明这里调用的是java/lang/invoke/LambdaMetafactory.metafactory()
```

- metafactory函数
1、源码如下
```java
public static CallSite metafactory(MethodHandles.Lookup caller,
                                       String invokedName,
                                       MethodType invokedType,
                                       MethodType samMethodType,
                                       MethodHandle implMethod,
                                       MethodType instantiatedMethodType)
            throws LambdaConversionException {
        AbstractValidatingLambdaMetafactory mf;
        mf = new InnerClassLambdaMetafactory(caller, invokedType,
                                             invokedName, samMethodType,
                                             implMethod, instantiatedMethodType,
                                             false, EMPTY_CLASS_ARRAY, EMPTY_MT_ARRAY);
        mf.validateMetafactoryArgs();
        return mf.buildCallSite();
```
2、参数：
caller、invokedName、invokedType都由jvm生成，invokedName和invokedType分别是由动态调用点的NameAndType生成，samMethodType和instantiatedMethodType表示该函数式接口的方法描述符，implMethod最为重要，指用户实现的Lambda方法，本例为：com.xin.lambda.LambdaTest1.lambda\$main\$0()void/invokeStatic

3、代码快速解读：

metafactory()核心要生成CallSite，这个任务主要由InnerClassLambdaMetafactory的buildCallSite()完成，buildCallSite()继续调用spinInnerClass()函数，spinInnerClass函数用字节码工具在内存中生成一个类。

4、到此Lambda表达式的脱糖过程结束

### 六、结论
以上简单的分析了Lambda表达式的脱糖过程，还有很多细节需要各位大神去研究，总之，Lambda表达式的实现原理依然是基于内部类的方式。