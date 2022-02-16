# 软件设计模式

设计模式：  <a href="https://www.runoob.com/design-pattern/design-pattern-tutorial.html#" target="_blank">https://www.runoob.com/design-pattern/design-pattern-tutorial.html </a>

23种设计模式全面解析： <a href="http://c.biancheng.net/design_pattern/#" target="_blank">http://c.biancheng.net/design_pattern/ </a>

## 设计模式的六大原则

### 1、开闭原则（Open Close Principle）

    开闭原则的意思是：对扩展开放，对修改关闭。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。
    简言之，是为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，
    后面的具体设计中我们会提到这点。

### 2、里氏代换原则（Liskov Substitution Principle）

    里氏代换原则是面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。
    LSP 是继承复用的基石，只有当派生类可以替换掉基类，且软件单位的功能不受到影响时，基类才能真正被复用，
    而派生类也能够在基类的基础上增加新的行为。里氏代换原则是对开闭原则的补充。实现开闭原则的关键步骤就是抽象化，
    而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。

### 3、依赖倒转原则（Dependence Inversion Principle）

    这个原则是开闭原则的基础，具体内容：针对接口编程，依赖于抽象而不依赖于具体。

### 4、接口隔离原则（Interface Segregation Principle）

    这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。它还有另外一个意思是：降低类之间的耦合度。
    由此可见，其实设计模式就是从大型软件架构出发、便于升级和维护的软件设计思想，它强调降低依赖，降低耦合。

### 5、迪米特法则，又称最少知道原则（Demeter Principle）

    最少知道原则是指：一个实体应当尽量少地与其他实体之间发生相互作用，使得系统功能模块相对独立。

### 6、合成复用原则（Composite Reuse Principle）

    合成复用原则是指：尽量使用合成/聚合的方式，而不是使用继承。

## 设计模式的类型

### 1、创建型模式

    这些设计模式提供了一种在创建对象的同时隐藏创建逻辑的方式，而不是使用 new 运算符直接实例化对象。这使得程序在判断针对某个给定实例需要创建哪些对象时更加灵活。###

<p>
<a href="#" onclick="refreshDesignContent('designfactory')">工厂模式（Factory）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designabstractfactory')">抽象工厂模式（Abstract Factory）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designsingleton')">单例模式（Singleton）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designbuilder')">建造者模式（Builder）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designprototype')">原型模式（Prototype）</a>&emsp;&emsp;&emsp;
</p>

### 2、结构型模式

    这些设计模式关注类和对象的组合。继承的概念被用来组合接口和定义组合对象获得新功能的方式

<p>
<a href="#" onclick="refreshDesignContent('designadapter')">适配器模式（Adapter）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designbridge')">桥接模式（Bridge）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designfilter')">过滤器模式（Filter、Criteria）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designcomposite')">组合模式（Composite）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designdecorator')">装饰器模式（Decorator）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designfacade')">外观模式（Facade）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designflyweight')">享元模式（Flyweight）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designproxy')">代理模式（Proxy）</a>&emsp;&emsp;&emsp;
</p>

### 3、行为型模式

    这些设计模式特别关注对象之间的通信。

<p>
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
</p>

### 4、J2EE 模式

    这些设计模式特别关注表示层。这些模式是由 Sun Java Center 鉴定的。

<p>
<a href="#" onclick="refreshDesignContent('designmvc')">MVC 模式（MVC）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designmvvm')">MVVM 模式（MVVM）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designbusinessdelegate')">业务代表模式（Business Delegate）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designcompositeentity')">组合实体模式（Composite Entity）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designdataaccessoject')">数据访问对象模式（Data Access Object）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designfrontcontroller')">前端控制器模式（Front Controller）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designinterceptingfilter')">拦截过滤器模式（Intercepting Filter）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designservicelocator')">服务定位器模式（Service Locator）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshDesignContent('designtransferobject')">传输对象模式（Transfer Object）</a>&emsp;&emsp;&emsp;
</p>


##一句话归纳设计模式

创建型设计模式 （简单来说就是用来创建对象的）

|设计模式|简述|一句话归纳|目的|生活案例|
|---|---|---|---|---|---|
|工厂模式（Factory Pattern）|不同条件下创建不同实例|产品标准化，生产更高效|封装创建细节|实体工厂|
|单例模式（Singleton Pattern）|保证一个类仅有一个实例，并且提供一个全局访问点|世上只有一个我|保证独一无二|CEO|
|原型模式（Prototype Pattern）|通过拷贝原型创建新的对象|拔一根猴毛，吹出千万个|高效创建对象|克隆|
|建造者模式（Builder Pattern）|用来创建复杂的复合对象|高配中配和低配，想选哪配就哪配|开放个性配置步骤|选配|

