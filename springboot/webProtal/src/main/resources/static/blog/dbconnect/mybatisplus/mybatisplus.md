# Mybatis plus框架

<p>
    <a href="#" onclick="refreshContent('dbconnect')">返回目录</a>
</p>


---
SpringBoot使用Mybatis&Mybatis-plus文件映射配置

<a href="https://www.cnblogs.com/taojietaoge/p/14555692.html#" target="_blank">https://www.cnblogs.com/taojietaoge/p/14555692.html </a>

1、使用Mybatis

    <dependency>
       <groupId>org.mybatis.spring.boot</groupId>
       <artifactId>mybatis-spring-boot-starter</artifactId>
       <version>1.3.2</version>
    </dependency>
    yml文件配置的mapper映射及相关信息格式：
    
    mybatis:
      mapper-locations: classpath:mybatis/mapper/*.xml
      config-locations: classpath:mybatis/mybatis-config.xml
      type-aliases-package: com.ausclouds.bdbsec.entity

2、使用Mybatis-Plus

     <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>3.2.0</version>
     </dependency>
    yml文件配置的mapper映射及相关信息格式：
    
    mybatis-plus:
      mapper-locations: classpath:mybatis/mapper/*.xml
      config-locations: classpath:mybatis/mybatis-config.xml
      type-aliases-package: com.example.demo.entity