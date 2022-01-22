<p>
    <a href="#" onclick="refreshContent('springcloud')">返回目录</a>
</p>

#Spring Cloud -ribbon知识积累

##如何搭建使用Ribbon

    Ribbon 是在客户端实现负载均衡的
    
    经过网络查看多个不同的用例，发现在springboot中使用这个组件的大部分是和结合RestTemplate 的直接单独的使用的情况使用较少。
    这里就不表述单独的使用方法了
    下面直接说如何和这个 RestTemplate 进行结合
        
###首先引入POM
   
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
       </dependency> 
       或者是
       <dependency> 
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-ribbon</artifactId> 
       </dependency>
       
###修改一下 RestTemplate 的实体获取方法 
   使用注释LoadBalanced开始负载均衡 如是不用这个我们调用服务是写的对应的服务名或只是一个IP 
   是起不到负载均衡的目的
   
       @Bean
       @LoadBalanced
       public RestTemplate getRestTemplate() {
           return new RestTemplate();
       }
           
   RestTemplate 常用的方法大致如下：
     
     public  <T>  T  getForObject(String  url,  Class<T>  responseType, Object ...  uriVariables); 
     public  <T>  T  getForObject(String  url,  Class<T>  responseType, Map<String,  ?>  uriVariables); 
     public  <T>  T  getForObject(URI  url,  Class<T>  responseType);
     
     public  <T>  T  postForObject(String  url,  Object  request, Class<T>  responseType,  Object ...  uriVariables); 
     public  <T>  T  postForObject(String  url,  Object  request, Class<T>  responseType,  Map<String,  ?>  uriVariables); 
     public  <T>  T  postForObject(URI  url,  Object  request,  Class<T>  responseType);
    



========================客户端负载均衡========================

客户端负载均衡和服务端负载均衡最大的区别在于服务清单所存储的位置。

    在客户端负载均衡中，所有的客户端节点都有一份自己要访问的服务端清单，这些清单统统都是从Eureka服务注册中心获取的。
        在Spring Cloud中我们如果想要使用客户端负载均衡，方法很简单，开启@LoadBalanced注解即可，
        这样客户端在发起请求的时候会先自行选择一个服务端，向该服务端发起请求，从而实现负载均衡
    
    服务端负载均衡又分为两种，一种是硬件负载均衡，还有一种是软件负载均衡。
        硬件负载均衡主要通过在服务器节点之间安装专门用于负载均衡的设备，常见的如F5。
        软件负载均衡则主要是在服务器上安装一些具有负载均衡功能的软件来完成请求分发进而实现负载均衡，常见的就是Nginx。
        
        无论是硬件负载均衡还是软件负载均衡都会维护一个可用的服务端清单，
        然后通过心跳机制来删除故障的服务端节点以保证清单中都是可以正常访问的服务端节点，
        此时当客户端的请求到达负载均衡服务器时，负载均衡服务器按照某种配置好的规则从可用服务端清单中选出一台服务器去处理客户端的请求。
        这就是服务端负载均衡


##Ribbon 模块如下
    ?  ribbon-loadbalancer 负载均衡模块，可独立使用，也可以和别的模块一起使用 。Ribbon 内置的负载均衡算法都实现在其中 。
    ?  ribbon-eureka ：基于 Eureka 封装的模块，能够快速方便地集成 Eureka 。
    ?  ribbon-transport ： 基于 Netty 实现多协议的支持，比如 H坤 、 Tep 、 Udp 等。
    ?  ribbon-httpclient ：基于 Apache HttpClient 封装的 REST 客户端，集成了负载均衡模块，可以直接在项目中使用来调用接口 。
    ?  ribbon-example:  Ribbon 使用代码示例，通过这些示例能够让你的学习事半功倍。
    ?  ribbon-core ：一些比较核心且具有通用性的代码，客户端 API 的一些配置和其他 API的定义 


