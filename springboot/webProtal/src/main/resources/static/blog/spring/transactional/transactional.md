<p>
    <a href="#" onclick="refreshContent('spring')">返回目录</a>
</p>

# Spring 事物 知识积累

## 事务必须遵循4个原则

    原子性（Atomicity）：事务是一个原子操作，由一系列动作组成。事务的原子性确保动作要么全部完成，要么完全不起作用。
    一致性（Consistency）：一旦事务完成（不管成功还是失败），系统必须确保它所建模的业务处于一致的状态，而不会是部分完成部分失败。
                            在现实中的数据不应该被破坏。
    隔离性（Isolation）：可能有许多事务会同时处理相同的数据，因此每个事务都应该与其他事务隔离开来，防止数据损坏。
    持久性（Durability）：一旦事务完成，无论发生什么系统错误，它的结果都不应该受到影响，这样就能从任何系统崩溃中恢复过来。
                            通常情况下，事务的结果被写到持久化存储器中。

## 设置事务隔离级别（5种）

    DEFAULT这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别；
    未提交读（read uncommited）：脏读，不可重复读，虚读都有可能发生
    已提交读（read commited）：避免脏读。但是不可重复读和虚读都有可能发生；
    可重复读（repeatable read）：避免脏读和不可重复读，但是虚读有可能发生；
    串行化的（serializable）：避免以上所有读问题。
    MySQL默认：可重复读
    Oracle默认：已提交读

## 如果我开启事务之后服务器突然宕机,或者硬盘损坏,断电断网了那么我执行的操作时执行成功了还是没成功呢？

    当我们对数据库进行增删改操作时,我们操作的对象都不属于持久化状态,如果突然发生意外,只要事务没有提交则不会对数据造成影响，
    当服务器通电了会根据数据库自身记录的信息找到没有提交的事务然后进行回滚。
    那么我们在提交事务的过程中断电会怎样？这就得看服务器是否收到提交事务的请求了,如果收到了执行成功,没有则失败,这就是事务的原子性要么成功要么失败

## Spring和事务的关系

    关系型数据库、某些消息队列等产品或中间件称为事务性资源，因为它们本身支持事务，也能够处理事务。

    Spring很显然不是事务性资源，但是它可以管理事务性资源，所以Spring和事务之间是管理关系

## Spring事务三要素

    数据源：表示具体的事务性资源，是事务的真正处理者，如MySQL等。

    事务管理器：像一个大管家，从整体上管理事务的处理过程，如打开、提交、回滚等。

    事务应用和属性配置：像一个标识符，表明哪些方法要参与事务，如何参与事务，以及一些相关属性如隔离级别、超时时间等。

## Spring事务的注解配置

    把一个DataSource（如DruidDataSource）作为一个@Bean注册到Spring容器中，配置好事务性资源。

    把一个@EnableTransactionManagement注解放到一个@Configuration类上，配置好事务管理器，并启用事务管理。

    把一个@Transactional注解放到类上或方法上，可以设置注解的属性，表明该方法按配置好的属性参与到事务中。

## 事务注解在类/方法上

    @Transactional注解既可以标注在类上，也可以标注在方法上。当在类上时，默认应用到类里的所有方法。如果此时方法上也标注了，则方法上的优先级高。

## 事务注解在类上的继承性

    @Transactional注解的作用可以传播到子类，即如果父类标了子类就不用标了。但倒过来就不行了。

    子类标了，并不会传到父类，所以父类方法不会有事务。父类方法需要在子类中重新声明而参与到子类上的注解，这样才会有事务。

## 事务注解在接口/类上

    @Transactional注解可以用在接口上，也可以在类上。在接口上时，必须使用基于接口的代理才行，即JDK动态代理。

    事实是Java的注解不能从接口继承，如果你使用基于类的代理，即CGLIB，或基于织入方面，即AspectJ，事务设置不会被代理和织入基础设施认出来，目标对象不会被包装到一个事务代理中。

    Spring团队建议注解标注在类上而非接口上。

