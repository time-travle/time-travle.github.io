<p>
    <a href="#" onclick="showITLearnPage('softdesign')">返回设计模式目录</a>
</p>

---

# 代理模式

<p>
    <a href="#" onclick="refreshDesignProxyContent('javastaticproxy')">Java静态代理 </a>  &emsp;&emsp;&emsp;
    <a href="#" onclick="refreshDesignProxyContent('javaproxy')">Java动态代理 </a>  &emsp;&emsp;&emsp;
    <a href="#" onclick="refreshDesignProxyContent('cglibproxy')">cglib动态代理 </a>  &emsp;&emsp;&emsp;
</p>

---

## 代理模式说明：

代理模式：

    在调用处不直接调用目标类进行操作，而是调用代理类，然后通过代理类来调用目标类进行操作。在代理类调用目标类的前后可以添加一些预处理和后处理操作来完成一些不属于目标类的功能。

使用代理的目的：

    通过代理模式可以实现对目标类调用的控制、在目标类调用前/后进行一些不属于目标类的操作，如：数据验证、预处理、后处理、异常处理等

什么是静态代理什么是动态代理？

    静态代理：代理类只能实现对”特定接口的实现类“进行代理
    动态代理：代理类可以实现对多种类的代理

jdk代理和cglib代理区别在哪里？

    jdk动态代理：代理所有“实现的有接口”的目标类
    cglib动态代理：代理任意一个目标类，但对final类和方法无法代理

    jdk动态代理的目标类必须实现的有接口,因为在调用Proxy.newProxyInstance()的时候需要传入目标类的接口类。而cglib不做此限制。

JDK动态代理与CGLIB动态代理对比

    JDK动态代理：基于Java反射机制实现，必须要实现了接口的业务类才能用这种办法生成代理对象。
                jdk动态代理是由Java内部的反射机制来实现的，目标类基于统一的接口（InvocationHandler）
    
    cglib动态代理：基于ASM机制实现，通过生成业务类的子类作为代理类。
                cglib动态代理底层则是借助asm来实现的，cglib这种第三方类库实现的动态代理应用更加广泛，且在效率上更有优势。
    
    JDK Proxy 的优势：    
        最小化依赖关系，减少依赖意味着简化开发和维护，JDK 本身的支持，可能比 cglib 更加可靠。
        平滑进行 JDK 版本升级，而字节码类库通常需要进行更新以保证在新版 Java 上能够使用。
        代码实现简单。

    基于类似 cglib 框架的优势：    
        无需实现接口，达到代理类无侵入
        只操作我们关心的类，而不必为其他相关类增加工作量。
        高性能

动态代理的几种实现方式？分别说出相应的优缺点

    代理可以分为 "静态代理" 和 "动态代理"，动态代理又分为 "JDK动态代理" 和 "CGLIB动态代理" 实现。

静态代理：

    代理对象和实际对象都继承了同一个接口，在代理对象中指向的是实际对象的实例，这样对外暴露的是代理对象而真正调用的是 Real Object

    优点：可以很好的保护实际对象的业务逻辑对外暴露，从而提高安全性。
    缺点：不同的接口要有不同的代理类实现，会很冗余

JDK 动态代理：

    为了解决静态代理中，生成大量的代理类造成的冗余；JDK 动态代理只需要实现 InvocationHandler 接口，重写 invoke 方法便可以完成代理的实现，
    jdk的代理是利用反射生成代理类 Proxyxx.class 代理类字节码，并生成对象 jdk动态代理之所以只能代理接口是因为代理类本身已经extends了Proxy，而java是不允许多重继承的，但是允许实现多个接口。

    优点：解决了静态代理中冗余的代理实现类问题。
    缺点：JDK 动态代理是基于接口设计实现的，如果没有接口，会抛异常。

CGLIB 代理：

    由于 JDK 动态代理限制了只能基于接口设计，而对于没有接口的情况，JDK方式解决不了；CGLib 采用了非常底层的字节码技术，
    其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑，来完成动态代理的实现。
    实现方式实现 MethodInterceptor 接口，重写 intercept 方法，通过 Enhancer 类的回调方法来实现。但是CGLib在创建代理对象时所花费的时间却比JDK多得多，
    所以对于单例的对象，因为无需频繁创建对象，用CGLib合适，反之，使用JDK方式要更为合适一些。 
    同时，由于CGLib由于是采用动态创建子类的方法，对于final方法，无法进行代理。


    优点：没有接口也能实现动态代理，而且采用字节码增强技术，性能也不错。
    缺点：技术实现相对难理解些。

