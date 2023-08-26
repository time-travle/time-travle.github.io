<p>
     <a href="#" onclick="refreshContent('database')">返回</a>
</p>

---

# 连接池 的使用

---

数据库连接池的作用

    对于一个简单的数据库引用，用于对数据库的访问不是很频繁。这时就可以简单的在需要访问数据库是，就创建一个连接，用完后就关闭它，这样做也不会带来什么明显的性能上的开销。
    
    但是对于一个复杂的数据库引用，情况就完全不同了。频繁的建立、关闭连接，会极大的减低系统的性能，因为对于连接的使用成了系统性能的瓶颈。 对于共享资源，有一个很著名的设计模式：资源池。
    
    该模式正是为了解决资源频繁分配、释放所造成的问题的。把该模式应用到数据库连接管理领域，就是建立一个数据库连接池，提供一套高效的连接分配、使用策略，最终目标是实现连接的高效、安全的复用。
    
    数据库连接池的基本原理是在内部对象池中维护一定数量的数据库连接，并对外暴露数据库连接获取和返回方法。
    
    如：外部使用者可通过getConnection 方法获取连接，使用完毕后再通过releaseConnection 方法将连接返回，注意此时连接并没有关闭，而是由连接池管理器回收，并为下一次使用做好准备。

数据库连接池技术带来的优势：

    1、资源复用
        由于数据库连接得到重用，避免了频繁创建、释放连接引起的大量性能开销。在减少系统消耗的基础上，另一方面也增进了系统运行环境的平稳性（减少内存碎片以及数据库临时进程/线程的数量）。
    2、更快的系统响应速度
        数据库连接池在初始化过程中，往往已经创建了若干数据库连接至于池中备用。此时连接的初始化工作均已完成。
        对于业务请求处理而言，直接利用现有可用连接，避免了数据库连接初始化和释放过程的时间，从而缩减了系统整体响应时间。
    3、统一的连接管理，避免数据库连接泄漏
        在较为完备的数据库连接池实现中，可根据预先的连接占用超时设定，强制收回被占用连接。从而避免了常规数据库连接操作中可能出现的资源泄漏

---

## 连接池 Hikari：

springboot 2.0 默认连接池就是Hikari了，所以引用parents后不用专门加依赖； application.yml中添加以下配置即可：（demo）

            spring:
                datasource:
                    driver-class-name: com.mysql.jdbc.Driver
                    username: mone_rootadfadsf
                    password: nYWTpo1Tr7CfiWKtafadsf
                    url: jdbc:mysql://rm-bp194e4uj9873e6m6lo.mysql.rds.aliyunafscs.com:3306/mone_tsdfest?characterEncoding=utf-8&useSSL=false
                    hikari:
                        # 连接只读数据库时配置为true， 保证安全
                        read-only: true
                        # 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 缺省:30秒
                        connection-timeout: 30000
                        # 一个连接idle状态的最大时长（毫秒），超时则被释放（retired），缺省:10分钟
                        idle-timeout: 600000
                        # 一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），缺省:30分钟，建议设置比数据库超时时长少30秒
                        max-lifetime: 1800000
                        # 连接池中允许的最大连接数。缺省值：10
                        maximum-pool-size: 60
                        minimum-idle: 10

## 连接池 Druid：

Druid首先是一个数据库连接池。Druid是目前最好的数据库连接池，在功能、性能、扩展性方面，都超过其他数据库连接池，包括DBCP、C3P0、BoneCP、Proxool、JBoss DataSource。

Druid不仅仅是一个数据库连接池，它包括四个部分：

    Druid是一个JDBC组件.
    基于Filter－Chain模式的插件体系.
    DruidDataSource 高效可管理的数据库连接池.
    SQLParser.

Druid可以：

    1、充当数据库连接池。
    2、可以监控数据库访问性能
    3、获得SQL执行日志
    4、扩展JDBC

pom.xml中添加

    <!--druid连接池-->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.9</version>
    </dependency>

