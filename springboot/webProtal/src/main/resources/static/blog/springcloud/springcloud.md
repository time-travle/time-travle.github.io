#Spring Cloud 知识积累
<a href="#" onclick="refreshSpringCloudContent('bus')">SpringCloud（bus）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringCloudContent('config')">SpringCloud（config）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringCloudContent('eureka')">SpringCloud（eureka）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringCloudContent('fegin')">SpringCloud（fegin）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringCloudContent('hystrix')">SpringCloud（hystrix）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringCloudContent('ribbon')">SpringCloud（ribbon）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringCloudContent('zuul')">SpringCloud（zuul）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringCloudContent('better')">SpringCloud（调优）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringCloudContent('other')">SpringCloud（其他）</a>&emsp;&emsp;&emsp;

---

###Spring Cloud 模块相关介绍如下：

    •  Eureka ：服务注册中心，用于服务管理。  
            除了用 Eureka 作为注册中心外，我们还可以使用 Consul 、 Etcd 、 Zookeeper 等来作为服务的注册中心
    •  Ribbon ：基于客户端的负载均衡组件。
    •  Hystrix ：容错框架，能够防止服务的雪崩效应 。
    •  Feign:  Web 服务客户端，能够简化 Http 接口的调用 。
    •  Zuul:  API 网关 ，提供路由转发、请求过滤等功能 。
    •  Config ： 分布式配置管理。
    •  Sleuth ：服务跟踪 。
    •  Stream ：构建消息驱动的微服务应用程序的框架 。
    •  Bus ：消息代理的集群消息总线。

====================================================================================================
注册中 心带来的好处就是你不需要知道有多少提供方，你只需要关注注册中心即可，

