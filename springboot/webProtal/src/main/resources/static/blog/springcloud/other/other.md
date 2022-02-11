<p>
    <a href="#" onclick="refreshContent('springcloud')">返回目录</a>
</p>

---
#Spring Cloud 与Spring Boot的 爱恨情仇
Spring boot 是 Spring 的一套快速配置脚手架，可以基于spring boot 快速开发单个微服务，
Spring Boot，看名字就知道是Spring的引导，就是用于启动Spring的，使得Spring的学习和使用变得快速无痛。
不仅适合替换原有的工程结构，更适合微服务开发。

Spring Cloud基于Spring Boot，为微服务体系开发中的架构问题，提供了一整套的解决方案——服务注册与发现，服务消费，服务保护与熔断，
网关，分布式调用追踪，分布式配置管理等

Spring Cloud是一个基于Spring Boot实现的云应用开发工具；
Spring boot专注于快速、方便集成的单个个体，
Spring Cloud是关注全局的服务治理框架；

spring boot使用了默认大于配置的理念，很多集成方案已经帮你选择好了，能不配置就不配置，
Spring Cloud很大的一部分是基于Spring boot来实现。



Spring Boot的哲学就是约定大于配置。既然很多东西都是一样的，为什么还要去配置。

    1. 通过starter和依赖管理解决依赖问题。
    2. 通过自动配置，解决配置复杂问题。
    3. 通过内嵌web容器，由应用启动tomcat，而不是tomcat启动应用，来解决部署运行问题。

Spring Cloud体系就比较复杂了。基本可以理解为通过Spring Boot的三大魔法，将各种组件整合在一起，非常简单易用。

Spring Cloud的5个经常用组件：
    
    服务发现——Netflix Eureka；   作用：实现服务治理（服务注册与发现）
    
    客服端负载均衡——Netflix Ribbon；   作用：Ribbon，主要提供客户侧的软件负载均衡算法。
    
    断路器——Netflix Hystrix；  作用：断路器，保护系统，控制故障范围。
    
    服务网关——Netflix Zuul；  作用：api网关，路由，负载均衡等多种作用
    
    分布式配置——Spring Cloud Config ； 作用：配置管理；


---

一句：Spring boot可以离开Spring Cloud独立使用开发项目，但是Spring Cloud离不开Spring boot，属于依赖的关系