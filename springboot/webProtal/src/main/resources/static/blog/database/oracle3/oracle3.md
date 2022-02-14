<p>
    <a href="#" onclick="refreshDatabaseContent('oracle2')">返回 Oracle 2.0</a>

</p>

---

# Oracle 3.0

## 同时插入多条数据

    Mysql：
    INSERT INTO users(name, age) VALUES('ccc', 333), ('aaa', 222), ('bbb', 111);
    Oracle：
    INSERT ALL INTO tb_red VALUES(1000, 8001, '2016-10-10 10:59:59', 1, 8001, '测试用户1000', '红名单0', '男', '膜法学院', '被测')
    INTO tb_red VALUES (1001, 8001, '2016-10-10 11:00:00', 2, 8001, '测试用户1001', '红名单1', '男', '膜法学院', '被测')
    INTO tb_red VALUES (1002, 8001, '2016-10-10 11:00:01', 0, 8001, '测试用户1002', '红名单2', '男', '膜法学院', '被测')
    INTO tb_red VALUES (1003, 8001, '2016-10-11 10:59:59', 1, 8001, '测试用户1003', '红名单3', '男', '膜法学院', '被测')
    INTO tb_red VALUES (1004, 8001, '2016-10-11 11:00:00', 2, 8001, '测试用户1004', '红名单4', '男', '膜法学院', '被测')
    INTO tb_red VALUES (1005, 8001, '2016-10-11 11:00:01', 0, 8001, '测试用户1005', '红名单5', '男', '膜法学院', '被测')
    select 1 from dual;

## 连表查询

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

## 伪列

    是Oracle中的一个列但是并不会存储在表中,伪列可以从表中查询,但不能插入,更新和删除就是不能进行操作
    rowid:存的是每一行的地址； 
    rownum:存的是每一列的id,代替limit进行每页查询的编写

## distinct 去重

	DISTINCT语句的语法如下：SELECT DISTINCT clo* from 
	单列 SELECT DISTINCT clo from 
	多列 SELECT DISTINCT clo1, clo1 from

## order by 排序

	按多个列排序行示例
		ORDER BY first_name, last_name DESC; --- 按first_name进行按升序排序，并按降序对last_name列进行排序
	
	按列位置排序行示例
		SELECT name, credit_limit,address FROM customers ORDER BY 2 DESC, 1;
		相当于
		SELECT name, credit_limit,address FROM customers ORDER BY credit_limit DESC, name;	    

## 每张表最多可建立12 种类型的触发器

	BEFORE INSERT
	BEFORE INSERT FOR EACH ROW
	AFTER INSERT
	AFTER INSERT FOR EACH ROW

	BEFORE UPDATE
	BEFORE UPDATE FOR EACH ROW
	AFTER UPDATE
	AFTER UPDATE FOR EACH ROW

	BEFORE DELETE
	BEFORE DELETE FOR EACH ROW
	AFTER DELETE
	AFTER DELETE FOR EACH ROW

## Oracle 数据库中 SYS、SYSTEM、DBSNMP、SYSMAN 四用户的区别 用户：

    SYS 用户： SYS，默认密码为 CHANGE_ON_INSTALL,当创建一个数据库时，SYS 用户将被默认 创建并授予 DBA 角色，
    所有数据库数据字典中的基本表和视图都存储在名为 SYS 的方案中，这些基本表和视图对于 Oracle 数据库的操作时非常重要的。
    为了维 护数据字典的真实性，SYS 方案中的表只能由系统来维护，他们不能被任何用户 或数据库管理员修改，而且任何用户不能在 SYS 方案中创建表。 
    
    SYSTEM 用户： SYSTEM，默认密码为 MANAGER，与 SYS 一样，在创建 Oracle 数据库时，SYSTEM 用户被默认创建并被授予 DBA 角色，
    用于创建显示管理信息的表或视图，以及被 各种 Oracle 数据库应用和工具使用的内容表或视图。
    
    DBSNMP 用户： DBSNMP 是 Oracle 数据库中用于智能代理（Intelligent Agent）的用户，用来 监控和管理数据库相关性能的用户，如果停止该用户，
    则无法提取相关的数据信息； 
    
    SYSMAN 用户： SYSMAN 是 Oracle 数据库中用于 EM 管理的用户，如果你不用该用户，也可以删 除。

