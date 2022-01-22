<p>
    <a href="#" onclick="refreshContent('springcloud')">返回目录</a>
</p>

#Spring Cloud -hystrix知识积累

##  Hystrix 后的远程调用流程
    简单的流程如下 ：
    1 ）构建 HystrixCommand 或者 Hys trixObservableCom mand 对象 。
    2 ）执行命令。
    3 ）检查是否有相同命令执行的缓存。
    4 ）检查断路器是否打开。
    5 ）检查线程池或者信号量是否被消耗完 。
    6 ）调用 HystrixObservableCommand#construct 或 HystrixCommand#run 执行被封装的远程调用逻辑 。
    7 ）计算链路的健康情况 。
    8 ）在命令执行失败时获取 Fallback 逻辑 。
    9 ）返回成功的 Observable 。

## 如何开启 Hystrix
在启动类上添加＠EnableHystrix 或者＠EnableCircuitBreaker 。 注意，＠EnableHystrix中包含了＠EnableCircuitBreaker 。

## Feign 整合 Hystrix 服务容错 处理的方式
 
    Fallback 方式
        在Feign 的客户端类 上的 ＠FeignClient 注解 中指 定 fallback 进行回退
        不过是实现的类中要继承这个FeignClient类同时实现里面的方法，方法内容就是当对应的服务是挂的时候的处理逻辑，
        相比不和Feign融合，和Feign融合后配置的异常处理时机相对靠后一点点。
        一般使用这种方式都是构造一个没有值值的对象返回去，这样可以确保走到这里服务是没挂掉的，这时可以记录日志下来，后再进行处理
        
       使用这个方式时我们就不关注异常的原因了，不管异常原因是啥我们都是直接执行我们预设置的逻辑 ，
       但是对于一个开发来说这明显是不可能的，有问题不记录，不处理是不对，这是我们就用到了第二种的处理方式
    
    FallbackFactory 方式
        使用就是在＠FeignClient 中用 fallbackFactory 指定回退处理类 
        这个工厂类FallbackFactory实现 FallbackFactory

##Hystrix 监控 如何使用 （单节点监控）

    在微服务架构中， Hystrix 除了实现容错外，还提供了 实 时监控功能。 
    在服务调用时，Hystrix 会实时累积关于 HystrixCommand 的执行信息，比如每秒的请求数、 成功数等

看官方文档地址：https://github.com/Netflix/Hystrix/wiki/Metrics-and-Monitoring


###pom必备条件：
     <!--健康检查-->
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-actuator</artifactId>
     </dependency>
     <!--Hystrix 服务容锚处理 -->
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
     </dependency>
 
 ##demo：
 这样调用一下cal!Hello 接口 http://localhost:8082/substitution/cal!Hello ,
 访问之后就可以看到 http://localhost:8082/hystrix.stream 这个页面中输出的数据了
 
 ## 不满足使用 hystrix.stream 俩查看监控数据时  喜欢使用图形话就可以使用监控面板了  （单节点监控）
     添加对应的依赖
     <!--整合 Dashboard 查看监控数据-->
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
     </dependency>
     
    之后在启动类上添加 ＠EnableHystrixDashboard 注解
    
    启动服务，访问 http://ip:port/hystrix 可以看到 dashboard 的主页
    主页中有 3 个地方需要我们填写，
    第一行是监控的 stream 地址，也就是将之前文字监控信息的地址（http://localhost:8082/hystrix.stream）输入到第一个文本框中 。
    第二行的 Delay 是时间，表示用多少毫秒同步一次监控信息， 
    第三个地方 Title 是标题，这个可以随便填写，
 
