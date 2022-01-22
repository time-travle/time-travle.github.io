<p>
    <a href="#" onclick="refreshContent('spring')">返回目录</a>
</p>

#Spring ioc 知识积累
##Spring通过DI（依赖注入）实现IOC（控制反转），

    常用的注入方式主要有三种：
        构造方法注入，
        setter注入，
        基于注解的注入。
##Spring IOC的四种注入方式
- https://segmentfault.com/a/1190000023309818
- https://www.pianshen.com/article/9944276092/        


###基于注解的注入

    在介绍注解注入的方式前，先简单了解bean的一个属性autowire，autowire主要有三个属性值：constructor，byName，byType。

    constructor：通过构造方法进行自动注入，spring会匹配与构造方法参数类型一致的bean进行注入，如果有一个多参数的构造方法，
    一个只有一个参数的构造方法，在容器中查找到多个匹配多参数构造方法的bean，那么spring会优先将bean注入到多参数的构造方法中。

    byName：被注入bean的id名必须与set方法后半截匹配，并且id名称的第一个单词首字母必须小写，这一点与手动set注入有点不同。

    byType：查找所有的set方法，将符合符合参数类型的bean注入。

    下面进入正题：注解方式注册bean，注入依赖 

    主要有四种注解可以注册bean，每种注解可以任意使用，只是语义上有所差异：

    @Component：可以用于注册所有bean
    @Repository：主要用于注册dao层的bean
    @Controller：主要用于注册控制层的bean
    @Service：主要用于注册服务层的bean
    描述依赖关系主要有两种：

    @Resource：java的注解，默认以byName的方式去匹配与属性名相同的bean的id，如果没有找到就会以byType的方式查找，如果byType查找到多个的话，
        使用@Qualifier注解（spring注解）指定某个具体名称的bean。
    @Resource
    @Qualifier("userDaoMyBatis")
    private IUserDao userDao;

    public UserService(){
    @Autowired：spring注解，默认是以byType的方式去匹配与属性名相同的bean的id，如果没有找到，就通过byName的方式去查找，
    @Autowired
    @Qualifier("userDaoJdbc")
    private IUserDao userDao;
    写在最后：虽然有这么多的注入方式，但是实际上开发的时候自己编写的类一般用注解的方式注册类，用@Autowired描述依赖进行注入，
    一般实现类也只有一种（jdbc or hibernate or mybatis），除非项目有大的变动，所以@Qualifier标签用的也较少；
    但是在使用其他组件的API的时候用的是通过xml配置文件来注册类，描述依赖，因为你不能去改人家源码嘛


###setter注入

    配置文件如下：

    <!-- 注册userService -->
    <bean id="userService" class="com.lyu.spring.service.impl.UserService">
        <!-- 写法一 -->
        <!-- <property name="UserDao" ref="userDaoMyBatis"></property> -->
        <!-- 写法二 -->
        <property name="userDao" ref="userDaoMyBatis"></property>
    </bean>

    <!-- 注册mybatis实现的dao -->
    <bean id="userDaoMyBatis" class="com.lyu.spring.dao.impl.UserDaoMyBatis"></bean>
    注：上面这两种写法都可以,spring会将name值的每个单词首字母转换成大写，然后再在前面拼接上”set”构成一个方法名,然后去对应的类中查找该方法,
    通过反射调用,实现注入。

    切记：name属性值与类中的成员变量名以及set方法的参数名都无关，只与对应的set方法名有关，下面的这种写法是可以运行成功的

    public class UserService implements IUserService {

        private IUserDao userDao1;

        public void setUserDao(IUserDao userDao1) {
            this.userDao1 = userDao1;
        }
        public void loginUser() {
            userDao1.loginUser();
        }
    }
    还有一点需要注意：如果通过set方法注入属性，那么spring会通过默认的空参构造方法来实例化对象，所以如果在类中写了一个带有参数的构造方法，
    一定要把空参数的构造方法写上，否则spring没有办法实例化对象，导致报错



###构造方法注入

    demo;
        entity：存储实体，里面只有一个User类
        dao：数据访问，一个接口，两个实现类
        service：服务层，一个接口，一个实现类，实现类依赖于IUserDao
        test：测试包 

    在spring的配置文件中注册UserService，将UserDaoJdbc通过constructor-arg标签注入到UserService的某个有参数的构造方法

    <!-- 注册userService -->
    <bean id="userService" class="com.lyu.spring.service.impl.UserService">
        <constructor-arg ref="userDaoJdbc"></constructor-arg>
    </bean>
    <!-- 注册jdbc实现的dao -->
    <bean id="userDaoJdbc" class="com.lyu.spring.dao.impl.UserDaoJdbc"></bean>
    如果只有一个有参数的构造方法并且参数类型与注入的bean的类型匹配，那就会注入到该构造方法中

    public class UserService implements IUserService {

        private IUserDao userDao;

        public UserService(IUserDao userDao) {
            this.userDao = userDao;
        }
        public void loginUser() {
            userDao.loginUser();
        }
    }
    @Test
    public void testDI() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取bean对象
        UserService userService = ac.getBean(UserService.class, "userService");
        // 模拟用户登录
        userService.loginUser();
    }

    如果有多个有参数的构造方法并且每个构造方法的参数列表里面都有要注入的属性，那userDaoJdbc会注入到哪里呢？
        会注入到只有一个参数的构造方法中，并且经过测试注入哪一个构造方法与构造方法的顺序无关

    如果只有一个构造方法，但是有两个参数，一个是待注入的参数，另一个是其他类型的参数，那么这次注入可以成功吗？
        失败，即使在costract-arg标签里面通过name属性指定要注入的参数名userDao也会失败.
        
    如果我们想向有多个参数的构造方法中注入值该在配置文件中怎么写呢？

    public class UserService implements IUserService {
        private IUserDao userDao;
        private User user;

        public UserService(IUserDao userDao, User user) {
            this.userDao = userDao;
            this.user = user;
        }
        public void loginUser() {
            userDao.loginUser();
        }
    }
    参考写法：通过name属性指定要注入的值，与构造方法参数列表参数的顺序无关。

    <!-- 注册userService -->
    <bean id="userService" class="com.lyu.spring.service.impl.UserService">
        <constructor-arg name="userDao" ref="userDaoJdbc"></constructor-arg>
        <constructor-arg name="user" ref="user"></constructor-arg>
    </bean>

    <!-- 注册实体User类，用于测试 -->
    <bean id="user" class="com.lyu.spring.entity.User"></bean>

    <!-- 注册jdbc实现的dao -->
    <bean id="userDaoJdbc" class="com.lyu.spring.dao.impl.UserDaoJdbc"></bean>
    问题四：如果有多个构造方法，每个构造方法只有参数的顺序不同，那通过构造方法注入多个参数会注入到哪一个呢？

    public class UserService implements IUserService {
        private IUserDao userDao;
        private User user;

        public UserService(IUserDao userDao, User user) {
            System.out.println("这是第二个构造方法");
            this.userDao = userDao;
            this.user = user;
        }
        public UserService(User user, IUserDao userDao) {
            System.out.println("这是第一个构造方法");
            this.userDao = userDao;
            this.user = user;
        }
        public void loginUser() {
            userDao.loginUser();
        }
    }
    结果：哪个构造方法在前就注入哪一个，这种情况下就与构造方法顺序有关。    



