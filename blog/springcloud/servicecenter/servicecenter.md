<p>
    <a href="#" onclick="refreshContent('springcloud')">返回目录</a>
</p>

<p>
    <a href="#" onclick="refreshServiceCenter('zk')">Zookeeper  </a> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshServiceCenter('consul')">Consul  </a> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshServiceCenter('nacos')">Nacos  </a> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
</p>

# 服务注册中心

---

springCloud 支持最好的是Eureka 其次是 Consul 最后是Zookpeeper

想将一个微服务注册到Eureka Server（或其他服务发现组件，例如Zookeeper、Consul等），Eureka 2.0闭源之后，Consul慢慢会成为主流。

注册中心是隐藏在服务框架背后最基础的服务，记录各个服务的实例信息，决定业务服务是否正常调用

注册中心主要涉及到三大角色 ：注册中心、服务提供者 、服务消费者

三者关系流程描述如下：

    1、各微服务启动时，将自己的实例信息(ip、端口、服务名等)注册到注册中心，注册中心存储这些数据

    2、服务消费者从注册中心获取到服务提供者的实例信息，通过ip + 端口 方式远程调用服务提供者的接口

    3、各个微服务通过心跳来上报注册中心，注册中心以某个时间段有没有接收到上报信息，来决定是否下线某服务实例
    
    4、 微服务发生变动时如 增加实例或 ip变动，重新注册信息到注册中心。这样，服务消费者就无需改动，直接从注册中心获取最新信息即可

三者关系图 如下：

![avatar](../blog/springcloud/imag/img.png)

注册中心功能

    1、服务注册表
        服务注册表是注册中心的核心，它用来记录各个微服务实例的信息，例如微服务的名称、IP、端口等。服务注册表提供查询API和管理API，查询API用于查询可用的微服务实例，管理API用于服务的注册与注销。
    
    2、服务注册与发现
        服务注册是指微服务在启动时，将自己的信息注册到注册中心的过程。
        服务发现是指查询可用的微服务列表及网络地址的机制。
        
    3、服务检查
        注册中心使用一定的机制定时检测已注册的服务，如发现某实例长时间无法访问，就会从服务注册表移除该实例。



CAP理论是分布式架构实现中重要的理论

    一致性(Consistency) : 所有节点在同一时间具有相同的数据
    
    可用性(Availability) : 保证每个请求不管成功或者失败都有响应
    
    分区容错(Partition tolerance) : 系统中任意信息的丢失或失败不会影响系统的继续运作

基于网络的不稳定性，分区容错是不可避免的，所以我们默认CAP中的P总是成立的。所以在此基础上，开源组件实现时 一般选择 一致性 （C）或 可用性（A）。


| |Nacos|Eureka|Consul|CoreDNS|Zookeeper|
|---|---|---|---|---|---|
|一致性协议|CP+AP|AP|CP|—|CP|
|健康检查|TCP/HTTP/MYSQL/Client Beat|Client Beat|TCP/HTTP/gRPC/Cmd|—|Keep Alive|
|负载均衡策略|权重/ metadata/Selector|Ribbon|Fabio|RoundRobin|—|
|雪崩保护|有|有|无|无|无|
|自动注销实例|支持|支持|支持|不支持|支持|
|访问协议|HTTP/DNS|HTTP|HTTP/DNS|DNS|TCP|
|监听支持|支持|支持|支持|不支持|支持|
|多数据中心|支持|支持|支持|不支持|不支持|
|跨注册中心同步|支持|不支持|支持|不支持|不支持|
|pringCloud集成|支持|支持|支持|不支持|支持|
|Dubbo集成|支持|不支持|支持|不支持|支持|
|K8S集成|支持|不支持|支持|支持|不支持|

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


##参考：

- <a href="https://www.yht7.com/news/175360" target="_blank">Nacos注册中心的部署与用法示例详解 </a>
- <a href="https://www.jianshu.com/p/fc59867bf1c1" target="_blank">微服务模式-注册中心详解 </a>
- <a href="https://blog.ntan520.com/article/10413" target="_blank">ZooKeeper、Eureka、Consul 、Nacos等微服务注册中心的区别 </a>
- <a href="https://developer.aliyun.com/article/766176" target="_blank">微服务技术栈：常见注册中心组件，对比分析 </a>
