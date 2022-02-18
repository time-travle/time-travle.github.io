<p>
    <a href="#" onclick="refreshContent('jvm')">返回 </a>
</p>

# JVM 模型介绍 日常积累

---

## JVM 模型

    方法区
        元空间
    
    堆
        新代码
        老代码
        常量池
        字符常量
        运行时常量
        
    本地方法栈
    
    虚拟机栈
        栈帧
        
    程序计数器

![avatar](../blog/jvm/imgs/img.png)


0x01:程序计数器（Program Counter Register）

    程序计数器（Program Counter Register）是一块较小的内存空间，它可以看作是当前线程所执行的字节码的行号指示器。
    在虚拟机概念模型里（概念模型，各种虚拟机可能会通过一些更高效的方式实现），
    字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令：分支、跳转、循环、异常处理、线程恢复等基础操作都会依赖这个计数器来完成。
    每个线程都有独立的程序计数器，用来在线程切换后能恢复到正确的执行位置，各条线程之间的计数器互不影响，独立存储。
    所以它是一个“线程私有”的内存区域。此内存区域是唯一一个在JVM规范中没有规定任何OutOfMemoryError情况的区域。



0x02:虚拟机栈（VM Stack）

    JVM栈是线程私有的内存区域。
    它描述的是java方法执行的内存模型，每个方法执行的同时都会创建一个栈帧（Stack Frame）用于存储局部变量表、操作数栈、动态链接、方法出口等信息。
    每个方法从调用直至完成的过程，都对应着一个栈帧从入栈到出栈的过程。每当一个方法执行完成时，该栈帧就会弹出栈帧的元素作为这个方法的返回值，
    并且清除这个栈帧，Java栈的栈顶的栈帧就是当前正在执行的活动栈，也就是当前正在执行的方法。
    就像是组成动画的一帧一帧的图片，方法的调用过程也是由栈帧切换来产生结果。
    
    局部变量表存放了编译器可知的各种基本数据类型（int、short、byte、char、double、float、long、boolean）、对象引用（reference类型，
        它不等同于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或其他与此对象相关的位置）和returnAddress类型（指向了一跳字节码指令的地址）。
    
    在JVM规范中，对这个区域规定了两种异常情况：
        如果线程请求的栈深度大于虚拟机允许的深度，将抛出StackOverflowError异常；
        如果虚拟机栈可以动态扩展，在扩展时无法申请到足够的内存，就会抛出OutOfMemoryError异常。
    


0x03:本地方法栈（ Native Method Stack）

    本地方法栈和虚拟机栈所发挥的作用是很相似的，它们之间的区别不过是虚拟机栈为虚拟机执行Java方法（字节码）服务，而本地方法栈则为虚拟机使用到的Native方法服务。
    Sun HotSpot 直接就把本地方法栈和虚拟机栈合二为一。本地方法栈也会抛出StackOverflowError和OutOfMemoryError异常。


0x04:堆（Heap）

    Heap是OOM故障最主要的发源地，它存储着几乎所有的实例对象，堆由垃圾收集器自动回收，堆区由各子线程共享使用；
    通常情况下，它占用的空间是所有内存区域中最大的，但如果无节制地创建大量对象，也容易消耗完所有的空间；
    堆的内存空间既可以固定大小，也可运行时动态地调整，通过参数-Xms设定初始值、-Xmx设定最大值。

    此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。
    这一点在Java 虚拟机规范中的描述是：所有的对象实例以及数组都要在堆上分配，不是“绝对”的。
    Java堆是垃圾收集器管理的主要区域，按照分代收集算法的划分，堆内存空间可以继续细分为年轻代，老年代。
    年轻代又可以划分为较大的Eden区，两个同等大小的From Survivor,To Survivor区。
    默认的Eden区和Survivor区的大小比例为8:1:1，这个比例可以调节。
    在为新创建的对象分配内存的时候先将对象分配到Eden区和From Survivor区，在立即回收时，会将Eden区和Survivor区还存活的对象复制到To Survivor区中，如果To Survivor区的大小不能容纳存活的对象，会把存活的对象分配到老年区。
    总体来说，新创建的小对象会放在年轻代，年轻代的对象大多在下一次垃圾回收时被回收，老年代存储大的对象和存活时间长的对象。

0x05:方法区（Method Area）
    
    方法区是被所有线程共享的内存区域，用来存储已被虚拟机加载的类信息、常量、静态变量、JIT（just in time,即时编译技术）编译后的代码等数据。
    运行时常量池是方法区的一部分，用于存放编译期间生成的各种字面常量和符号引用。

![avatar](../blog/jvm/imgs/img_1.png)

    通过反射获取到的类型、方法名、字段名称、访问修饰符等信息就是从方法区获取到的。
    在使用到CGLib对类进行增强时，增强的类越多，就需要越大的方法区类存储动态生成的Class信息，当存放方法区数据的内存溢出时，
    会报OutOfMemoryError异常。在jdk1.8中也就是Metaspace内存溢出，可以通过参数JVM参数-XX:MetaspaceSize和-XX:MaxMetaspaceSize设置Metaspace的空间大小。
    jdk1.8后方法区（Method Area）被元空间(Metaspace)代替。


##参考：
- <a href="https://zhuanlan.zhihu.com/p/101495810" target="_blank">JVM内存模型（详解） </a>
- <a href="https://www.cnblogs.com/swordfall/p/10723938.html" target="_blank">Java虚拟机—Java8内存模型JVM（整理版）  </a>