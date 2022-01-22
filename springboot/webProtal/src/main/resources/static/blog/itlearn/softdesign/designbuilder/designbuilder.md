<p>
    <a href="#" onclick="showITLearnPage('softdesign')">返回设计模式目录</a>
</p>

# Builder 模式


将一个复杂对象的构建与它的表示分离,使得同样的构建过程可以创建不同的表示. 

使用builder的原因：

    为了就爱那个构建对象的过程和它的部件进行解耦合，  
    例如：
        一个复杂的对象,不但有很多大量组成部分,如汽车,有很多部件:车轮 方向盘 发动机
        还有各种小零件等等,部件很多,但远不止这些,如何将这些部件装配成一辆汽车,这个装配
        过程也很复杂(需要很好的组装技术),Builder 模式就是为了将部件和组装过程分开
        


如何使用 Builder 模式 呢？

    首先假设一个复杂对象是由多个部件组成的,Builder 模式是把复杂对象的创建和部件的创建分别开来,分别用 Builder 类和 Director 类来表示.
    
    首先,需要一个接口,它定义如何创建复杂对象的各个部件: 
    
    public interface Builder {
    
         //创建部件 A 比如创建汽车车轮
         void buildPartA();
         
         //创建部件 B 比如创建汽车方向盘
         void buildPartB();
         
         //创建部件 C 比如创建汽车发动机
         void buildPartC();
         
         //返回最后组装成品结果 (返回最后装配好的汽车) 
         //成品的组装过程不在这里进行,而是转移到下面的 Director 类中进行.
         //从而实现了解耦过程和部件
         Product getResult();
    } 
    
    
    用 Director 构建最后的复杂对象,而在上面 Builder 接口中封装的是如何创建一个个部件(复杂对象是由这些部件组成的),也就是说 Director 的内容是如何将部件最后组装成成品:
    public class Director {
         private Builder builder;
         
         public Director( Builder builder ) {
            this.builder = builder;
         }
         
         // 将部件 partA partB partC 最后组成复杂对象
         //这里是将车轮 方向盘和发动机组装成汽车的过程
         public void construct() {
             builder.buildPartA();
             builder.buildPartB();
             builder.buildPartC();
         }
    }
    Builder 的具体实现 ConcreteBuilder:
    通过具体完成接口 Builder 来构建或装配产品的部件; 
    定义并明确它所要创建的是什么具体东西;
    提供一个可以重新获取产品的接口:
    public class ConcreteBuilder implements Builder {
         Part partA, partB, partC;
         
         public void buildPartA() {
            //这里是具体如何构建 partA 的代码
         };
         
         public void buildPartB() {
            //这里是具体如何构建 partB 的代码
         };
         
         public void buildPartC() {
            //这里是具体如何构建 partB 的代码
         };
         
         public Product getResult() {
            //返回最后组装成品结果
         };
    }
    复杂对象:产品 Product:
    public interface Product { }
    
    复杂对象的部件:
    public interface Part { }
    
    看看如何调用 Builder 模式: 
    ConcreteBuilder builder = new ConcreteBuilder();
    Director director = new Director( builder );
    director.construct();
    Product product = builder.getResult();

