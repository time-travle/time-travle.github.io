<p>
    <a href="#" onclick="refreshContent('spring')">返回目录</a>
</p>

#Spring aop知识积累

SpringAOP的在实际应用中场景有哪些？

    Authentication 权限                                            Caching 缓存
    Context passing 内容传递                                        Error handling 错误处理
    Lazy loading 懒加载                                            Debugging 调试
    logging，tracing，profiling and monitoring 记录跟踪 优化 校准
    Performance optimization 性能优化                               Persistence 持久化
    Resource pooling 资源池                                         Synchronization 同步
    Transactions 事务                                              Logging 日志

<a href="https://zhuanlan.zhihu.com/p/103236714" target="_blank">Spring中的5种Aop常见应用方式</a>

<p>
    <a href="#" onclick="refreshAopUseScenarios('authentication')">Authentication 权限</a> &emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('caching')">Caching 缓存</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('contextpassing')">Context passing 内容传递</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('errorhandling')">Error handling 错误处理</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('lazyloading')">Lazy loading 懒加载</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('debugging')">Debugging 调试</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('synchronization')">Synchronization 同步</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('transactions')">Transactions 事务</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('logging')">Logging 日志</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('resource')">Resource pooling 资源池</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('persistence')"> Persistence 持久化</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('performance')">Performance optimization 性能优化</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('tracing')">记录跟踪</a>&emsp;&emsp;&emsp;
    <a href="#" onclick="refreshAopUseScenarios('monitoring')">监控</a>&emsp;&emsp;&emsp;
</p>

什么是AOP？  

    面向切面编程（AOP）完善spring的依赖注入（DI），面向切面编程在spring中主要表现为两个方面 
        1.面向切面编程提供声明式事务管理 
        2.spring支持用户自定义的切面 

    面向切面编程（aop）是对面向对象编程（oop）的补充， 
    面向对象编程将程序分解成各个层次的对象，面向切面编程将程序运行过程分解成各个切面。 
    AOP从程序运行角度考虑程序的结构，提取业务处理过程的切面，oop是静态的抽象，aop是动态的抽象， 
    是对应用执行过程中的步骤进行抽象，，从而获得步骤之间的逻辑划分。 

Spring AOP用的是代理设计模式


aop框架具有的两个特征： 

        1.各个步骤之间的良好隔离性 
        2.源代码无关性 
    主要的功能是：日志记录，性能统计，安全控制，事务处理，异常处理等等，解决代码复用
    
    Spring的事务管理机制实现的原理，就是通过这样一个动态代理对所有需要事务管理的Bean进行加载，并根据配置在invoke方法中对当前调用的 方法名进行判定，
    并在method.invoke方法前后为其加上合适的事务管理代码，这样就实现了Spring式的事务管理。Spring中的AOP实 现更为复杂和灵活，不过基本原理是一致的
AOP包含的几个概念

    Jointpoint（连接点）：具体的切面点点抽象概念，可以是在字段、方法上，Spring中具体表现形式是PointCut（切入点），仅作用在方法上。
    Advice（通知）: 在连接点进行的具体操作，如何进行增强处理的，分为前置、后置、异常、最终、环绕五种情况。
    目标对象：被AOP框架进行增强处理的对象，也被称为被增强的对象。
    AOP代理：AOP框架创建的对象，简单的说，代理就是对目标对象的加强。Spring中的AOP代理可以是JDK动态代理，也可以是CGLIB代理。
    Weaving（织入）：将增强处理添加到目标对象中，创建一个被增强的对象的过程

    @Pointcut("@annotation(XXXXAnnotation)")   // 匹配的是 方法添加XXXXAnnotation注解
    @Pointcut("execution(public void com.XXXXX.controller.*.*(..))")// 匹配的是 public类型 返回void并且是com.XXXXX.controller文件夹下面的所有类方法
![avatar](../blog/spring/aop/imag/aop2.png)

