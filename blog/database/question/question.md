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

####12、MySql 存储引擎 有哪几种 什么区别


|Engine|Support|Comment|Transactions|XA|Savepoints|
|---|---|---|---|---|---|
|MEMORY|YES|Hash based, stored in memory, useful for temporary tables(基于哈希，存储在内存中，对临时表有用)|NO|NO|NO|
|MRG_MYISAM|YES|Collection of identical MyISAM tables(相同 MyISAM 表的集合)|NO|NO|NO|
|CSV|YES|CSV storage engine(CSV 存储引擎)|NO|NO|NO|
|FEDERATED|NO|Federated MySQL storage engine(联合 MySQL 存储引擎)||||
|PERFORMANCE_SCHEMA|YES|Performance Schema(性能模式)|NO|NO|NO|
|MyISAM|YES|MyISAM storage engine(MyISAM 存储引擎)|NO|NO|NO|
|InnoDB|DEFAULT|Supports transactions, row-level locking, and foreign keys(支持事务、行级锁定和外键)|YES|YES|YES|
|BLACKHOLE|YES|/dev/null storage engine (anything you write to it disappears)|NO|NO|NO|
|ARCHIVE|YES|Archive storage engine(存档存储引擎)|NO|NO|NO|


在MySQL中，不需要在整个服务器中使用同一种存储引擎，针对具体的要求，可以对每一个表使用不同的存储引擎。
其中几种常用的引擎。

#####一 、Innodb
    
    支持事务，是事务安全的，提供行级锁与外键约束，有缓冲池，用于缓冲数据和索引。
    
    适用场景：用于事务处理，具有ACID事物支持，应用于执行大量的insert和update操作的表。
    
    InnoDB是mysql5.5.x开始默认的事务型引擎，也是使用最广泛的存储引擎。被设计用来处理大量短期事务的。
    
    InnoDB所有的表都保存在同一个数据文件中（也可能是多个文件，或者是独立的表空间文件），表的大小只受限于操作系统文件的大小。
    表的结构定义存在 .frm后缀文件中，数据和索引集中存放在 .idb后缀文件中。因为表数据和索引是在同一个文件，InnoDB的索引是聚簇索引。
    
    InnoDB采用MVCC支持高并发，并且实现了四种标准的隔离级别(读未提交，读已提交，可重复读，可串行化)，其默认级别是REPEATABLE-READ(可重复读)，并且通过间隙锁(next-key locking)策略防止幻读的出现。间隙锁不仅仅锁定查询涉及的行，还会对索引中的间隙行进行锁定，以防止幻影行的插入。
    InnoDB表是基于聚簇索引建立的，聚簇索引对主键的查询有很高的性能。
    但是InnoDB的非主键索引中必须包含主键列，所以如果主键列很大的话，非主键索引也会很大。
    如果一张表的索引较多，主键应该尽可能的小。 关于索引，后面会详细讲解。
    
    InnoDB的内部优化，包括磁盘预读(从磁盘读取数据时采用可预测性读取)，自适应哈希(自动在内存中创建hash索引以加速读操作)以及能够加速插入操作的插入缓冲区。

#####二 、MyISAM

    不支持事务，不支持外键约束，不支持行级锁，操作时需要锁定整张表，不过会保存表的行数，所以当执行select count(*) from tablename时执行特别快。
    
    适用场景：用于管理非事务表，提供高速检索及全文检索能力，适用于有大量的select操作的表，如 日志表
    
    MyISAM的数据表存储在磁盘上是3个文件，表结构定义存在 .frm后缀文件中，表数据存储在 .MYD后缀文件中，表索引存储在 .MYI后缀文件中。
    表数据和表索引在不同的文件中，所以MyISAM索引是非聚簇索引。而且MyISAM可以存储表数据的总行数。
    
    MyISAM表支持数据压缩，对于表创建后并导入数据以后，不需要修改操作，可以采用MyISAM压缩表。
    压缩命令：myisampack，压缩表可以极大的减少磁盘空间占用，因此也可以减少磁盘I/O，提高查询性能。
    而且压缩表中的数据是单行压缩，所以单行读取是不需要解压整个表。



#####三 、MEMORY

    使用存在于内存中的内容创建表，每一个memory只实际对应一个磁盘文件。因为是存在内存中的，所以memory访问速度非常快，
    而且该引擎使用hash索引，可以一次定位，不需要像B树一样从根节点查找到支节点，所以精确查询时访问速度特别快，但是非精确查找时，
    比如like，这种范围查找，hash就起不到作用了。另外一旦服务关闭，表中的数据就会丢失，因为没有存到磁盘中。
    
    适用场景：主要用于内容变化不频繁的表，或者作为中间的查找表。对表的更新要谨慎因为数据没有被写入到磁盘中，服务关闭前要考虑好数据的存储
    
    
    Memory存储引擎的数据是存放在内存中的，所以如果服务器重启会导致数据丢失，但是表结构还是存在的表结构是以 .frm 后缀的文件中。
    
    Memory默认hash索引，因此查询非常快。Memory表是表级锁，因此并发写入的性能较低。不支持BLOB或TEXT类型的列，并且每行的长度都是固定的，
    所以即使指定了varchar列实际存储也会转换成char，会导致内存浪费。
    
    如果mysql查询过程中需要使用临时表来保存中间结果，内部使用的临时表就是Memory表，如果中间结果太大超出Memory表的限制或者含有BLOB或TEXT字段，那么临时表会转换成MyISAM表。

