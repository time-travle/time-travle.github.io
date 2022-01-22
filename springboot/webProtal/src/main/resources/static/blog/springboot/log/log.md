<a href="#" onclick="refreshContent('springboot')">返回</a>

##SpringBoot 日志系统

Spring Boot默认日志系统

    Spring Boot默认使用LogBack日志系统，如果不需要更改为其他日志系统如Log4j2等，则无需多余的配置，LogBack默认将日志打印到控制台上。
    Spring Boot默认的日志级别为INFO，这里打印的是INFO级别的日志所以可以显示
    
    如果要使用LogBack，原则上是需要添加dependency依赖的

    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-logging</artifactId></pre>

    但是因为新建的Spring Boot项目一般都会引用spring-boot-starter或者spring-boot-starter-web，
    而这两个起步依赖中都已经包含了对于spring-boot-starter-logging的依赖，所以，无需额外添加依赖。
    
    Spring Cloud日志配置可以使用Log4j或者logback，但是官方推荐使用logback。logback是log4j作者出的，用来替代log4j的

- https://cloud.tencent.com/developer/article/1445599
- https://cloud.tencent.com/developer/article/1403110
- https://blog.csdn.net/inke88/article/details/75007649
- https://www.jianshu.com/p/1fa12b92d5c4

- https://blog.csdn.net/qq_42216517/article/details/108397406
- https://www.jianshu.com/p/f67c721eea1b
- https://www.cnblogs.com/itgaofei/p/9345569.html
- https://blog.csdn.net/qq_42216517/article/details/108397406
    