###静态工厂的方法注入

    静态工厂顾名思义，就是通过调用静态工厂的方法来获取自己需要的对象，为了让spring管理所有对象，我们不能直接通过"工程类.静态方法()"来获取对象，
    而是依然通过spring注入的形式获取：

    package com.guage.springdemo.factory; 
    import com.guage.springdemo.dao.FactoryDao; 
    import com.guage.springdemo.dao.impl.FactoryDaoImpl; 
    import com.guage.springdemo.dao.impl.StaticFacotryDaoImpl; 
    public class DaoFactory { 
         //静态工厂 
         public static final FactoryDao getStaticFactoryDaoImpl(){ 
         return new StaticFacotryDaoImpl(); 
         } 
    }
    同样看关键类，这里我需要注入一个FactoryDao对象，这里看起来跟第一种注入一模一样，但是看随后的xml会发现有很大差别:

    public class SpringAction { 
         //注入对象 
         private FactoryDao staticFactoryDao; 
         
         public void staticFactoryOk(){ 
         staticFactoryDao.saveFactory(); 
         } 
        //注入对象的set方法

         public void setStaticFactoryDao(FactoryDao staticFactoryDao) { 
         this.staticFactoryDao = staticFactoryDao; 
         } 
    } 
    Spring的IOC配置文件，注意看<bean name="staticFactoryDao">指向的class并不是FactoryDao的实现类，而是指向静态工厂DaoFactory，
    并且配置 factory-method="getStaticFactoryDaoImpl"指定调用哪个工厂方法：

    <!--配置bean,配置后该类由spring管理-->
    <bean name="springAction" class="com.guage.springdemo.action.SpringAction" > 
     <!--(3)使用静态工厂的方法注入对象,对应下面的配置文件(3)-->
     <property name="staticFactoryDao" ref="staticFactoryDao"></property> 
     </property> 
     </bean> 
     <!--(3)此处获取对象的方式是从工厂类中获取静态方法-->
    <bean name="staticFactoryDao" class="com.guage.springdemo.factory.DaoFactory" factory-method="getStaticFactoryDaoImpl"></bean>

        
        
        
