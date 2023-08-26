<p>
    <a href="#" onclick="refreshContent('springcloud')">返回目录</a>
</p>

# Spring Cloud -调优知识积累

第一步：熔断器并发调优

    首先想到的是Feign调用并发过大，导致的熔断问题，优化服务A中的熔断配置
    hystrix.command.default.fallback.isolation.semaphore.maxConcurrentRequests 如果并发数达到该设置值，请求会被拒绝和抛出异常并且fallback不会被调用。
    默认10

第二步：Zuul并发调优

    经历了将熔断器执行线程并发设置为500后，继续用JMeter进行并发测试，结果QPS到达100后，又出现大量请求失败。
    查看日志，发现zuul很多请求连接关闭。

    优化配置：

    # zuul网关配置
    zuul.semaphore.max-semaphores=500

系统说下spring cloud工程调优的问题

<a href="https://blog.csdn.net/xiaolyuh123/article/details/106967193#" target="_blank">https://blog.csdn.net/xiaolyuh123/article/details/106967193</a>

    主要从以下几个方面入手：
    1、hystrix熔断器并发调优
    2、zuul网关的并发参数控制
    3、Feign客户端和连接数参数调优
    4、Tomcat并发连接数调优
    5、timeout超时参数调优
    6、JVM参数调优
    7、ribbon和hystrix的请求超时，重试以及幂等性配置
