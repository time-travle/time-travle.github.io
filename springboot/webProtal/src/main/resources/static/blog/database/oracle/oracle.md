<p>
    <a href="#" onclick="refreshContent('database')">返回</a>
    <a href="#" style="float: right;" onclick="refreshDatabaseContent('oracle2')">进阶 Oracle 2.0</a>
</p>

---

# Oracle 1.0

## 创建表空间

    create tablespace MYUSER_SPACE 
        datafile 'E:\APP\ADMINISTRATOR\ORADATA\ORCL\MYUSER_SPACE_S01.DBF' 
        size 1024m 
        autoextend on next 2000M   
        maxsize unlimited  
        extent management local autoallocate   
        segment space management auto ;
    
    Oracle查询用户表空间：
        select * from user_all_tables;
        select * from user_tablespaces;
        
    --查看数据库里面所有用户，前提是你是有dba权限的帐号，如sys,system
    select username from dba_users
    --查看表所属的变空间
    select table_name 表名 ,tablespace_name 所使用表空间 from user_tables;
    --给已存在的用户指定一个表空间
    alter user username default tablespace userspace;
    --创建用户的时候指定表空间
    create user username identified by userpassword default tablespace userspace;
    --查看当前用户所在表空间
    select username,default_tablespace from user_users;

## 建用户

 	-- Create the user  新建用户
 	create user READ_USER
 	  identified by "read_user"
 	  default tablespace TIMETRAVEL_USER
 	  temporary tablespace TIMETRAVEL_TEMP
 	  profile PASSWD_UNLIMIT;
 	  
 	-- Grant/Revoke role privileges  受/收 角色权限
 	grant base_role to READ_USER;
 	grant resource to READ_USER;--> 赋资源角色
 	
 	-- Grant/Revoke system privileges   受/收 系统权限
 	grant create database link to READ_USER ; --只有 READ_USER 用户能使用的dblink
    grant create public database link to READ_USER ;--所有用户都可以使用的dblink
    grant drop public database link to READ_USER; --删除dblink的权限
 	grant create sequence to READ_USER;--->给予创建序列权限
 	grant create session to READ_USER;--->给予连接权限
 	grant create synonym to READ_USER;--->创建同义词的权限.
 	grant create table to READ_USER;--->创建表权限
 	grant create view to READ_USER;--->创建视图权限
    
 	查看当前用户： 
 	select * from v$session;     		--->查看所有连接信息
 	select * from V$PWFILE_USERS;　　	--->查看当前用户有sysdba或sysoper系统权限(查询时需要相应权限)
 	select * from session_privs;　　	    --->当前用户所拥有的全部权限 
 	select * from session_roles;   		--->当前用户被激活的全部角色
 
 	select * from user_role_privs;		--->查看当前用户的角色
 	select * from user_sys_privs;		--->查看当前用户的系统权限
 	select * from user_tab_privs;		--->查看当前用户的表级权限
 
 	select * from user_users;			--->查看当前用户的详细信息
 	select * from user_tables;			--->查看当前用户的表
 	select * from all_tab_privs; 　　	--->查看当前用户的所拥有对象
 	用户：
 	select * from dba_users;  			--->显示所有用户的详细信息
 	select * from all_users;  			--->显示用户及用户id和创建用户时间三个字段
 
    Oracle查询所有用户：
        select * from all_users；
        select * from dba_users；

### 创建表空间和临时表空间

    create tablespace TIMETRAVEL_USER datafile 'D:\APP\ADMINISTRATOR\ORADATA\ORCL\TIMETRAVEL_USER.DBF'
    size 200m
    autoextend on
    next 32m maxsize 2048m;
    
    create temporary tablespace TIMETRAVEL_TEMP tempfile 'D:\APP\ADMINISTRATOR\ORADATA\ORCL\TIMETRAVEL_TEMP.DBF' size 50M autoextend ON next 10M maxsize 100M;

### oracle怎么查看用户属于哪个表空间 用如下语句查看：

    select username,default_tablespace from dba_users  where username='用户名';

### 查询概要文件信息： select * from SYS.DBA_PROFILES;

    select * from SYS.DBA_PROFILES where profile='DEFAULT';

