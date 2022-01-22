<p>
    <a href="#" onclick="showITLearnPage('softdesign')">返回设计模式目录</a>
</p>

# 中介者模式（Mediator）


Mediator 中介者模式定义:

    用一个中介对象来封装一系列关于对象交互行为

为何使用 Mediator?
    
    各个对象之间的交互操作非常多;每个对象的行为操作都依赖彼此对方,修改一个对象的行为,同时会涉及到修改很多其他对象的行为,如果使用 Mediator 模式,可以使各个对象间
    的耦合松散,只需关心和 Mediator 的关系,使多对多的关系变成了一对多的关系,可以降低系统的复杂性,提高可修改扩展性.


如何使用?

    首先 有一个接口,用来定义成员对象之间的交互联系方式:
        public interface Mediator { }

    Meiator 具体实现,真正实现交互操作的内容:
        public class ConcreteMediator implements Mediator {
            //假设当前有两个成员.
            private ConcreteColleague1 colleague1 = new ConcreteColleague1();
            private ConcreteColleague2 colleague2 = new ConcreteColleague2();
        }

再看看另外一个参与者:成员,因为是交互行为,都需要双方提供一些共同接口,这种要求在Visitor Observer 等模式中都是相同的.

    public class Colleague {
        private Mediator mediator;
        
        public Mediator getMediator() {
            return mediator;
        }
        
        public void setMediator( Mediator mediator ) {
            this.mediator = mediator;
        }
    }
    public class ConcreteColleague1 { }
    public class ConcreteColleague2 { }
    
每个成员都必须知道 Mediator,并且和 Mediator 联系,而不是和其他成员联系.至此,Mediator 模式框架完成,可以发现 Mediator 模式规定不是很多,大体框架也比较
简单,但实际使用起来就非常灵活.Mediator 模式在事件驱动类应用中比较多,

例如界面设计 GUI.;聊天,消息传递等,在聊天应用中,需要有一个 MessageMediator,专门负责 request/reponse 之间任务的调节.

MVC 是 J2EE 的一个基本模式,View Controller 是一种 Mediator,它是 Jsp 和服务器上应用程序间的 Mediator.

每太能理解透。。。