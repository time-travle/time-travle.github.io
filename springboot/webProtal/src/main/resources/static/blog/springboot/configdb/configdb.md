<p>
<a href="#" onclick="refreshContent('springboot')">返回</a>
</p>

---

# Spring Boot 数据库连接配置

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

2、添加一个配置文件加载的类 取配置信息建立链接

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
参考wiki：

- <a href="https://blog.csdn.net/xyh930929/article/details/84136673#" target="_blank">https://blog.csdn.net/xyh930929/article/details/84136673 </a>
- <a href="https://www.cnblogs.com/carrychan/p/9401471.html#" target="_blank">https://www.cnblogs.com/carrychan/p/9401471.html </a>

 