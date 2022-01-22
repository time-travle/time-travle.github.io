<ul><a href="#" onclick="refreshDBConnectContent('mybatis')">返回</a></ul>

MyBatis中#{}和${}的区别    			

	#{}：占位符号，好处防止sql注入 （替换结果会增加单引号‘’）
    ${}：sql拼接符号 不会转义字符串（替换结果不会增加单引号‘’，like和order by后使用，存在sql注入问题，需手动代码中过滤）
	示例1： 
	执行SQL：Select * from emp where name = #{employeeName} 
	参数：employeeName=>Smith 
	解析后执行的SQL：Select * from emp where name = ？ 
	执行SQL：Select * from emp where name = ${employeeName} 
	参数：employeeName传入值为：Smith 
	解析后执行的SQL：Select * from emp where name =Smith


学习期间遇到的疑问

1、为什么要使用缓存，什么是一级缓存 二级缓存 如何开户并使用 

	使用缓存是为了减少程序和数据库之间的频繁交互，导致压垮数据库，同时也可以提高程序的执行效率。
	作用域(一级缓存 Session/二级缓存Namespaces)
	一级缓存：默认是开启的
		SqlSession级别的缓存，缓存的是对象
		当我们执行查询以后，查询的结果会同时存入到SqlSession为我们提供的一块区域中，该区域的结构是一个Map，
		不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。
		当我们再次查询同样的数据，mybatis会先去sqlsession中查询是否有，的话直接拿出来用，
		也就是在同一个SqlSession中，执行相同的查询SQL，第一次会去数据库进行查询，并写到缓存中；第二次以后是直接去缓存中取
		当SqlSession对象消失时，mybatis的一级缓存也就消失了，同时一级缓存是SqlSession范围的缓存，
		当调用SqlSession的修改、添加、删除、commit(),close等方法时，就会清空一级缓存。

		remark：
			在同一个sqlSession中两次执行相同的sql语句，第一次执行完毕会将数据库中查询的数据写到缓存（内存），
			第二次会从缓存中获取数据将不再从数据库查询，从而提高查询效率。当一个sqlSession结束后该sqlSession中的一级缓存也就不存在了
		
		关闭一级缓存：
		一级缓存的范围有SESSION和STATEMENT两种，默认是SESSION，如果不想使用一级缓存，可以把一级缓存的范围指定为STATEMENT，
		这样每次执行完一个Mapper中的语句后都会将一级缓存清除。
		如果需要更改一级缓存的范围，可以在Mybatis的配置文件中，在下通过localCacheScope指定。
		<setting name="localCacheScope" value="STATEMENT"/>
		
	二级缓存：默认是关闭的
		二级缓存是手动开启的，作用域为sessionfactory（也可以说MapperStatement级缓存，也就是一个namespace就会有一个缓存），
		因为二级缓存的数据不一定都是存储到内存中，它的存储介质多种多样，实现二级缓存的时候，MyBatis要求返回的POJO必须是可序列化的，
		也就是要求实现Serializable接口，如果存储在内存中的话，实测不序列化也可以的。
		
		SqlSessionFactory对象的缓存，由同一个SqlSessionFactory对象创建的SqlSession共享其缓存，
		但是其中缓存的是数据而不是对象，所以从二级缓存再次查询出得结果的对象与第一次存入的对象是不一样的。
	
	开启二级缓存并使用：
		第一步让Mybatis框架支持二级缓存（在Mybatis的主配置文件中配置），
		第二步让当前的映射文件支持二级缓存（在Dao.xml映射文件中配置），
		第三步让当前的方法支持二级缓存（在标签中配置）
		　1.配置Mybatis框架支持二级缓存
			<setting name="cacheEnabled" value="true"/>
		　2.配置UserDao.xml支持二级缓存
			<cache/> <!-- 加上该句即可，使用默认配置-->
		　3.配置查询的方法支持二级缓存  如果不想该语句缓存，可使用useCache="false"  flushCache='true' 来手动刷新缓存
			<select id="findById" resultType="com.example.domain.User" parameterType="INT" useCache="true">
				SELECT * FROM  USER  WHERE ID = #{ID}
			</select>
	
	一级缓存是SqlSession级别的缓存，
	一级缓存缓存的是对象，当SqlSession提交、关闭以及其他的更新数据库的操作发生后，一级缓存就会清空。
	二级缓存是SqlSessionFactory级别的缓存，同一个SqlSessionFactory产生的SqlSession都共享一个二级缓存，
	二级缓存中存储的是数据，当命中二级缓存时，通过存储的数据构造对象返回。
	
	查询数据的时候，查询的流程是二级缓存>一级缓存>数据库。

	二级缓存的使用原则
		1、只能在一个命名空间下使用二级缓存
		由于二级缓存中的数据是基于namespace的，即不同namespace中的数据互不干扰。
		在多个namespace中若均存在对同一个表的操作，那么这多个namespace中的数据可能就会出现不一致现象。
		2、在单表上使用二级缓存 
		如果一个表与其它表有关联关系，那么久非常有可能存在多个namespace对同一数据的操作。
		而不同namespace中的数据互补干扰，所以就有可能出现多个namespace中的数据不一致现象。
		3、查询多于修改时使用二级缓存
		在查询操作远远多于增删改操作的情况下可以使用二级缓存。因为任何增删改操作都将刷新二级缓存，
		对二级缓存的频繁刷新将降低系统性能。


