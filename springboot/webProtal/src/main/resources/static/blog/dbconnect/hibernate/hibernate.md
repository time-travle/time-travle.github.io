# Hibernate 相关
<p>
    <a href="#" onclick="refreshContent('dbconnect')">返回目录</a>
</p>

---

Hibernate的配置，我们可以使用一个简单的hibernate.properties文件，
或者一个稍微复杂的hibernate.cfg.xml


hibernate.properties样例文件


    hibernate.connection.driver_class = org.postgresql.Driver
    hibernate.connection.url = jdbc:postgresql://localhost/mydatabase
    hibernate.connection.username = myuser
    hibernate.connection.password = secret
    hibernate.c3p0.min_size=5
    hibernate.c3p0.max_size=20
    hibernate.c3p0.timeout=1800
    hibernate.c3p0.max_statements=50
    hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect


Hibernate JDBC属性 

    属性名                               用途  
    hibernate.connection.driver_class   jdbc驱动类 
    hibernate.connection.url            jdbc URL 
    hibernate.connection.username       数据库用户 
    hibernate.connection.password       数据库用户密码 
    hibernate.connection.pool_size      连接池容量上限数目 

Hibernate数据源属性 

    属性名                              用途  
    hibernate.connection.datasource     数据源JNDI名字 
    hibernate.jndi.url                  JNDI提供者的URL (可选)  
    hibernate.jndi.class                JNDI InitialContextFactory类 (可选)  
    hibernate.connection.username       数据库用户 (可选)  
    hibernate.connection.password       数据库用户密码 (可选)  

Hibernate配置属性 

    属性名                                   用途  
    hibernate.dialect                       一个Hibernate Dialect类名允许Hibernate针对特定的关系数据库生成优化的SQL. 
                                            取值 full.classname.of.Dialect 
     
    hibernate.show_sql                      输出所有SQL语句到控制台. 有一个另外的选择是把org.hibernate.SQL这个log category设为debug。 
                                            eg. true | false 
     
    hibernate.format_sql                    在log和console中打印出更漂亮的SQL。 
                                            取值 true | false 
     
    hibernate.default_schema                在生成的SQL中, 将给定的schema/tablespace附加于非全限定名的表名上. 
                                            取值 SCHEMA_NAME 
     
    hibernate.default_catalog               在生成的SQL中, 将给定的catalog附加于非全限定名的表名上. 
                                            取值 CATALOG_NAME 
     
    hibernate.session_factory_name          SessionFactory创建后，将自动使用这个名字绑定到JNDI中. 
                                            取值 jndi/composite/name 
     
    hibernate.max_fetch_depth               为单向关联(一对一, 多对一)的外连接抓取（outer join fetch）树设置最大深度. 值为0意味着将关闭默认的外连接抓取. 
                                            取值 建议在0到3之间取值 
     
    hibernate.default_batch_fetch_size      为Hibernate关联的批量抓取设置默认数量. 
                                            取值 建议的取值为4, 8, 和16 
     
    hibernate.default_entity_mode           为由这个SessionFactory打开的所有Session指定默认的实体表现模式. 
                                            取值 dynamic-map, dom4j, pojo 
     
    hibernate.order_updates                 强制Hibernate按照被更新数据的主键，为SQL更新排序。这么做将减少在高并发系统中事务的死锁。 
                                            取值 true | false 
     
    hibernate.generate_statistics           如果开启, Hibernate将收集有助于性能调节的统计数据. 
                                            取值 true | false 
     
    hibernate.use_identifer_rollback        如果开启, 在对象被删除时生成的标识属性将被重设为默认值. 
                                            取值 true | false 
     
    hibernate.use_sql_comments              如果开启, Hibernate将在SQL中生成有助于调试的注释信息, 默认值为false. 
                                            取值 true | false 

