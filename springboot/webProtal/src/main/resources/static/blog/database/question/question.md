<p>
    <a href="#" onclick="refreshContent('database')">返回</a>

</p>

---

# Question 1.0

#### 1、什么情况下数据库索引会失效

    a、如果条件中有or，即使其中有条件带索引也不会使用(这也是为什么尽量少用or的原因)
        要想使用or，又想让索引生效，只能将or条件中的每个列都加上索引
    
    b、对于多列索引，不是使用的第一部分，则不会使用索引
    c、like查询是以%开头
    d、如果列类型是字符串，那一定要在条件中将数据使用引号引用起来,否则不使用索引
    
    e、在where子句中进行null值判断的话会导致引擎放弃索引而产生全表扫描
    f、避免在where子句中使用!= ,< >这样的符号,否则会导致引擎放弃索引而产生全表扫描
    g、避免在where子句中使用or来连接条件,因为如果俩个字段中有一个没有索引的话,引擎会放弃索引而产生全表扫描
    
    h、在使用联合索引是要注意最左原则

#### 2、分析sql 的关键字 explain + 语句

#### 3、连表查询

    外连接可分为：左连接、右连接、完全外连接。

        1、左连接  left join 或 left outer join
            SQL语句：select * from student left join course on student.ID=course.ID
            左外连接包含left join左表所有行，如果左表中某行在右表没有匹配，则结果中对应行右表的部分全部为空(NULL).


        2、右连接  right join 或 right outer join
            SQL语句：select * from student right join course on student.ID=course.ID
            右外连接包含right join右表所有行，如果左表中某行在右表没有匹配，则结果中对应左表的部分全部为空(NULL)。
        
        3、完全外连接  full join 或 full outer join
            SQL语句：select * from student full join course on student.ID=course.
            完全外连接包含full join左右两表中所有的行，如果右表中某行在左表中没有匹配，则结果中对应行右表的部分全部为空(NULL)，
            如果左表中某行在右表中没有匹配，则结果中对应行左表的部分全部为空(NULL)。
         
    内连接  join 或 inner join

        SQL语句：select * from student inner join course on student.ID=course.ID

        inner join 是比较运算符，只返回符合条件的行。
        此时相当于：select * from student,course where student.ID=course.ID     
    
    
    交叉连接 cross join

        1.概念：没有 WHERE 子句的交叉联接将产生连接所涉及的表的笛卡尔积。第一个表的行数乘以第二个表的行数等于笛卡尔积结果集的大小。
        SQL语句：select * from student cross join course
        
        如果我们在此时给这条SQL加上WHERE子句的时候比如SQL:select * from student cross join course where student.ID=course.ID
        此时将返回符合条件的结果集，结果和inner join所示执行结果一样

#### 4(Mysql)、执行SET CLOBAL log_bin_trust_function_creators = 1 报错

	现象：
	当有mysql本地或远程建立function或procedure时报上面的错误
	经试验是log_bin_trust_function_creators值为off导致
	设置：set global log_bin_trust_function_creators=1;
	但重启后失效

永久windows下my.ini[mysqld]段加上log_bin_trust_function_creators=1

linux下/etc/my.cnf下my.ini[mysqld]段加上log_bin_trust_function_creators=1

#### 5(Mysql)问题、

	Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that 
	uses a KEY column To disable safe mode, toggle the option in Preferences -> SQL Editor -> Query Editor and reconnect.

	SQL_SAFE_UPDATES有两个取值0和1，
	SQL_SAFE_UPDATES = 1时，不带where和limit条件的update和delete操作语句是无法执行的，即使是有where和limit条件但不带key column的update和delete也不能执行。
	SQL_SAFE_UPDATES = 0时，update和delete操作将会顺利执行。那么很显然，此变量的默认值是1。
	所以，出现1175错误的时候，可以先设置SQL_SAFE_UPDATES的值为0，然后再执行更新

#### 6(Mysql)、执行创建函数的sql语句时，提示:This function has none of DETERMINISTIC, NO SQL, or READS SQL DATA in its declaration and binary logging is enabled

<a href="https://www.cnblogs.com/kiko2014551511/p/11527423.html#" target="_blank">https://www.cnblogs.com/kiko2014551511/p/11527423.html </a>

