<p>
    <a href="#" onclick="refreshContent('messagemq')">返回目录</a>
</p>

# MessageMQ -activemq
 
##什么是ActiveMQ
 
     ActiveMQ是一种开源的基于JMS（Java Message Servie）规范的一种消息中间件的实现，ActiveMQ的设计目标是提供标准的，
     面向消息的，能够跨越多语言和多系统的应用集成消息通信中间件。
 
##对于消息的传递有两种类型：
 
     点对点的，即一个生产者和一个消费者一一对应。
     发布/订阅模式，即一个生产者产生消息并进行发送后，可以由多个消费者进行接收
 
 
##五种发送与接收的数据格式：
 
    · StreamMessage -- Java原始值的数据流
    · MapMessage--一套名称-值对
    · TextMessage--一个字符串对象
    · ObjectMessage--一个序列化的 Java对象
    · BytesMessage--一个字节的数据流
 
##特点(作用)
     应用解耦
     异步通信
     流量削峰
     (海量)日志处理
     消息通讯
 
## ActiveMQ服务器的安装
     ActiveMQ是java语言开发的，需要使用到jdk，这里介绍在Linux系统种进行安装。具体步骤如下：
 
####第一步：下载ActiveMQ压缩包，上传并解压到Linux系统
 
###第二步：进入解压包路径/bin目录下，启动ActivceMQ，相关操作命令如下：
 
         启动：
         [root@localhost bin]# ./activemq start
         关闭：
         [root@localhost bin]# ./activemq stop
         查看状态：
         [root@localhost bin]# ./activemq status
 
###第三步：检测是否启动成功，
         在浏览器中访问网址：ip:8161/admin，在不修改配置文件的前提下，8161端口为默认端口，用户名：admin，密码：admin
 
##ActiveMQ的使用模式
     ActiveMQ一共有三种使用模式，分别是Queue点对点模式，Topic发布订阅模式和Spring整合模式。
 
     Queue 是点对点模式，只能是一个生产者产生一个消息，被一个消费者消费。默认是存在于MQ的服务器中的，发送消息之后，消费者随时取。但是一定是一个消费者取，消费完消息也就没有了。
 
     Topic 是发布订阅模式，一个生产者可以一个消息，可以被多个消费者消费。默认是不存在于MQ服务器中的，一旦发送之后，如果没有订阅，消息则丢失。
 
     Spring整合模式，是基于Spring框架进行使用，特点是使用非常简单，可以任意的切换运用Queue和Topic两种模式
 
 
 ##点对点(P2P)模型 和 发布/订阅(Pub/Sub)模型 两种模式对比
     1）由以上，我们可以总结出ActiveMQ的实现步骤：
 
         建立ConnectionFactory工厂对象，需要填入用户名、密码、连接地址
         通过ConnectionFactory对象创建一个Connection连接
         通过Connection对象创建Session会话
         通过Session对象创建Destination对象；在P2P的模式中，Destination被称作队列（Queue），在Pub/Sub模式中，Destination被称作主题（Topic）
         通过Session对象创建消息的发送和接收对象
         发送消息
         关闭资源
     2）可以看出，P2P模式和Pub/Sub模式，在实现上的区别是通过Session创建的Destination对象不一样，
         在P2P的模式中，Destination被称作队列（Queue），在Pub/Sub模式中，Destination被称作主题（Topic）
 
 
 ##demo:
 基于队列点对点的传输类型  即队列中的数据会平均的分给每一个消费者消费，且每一条数据只能被消费一次
 
 
     引入依赖
 
     <dependency>
         <groupId>org.apache.activemq</groupId>
         <artifactId>activemq-all</artifactId>
         <version>5.9.0</version>
     </dependency>
 
     /**
      * @Description 生产者
      * @Date 2019/7/20
      * @Created by yqh
      */
     public class MyProducer {
 
         private static final String ACTIVEMQ_URL = "tcp://192.168.168.242:61616";
 
         public static void main(String[] args) throws JMSException {
             // 创建连接工厂
             ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
             // 创建连接
             Connection connection = activeMQConnectionFactory.createConnection();
             // 打开连接
             connection.start();
             // 创建会话
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             // 创建队列目标,并标识队列名称，消费者根据队列名称接收数据
             Destination destination = session.createQueue("myQueue");
             // 创建一个生产者
             MessageProducer producer = session.createProducer(destination);
             // 向队列推送10个文本消息数据
             for (int i = 1 ; i <= 10 ; i++){
                 // 创建文本消息
                 TextMessage message = session.createTextMessage("第" + i + "个文本消息");
                 //发送消息
                 producer.send(message);
                 //在本地打印消息
                 System.out.println("已发送的消息：" + message.getText());
             }
             //关闭连接
             connection.close();
         }
     }
 
     /**
      * @Description 消费者类
      * @Date 2019/7/20 0020
      * @Created by yqh
      */
     public class MyConsumer {
 
         private static final String ACTIVEMQ_URL = "tcp://192.168.168.242:61616";
 
         public static void main(String[] args) throws JMSException {
             // 创建连接工厂
             ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
             // 创建连接
             Connection connection = activeMQConnectionFactory.createConnection();
             // 打开连接
             connection.start();
             // 创建会话
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             // 创建队列目标,并标识队列名称，消费者根据队列名称接收数据
             Destination destination = session.createQueue("myQueue");
             // 创建消费者
             MessageConsumer consumer = session.createConsumer(destination);
             // 创建消费的监听
             consumer.setMessageListener(new MessageListener() {
                 public void onMessage(Message message) {
                     TextMessage textMessage = (TextMessage) message;
                     try {
                         System.out.println("消费的消息：" + textMessage.getText());
                     } catch (JMSException e) {
                         e.printStackTrace();
                     }
                 }
             });
         }
     }
 
 
