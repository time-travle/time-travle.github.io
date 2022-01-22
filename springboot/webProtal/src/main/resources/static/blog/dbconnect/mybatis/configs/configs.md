<ul><a href="#" onclick="refreshDBConnectContent('mybatis')">返回</a></ul>

###配置文件的说明
在mybatis框架自己对应的配置文件中比较重要的 配模块是 properties 和settings 两部分

properties
    
	这些属性都是可外部配置且可动态替换的，既可以在典型的 Java 属性文件中配置，亦可通过 properties 元素的子元素来传递
    demo：
        <properties resource="org/mybatis/example/config.properties">
          <property name="username" value="dev_user"/>
          <property name="password" value="F2Fa3!33TYyg"/>
        </properties>
    
    在这个位置配置的一些属性可以在文件中其他的位置进行应用，起到一处变处处变。

    将数据库连接参数单独配置在db.properties(名称可变)中，放在类路径下。
    这样只需要在SqlMapConfig.xml中加载db.properties的属性值。
    这样在SqlMapConfig.xml中就不需要对数据库连接参数硬编码。
    将数据库连接参数只配置在db.properties中，
    原因：方便对参数进行统一管理，其它xml可以引用该db.properties
 
    例如：db.properties
     jdbc.driver=com.mysql.jdbc.Driver
     jdbc.url=jdbc:mysql://localhost:3306/mybatis
     jdbc.username=root
     jdbc.password=root
 
	 相应的SqlMapConfig.xml
	   <properties resource="db.properties"/>
	   
		<environments default="development">
			<environment id="development">
				<transactionManager type="JDBC"/>
				<dataSource type="POOLED">
					<property name="driver" value="${jdbc.driver}"/>
					<property name="url" value="${jdbc.url}"/>
					<property name="username" value="${jdbc.username}"/>
					<property name="password" value="${jdbc.password}"/>
				</dataSource>
			</environment>
		</environments>
 
	注意： MyBatis 将按照下面的顺序来加载属性：
	 首先、在properties标签中指定的属性文件首先被读取。
	 其次、会读取properties元素中resource或 url 加载的属性，它会覆盖已读取的同名属性。
	 最后、读取parameterType传递的属性，它会覆盖已读取的同名属性。
	常用做法：
	 不要在properties元素体内添加任何属性值，只将属性值定义在外部properties文件中。
	 在properties文件中定义属性名要有一定的特殊性，如：XXXXX.XXXXX.XXXX的形式，就像jdbc.driver。这样可以防止和parameterType传递的属性名冲突，从而被覆盖掉。
 
 
settings

https://www.cnblogs.com/lone5wolf/p/10955780.html
    
	这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为
        
    mybatis全局配置参数，全局参数将会影响mybatis的运行行为。比如：开启二级缓存、开启延迟加载。具体可配置情况如下：
      
    配置示例：
        <settings>
            <!-- 在 mybatis-config.xml中开启二级缓存 -->
           <setting name="cacheEnabled" value="true"/>
           <!-- 在 mybatis-config.xml中开启懒加载 -->
           <setting name="lazyLoadingEnabled" value="true"/>
           <!-- 在 mybatis-config.xml中同时开启多条 -->
           <setting name="multipleResultSetsEnabled" value="true"/>
        </settings>
        
    一个完整的settings元素示例
     <settings>
      <setting name="cacheEnabled" value="true"/>
      <setting name="lazyLoadingEnabled" value="true"/>
      <setting name="multipleResultSetsEnabled" value="true"/>
      <setting name="aggressiveLazyLoading" value="true"/>
      <setting name="useColumnLabel" value="true"/>
      <setting name="useGeneratedKeys" value="false"/>
      <setting name="autoMappingBehavior" value="PARTIAL"/>
      <setting name="defaultExecutorType" value="SIMPLE"/>
      <setting name="defaultStatementTimeout" value="25"/>
      <setting name="defaultFetchSize" value="100"/>
      <setting name="safeRowBoundsEnabled" value="false"/>
      <setting name="mapUnderscoreToCamelCase" value="false"/>
      <setting name="localCacheScope" value="SESSION"/>
      <setting name="jdbcTypeForNull" value="OTHER"/>
      <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
    </settings>

