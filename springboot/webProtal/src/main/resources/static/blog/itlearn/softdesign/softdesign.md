#软件设计模式

设计模式：  <a href="https://www.runoob.com/design-pattern/design-pattern-tutorial.html#" target="_blank">https://www.runoob.com/design-pattern/design-pattern-tutorial.html </a>

23种设计模式全面解析： <a href="http://c.biancheng.net/design_pattern/#" target="_blank">http://c.biancheng.net/design_pattern/ </a>

##设计模式的六大原则
###1、开闭原则（Open Close Principle）
    开闭原则的意思是：对扩展开放，对修改关闭。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。
    简言之，是为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，
    后面的具体设计中我们会提到这点。

###2、里氏代换原则（Liskov Substitution Principle）
    里氏代换原则是面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。
    LSP 是继承复用的基石，只有当派生类可以替换掉基类，且软件单位的功能不受到影响时，基类才能真正被复用，
    而派生类也能够在基类的基础上增加新的行为。里氏代换原则是对开闭原则的补充。实现开闭原则的关键步骤就是抽象化，
    而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。

###3、依赖倒转原则（Dependence Inversion Principle）
    这个原则是开闭原则的基础，具体内容：针对接口编程，依赖于抽象而不依赖于具体。

###4、接口隔离原则（Interface Segregation Principle）
    这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。它还有另外一个意思是：降低类之间的耦合度。
    由此可见，其实设计模式就是从大型软件架构出发、便于升级和维护的软件设计思想，它强调降低依赖，降低耦合。

###5、迪米特法则，又称最少知道原则（Demeter Principle）
    最少知道原则是指：一个实体应当尽量少地与其他实体之间发生相互作用，使得系统功能模块相对独立。

###6、合成复用原则（Composite Reuse Principle）
    合成复用原则是指：尽量使用合成/聚合的方式，而不是使用继承。


##设计模式的类型
###1、创建型模式 
    这些设计模式提供了一种在创建对象的同时隐藏创建逻辑的方式，而不是使用 new 运算符直接实例化对象。这使得程序在判断针对某个给定实例需要创建哪些对象时更加灵活。###
<a href="#" onclick="refreshDesignContent('designfactory')">工厂模式（Factory）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designabstractfactory')">抽象工厂模式（Abstract Factory）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designsingleton')">单例模式（Singleton）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designbuilder')">建造者模式（Builder）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designprototype')">原型模式（Prototype）</a>&emsp;&emsp;&emsp;

###2、结构型模式
    这些设计模式关注类和对象的组合。继承的概念被用来组合接口和定义组合对象获得新功能的方式
<a href="#" onclick="refreshDesignContent('designadapter')">适配器模式（Adapter）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designbridge')">桥接模式（Bridge）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designfilter')">过滤器模式（Filter、Criteria）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designcomposite')">组合模式（Composite）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designdecorator')">装饰器模式（Decorator）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designfacade')">外观模式（Facade）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designflyweight')">享元模式（Flyweight）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designproxy')">代理模式（Proxy）</a>&emsp;&emsp;&emsp;

###3、行为型模式
    这些设计模式特别关注对象之间的通信。
<a href="#" onclick="refreshDesignContent('designchain')">责任链模式（Chain of Responsibility）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designcommand')">命令模式（Command）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designinterpreter')">解释器模式（Interpreter）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designiterator')">迭代器模式（Iterator）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designmediator')">中介者模式（Mediator）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designmemento')">备忘录模式（Memento）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designobserver')">观察者模式（Observer）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designstate')">状态模式（State）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designnullobject')">空对象模式（Null Object）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designstrategy')">策略模式（Strategy）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designtemplate')">模板模式（Template）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designvisitor')">访问者模式（Visitor）</a>&emsp;&emsp;&emsp;
###4、J2EE 模式
    这些设计模式特别关注表示层。这些模式是由 Sun Java Center 鉴定的。
<a href="#" onclick="refreshDesignContent('designmvc')">MVC 模式（MVC）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designmvvm')">MVVM 模式（MVVM）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designbusinessdelegate')">业务代表模式（Business Delegate）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designcompositeentity')">组合实体模式（Composite Entity）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designdataaccessoject')">数据访问对象模式（Data Access Object）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designfrontcontroller')">前端控制器模式（Front Controller）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designinterceptingfilter')">拦截过滤器模式（Intercepting Filter）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designservicelocator')">服务定位器模式（Service Locator）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designtransferobject')">传输对象模式（Transfer Object）</a>&emsp;&emsp;&emsp;