### 角色：

    -- 查询所有角色, connect/resource/dba比较常见
    select * from dba_roles
    ---查询所有角色 权限
    select * from dba_role_privs;
    -- 角色的系统权限和对象权限
    select * from role_sys_privileges;
    select * from role_tab_privileges;
    ---删除角色
    sql>drop role role1; 
    
    Connect 角色 是授予最终用户的典型权利，最基本的权利，能够连接到Oracle数据库中，并在对其他用户的表有访问权限时，做SELECT、UPDATE、INSERTT等操作。
        Alter session--修改会话；
        Create cluster--建立聚簇；
        Create database link--建立数据库连接；
        Create sequence--建立序列；
        Create session--建立会话；
        Create synonym--建立同义词；
        Create view--建立视图。 
    
    Resoure角色 是授予开发人员的，能在自己的方案中创建表、序列、视图等。
        Create cluster--建立聚簇；
        Create procedure--建立过程；
        Create sequence—建立序列；
        Create table--建表；
        Create trigger--建立促发器；
        Create type--建立类型。
    
    DBA角色
        是授予系统管理员的，拥有该角色的用户就能成为系统管理员了，它拥有所有的系统权限。

## 赋予权限

 	查看权限：
 	select * from dba_sys_privs;		--->查询某个用户所拥有的系统权限
 	select * from dba_tab_privs;        --->查看所有用户的对象权限
 
 	select * from table_privilege_map;?	--->查看所有对象权限?
 	select * from system_privilege_map;	--->查看所有系统权限
 		
 	select * from all_sys_privs;		--->查看系统权限
 	select * from V$PWFILE_USERS;		--->查看哪些用户有sysdba或sysoper系统权限(查询时需要相应权限)
 
 	角色：
 	select * from dba_roles;			--->查看所有角色
 	select * from dba_role_privs;		--->全部用户被授予的角色
 	Select * from ROLE_ROLE_PRIVS;		--->查看所有角色被赋予的角色
 	select * from role_tab_privs;?		--->查看角色拥有的对象权限
 	select * from role_sys_privs;		--->查看角色(只能查看登陆用户拥有的角色)所包含的权限
 
    Oracle查看当前用户权限：
        select * from session_privs
    
    --把所有表的查询权限赋给另一个用户
    grant select any table to 'QUERY';

