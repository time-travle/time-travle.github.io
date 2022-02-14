<p>
    <a href="#" onclick="refreshDatabaseContent('oracle')">返回 Oracle 1.0</a>
    <a href="#" style="float: right;" onclick="refreshDatabaseContent('oracle3')">进入问题 Oracle 3.0</a>
</p>

---

# Oracle 2.0

## 增

### 增加分区

    ---创建增加分区语句， 只增加一级分区，没有子分区
    alter table v_table_name add partition v_partition_nameYYYYMM values (YYYYMM);

### 新增字段

    ---表新增字段 格式：alter table 表名称 add 新增字段名称 新增字段类型(字段长度);
    ALTER TABLE SYS_JOB ADD CSIGN VARCHAR2(20) ;
    ---同时新增多个字段
    ALTER TABLE members ADD(
            created_at TIMESTAMP WITH TIME ZONE NOT NULL,
            updated_at TIMESTAMP WITH TIME ZONE NOT NULL);

### 表新增约束条件

    ALTER TABLE SYS_JOB ADD CONSTRAINT PK_SYS_JOB PRIMARY KEY (JOB_ID);      

### 建立索引

    create index index_name on tableName (tableName.COLUMN);
    create index index_name on tableName (tableName.COLUMN1,tableName.COLUMN2);    

### 添加数据

    INSERT INTO table_name (column1,column2...) VALUES (value1,value2,...);---按字段插入
    insert into studentinfo values(2,'李四','男',18,'1325655563','南昌',2);---全字段插入

## 删

### 删除用户

    drop user gary;

### 删除表

    ---删除表
    DROP TABLE SYS_JOB;

### 删除字段

    ---删除指定字段 格式：alter table 表名称 DROP COLUMN 字段名称;
    ALTER TABLE table_name DROP COLUMN column_name;
    ---删除多列
    ALTER TABLE table_name DROP (column_1,column_2,...);

### 删除数据

    delete from studentinfo where studentid=1;

## 改

### 修改表名

    rename t1 to tb1;

### 改列

    ---修改指定字段长度 格式：alter table 表名称 MODIFY 字段名称 字段类型(字段长度);
    ALTER TABLE SYS_JOB MODIFY  JOB_NAME VARCHAR2(3000);
    ---修改列：     
    alter  table A  rename   column   CFYJSNR   to   CFJYSNR;

### 追加新字段

    --- 一起执行，同时添加三个字段
    alter table PROJ_BID_EVAL 
    add procurement_office_opinion varchar2(2000) 
    add procurement_decision varchar2(20) 
    add procurement_decision_desc varchar2(200) 

### 添加注释

    --- 逐条执行，添加字段的注释 
    comment on column PROJ_BID_EVAL.Procurement_Office_Opinion is '采购办仲裁意见'
    comment on column PROJ_BID_EVAL.Procurement_Decision is '采购办决策(字典编码)'
    comment on column PROJ_BID_EVAL.Procurement_Decision_Desc is '采购办决策(中文)'

### 改数据 update  set

    update  studentinfo set  studentsex='女' where studentid=1;

## 查

    ---查看表结构
    1.命令窗口：desc 表名
    2.sql窗口：select * from user_tab_columns where table_name=‘大写表名’；

### 别名使用

    在oracle中，数据表别名不能加as，如：
    select a.appname from appinfo a;-- 正确
    select a.appname from appinfo as a;-- 错误

### 并行查

    https://www.cnblogs.com/xingmeng/p/3303761.html
    
    select /*+ parallel(t1 8) */ count(*) from t1;

### 回滚查

    Oracle表数据被更新，需要恢复到一个小时之前
    
    处理：select * from 表名 as of TIMESTAMP>sysdate-1/24;
    
    insert into 表名（
        select * from 表名 as of TIMESTAMP>sysdate-1/24;
    ）

    -- 查询的是现在的某条记录 与 3分钟之前这条记录的数据
    select * from sysuser where id ='fa5224f9024d431c97ce653910ab9289'
    union all
    select * from sysuser as of timestamp (systimestamp - interval '3' minute) 
    where id = 'fa5224f9024d431c97ce653910ab9289' 

### 分组查

### 转置查

### 连表查

#### 左连接

#### 右连接

#### 全连接

#### 内连接

#### 外连接

<p>
    <a href="#" onclick="refreshDatabaseContent('oracle')">返回 Oracle 1.0</a>
    <a href="#" style="float: right;" onclick="refreshDatabaseContent('oracle3')">进入问题 Oracle 3.0</a>
</p>