application.yml中添加

            spring:
                datasource:
                    driver-class-name: com.mysql.cj.jdbc.Driver
                    username: root
                    password: root
                    url: jdbc:mysql://localhost:3306/springBootdemo?characterEncoding=utf-8&useSSL=false
                    druid:
                        initialSize: 1 #初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
                        minIdle: 1  #最小连接池数量
                        maxActive: 20 #最大连接池数量
                        maxWait: 60000 #获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
                        timeBetweenEvictionRunsMillis: 60000
                        minEvictableIdleTimeMillis: 300000
                        validationQuery: SELECT 1
                        testWhileIdle: true
                        testOnBorrow: true
                        testOnReturn: false
                        poolPreparedStatements:true#是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql5.5以下的版本中没有PSCache功能，建议关闭掉。5.5及以上版本有PSCache，建议开启。
                        maxPoolPreparedStatementPerConnectionSize: 20
                        filters: stat,wall  #属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat，日志用的filter:log4j， 防御sql注入的filter:wall
                        connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
                        stat-view-servlet:
                            allow: 47.111.96.214

## 参考：

- <a href="https://www.jianshu.com/p/205b263bca8b" target="_blank">Spring Boot(八):配置连接池Hikari和Druid连接池 </a>
- <a href="https://blog.csdn.net/systemoutprintax/article/details/88840837" target="_blank">springboot配置数据库连接池 </a>
- <a href="https://blog.csdn.net/L_Sail/article/details/70233529" target="_blank">SpringBoot配置连接池 </a>

---

# 多数据源配置

主要介绍两种整合方式，分别是 springboot+mybatis 使用分包方式整合，和 springboot+druid+mybatisplus 使用注解方式整合。

## springboot+mybatis 使用分包方式整合 (例)

### 表结构

在本地新建两个数据库，名称分别为db1和db2，新建一张user表，表结构如下：
![avatar](../blog/database/imgs/img_3.png)

### 主要依赖包

    spring-boot-starter-web
    mybatis-spring-boot-starter
    mysql-connector-java
    lombok
    
     <!-- spring 依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
         <!-- mysql 依赖 -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.0</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

### application.yml 配置文件

    spring:
        datasource:
            db1: # 数据源1
                jdbc-url: jdbc:mysql://localhost:3306/db1?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
                username: root
                password: root
                driver-class-name: com.mysql.cj.jdbc.Driver
            db2: # 数据源2
                jdbc-url: jdbc:mysql://localhost:3306/db2?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
                username: root
                password: root
                driver-class-name: com.mysql.cj.jdbc.Driver

    各个版本的 springboot 配置 datasource 时参数有所变化，例如低版本配置数据库 url 时使用 url 属性，高版本使用 jdbc-url 属性，请注意区分。

### 建立连接数据源的配置文件