###为什么 Eureka 比 Zookeeper 更适合作为注册中心呢？
    主要是因为 Eureka 是基于 AP 原则构建的，而 ZooKeeper 是基于 CP 原则构建的 。 
    在分布式系统领域有个著名的 CAP 定理，
        即 C 为数据一致性； A 为服务可用性； P 为服务对网络分区故障的容错性。 
    这三个特性在任何分布式系统中都不能同时满足，最多同时满足两个
   
    Zookeeper 有 一个 Leader ，而且在 Leader 无法使用的时候通过 Paxos (  ZAB ） 算法选
    举出一个新的 Leader 。 这个 Leader 的任务就是保证写数据的时候只向这个 Leader 写人 ，
    Leader 会同步信息到其他节点 。 通过这个操作就可以保证数据的一致性。
    总而言之，想要保证 AP 就要用 Eureka ， 想要保证 CP 就要用 Zookeeper 。 Dubbo 中大
    部分都是基于 Zookeeper 作为注册中心的 。 Spring Cloud 中 当然首选 Eureka 。



###微服务的优缺点
    1、易于开发和维护
    2、启动较快
    3、局部修改容易部署
    4、技术栈不受限
    5、按需伸缩
    6、DevOps

###服务治理框架
    （1）Dubbo（http://dubbo.io/）、Dubbox（当当网对Dubbo的扩展）
    （2）Netflix的Eureka、Apache的Consul等。

Spring Cloud Eureka是对Netflix的Eureka的进一步封装。


===============================================注解===============================================
@EnableEurekaServer 开启eureka服务  在主函数上

@EnableEurekaClient 表示发布一个服务 在主函数上

@EnableDiscoveryClient 向注册中心上注册服务 在主函数上

    它俩的区别是@EnableEurekaClient 只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心

@LoadBalanced 开启ribbon负载均衡器  在主函数上

@EnableFeignClients //开启feign权限  @FeignClient(name = "app-buba-member")

@EnableHystrix 开启Hystrix@HystrixCommand(fallbackMethod = "orderToUserInfoFallback")  熔断机制  在主函数上或方法上

@EnableConfigServer 开启config-server   @RefreshScope刷新注解  在主函数上

@EnableZuulProxy 开启注册网关 在主函数上



@Controller 控制层，里面有多个连接

@Service 业务层，一般对于接口和实现

@Qualifier 如果一个接口有多个实现，那么注入时候加上唯一标示

@Repository 一般的dao层

@Autowired 自动注入依赖

@Resource bean的注入，同Autowired 有相同的功能。

    说明：
        共同点：@Resource和@Autowired都可以作为注入属性的修饰，在接口仅有单一实现类时，两个注解的修饰效果相同，可以互相替换，不影响使用。
        不同点：
        @Resource是Java自己的注解，@Resource有两个属性是比较重要的，分是name和type；Spring将@Resource注解的name属性解析为bean的名字，
            而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。
            如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。
        @Autowired是spring的注解，是spring2.5版本引入的，Autowired只根据type进行注入，不会去匹配name。
            如果涉及到type无法辨别注入对象时，那需要依赖@Qualifier或@Primary注解一起来修饰。


@Component 定义其它组件（比如访问外部服务的组件）

@RequestMapping （value=’’,method={RequestMethod。GET或者POSt}）绑定url

@RequestParam (value=’’ required=false)绑定参数,将客户端请求中的参数值映射到相应方法的参数上;

@ModelAttribute  一般用于controller层，呗注解的方法会在所以mapping执行之前执行，并且可以绑定参数到Model model里面。

@Transactional （readOnly=true）注解式事务

@TransactionalEventListener 用于配置事务的回调方法，可以在事务提交前、提交后、完成后以及回滚后几个阶段接受回调事件。

@Value（“${}”）可以注入properties里面的配置项

@ControllerAdvice 是spring3提供的新注解

@ExceptionHandler 如果在controller方法遇到异常，就会调用含有此注解的方法。

@InitBinder 一般用于controller 可以将所以form 讲所有传递进来的string 进行html编码，防止xss攻击，比如可以将字符串类型的日期转换成date类型

@EnableCaching 注解自动化配置合适的缓存管理器。

@EnableWebSecurity 注解开启spring security的功能，集成websercrityconfigureadapter。

@SringBootApplication 相当于@configuration，@EnableAutoConfiguation @ComponentScan三个注解合用。

@EnableDiscoveryclient 注册应用为Eureka客户端应用，以获得服务发现的能力

@EnableAdminServer 使用admin监控应用。

@EnableEurekaClient 配置本应用将使用服务注册和服务发现，注意：注册和发现用这个注解。

@EnableEurekaServer 启动一个服务注册中心

@EnableHystrix 表示启动断路器，断路器依赖于服务注册和发现。

@HystrixCommand 注解方法失败后，系统将西东切换到fallbackMethod方法执行。指定回调方法

@EnableAutoConfiguration spring boot自动配置，尝试根据你添加的jar依赖自动配置你的spring应用。

@ComponentScan 表示将该类自动发现并注册bean 可以自动收集所有的spring组件

@Comfiguration 相当于传统的xml配置文件

@Import 导入其他配置类

@ImportResource 用来 加载xml配置文件

@FeignClient 注解中的fallbank属性指定回调类

@RestController 返回json字符串的数据，直接可以编写RESTFul的接口;

@CrossOrigin 可以处理跨域请求，让你能访问不是一个域的文件;

@ApiOperation 首先@ApiOperation注解不是Spring自带的，它是是swagger里的注解@ApiOperation是用来构建Api文档的@ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”；

@SpringBootApplication　　申明让spring boot自动给程序进行必要的配置，等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan;

@RefreshScope　如果代码中需要动态刷新配置，在需要的类上加上该注解就行。但某些复杂的注入场景下，这个注解使用不当，配置可能仍然不动态刷新;

@FeignClient　springboot调用外部接口:声明接口之后，在代码中通过@Resource注入之后即可使用。
    
    FeignClient标签的常用属性如下：
    
    name：指定FeignClient的名称，如果项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
    url: url一般用于调试，可以手动指定@FeignClient调用的地址decode404:当发生http 404错误时，如果该字段位true，会调用decoder进行解码，否则抛出FeignException
    configuration: 
        Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contractfallback: 
        定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口
    fallbackFactory: 工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码path: 定义当前FeignClient的统一前缀

@EnableFeignClients 开启Spring Cloud Feign的支持

@EnableCircuitBreaker 开启断路器功能

@LoadBalanced 开启客户端负载均衡

@WebAppConfiguration 开启Web 应用的配置，用于模拟ServletContext

@RibbonClient，这个注解用来为负载均衡客户端做一些自定义的配置，可以进一步配置或自定义从哪里获取服务端列表、负载均衡策略、Ping也就是服务鉴活策略等等


===============================================maven===============================================

##maven 部分

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Dalston.SR2</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
===============================================参考  wiki=============================================

https://blog.csdn.net/varyall/article/details/82085313
https://how2j.cn/k/springcloud/springcloud-intro/2035.html




  
    