##IOC 控制反转（Inversion of Control） 也可以称为依赖倒置

    依赖注入（Dependecy Injection）和控制反转（Inversion of Control）是同一个概念，
    具体的讲：当某个角色需要另外一个角色协助的时候，在传统的程序设计过程中，通常由调用者来创建被调用者的实例。
    但在spring中创建被调用者的工作不再由调用者来完成，因此称为控制反转。创建被调用者的工作由spring来完成，然后注入调用者，因此也称为依赖注入。 
    
    使用IOC的好处
        集中管理，实现类的可配置和易管理。
        降低了类与类之间的耦合度
            
      通俗解释1：
      什么叫依赖注入控制反转啥的，我认为这一切都要建立在一个中心容器上面的，我们回来配置文件中，或者什么地方，约定好，什么接口的实现类是什么，约定好一些常量值。
      在我们有需要的时候，跟容器申请，那么申请回来的实现类是单例的，还是每次都是初始化的，这个也都是在配置文件约定好的。
      IOC 其实到这就差不多了，上面虽然没怎么说控制反转，其实也是说了的，什么是反转啊，反转了什么啊，以前的操作，我们就是各种 new 就完了，反转以后，不用 new 了，
      我们跟容器申请，这就是我理解的控制反转。
      有了 IOC 就有了 DI，这是个什么东西呢，在容器的初期，我们比如要 A，这个 A 依赖 B 和 C，我们就得从容器申请到 B 和 C，
      然后在申请 A 并把B 和 C 传递进去用来初始化 A，这本来没什么，毕竟程序员懒，不想这样复杂，所以就有了 DI，这东西是怎么运行的呢，就是利用反射技术，
      让容器自己分析依赖并且从容器中获取回来，并且初始化。由于 DI 用了反射，所以会使效率降低一些不过也有优化方案不用担心的
      
      
      通俗解释2：
      所谓依赖，从程序的角度看，就是比如A要调用B的方法，那么A就依赖于B，反正A要用到B，则A依赖于B。
      所谓倒置，你必须理解如果不倒置，会怎么着，因为A必须要有B，才可以调用B，如果不倒置，意思就是A主动获取B的实例：B b = new B()，
      这就是最简单的获取B实例的方法（当然还有各种设计模式可以帮助你去获得B的实例，比如工厂、Locator等等），然后你就可以调用b对象了。
      所以，不倒置，意味着A要主动获取B，才能使用B；到了这里，就应该明白了倒置的意思了。倒置就是A要调用B的话，A并不需要主动获取B，而是由其它人自动将B送上门来。
    
      形象的举例就是：
      通常情况下，假如你有一天在家里口渴了，要喝水，那么你可以到你小区的小卖部去，告诉他们，你需要一瓶水，然后小卖部给你一瓶水！
      这本来没有太大问题，关键是如果小卖部很远，那么你必须知道：从你家如何到小卖部；小卖部里是否有你需要的水；你还要考虑是否开着车去；
      等等等等，也许有太多的问题要考虑了。也就是说，为了一瓶水，你还可能需要依赖于车等等这些交通工具或别的工具，问题是不是变得复杂了？那么如何解决这个问题呢？
      解决这个问题的方法很简单：小卖部提供送货上门服务，凡是小卖部的会员，你只要告知小卖部你需要什么，小卖部将主动把货物给你送上门来！
      这样一来，你只需要做两件事情，你就可以活得更加轻松自在：
         第一：向小卖部注册为会员。
         第二：告诉小卖部你需要什么。
        
      当然，我们也可以使用注解来注入。Spring依赖注入的实现技术是：动态代理
      
      IOC的主要设计模式是工厂模式。


###注入的两种方式，设置注入和构造注入。 

    设置注入的优点：直观，自然 
    构造注入的优点：可以在构造器中决定依赖关系的顺序
