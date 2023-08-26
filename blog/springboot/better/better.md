<a href="#" onclick="refreshContent('springboot')">返回</a>

# Spring Boot 性能优化

springboot性能优化

    一、扫描优化
        SpringBoot项目中的启动类，会使用@SpringBootApplication 注解，该注解的作用扫描外部依赖项目（例如：Tomcat，SpringMVC，Transaction等）
        以及运行一些自动配置类。

        @SpringBootApplication注解中包含了@SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan等等，通过以下配置
        
        使用 @import注解直接引入需要的 减去扫描时间
        
    二、JVM参数调优
        先了解两个配置指令

        （1）-Xmx:设置最大的java堆大小

        （2） -Xms:设置Java堆栈的初始化大小

    三、根据项目采用合适的容器
        现在最流行也最常用的就是tomcat容器，tomcat是默认支持jsp的，当然也可以去优化tomcat，关闭tomcat对jsp的支持。
        如果不优化tomcat也可以在项目中更换使用其他容器，例如：Undertow，该容器默认是不支持jsp。减少一些其他不需要的支持来提升容器启动和运行效率。


 