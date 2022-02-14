# redis

---
<p>
<a href="#" onclick="refreshRedisContent('config')">Redis（config）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshRedisContent('note')">Redis（note）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshRedisContent('order')">Redis（order）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshRedisContent('question')">Redis（question）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshRedisContent('usage')">Redis（Usage）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshRedisContent('cluster')">Redis（集群部署）</a>&emsp;&emsp;&emsp;
</p>

---

## 简介

Redis 是完全开源免费的，遵守BSD协议，是一个高性能的key-value数据库。

Redis 与其他 key - value 缓存产品有以下三个特点：

- Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用。
- Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。
- Redis支持数据的备份，即master-slave模式的数据备份。

Redis 优势

- 性能极高 – Redis能读的速度是110000次/s,写的速度是81000次/s 。
- 丰富的数据类型 – Redis支持二进制案例的 Strings, Lists, Hashes, Sets 及 Ordered Sets 数据类型操作。
- 原子 – Redis的所有操作都是原子性的，意思就是要么成功执行要么失败完全不执行。单个操作是原子性的。多个操作也支持事务，即原子性，通过MULTI和EXEC指令包起来。
- 丰富的特性 – Redis还支持 publish/subscribe, 通知, key 过期等等特性。

Redis与其他key-value存储有什么不同？

- Redis有着更为复杂的数据结构并且提供对他们的原子性操作，这是一个不同于其他数据库的进化路径。

  Redis的数据类型都是基于基本数据结构的同时对程序员透明，无需进行额外的抽象。

- Redis运行在内存中但是可以持久化到磁盘，所以在对不同数据集进行高速读写时需要权衡内存，因为数据量不能大于硬件内存。

  在内存数据库方面的另一个优点是，相比在磁盘上相同的复杂的数据结构，在内存中操作起来非常简单，这样Redis可以做很多内部复杂性很强的事情。 同时，在磁盘格式方面他们是紧凑的以追加的方式产生的，因为他们并不需要进行随机访问。

## 安装

runoob： <a href="https://www.runoob.com/redis/redis-install.html#" target="_blank">https://www.runoob.com/redis/redis-install.html </a>

## 启动

在安装目录执行：

redis-server.exe redis.windows.conf

## redis集群搭建

redis集群搭建

- <a href="https://blog.csdn.net/qq_42815754/article/details/82912130#" target="_blank">https://blog.csdn.net/qq_42815754/article/details/82912130</a>
- <a href="https://www.cnblogs.com/silent2012/p/10697896.html#" target="_blank">https://www.cnblogs.com/silent2012/p/10697896.html</a>

### redis cluster 介绍

    自动将数据进行分片，每个 master 上放一部分数据
    提供内置的高可用支持，部分 master 不可用时，还是可以继续工作的
    在 redis cluster 架构下，每个 redis 要放开两个端口号，比如一个是 6379，另外一个就是 加1w 的端口号，比如 16379。
    
    16379 端口号是用来进行节点间通信的，也就是 cluster bus 的东西，cluster bus 的通信，用来进行故障检测、配置更新、故障转移授权。
    cluster bus 用了另外一种二进制的协议，gossip 协议，用于节点间进行高效的数据交换，占用更少的网络带宽和处理时间

    redis cluster 节点间采用 gossip 协议进行通信。

## 参考链接

- <a href="https://blog.csdn.net/summerZBH123/article/-details/81406856#" target="_blank">https://blog.csdn.net/summerZBH123/article/-details/81406856 </a>
- <a href="https://blog.csdn.net/u011320646/article/details/85491103#" target="_blank">https://blog.csdn.net/u011320646/article/details/85491103 </a>
- <a href="https://www.cnblogs.com/lezon1995/p/11175380.html" target="_blank">https://www.cnblogs.com/lezon1995/p/11175380.html </a>
- <a href="https://www.cnblogs.com/1013wang/p/12192610.html#" target="_blank">https://www.cnblogs.com/1013wang/p/12192610.html </a>
- <a href="https://www.cnblogs.com/jasontec/p/9699242.html#" target="_blank">https://www.cnblogs.com/jasontec/p/9699242.html </a>
- <a href="https://blog.csdn.net/Butterfly_resting/article/details/89668661#" target="_blank">https://blog.csdn.net/Butterfly_resting/article/details/89668661 </a>

- <a href="https://zhuanlan.zhihu.com/p/64176772#" target="_blank">springboot整合redis </a>

