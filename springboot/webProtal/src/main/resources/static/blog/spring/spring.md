# Spring 知识积累

<p>
<a href="#" onclick="refreshSpringContent('aop')">Spring（aop）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringContent('ioc')">Spring（ioc）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringContent('transactional')">Spring（事物）</a>&emsp;&emsp;&emsp;
</p>

----
怎样开启注解装配？

    注解装配在默认情况下是不开启的，为了使用注解装配，我们必须在Spring配置文件中配置 <context:annotation-config/>元素    

将一个类声明为Spring的 bean 的注解有哪些?

    我们一般使用 @Autowired 注解自动装配 bean，要想把类标识成可用于 @Autowired 注解自动装配的 bean 的类,采用以下注解可实现：

    @Component ：通用的注解，可标注任意类为 Spring 组件。如果一个Bean不知道属于拿个层，可以使用@Component 注解标注。
    @Repository : 对应持久层即 Dao 层，主要用于数据库相关操作。
    @Service : 对应服务层，主要涉及一些复杂的逻辑，需要用到 Dao层。
    @Controller : 对应 Spring MVC 控制层，主要用户接受用户请求并调用 Service 层返回数据给前端页面

spring 的优点？

    1.降低了组件之间的耦合性 ，实现了软件各层之间的解耦 
    2.可以使用容易提供的众多服务，如事务管理，消息服务等 
    3.容器提供单例模式支持 
    4.容器提供了AOP技术，利用它很容易实现如权限拦截，运行期监控等功能 
    5.容器提供了众多的辅助类，能加快应用的开发 
    6.spring对于主流的应用框架提供了集成支持，如hibernate，JPA，Struts等 
    7.spring属于低侵入式设计，代码的污染极低 
    8.独立于各种应用服务器 
    9.spring的DI机制降低了业务对象替换的复杂性 
    10.Spring的高度开放性，并不强制应用完全依赖于Spring，开发者可以自由选择spring的部分或全部 

Spring的三大特性
- <a href="https://blog.csdn.net/xiaoxiaoxiang1/article/details/105598931" target="_blank">【Spring篇】Spring的三大特性 </a>
    

    1、三大特征：AOP、IOC、DI。
    
        IOC ：控制反转。
        DI ：依赖注入。
        AOP ：面向切面编程。
    
    2、IOC：控制反转。
        （1）概念：控制反转。将创建对象的权利交给Spring来进行处理。
        
        （2）作用：解耦（减低程序间的耦合性）。
        
        （3）优缺点：
            A、优点：
            解耦，降低程序间的依赖关系；
            B、缺点：
            使用反射生成对象，损耗效率。
    （4）实现步骤：（Spring注解开发IOC）
    
        创建Spring主配置文件，并添加约束；
        在主配置文件中，使用<context:component-scan>标签，标明使用IOC的类所在的包；
        使用注解标注IOC的类；
        使用注解依赖注入的数据；
        获取bean对象，并使用。
    
    （5）涉及的注解：
    
        @Component //用于标注一般类
        @Controller //用于标注表现层的类
        @Service //用于标注业务层的类
        @Repository //用于标注持久层的类
        @Autowired //自动按照类型注入
        @Qualifier //在按照类中注入的基础之上, 再按照名称注入
        @Resource //直接按照bean的id注入
3、AOP：面向切面编程。

    （1）概念：
    
        面向切面编程。简单描述就是：将程序重复的代码抽取出来，在需要执行的时候，使用动态代理的技术，在不修改源码的基础上，对自己的已有方法进行增强。
    
    （2）作用：
    
        在程序运行期间，不修改源码对已有方法进行增强。
    
    （3）优缺点：
    
        减少重复代码；
        提高开发效率；
        维护方便。
    
    （4）实现方式：
    
        使用动态代理技术。

4、DI：依赖注入。
    
    （1） 作用：减低程序间的耦合。
    
    （2）注入方式：使用构造函数注入；使用set方法注入；使用注解。
    
    （3）能注入的数据：基本类型和string，其他bean类型（在配置文件或者注解配置过的bean），复杂类型/集合类型。
    
    （4）构造函数注入：
        ①、使用的标签constructor-arg。
        ②、标签出现的位置：bean标签的内部。
        ③、标签中的属性：
        type：用于指定要注入的数据类型，该数据类型也是构造函数中某个或某些参数的类型。
        index：用于指定要注入的数据给构造函数中，指定索引位置的参数赋值。索引位置从0开始。
        name：用于指定给构造函数中指定名称的参数赋值。
        value：用于提供基本类型和string类型的数据。
        ref：用于指定其他的bean类型数据。（指的是在spring的ioc核心容器中出现过的bean对象）。
        ④、优势：在获取bean对象时，必须要注入数据，否则对象无法创建成功。
        ⑤、坏处：
        改变了bean对象的实例化方式，使我们在创建对象时，即使用不到这些数据，也要提供。
    
    （5）set方法注入：（更常用的方法）
        ①、涉及的标签：property
        ②、出现的位置：bean标签的内部
        ③、标签的属性：
        name：用于指定注入时所调用的set方法名称。
        value：用于提供基本类型和string类型的数据。
        ref：用于指定其他的bean类型数据，它指的就是在spring的IOC核心容器中出现过的bean对象。
        ④、优势：创建对象时没有明确的限制，可以直接使用默认构造函数。
        ⑤、坏处：如果有某个成员必须有值，则获取对象是有可能set方法没有执行。






