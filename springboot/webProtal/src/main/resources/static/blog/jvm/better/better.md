<p>
    <a href="#" onclick="refreshContent('jvm')">返回 </a>
</p>

# JVM 调优方案 日常积累

---
1 调优层次

    性能调优包含多个层次，比如:架构调优、代码调优、JVM调优、数据库调优、操作系统调优等。
    架构调优和代码调优是JVM调优的基础，其中架构调优是对系统影响最大的。

2 调优指标

    吞吐量：运行用户代码的时间占总运行时间的行例 （总运行时间=程序的运行时间+内存回收的时间）；
    暂停时间：执行垃圾收集时，程序的工作线程被暂停的时间；
    内存占用：java堆区所占的内存大小；
    这三者共同构成一个“不可能三角”。三者总体的表现会随着技术进步而越来越好。一款优秀的收集器通常最多同时满足其中的两项。
    
    简单来说，主要抓住两点:
    
    吞吐量
    吞吐量优先，意味着在单位时间内，STW的时间最短
    暂停时间
    暂停时间优先，意味这尽可能让单次STW的时间最短
    在设计(或使用)GC算法时，必须确定我们的目标：一个GC算法只可能针对两个目标之一（即只专注于较大吞吐量或最小暂停时间），或尝试找一个二者的折衷。
    
    现在标准，在最大吞吐量优先的情况下，降低停顿时间。

3 JVM调优原则

3.1 优先原则

    优先架构调优和代码调优，JVM优化是不得已的手段，大多数的Java应用不需要进行JVM优化

3.2 堆设置

    参数-Xms和-Xmx，通常设置为相同的值，避免运行时要不断扩展JVM内存，建议扩大至3-4倍FullGC后的老年代空间占用。

3.3 年轻代设置

    参数-Xmn，1-1.5倍FullGC之后的老年代空间占用。
    
    避免新生代设置过小，当新生代设置过小时，会带来两个问题：一是minor GC次数频繁，二是可能导致 minor GC对象直接进老年代。当老年代内存不足时，会触发Full GC。
    避免新生代设置过大，当新生代设置过大时，会带来两个问题：一是老年代变小，可能导致Full GC频繁执行；二是 minor GC 执行回收的时间大幅度增加。

3.4 老年代设置

    注重低延迟的应用
    
    老年代使用并发收集器，所以其大小需要小心设置，一般要考虑并发会话率和会话持续时间等一些参数
    如果堆设置偏小，可能会造成内存碎片、高回收频率以及应用暂停
    如果堆设置偏大，则需要较长的收集时间
    吞吐量优先的应用
    一般吞吐量优先的应用都有一个较大的年轻代和一个较小的老年代。原因是，这样可以尽可能回收掉大部分短期对象，减少中期的对象，而老年代尽可能存放长期存活对象

3.5 方法区设置

    基于jdk1.7版本，永久代：参数-XX:PermSize和-XX:MaxPermSize；
    基于jdk1.8版本，元空间：参数 -XX:MetaspaceSize和-XX:MaxMetaspaceSize；
    通常设置为相同的值，避免运行时要不断扩展，建议扩大至1.2-1.5倍FullGc后的永久带空间占用。

4 JVM调优步骤

4.1 监控分析

    分析GC日志及dump文件，判断是否需要优化，确定瓶颈问题点。

4.1.1 如何生成GC日志

    常用参数部分会详细讲解如何生成GC日志

4.1.2 如何产生dump文件

4.1.2.1 JVM的配置文件中配置

    JVM启动时增加两个参数:
            # 出现OOME时生成堆dump:
            -XX:+HeapDumpOnOutOfMemoryError
            # 生成堆文件地址：
            -XX:HeapDumpPath=/home/hadoop/dump/
4.1.2.2 jmap生成

    发现程序异常前通过执行指令，直接生成当前JVM的dump文件
            jmap -dump:file=文件名.dump [pid]
            # 9257是指JVM的进程号
            jmap -dump:format=b,file=testmap.dump 9257
    
    第一种方式是一种事后方式，需要等待当前JVM出现问题后才能生成dump文件，实时性不高；
    第二种方式在执行时，JVM是暂停服务的，所以对线上的运行会产生影响。
    
    所以建议第一种方式。

4.1.2.3 第三方可视化工具生成