## sys 和 system 用户的区别

     【system】用户只能用 normal 身份登陆 em。 
     【sys】用户具有“SYSDBA”或者“SYSOPER”权限，登陆 em 也只能用这两个身份，不能用 normal。 
    
    “SYSOPER”权限，即数据库操作员权限，
        权限包括： 打开数据库服务器、 关闭数据库服务器、 备份数据库 日志归档、 恢复数据库、 会话限制
    
    “SYSDBA”权限，即数据库管理员权限，
        权限包括： 打开数据库服务器、 关闭数据库服务器、 备份数据库、 日志归档、 管理功能、 恢复数据库、 会话限制、

## normal 、sysdba、 sysoper 有什么区别：

    normal 是普通用户。  另外两个，你考察他们所具有的权限就知道了，
    
    sysdba 拥有最高的系统权限 
    
    sysoper 主要用来启动、关闭数据库，登陆后用户是
    
    public sysdba 登陆后是 sys

<p>
    <a href="#" onclick="refreshDatabaseContent('oracle2')">返回 Oracle 2.0</a>
</p>

# 练习Demo：

    https://blog.csdn.net/yelang0111/article/details/77435025
    
    declare
          num   number;
    begin
        select count(1) into num from all_all_tables where owner = UPPER('test_user') and table_name = UPPER('student'); 
        if num > 0 then
            execute immediate 'drop table test_user.student' ;
        end if;
    end;   
    
    上下不能同时执行  
           
    create table test_user.Student(
    Sno varchar2(7) primary key,
    Sname varchar2(10),
    Ssex varchar2(2),
    Sage  int,
    Dept varchar2(20) 
    ) tablespace MYUSER_SPACE;
    comment on column test_user.Student.Sname is '学号';
    comment on column test_user.Student.Sno is '姓名';
    comment on column test_user.Student.Ssex is '性别';
    comment on column test_user.Student.Sage is '年龄';
    comment on column test_user.Student.Dept is '所在系';  

    alter table test_user.Student add constraint CK_Sage check (Sage between 5 and 80);
    alter table test_user.Student add constraint CK_Ssex check (Ssex in ('男','女'));
    alter table test_user.Student modify Ssex varchar2(4);
    
        INSERT ALL INTO test_user.Student values('0811101','李勇','男',21,'计算机系')
        into  test_user.Student values('0811102','刘晨','男',20,'计算机系')
        into  test_user.Student values('0811103','王敏','女',20,'计算机系')
        into  test_user.Student values('0811104','张小红','女',19,'计算机系')   
        into  test_user.Student values('0821101','张立','男',21,'信息管理系')
        into  test_user.Student values('0821102','吴宾','女',19,'信息管理系')
        into  test_user.Student values('0821103','张海','男',20,'信息管理系')     
        into  test_user.Student values('0831101','钱小平','女',21,'通信工程系')
        into  test_user.Student values('0831102','王大力','男',21,'通信工程系')
        into  test_user.Student values('0831103','张姗姗','女',19,'通信工程系')
         select 1 from dual;
         commit ;
    
    drop table test_user.Course;
    create table test_user.Course(
    Cno varchar2(10) primary key,
    Cname  varchar2(20) not null,
    Credit int,
    Semester  int
    ) tablespace MYUSER_SPACE;
    comment on column test_user.Course.Cno is '课程号';
    comment on column test_user.Course.Cname is '课程名';
    comment on column test_user.Course.Credit is '学分';
    comment on column test_user.Course.Semester is '开课学期';  
    alter table test_user.Course add constraint CK_Credit check (Credit > 0);
    
    drop table test_user.Score;
    create table test_user.Score(
    Sno  varchar2(7),
    Cno  varchar2(10),
    Grade  int
    ) tablespace MYUSER_SPACE;
    
    comment on column test_user.Score.Sno is '学号';
    comment on column test_user.Score.Cno is '课程号';
    comment on column test_user.Score.Grade is '成绩';
    
    alter table test_user.Score add constraint FK_Sno foreign key(Sno) references test_user.Student(Sno);
    alter table test_user.Score add constraint FK_Cno foreign key(Cno) references test_user.Course(Cno);
    
    alter table test_user.Score add constraint CK_Grade check (Grade between 0 and 100);