Spring如何解决循环依赖问题：

- <a href="https://www.cnblogs.com/xiaoxing/p/10762686.html" target="_blank">https://www.cnblogs.com/xiaoxing/p/10762686.html </a>
- <a href="https://www.zhihu.com/question/438247718" target="_blank">https://www.zhihu.com/question/438247718 </a>


    生成代理对象产生的循环依赖
        这类循环依赖问题解决方法很多，主要有：
        
        使用@Lazy注解，延迟加载
        使用@DependsOn注解，指定加载先后关系
        修改文件名称，改变循环依赖类的加载顺序
    使用@DependsOn产生的循环依赖
        这类循环依赖问题要找到@DependsOn注解循环依赖的地方，迫使它不循环依赖就可以解决问题。
    
    多例循环依赖
        这类循环依赖问题可以通过把bean改成单例的解决。
    
    构造器循环依赖
        这类循环依赖问题可以通过使用@Lazy注解解决。
        
        实现原理：<a href="http://zengbingo.com/p/1985.html" target="_blank">http://zengbingo.com/p/1985.html</a>
            1.先加载A，依次判断(一级缓存)、(二级缓存)、(三级缓存)中是否有A，没有就将A加入(三级缓存)
            2.A依赖B，先加载B
            2.1 依次判断(一级缓存)、(二级缓存)、(三级缓存)中是否有B,没有就将B加入(三级缓存)
            2.2 加载B的依赖，发现依赖A，依次从(一级缓存)、(二级缓存)、(三级缓存)中查找A，发现(三级缓存)有A，将A上升到(二级缓存)中
            2.3 将A注入B的引用，完成B的加载，将B从(三级缓存)升级至(一级缓存)中
            3.A依赖的B加载完了，继续加载A完成。将A从(二级缓存)上升到(一级缓存)。

DispatcherServlet 的工作流程

            向服务器发送 HTTP 请求，请求被前端控制器 DispatcherServlet 捕获。
        
            DispatcherServlet 根据 -servlet.xml 中的配置对请求的 URL 进行解析，得到请求资源标识符（URI）。
            然后根据该 URI，调用 HandlerMapping 获得该 Handler 配置的所有相关的对象（包括 Handler 对象以及 Handler 对象对应的拦截器），最后以HandlerExecutionChain 对象的形式返回。
        
            DispatcherServlet 根据获得的Handler，选择一个合适的 HandlerAdapter。（附注：如果成功获得HandlerAdapter后，此时将开始执行拦截器的 preHandler(...)方法）。
        
            提取Request中的模型数据，填充Handler入参，开始执行Handler（Controller)。 在填充Handler的入参过程中，根据你的配置，Spring 将帮你做一些额外的工作：
                HttpMessageConveter： 将请求消息（如 Json、xml 等数据）转换成一个对象，将对象转换为指定的响应信息。
                数据转换：对请求消息进行数据转换。如String转换成Integer、Double等。
                数据根式化：对请求消息进行数据格式化。 如将字符串转换成格式化数字或格式化日期等。
                数据验证： 验证数据的有效性（长度、格式等），验证结果存储到BindingResult或Error中。
        
            Handler(Controller)执行完成后，向 DispatcherServlet 返回一个 ModelAndView 对象；
        
            根据返回的ModelAndView，选择一个适合的 ViewResolver（必须是已经注册到 Spring 容器中的ViewResolver)返回给DispatcherServlet。
        
            ViewResolver 结合Model和View，来渲染视图。
        
            视图负责将渲染结果返回给客户端。

Spring框架支持以下五种bean的作用域：

        singleton : bean在每个Spring ioc 容器中只有一个实例。
        prototype：一个bean的定义可以有多个实例。
        request：每次http请求都会创建一个bean，该作用域仅在基于web的Spring ApplicationContext情形下有效。
        session：在一个HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring ApplicationContext情形下有效。
        global-session：在一个全局的HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring ApplicationContext情形下有效。
        缺省的Spring bean 的作用域是Singleton.

Spring常见面试题

- <a href="https://www.w3cschool.cn/fisug/" target="_blank">https://www.w3cschool.cn/fisug/ </a>

69道Spring面试题和答案

- <a href="http://ifeve.com/spring-interview-questions-and-answers/" target="_blank">http://ifeve.com/spring-interview-questions-and-answers/ </a>
    
                       