[comment]: <> (![avatar]&#40;imag/aop2.png&#41;)

使用AOP的好处

        降低模块的耦合度
        使系统容易扩展
        提高代码复用性
        
AOP的基本概念

        连接点（JoinPoint）：需要在程序中插入横切关注点的点，连接点可能是在类初始化、方法调用、字段调用或处理异常等等。Spring中只支持方法执行连接点。
        切入点（Pointcut）：一组相关连接点的集合。
        通知（Advice）：在连接点上执行的行为，增强提供了在AOP中需要在切入点所选择的连接点处进行扩展现有行为的手段。
            包括前置增强（before advice）、后置增强 (after advice)、环绕增强 （around advice）。
        切面（Aspect）：通知和切入点的结合。
        织入（Weaving）：织入是一个过程，是将切面应用到目标对象从而创建出AOP代理对象的过程。
        代理（Proxy）：通过代理方式来对目标对象应用切面。AOP代理可以用JDK动态代理或CGLIB代理实现。
        目标对象（Target）：需要被织入关注点的对象。即被代理的对象。

<a href="https://www.jianshu.com/p/78ba8bafb90a" target="_blank">https://www.jianshu.com/p/78ba8bafb90a</a>

<a href="https://www.cnblogs.com/lingyejun/p/9941350.html" target="_blank">https://www.cnblogs.com/lingyejun/p/9941350.html</a>

<a href="https://www.cnblogs.com/hts-technology/p/7244180.html" target="_blank">https://www.cnblogs.com/hts-technology/p/7244180.html</a>


那么什么是切面呢，这个我的理解就是在一个完整的请求过程中，我们想插入操作的一些位置就叫做切面。

为什么要这么做呢，就是要跟我们的主逻辑解耦，在修改或者替换的时候造成的影响最小，比如 Tp3.2 里面就会有一些 before 、 after 的方法定义。
在 laravel 这类的主流框架中实现了 AOP 的东西就是中间件了。我们可以在任何想要进行前置处理或者后置处理的位置插入一个中间件。
比如请求的验证就是前置操作，比如成功后的通知、这个操作的会影响的操作都可以作为后置操作。

为什么要这么拆分，首先我们要保证主体流程稳定，不要因为插入了某些操作就出了问题，所以，把一些方法抽出到前置或者后置操作上就不会更改主业务部分的代码。
纠错方便了，替换也方便了，比如把邮件通知换成站内信通知，只要把后置操作的中间件替换一下就ok了，主业务完全不动，laravel 这类的就在路由那边就修改了。


AOP的好处就是你只需要干你的正事，其它事情别人帮你干。也许有一天，你想裸奔，不想穿衣服，那么你把仆人A解雇就是了！也许有一天，出门之前你还想带点钱，
那么你再雇一个仆人D专门帮你干取钱的活！这就是AOP。每个人各司其职，灵活组合，达到一种可配置的、可插拔的程序结构


从Spring的角度看，AOP最大的用途就在于提供了事务管理的能力。事务管理就是一个关注点，你的正事就是去访问数据库，而你不想管事务（太烦），
所以，Spring在你访问数据库之前，自动帮你开启事务，当你访问数据库结束之后，自动帮你提交/回滚事务！


AOP中的5种通知（advice）类型，分别是：

        Before（前置通知） 目标方法调用之前执行
        After（后置通知） 目标方法调用之后执行
        After-returning（返回通知） 目标方法执行成功后执行
        After-throwing（异常通知） 目标方法抛出异常后执行
        Around（环绕通知） 相当于合并了前置和后置

![avatar](../blog/spring/aop/imag/aop1.png)

[comment]: <> (![avatar]&#40;imag/aop1.png&#41;)

Spring面向切面编程（AOP）的简单实例
- <a href="https://blog.csdn.net/pan_junbiao/article/details/101535889" target="_blank">https://blog.csdn.net/pan_junbiao/article/details/101535889 </a>
    
创建UserService.java用户信息业务逻辑接口。

    /**
     * 用户信息业务逻辑接口
     * @author pan_junbiao
     **/
    public interface UserService
    {
        /**
         * 用户注册
         */
        public boolean register(String userName, String blogUrl, String sex);
     
        /**
         * 用户评论
         */
        public void comment(String userName,String comments);
    }


创建UserServiceImpl.java用户信息业务逻辑实现类。 

    /**
     * 用户信息业务逻辑实现类
     * @author pan_junbiao
     **/
    public class UserServiceImpl implements UserService
    {
        /**
         * 用户注册
         */
        @Override
        public boolean register(String userName, String blogUrl, String sex)
        {
            System.out.println("业务方法register开始执行：");
            System.out.println("用户名称："+userName);
            System.out.println("博客地址："+blogUrl);
            System.out.println("用户性别："+sex);
            System.out.println("业务方法register执行完成");
            return true;
        }
     
        /**
         * 用户评论
         */
        @Override
        public void comment(String userName, String comments)
        {
            System.out.println("业务方法comment开始执行：");
            System.out.println("用户名称："+userName);
            System.out.println("评论内容："+comments);
            System.out.println("业务方法comment执行完成");
        }
    }


这个分类是根据通知织入到业务代码时执行的时间划分的。

    前置通知是在方法执行前自动执行的通知；
    后置通知是在方法执行后自动执行的通知；
    环绕通知能力最强，它可以在方法调用前执行通知代码，可以决定是否还调用目标方法；
    异常通知是方法抛出异常时自动执行的切面代码。
        前置通知：org.springframework.aop.MethodBeforeAdvice 
        后置通知：org.springframework.aop.AfterReturningAdvice 
        环绕通知：org.aopalliance.intercept.MethodInterceptor 
        异常通知：org.springframework.aop.ThrowsAdvice

    import org.springframework.aop.MethodBeforeAdvice;
    import org.springframework.lang.Nullable;
     
    import java.lang.reflect.Method;
    import java.text.DateFormat;
    import java.text.SimpleDateFormat;
    import java.util.Arrays;
    import java.util.Date;
     
    /**
     * 日志通知
     * @author pan_junbiao
     **/
    public class LogAdvice implements MethodBeforeAdvice
    {
        @Override
        public void before(Method var1, Object[] var2, @Nullable Object var3) throws Throwable
        {
            DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            System.out.println("\n[系统日志]");
            System.out.println("执行时间：" + sdf.format(new Date()));
            System.out.println("方法名称：" + var1.getName());
            System.out.println("执行参数：" + Arrays.toString(var2));
            System.out.println("====================================================================");
        }
    }

在src目录下创建applicationContext.xml配置文件

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN" "http://www.springframework.org/dtd/spring-beans.dtd">
    <!-- Spring配置文件 -->
    <beans>
        <bean id="userServiceTarget" class="com.pjb.aop.UserServiceImpl"/>
        <bean id="logAdvice" class="com.pjb.aop.LogAdvice"/>
        <!-- 定义代理类 -->
        <bean id="userService" class="org.springframework.aop.framework.ProxyFactoryBean">
            <!-- 被代理的接口 -->
            <property name="proxyInterfaces">
                <value>com.pjb.aop.UserService</value>
            </property>
            <!-- 织入的通知列表 -->
            <property name="interceptorNames">
                <list>
                    <value>logAdvice</value>
                </list>
            </property>
            <!-- 被代理的原Bean -->
            <property name="target" ref="userServiceTarget"/>
        </bean>
    </beans>


创建AopTest.java类，编写测试代码

    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.ClassPathXmlApplicationContext;
     
    /**
     * 运行测试
     * @author pan_junbiao
     **/
    public class AopTest
    {
        public static void main(String[] args)
        {
            //装载配置文件
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            //获取UserService的代理类
            UserService userService = (UserService)context.getBean("userService");
            //调用注册方法
            userService.register("pan_junbiao的博客","https://blog.csdn.net/pan_junbiao","男");
            //调用用户评论方法
            userService.comment("pan_junbiao的博客","您好，欢迎访问 pan_junbiao的博客！");
        }
    }

使用spring aop，有两点需要注意：

        1、将切面类声明为一个bean
        2、切点指定的方法所在的类也同样需由spring注入才能生效