### oracle的系统和对象权限列表

    alter any cluster 修改任意簇的权限
    alter any index 修改任意索引的权限
    alter any role 修改任意角色的权限
    alter any sequence 修改任意序列的权限
    alter any snapshot 修改任意快照的权限
    alter any table 修改任意表的权限
    alter any trigger 修改任意触发器的权限
    alter cluster 修改拥有簇的权限
    alter database 修改数据库的权限
    alter procedure 修改拥有的存储过程权限
    alter profile 修改资源限制简表的权限
    alter resource cost 设置佳话资源开销的权限
    alter rollback segment 修改回滚段的权限
    alter sequence 修改拥有的序列权限
    alter session 修改数据库会话的权限
    alter sytem 修改数据库服务器设置的权限
    alter table 修改拥有的表权限
    alter tablespace 修改表空间的权限
    alter user 修改用户的权限
    analyze 使用analyze命令分析数据库中任意的表、索引和簇
    audit any 为任意的数据库对象设置审计选项
    audit system 允许系统操作审计
    backup any table 备份任意表的权限
    become user 切换用户状态的权限
    commit any table 提交表的权限
    create any cluster 为任意用户创建簇的权限
    create any index 为任意用户创建索引的权限
    create any procedure 为任意用户创建存储过程的权限
    create any sequence 为任意用户创建序列的权限
    create any snapshot 为任意用户创建快照的权限
    create any synonym 为任意用户创建同义名的权限
    create any table 为任意用户创建表的权限
    create any trigger 为任意用户创建触发器的权限
    create any view 为任意用户创建视图的权限
    create cluster 为用户创建簇的权限
    create database link 为用户创建的权限
    create procedure 为用户创建存储过程的权限
    create profile 创建资源限制简表的权限
    create public database link 创建公共数据库链路的权限
    create public synonym 创建公共同义名的权限
    create role 创建角色的权限
    create rollback segment 创建回滚段的权限
    create session 创建会话的权限
    create sequence 为用户创建序列的权限
    create snapshot 为用户创建快照的权限
    create synonym 为用户创建同义名的权限
    create table 为用户创建表的权限
    create tablespace 创建表空间的权限
    create user 创建用户的权限
    create view 为用户创建视图的权限
    delete any table 删除任意表行的权限
    delete any view 删除任意视图行的权限
    delete snapshot 删除快照中行的权限
    delete table 为用户删除表行的权限
    delete view 为用户删除视图行的权限
    drop any cluster 删除任意簇的权限
    drop any index 删除任意索引的权限
    drop any procedure 删除任意存储过程的权限
    drop any role 删除任意角色的权限
    drop any sequence 删除任意序列的权限
    drop any snapshot 删除任意快照的权限
    drop any synonym 删除任意同义名的权限
    drop any table 删除任意表的权限
    drop any trigger 删除任意触发器的权限
    drop any view 删除任意视图的权限
    drop profile 删除资源限制简表的权限
    drop public cluster 删除公共簇的权限
    drop public database link 删除公共数据链路的权限
    drop public synonym 删除公共同义名的权限
    drop rollback segment 删除回滚段的权限
    drop tablespace 删除表空间的权限
    drop user 删除用户的权限
    execute any procedure 执行任意存储过程的权限
    execute function 执行存储函数的权限
    execute package 执行存储包的权限
    execute procedure 执行用户存储过程的权限
    force any transaction 管理未提交的任意事务的输出权限
    force transaction 管理未提交的用户事务的输出权限
    grant any privilege 授予任意系统特权的权限
    grant any role 授予任意角色的权限
    index table 给表加索引的权限
    insert any table 向任意表中插入行的权限
    insert snapshot 向快照中插入行的权限
    insert table 向用户表中插入行的权限
    insert view 向用户视图中插行的权限
    lock any table 给任意表加锁的权限
    manager tablespace 管理（备份可用性）表空间的权限
    references table 参考表的权限
    restricted session 创建有限制的数据库会话的权限
    select any sequence 使用任意序列的权限
    select any table 使用任意表的权限
    select snapshot 使用快照的权限
    select sequence 使用用户序列的权限
    select table 使用用户表的权限
    select view 使用视图的权限
    unlimited tablespace 对表空间大小不加限制的权限
    update any table 修改任意表中行的权限
    update snapshot 修改快照中行的权限
    update table 修改用户表中的行的权限
    update view 修改视图中行的权限

## 建表

    --例：
    创建表名为table1，列名为column1，column2，…，数据类型为特定数据类型的表
    Create table table1（
        Column1 datetype，
        Column2 datetype,
        ……
    ）；
    -- Add comments to the table 
        comment on table table1 is 'comments';
    -- Add comments to the columns 
        comment on column table1.Column1 is 'comments';
    --添加字段：在已经建好的表table1中添加字段columnX，字符类型为number
    Alter table table1 add columnX number；
    --修改字段数据类型：修改columnX的数据类型为date
    Alter table table1 Modify columnX date；
    --修改字段名：修改columnX的名称为columnY
    Alter table table1 Rename column columnX to columnY；
    --删除字段：删除字段columnY
    Alter table table1 Drop column columnY
    --修改表名：修改表table1的名称为table2
    Rename table1 to table2；
    --删除表：删除表table2：
    Drop table table2    
    
    --当前用户下是否有某个表
        select count(*) from user_tables where table_name = 'TABLE_NAME';
    --某个用户下是否有某个表？
        select count(*) from dba_tables where owner = 'USER_NAME' and table_name = 'TABLE_NAME';
    ---数据库是否存在某字段
        SELECT COUNT(*) FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '表名' AND COLUMN_NAME = '字段名';
    ---数据库是否存在某张表
        select count(*) from user_objects where  object_name = '表名';
        注：表名和字段名一定要大写
        
    判断表是否存在字段    
        方法一：可以用user_tab_cols表进行查询，查询有结果表示字段存在：
        sql：select * from user_tab_cols where table_name='T_AAA' and column_name='COL_BBB';
        方法二：也可以用all_tab_columns表进行查询，查询有结果表示字段存在：
        sql：select * from all_tab_columns where owner='SYS_CCC' and table_name='T_AAA' and column_name='COL_BBB';
        备注：所有的查询字段必须是大写，否则查询会有误差

