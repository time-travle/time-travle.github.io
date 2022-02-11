

# Mybatis 框架
<p>
    <a href="#" onclick="refreshContent('dbconnect')">返回目录</a>
</p>
<p>
<a href="#" onclick="refreshybatisContent('configs')">properties 和settings</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshybatisContent('mapconfig')">Mapper 配置</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshybatisContent('questions')">questions</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshybatisContent('dynamic')">动态 sql 拼写</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshybatisContent('linkedquery')">连表查询 sql 拼写</a>&emsp;&emsp;&emsp;
</p>

---

####模糊查询的写法
	<if test="">// 使用|| 进行拼接
		like '%'|| value || '%' ---> like '%value%' 
	</if>
	<if test="">// 使用contat进行拼接
		like contat("%",value)  ---> like '%value'
	</if>
		
##映射配置	
###select
    查询是我们在日常的数据处理中使用的频率最高的一个语法，也是相对其他的语法来说是出现的频率最高的一个
    基础的格式：
        <select id="该条语句在本文件中的唯一身份编码" parameter="入参的类型" resultType="出参类型">
            查询语句的
        </select>
    入参和出参我们要根据我们的需要进行选择而不是任意的定义的，一切以实用为主，
    select 除了这基础的外还有一些其他的入参，
    
    
    下面将查询语句的可以选择的参数进行展示
    
    属性～～描述
    id～～在命名空间中唯一的标识符，可以被用来引用这条语句。
    parameterType～～将会传入这条语句的参数类的完全限定名或别名。这个属性是可选的，因为 MyBatis 可以通过 TypeHandler 推断出具体传入语句的参数，默认值为 unset。
    resultType～～从这条语句中返回的期望类型的类的完全限定名或别名。注意如果是集合情形，那应该是集合可以包含的类型，而不能是集合本身。使用 resultType 或 resultMap，但不能同时使用。
    resultMap～～外部 resultMap 的命名引用。结果集的映射是 MyBatis 最强大的特性，对其有一个很好的理解的话，许多复杂映射的情形都能迎刃而解。使用 resultMap 或 resultType，但不能同时使用。
    resultOrdered～～这个设置仅针对嵌套结果 select 语句适用：如果为 true，就是假设包含了嵌套结果集或是分组了，这样的话当返回一个主结果行的时候，就不会发生有对前面结果集的引用的情况。这就使得在获取嵌套的结果集的时候不至于导致内存不够用。默认值：false。
    resultSets～～这个设置仅对多结果集的情况适用，它将列出语句执行后返回的结果集并每个结果集给一个名称，名称是逗号分隔的。
    resultSetType～～FORWARD_ONLY，SCROLL_SENSITIVE 或 SCROLL_INSENSITIVE 中的一个，默认值为 unset （依赖驱动）。
    
    flushCache～～将其设置为 true，任何时候只要语句被调用，都会导致本地缓存和二级缓存都会被清空，默认值：false。
    useCache～～将其设置为 true，将会导致本条语句的结果被二级缓存，默认值：对 select 元素为 true。
    timeout～～这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为 unset（依赖驱动）。
    fetchSize～～这是尝试影响驱动程序每次批量返回的结果行数和这个设置值相等。默认值为 unset（依赖驱动）。
    statementType～～STATEMENT，PREPARED 或 CALLABLE 的一个。这会让 MyBatis 分别使用 Statement，PreparedStatement 或 CallableStatement，默认值：PREPARED。
    
    databaseId～～如果配置了 databaseIdProvider，MyBatis 会加载所有的不带 databaseId 或匹配当前 databaseId 的语句；如果带或者不带的语句都有，则不带的会被忽略。
    
###update/delete/insert
    但我们进行批量的操作时少不了需要使用到set的这个关键字<set></set>
    
    对应语句的对应的属性
    属性～～描述
    id～～命名空间中的唯一标识符，可被用来代表这条语句。
    parameterType～～将要传入语句的参数的完全限定类名或别名。这个属性是可选的，因为 MyBatis 可以通过 TypeHandler 推断出具体传入语句的参数，默认值为 unset。
    flushCache～～将其设置为 true，任何时候只要语句被调用，都会导致本地缓存和二级缓存都会被清空，默认值：true（对应插入、更新和删除语句）。
    timeout～～这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为 unset（依赖驱动）。
    databaseId～～如果配置了 databaseIdProvider，MyBatis 会加载所有的不带 databaseId 或匹配当前 databaseId 的语句；如果带或者不带的语句都有，则不带的会被忽略。
    statementType～～STATEMENT，PREPARED 或 CALLABLE 的一个。这会让 MyBatis 分别使用 Statement，PreparedStatement 或 CallableStatement，默认值：PREPARED。
    
    useGeneratedKeys～～（仅对 insert 和 update 有用）这会令 MyBatis 使用 JDBC 的 getGeneratedKeys 方法来取出由数据库内部生成的主键（比如：像 MySQL 和 SQL Server 这样的关系数据库管理系统的自动递增字段），默认值：false。
    keyProperty～～（仅对 insert 和 update 有用）唯一标记一个属性，MyBatis 会通过 getGeneratedKeys 的返回值或者通过 insert 语句的 selectKey 子元素设置它的键值，默认：unset。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表。
    keyColumn～～（仅对 insert 和 update 有用）通过生成的键值设置表中的列名，这个设置仅在某些数据库（像 PostgreSQL）是必须的，当主键列不是表中的第一列的时候需要设置。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表。
    
    
    
    这时的语句除了有其自己的属性还有内嵌子sql（selectKey）的存在
    
    selectKey 的属性
    
    属性～～描述
    
    keyProperty～～selectKey 语句结果应该被设置的目标属性。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表。
    keyColumn～～匹配属性的返回结果集中的列名称。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表。
    resultType～～结果的类型。MyBatis 通常可以推算出来，但是为了更加确定写上也不会有什么问题。MyBatis 允许任何简单类型用作主键的类型，包括字符串。如果希望作用于多个生成的列，则可以使用一个包含期望属性的 Object 或一个 Map。
    order～～这可以被设置为 BEFORE 或 AFTER。如果设置为 BEFORE，那么它会首先选择主键，设置 keyProperty 然后执行插入语句。如果设置为 AFTER，那么先执行插入语句，然后是 selectKey 元素 - 这和像 Oracle 的数据库相似，在插入语句内部可能有嵌入索引调用。
    statementType～～与前面相同，MyBatis 支持 STATEMENT，PREPARED 和 CALLABLE 语句的映射类型，分别代表 PreparedStatement 和 CallableStatement 类型。
    
    