<a href="https://www.runoob.com/w3cnote/mysql-different-nnodb-myisam.html" target="_blank">MySQL存储引擎InnoDB与Myisam的六大区别 </a>

上面介绍了种，你如何选择存储引擎呢：

    事务 ：目前只有Innodb能完美的支持事务。
    备份 ：只有Innodb有免费的在线热备方案，mysqldump不算在线热备的方案，它需要对数据加锁。
    崩溃恢复：myisam表由于系统崩溃导致数据损坏的概率比Innodb高跟很多，而且恢复速度也没有innodb快。
    特有的特性：如需要聚簇索引，那就需要选择innodb存储引擎，有的需要使用地理空间搜索，那就选择myisam 。

#####四、 MERGE

    MERGE存储引擎把一组MyISAM数据表当做一个逻辑单元来对待，让我们可以同时对他们进行查询。构成一个MERGE数据表结构的各成员MyISAM数据表必须具有完全一样的结构。每一个成员数据表的数据列必须按照同样的顺序定义同样的名字和类型，索引也必须按照同样的顺序和同样的方式定义。
    
    除了便于同时引用多个数据表而无需发出多条查询，MERGE数据表还提供了以下一些便利。
    
    MERGE数据表可以用来创建一个尺寸超过各个MyISAM数据表所允许的最大长度逻辑单元
    
    你看一把经过压缩的数据表包括到MERGE数据表里。比如说，在某一年结束之后，你应该不会再往相应的日志文件里添加记录，所以你可以用myisampack工具压缩它以节省空间，而MERGE数据表仍可以像往常那样工作
    
    Merge引擎是MyISAM引擎的一个变种。Merge表是由多个MyISAM表合并而来的虚拟表。
    如果将MySQL用于日志或者数据仓库类应用，该引擎可以发挥作用。但是引入分区功能后，该引擎已经被放弃

#####五、CSV

    CSV引擎可以将普通的CSV文件作为MySQL表来处理，但是这种表不支持索引，CSV可以在数据库运行时拷贝或者拷出文件，
    可以将Excel等电子表格中的数据存储未CSV文件，然后复制到MySQL中，就能在MySQL中打开使用。
    同样，如果将数据写入到一个CSV引擎表，其他外部程序也可以从表的数据文件中读取CSV的数据。因此CSV可以作为数据交换机制。非常好用

####13、Mysql 8 和mysql 5.7 的区别
    1.NoSql存储
        Mysql从5.7 版本提供了NoSQL的存储功能,在8.0中这部分得到一些修改,不过这个在实际中用的极少
    2.隐藏索引 隐藏索引的特性对于性能调试非常有用,在8.0 中,索引可以被隐藏和显示,当一个索引隐藏时,他不会被查询优化器所使用
        也就是说可以隐藏一个索引,然后观察对数据库的影响.如果性能下降,就说明这个索引是有效的,于是将其”恢复显示”即可;如果数据库性能看不出变化,说明这个索引是多于的,可以删掉了
        
        隐藏一个索引的语法
            ALTER TABLE t ALTER INDEX i INVISIBLE;
        恢复显示该索引的语法是：
            ALTER TABLE t ALTER INDEX i VISIBLE;
        
        当一个索引被隐藏时,我们可以从show index命令的输出汇总看出,该索引visible属性值为No
        **注意:**当索引被隐藏时,他的内容仍然是和正常索引一样实时更新的,这个特性本身是专门为了优化调试而使用的,如果你长期隐藏一个索引,那还不如干掉,因为索引的存在会影响数据的插入\更新和删除功能
    
    3.设置持久化 
        MySQL 的设置可以在运行时通过 SET GLOBAL 命令来更改，但是这种更改只会临时生效，到下次启动时数据库又会从配置文件中读取。 MySQL 8 新增了 SET PERSIST 命令，例如：
            SET PERSIST max_connections = 500;
        MySQL 会将该命令的配置保存到数据目录下的 mysqld-auto.cnf 文件中，下次启动时会读取该文件，用其中的配置来覆盖缺省的配置文件。
    4.UTF-8 编码
        从 MySQL 8 开始，数据库的缺省编码将改为 utf8mb4，这个编码包含了所有 emoji 字符。多少年来我们使用 MySQL 都要在编码方面小心翼翼，生怕忘了将缺省的 latin 改掉而出现乱码问题。从此以后就不用担心了。
    5.通用表表达式（Common Table Expressions）
        复杂的查询会使用嵌入式表，例如：
            SELECT t1.*, t2.* FROM
            (SELECT col1 FROM table1) t1,
            (SELECT col2 FROM table2) t2;
        而有了 CTE，我们可以这样写：
            WITH
                t1 AS (SELECT col1 FROM table1),
                t2 AS (SELECT col2 FROM table2)
            SELECT t1.*, t2.*
            FROM t1, t2;
        这样看上去层次和区域都更加分明，改起来也更清晰的知道要改哪一部分。 
        这个特性在很多报表场景是很有用的，也是mysql优化的一个很重要特性。


- <a href="https://juejin.cn/post/6978735719099400223" target="_blank">Mysql 8 和mysql 5.7 的区别 </a>









<p>
    <a href="#" onclick="refreshContent('database')">返回</a>
</p>

 <style>
  table{
    border-left:1px solid #000000;border-top:1px solid #000000;
    width: 100%;
    word-wrap:break-word; word-break:break-all;
  }
  table th{
  text-align:center;
  }
  table th,td{
    border-right:1px solid #000000;border-bottom:1px solid #000000;
  }
</style>