- <a href="https://zhuanlan.zhihu.com/p/347141071#" target="_blank">深入理解Java动态代理 </a>
- <a href="https://cloud.tencent.com/developer/article/1429932#" target="_blank">Java 静态代理、Java动态代理、CGLIB动态代理 </a>
- <a href="https://www.cnblogs.com/whirly/p/10154887.html#" target="_blank">Java 动态代理详解 </a>
- <a href="https://blog.csdn.net/qq_45034708/article/details/115030032#" target="_blank">
  设计模式-代理模式（静态代理、动态代理、cglib代理） </a>

---

# 代理模式 实现：

<a href="https://www.runoob.com/design-pattern/proxy-pattern.html#" target="_blank">https://www.runoob.com/design-pattern/proxy-pattern.html </a>

	在代理模式（Proxy Pattern）中，一个类代表另一个类的功能。这种类型的设计模式属于结构型模式。

	在代理模式中，我们创建具有现有对象的对象，以便向外界提供功能接口。

    
    意图：为其他对象提供一种代理以控制对这个对象的访问。
    
    主要解决：	在直接访问对象时带来的问题，比如说：要访问的对象在远程的机器上。
                在面向对象系统中，有些对象由于某些原因（比如对象创建开销很大，或者某些操作需要安全控制，或者需要进程外的访问），
                直接访问会给使用者或者系统结构带来很多麻烦，我们可以在访问此对象时加上一个对此对象的访问层。
    
    何时使用：想在访问一个类时做一些控制。
    
    如何解决：增加中间层。
    
    关键代码：实现与被代理类组合。
    
    优点： 1、职责清晰。 2、高扩展性。 3、智能化。
            代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用；
            代理对象可以扩展目标对象的功能；
            代理模式能将客户端与目标对象分离，在一定程度上降低了系统的耦合度，增加了程序的可扩展性
    
    缺点： 	1、由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢。 
            2、实现代理模式需要额外的工作，有些代理模式的实现非常复杂。
            3、代理模式会造成系统设计中类的数量增加

            如何解决以上提到的缺点呢？答案是可以使用动态代理方式
    
    使用场景：按职责来划分，通常有以下使用场景： 	
        1、远程代理。 
        2、虚拟代理。 
        3、Copy-on-Write 代理。 
        4、保护（Protect or Access）代理。 
        5、Cache代理。 6、防火墙（Firewall）代理。 
        7、同步化（Synchronization）代理。 
        8、智能引用（Smart Reference）代理。

    注意事项： 	1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。 
                2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制。

![avatar](../blog/itlearn/softdesign/designproxy/imgs/img.png)

## demo code:

步骤 1 创建一个接口。

	Image.java
		public interface Image {
		   void display();
		}

步骤 2 创建实现接口的实体类。

	RealImage.java
		public class RealImage implements Image {
		 
		   private String fileName;
		 
		   public RealImage(String fileName){
			  this.fileName = fileName;
			  loadFromDisk(fileName);
		   }
		 
		   @Override
		   public void display() {
			  System.out.println("Displaying " + fileName);
		   }
		 
		   private void loadFromDisk(String fileName){
			  System.out.println("Loading " + fileName);
		   }
		}
	ProxyImage.java
		public class ProxyImage implements Image{
		 
		   private RealImage realImage;
		   private String fileName;
		 
		   public ProxyImage(String fileName){
			  this.fileName = fileName;
		   }
		 
		   @Override
		   public void display() {
			  if(realImage == null){
				 realImage = new RealImage(fileName);
			  }
			  realImage.display();
		   }
		}

步骤 3 当被请求时，使用 ProxyImage 来获取 RealImage 类的对象。

	ProxyPatternDemo.java
		public class ProxyPatternDemo {
		   
		   public static void main(String[] args) {
			  Image image = new ProxyImage("test_10mb.jpg");
		 
			  // 图像将从磁盘加载
			  image.display(); 
			  System.out.println("");
			  // 图像不需要从磁盘加载
			  image.display();  
		   }
		}