###sql块
    这个元素可以被用来定义可重用的 SQL 代码段，可以包含在其他语句中
    
    声明：
        <sql id="文件内唯一编码">
            ...
            在这里可以自己定义属于这个的属性，也可以同样的通过include 来引入其他的sql的内容
        </sql>
    使用：
        <include refid="文件内唯一编码的sql">
            可以给sql进传参数 ，若是没有写就默认不进行传，直接使用sql指定的内容
        </include>
###resultMap
    resultMap 元素是 MyBatis 中最重要最强大的元素
    ResultMap 的设计就是简单语句不需要明确的结果映射,而很多复杂语句确实需要描述它们 的关系。
    
###cache 缓存 
    开启二级缓存的方法 在SQl映射文件中添加就会震动当前的文件中的sql自动开启二级缓存
    <cache/>
    
    这个简单语句的效果如下:
    
    映射语句文件中的所有 select 语句将会被缓存。
    映射语句文件中的所有 insert,update 和 delete 语句会刷新缓存。
    缓存会使用 Least Recently Used(LRU,最近最少使用的)算法来收回。
    根据时间表(比如 no Flush Interval,没有刷新间隔), 缓存不会以任何时间顺序 来刷新。
    缓存会存储列表集合或对象(无论查询方法返回什么)的 1024 个引用。
    缓存会被视为是 read/write(可读/可写)的缓存,意味着对象检索不是共享的,而 且可以安全地被调用者修改,而不干扰其他调用者或线程所做的潜在修改。
    所有的这些属性都可以通过缓存元素的属性来修改。
    比如:
        <cache
          eviction="FIFO"   默认LRU
          flushInterval="60000"  
          size="512"    默认 1024
          readOnly="true"/>  默认false
    这个更高级的配置创建了一个 FIFO 缓存,并每隔 60 秒刷新,存数结果对象或列表的 512 个引用,而且返回的对象被认为是只读的,因此在不同线程中的调用者之间修改它们会 导致冲突。
     
    可用的收回策略有:
    LRU – 最近最少使用的:移除最长时间不被使用的对象。  默认
    FIFO – 先进先出:按对象进入缓存的顺序来移除它们。
    SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。
    WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。
	

##视图
    把视图当普通的表，用select  字段  from 视图 where 的形式。
    如：
    <select id="getListByName" parameterType="java.util.Map" resultMap="Country">
        SELECT DISTINCT countryname FROM `idc_cmdb`.view_city  
            <where>
                <if test="countryname != null">
                    countryname LIKE  CONCAT('%', '${countryname}', '%')
                </if>
            </where>
    </select>
    备注： mapper.xml中where后面的参数条件一定是在视图中返回的，不然会报错。

##存储过程
    demo1:
     <!-- 调用存储过程查询 -->
     <select id="testProcedure" parameterType="java.util.Map" statementType="CALLABLE" resultType="java.util.HashMap">  
        {
            call loginandreg(
                #{out_ret,mode=OUT,javaType=java.lang.Integer,jdbcType=INTEGER},
                #{out_desc,mode=OUT,javaType=java.lang.String,jdbcType=VARCHAR},
                #{userId,jdbcType=VARCHAR,mode=OUT},
                #{user_pwd,jdbcType=VARCHAR,mode=IN},
                #{nickname,jdbcType=VARCHAR,mode=IN}
            )
        }
      </select>
      
     demo2:
     有返回结果集
     如存储过程结构如下：
         p_my_wallet(IN var_user_id INT)；
         参数是用户id
         revenue_today   今日收益
         revenue_contacts  人脉收益
         balance   可用余额
     sql部分如下：
         <!-- 获取钱包信息 -->
         <select id="getMyWallet" parameterType="java.lang.Integer" resultType="java.util.Map" statementType="CALLABLE">
         {
         　　call p_my_wallet(
         　　　　#{userId,jdbcType=INTEGER,mode=IN}
         　　)
         }
         </select>
##序列
    oracle的写法：
        <selectKey keyProperty="id" resultType="long" order="BEFORE">
          SELECT user_id_sequence.nextval from dual
        </selectKey>

##insert all
    demo1：
     <insert id="batchInsertArriveInfo" parameterType="java.util.List" useGeneratedKeys="false">
        insert all
        <foreach collection="list" item="info" index="index">
          into
          T_ARRIVAL_STATION_INFORMATION
          (BUS_PATH_ID,STATION_SN,TASK_STATUS,ARR_TIME,BUS_PATH_NAME,RUN_FLAG,VEHICLE_ID,station_name,station_id)
          values
          (
          #{info.busPathId},#{info.stationSn},#{info.taskStatus},
          #{info.arrTime},#{info.busPathName},#{info.runFlag},
          #{info.vehicleId},#{info.stationName},#{info.stationId}
          )
        </foreach>
        select 1 from dual
      </insert>	