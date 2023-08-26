<p>
    <a href="#" onclick="refreshSpringCloudContent('servicecenter')">返回目录</a>
</p>


# Consul 服务注册中心

---
Consul 是 HashiCorp 公司推出的开源工具，用于实现分布式系统的服务发现与配置。Consul 使用 Go 语言编写，因此具有天然可移植性（支持Linux、windows和Mac OS X）。

Consul 内置了服务注册与发现框架、分布一致性协议实现、健康检查、Key/Value 存储、多数据中心方案，不再需要依赖其他工具（比如 ZooKeeper 等），使用起来也较为简单。

Consul 遵循CAP原理中的CP原则，保证了强一致性和分区容错性，且使用的是Raft算法，比zookeeper使用的Paxos算法更加简单。

虽然保证了强一致性，但是可用性就相应下降了，例如服务注册的时间会稍长一些，因为 Consul 的 raft 协议要求必须过半数的节点都写入成功才认为注册成功 ；

在leader挂掉了之后，重新选举出leader之前会导致Consul 服务不可用



Consul本质上属于应用外的注册方式，但可以通过SDK简化注册流程。而服务发现恰好相反，默认依赖于SDK，但可以通过Consul Template（下文会提到）去除SDK依赖

Consul Template

            Consul，默认服务调用者需要依赖Consul SDK来发现服务，这就无法保证对应用的零侵入性。
            所幸通过Consul Template，可以定时从Consul集群获取最新的服务提供者列表并刷新LB配置（比如nginx的upstream），这样对于服务调用者而言，只需要配置一个统一的服务调用地址即可。

Consul强一致性(C)带来的是：

            服务注册相比Eureka会稍慢一些。因为Consul的raft协议要求必须过半数的节点都写入成功才认为注册成功
            Leader挂掉时，重新选举期间整个consul不可用。保证了强一致性但牺牲了可用性。


- <a href="https://www.jianshu.com/p/bfcc8855f3d4?utm_campaign=hugo" target="_blank">ZooKeeper、Eureka、Consul 、Nacos微服务注册中心对比 </a>