##Ribbon工作时分为两步：
    第一步先选择	Eureka	Server,	它优先选择在同一个Zone且负载较少的Server；
    第二步再根据用户指定的策略，在从Server取到的服务注册列表中选择一个地址。
    其中Ribbon提供了三种策略：轮询、断路器和根据响应时间加权。

    <!--	整合ribbon	-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-ribbon</artifactId>
    </dependency>
    或者
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
    </dependency>
    使用@LoadBalanced 注解，为 RestTemplate 开启负载均衡的能力
    @SpringBootApplication
    @EnableDiscoveryClient
    public	class	MovieRibbonApplication	{
        /**
            *	实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
            *	@return	restTemplate
            */
        @Bean
        @LoadBalanced
        public	RestTemplate	restTemplate()	{
                return	new	RestTemplate();
        }
        public	static	void	main(String[]	args)	{
                SpringApplication.run(MovieRibbonApplication.class,	args);
        }
    }

    @Service
    public	class	RibbonService	{
        @Autowired
        private	RestTemplate	restTemplate;
        public	User	findById(Long	id)	{
            //	http://服务提供者的serviceId/url
            return	this.restTemplate.getForObject("http://microservice-provider-user/"	+	id,	User.class);
        }
    }
    @RestController
    public	class	RibbonController	{
        @Autowired
        private	RibbonService	ribbonService;
        @GetMapping("/ribbon/{id}")
        public	User	findById(@PathVariable	Long	id)	{
                return	this.ribbonService.findById(id);
        }
    }
    
    server:
            port:	8010
    spring:
            application:
                    name:	microservice-consumer-movie-ribbon
    eureka:
            client:
                    serviceUrl:
                            defaultZone:	http://discovery:8761/eureka/
            instance:
                    preferIpAddress:	true


##ribbon 常用配置:
    1 禁用 Eureka
    当 我们在 RestTemplate 上添加 ＠LoadBalanced 注解后，就可 以用服务名 称来调用接口
    了 ， 当有多个服务 的时候，还能做负载均衡。 这是因为 Eur eka 中的服务信息 已 经被拉取到
    了客户端本地 ， 如果我们不想和 Eureka 集成， 可以通过下面的配置方法将其禁用 。
    ＃ 禁用 Eureka
    ribbon.Eureka.enabled=false

    2 当 我们禁用 了 Eureka 之后，就不能使用服务名称去调用接口了 ， 必须指定服务地址。
    禁用之后就需要手动配置调 用的服务地址了， 配置如下：
    ＃ 禁用 Eureka 后手动配置服务地址
    fsh-house.ribbon.listOfServers=localhost:8081,localhost:8083
    这个配置是针对具体服务的，前缀就是服务名称 ， 配置完之后就可以和之前一样使用服务名称来调用接口了

    3 配置负载均衡策略
    ＃ 配置负载均衡策略
    fsh-house.ribbon.NFLoadBalancerRuleClassName=com.netfl工x.loadbalancer.RandomRule

    4 超时时间
    Ribbon 中有两种时间相关的设置， 分别是请求连接的超时时间和请求处理的超时时间，设置规则如下：
    ＃ 请求连接的超时时间
    ribbon.connectTimeout=2000
    ＃ 请求处理的超时时间
    ribbon.readTimeout=5OOO
    
    
##一个疑问：
为什么在 RestTemplate 上加了一个＠LoadBalanced 之后，RestTemplate 就能够跟 Eureka 结合了，可以使用服务名称去调用接口，还可以负载均衡？

    这功劳应归于 Spring Cloud 给我们做了大量的底层工作，因为它将这些都封装好了，我们用起来才会那么简单。
    框架就是为了简化代码，提高效率而产生的 。
    主要的逻辑就是给 RestTemplate 增加拦截器，在请求之前对请求的地址进行替换，或者根据具体的负载策略选择服务地址 ，然后再去调用 ，这就是＠LoadBalanced 的原理。
    
    
  