Hibernate缓存属性 

    属性名                                         用途  
    hibernate.cache.provider_class              自定义的CacheProvider的类名. 
                                                取值 classname.of.CacheProvider 
     
    hibernate.cache.use_minimal_puts            以频繁的读操作为代价, 优化二级缓存来最小化写操作. 在Hibernate3中，这个设置对的集群缓存非常有用, 对集群缓存的实现而言，默认是开启的. 
                                                取值 true|false 
     
    hibernate.cache.use_query_cache             允许查询缓存, 个别查询仍然需要被设置为可缓存的. 
                                                取值 true|false 
     
    hibernate.cache.use_second_level_cache      能用来完全禁止使用二级缓存. 对那些在类的映射定义中指定<cache>的类，会默认开启二级缓存. 
                                                取值 true|false 
     
    hibernate.cache.query_cache_factory         自定义实现QueryCache接口的类名, 默认为内建的StandardQueryCache. 
                                                取值 classname.of.QueryCache 
     
    hibernate.cache.region_prefix               二级缓存区域名的前缀. 
                                                取值 prefix 
     
    hibernate.cache.use_structured_entries      强制Hibernate以更人性化的格式将数据存入二级缓存. 
                                                取值 true|false 
 
Hibernate事务属性 

    属性名                                         用途  
    hibernate.transaction.factory_class             一个TransactionFactory的类名, 用于Hibernate Transaction API (默认为JDBCTransactionFactory). 
                                                    取值 classname.of.TransactionFactory 
     
    jta.UserTransaction                            一个JNDI名字，被JTATransactionFactory用来从应用服务器获取JTA UserTransaction. 
                                                    取值 jndi/composite/name 
     
    hibernate.transaction.manager_lookup_class      一个TransactionManagerLookup的类名 - 当使用JVM级缓存，或在JTA环境中使用hilo生成器的时候需要该类. 
                                                    取值 classname.of.TransactionManagerLookup 
     
    hibernate.transaction.flush_before_completion   如果开启, session在事务完成后将被自动清洗(flush)。 现在更好的方法是使用自动session上下文管理。请参见第 2.5 节 “上下文相关的（Contextual）Session”。 
                                                        取值 true | false 
     
    hibernate.transaction.auto_close_session        如果开启, session在事务完成后将被自动关闭。 现在更好的方法是使用自动session上下文管理。请参见第 2.5 节 “上下文相关的（Contextual）Session”。 
                                                    取值 true | false 
 
Hibernate日志类别 

    类别                                          功能  
    org.hibernate.SQL                           在所有SQL DML语句被执行时为它们记录日志  
    org.hibernate.type                          为所有JDBC参数记录日志  
    org.hibernate.tool.hbm2ddl                  在所有SQL DDL语句执行时为它们记录日志  
    org.hibernate.pretty                        在session清洗(flush)时，为所有与其关联的实体(最多20个)的状态记录日志  
    org.hibernate.cache                         为所有二级缓存的活动记录日志  
    org.hibernate.transaction                   为事务相关的活动记录日志  
    org.hibernate.jdbc                          为所有JDBC资源的获取记录日志  
    org.hibernate.hql.AST                       在解析查询的时候,记录HQL和SQL的AST分析日志  
    org.hibernate.secure                        为JAAS认证请求做日志  
    org.hibernate                               为任何Hibernate相关信息做日志 (信息量较大, 但对查错非常有帮助)  


hibernate-mapping

    <hibernate-mapping
             schema="schemaName"                          (1)
             catalog="catalogName"                        (2)
             default-cascade="cascade_style"              (3)
             default-access="field|property|ClassName"    (4)
             default-lazy="true|false"                    (5)
             auto-import="true|false"                     (6)
             package="package.name"                       (7)
     />
     
    (1) schema (可选): 数据库schema的名称。 
     
    (2) catalog (可选): 数据库catalog的名称。 
     
    (3) default-cascade (可选 - 默认为 none): 默认的级联风格。 
     
    (4) default-access (可选 - 默认为 property): Hibernate用来访问所有属性的策略。可以通过实现PropertyAccessor接口 自定义。 
     
    (5) default-lazy (可选 - 默认为 true): 指定了未明确注明lazy属性的Java属性和集合类， Hibernate会采取什么样的默认加载风格。 
     
    (6) auto-import (可选 - 默认为 true): 指定我们是否可以在查询语言中使用非全限定的类名（仅限于本映射文件中的类）。 
     
    (7) package (可选): 指定一个包前缀，如果在映射文档中没有指定全限定的类名， 就使用这个作为包名。 
     
---
<p>
    <a href="#" onclick="refreshContent('dbconnect')">返回目录</a>
</p>