第一个配置文件

    @Configuration
    @MapperScan(basePackages = "com.example.multipledatasource.mapper.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
    public class DataSourceConfig1 {
    
        @Primary // 表示这个数据源是默认数据源, 这个注解必须要加，因为不加的话spring将分不清楚那个为主数据源（默认数据源）
        @Bean("db1DataSource")
        @ConfigurationProperties(prefix = "spring.datasource.db1") //读取application.yml中的配置参数映射成为一个对象
        public DataSource getDb1DataSource(){
            return DataSourceBuilder.create().build();
        }
    
        @Primary
        @Bean("db1SqlSessionFactory")
        public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/db1/*.xml"));
            return bean.getObject();
        }
    
        @Primary
        @Bean("db1SqlSessionTemplate")
        public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
            return new SqlSessionTemplate(sqlSessionFactory);
        }
    }

第二个配置文件

    @Configuration
    @MapperScan(basePackages = "com.example.multipledatasource.mapper.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
    public class DataSourceConfig2 {
    
        @Bean("db2DataSource")
        @ConfigurationProperties(prefix = "spring.datasource.db2")
        public DataSource getDb1DataSource(){
            return DataSourceBuilder.create().build();
        }
    
        @Bean("db2SqlSessionFactory")
        public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/db2/*.xml"));
            return bean.getObject();
        }
    
        @Bean("db2SqlSessionTemplate")
        public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
            return new SqlSessionTemplate(sqlSessionFactory);
        }
    }

### 具体实现

注意事项

    在 service 层中根据不同的业务注入不同的 dao 层
    如果是主从复制- -读写分离：比如 db1 中负责增删改，db2 中负责查询。但是需要注意的是负责增删改的数据库必须是主库（master）

![avatar](../blog/database/imgs/img_4.png)

## springboot+druid+mybatisplus 使用注解方式整合(例)

主要依赖包

    spring-boot-starter-web
    mybatis-plus-boot-starter
    dynamic-datasource-spring-boot-starter # 配置动态数据源
    druid-spring-boot-starter # 阿里的数据库连接池
    mysql-connector-java
    lombok

    <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
       <dependency>
           <groupId>com.baomidou</groupId>
           <artifactId>mybatis-plus-boot-starter</artifactId>
           <version>3.2.0</version>
       </dependency>
       <dependency>
           <groupId>com.baomidou</groupId>
           <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
           <version>2.5.6</version>
       </dependency>
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <scope>runtime</scope>
       </dependency>
       <dependency>
           <groupId>com.alibaba</groupId>
           <artifactId>druid-spring-boot-starter</artifactId>
           <version>1.1.20</version>
       </dependency>
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <optional>true</optional>
       </dependency>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-test</artifactId>
           <scope>test</scope>
       </dependency>

application.yml 配置文件

    spring:
        datasource:
            dynamic:
                primary: db1 # 配置默认数据库
                datasource:
                    db1: # 数据源1配置
                        url: jdbc:mysql://localhost:3306/db1?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
                        username: root
                        password: root
                        driver-class-name: com.mysql.cj.jdbc.Driver
                    db2: # 数据源2配置
                        url: jdbc:mysql://localhost:3306/db2?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
                        username: root
                        password: root
                        driver-class-name: com.mysql.cj.jdbc.Driver
                durid:
                    initial-size: 1
                    max-active: 20
                    min-idle: 1
                    max-wait: 60000
        autoconfigure:
            exclude:  com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure # 去除druid配置


    DruidDataSourceAutoConfigure会注入一个DataSourceWrapper，其会在原生的spring.datasource下找 url, username, password 等。
    动态数据源 URL 等配置是在 dynamic 下，因此需要排除，否则会报错。排除方式有两种，一种是上述配置文件排除，还有一种可以在项目启动类排除：
        @SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
        public class Application {
            public static void main(String[] args) {
                SpringApplication.run(Application.class, args);
            }
        }

给使用非默认数据源添加注解@DS

    @DS 可以注解在方法上和类上，同时存在方法注解优先于类上注解。
    注解在 service 实现或 mapper 接口方法上，不要同时在 service 和 mapper 注解。

## 参考：

- <a href="https://www.jianshu.com/p/dfd5ae340011" target="_blank">SpringBoot多数据源配置详细教程 </a>
- <a href="https://www.cnblogs.com/aizen-sousuke/p/11756279.html" target="_blank">springboot-整合多数据源配置 </a>
- <a href="https://www.cnblogs.com/nxzblogs/p/11849797.html" target="_blank">springboot多数据源&动态数据源（主从） </a>

- <a href="https://blog.csdn.net/qq_38058332/article/details/84325009" target="_blank">springboot配置多个数据源（两种方式） </a>
- <a href="https://zhuanlan.zhihu.com/p/47204637" target="_blank">Spring Boot + Mybatis多数据源和动态数据源配置 </a>
- <a href="https://zhuanlan.zhihu.com/p/272663823" target="_blank">SpringBoot多数据源配置详细教程 </a>
- <a href="https://blog.csdn.net/qq_21187515/article/details/121613369" target="_blank">springboot动态多数据源配置和使用（从数据库读取数据源配置） </a>
- <a href="https://blog.csdn.net/qq_45408390/article/details/119574030" target="_blank">Spring Boot配置多数据源（MyBatis + MySQL & MyBatis-Puls + Oracle） </a>
- <a href="https://www.jianshu.com/p/099c0850ba16" target="_blank">SpringBoot多数据源配置 </a>

