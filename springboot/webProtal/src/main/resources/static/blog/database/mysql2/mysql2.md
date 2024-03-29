<p>
    <a href="#" onclick="refreshDatabaseContent('mysql')">返回 mysql 1.0</a>
    <a href="#" style="float: right;" onclick="refreshDatabaseContent('mysql3')">进入问题 mysql 3.0</a>
</p>

---

# MySQL 2.0

<p>
    <a href="#" onclick="refreshDatabaseContent('mysql')">返回 mysql 1.0</a>
    <a href="#" style="float: right;" onclick="refreshDatabaseContent('mysql3')">进入问题 mysql 3.0</a>
</p>

### 常见的关系型数据库

    MySQL，Oracle 等其他的一些数据库
    MySQL是一种中小型的数据库，对于一些中小型企业和个人学习来说是已经足够。
    对于一些小的企业偏向使用MySQL数据库。
    下载网址：www.mysql.com 下载的时候需要进行必要的注册，注册完成后选择自己需要的下载的安装文件。

## 安装SQL的细节

### MySQL的安装有不同的安装类型：

    Typical（典型安装）Complete（完全安装）Custom（用户自定义安装）

### 两种配置方式：

    Detail Configuration（详细配置）Standard Configuration（标准配置）

### 服务器模式：

    Developer Machine（开发模式、占用很少的内存）
    Server Machine（服务器模式、会占用较多的内存）
    Dedicated MySQL Server Machine（转有服务器模式、会占有所有可以使用的内存）

### 服务器用途：

    Multifunctional Database（多功能型数据库、通用数据库一般在开发过程中使用）
    Transactional Database Only（事物处理型数据库、适用于经常更新而少量的查询的操作）
    Non-Transactional Database Only（非事物处理型数据库、适用于经常查询而不做更新的情况）

### 服务器并发连接数目：

    Decision Support(DSS)/OLAP（使用于不需要大量连接的数据库应用、最大连接数100）
    Online Transaction Proncessing(OLTP)（适用于大量并发连接频繁出现的情况、最大连接数500）
    Manual Setting（可以手动设置最大连接数目）

### 网络选项：

    Enable TCP/IP Networking 选项可以启用和禁用TCP/IP连接，如果不启用，那么只可以在本机进行连接，如果启用了就需要设置端口号，若是端口号被占用则可以选用新的端口号和，
    Enable Strict Mode（启用严禁格式）勾选时不允许出现任何的细微的语法错误

### 设置字符集：

    根据自己需要进行选择，如果需要支持汉字 建议使用UTF-8或者GBK。。。

### 使用JDBC连接数据库

    下载必须的jar包 使用反射加载驱动
    Class.forName(“com.mysql.jdbc.Driver”)；

### 数据库连接地址

    String URL=“jdbc:mysql://host:port/dbName”;dbName  数据库的名称

### 创建连接对象

    Connection c = DriverManager.getConnection(URL,”userName”,”userPwd”);

### 创建SQL语句对象

    Connection.createStatement：这个对象主要用于传递不带参数的SQL语句提交给数据来执行
    Connection.prepareStatement：用来传递带有个或者多个参数的SQL语句
    Connection.prepareCall：该对象用来调用存储过程的

### 执行语句

    executeQuery：查询，并返回单个结果集 放置在ResultSet中
    executeUpdate：执行插入 更新和删除 操作，返回一个整数
    execute：可以执行查询也可以执行更新操作

### 对于返回的结果集进行处理

## 关闭数据库连接

## 查询SQL

<a href="https://www.cnblogs.com/whgk/p/6149009.html" target="_blank"> MySQL(三) 数据库表的查询操作【重要】 </a>