2、一级缓存的生命周期

	a、MyBatis在开启一个数据库会话时，会 创建一个新的SqlSession对象，SqlSession对象中会有一个新的Executor对象。
		Executor对象中持有一个新的PerpetualCache对象；当会话结束时，SqlSession对象及其内部的Executor对象还有PerpetualCache对象也一并释放掉。
    b、如果SqlSession调用了close()方法，会释放掉一级缓存PerpetualCache对象，一级缓存将不可用。
    c、如果SqlSession调用了clearCache()，会清空PerpetualCache对象中的数据，但是该对象仍可使用。
    d、SqlSession中执行了任何一个update操作(update()、delete()、insert()) ，都会清空PerpetualCache对象的数据，但是该对象可以继续使用



3、什么样数据适合用缓存，什么数据适合用一级缓存，什么数据适合使用二级缓存
	
	经常查询同时不经常改变的，最好数据的正确性对最终结果影响不大
	对于经常进行改变的数据是不适宜用缓存的



4、执行查询的流程

	SqlSessionFactoryBuilder->parse->Configuation->build->SqlSessionFactory
	->openSession->SqlSession->query->Executor->newStatementHandler->
	StatementHandler->handlerResultSets->ResultSetHandler
	
	
5、如何开启事务 并使用

	首先，找到你的service实现类，加上@Transactional 注解，
		demo：@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	如果你加在类上，那该类所有的方法都会被事务管理，
	如果你加在方法上，那仅仅该方法符合具体的事务。
	当然我们一般都是加在方法上。因为只有增、删、改才会需要事务
	
	配置完后，spring boot启动类必须要开启事务，而开启事务用的注解就是@EnableTransactionManagement
	
	
6、@Transactional事务几点注意

	A. 一个功能是否要事务，必须纳入设计、编码考虑。不能仅仅完成了基本功能就ok。
	B. 如果加了事务，必须做好开发环境测试（测试环境也尽量触发异常、测试回滚），确保事务生效。
	C. 以下列了事务使用过程的注意事项，请大家留意。
		1. 不要在接口上声明@Transactional ，而要在具体类的方法上使用 @Transactional 注解，否则注解可能无效。
		2.不要图省事，将@Transactional放置在类级的声明中，放在类声明，会使得所有方法都有事务。故@Transactional应该放在方法级别，
			不需要使用事务的方法，就不要放置事务，比如查询方法。否则对性能是有影响的。
		3.使用了@Transactional的方法，对同一个类里面的方法调用， @Transactional无效。比如有一个类Test，它的一个方法A，
			A再调用Test本类的方法B（不管B是否public还是private），但A没有声明注解事务，而B有。则外部调用A之后，B的事务是不会起作用的。
			（经常在这里出错）
		4.使用了@Transactional的方法，只能是public，@Transactional注解的方法都是被外部其他类调用才有效，故只能是public。
			道理和上面的有关联。故在 protected、private 或者 package-visible 的方法上使用 @Transactional 注解，它也不会报错，但事务无效。
		5.经过在ICORE-CLAIM中测试，效果如下：
	A.抛出受查异常XXXException，事务会回滚。
	B.抛出运行时异常NullPointerException，事务会回滚。
	C.Quartz中，execute直接调用加了@Transactional方法，可以回滚；间接调用，不会回滚。（即上文3点提到的）
	D.异步任务中，execute直接调用加了@Transactional方法，可以回滚；间接调用，不会回滚。（即上文3点提到的）
	E.在action中加上@Transactional，不会回滚。切记不要在action中加上事务。
	F.在service中加上@Transactional，如果是action直接调该方法，会回滚，如果是间接调，不会回滚。（即上文3提到的）
	G.在service中的private加上@Transactional，事务不会回滚	


7、表中的字段和现有的实体类属性名对不上的处理方法

	第1种解决方案：通过在查询的sql语句中定义字段名的别名，让字段名的别名和实体类的属性名一致
	第2种解决方案：通过<resultMap>来映射字段名和实体类属性名的一一对应的关系
	
	
8、模糊查询的写法

	<if test="">// 使用|| 进行拼接
		like '%'|| value || '%' ---> like '%value%' 
	</if>
	<if test="">// 使用contat进行拼接
		like contat("%",value)  ---> like '%value'
	</if>	


9、Dao接口如何工作 里面的方法可以重载吗（不可以重载）

	Dao接口即Mapper接口。接口的全限名，就是映射文件中的namespace的值 进行接口的绑定；
	接口的方法名，就是映射文件中Mapper的Statement的id值；接口方法内的参数，就是传递给sql的参数。

	Mapper接口是没有实现类的，当调用接口方法时，接口全限名+方法名拼接字符串作为key值，可唯一定位一个MapperStatement。在Mybatis中，
	每一个 <select>、<insert>、<update>、<delete>标签，都会被解析为一个MapperStatement对象。
	
	Mapper接口里的方法，是不能重载的，因为是使用 全限名+方法名 的保存和寻找策略。
	
	Mapper 接口的工作原理是JDK动态代理，Mybatis运行时会使用JDK动态代理为Mapper接口生成代理对象proxy，代理对象会拦截接口方法，
	转而执行MapperStatement所代表的sql，然后将sql执行结果返回


10、接口的绑定方式

	一种是通过注解绑定，就是在接口的方法上面加上 @Select、@Update等注解，里面包含Sql语句来绑定；（sql比较简单时使用）
	一种就是通过xml里面写SQL来绑定，在这种情况下，要指定xml映射文件里面的namespace必须为接口的全路径名。（SQL比较复杂时使用）