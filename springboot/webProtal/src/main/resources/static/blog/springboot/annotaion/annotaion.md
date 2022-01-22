#注解 Annotaion
<a href="#" onclick="refreshContent('springboot')">返回</a>

---

===============================================注解===============================================

    @Controller：用于定义控制器类，在spring 项目中由控制器负责将用户发来的URL请求转发到对应的服务接口（service层），
                一般这个注解在类中，通常方法需要配合注解@RequestMapping  作用于表现层（spring-mvc的注解）
    @RestController：用于标注控制层组件(如struts中的action)，@ResponseBody和@Controller的合集            
    @RequestMapping(value = "/user", produces = {"application/json;charset=UTF-8"}) 提供路由信息，负责URL到Controller中的具体函数的映射
    @ResponseBody：表示该方法的返回结果直接写入HTTP response body中，一般在异步获取数据时使用，用于构建RESTful的api。
                    在使用@RequestMapping后，返回值通常解析为跳转路径，加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。
                    比如异步获取json数据，加上@responsebody后，会直接返回json数据。该注解一般会配合@RequestMapping一起使用
    @PathVariable   获取路径参数。即url/{id}这种形式
    @Service(value = "userService")    一般用于修饰service层的组件 作用于业务逻辑层
    @RequestParam   获取查询参数。即url?name=这种形式
    @SpringBootApplication：包含了@ComponentScan、@Configuration和@EnableAutoConfiguration注解。
                            其中@ComponentScan让spring Boot扫描到Configuration类并把它加入到程序上下文。
    @RestController 注解是@Controller和@ResponseBody的合集,表示这是个控制器bean,并且是将函数的返回值直 接填入HTTP响应体中,是REST风格的控制器。
    @JsonBackReference 解决嵌套外链问题。
    @RepositoryRestResourcepublic 配合spring-boot-starter-data-rest使用。
    @EnableAutoConfiguration：Spring Boot自动配置（auto-configuration）：尝试根据你添加的jar依赖自动配置你的Spring应用。
                            例如，如果你的classpath下存在HSQLDB，并且你没有手动配置任何数据库连接beans，那么我们将自动配置一个内存型（in-memory）数据库”。
                            你可以将@EnableAutoConfiguration或者@SpringBootApplication注解添加到一个@Configuration类上来选择自动配置。
                            如果发现应用了你不想要的特定自动配置类，你可以使用@EnableAutoConfiguration注解的排除属性来禁用它们。
    @ComponentScan：表示将该类自动发现扫描组件。个人理解相当于，如果扫描到有@Component、@Controller、@Service等这些注解的类，并注册为Bean，
                    可以自动收集所有的Spring组件，包括@Configuration类。我们经常使用@ComponentScan注解搜索beans，并结合@Autowired注解导入。
                    如果没有配置的话，Spring Boot会扫描启动类所在包下以及子包下的使用了@Service,@Repository等注解的类。
    @Configuration：相当于传统的xml配置文件，如果有些第三方库需要用到xml文件，等同于spring的XML配置文件；使用Java代码可以检查类型安全。
                    建议仍然通过@Configuration类作为项目的配置主类——可以使用@ImportResource注解加载xml配置文件。
    @Conditional： 设置自动配置条件依赖
    @Import：用来导入其他配置类。
    @ImportResource：用来加载xml配置文件。
    @Repository：使用@Repository注解可以确保DAO或者repositories提供异常转译，这个注解修饰的DAO或者repositories类会被ComponetScan发现并配置，
                    同时也不需要为它们提供XML配置项。
    @Value：注入Spring boot application.properties配置的属性的值
    @Inject：等价于默认的@Autowired，只是没有required属性；
    @Component：泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
                可配合CommandLineRunner使用，在程序启动后执行一些基础任务。
    @Bean:相当于XML中的,放在方法的上面，而不是类，意思是产生一个bean,并交给spring管理。用@Bean标注方法等价于XML中配置的bean。
    @AutoWired：自动导入依赖的bean。byType方式。把配置好的Bean拿来用，完成属性、方法的组装，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
                当加上（required=false）时，就算找不到bean也不报错。
    @Qualifier：当有多个同一类型的Bean时，可以用@Qualifier(“name”)来指定。与@Autowired配合使用。@Qualifier限定描述符除了能根据名字进行注入，
                但能进行更细粒度的控制如何选择候选者
    @Resource(name=”name”,type=”type”)：没有括号内内容的话，默认byName。与@Autowired干类似的事
    @Entity：@Table(name=”“)：表明这是一个实体类。一般用于jpa这两个注解一般一块使用，但是如果表名和实体类名相同的话，@Table可以省略
    @MappedSuperClass:用在确定是父类的entity上。父类的属性子类可以继承。
    @NoRepositoryBean:一般用作父类的repository，有这个注解，spring不会去实例化该repository。
    @Column：如果字段名与列名相同，则可以省略。
    @Id：表示该属性为主键。
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = “repair_seq”)：表示主键生成策略是sequence（可以为Auto、IDENTITY、native等，
                Auto表示可在多个数据库间切换），指定sequence的名字是repair_seq。
    @SequenceGeneretor(name = “repair_seq”, sequenceName = “seq_repair”, allocationSize = 1)：
                name为sequence的名称，以便使用，sequenceName为数据库的sequence名称，两个名称可以一致。
    @Transient：表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性。如果一个属性并非数据库表的字段映射,就务必将其标示为@Transient,
            否则,ORM框架默认其注解为@Basic。@Basic(fetch=FetchType.LAZY)：标记可以指定实体属性的加载方式
    @JsonIgnore：作用是json序列化时将Java bean中的一些属性忽略掉,序列化和反序列化都受影响。
    @JoinColumn（name=”loginId”）:一对一：本表中指向另一个表的外键。一对多：另一个表指向本表的外键。
    @OneToOne、@OneToMany、@ManyToOne：对应hibernate配置文件中的一对一，一对多，多对一
    @EnableScheduling 开启Schedule定时器
    @EnableSched(cron='')定时器表达式注解
    @Scheduled 定时器表达式注解
    @EnableAsync 开启异步调用
    @Async 注解 辨识对应的代码是在线程池中进行异步进行的 直接在 Controller 中调用这个业务方法(不能在类的内部直接进行调用)，它就是异步执行的，会在默认的线程池中去执行
    
    @ConfigurationProperties(prefix = "myClass")这个是读取springboot配置文件信息
    @EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class }) 这个注解用来优先加载配置类的
            @ConfigurationProperties注解的作用是把yml或者properties配置文件转化为bean。
            @EnableConfigurationProperties注解的作用是使@ConfigurationProperties注解生效

    @MapperScan(basePackages = {"com.buba.mapper"})扫描mapper层接口,放到启动类上,参数填接口包名
    
    常用的 lombok 注解
        @EqualsAndHashCode　　　　实现equals()方法和hashCode()方法 @ToString：实现toString()方法 
        @Data 　　　　　　　　　　注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法 
        @Setter　　　　　　　　　　注解在属性上；为属性提供 setting 方法 
        @Getter　　　　　　　　　　注解在属性上；为属性提供 getting 方法 
        @Log4j 　　　　　　　　　　注解在类上；为类提供一个 属性名为log 的 log4j 日志对象 
        @NoArgsConstructor　　　　注解在类上；为类提供一个无参的构造方法 
        @AllArgsConstructor　　　　注解在类上；为类提供一个全参的构造方法 
        @Cleanup　　　　　　　　关闭流 
        @Synchronized：对象同步 
        @SneakyThrows：抛出异常
        @ConfigurationProperties　　　　把同类的配置信息自动封装成实体类:可以使属性文件中的值和类中的属性对应起来;使用方式有两种 :　　1、在类上使用该注解 　　2、在工厂方法上使用该注解 （@bean）
        注意：在springBoot中除了使用这个注解读取属性文件值外，还可以用@Value注解。
