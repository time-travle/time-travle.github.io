<p>
<a href="#" onclick="refreshDesignContent('designproxy')">返回</a>&emsp;&emsp;&emsp;
</p>

# Java 静态代理

---
静态代理实现的条件

需要具备以下条件：

    1.目标类具有接口，并实现了其接口。
    2.代理类也得实现目标类的接口，并有一个属性是目标类接口。
    3.代理类的得有一个无参构造方法和一个构造方法，参数为目标类接口类型，用于接收目标对象赋值给代理类的目标类接口属性。
    4.代理类必须实现接口的所有方法，并在在方法中访问目标类对象的方法，在访问之前和之后都可以进行一些代理操作。UML如下图所示：

![avatar](../blog/itlearn/softdesign/designproxy/imgs/img_3.png)


静态代理是代理类在编译期间就创建好了，不是编译器生成的代理类，而是手动创建的类。在编译时就已经将接口，被代理类，代理类等确定下来。

软件设计中所指的代理一般是指静态代理，也就是在代码中显式指定的代理。

实现步骤

    委托类和代理类之间的约束接口
    约束接口实现类 ，实现  接口，委托角色
    代理类实现 Proxy，实现 接口，并含有一个 接口引用属性。 

    代理角色，代理 接口属性引用实例的行为并可以新增公共逻辑

优点：

    1. 易于理解和实现
    2. 代理类和真实类的关系是编译期静态决定的，和动态代理比较起来，执行时没有任何额外开销
    静态代理对客户（测试类）隐藏了被代理类接口（目标类接口）的具体实现类，在一定程度上实现了解耦合，同时提高了安全性

缺点：

    每一个真实类都需要一个创建新的代理类。
    由于代理只能为一个类服务，如果需要代理的类很多，那么就需要编写大量的代理类，比较繁琐
    静态代理类需要实现目标类（被代理类）的接口，并实现其方法，造成了代码的大量冗余

##demo:

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

静态代理类Proxy：

    //静态代理，代理必须和目标类实现共同的接口
    public class Proxy implements Person {
        private Person person;// 被代理人        
        //这里的目标类型决定了该代理类只能代理实现了Person接口的实例，而不能接收其他类型参数，这也就是静态代理的局限性
        public Proxy(Person person) {
            this.person = person;
        }
        
        @Override
        public void doWork() {
            System.out.println("doSomething-----start");
            person.doWork();
            System.out.println("doSomething-----end");
        }    
    }

静态代理测试程序：

    public class Run {
        public static void main(String[] args) {
            MrLi li = new MrLi();
            Proxy proxy = new Proxy(li);
            //调用处直接调用代理进行目标方法的操作。
            proxy.doWork();
        }
    }




#参考：
- <a href="http://www.zzvips.com/article/101448.html" target="_blank">深入理解java动态代理的两种实现方式（JDK/Cglib） </a>
- <a href="https://blog.csdn.net/qq_40572806/article/details/89738486" target="_blank">代理设计模式：静态代理和动态代理的理解、实现与区别（优缺点） </a>