##基于发布/订阅模式传输的类型测试
     现在如果我们先启动生产者，再启动消费者，会发现消费者是无法接收到之前生产者之前所生产的数据，只有消费者先启动，
     再让生产者消费才可以正常接收数据，这也是发布/订阅的主题模式与点对点的队列模式的一个明显区别。
 
     而如果启动两个消费者，那么每一个消费者都能完整的接收到生产者生产的数据，即每一条数据都被消费了两次，
     这是发布/订阅的主题模式与点对点的队列模式的另一个明显区别
 
     /**
      * @Description 基于发布/订阅模式传输类型的生产者测试
      * @Date 2019/7/20 0020
      * @Created by yqh
      */
     public class MyProducerForTopic {
     
         private static final String ACTIVEMQ_URL = "tcp://192.168.168.242:61616";
     
         public static void main(String[] args) throws JMSException {
             // 创建连接工厂
             ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
             // 创建连接
             Connection connection = activeMQConnectionFactory.createConnection();
             // 打开连接
             connection.start();
             // 创建会话
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             // 创建队列目标,并标识队列名称，消费者根据队列名称接收数据
             Destination destination = session.createTopic("topicTest");
             // 创建一个生产者
             MessageProducer producer = session.createProducer(destination);
             // 向队列推送10个文本消息数据
             for (int i = 1 ; i <= 10 ; i++){
                 // 创建文本消息
                 TextMessage message = session.createTextMessage("第" + i + "个文本消息");
                 //发送消息
                 producer.send(message);
                 //在本地打印消息
                 System.out.println("已发送的消息：" + message.getText());
             }
             //关闭连接
             connection.close();
         }
     }
     /**
      * @Description 基于发布/订阅模式传输类型的消费者测试
      * @Date 2019/7/20 0020
      * @Created by yqh
      */
     public class MyConsumerForTopic {
     
         private static final String ACTIVEMQ_URL = "tcp://192.168.168.242:61616";
     
         public static void main(String[] args) throws JMSException {
             // 创建连接工厂
             ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
             // 创建连接
             Connection connection = activeMQConnectionFactory.createConnection();
             // 打开连接
             connection.start();
             // 创建会话
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             // 创建队列目标,并标识队列名称，消费者根据队列名称接收数据
             Destination destination = session.createTopic("topicTest");
             // 创建消费者
             MessageConsumer consumer = session.createConsumer(destination);
             // 创建消费的监听
             consumer.setMessageListener(new MessageListener() {
                 public void onMessage(Message message) {
                     TextMessage textMessage = (TextMessage) message;
                     try {
                         System.out.println("消费的消息：" + textMessage.getText());
                     } catch (JMSException e) {
                         e.printStackTrace();
                     }
                 }
             });
         }
     }
 
