<p>
<a href="#" onclick="refreshContent('springboot')">返回</a>
</p>

---

## SpringBoot 常见问题

Java finally语句到底是在return之前还是之后执行？

        有两种情况下finally语句是不会被执行的：
        （1）try语句没有被执行到，如在try语句之前就返回了，这样finally语句就不会执行，这也说明了finally语句被执行的必要而非充分条件是：相应的try语句一定被执行到。
        
        （2）在try块中有System.exit(0);这样的语句，System.exit(0);是终止Java虚拟机JVM的，连JVM都停止了，所有都结束了，当然finally语句也不会被执行到。
        
        
        finally语句是在try的return语句执行之后，return返回之前执行
        finally块中的return语句会覆盖try块中的return返回。
        如果finally语句中没有return语句覆盖返回值，那么原来的返回值可能因为finally里的修改而改变也可能不变。
        try块里的return语句在异常的情况下不会被执行，这样具体返回哪个看情况。
        当发生异常后，catch中的return执行情况与未发生异常时try中return的执行情况完全一样。

两个SpringBoot项目之间调用

- <a href="https://www.jianshu.com/p/a903557a6c64#" target="_blank">https://www.jianshu.com/p/a903557a6c64</a>

接口服务重复调用的触发原因：

        1、客户端接口和服务端接口没有一一对齐
        2、客户端请求服务端超时，触发重复请求条件
        3、传输参数过大，导致服务端返回的客户端的时候出现问题
        4、返回的数据没有进行序列化，导致结果不能返回到客户端
        5、使用公共方法不当

使用dubbo管理服务时 可以配置默认超时重连机制

	    超时时间和超时后重试的次数

springmvc+fastjson返回页面出现乱码问题

        在controller里面的接口上面加,produces="text/html;charset=UTF-8"即可
        @RequestMapping(value = "/addLjlUsrServiceManInfoOne" ,produces="text/html;charset=UTF-8")	

SpringBoot Controller接收参数的几种常用方式

        第一类：请求路径参数
            1、@PathVariable
            获取路径参数。即url/{id}这种形式。
    
            2、@RequestParam
            获取查询参数。即url?name=这种形式
            
        第二类：Body参数
            @RequestBody
            
        第三类：请求头参数以及Cookie    
            1、@RequestHeader
            2、@CookieValue	

配置文件的读取

            Spring Boot 中的配置通常放在 application.properties 中，读取配
            置信息是非常方便的，总共分为 3 种方式 ：
            ( I )  Environment  ：可以通过 Environment 的 getProperty 方法来获取想要 的配置信息，12 令 第一部分准备篇
                ／／ 读取配置
                    String  port  =  env.getProperty(”server.port”}; 
        
            ( 2) @Value ：可以注入具体的配置信息，
        
                ／／ 注入配置
                    @Value (”${ server.port}”) 
                    private  String  port; 
            ( 3 ）自定义配置类： prefix 定义配置的前缀 
        
            定义配置 application.properties 的方法如下：
            corn.cxytiandi.name=yinjihuan 

                @ConfigurationProperties(prefix ＝” com.cxytiandi”）
                @Component 
                public  class  MyConfig  { 
                    private  String  name; 
                    public  String  getName()  { 
                        return  name; 
                    }
                    public  void  setName(String 口出ne) { 
                        this . name  =  name; 
                    }
                }
                
            读取配置的方法如代码
        
                @RestController 
                public  class  HelloController  { 
                    @Autowired 
                    private  MyConfig  rnyConfig; 
        
                    @GetMapping("/hello”) 
                    public  String  hello()  { 
                        return  rnyConfig.getNane();
                    }
                }