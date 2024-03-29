<p>
    <a href="#" onclick="refreshContent('springcloud')">返回目录</a>
</p>

# Spring Cloud -eureka知识积累

===========================================注册 中心============================================

springCloud 支持最好的是Eureka 其次是 Consul 最后是Zookpeeper 想将一个微服务注册到Eureka Server（或其他服务发现组件，例如Zookeeper、Consul等），Eureka
2.0闭源之后，Consul慢慢会成为主流。

## pom依赖

        添加Eureka Client（或其他服务发现组件的Client）依赖：
        <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- Spring  Cloud  --> 
        <dependencyManagement> 
            <dependencies> 
                <dependency> 
                    <groupid>org.springframework.cloud</groupId>
                    <artifactid>spring-cloud-dependencies</ artifactid> 
                    <version>Dalston.SR4</version> 
                    <type>pom</type> 
                    <scope>import</scope> 
                </dependency> 
            </dependencies> 
        </dependencyManagement> 

## 启动类

    从Spring Cloud Edgware开始，@EnableDiscoveryClient 或@EnableEurekaClient (可省略)。
    只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上。
        写注解：在启动类上添加注解@EnableDiscoveryClient 或@EnableEurekaClient
        @EnableEurekaClient
        @SpringBootApplication
        public class ProviderUserApplication {
          public static void main(String[] args) {
            SpringApplication.run(ProviderUserApplication.class, args);
          }
        }
        
    @EnableDiscoveryClient 和@EnableEurekaClient 共同点就是：都是能够让注册中心能够发现，扫描到改服务。
    不同点：@EnableEurekaClient 只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心

## 配置文件

        写配置：
        spring:
          application:
            name: microservice-provider-user
          port: 8761  
        eureka:
          instance:
            hostname: localhost
          client:
            enableSelfPreservation: false  将自我保护模式关闭  保护模式主要用于一组客户端和 Eureka Server 之间存在网络分区场景时 。 一旦进入保护模式， Eureka Server 将会尝试保护其服务的注册表中的信息，不再删除服务注册表中的数据。 当网络故障恢复后，该 Eure ka Server 节点会自动退出保护模式。
           .evi ction-interval-tirner-in-ms: SOOO  和清理间隔 ＃ 默认 6000 0 毫秒
            registry-fetch-interval-seconds: 30 Eureka Server上则会维护一份只读的服务清单来返回给客户端，这个服务清单并不是实时数据，而是一份缓存数据，默认30秒更新一次，如果想要修改清单更新的时间间隔
            fetch-registry: false  由于注册中心的职责就是维护服务实例 ， 他并不需要去检索服务，所以也设置为 false
            register-with-eureka: false  由于该应用为注册中心，所以设置为 false ， 代表不向汪册中心注册自己 ，不然启动时会把自己当作客户端向自己注册，会报错
            serviceUrl:
              defaultZone: http://localhost:8761/eureka/   在url中看到注册中心的地址

