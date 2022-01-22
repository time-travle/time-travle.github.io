# 常用的服务器

<a href="#" onclick="refreshServiceContent('apache')">Service（apache）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshServiceContent('nginx')">Service（nginx）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshServiceContent('tomcat')">Service（tomcat）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshServiceContent('deployment')">Service（deployment）</a>&emsp;&emsp;&emsp;

---


Apache

	Apache的特点是简单、高速、性能稳定，可作代理服务器使用
	Apache是以进程为基础的结构，要比线程消耗更多的系统开支，不太适合于多处理器环境，还有就是并发不强，流量大了就容易出现500错误
	支持静态界面对于动态的界面不支持，若是要支持需要通过扩展脚本或者模块实现动态
	
	
Tomcat

	基于Java的Web应用容器,不过Tomcat处理静态HTML的能力不如Apache服务器 侧重于动态界面的渲染
	apache是web服务器，tomcat是应用（java）服务器，它只是一个servlet容器，可以认为是apache的扩展，也可以独立于apache运行
	配置相对比较复杂
	Apache:侧重于HTTPServer ，Tomcat:侧重于Servlet引擎
	
	
Nginx

	Nginx是一种高性能的HTTP和反向代理web服务器，支持高并发和负载均衡，以稳定性、丰富的功能集 相比于Apache 它占用的内存更少  
	在连接高并发的情况下，Nginx是Apache服务不错的替代品。同时Nginx的模块也非常丰富，能够满足不同的需求，适合做静态使用
	
	缺点：Nginx 只适合静态和反向代理。
	优点：负载均衡、反向代理、处理静态文件优势。Nginx 处理静态请求的速度高于Apache。
	Nginx有动态分离机制，静态请求直接就可以通过Nginx处理，动态请求才转发请求到后台交由Tomcat进行处理。
	
	nignx的正向代理何反向代理
		正向代理
			客户端直接访问目标服务器受限制，通过在客户端配置一个可以访问目标服务器代理服务器，之后继续使用客户端访问，
		  达到能访问目标服务器的目的
		反向代理
			客户端不能知晓目标服务器的地址信息，但是目标服务器在一台机器（中间服务器）上配置了可以访问目标服务器的映射关系，
		  再进行访问时，使用客户端直接访问中间服务器，从而达到访问目标服务器的目的。
		参考wiki：https://www.cnblogs.com/mpp0905/p/9502856.html
		
	Apache在处理动态有优势，Nginx并发性比较好，CPU内存占用低，如果rewrite频繁，那还是Apache较适合。
	
    
Nginx 和 Apache 各有什么优缺点 链接：https://www.zhihu.com/question/19571087/answer/12313829

    nginx 相对 apache 的优点：
        轻量级，同样起web 服务，比apache 占用更少的内存及资源
        抗并发，nginx 处理请求是异步非阻塞的，而apache 则是阻塞型的，在高并发下nginx 能保持低资源低消耗高性能
        高度模块化的设计，编写模块相对简单
        社区活跃，各种高性能模块出品迅速啊
    apache 相对nginx 的优点：
        rewrite ，比nginx 的rewrite 强大
        模块超多，基本想到的都可以找到
        少bug ，nginx 的bug 相对较多
        超稳定

	
nginx apache tomcat 之间的关系	
	
	nginx / apache是一辆卡车，上面可以装一些东西如html等（静态的）。但是不能装水（动态的），要装水必须要有桶（容器），
	Tomcat就是一个桶（装像Java这样的水），而这个桶也可以放在车上，也可以不放在卡车上。
		客户端（浏览器）：人；
		nginx / apache：卡车；
		静态页面：毛巾；
		tomcat：水桶；
		动态页面：水。
		人要拿毛巾，可以直接从卡车上拿取；
		人要拿水，需要先通过卡车找到车上的桶，才能取得（桶可以不放在车上，把桶单独放在别的地方人也可以取到水）；
	
	nginx常用做静态内容服务和反向代理服务器，以及页面前端高并发服务器。
	适合做负载均衡，直面外来请求转发给后面的应用服务（tomcat什么的），tomcat更多用来做做一个应用容器，让java web app跑在里面的东西。
	大部分适用于静态资源文件的访问（图片，文件）。同类型服务器有apach（nginx使用更多）。


1、作为 Web 服务器：

    相比 Apache，Nginx 使用更少的资源，支持更多的并发连接，体现更高的效率，这点使 Nginx 尤其受到虚拟主机提供商的欢迎。
    在高连接并发的情况下，
    Nginx是Apache服务器不错的替代品: 
        Nginx在美国是做虚拟主机生意的老板们经常选择的软件平台之一. 能够支持高达 50000 个并发连接数的响应, 
        感谢Nginx为我们选择了 epoll and kqueue 作为开发模型. 
    Nginx作为负载均衡服务器: 
        Nginx 既可以在内部直接支持 Rails 和 PHP 程序对外进行服务, 也可以支持作为 HTTP代理 服务器对外进行服务. Nginx采用C进行编写, 
        不论是系统资源开销还是CPU使用效率都比 Perlbal 要好很多. 
2、Nginx 配置简洁, Apache 复杂 ，

    1、Nginx 启动特别容易, 并且几乎可以做到7*24不间断运行，即使运行数个月也不需要重新启动. 
    你还能够不间断服务的情况下进行软件版本的升级 . Nginx 静态处理性能比 Apache 高 3倍以上 ，Apache 对 PHP 支持比较简单，
    2、Nginx 需要配合其他后端来使用 ,Apache 的组件比 Nginx 多. 
    3、最核心的区别在于apache是同步多进程模型，一个连接对应一个进程；
    nginx是异步的，多个连接（万级别）可以对应一个进程 .
    4、nginx的优势是处理静态请求，cpu内存使用率低，apache适合处理动态请求，所以现在一般前端用nginx作为反向代理抗住压力，apache作为后端处理动态请求。	
    
   	
WebSphere

	websphere修改bai配置文件不用像tomcat那样重起服du务器
	不足之处是它的配置十分麻烦
	WebSphere都是对bai业内多种标准的全du面支持，包括EJB、JSB、JMS、JDBC、XML和WML，使Web应用系统的实施更为简单
	
	
	
WebLogic (不开源)

	一套基于JAVA功能强大的电子商务套件，提供了许多功能强大的中间件 以方便编程人员编写的JSP、SERVLET 等电子商务应用，
	可以为企业提供一个完整的商务应用 解决方案
	
	Weblogic的优点：
		1、WLS全面支持J2EE的标准规范和其他规范，Web Service， SSL，XML等等。
		2、完善的售后支持
		3、集群机制，支持分布式的应用
		4、Web控制台进行组件、JDBC、管理和配置
		5、热部署新的Web，EJB应用



一次 RPC 调用流程如下： https://zhuanlan.zhihu.com/p/48760074

    服务消费者(Client 客户端)通过本地调用的方式调用服务。
    客户端存根(Client Stub)接收到调用请求后负责将方法、入参等信息序列化(组装)成能够进行网络传输的消息体。
    客户端存根(Client Stub)找到远程的服务地址，并且将消息通过网络发送给服务端。
    服务端存根(Server Stub)收到消息后进行解码(反序列化操作)。
    服务端存根(Server Stub)根据解码结果调用本地的服务进行相关处理
    服务端(Server)本地服务业务处理。
    处理结果返回给服务端存根(Server Stub)。
    服务端存根(Server Stub)序列化结果。
    服务端存根(Server Stub)将结果通过网络发送至消费方。
    客户端存根(Client Stub)接收到消息，并进行解码(反序列化)。
    服务消费方得到最终结果。	