### 安全建表 demo

    declare
          num   number;
    begin
        select count(1) into num from user_tables where table_name = upper('sys_area') ;
        if num > 0 then
            execute immediate 'drop table sys_area' ;
        end if;
        ---删除之后再创建该表
        execute immediate '
            -- 区域表
            CREATE TABLE sys_area (
                id Number(4) NOT NULL,
                parent_id varchar2(64) NOT NULL,
                parent_ids varchar2(2000) NOT NULL,
                name nvarchar2(100) NOT NULL,
                sort number(10,0) NOT NULL,
                code varchar2(100),
                type char(1),
                create_by varchar2(64) NOT NULL,
                create_date timestamp NOT NULL,
                update_by varchar2(64) NOT NULL,
                update_date timestamp NOT NULL,
                remarks nvarchar2(255),
                del_flag char(1) DEFAULT '0' NOT NULL,
                PRIMARY KEY (id)
            )';
    end;

## 索引

    创建索引
        // 组合索引
        create index 索引名 on 表名(列名a, 列名b); 
        CREATE INDEX 索引名 ON 表名 (列名) TABLESPACE 表空间名;
        
        创建索引语法
        
        CREATE [UNIQUE] | [BITMAP] INDEX index_name  --unique表示唯一索引
        ON table_name([column1 [ASC|DESC],column2    --bitmap，创建位图索引
        [ASC|DESC],…] | [express])                   --缺省为ASC即升序排序
        [TABLESPACE tablespace_name]                 --表空间名
        [CLUSTER [scheam.]cluster]                   --指定一个聚簇（Hash cluster不能建索引）
        [PCTFREE n1]                                 --指定索引在数据块中空闲空间
        [STORAGE (INITIAL n2)]                       --存储参数，同create table 中的storage.
        [NOLOGGING]                                  --表示创建和重建索引时允许对表做DML操作，默认情况下不应该使用
        [NOLINE]
        [NOSORT];                                    --表示创建索引时不进行排序，默认不适用，如果数据已经是按照该索引顺序排列的可以使用
    
    重命名索引
        alter index index_sno rename to bitmap_index;   
    
    删除索引
        drop index 索引名;
        
    建立索引的目的：
        建立索引的目的是：
        l 提高对表的查询速度；
        l 对表有关列的取值进行检查。
    
        但是，对表进行insert,update,delete处理时，由于要表的存放位置记录到索引项中而会降低一些速度。
        注意：一个基表不能建太多的索引；
              空值不能被索引
              只有唯一索引才真正提高速度,一般的索引只能提高30%左右。
              
    合并索引
        （表使用一段时间后在索引中会产生碎片，此时索引效率会降低，可以选择重建索引或者合并索引,合并索引方式更好些，
        无需额外存储空间，代价较低）
        alter index index_sno coalesce;
    
    修改索引
            ALTER [UNIQUE] INDEX [user.]index
            [INITRANS n]
            [MAXTRANS n] 
            REBUILD 
            [STORAGE n]
            
            REBUILD 是 根据原来的索引结构重新建立索引，实际是删除原来的索引后再重新建立。
             
            提示：DBA经常用 REBUILD 来重建索引可以减少硬盘碎片和提高应用系统的性能。
    
    
    重建索引
    　　方式一：删除原来的索引，重新建立索引
    　　方式二：alter index index_sno rebuild;
    
    查看索引
        --查看该表的所有索引
        select * from all_indexes where table_name = 'tablename';
         
        --查看该表的所有索引列
        select* from all_ind_columns where table_name = 'tablename';

	获得单个表和索引DDL语句的方法
	　　set heading off;　　 
	　　set echo off;　　 
	　　Set pages 999;　　 
	　　set long 90000; 

	　　spool get_single.sql　　 
	　　select dbms_metadata.get_ddl('TABLE','SZT_PQSO2','SHQSYS') from dual;　　 
	　　select dbms_metadata.get_ddl('INDEX','INDXX_PQZJYW','SHQSYS') from dual;　 
	　　spool off;