详细描述：
设置参数||描述||有效值||默认值

cacheEnabled
	
	该配置影响的所有映射器中配置的缓存的全局开关。||true,false||true

lazyLoadingEnabled
	
	延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置

fetchType
	
	属性来覆盖该项的开关状态。||true,false||false

aggressiveLazyLoading

	当启用时，对任意延迟属性的调用会使带有延迟加载属性的对象完整加载；反之，每种属性将会按需加载。||true,false,true

multipleResultSetsEnabled
	
	是否允许单一语句返回多结果集（需要兼容驱动）。||true,false||true

useColumnLabel
	
	使用列标签代替列名。不同的驱动在这方面会有不同的表现， 具体可参考相关驱动文档或通过测试这两种不同的模式来观察所用驱动的结果。||true,false||true

useGeneratedKeys
	
	允许 JDBC 支持自动生成主键，需要驱动兼容。 
	如果设置为 true 则这个设置强制使用自动生成主键，尽管一些驱动不能兼容但仍可正常工作（比如 Derby）。||true,false||False

autoMappingBehavior
	
	指定 MyBatis 应如何自动映射列到字段或属性。 
	NONE 表示取消自动映射；PARTIAL 只会自动映射没有定义嵌套结果集映射的结果集。 
	FULL 会自动映射任意复杂的结果集（无论是否嵌套）。||NONE, PARTIAL, FULL||PARTIAL

defaultExecutorType

	配置默认的执行器。SIMPLE 就是普通的执行器；REUSE 执行器会重用预处理语句（prepared statements）； BATCH 执行器将重用语句并执行批量更新。||SIMPLE REUSE BATCH||SIMPLE

defaultStatementTimeout
	
	设置超时时间，它决定驱动等待数据库响应的秒数。||Any positive integer||Not Set (null)

safeRowBoundsEnabled
	允许在嵌套语句中使用分页（RowBounds）。||true,false||False

mapUnderscoreToCamelCase

	是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射。||true, false||False

localCacheScope
	
	MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。 默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。 若设置值为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据。||SESSION,STATEMENT||SESSION

jdbcTypeForNull

	当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。 
	某些驱动需要指定列的 JDBC 类型，多数情况直接用一般类型即可，比如 NULL、VARCHAR 或 OTHER。||JdbcType enumeration. Most common are: NULL, VARCHAR and OTHER||OTHER

lazyLoadTriggerMethods

	指定哪个对象的方法触发一次延迟加载。||A method name list separated by commas||equals,clone,hashCode,toString

defaultScriptingLanguage

	指定动态 SQL 生成的默认语言。||A type alias or fully qualified class name.||org.apache.ibatis.scripting.xmltags.XMLDynamicLanguageDriver

callSettersOnNulls
	
	指定当结果集中值为 null 的时候是否调用映射对象的 setter（map 对象时为 put）方法，这对于有 Map.keySet() 依赖或 null 值初始化的时候是有用的。
	注意基本类型（int、boolean等）是不能设置成 null 的。||true,false||false

logPrefix
	
	指定 MyBatis 增加到日志名称的前缀。||Any String||Not set

logImpl

	指定 MyBatis 所用日志的具体实现，未指定时将自动查找。||SLF4J,LOG4J,LOG4J2,JDK_LOGGING,COMMONS_LOGGING,STDOUT_LOGGING,NO_LOGGING||Not set

proxyFactory
	
	指定 Mybatis 创建具有延迟加载能力的对象所用到的代理工具。||CGLIB JAVASSIST||CGLIB




