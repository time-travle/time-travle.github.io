<p>
    <a href="#" onclick="refreshContent('springcloud')">返回目录</a>
</p>

#Spring Cloud -fegin 知识积累

##Q1：如何配置搭建使用Feign 

参考wiki：

https://www.cnblogs.com/feifuzeng/p/13613732.html

https://www.jianshu.com/p/8bca50cb11d8

http://c.biancheng.net/view/5357.html


##Q2 Feign 和 Ribbon 的区别
Ribbon：

    是一个基于 HTTP 和 TCP 客户端 的负载均衡的工具。
    它可以 在客户端 配置 RibbonServerList（服务端列表），使用 HttpClient 或 RestTemplate 模拟http请求，步骤相当繁琐。

Feign：

    Feign 是在 Ribbon的基础上进行了一次改进，是一个使用起来更加方便的 HTTP 客户端。
    采用接口的方式， 只需要创建一个接口，然后在上面添加注解即可 ，将需要调用的其他服务的方法定义成抽象方法即可， 不需要自己构建http请求。
    然后就像是调用自身工程的方法调用，而感觉不到是调用远程方法，使得编写 客户端变得非常容易。

代码方面的用法区别：

    Ribbon：用 REST_URL_PREFIX 指定请求地址 ， 使用 restTemplate 模拟 http 请求。也就是说需要自己来构建发起HTTP请求
    Feign：
        a) 新建一个接口，添加@FeignClient 注解，指定微服务名称 MICROSERVICECLOUD-DEPT
        b) 指定请求地址 @RequestMapping

参考：

https://www.cnblogs.com/tongxuping/p/12297964.html

https://www.cnblogs.com/linkworld/p/9191161.html

##如何优雅的开启 Feign 对 Hystrix 的支持
    OpenFeign 是 自带 Hystrix ，但是默认没有打开
    先添加了Hystrix 的pom依赖， 然后在属性文件中开启 Feign 对 Hystrix 的支持：
        feign.hystrix.enabled=true

##如何优雅的关闭 Feign 对 Hystrix 的支持
    禁用 Hystrix 还是 比较简单的，目前有两种方式可以禁用 ， 其中一种是在属性文件中进行全部禁用 。
    feign.hystrix.enabled=false 
    
    另一种是通过代码 的方式禁用某个客户端 ， 在 Feign 的配置类中 增加代码
        @Configuration 
        public  class  FeignConfiguration { 
            @Bean 
            @Scope ("prototype") 
            public Feign.Builder feignBuilder() { 
                return  Feign.builder(); 
            }
        }



    
==========================REST客户端 Feign=======================

JAVA 项目中接口调用

    ?  Httpclient :  HttpClient 是 Apache Jakarta Common 下的子项目，用来提供高效的 、 最
        新的、功能丰富的支持 Http 协议的客户端编程工具包，井且它支持 Http 协议最新的
        版本和建议。 HttpClient 相比传统 JDK 自带的 URLConnection ，增加了易用性和灵
        活性，使客户端发送 Http 请求变得容易，提高了开发的效率。
    ?  Okhttp ：一个处理网络请求的开源项目，是安卓端最火的轻量级框架，由 Square 公
        司贡献，用于替代 HttpUr!Connection 和 Apache HttpClient 。 OkHttp 有简洁的 API 、
        高效的性能，并支持多种协议（ Http/ 2 和 SPDY ） 。
    ?  Httpurlconnection  :  HtφURLConnection 是 Java 的标准类，它继承自 URLConnection,
        可用于向指定网站发送 GET 请求、 POST 请求。 H即URLConnection 使用比较复杂，
        不像 HtψClient 那样容易使用 。
    ?  RestTemplate  :  RestTemplate 是 Spring 提供的用于访问 Rest 服务的客户端，
        RestTemplate 提供了多种便捷访问远程 Http 服务的方法，能够大大提高客户端的编
        写效率。
    比上面的更简单，方便，它就是 Feign 。
    
    
###Spring	Cloud为Feign添加了Spring MVC的注解支持，并整合了Ribbon和Eureka来为使用Feign时提供负载均衡
	<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-feign</artifactId>
    </dependency>
    
    /**
        *	使用@EnableFeignClients 开启Feign
        *	@author	eacdy
        */
    @SpringBootApplication
    @EnableFeignClients  如果你的 Feign 接口 定义跟你 的启动类不在一 个包名下，还需要制定扫描的包名＠EnableFeignClients (basePackages ＝"")
    @EnableDiscoveryClient  服务提供者 或者消费者
    public	class	MovieFeignApplication	{
        public	static	void	main(String[]	args)	{
            SpringApplication.run(MovieFeignApplication.class,	args);
        }
    }

##使用 Feign 调用接口
    1、定义一个 Feign 的 客户端， 以 接口形式存在
    @FeignClient(value  =”fsh-house ”, path  = ” / house ”) 
    public  interface  HouseRemoteClient  { 
        @GetMapping (”/hello ”} 
        String  hello(); 
    }
    ＠FeignClient 注解。这个注解标识当前是一个 Feign 的客户端，value 属性是对应的服务名称，也就是你需要调用哪个服务中的接口，path 就是接口中URI 统一的前缀
    定义方法时直接复制接口的定义即可，当然还有一种做法，就是将接口单独抽出来定义，然后在 Controller 中实现接口。
    在调用的客户端中也实现了接口，从而达到接口共用的目的 ，若是在接口类中声明就不能共用了
    
    
    
    
##Feign 整合 Hystrix 服务窑错    
    在属性文件中开启 Feign 对 Hystrix 的支持：
    feign.hystrix.enabled=true 
    
    1. Fallback 方式
    在 Feign 的 客户端类 上的 ＠FeignClient 注解 中指 定 fallback 进行回退
    客户端类 HouseRemoteClient ，为其配置 fallback 。
        @FeignClient (value  =  "fsh-house ”, path  = ” / house ”, configuration=FeignConfiguration.class,  fallback  =  HouseRemoteClientHystrix.class) 
        public  interface  HouseRemoteClient  { 
            @GetMapping （” ／ ｛ houseId ｝ ” ）
            HouseinfoDto  hosueinfo(@PathVariable("houseid”) Long  houseid); 
        }
    HouseRemoteClientHystrix 类需要实现 HouseRemoteClient 类 中所有的方法 ，返回回退时的内容     
        @Component 
        public  class  HouseRemoteClientHystrix  implements  HouseRemoteClient  { 
            @Override 
            public  HouseInfoDto hosueinfo(Long  house 工d ){ 
                return  new  HouseinfoDto();
            }
        }
        
    2. FallbackFactory 方式
    通过 fallback 已 经可以实现服务不可用时回退的功能，如果你想知道触发回退的原因 ，可以使用 FallbackFactory 来实现回退功能
    @Component 
    public  class  HouseRemoteClientFallbackFactory  implements FallbackFactory<HouseRemoteClient>  { 
            @Override 
            public  HouseRemoteClient  create(final  Throwable  cause)  { 
                return  new  HouseRemoteClient()  { 
                    @Override 
                    public  HouseinfoDto  hosueinfo(Long  houseid)  { 
                        HouseinfoDto  info= new  HouseinfoDto(); 
                        info.setData(new  Houseinfo(lL,””,””, cause. getMessage () ) ) ; 
                        return  info;
                }
            }
        }
    }
    
##Feign 中禁用 Hystrix    
  
    禁用 Hystrix 还是 比较简单的，目前有两种方式可以禁用 ， 其中一种是在属性文件中进行全部禁用 。
    feign.hystrix.enabled=false    