- <a href="https://www.cnblogs.com/djcsch2001/articles/1823459.html#" target="_blank">oracle索引，索引的建立、修改、删除 </a>
- <a href="https://blog.csdn.net/qq_40285302/article/details/81874641#" target="_blank">Oracle 建立索引及SQL优化  </a>

## 约束

    唯一主键
    联合主键
    外键
    插入校验

    1、添加主键约束（将stuNo作为主键）
    alter table stuInfo add constraint PK_stuNo primary key (stuNo)

    2、添加外键约束 (主表stuInfo和从表stuMarks建立关系，关联字段stuNo)
    alter table stuInfo add constraint FK_stuNo foreign key(stuNo) references stuinfo(stuNo)

    3、添加唯一约束（身份证号唯一）
    alter table stuInfo add constraint UQ_stuID unique(stuID)

    4、添加默认约束（如果地址不填 默认为“地址不详”）
    alter table stuInfo add constraint DF_stuAddress default (‘地址不详’) for stuAddress

    5、添加检查约束 （对年龄加以限定 15-40岁之间）
    alter table stuInfo add constraint CK_stuAge check (stuAge between 15 and 40)

    6、添加表注释：学生信息表
    comment on table STUINFO is '学生信息表';

    7、添加列名称：学号
    comment on column STUINFO.stuid is '学号';
    comment on column STUINFO.stuname is '学生姓名';
    
    约束: 
        主键约束: ssid number primary key; 
        非空约束: sname varchar2(20) not null 
        检查约束(是否男女): ssex char(5) check(ssex,('男','女'))
        年龄区间约束: age number check(age>=0 and age<=100);
        添加外键约束: foreign key(ssid) references java0611(ssid);

## 视图

	视图是一个对应的虚拟的表，并不是真实的存在与数据库中
	CREATE [OR REPLACE]  VIEW view_name
	AS
	SELECT查询
	[WITH READ ONLY CONSTRAINT]
	解释：
		1、OR REPLACE：如果视图已经存在，则替换旧视图。
		2、WITH READ ONLY：默认不填的，用户是可以通过视图对基表执行增删改操作，但是有很多在基表上的限制
		（比如：基表中某列不能为空，但是该列没有出现在视图中，则不能通过视图执行 insert 操作，或者基表设置了某些约束，
		这时候插入视图或者修改视图的值，有可能会报错）， 
		WITH READ ONLY 说明视图是只读视图，不能通过该视图进行增删改操作。但是在现实开发中，基本上不通过视图对表中的数据进行增删改操作
	
	使用的时候可以直接当作表来进行处理

## 序列

	CREATE SEQUENCE sequence_name
        [MAXVALUE num|NOMAXVALUE]
        [MINVALUE num|NOMINVALUE]
        [START WITH num]
        [INCREMENT BY increment]
        [CYCLE|NOCYCLE]
        [CACHE num|NOCACHE]

	语法解析：
		1、MAXVALUE/MINVALUE：指定的是序列的最大值和最小值。
		2、NOMAXVALUE/NOMINVALUE：不指定序列的最大值和最小值，使用系统的默认选项，升序的最大值：10^27次方，降序是-1。
			升序最小值：1，降序的最小值：-10^26。
		3、START WITH：指定从某一个整数开始，升序默认是1，降序默认是-1。
		4、CYCLE | NOCYCLE:表示序列达到最大值或者最小值的时候，是否重新开始。CYCLE：重新开始，NOCYCLE：不重新开始。
		5、CACHE：使用 CACHE 选项时，该序列会根据序列规则预生成一组序列号。保留在内存中，当使用下一个序列号时，可以更快的响应。
			当内存中的序列号用完时，系统再生成一组新的序列号，并保存在缓存中，这样可以提高生成序列号的效率 。
		6、NOCACHE：不预先在内存中生成序列号。
	
	CREATE SEQUENCE seqTest
        INCREMENT BY 1 -- 每次加几个
        START WITH 1 -- 从1开始计数
        NOMAXvalue -- 不设置最大值
        minvalue 1  --最小值
        NOCYCLE -- 一直累加，不循环
        CACHE NOCACHE --设置缓存cache个序列，如果系统down掉了或者其它情况将会导致序列不连续，也可以设置为---------NOCACHE
    
    --查看当前用户的所有序列
    select SEQUENCE_OWNER,SEQUENCE_NAME from dba_sequences where sequence_owner='用户名';
    --查询当前用户的序列总数
    select count(*) from dba_sequences where sequence_owner='用户名';        
	--查询oracle所有已创建序列命令
    select *from user_sequences；
	
        1.必须以管理员身份登录；    
        2.sequence_owner必须为大写，不管你的用户名是否大写。只有大写才能识别
        

	使用
		select seqName.nextval from dual--下一个值
		select seqName.currval from dual--当前的值

