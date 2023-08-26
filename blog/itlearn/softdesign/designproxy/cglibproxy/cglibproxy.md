<p>
<a href="#" onclick="refreshDesignContent('designproxy')">返回</a>&emsp;&emsp;&emsp;
</p>

# cglib 动态代理

---

## CGLIB 创建动态代理类的模式是：

    查找目标类上的所有非final 的public类型的方法定义；
    将这些方法的定义转换成字节码；
    将组成的字节码转换成相应的代理的class对象；
    实现 MethodInterceptor接口，用来处理对代理类上所有方法的请求

## demo0:

maven引入CGLIB包，然后编写一个UserDao类，它没有接口，只有两个方法，select() 和 update()

不用继承的要被代理的类

    public class UserDao {
        public void select() {
            System.out.println("UserDao 查询 selectById");
        }
        public void update() {
            System.out.println("UserDao 更新 update");
        }
    }

CglibProxy代理

    import java.lang.reflect.Method;
    import java.util.Date;
    
    public class LogInterceptor implements MethodInterceptor {
        /**
        * @param object 表示要进行增强的对象
        * @param method 表示拦截的方法
        * @param objects 数组表示参数列表，基本数据类型需要传入其包装类型，如int-->Integer、long-Long、double-->Double
        * @param methodProxy 表示对方法的代理，invokeSuper方法表示对被代理对象方法的调用
        * @return 执行结果
        * @throws Throwable
        */
        @Override
        public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            before();
            // 注意这里是调用 invokeSuper 而不是 invoke，否则死循环，methodProxy.invokesuper执行的是原始类的方法，
            // method.invoke执行的是子类的方法

            Object result = methodProxy.invokeSuper(object, objects);
            after();
            return result;
        }
        private void before() {
            System.out.println(String.format("log start time [%s] ", new Date()));
        }
        private void after() {
            System.out.println(String.format("log end time [%s] ", new Date()));
        }
    }

测试

    import net.sf.cglib.proxy.Enhancer;
    
    public class CglibTest {
    public static void main(String[] args) {
        DaoProxy daoProxy = new DaoProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);  // 设置超类，cglib是通过继承来实现的
        enhancer.setCallback(daoProxy);
    
        Dao dao = (Dao)enhancer.create();   // 创建代理类
        dao.update();
        dao.select();
        }
    }

多个 MethodInterceptor 进行过滤筛选

    public class LogInterceptor2 implements MethodInterceptor {
        @Override
        public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            before();
            Object result = methodProxy.invokeSuper(object, objects);
            after();
            return result;
        }
        private void before() {
            System.out.println(String.format("log2 start time [%s] ", new Date()));
        }
        private void after() {
            System.out.println(String.format("log2 end time [%s] ", new Date()));
        }
        }
        
        // 回调过滤器: 在CGLib回调时可以设置对不同方法执行不同的回调逻辑，或者根本不执行回调。
        public class DaoFilter implements CallbackFilter {
            @Override
            public int accept(Method method) {
                if ("select".equals(method.getName())) {
                    return 0;   // Callback 列表第1个拦截器
                }
            return 1;   // Callback 列表第2个拦截器，return 2 则为第3个，以此类推
        }
    }

再次测试

    public class CglibTest2 {
        public static void main(String[] args) {
        LogInterceptor logInterceptor = new LogInterceptor();
        LogInterceptor2 logInterceptor2 = new LogInterceptor2();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);   // 设置超类，cglib是通过继承来实现的
        // 设置多个拦截器，NoOp.INSTANCE是一个空拦截器，不做任何处理
        enhancer.setCallbacks(new Callback[]{logInterceptor, logInterceptor2, NoOp.INSTANCE});   
        enhancer.setCallbackFilter(new DaoFilter());
        
        UserDao proxy = (UserDao) enhancer.create();   // 创建代理类
        proxy.select();
        proxy.update();
        }
    }

## demo1:

接口：

    //定义一个Person接口
    public interface Person {
        public void doWork();
    }

实现类：

    //添加一个实现类
    public class MrLi implements Person {
        @Override
        public void doWork() {
            System.out.println("-----doWork");
        }
    }

CglibProxy代理，同时实现MethodInterceptor接口

    import java.lang.reflect.Method;

    import net.sf.cglib.proxy.Enhancer;
    import net.sf.cglib.proxy.MethodInterceptor;
    import net.sf.cglib.proxy.MethodProxy;
    

    public class CglibProxy implements MethodInterceptor {
        private Object targetObject;
    
        // 这里的目标类型为Object，则可以接受任意一种参数作为被代理类，实现了动态代理
        public Object getInstance(Object target) {
            this.targetObject = target;
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(target.getClass());
            enhancer.setCallback(this);
            //注意该处代理的创建过程
            Object proxyObj = enhancer.create();
            return proxyObj;// 返回代理对象
        }
        
        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            Object obj = null;
            System.out.println("doSomething---------start");
            obj = method.invoke(targetObject, args);
            System.out.println("doSomething---------end");
            return obj;
        }
    
    }

Cglib动态代理测试程序

    import com.zpj.designMode.proxy.MrLi;
    import com.zpj.designMode.proxy.Person;
    
    public class Run {
    
        public static void main(String[] args) {
            Person person = (Person)new CglibProxy().getInstance(new MrLi());
            person.doWork();
        }
    }

# 参考：

- <a href="http://www.zzvips.com/article/101448.html" target="_blank">深入理解java动态代理的两种实现方式（JDK/Cglib） </a>
- <a href="https://juejin.cn/post/6854573218804531207#" target="_blank">Java中的动态代理 </a>
- <a href="https://juejin.cn/post/6991005250790522893#" target="_blank">Java中动态代理的两种方式JDK动态代理和cglib动态代理以及区别 </a>