## 多中心配置 Eureka 高可用搭建

    假设我们有 master 和 slaveone 两台机器，
        需要做的就是 ：
            ·将 master 注册到 slaveone 上面。
            ．将 slaveone 注册到 master 上面。
        如果是 3 台机器，以此类推：
            ·将 master 注册到 slaveone 和 slavetwo 上面 。
            ·将 slaveone 注册到 master 和 slavetwo 上面 。
            ·将 slavetwo 注册到 master 和 slaveone 上面。

    先描述一个场景：首先我有两个服务注册中心（相互注册），地址分别是http://localhost:1111和http://localhost:1112，
    然后我还有两个服务提供者，地址分别是http://localhost:8080和http://localhost:8081，
    然后我将8080这个服务提供者注册到1111这个注册中心上去，将8081这个服务提供者注册到1112这个注册中心上去，
    此时我在服务消费者中如果只向1111这个注册中心去查找服务提供者，那么是不是只能获取到8080这个服务而获取不到8081这个服务？
    
    答案是服务消费者可以获取到两个服务提供者提供的服务。
    虽然两个服务提供者的信息分别被两个服务注册中心所维护，但是由于服务注册中心之间也互相注册为服务，当服务提供者发送请求到一个服务注册中心时，
    它会将该请求转发给集群中相连的其他注册中心，从而实现注册中心之间的服务同步，
    通过服务同步，两个服务提供者的服务信息我们就可以通过任意一台注册中心来获取到
    
    两个中心 （可以是一个工程两个配置文件 启动时启动不同的配置文件） 
        创建了application-peer1.properties和application-peer2.properties配置文件，如下：
            application-peer1.properties:
                spring.application.name=eureka-server
                server.port=1111
                eureka.instance.hostname=peer1
                eureka.client.register-with-eureka=true
                eureka.client.service-url.defaultZone=http://peer2:1112/eureka/
                
            application-peer2.properties:
                spring.application.name=eureka-server
                server.port=1112
                eureka.instance.hostname=peer2
                eureka.client.register-with-eureka=true
                eureka.client.service-url.defaultZone=http://peer1:1111/eureka/

            命令启动 服务 java -jar 打好的jar包全名 --spring.profiles.active=peer2  

    两个服务提供者  （可以是一个工程两个配置文件 启动时启动不同的配置文件） 
        provider工程也创建两个配置文件，分别为application-p1.properties和application-p2.properties，内容如下：

        application-p1.properties:
            spring.application.name=hello-service
            server.port=8080
            eureka.client.service-url.defaultZone=http://peer1:1111/eureka

        application-p2.properties:
            spring.application.name=hello-service
            server.port=8081
            eureka.client.service-url.defaultZone=http://peer2:1112/eureka
    
        命令启动 服务 java -jar 打好的jar包全名 --spring.profiles.active=p1  

    我们将8080这个服务注册到1111这个服务注册中心上去了，将8081这个服务注册到1112这个服务注册中心上去了

## 开启 Eureka 认证

    添加pom依赖
        <dependency> 
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency> 
    修改配置
            application.properties 中加上认证的配置信息：
            security.basic.enabled=true  ＃开启 认证
            security.user.name=yinjihuan  ＃ 用户名
            security.user.password=123456  ＃ 密码

    重新启动注册中心，访问 http: // localhost:8761/ ，此时浏览器会提示你输入用户名和密码，输入正确后才能继续访问 Eureka 提供的管理页面 。
        在 Eureka 开启认证后，客户端注册的配置也要加上认证的用户名和密码信息：
        eureka.client.serviceUrl.defaultZone= http://yinjihuan:123456@localhost:8761/eureka/ 

## Q1:

![avatar](../blog/springcloud/imag/Whitelabel_Error_Page.png)![avatar](../imag/Whitelabel_Error_Page.png)

<a href="https://blog.csdn.net/li_ainy/article/details/87277695#" target="_blank">https://blog.csdn.net/li_ainy/article/details/87277695</a>

## Q2:如何快速找到指定端口被哪个程序占用并释放该端口

<a href="https://blog.csdn.net/HumorChen99/article/details/81030330#" target="_blank">https://blog.csdn.net/HumorChen99/article/details/81030330</a>

## Q3：如何配置多注册中心

单击模拟时需要修改对用的hosts 才可在一台电脑模拟集群

<a href="https://zhuanlan.zhihu.com/p/76582106#" target="_blank">https://zhuanlan.zhihu.com/p/76582106 </a>

<a href="https://blog.csdn.net/xcbeyond/article/details/81503484#" target="_blank">https://blog.csdn.net/xcbeyond/article/details/81503484 </a>

<a href="https://www.jianshu.com/p/5613212e91bb#" target="_blank">https://www.jianshu.com/p/5613212e91bb</a>

## Q4: 开启认证以后服务提供者频繁注册不到注册中心

<a href="https://www.cnblogs.com/JamieLove/p/11087564.html#" target="_blank">https://www.cnblogs.com/JamieLove/p/11087564.html</a>

<a href="https://www.cnblogs.com/idoljames/p/11622343.html#" target="_blank">https://www.cnblogs.com/idoljames/p/11622343.html</a>

<a href="http://c.biancheng.net/view/5325.html#" target="_blank">http://c.biancheng.net/view/5325.html</a>

## Q5:单机如何搭建高可用 multi-eureka