#### 7、order by 排序

	按多个列排序行示例
		ORDER BY first_name, last_name DESC; --- 按first_name进行按升序排序，并按降序对last_name列进行排序
	
	按列位置排序行示例
		SELECT name, credit_limit,address FROM customers ORDER BY 2 DESC, 1;
		相当于
		SELECT name, credit_limit,address FROM customers ORDER BY credit_limit DESC, name;    

#### 8(oracle)、密码过期处理:

	通过管理员访问数据库 sys或者system
	
	--查看用户账户的状态
	select username,account_status from dba_users;
	--查看用户账户使用的密码的配置文件类型
	select username,PROFILE  from dba_users; 
	SELECT * FROM dba_profiles WHERE profile='DEFAULT' AND resource_name='PASSWORD_LIFE_TIME';
		
	https://www.cnblogs.com/luckly-hf/p/3828573.html
	demo：
		1、
			更改SYSMAN的密码
			alter user sysman identified by [new password];
			alter user sysman account ;
		2、
			alter user SCOTT identified by scott account unlock;

#### 9、事务

	事务提交 commit
		COMMIT [ WORK ] [ COMMENT clause ] [ WRITE clause ] [ FORCE clause ];
		参数
			WORK：可选的。它被Oracle添加为符合SQL标准。使用或不使用WORK参数来执行COMMIT将产生相同的结果。
			COMMENT clause：可选的。 它用于指定与当前事务关联的注释。 该注释最多可以包含在单引号中的255个字节的文本中。 如果出现问题，它将与事务ID一起存储在名为DBA_2PC_PENDING的系统视图中。
			WRITE clause：可选的。 它用于指定将已提交事务的重做信息写入重做日志的优先级。 用这个子句，有两个参数可以指定：WAIT 或 NOWAIT (如果省略，WAIT是默认值)IMMEDIATE或BATCH(IMMEDIATE是省略时的默认值)
			FORCE clause：可选的。 它用于强制提交可能已损坏或有疑问的事务。 有了这个子句，可以用3种方式指定FORCE：FORCE'string'，[integer]或FORCE CORRUPT_XID'string'或FORCE CORRUPT_XID_ALL
		注意
			必须拥有DBA权限才能访问系统视图 - DBA_2PC_PENDING和V$CORRUPT_XID_LIST。
			必须拥有DBA权限才能指定COMMIT语句的某些功能。
	事务回滚 rollback
		ROLLBACK [ WORK ] [ TO [SAVEPOINT] savepoint_name  | FORCE 'string' ];
		参数
			WORK：可选的。 它被Oracle添加为符合SQL标准。 使用或不使用WORK参数来发出ROLLBACK会导致相同的结果。
			TO SAVEPOINT savepoint_name：可选的。 ROLLBACK语句撤消当前会话的所有更改，直到由savepoint_name指定的保存点。 如果省略该子句，则所有更改都将被撤消。
			FORCE ‘string’：可选的。它用于强制回滚可能已损坏或有问题的事务。 使用此子句，可以将单引号中的事务ID指定为字符串。 可以在系统视图中找到名为DBA_2PC_PENDING的事务标识。
			必须拥有DBA权限才能访问系统视图：DBA_2PC_PENDING和V$CORRUPT_XID_LIST。
			您无法将有问题的事务回滚到保存点。
	事务设置 SET TRANSACTION
		SET TRANSACTION [ READ ONLY | READ WRITE ]
                [ ISOLATION LEVEL [ SERIALIZE | READ COMMITED ]
                [ USE ROLLBACK SEGMENT 'segment_name' ]
                [ NAME 'transaction_name' ];
		参考
		   ● READ ONLY：可以将事务设置为只读事务。
		   ● READ WRITE：可以将事务设置为读/写事务。
		   ● ISOLATION LEVEL： 如果指定，它有两个选项：
				1.ISOLATION LEVEL SERIALIZE：如果事务尝试更新由另一个事务更新并未提交的资源，则事务将失败。
				2.ISOLATION LEVEL READ COMMITTED：如果事务需要另一个事务持有的行锁，则事务将等待，直到行锁被释放。
		   ● USE ROLLBACK SEGMENT：可选的。 如果指定，它将事务分配给由'segment_name'标识的回退段，该段是用引号括起来的段名称。
		   ● NAME：为'transaction_name'标识的事务分配一个名称，该事务用引号括起来。		
				
	锁表 lock table
		LOCK TABLE tables IN lock_mode MODE [ WAIT [, integer] | NOWAIT ];
		参数
			tables：用逗号分隔的表格列表。
			WAIT：它指定数据库将等待(达到指定整数的特定秒数)以获取DML锁定。
			NOWAIT：它指定数据库不应该等待释放锁。
			lock_mode ：它是以下值之一：
				ROW SHARE	允许同时访问表，但阻止用户锁定整个表以进行独占访问。
				ROW EXCLUSIVE	允许对表进行并发访问，但阻止用户以独占访问方式锁定整个表并以共享方式锁定表。
				SHARE UPDATE	允许同时访问表，但阻止用户锁定整个表以进行独占访问。
				SHARE	允许并发查询，但用户无法更新锁定的表。
				SHARE ROW EXCLUSIVE	用户可以查看表中的记录，但是无法更新表或锁定SHARE表中的表。
				EXCLUSIVE	允许查询锁定的表格，但不能进行其他活动。

#### 10、内置函数

	Ascii()函数
		ASCII( single_character ) 
		参数
			single_character：指定的字符来检索NUMBER代码。 如果输入多个字符，则ASCII函数将返回第一个字符的值，
			并忽略第一个字符后的所有字符。
		返回值
			ASCII函数返回一个数值。

	Asciistr()函数
		ASCIISTR( string )
		参数
			string：任何字符集中的字符串，希望将其转换为数据库字符集中的ASCII字符串。
		返回值
			返回一个字符串值

	Chr()函数
		CHR( number_code )
		参数
			number_code：用于检索对应字符的NUMBER代码。
		返回值
			返回一个字符串值

	Compose()函数
		COMPOSE( string )
		参数	
			string：用于创建Unicode字符串的输入值。 它可以是char，varchar2，nchar，nvarchar2，clob或nclob。
		返回值
			返回一个字符串值。

	CONCAT()函数
		CONCAT( string1, string2 )
		参数
			string1：第一个要连接的字符串。
			string2：第二个要连接的字符串。
		返回值
			CONCAT函数返回string1连接string2后的一个字符串值。

	||运算符语法
		string1 || string2 [ || string_n ]
		参数
			string1： 第一个要连接的字符串。
			string2：第二个要连接的字符串。
			string_n：可选项，第n个要连接的字符串。
		返回值
			返回连接后的一个字符串值。
		
	Convert()函数
		CONVERT( string1, char_set_to [, char_set_from] )
		参数
			string1：要转换的字符串。
			char_set_to：要转换为的字符集。
			char_set_from：可选的，要从中转换的字符集。
		返回值
			CONVERT函数返回特定字符集中的字符串值。

	Initcap()函数
		INITCAP( string1 )
		参数
			string1 ：字符串参数，其中每个单词中的第一个字符将转换为大写字母，其余所有字符转换为小写字母。
		返回值
			返回一个字符串值。

	LOWER
		语法：LOWER(string)
		功能：所以字母小写

	UPPER
		语法: UPPER（string）
		功能: 所有字母大写.
		（不是字母的字符不变.如果string是CHAR数据类型的,那么结果也是CHAR类型的.如果string是VARCHAR2类型的,那么结果也是VARCHAR2类型的）.

#### 11,用户（Oracle）：

	sys：超级管理员用户，拥有数据库去啊不的最高权限
		所有的oracle的数据字典的基表和视图都放在sys用户中,这些基表和视图对于oracle运行是至关重要的,这些都由数据库自己维护,
		任何用户都不能手动更改。
		sys用户拥有dba、sysoper、sysdba角色或权限是Oracle中权限最高的用户，sys用户不能以narmal身份登录
	system: 用于存放次一级的内部数据,如oracle的一些特性或工具的管理信息.
	
	安装 ORACLE 时，若没有为下列用户重设密码，则其默认密码如下：

		用户名 / 密码 		 		登录身份				说明
		sys/change_on_install		SYSDBA 或 SYSOPER		不能以 NORMAL 登录，可作为默认的系统管理员
		system/manager				SYSDBA 或 NORMAL		不能以 SYSOPER 登录，可作为默认的系统管理员
		sysman/oem_temp										sysman 为 oms 的用户名
		scott/tiger					NORMAL					普通用户
		aqadm /aqadm				SYSDBA 或 NORMAL		高级队列管理员
		Dbsnmp/dbsnmp				SYSDBA 或 NORMAL		复制管理员

<p>
    <a href="#" onclick="refreshContent('database')">返回</a>
</p>