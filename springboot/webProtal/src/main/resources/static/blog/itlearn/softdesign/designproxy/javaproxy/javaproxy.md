<p>
<a href="#" onclick="refreshDesignContent('designproxy')">返回</a>&emsp;&emsp;&emsp;
</p>

# java 动态代理

---

    动态代理是代理模式的一种实现方式，代理模式除了动态代理还有 静态代理，只不过静态代理能够在编译时期确定类的执行对象，
    而动态代理只有在运行时才能够确定执行对象是谁。代理可以看作是对最终调用目标的一个封装，我们能够通过操作代理对象来调用目标类，
    这样就可以实现调用者和目标对象的解耦合。
    
    动态代理的应用场景有很多，最常见的就是 AOP 的实现、RPC 远程调用、Java 注解对象获取、日志框架、全局性异常处理、事务处理等。

    代理类在程序运行时创建的代理方式被成为 动态代理。 
    也就是说，这种情况下，代理类并不是在Java代码中定义的，而是在运行时根据我们在Java代码中的“指示”动态生成的。
    相比于静态代理， 动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类的函数。

![avatar](../blog/itlearn/softdesign/designproxy/imgs/img_1.png)

    在 JDK 动态代理中，实现了 InvocationHandler的类可以看作是代理类(因为类也是一种对象，
    所以我们上面为了描述关系，把代理类形容成了代理对象)。JDK 动态代理就是围绕实现了 InvocationHandler 的代理类进行的

优点：

    易于理解和实现
    代理类和真实类的关系是编译期静态决定的，和动态代理比较起来，执行时没有任何额外开销
    动态代理实现了只需要将被代理对象作为参数传入代理类就可以获取代理类对象，从而实现类代理，具有较强的灵活性。
    动态代理的服务内容不需要像静态代理一样写在每个代码块中，只需要写在invoke()方法中即可，降低了代码的冗余度

缺点：

    通过InvocationHandler实现动态代理的局限性：没有实现任何接口的类不能被代理,动态代理类仍然需要实现接口

动态代理的本质就是反射

使用步骤

    声明 调用处理器类
    声明目标对象类的抽象接口
    声明目标对象类
    通过动态代理对象，调用目标对象的方法

![avatar](../blog/itlearn/softdesign/designproxy/imgs/img_2.png)

动态代理的作用：
    
    1)在日标类源代码不改变的情况下，增加功能.
    
    2)减少代码的重复
    
    3)专注业务逻辑代码
    
    4)解耦合，让你的业务功能和日志，事务非业务功能分离。

## demo:

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

代理JDKProxy,该代理实现InvocationHandler接口且覆写invoke方法

    import java.lang.reflect.InvocationHandler;
    import java.lang.reflect.Method;
    import java.lang.reflect.Proxy;

    public class JDKProxy implements InvocationHandler {
    
        private Object proxyObject ;// 被代理人
        
        //这里的目标类型为Object，则可以接受任意一种参数作为被代理类，实现了动态代理。但是要注意下面的newProxyInstance（）中的参数
        public Object getInstance(Object proxyObject) {
            this.proxyObject = proxyObject;
            //与cglib的区别在于这里构建代理对象的时候需要传入被代理对象的接口对象，第二个参数。而cglib不需要被代理对象实现任何接口即可   
            return Proxy.newProxyInstance(proxyObject.getClass().getClassLoader(), proxyObject.getClass().getInterfaces(), this);
        }

        // 参数说明：
        // 参数1：动态代理对象（即哪个动态代理对象调用了method（）
        // 参数2：目标对象被调用的方法
        // 参数3：指定被调用方法的参数
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("doSomething---------start");
            Object result = null;
            result = method.invoke(person, args);
            System.out.println("doSomething---------end");
            return result;
        }    
    }

JDK动态代理测试程序

    import com.zpj.designMode.proxy.MrLi;
    import com.zpj.designMode.proxy.Person;

    public class Run {
    
        public static void main(String[] args) {
            Person person = (Person) new JDKProxy().getInstance(new MrLi());
            //注意这里的person不是目标类person，而是代理类person：debug的时候显示null，有'$'标识符
            person.doWork();
        }
    }

# 参考：

- <a href="http://www.zzvips.com/article/101448.html" target="_blank">深入理解java动态代理的两种实现方式（JDK/Cglib） </a>
- <a href="https://www.jianshu.com/p/5dc416ea58a2" target="_blank">Carson带你学设计模式：动态代理模式（Proxy Pattern）（JDK/Cglib） </a>
- <a href="https://juejin.cn/post/6854573218804531207#" target="_blank">Java中的动态代理 </a>
- <a href="https://juejin.cn/post/6991005250790522893#" target="_blank">Java中动态代理的两种方式JDK动态代理和cglib动态代理以及区别 </a>