4.2 判断

    如果各项参数设置合理，系统没有超时日志或异常信息出现，GC频率不高，GC耗时不高，那么没有必要进行GC优化，如果GC时间超过1-3秒，或者频繁GC，则必须优化。
    遇到以下情况，就需要考虑进行JVM调优：
    
    系统吞吐量与响应性能不高或下降；
    Heap内存（老年代）持续上涨达到设置的最大内存值；
    Full GC 次数频繁；
    GC 停顿时间过长（超过1秒）；
    应用出现OutOfMemory等内存异常；
    应用中有使用本地缓存且占用大量内存空间；
4.3 确定目标

    调优的最终目的都是为了应用程序使用最小的硬件消耗来承载更大的吞吐量或者低延迟。
    jvm调优主要是针对垃圾收集器的收集性能优化，减少GC的频率和Full GC的次数，令运行在虚拟机上的应用能够使用更少的内存、高吞吐量、低延迟。
    
    下面列举一些JVM调优的量化目标参考实例，注意：不同应用的JVM调优量化目标是不一样的。
    
    堆内存使用率<=70%;
    老年代内存使用率<=70%;
    avgpause<=1秒;
    Full GC次数0或avg pause interval>=24小时 ;
4.4 调整参数

    调优一般是从满足程序的内存使用需求开始的，之后是时间延迟的要求，最后才是吞吐量的要求。
    要基于这个步骤来不断优化，每一个步骤都是进行下一步的基础，不可逆行之。

4.5 对比调优前后指标差异

4.6 重复以上过程

4.7 应用

    找到合适的参数，先在单台服务器上试运行，然后将这些参数应用到所有服务器，并进行后续跟踪。


##常用JVM参数参考
|参数|说明|实例|
|---|---|---|
|-Xms|初始堆大小，默认物理内存的1/64|-Xms512M|
|-Xmx|最大堆大小，默认物理内存的1/4|-Xms2G|
|-Xmn|新生代内存大小，官方推荐为整个堆的3/8|-Xmn512M|
|-Xss|线程堆栈大小，jdk1.5及之后默认1M，之前默认256k|-Xss512k|
|-XX:NewRatio=n|设置新生代和年老代的比值。如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4|-XX:NewRatio=3|
|-XX:SurvivorRatio=n|年轻代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如:8，表示Eden：Survivor=8:1:1，一个Survivor区占整个年轻代的1/8|-XX:SurvivorRatio=8
|-XX:PermSize=n|永久代初始值，默认为物理内存的1/64|-XX:PermSize=128M|
|-XX:MaxPermSize=n|永久代最大值，默认为物理内存的1/4|-XX:MaxPermSize=256M|
|-verbose:class|在控制台打印类加载信息| |
|-verbose:gc|在控制台打印垃圾回收日志| |
|-XX:+PrintGC|打印GC日志，内容简单| |
|-XX:+PrintGCDetails|打印GC日志，内容详细| |
|-XX:+PrintGCDateStamps|在GC日志中添加时间戳|| 
|-Xloggc:filename|指定gc日志路径|-Xloggc:/data/jvm/gc.log|
|-XX:+UseSerialGC|年轻代设置串行收集器Serial| |
|-XX:+UseParallelGC|年轻代设置并行收集器Parallel Scavenge| |
|-XX:ParallelGCThreads=n|设置Parallel Scavenge收集时使用的CPU数。并行收集线程数。|-XX:ParallelGCThreads=4|
|-XX:MaxGCPauseMillis=n|设置Parallel Scavenge回收的最大时间(毫秒)|-XX:MaxGCPauseMillis=100|
|-XX:GCTimeRatio=n|设置Parallel Scavenge垃圾回收时间占程序运行时间的百分比。公式为1/(1+n)|-XX:GCTimeRatio=19|
|-XX:+UseParallelOldGC|设置老年代为并行收集器ParallelOld收集器| |
|-XX:+UseConcMarkSweepGC|设置老年代并发收集器CMS| |
|-XX:+CMSIncrementalMode|设置CMS收集器为增量模式，适用于单CPU情况。||



##参考：
- <a href="https://www.cnblogs.com/hanease/p/15890288.html" target="_blank">JVM性能调优详解 </a>
- <a href="https://www.jianshu.com/p/b1eaf6c4eaab" target="_blank">JVM调优 </a>
- <a href="https://blog.csdn.net/weixin_42447959/article/details/81637909" target="_blank">JVM性能调优 </a>





 <style>
  table{
    border-left:1px solid #000000;border-top:1px solid #000000;
    width: 100%;
    word-wrap:break-word; word-break:break-all;
  }
  table th{
  text-align:center;
  }
  table th,td{
    border-right:1px solid #000000;border-bottom:1px solid #000000;
  }
</style>