需要修改对用的hosts 才可在一台电脑模拟集群

<a href="https://zhuanlan.zhihu.com/p/76582106#" target="_blank">https://zhuanlan.zhihu.com/p/76582106 </a>

<a href="https://blog.csdn.net/xcbeyond/article/details/81503484#" target="_blank">https://blog.csdn.net/xcbeyond/article/details/81503484 </a>

<a href="https://www.jianshu.com/p/5613212e91bb#" target="_blank">https://www.jianshu.com/p/5613212e91bb</a>

修改hosts文件添加如下

    # for multi-eureka begin
    127.0.0.1 center-master
    127.0.0.1 center-second
    127.0.0.1 center-third
    # for multi-eureka end

## Q6:Eureka高可用，节点均出现在unavailable-replicas下

    1.eureka.client.serviceUrl.defaultZone配置项的地址，不能使用localhost，要使用域名，DNS解析请自行配置。
    
    2.spring.application.name要一致（这个个人测试默认不配也可以）
    
    3.如下两个参数需配为true（个人测试默认不配也可行）
    eureka:
      client:
        register-with-eureka: true
        fetch-registry:  true
    
    4.配置eureka.instance.hostname(好像看到过正常eureka会自动拉取设备host，但各节点在同一机器下时请务必添加，注意各节点配置自己节点的host)
    eureka:
      instance:
        hostname:host1
    作者：A雪_辰A链接：https://www.jianshu.com/p/59c54ccc6ba6

### 自定义 Eureka 的 Instance ID

    客户端在注册时，服务的 Instance ID 的默认值的格式如下：
    ${spring.cloud.client.hostname ｝：$｛ spring.application.name ｝：$｛ spring.application.instance  id:${server.port}} 
    翻译过来就是“主机名：服务名称：服务端口” 。 
    当我们在 Eureka 的 Web 控制台查看服务注册信息的时候，就是这样的一个格式： user-PC :fsh-house:  8081 。

    很多时候我们想把 IP 显示在上述格式中，此时，只要把主机名替换成 IP 就可以了，或者调整顺序也可以 。
    可以改成下面的样子，用“服务名称：服务所在 IP ：服务端口”的格式来定义：
    eureka.instance.instance-id=${spring.application.name}:${spring.cloud.client.ipAddress ｝：$｛ spring.application.instance  id ：$｛ server.port}}