## 只在public方法上生效？

    当采用代理来实现事务时，（注意是代理），@Transactional注解只能应用在public方法上。当标记在protected、private、package-visible方法上时，不会产生错误，但也不会表现出为它指定的事务配置。可以认为它作为一个普通的方法参与到一个public方法的事务中。

    如果想在非public方法上生效，考虑使用AspectJ（织入方式）。

## 逻辑事务与物理事务

    事务性资源实际打开的事务就是物理事务，如数据库的Connection打开的事务。Spring会为每个@Transactional方法创建一个事务范围，可以理解为是逻辑事务。

    在逻辑事务中，大范围的事务称为外围事务，小范围的事务称为内部事务，外围事务可以包含内部事务，但在逻辑上是互相独立的。
    每一个这样的逻辑事务范围，都能够单独地决定rollback-only状态

## 脏读

    一个事务修改了一行数据但没有提交，第二个事务可以读取到这行被修改的数据，如果第一个事务回滚，第二个事务获取到的数据将是无效的。

## 不可重复读

    一个事务读取了一行数据，第二个事务修改了这行数据，第一个事务重新读取这行数据，将获得到不同的值。

## 幻读

    一个事务按照一个where条件读取所有符合的数据行，第二个事务插入了一行数据且恰好也满足这个where条件，
    第一个事务再以这个where条件重新读取，将会获取额外多出来的这一行。

## 事物Transactional 开启事物 支持

    @EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
    @Transactional(rollbackFor=Exception.class)  
    为方法开启事务同时 指定事务回滚方法
    @Transactional(noRollbackFor=Exception.class)
    不回滚    

## Transactional注解的可用参数

### readOnly

    该属性用于设置当前事务是否为只读事务，设置为true表示只读，false则表示可读写，默认值为false

### rollbackFor

    该属性用于设置需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，则进行事务回滚。例如：
    1. 指定单一异常类：@Transactional(rollbackFor=RuntimeException.class)
    2. 指定多个异常类：@Transactional(rollbackFor={RuntimeException.class, BusnessException.class})

### rollbackForClassName

    该属性用于设置需要进行回滚的异常类名称数组，当方法中抛出指定异常名称数组中的异常时，则进行事务回滚。例如：
    1. 指定单一异常类名称：@Transactional(rollbackForClassName=“RuntimeException”)
    2. 指定多个异常类名称：@Transactional(rollbackForClassName={“RuntimeException”,“BusnessException”})

### noRollbackFor

    该属性用于设置不需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，不进行事务回滚

### noRollbackForClassName

    参照上方的例子

### timeout

    该属性用于设置事务的超时秒数，默认值为-1表示永不超时

### propagation

    该属性用于设置事务的传播行为
    例如：@Transactional(propagation=Propagation.NOT_SUPPORTED)


    事物传播行为介绍:
    
        @Transactional(propagation=Propagation.REQUIRED) 如果有事务, 那么加入事务, 没有的话新建一个(默认)
        @Transactional(propagation=Propagation.NOT_SUPPORTED) 容器不为这个方法开启事务
        @Transactional(propagation=Propagation.REQUIRES_NEW) 不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
        @Transactional(propagation=Propagation.MANDATORY) 必须在一个已有的事务中执行,否则抛出异常
        @Transactional(propagation=Propagation.NEVER) 必须在一个没有的事务中执行,否则抛出异常(与Propagation.MANDATORY相反)
        @Transactional(propagation=Propagation.SUPPORTS) 如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不用事务

### isolation

    该属性用于设置底层数据库的事务隔离级别
    
    事务隔离级别介绍:
    
        @Transactional(isolation = Isolation.READ_UNCOMMITTED)读取未提交数据(会出现脏读, 不可重复读) 基本不使用
        @Transactional(isolation = Isolation.READ_COMMITTED)读取已提交数据(会出现不可重复读和幻读)
        @Transactional(isolation = Isolation.REPEATABLE_READ)可重复读(会出现幻读)
        @Transactional(isolation = Isolation.SERIALIZABLE)串行化














    