===============================================注解===============================================

 ##如果要让一个普通类交给Spring容器管理，通常有以下方法：
     1、使用 @Configuration与@Bean 注解
     2、使用@Controller @Service @Repository @Component 注解标注该类，然后启用@ComponentScan自动扫描
     3、使用@Import 方法
 
 ##依赖注入三种方式
 	依赖注入分为三种方式：
 		变量（filed）注入
 			    @Autowired
 			    UserDao userDao;
 		构造器注入
 			    UserDao userDao;
 			    @Autowired
 			    public UserServiceImpl(UserDao userDao) {
 			        this.userDao = userDao;
 			    }
 		set方法注入
 			    private UserDao userDao;
 			    @Autowired
 			    public void setUserDao (UserDao userDao) {
 			        this.userDao = userDao;
 			    }
 		原文链接：https://blog.csdn.net/zhangjingao/article/details/81094529
 		
 	优点：变量方式注入非常简洁，没有任何多余代码，非常有效的提高了java的简洁性。即使再多几个依赖一样能解决掉这个问题。
 	
 	缺点：不能有效的指明依赖。相信很多人都遇见过一个bug，依赖注入的对象为null，在启动依赖容器时遇到这个问题都是配置的依赖注入少了一个注解什么的，
 	        然而这种方式就过于依赖注入容器了，当没有启动整个依赖容器时，这个类就不能运转，在反射时无法提供这个类需要的依赖。
 	
 	在使用set方式时，这是一种选择注入，可有可无，即使没有注入这个依赖，那么也不会影响整个类的运行。
 	在使用构造器方式时已经显式注明必须强制注入。通过强制指明依赖注入来保证这个类的运行。
 	
 	总结下：
 	变量方式注入应该尽量避免，使用set方式注入或者构造器注入，这两种方式的选择就要看这个类是强制依赖的话就用构造器方式，选择依赖的话就用set方法注入
 	
 	结论
 	如果注入的属性是必选的属性，则通过构造器注入。
 	如果注入的属性是可选的属性，则通过setter方法注入。
 	至于field注入，不建议使用
