<p>
    <a href="#" onclick="refreshContent('database')">返回</a>
    <a href="#" style="float: right;" onclick="refreshDatabaseContent('mysql2')">进阶 mysql 2.0</a>
</p>

---

# MySQL 1.0

1、创建数据库

	CREATE DATABASE 数据库名;

2、删除数据库

	drop database <数据库名>;

3、安全建表

    DROP TABLE IF EXISTS sys_area;
     
    CREATE TABLE sys_area (
        id int NOT NULL AUTO_INCREMENT COMMENT '编号',
        parent_id varchar(64) NOT NULL COMMENT '父级编号',
        parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
        name varchar(100) NOT NULL COMMENT '名称',
        sort decimal(10,0) NOT NULL COMMENT '排序',
        code varchar(100) COMMENT '区域编码',
        type char(1) COMMENT '区域类型',
        create_by varchar(64) NOT NULL COMMENT '创建者',
        create_date datetime NOT NULL COMMENT '创建时间',
        update_by varchar(64) NOT NULL COMMENT '更新者',
        update_date datetime NOT NULL COMMENT '更新时间',
        remarks varchar(255) COMMENT '备注信息',
        del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
        PRIMARY KEY (id)
    ) COMMENT = '区域表';

<p>
    <a href="#" onclick="refreshContent('database')">返回</a>
    <a href="#" style="float: right;" onclick="refreshDatabaseContent('mysql2')">进阶 mysql 2.0</a>
</p>