结构型设计模式 （关注类和对象的组合）

|设计模式|简述|一句话归纳|目的|生活案例|
|---|---|---|---|---|---|
|代理模式（Proxy Pattern）|为其他对象提供一种代理以控制对这个对象的访问|没有资源没时间，得找别人来帮忙|增强职责|媒婆|
|外观模式（Facade Pattern）|对外提供一个统一的接口用来访问子系统|打开一扇门，通向全世界|统一访问入口|前台|
|装饰器模式（Decorator Pattern）|为对象添加新功能|他大舅他二舅都是他舅|灵活扩展、同宗同源|煎饼|
|享元模式（Flyweight Pattern）|使用对象池来减少重复对象的创建|优化资源配置，减少重复浪费|共享资源池|全国社保联网|
|组合模式（Composite Pattern）|将整体与局部（树形结构）进行递归组合，让客户端能够以一种的方式对其进行处理|人在一起叫团伙，心在一起叫团队|统一整体和个体|组织架构树|
|适配器模式（Adapter Pattern）|将原来不兼容的两个类融合在一起|万能充电器|兼容转换|电源适配|
|桥接模式（Bridge Pattern）|将两个能够独立变化的部分分离开来|约定优于配置|不允许用继承|桥|

行为型设计模式 （关注对象之间的通信）

|设计模式|简述|一句话归纳|目的|生活案例|
|---|---|---|---|---|---|
|模板模式（Template Pattern）|定义一套流程模板，根据需要实现模板中的操作|流程全部标准化，需要微调请覆盖|逻辑复用|把大象装进冰箱|
|策略模式（Strategy Pattern）|封装不同的算法，算法之间能互相替换|条条大道通罗马，具体哪条你来定|把选择权交给用户|选择支付方式|
|责任链模式（Chain of Responsibility Pattern）|拦截的类都实现统一接口，每个接收者都包含对下一个接收者的引用。将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。|各人自扫门前雪，莫管他们瓦上霜|解耦处理逻辑|踢皮球|
|迭代器模式（Iterator Pattern）|提供一种方法顺序访问一个聚合对象中的各个元素|流水线上坐一天，每个包裹扫一遍|统一对集合的访问方式|逐个检票进站|
|命令模式（Command Pattern）|将请求封装成命令，并记录下来，能够撤销与重做|运筹帷幄之中，决胜千里之外|解耦请求和处理|遥控器|
|状态模式（State Pattern）|根据不同的状态做出不同的行为|状态驱动行为，行为决定状态|绑定状态和行为|订单状态跟踪|
|备忘录模式（Memento Pattern）|保存对象的状态，在需要时进行恢复|失足不成千古恨，想重来时就重来|备份、后悔机制|草稿箱|
|中介者模式（Mediator Pattern）|将对象之间的通信关联关系封装到一个中介类中单独处理，从而使其耦合松散|联系方式我给你，怎么搞定我不管|统一管理网状资源|朋友圈|
|解释器模式（Interpreter Pattern）|给定一个语言，定义它的语法表示，并定义一个解释器，这个解释器使用该标识来解释语言中的句子|我想说”方言“，一切解释权都归我|实现特定语法解析|摩斯密码|
|观察者模式（Observer Pattern）|状态发生改变时通知观察者，一对多的关系|到点就通知我|解耦观察者与被观察者|闹钟|
|访问者模式（Visitor Pattern）|稳定数据结构，定义新的操作行为|横看成岭侧成峰，远近高低各不同|解耦数据结构和数据操作|KPI考核|
|委派模式（Delegate Pattern）|允许对象组合实现与继承相同的代码重用，负责任务的调用和分配|这个需求很简单，怎么实现我不管|只对结果负责|授权委托书|


![avatar](../blog/itlearn/softdesign/img.png)

 <style>
  table{
    border-left:1px solid #000000;border-top:1px solid #000000;
    width: 100%;
    word-wrap:break-word; word-break:break-all;
  }
  table th{
  text-align:center;
  }
  table th,td{
    border-right:1px solid #000000;border-bottom:1px solid #000000;
  }
</style>