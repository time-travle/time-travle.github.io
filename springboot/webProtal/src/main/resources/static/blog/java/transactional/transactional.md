<p>
    <a href="#" onclick="refreshContent('java')">返回目录</a>
</p>

---

# java 事物

##事务的4个特性（ACID）：

    1) 原子性（atomicity）：事务是数据库的逻辑工作单位，而且是必须是原子工作单位，对于其数据修改，要么全部执行，要么全部不执行。
    
    2) 一致性（consistency）：事务在完成时，必须是所有的数据都保持一致状态。在相关数据库中，所有规则都必须应用于事务的修改，以保持所有数据的完整性。（实例：转账，两个账户余额相加，值不变。）
    
    3) 隔离性（isolation）：一个事务的执行不能被其他事务所影响。
    
    4) 持久性（durability）：一个事务一旦提交，事物的操作便永久性的保存在DB中。即便是在数据库系统遇到故障的情况下也不会丢失提交事务的操作。

##Java有几种类型的事务？

Java事务的类型有三种：JDBC事务、JTA（Java Transaction API）事务、容器事务。

###1.JDBC事务

在JDBC中处理事务，都是通过Connection完成的。同一事务中所有的操作，都在使用同一个Connection对象。JDBC事务默认是开启的，并且是默认提交。

JDBC Connection 接口提供了两种事务模式：自动提交和手工提交

JDBC中的事务java.sql.Connection 的三个方法与事务有关：

    setAutoCommit（boolean）:设置是否为自动提交事务，如果true（默认值为true）表示自动提交，也就是每条执行的SQL语句都是一个单独的事务，如果设置为false，需要手动提交事务。
    
    commit（）：提交结束事务。
    
    rollback（）：回滚结束事务。

####传统JDBC操作流程：

1).获取JDBC连接   2).声明SQL   3).预编译SQL   4).执行SQL   5).处理结果集

6).释放结果集  7).释放Statement  8).提交事务  9).处理异常并回滚事务 10).释放JDBC连接

####JDBC优缺点：
    1.冗长、重复     2.显示事务控制     3.每个步骤不可获取    4.显示处理受检查异常

JDBC为使用Java进行数据库的事务操作提供了最基本的支持。通过JDBC事务，我们可以将多个SQL语句放到同一个事务中，保证其ACID特性。

JDBC事务的主要优点就是API比较简单，可以实现最基本的事务操作，性能也相对较好。

但是，JDBC事务有一个局限：一个 JDBC 事务不能跨越多个数据库！所以，如果涉及到多数据库的操作或者分布式场景，JDBC事务就无能为力了。

###2.JTA事务

JTA(Java Transaction API)提供了跨数据库连接（或其他JTA资源）的事务管理能力。

JTA事务管理则由JTA容器实现，J2ee框架中事务管理器与应用程序，资源管理器，以及应用服务器之间的事务通讯。

####1)JTA的构成

    a、高层应用事务界定接口，供事务客户界定事务边界的
    
    b、X/Open XA协议(资源之间的一种标准化的接口)的标准Java映射，它可以使事务性的资源管理器参与由外部事务管理器控制的事务中
    
    c、高层事务管理器接口，允许应用程序服务器为其管理的应用程序界定事务的边界

####2)JTA的主要接口位于javax.transaction包中
    
    a、UserTransaction接口：让应用程序得以控制事务的开始、挂起、提交、回滚等。由Java客户端程序或EJB调用。
    
    b、TransactionManager 接口：用于应用服务器管理事务状态
    
    c、Transaction接口：用于执行相关事务操作
    
    d、XAResource接口：用于在分布式事务环境下，协调事务管理器和资源管理器的工作
    
    e、Xid接口：为事务标识符的Java映射

注：前3个接口位于Java EE版的类库 javaee.jar 中，Java SE中没有提供！UserTransaction是编程常用的接口,JTA只提供了接口，没有具体的实现。

JTS(Java Transaction Service)是服务OTS的JTA的实现。简单的说JTS实现了JTA接口，并且符合OTS的规范。

JTA的事务周期可横跨多个JDBC Connection生命周期，对众多Connection进行调度，实现其事务性要求。

JTA可以处理任何提供符合XA接口的资源。包括：JDBC连接，数据库，JMS，商业对象等等。

####3)JTA编程的基本步骤
    
    a、首先配置JTA ，建立相应的数据源
    
    b、建立事务：通过创建UserTransaction类的实例来开始一个事务。代码如下：
    
        Context ctx = new InitialContext(p) ;
    
        UserTransaction trans = (UserTransaction) ctx.lookup("javax. Transaction.UserTransaction")
    
    c、开始事务：代码为 trans.begin() ;
    
    d、找出数据源：从Weblogic Server上找到数据源，代码如下：
    
    DataSource ds = (DataSource) ctx.lookup(“mysqldb") ;
    
    e、建立数据库连接：Connection mycon = ds.getConnection() ;
    
    f、执行SQL操作：stmt.executeUpdate(sqlS);
    
    g、完成事务：trans.commit(); / trans.rollback();
    
    h、关闭连接：mycon.close() ;

###JTA的优缺点：

    JTA的优点很明显，就是提供了分布式事务的解决方案，严格的ACID。但是，标准的JTA方式的事务管理在日常开发中并不常用。
    
    JTA的缺点是实现复杂，通常情况下，JTA UserTransaction需要从JNDI获取。这意味着，如果我们使用JTA，就需要同时使用JTA和JNDI。
    
    JTA本身就是个笨重的API，通常JTA只能在应用服务器环境下使用，因此使用JTA会限制代码的复用性。


###3、Spring容器事务

Spring事务管理的实现有许多细节，如果对整个接口框架有个大体了解会非常有利于我们理解事务，下面通过讲解Spring的事务接口来了解Spring实现事务的具体策略。

Spring事务管理涉及的接口及其联系：

![avatar](../blog/java/imgs/img_3.png)

Spring并不直接管理事务，而是提供了多种事务管理器，他们将事务管理的职责委托给Hibernate或者JTA等持久化机制所提供的相关平台框架的事务来实现。 

Spring事务管理器的接口是org.springframework.transaction.PlatformTransactionManager，通过这个接口，Spring为各个平台如JDBC、Hibernate等都提供了对应的事务管理器，但是具体的实现就是各个平台自己的事情了。


    Public interface PlatformTransactionManager{  
        // 由TransactionDefinition得到TransactionStatus对象
        TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
        // 提交
        Void commit(TransactionStatus status) throws TransactionException;  
        // 回滚
        Void rollback(TransactionStatus status) throws TransactionException;  
    }


##参考：
- <a href="https://blog.csdn.net/weixin_37934748/article/details/82774230" target="_blank">Java中的事务及使用 </a>
- <a href="#" onclick="refreshSpringContent('transactional')">Spring（事物）</a>&emsp;&emsp;&emsp;