## 聚合监控工具Turbine （简单描述）
     Turbine 是聚合服务器发送事件流数据的一个工具。 Hystrix 只能监控单个节点，然后通
     过 dashboard 进行展示 。 实际生产中都为集群，这个时候我们可以通过 Turb ine 来监控集群
     下 Hystrix 的 metrics 情况，通过 Eureka 来发现 Hystrix 服务
 
 ###POM
     <dependency> 
        <groupId>org.springframework.cloud</groupId>
        <artifactid>spring-cloud-starter-turbine</artifactid>
     </dependency> 
     启动类上增加＠Enable Turbine 和＠EnableDiscoveryClient
     
 ###属性文件中配置如下内容：
     
     eureka.client.serviceUrl.defaultZone=http://yinjihuan:123456@master:B761/eureka/ 
     turbine.appConfig=fsh-substitution,fsh-house 
     turbine.aggregator.clusterConfig=default 
     turbine.clusterNameExpression=new  String ("default")
     
      其中 ：
      •  turbine.appConfig ：配置需要聚合的服务名称 。
      •  turbine.aggregator.clusterConfig:  Turbine 需要聚合的集群名称
      •  turbine.clusterNameExpression ：集群名表达式。
    重启服务，就可以使用 http://localhost:90 ! ! /turbine.stream 来访问集群的监控数据了 。
    Turbine 会通过在 Eureka 中查找服务的 homePageUrl 加上 hystrix.stream 来获取其他服务的
    监控数据，并将其汇总显示  

###context-path 导致监控失败
    如果被监控的服务中设置了 context-path ，则会导致 Turbine 无法获 取监控数据
    
    这个时候需要在 Turbine 中指定 turbine.instanceUr!Suffix 来解决这个问题：
    turbine .instanceUrlSuffix=/sub/hystrix.stream 
    sub 用于监控服务的 context-path 。 上面这种方式是全局配置，会有一个问题，就是一
    般我们在使用中会用 一个集群去监控多个服务，如果每个服务的 context-path 都不一样，
    这个时候有一些就会出问题，那么就需要对每个服务做一个集群，然后配置集群对应的
    context-path: 
    turbine.instanceUrlSuffix. 集群名称＝／sub/hystrix.stream
    


==========================================Hystrix熔断器=========================================

Hystrix：

##在 Spring Cloud 中使用 Hystrix

    <!--	整合hystrix	-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-hystrix</artifactId>
    </dependency>
    或者是
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
    
##使用@EnableCircuitBreaker注解开启断路器功能

    /**
        *	使用@EnableCircuitBreaker注解开启断路器功能  
            在启动类上添加＠EnableHystrix 或者＠EnableCircuitBreaker 。
            注意，＠EnableHystrix中包含了＠EnableCircuitBreaker 。
        *	@author	eacdy
        */
    @SpringBootApplication
    @EnableDiscoveryClient
    @EnableCircuitBreaker
    public	class	MovieRibbonHystrixApplication	{
        /**
            *	实例化RestTemplate，通过@LoadBalanced 注解开启均衡负载能力.
            *	@return	restTemplate
            */
        @Bean
        @LoadBalanced
        public	RestTemplate	restTemplate()	{
            return	new	RestTemplate();
        }
        public	static	void	main(String[]	args)	{
            SpringApplication.run(MovieRibbonHystrixApplication.class,	args);
        }
    }
    
##使用@HystrixCommand 注解指定当该方法发生异常时调用的方法    
    @Service
    public	class	RibbonHystrixService	{
        @Autowired
        private	RestTemplate	restTemplate;
        private	static	final	Logger	LOGGER	=	LoggerFactory.getLogger(RibbonHystrixService.class);
        /**
            *	使用@HystrixCommand注解指定当该方法发生异常时调用的方法
            *	@param	id	id
            *	@return	通过id查询到的用户
            */
        @HystrixCommand(fallbackMethod	=	"fallback")
        @GetMapping (”/findById”) 
        public	User	findById(Long	id)	{
                return	this.restTemplate.getForObject("http://microservice-provider-user/"	+	id,	User.class);
        }
        /**
            *	hystrix	fallback方法
            *	@param	id	id
            *	@return	默认的用户
            */
        public	User	fallback(Long	id)	{
                RibbonHystrixService.LOGGER.info("异常发生，进入fallback方法，接收的参数：id	=	{}",	id);
                User	user	=	new	User();
                user.setId(-1L);
                user.setUsername("default	username");
                user.setAge(0);
                return	user;
        }
    }
    
    
