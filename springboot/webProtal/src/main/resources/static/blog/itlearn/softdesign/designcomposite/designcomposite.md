<p>
    <a href="#" onclick="showITLearnPage('softdesign')">返回设计模式目录</a>
</p>

# 组合模式（Composite）


Composite 模式定义:

将对象以树形结构组织起来,以达成“部分－整体” 的层次结构，使得客户端对单个对象和组合对象的使用具有一致性。

Composite 比较容易理解，想到 Composite 就应该想到树形结构图。
组合体内这些对象都有共同接口,当组合体一个对象的方法被调用执行时，Composite 将遍历(Iterator)整个树形结构,寻找同样包含这个方法的对象并实现调用执行。
可以用牵一动百来形容。所以 Composite 模式使用到 Iterator 模式，和 Chain of Responsibility 模式类似


Composite 好处:

    1.使客户端调用简单，客户端可以一致的使用组合结构或其中单个对象，用户就不必关系自己处理的是单个对象还是整个组合结构，这就简化了客户端代码。
    2.更容易在组合体内加入对象部件. 客户端不必因为加入了新的对象部件而更改代码。


如何使用 Composite?

    首先定义一个接口或抽象类，这是设计模式通用方式了，其他设计模式对接口内部定义限制
    不多，Composite 却有个规定，那就是要在接口内部定义一个用于访问和管理 Composite 组合体的对象们（或称部件 Component）. 

下面的代码是以抽象类定义，一般尽量用接口 interface,

    public abstract class Equipment
    {
         private String name;
         
         //实价
         public abstract double netPrice();
         
         //折扣价格
         public abstract double discountPrice();
         
         //增加部件方法
         public boolean add(Equipment equipment) { 
            return false; 
         }
         
         //删除部件方法
         public boolean remove(Equipment equipment) { 
            return false; 
         }
         
         //注意这里，这里就提供一种用于访问组合体类的部件方法。
         public Iterator iter() { 
            return null; 
         }

         public Equipment(final String name)
         {
            this.name=name; 
         }
    }

抽象类 Equipment 就是 Component 定义，代表着组合体类的对象们,Equipment 中定义几个共同的方法。

    public class Disk extends Equipment
    {
         public Disk(String name) {
            super(name); 
         }
         
         //定义 Disk 实价为 1
         public double netPrice() { 
            return 1.; 
         }
         
         //定义了 disk 折扣价格是 0.5 对折。
         public double discountPrice() { 
            return .5;
         }
    } 

待续。。。




