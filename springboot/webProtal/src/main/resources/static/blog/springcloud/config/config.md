<p>
    <a href="#" onclick="refreshContent('springcloud')">返回目录</a>
</p>

#Spring Cloud -config知识积累

在配置中心的基础应用案例中，将会包括两个部分 ： 配置服务器和配置客户端。

    Config Server即 配置服务器，
        为配置客户端提供其对应的配置信息 ， 配置信息的来源为配置仓库，启动时需要拉取配置仓库的信息， 缓存到本地仓库中； 
    Config Client 即 配置客户端，
        只会在本地配置必要的信息，如指定获取配置的 Config Server地址，启动时从配置服务器获取配置信息，并支持动态刷新配置仓库中的属性值。


配置仓库

    一般是用git的一种仓库，可以是私有的也可以是公开的，  私有的需要多配置一个对应的仓库访问用户密码
    
    
    配置规则    
        /{application}/{profile}[/{label}]
        /{application}-{profile}.yml
        /{label}/{application}-{profile}.yml 
        /{application}-(profile}.properties
        /{label}/{application}-{profile}.properties 

    {application}对应客户端的应用名 spring.application.name,  
    {profile}对应不同的 profile 值 spring.cloud.config.profile,  
    {label}对应配 置 仓库的分支 spring.cloud.config.label，默认为 master 



配置客户端

    <dependency>
        <groupid>org.springframework.cloud</groupid> 
        <artifactid>spring-cloud-starter-config</artifactid>
    </dependency>


配置服务端

    <dependency> 
        <groupid>org.springframework.cloud</groupid> 
        <artifactid>spring-cloud-config-server</artifactid> 
    </dependency> 


    入口类添加 ＠EnableConfigServer 注解，启用 Config Server 
    
     配置动态更新
     需要引人 actuator 监控模块，用于动态刷新相关的配置信息，如下所示：
        <dependency> 
        <groupid>org.springframework-boot</groupid> 
        <artifactid>spring-boot-starter-actuator< /artifactid>
        </dependency> 

     开启更新机制
        还需要给加载变量 的 类标注＠RefreshScop 巳，使用该注解 的类 ，在客户端执行 /refresh
      的时候就会更新此类的变量值，如下所示 ：
        @RestController 
        @RequestMapping ("/cloud") 
        @RefreshScope 
        public  class  TestController  { 
            @Value ("${cloud.version}") 
            private  String version; 
            @Get Mapping("/version")
            public  String  getVersion()  { 
                return  version , 
            }
        )    