##配置描述：
        HystrixCommand 中除了 fallbackMethod 还有很多的配置，下面我们来看看这些配置：
        ?  hystrix.command.default.execution.isolation.strategy ：
            该配置用来指定隔离策略，具体策略有下面 2 种 。
                ?  THREAD ：线程隔离，在单独的线程上执行，并发请求受线程池大小的控制 。
                ?  SEMAPHORE ：信号量隔离，在调用线程上执行，并发请求受信号量计数器的限制 。
        ?  hystrix.command.default.execution.isolation.thread.timeoutlnMilliseconds ： 
            该配置用于 Hystrix Command 执行的超时时间设置，当 HystrixCommand 执行的时间超过了该
            配置所设置的数值后就会进入服务降级处理，单位是毫秒，默认值为 1000 。
        ?  hystrix.command.default.execution.timeout.enabled ： 
            该配置用于确定是否启用 execution.isolation.thread.timeoutlnMilliseconds 设置的超时时间，默认值为 true 。 设
            置为 false 后 exe cution.isolation.thread.timeoutinMilliseconds 配置也将失效。
        ?  hystrix.command.default.execution.iso la ti on.thread.interruptOn Timeout ： 
            该配置用于确定 HystrixCommand 执行超时后是否需要中断它，默认值为 true 。
        ?  hystrix.command.default.execution.isolation.thread.interruptOnCancel ：
            该配置用于确定 HystrixCommand 执行被取消时是否需要中断它，默认值为 fa lse 。
        ?  hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests ： 
            该配置用于确定 Hystrix 使用信号量策略时最大的并发请求数。
        ?  hystrix.command.default.fa] !back.isolation.semaphore.max Concurren tRequests ： 
            该配置用于如果并发数达到该设置值，请求会被拒绝和抛出异常并且 fall back 不会被调用，默认值为 10 。
        ?  hystrix.command.default.fal !back.enabled ： 
            该配置用于确定当执行失败或者请求被拒绝时，是否会尝试调用 hystrixCommand.getFallback （），默认值为 true 。
        ?  hystrix.command.default.circuitBreaker.enabled ： 
            该配置用来跟踪 circuit 的健康性，如果未达标则让 request 短路，默认值为 true 。
        ?  hystrix.command.default.circuitBreaker.requestVolumeThreshold ：
            该配 置用于设置一个 rolling window 内 最小的请求数。 如果设为 20 ，那么 当一个 rolling window 的时－
            间内（比如说 l 个 rolling window 是 10 秒）收到 19 个请求， 即使 19 个请求都失败，也不会触发 circuit break ， 默认值为 20 。
        ?  hystrix.command.default.circuitBreak巳r.sleep WindowlnMilliseconds ： 
            该配置用于设置一个触发短路的时间值，当该值设为 5000 时，则 当触发 circuit break 后的 5000 毫秒
            内都会拒绝 request ，也就是 5000 毫秒后才会关闭 circuit 。 默认值为 5000 。
        ?  hystrix.command.default.circ 山tBreaker.errorThresholdPercentage ： 
            该配置用于设置错误率阔值，当错误率超过此值时，所有请求都会触发 fa ll back ，默认值为 50 。
        ?  hystrix.command.default.circuitBreaker.forceOpen ：
            如果配置为 true ，将强制打开熔断器，在这个状态下将拒绝所有请求，默认值为 false 。
        ?  hystrix.command.default.circuitBreaker.forceClosed ：
            如果配置为 true ，则将强制关闭熔断器，在这个状态下，不管错误率有多高，都允许请求，默认值为 false 。
        ?  hystrix.command.default.metrics.rollingStats.timelnMilliseconds ： 
            设置统计的时间窗口值，单位为毫秒。 circuit break 的打开会根据 1 个 rolling window 的统计来计算。
            若 rolling window 被设为 10 000 毫秒 ， 则 rolling window 会被分成多个 buckets ，每
            个 bucket 包含 success 、 failure 、 timeout 、 rection 的次数的统计信息。 默 认值为10000 毫秒。
        ?  hystrix.command.defau l t.metrics.rol l ingStats.numBuckets ：
            设置 一 个 rolling window 被划分的数量，若 numBuckets=l 0 、 rolling window= 10  000 ，那么一个 bucket 的时
            间即 l 秒。 必须符合 rolling window % numberBuckets == 0 。 默认值为 10 。
        ?  hystrix.command.default.metrics.rollingPercentile.enabled ： 
            是否开启指标的计算和跟踪，默认值为 true 。
        ?  hystrix.command.default.metrics.rollingPercentile.timelnMilliseconds ：
            设置 rolling percentile window 的时间， 默认值为 60 000 毫秒。
        ?  hystrix.command.default.metrics.rollingPercentile.numBuckets ：
            设置 rolling percentile window 的 numberBuckets ，默认值为 6 。
        ?  hystrix.cornrnand.defauIt.metrics.rollingPercentile.bucketSize ：
            如果 bucket size= 100 、window= IO 秒，若这 10 秒里有 500 次执行，只有最后 100 次执行会被统计到 bucket
            里去 。 增加该值会增加内存开销及排序的开销 。 默认值为 100 。
        ?  hystrix.command.default.metrics.healthSnapshot.intervallnMilliseconds ：
            用来计算影响断路器状态的健康快照的间隔等待时间，默认值为 500 毫秒。
        ?  hystrix.command.default.requestCache.enabled ： 
            是否开启请求缓存功能，默认值为true 。
        ?  hystrix.cornmand.default.requestLog.enabled ：
            记录日志到 Hys trixRequestLog ， 默认值为 true 。
        ?  hystrix.collapser.default.maxRequestslnBatch ： 
            单次批处理的最大请求数，达到眩数量触发批处理， 默认为 Integer.MAX_VALUE 。
        ?  hystrix.col Japser.default.timerDelay InMilliseconds ：
            触发批处理的延迟，延迟也可以为创建批处理的时间与该值的和，默认值为 10 毫秒。
        ?  hystrix.collapser.defauIt.requestCache.enabled ：
            是否启用对 HystrixCollapser.execute()和 Hystrix Collapser.queue （）的请求缓存 ， 默认值为 true 。
        ?  hystrix.threadpool.default.coreSize ： 
            并发执行的最大线程数，默认值为 10 。
        ?  hystrix.threadpool.default.maxQueueSize  :  
            BlockingQueue 的最大队列数。 当设为 － 1时，会使用 SynchronousQueue ；值为正数时， 会使用 LinkedBlcokingQueue 。 该设
            置只会在初始化时有效，之后不能修改 thread pool 的 queue size 。 默认值为一 1 。
        ?  hystrix.threadpool.default.queueSizeRejectionThreshold ：
            即使没有达到 maxQueueSize,但若达到 queueSizeRejectionThreshold 该值后，请求也会被拒绝。 因为 maxQueueSize
            不能被动态修改，而 queueSizeRejectionThreshold 参数将允许我们动态设置该值。 ifmaxQueueSize == -1 ， 该字段将不起作用 。
        ?  hystrix.threadpool.defauIt.keepAliveTimeMinutes ：
            设置存活时间，单位为分钟 。 如果coreSize 小于 maximumSize ，那么该属性控制一个线程从实用完成到被释放的时间 。
            默认值为 1 分钟。
        ?  hystrix.threadpoo I.defauIt.allow MaximumsizeToDivergeFrom Coresize ：
            该属性允许maximumSize 的配置生效。 那么该值可以 等于或高于 c oreSize 。 设置 coreSize 小 于
            max imum  Size 会创建一个线程池， 该线程池可 以 支持 maxi mumSize 并发，但在相对
            不活动期 间将 向系统返回线程。 默认值为 false 。
        ?  hystrix.thread pool.default.metrics.rollings tats.tim 巳InMilliseconds ： 
            设置滚动时间 窗 的时间 ，单位为毫秒， 默认值是 10 000 。
        ?  hystrix.threadpool.d巳fault.metrics.rollingStats.numBuckets ：
            设置滚动时间 窗划分桶的数量，默认值为 10 。

        官方的配置信息文档请参考 ： https://github.com/Netflix/Hystrix/wiki/Configuration 。


##Hystrix 监控
    Hystrix 监控需要两个必备条件
    maven pom.xml
        <!-- 必须有 Actuator 的依赖 -->
        <dependency> 
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency> 
        <!-- 有 Hystrix 的依赖，Spring Cloud 中必须在启动类中添加＠EnableHystrix 开启Hystrix  -->
        <dependency> 
            <groupId>org.springframework.cloud</groupid>
            <a rtifactId >spring-cloud-starter-hystrix</artifactId>
        </dependency> 