## 定时任务

    写定时任务 dbms_job.submit(...)
    执行定时任务 dbms_job.run(job num)

	创建job
		declare job job_number;
		begin
			dbms_job.submit(
				 job =>job, /*自动生成JOB_ID*/
				 what=>'PROC1;PROC2;',--存储过程名字 可以写多个也可以写单个
				 next_date => sysdate,--下一次执行的时间  /*初次执行时间-立即执行*/  
				 interval =>'TRUNC(SYSDATE + 1)');--每天凌晨同步一次
			commit;
		end;
	查看job
		SELECT * FROM DBA_JOBS;
		select * from all_jobs;
		SELECT * FROM USER_JOBS;
	正在运行的定时任务	
		select * from dba_jobs_running;

	根据查看到的job id进行删除
		begin
		  dbms_job.remove(job_number);  /*删除自动执行的job,参数是 job的id*/
		  commit;
		end;
	手动执行定时任务	
		BEGIN  
			DBMS_JOB.RUN(643); --643为任务的ID  
		END;

	SYSDATE+n’,n泛指一个以天为单位的时间间隔.eg:
		描述                   Interval参数值
		每天运行一次            ‘SYSDATE+1′
		每小时运行一次        ‘SYSDATE+1/24′
		10分钟运行一次      ‘SYSDATE+10/(60*24)’
		每30秒运行一次    ‘SYSDATE+30/(60*24*60)’
		每星期运行一次         ‘SYSDATE+7′
	定时到特定日期或时间的任务,eg:
		描述									Lnterval参数值
		每天午夜12点						‘TRUNC(SYSDATE+1)’
		每天早上8点30分					‘TRUNC(SYSDATE+1)+(8*60+30)/(24*60)’
		每星期二中午12点			‘NEXT_DAY(TRUNC(SYSDATE),”TUESDAY”)+12/24′
		每个月第一天的午夜12点			‘TRUNC(LAST_DAY(SYSDATE)+1)’
		每个季度最后一天的晚上11点	‘TRUNC(ADD_MONTHS(SYSDATE+2/24,3),’Q')-1/24′
		每星期六,日早上6点10分		‘TRUNC(LEAST(NEXT_DAY(SYSDATE,”SATURDAY”),NEXT_DAY(SYSDATE,”SUNDAY”)))+(6*60+10)/(24*60)’

    删除job:				dbms_job.remove(jobno);
    修改要执行的操作:job:	dbms_job.what(jobno,what);
    修改下次执行时间：		dbms_job.next_date(job,next_date);
    修改间隔时间：			dbms_job.interval(job,interval);
    停止job:				dbms.broken(job,broken,nextdate);
    启动job:				dbms_job.run(jobno);

	子过程：  
		Broken()过程。 
		change()过程。 
		Interval()过程。 
		Isubmit()过程。 
		Next_Date()过程。 
		Remove()过程。 
		Run()过程。 
		Submit()过程。 
		User_Export()过程。 
		What()过程。

## 存储过程

	写ORACLE存储过程 create or replace procedure ...
	基本结构
		CREATE OR REPLACE PROCEDURE 存储过程名字 
		( 
		   参数1 IN NUMBER, 
		   参数2 IN NUMBER 
		) AS / IS   /*IS关键词表明后面将跟随一个PL/SQL体*/
		变量1 INTEGER :=0; 
		变量2 DATE; 
		BEGIN  /*表明PL/SQL体的开始*/
			null /*表明什么事都不做，这句不能删去，因为PL/SQL体中至少需要有一句;*/
		END 存储过程名字  /*表明PL/SQL体的结束*/

    SELECT INTO STATEMENT  用法
          将select查询的结果存入到变量中，可以同时将多个列存储多个变量中，必须有一条
          记录，否则抛出异常(如果没有记录抛出NO_DATA_FOUND)
          例子： 
          BEGIN
          SELECT col1,col2 into 变量1,变量2 FROM typestruct where xxx;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
              xxxx;
          END;	
    while 循环 用法
        WHILE V_TEST=1 LOOP 
         BEGIN 
        XXXX 
         END; 
         END LOOP; 
    IF 判断 用法
        IF V_TEST=1 THEN 
            BEGIN  
                do something 
            END; 
        END IF; 
    变量赋值
        V_TEST := 123; 
    用for in 使用cursor (游标) 用法
        create or replace procedure test() as
            Cursor cursor is select name from student; name varchar(20);
        begin
            for name in cursor LOOP
                begin
                    dbms_output.putline(name);
                end;
            end LOOP;
        end test; 
    带参数的cursor(游标) 用法
        CURSOR C_USER(C_ID NUMBER) IS SELECT NAME FROM USER WHERE TYPEID=C_ID; 
         OPEN C_USER(变量值); 
         LOOP 
        FETCH C_USER INTO V_NAME; 
        EXIT FETCH C_USER%NOTFOUND; 
           do something 
         END LOOP; 
         CLOSE C_USER;

### 已有的和定时任务有关的过程 job相关的存储过程

    1、Broken()过程更新一个已提交的工作的状态，典型地是用来把一个已破工作标记为未破工作。 
        这个过程有三个参数：job 、broken与next_date。 
         
        PROCEDURE Broken (job       IN binary_integer, 
                          Broken    IN boolean, 
                          next_date IN date :=SYSDATE) 
         
        job参数是工作号，它在问题中唯一标识工作。 
        broken参数指示此工作是否将标记为破——TRUE说明此工作将标记为破，而FLASE说明此工作将标记为未破。 
        next_date参数指示在什么时候此工作将再次运行。此参数缺省值为当前日期和时间。 
     
    2、Change()过程用来改变指定工作的设置。 
        这个过程有四个参数：job、what 、next_date与interval。 
         
        PROCEDURE Change (job        IN binary_integer, 
                          What       IN varchar2, 
                          next_date  IN date, 
                          interval   IN varchar2) 
         
        此job参数是一个整数值，它唯一标识此工作。 
        What参数是由此工作运行的一块PL/SQL代码块。 
        next_date参数指示何时此工作将被执行。 
        interval参数指示一个工作重执行的频度。 
     
    3、Interval()过程用来显式地设置重执行一个工作之间的时间间隔数。 
        这个过程有两个参数：job与interval。 
         
        PROCEDURE Interval (job      IN binary_integer, 
                            Interval IN varchar2) 
         
        job参数标识一个特定的工作。interval参数指示一个工作重执行的频度。 
         
    4、ISubmit()过程用来用特定的工作号提交一个工作。 
        这个过程有五个参数：job、what、next_date、interval与no_parse。 
         
        PROCEDURE ISubmit (job       IN binary_ineger, 
                           What      IN varchar2, 
                           next_date IN date, 
                           interval  IN varchar2, 
                           no_parse  IN booean:=FALSE) 
         
        这个过程与Submit()过程的唯一区别在于此job参数作为IN型参数传递且包括一个 
        由开发者提供的工作号。如果提供的工作号已被使用，将产生一个错误。 
         
    5、Next_Date()过程用来显式地设定一个工作的执行时间。这个过程接收两个参数：job与next_date。 
     
        PROCEDURE Next_Date(job         IN binary_ineger, 
                            next_date   IN date) 
         
        job标识一个已存在的工作。next_date参数指示了此工作应被执行的日期与时间。 
     
    6、Remove()过程来删除一个已计划运行的工作。这个过程接收一个参数： 
     
        PROCEDURE Remove(job IN  binary_ineger); 
         
        job参数唯一地标识一个工作。这个参数的值是由为此工作调用Submit()过程返回的job参数的值。 
        已正在运行的工作不能由调用过程序删除。 
     
    7、Run()过程用来立即执行一个指定的工作。这个过程只接收一个参数： 
     
        PROCEDURE Run(job IN binary_ineger)  
         
        job参数标识将被立即执行的工作。 
     
    8、使用Submit()过程，工作被正常地计划好。 
        这个过程有五个参数：job、what、next_date、interval与no_parse。 
         
        PROCEDURE Submit ( job       OUT binary_ineger, 
                           What      IN  varchar2, 
                           next_date IN  date, 
                           interval  IN  varchar2, 
                           no_parse  IN  booean:=FALSE) 
         
        job参数是由Submit()过程返回的binary_ineger。这个值用来唯一标识一个工作。 
        what参数是将被执行的PL/SQL代码块。 
        next_date参数指识何时将运行这个工作。 
        interval参数何时这个工作将被重执行。 
        no_parse参数指示此工作在提交时或执行时是否应进行语法分析——TRUE 
        指示此PL/SQL代码在它第一次执行时应进行语法分析， 
        而FALSE指示本PL/SQL代码应立即进行语法分析。 
     
    9、User_Export()过程返回一个命令，此命令用来安排一个存在的工作以便此工作能重新提交。 
        此程序有两个参数：job与my_call。 
         
        PROCEDURE User_Export(job       IN binary_ineger, 
                              my_call   IN OUT varchar2) 
         
        job参数标识一个安排了的工作。my_call参数包含在它的当前状态重新提交此工作所需要的正文。 
     
    10、What()过程应许在工作执行时重新设置此正在运行的命令。这个过程接收两个参数：job与what。 
     
        PROCEDURE What (job  IN binary_ineger,
                        What IN OUT varchar2) 
         
        job参数标识一个存在的工作。what参数指示将被执行的新的PL/SQL代码。

## 触发器

	触发器是特定事件出现的时候，自动执行的代码块。类似于存储过程，
	触发器与存储过程的区别在于: 存储过程是由用户或应用程序显式调用的,而触发器是不能被直接调用的。
	
	触发器的语法：
		CREATE [OR REPLACE] TIGGER 触发器名 触发时间 触发事件
		ON 表名
		[FOR EACH ROW]
		BEGIN
			pl/sql 语句
		END；
		/
		
		触发器名：触发器对象的名称。
			由于触发器是数据库自动执行的，因此该名称只是一个名称，没有实质的用途。
		触发时间：指明触发器何时执行，该值可取：
			before---表示在数据库动作之前触发器执行；
			after---表示在数据库动作之后出发器执行。
		触发事件：指明哪些数据库动作会触发此触发器：
			insert：数据库插入会触发此触发器；
			update：数据库修改会触发此触发器；
			delete：数据库删除会触发此触发器。
		表 名：数据库触发器所在的表。
			for each row：对表的每一行触发器执行一次。如果没有这一选项，则只对整个表执行一次。
	
	触发器类型：
        1、 语句触发器
        2、 行触发器
        3、 INSTEAD OF 触发
        4、 系统条件触发器
        5、 用户事件触发器	
		
	触发器禁止和启用： 
        ALTER TRIGGER trigger_name DISABLE;
        ALTER TRIGGER trigger_name ENABLE;
		
	demo:	
	*表级触发器
		create or replace trigger Tristudinfo
		after insert or update or delete
		on studinfo
		begin
			dbms_output.put_line("在Studinfo表上执行了DML语句操作");
		end;
	*行级触发器
		create or replace trigger Tristudinfo
		after insert or update or delete
		on studinfo
		for each row
		begin
			dbms_output.put_line("在Studinfo表上执行了DML语句操作");
		end;

## 其他

    select * from v$database;			--->查看数据库名
    select * from user_source			--->查询所有函数和储存过程
    select * from v$instance; 			--->查看sid
    desc tableName						--->查询表结构

<p>
    <a href="#" onclick="refreshContent('database')">返回</a>
    <a href="#" style="float: right;" onclick="refreshDatabaseContent('oracle2')">进阶 Oracle 2.0</a>
</p>