#Spring boot 知识积累
<a href="#" onclick="refreshSpringBootContent('annotaion')">注解 Annotaion</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('banner')">banner生成链接</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('servlet')">SpringBoot中使用servlet</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('crossdomain')">SpringBoot中跨域</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('better')">SpringBoot 性能优化</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('log')">SpringBoot 日志系统</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('question')">SpringBoot 常见问题</a>&emsp;&emsp;&emsp;

---

1、SpringBoot入门，整合Redis实现缓存
	https://blog.csdn.net/qq_27317475/article/details/81188642
	https://my.oschina.net/u/2935623/blog/2223054
	
2、springboot项目搭建及常用技术整合
	https://www.cnblogs.com/leskang/p/10942506.html

3、常量类的定义
	https://www.cnblogs.com/lihaoyang/p/6913295.html
	
4、lombok的使用
	https://blog.csdn.net/qq_22860341/article/details/81224890
	
	
5、若是项目是一个不需要数据的一个工程那么若是我们不对工程的启动类就处理一下的话，在工程启动的过程中就会报错导致工程无法启动
	问题的修改方法是在启动类上将数据库配置进行去除
	@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
	这样我们的工程就是一个脱离数据存在的工程了这样的配置一般是因为我这个工程是单独进行放置前端文件的。
   
   
6、统一升级版本号
参考Wiki：https://www.cnblogs.com/lukelook/p/11298168.html
 
---

Spring 多数据源配置

1、添加配置文件 或者再要给文件中统一进行添加

    内容大致如下
                spring:
                    datasource:  
                      logapi: 
                        name: logapi
                        jdbc-url: jdbc:mysql://localhost:3306/logapi?&characterEncoding=utf-8&serverTimezone=UTC
                        username: username
                        password: password
                        # 使用druid数据源
                        type: com.alibaba.druid.pool.DruidDataSource
                        driver-class-name: com.mysql.cj.jdbc.Driver
                        filters: stat
                        maxActive: 20
                        initialSize: 1
                        maxWait: 60000
                        minIdle: 1
                        timeBetweenEvictionRunsMillis: 60000
                        minEvictableIdleTimeMillis: 300000
                        validationQuery: select 'x'
                        testWhileIdle: true
                        testOnBorrow: false
                        testOnReturn: false
                        poolPreparedStatements: true
                        maxOpenPreparedStatements: 20
                      hnkyyy: 
                        name: hnkyyy
                        jdbc-url: jdbc:oracle:thin:@localhost:hnkyyy
                        username: username
                        password: password
                        # 使用druid数据源
                        type: com.alibaba.druid.pool.DruidDataSource
                        driver-class-name: oracle.jdbc.driver.OracleDriver
                        filters: stat
                        maxActive: 20
                        initialSize: 1
                        maxWait: 60000
                        minIdle: 1
                        timeBetweenEvictionRunsMillis: 60000
                        minEvictableIdleTimeMillis: 300000
                        validationQuery: select 'x'
                        testWhileIdle: true
                        testOnBorrow: false
                        testOnReturn: false
                        poolPreparedStatements: true
                        maxOpenPreparedStatements: 20
		
2、添加一个配置文件加载的类  取配置信息建立链接

    内容大致如下  每添加一个就这样类似的加一个 对应的方法
            
            @Configuration
            public class DataSourceConfig {
                
                // Orcale 数据库
                // 这里的 bean 名字要和上面自定义的名字一样
                @Bean(name = "hnkyyy")
                @ConfigurationProperties(prefix = "spring.datasource.hnkyyy") // application.properteis中对应属性的前缀
                public DataSource dataSourceCrm() {
                    return DataSourceBuilder.create().build();
                }
                
                // mysql 数据库
                @Bean(name = "logapi")
                @ConfigurationProperties(prefix = "spring.datasource.logapi") // application.properteis中对应属性的前缀
                public DataSource dataSourcelogapi() {
                    return DataSourceBuilder.create().build();
                }
            }

3、 建立这个没看懂啥意思 
            
            @Configuration
            @MapperScan(basePackages = {"com.ky.mapper.logapi"}, sqlSessionFactoryRef = "sqlSessionFactorylogapi")
            public class MybatisDbLogApiConfig {
                
                @Autowired
                @Qualifier("logapi")
                private DataSource logapi;
            
                @Bean
                public SqlSessionFactory sqlSessionFactorylogapi() throws Exception {
                    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
                    factoryBean.setDataSource(logapi); // 连接 logapi 库
                    return factoryBean.getObject();
                }
            
                @Bean
                public SqlSessionTemplate sqlSessionTemplatelogapi() throws Exception {
                    SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactorylogapi()); // 使用上面配置的Factory
                    return template;
                }
            }
            
            @Configuration
            @MapperScan(basePackages = {"com.ky.mapper.hnkyyy"}, sqlSessionFactoryRef = "sqlSessionFactoryCrm")
            public class MybatisDbhnkyyConfig {
                @Autowired
                @Qualifier("hnkyyy")
                private DataSource hnkyyy;
            
                @Bean
                public SqlSessionFactory sqlSessionFactoryCrm() throws Exception {
                    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
                    factoryBean.setDataSource(hnkyyy); // 连接 hnkyyy 库
                    return factoryBean.getObject();
                }
            
                @Bean
                public SqlSessionTemplate sqlSessionTemplateCrm() throws Exception {
                    SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryCrm()); // 使用上面配置的Factory
                    return template;
                }
            }
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        
   - https://blog.csdn.net/xyh930929/article/details/84136673
   - https://www.cnblogs.com/carrychan/p/9401471.html

 