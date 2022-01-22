#zookeeper  知识点

1、如何添加Watch

	一、向客户端的构造方法中传递地阐述
		new ZooKeeper(String connectString, int sessionTimeout, Watcher watcher)
		connectString 服务端地址
		sessionTimeout：超时时间
		Watcher：监控事件
	二、客户端通过getData、exists、getChildren 接口来向服务器注册Watcher
		getData(String path, Watcher watcher, Stat stat)


2、ZooKeeper 如何进行序列化？ https://kaiwu.lagou.com/course/courseInfo.htm?courseId=158#/detail/pc?id=3134

	序列化是指将我们定义好的 Java 类型转化成数据流的形式，
	之所以这么做是因为在网络传输过程中，TCP 协议采用“流通信”的方式，提供了可以读写的字节流。
	而这种设计的好处在于避免了在网络传输过程中经常出现的问题：比如消息丢失、消息重复和排序等问题。
	
	Java 中进行序列化和反序列化的过程中，主要用到了 ObjectInputStream 和 ObjectOutputStream 两个 IO 类。
	ObjectOutputStream 负责将对象进行序列化并存储到本地。而 ObjectInputStream 从本地存储中读取对象信息反序列化对象。
	
	在 ZooKeeper 中并没有采用和 Java 一样的序列化方式，而是采用了一个 Jute 的序列解决方案作为 ZooKeeper 框架自身的序列化方式
	
	
3、如何使用 Jute 在 ZooKeeper 中实现序列化。

	如果我们要想将某个定义的类进行序列化，首先需要该类实现 Record 接口的 serilize 和 deserialize 方法，
	这两个方法分别是序列化和反序列化方法。	
	
	
4、ZooKeeper 协议	

	ZooKeeper 是在 TCP/IP 协议的基础上实现了自己特有的通信协议格式

5、zookeeper 常用的命令。

    常用命令：ls get set create delete 等

6、Zookeeper 的典型应用场景

    （1）数据发布/订阅
    （2）负载均衡
    （3）命名服务
    （4）分布式协调/通知
    （5）集群管理
    （6）Master 选举
    （7）分布式锁
    （8）分布式队列

7、四种类型的数据节点 Znode

    （1）PERSISTENT-持久节点
    除非手动删除，否则节点一直存在于 Zookeeper 上

    （2）EPHEMERAL-临时节点
    临时节点的生命周期与客户端会话绑定，一旦客户端会话失效（客户端与zookeeper 连接断开不一定会话失效），那么这个客户端创建的所有临时节点都会被移除。

    （3）PERSISTENT_SEQUENTIAL-持久顺序节点
    基本特性同持久节点，只是增加了顺序属性，节点名后边会追加一个由父节点维护的自增整型数字。

    （4）EPHEMERAL_SEQUENTIAL-临时顺序节点
    基本特性同临时节点，增加了顺序属性，节点名后边会追加一个由父节点维护的自增整型数字。

8、节点查看：
   
    创建节点
    create /nodeName
    删除节点
    delete /nodeName
    查看节点的状态
    stat /nodeName
    

9、部署模式：

    单机模式、伪集群模式、集群模式
    集群需要一半以上的机器可用，所以，3台挂掉1台还能工作，2台不能

10、ACL 权限控制	

	//创建节点
	create /digest_node1
	//设置digest权限验证
	setAcl /digest_node1 digest:用户名:base64格式密码:rwadc 
	//查询节点Acl权限
	getAcl /digest_node1 
	//授权操作
	addauth digest user:passwd
	
	在 ZooKeeper 中已经定义好的权限有 5 种：
		数据节点（create）创建权限，授予权限的对象可以在数据节点下创建子节点；
		数据节点（wirte）更新权限，授予权限的对象可以更新该数据节点；
		数据节点（read）读取权限，授予权限的对象可以读取该节点的内容以及子节点的信息；
		数据节点（delete）删除权限，授予权限的对象可以删除该数据节点的子节点；
		数据节点（admin）管理者权限，授予权限的对象可以对该数据节点体进行 ACL 权限设置。
		
	权限模式：Scheme
		权限模式就是用来设置 ZooKeeper 服务器进行权限验证的方式。
		ZooKeeper 的权限验证方式大体分为两种类型，一种是范围验证，另外一种是口令验证(也可以理解为用户名密码的方式)
		
	每个节点都有维护自身的 ACL 权限数据，即使是该节点的子节点也是有自己的 ACL 权限而不是直接继承其父节点的权限	
	
	自定义权限：
		最核心的一点是实现 ZooKeeper 提供的权限控制器接口 AuthenticationProvider。
		实现了自定义权限后，接下来就需要将自定义的权限控制注册到 ZooKeeper 服务器中，而注册的方式通常有两种。
			第一种是通过设置系统属性来注册自定义的权限控制器：
			复制-Dzookeeper.authProvider.x=CustomAuthenticationProvider
			另一种是在配置文件 zoo.cfg 中进行配置：
			复制authProvider.x=CustomAuthenticationProvider



ZooKeeper面试题

https://blog.csdn.net/yy339452689/article/details/105864417