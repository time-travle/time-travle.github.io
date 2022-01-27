<p>
    <a href="#" onclick="refreshContent('redis')">返回目录</a>
</p>

使用
---

启动与停止

    Windows下启动与停止：
    
        方式一：
            在命令行窗口中输入redis-server redis.windows.conf 启动redis
            关闭命令行窗口就是关闭redis。
            启动命令：D:\redis\redis-server.exe D:\redis\redis.windows.conf
            
        方式二：redis作为windows服务启动方式（推荐这种方式）
            redis-server --service-install redis.windows.conf --maxheap 内存大小
            启动服务：redis-server --service-start
            停止服务：redis-server --service-stop
            安装服务 D:\redis\redis-server.exe --service-install D:\redis\redis.windows.conf --maxheap 200m
            启动：D:\redis\redis-server.exe --service-start
            停止：D:\redis\redis-server.exe --service-stop
        
    Linux下
        只需要解压到任意下目录即可。
        例如：下载后的文件为：Redis-x64-3.2.100.tar.gz.
        
        解压放在Linux /opt目录下。
        
        1、tar -zxvf Redis-x64-3.2.100.tar.gz解压。
        
        2、解压后出现redis-3.2.100。
        
        3、进入目录：cd redis-3.2.100。
            在里面执行make命令，完成后继续执行make insatll。
        
        4、解压成功后。进入/usr/local/bin下可查看安装成功。
        
        5、启动命令：redis-server /myredis/redis.conf。
        
        6、停止命令：redis-cli shutdown
##五种数据类型和使用场景
|数据类型|数据特点|使用场景|
|----|----|----|
|String|Redis支持的字符串类型不是定长分配的字符串，是动态变长字符串|计数功能|
|List列表|List类型的前后插入和删除速度是非常快的，但是随机定位速度非常慢，时间复杂度是O（n）需要对列表进行遍历|异步队列使用、秒杀抢购场景|
|Hash|redis的hash相当于hashmap，内部实现上和hashmap一致，数组＋链表的数据结构|保存结构体信息|
|Set集合|redis的set相当于java中的HashSet，内部的健值是无序唯一的，相当于一个hashmap，但是value都是null|去重的场景|
|Zset有序集合|类似于 Java 的 SortedSet 和 HashMap 的结合体，一方面它是一个 set，保证了内部 value 的唯一性，另一方面它可以给每个 value 赋予一个 score，代表这个 value 的排序权重。zset内部是通过跳跃列表这种数据结构来实现的。因为zset要支持随机的插入和删除，所以不能使用数组结构，而需要改成普通链表数据结构。zset需要根据score进行排序，所以每次插入或者删除值都需要进行先在链表上查找定位|热门排序场景|

##客户端数据操作参考

<a href="https://blog.csdn.net/Dance_sheng/article/details/118904661" target="_blank">Redis - 五种数据类型以及基本操作</a>

<a href="https://blog.csdn.net/qq_41264674/article/details/81260135" target="_blank">redis操作五种数据类型</a>