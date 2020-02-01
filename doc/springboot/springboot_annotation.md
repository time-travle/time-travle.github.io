#注解Annotaion
@Component	最普通的组件，可以被注入到spring容器进行管理

@Repository	作用于持久层

@Service	作用于业务逻辑层

@Controller	作用于表现层（spring-mvc的注解）

@Autowired 依赖注入

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
	
	缺点：不能有效的指明依赖。相信很多人都遇见过一个bug，依赖注入的对象为null，在启动依赖容器时遇到这个问题都是配置的依赖注入少了一个注解什么的，然而这种方式就过于依赖注入容器了，当没有启动整个依赖容器时，这个类就不能运转，在反射时无法提供这个类需要的依赖。
	
	在使用set方式时，这是一种选择注入，可有可无，即使没有注入这个依赖，那么也不会影响整个类的运行。
	在使用构造器方式时已经显式注明必须强制注入。通过强制指明依赖注入来保证这个类的运行。
	
	总结下：
	变量方式注入应该尽量避免，使用set方式注入或者构造器注入，这两种方式的选择就要看这个类是强制依赖的话就用构造器方式，选择依赖的话就用set方法注入
	
	结论
	如果注入的属性是必选的属性，则通过构造器注入。
	如果注入的属性是可选的属性，则通过setter方法注入。
	至于field注入，不建议使用
