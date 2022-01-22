<p>
    <a href="#" onclick="refreshContent('spring')">返回目录</a>
</p>

#Spring aop知识积累

什么是AOP？  

    面向切面编程（AOP）完善spring的依赖注入（DI），面向切面编程在spring中主要表现为两个方面 
        1.面向切面编程提供声明式事务管理 
        2.spring支持用户自定义的切面 

    面向切面编程（aop）是对面向对象编程（oop）的补充， 
    面向对象编程将程序分解成各个层次的对象，面向切面编程将程序运行过程分解成各个切面。 
    AOP从程序运行角度考虑程序的结构，提取业务处理过程的切面，oop是静态的抽象，aop是动态的抽象， 
    是对应用执行过程中的步骤进行抽象，，从而获得步骤之间的逻辑划分。 

    aop框架具有的两个特征： 
        1.各个步骤之间的良好隔离性 
        2.源代码无关性 
    主要的功能是：日志记录，性能统计，安全控制，事务处理，异常处理等等，解决代码复用
    
    Spring的事务管理机制实现的原理，就是通过这样一个动态代理对所有需要事务管理的Bean进行加载，并根据配置在invoke方法中对当前调用的 方法名进行判定，
    并在method.invoke方法前后为其加上合适的事务管理代码，这样就实现了Spring式的事务管理。Spring中的AOP实 现更为复杂和灵活，不过基本原理是一致的

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

https://www.jianshu.com/p/78ba8bafb90a

https://www.cnblogs.com/lingyejun/p/9941350.html

https://www.cnblogs.com/hts-technology/p/7244180.html


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




Spring面向切面编程（AOP）的简单实例

         https://blog.csdn.net/pan_junbiao/article/details/101535889
    
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