## 服务上下线监控

    在某些特定的需求下，我们需要对服务 的上下线进行监控，上线或下线都进行邮件通知， Eureka 中提供了事件监听的方式来扩展 。
    目前支持的事件如下：
    ?  EurekainstanceCanceledEvent 服务下线事件。
    ?  EurekalnstanceRegisteredEvent 服务注册事件。
    ?  EurekalnstanceRenewedEvent 服务续约事件。
    ?  EurekaRegistryAvailableEvent  Eureka 注册中心启动事件。
    ?  EurekaServerStartedEvent Eureka  Server 启动事件。
    
    基于 Eureka 提供的事件机制，可以监控服务的上下线过程，在过程发生 中可以发送邮件来进行通知 。 
    下面的代码只是演示了监控的过程， 并未发送邮件。
    @Component 
    public  class  EurekaStateChangeListener  { 
        @EventListener 
        public  void  listen(EurekaInstanceCanceledEven t event)  { 
            System.err.println(event.getServerid()  +”\t"  + event.getAppName ( )  ＋” 服务下 线 ”）；
        }
        @EventListener 
        public  void  listen(EurekainstanceRegisteredEvent  event)  { 
            Instanceinfo instanceinfo  =  event.getinstanceinfo(); 
            System.err.println(instanceinfo.getAppName()  ＋” 进行注册 ”）；
        }
        @EventListener 
        public  void  lIsten(EurekainstanceRenewedEvent event)  { 
            System.err.println(event.getServerid()  +  " \ t ”+ event.getAppName ( )  ＋” 服务进行续约 ”）；
        }
        @EventListener 
        public  void  listen(EurekaRegistryAvailableEvent  event)  { 
            System.err.println （” 注册中心启动 ”）；
        }
        @EventListener 
        public  void  listen(EurekaServerStartedEvent  event)  { 
            System.err.printl n (”Eureka  Server 启动 ”）；
        }
    }

============================================服务提供者==========================================

## demo：

现将一个服务提供者service 注册到 Eureka 中

### maven

    <dependencies> 
        <!-- eureka  --> 
        <dependency> 
            <groupId>org.springframework.cloud</groupid>
            <artifactid>spring- cloud-starter-eureka</artifactid> 
        </dependency> 
    </dependencies> 
    依赖管理部分同上。。。

### 启动类

        @EnableDiscoveryClient 这个表示当前服务是一个 Eureka 的客户端。
        @SpringBootApplication
        public class Application {
          public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
          }
        }

### 配置：

    在 src/main/resources 下面创建一个 application.properties 属性文件，增加下面的
    配置：
        spring.appl 工cation.name=fsh-house
        server.port=8081 
        eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/   上面注册中心的地址
        # 在注册完服务之后，服务提供者会维护一个心跳来不停的告诉Eureka Server：“我还在运行”，
        # 以防止Eureka Server将该服务实例从服务列表中剔除，这个动作称之为服务续约，和服务续约相关的属性有两个
        eureka.instance.lease-expiration-duration-in-seconds=90   配置用来定义服务失效时间，默认为90秒
        eureka.instance.lease-renewal-interval-in-seconds=30  用来定义服务续约的间隔时间，默认为30秒。

    eureka.client.serviceUrl.defaultZone 的地址就是我们之前启动的 Eureka 服务的地址，在启动的时候需要将自身的信息注册到 Eureka 中去 

### 提供一个服务demo：

    @RestController 
    @RequestMapping (”/house ”) 
    public  class  HouseController  { 
        @GetMapping (”/hello ”) 
        public  String  hello()  { 
            return ”Hello ”; 
        }
    }
    
    http://localhost : 8081 / house/hello     就是 OK的 

==========================================服务消费者==========================================

来一个来消费者工程 消费我们刚刚写的 house/ hello 接口

    RestTemplate 是 Spring 提供的用于访问 Rest 服务的 客户端， RestTemplate 提供了
    多种便捷访 问远程 Http 服 务的方法，能够大大提高客户端的编写效率

## maven：

    正常的maven配置

## 配置文件：

    app lic ation.properties 文件中的配置信息 ：
    spring.application.name=fsh-substitution 
    server.port=8082 

## 正常的使用接口直接调用

    消费服务类：
        @RestController 
        @RequestMapping (” / substitution”) 
        public  class  SubstitutionController  { 
            @Autowired 
            private  RestTemplate  restTemplate; 
            
            @GetMapping (” / callHello”} 
            public  String  callHello()  { 
                return  restTemplate.getForObject("http://localhost:8081/house/hello ”, String.class) ; 
            }
        }
         // 抽出来
        @Configuration 
        public  class  BeanConfiguration  { 
            @Bean 
            public  RestTemplate  getRestTemplate()  { 
                return  new  RestTemplate(); 
            }
        }

        http://localhost: 8082/substitution/ callHello 若是这个 连接能直接调通  说明直接通过接口调用是ok的

### 通过 Eureka 来消费接口

    消费服务类： 
    改造调用代码，我们不再直接写固定地址， 而是写成服务的名称，这个名称也就是我们注册到 Eureka 中的名称 ， 是属性文件中的 spr ing.application.name
        @RestController 
        @RequestMapping (” / substitution”) 
        public  class  SubstitutionController  { 
            @Autowired 
            private  RestTemplate  restTemplate; 
            
            @GetMapping (” / callHello”} 
            public  String  callHello()  { 
                return  restTemplate.getForObject("http://fsh-house/house/hello ”, String.class) ; 
            }
        }
         // 抽出来
        @Configuration 
        public  class  BeanConfiguration  { 
            @Bean 
            @LoadBalanced  这个注解会自动构造 LoadBalancerClient 接口的实现类并注册到 Spring 容器中， 实现负载均衡
            public  RestTemplate  getRestTemplate()  { 
                return  new  RestTemplate(); 
            }
        }

        http://localhost: 8082/substitution/ callHello 若是这个 连接能直接调通  说明直接注册中心调用是ok的
