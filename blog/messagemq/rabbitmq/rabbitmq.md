<p>
    <a href="#" onclick="refreshContent('messagemq')">返回目录</a>
</p>

---

# MessageMQ -rabbitmq

- <a href="https://blog.csdn.net/qq_41936805/article/details/88896623#" target="_blank">SpringBoot框架学习(九)——消息队列 </a>
- <a href="https://blog.csdn.net/wangbing25307/article/details/80845641#" target="_blank">RabbitMQ的简单使用 </a>
- <a href="https://blog.csdn.net/qq_41936805/article/details/88896623##" target="_blank">SpringBoot框架学习(九)——消息队列 </a>

## 核心概念

    Message ：消息，消息是不具名的，它由消息头和消息体组成。消息体是不透明的，而消息头则由一系列的可选属性组成，这些属性包括routing-key（路由键）、 
        priority（相对于其他消息的优先权）、 delivery-mode（指出该消息可能需要持久性存储）等。

    Publisher：消息的生产者，也是一个向交换器发布消息的客户端应用程序

    Exchange：交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列。
        Exchange有4种类型： direct(默认)， fanout, topic, 和headers，不同类型的Exchange转发消息的策略有所区别

    Queue：消息队列，用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走。

    Binding：绑定，用于消息队列和交换器之间的关联。一个绑定就是基于路由键将交换器和消息队列连
        接起来的路由规则，所以可以将交换器理解成一个由绑定构成的路由表。Exchange 和Queue的绑定可以是多对多的关系。
    Connection：网络连接，比如一个TCP连接。
    Channel：信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的TCP连接内的虚拟连接， 
        AMQP 命令都是通过信道发出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成。
        因为对于操作系统来说建立和销毁 TCP 都是非常昂贵的开销，所以引入了信道的概念，以复用一条 TCP 连接。
    Consumer：消息的消费者，表示一个从消息队列中取得消息的客户端应用程序。
    Virtual Host：虚拟主机，表示一批交换器、消息队列和相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。
        每个 vhost 本质上就是一个 mini 版的 RabbitMQ 服务器，拥有自己的队列、交换器、绑定和权限机制。 vhost 是 AMQP 概念的基础，必须在连接时指定，RabbitMQ 默认的 vhost 是/。

## RabbitMQ特点

    高可靠：RabbitMQ 提供了多种多样的特性让你在可靠性和性能之间做出权衡，包括持久化、发送应答、发布确认以及高可用性。

    高可用队列：支持跨机器集群，支持队列安全镜像备份，消息的生产者与消费者不论哪一方出现问题，均不会影响消息的正常发出与接收。

    灵活的路由：所有的消息都会通过路由器转发到各个消息队列中，RabbitMQ内建了几个常用的路由器，并且可以通过路由器的组合以及自定义路由器插件来完成复杂的路由功能。

    支持多客户端：对主流开发语言（如：Python、Ruby、.NET、Java、C、PHP、ActionScript等）都有客户端实现。

    集群：本地网络内的多个Server可以聚合在一起，共同组成一个逻辑上的broker。

    扩展性：支持负载均衡，动态增减服务器简单方便。

    权限管理：灵活的用户角色权限管理，Virtual Host是权限控制的最小粒度。

    插件系统：支持各种丰富的插件扩展，同时也支持自定义插件，其中最常用的插件是web管理工具rabbitmq_management

## 6种基本用法：

    单对单、
    单对多、
    发布订阅模式、
    按路由规则发送接收、
    主题、
    RPC（即远程存储调用）
## RabbitMQ如何防止消息丢失

    1. 消息确认机制（ACK）
        RabbitMQ有一个ACK机制，消费者在接收到消息后会向mq服务发送回执ACK，告知消息已被接收。这种ACK分为两种情况：

        自动ACK：消息一旦被接收，消费者会自动发送ACK
        手动ACK：消息接收后，不会自动发送ACK，而是需要手动发送ACK
        如果消费者没有发送ACK，则消息会一直保留在队列中，等待下次接收。但这里存在一个问题，就是一旦消费者发送了ACK，
        如果消费者后面宕机，则消息会丢失。因此自动ACK不能保证消费者在接收到消息之后能够正常完成业务功能，因此需要在消息被充分利用之后，手动ACK确认

        自动ACK，basicConsume方法中将autoAck参数设为true即可：

        手动ack，在匿名内部类中，手动发送ACK：
        当然，如果设置了手动ack，但又不手动发送ACK确认，消息会一直停留在队列中，可能造成消息的重复获取

    2. 持久化
        消息确认机制（ACK）能够保证消费者不丢失消息，但假如消费者在获取消息之前mq服务宕机，则消息也会丢失，
        因此要保证消息在服务端不丢失，则需要将消息进行持久化。队列、交换机、消息都要持久化。
    3. 生产者确认
        生成者在发送消息过程中也可能出现错误或者网络延迟灯故障，导致消息未成功发送到交换机或者队列，或重复发送消息，
        为了解决这个问题，rabbitmq中有多个解决办法：
            事务：
            Confirm模式：
            异步confirm方法：
## demo:

###基本消息模型
        public class ConnectionUtil {
            /**
             * 建立与RabbitMQ的连接
             * @return
             * @throws Exception
             */
            public static Connection getConnection() throws Exception {
                //定义连接工厂
                ConnectionFactory factory = new ConnectionFactory();
                //设置服务地址
                factory.setHost("192.168.18.130");
                //端口
                factory.setPort(5672);
                //设置账号信息，用户名、密码、vhost
                factory.setUsername("admin");
                factory.setPassword("admin");
                // 通过工程获取连接
                Connection connection = factory.newConnection();
                return connection;
            }
        }
        接下来是生产者发送消息，其过程包括：
        1.与mq服务建立连接，2.建立通道，3.声明队列（有相同队列则不创建，没有则创建），4.发送消息，代码如下：

        public class Send {
            private static final String QUEUE_NAME = "basic_queue";
            public static void main(String[] args) throws Exception {
                //消息发送端与mq服务创建连接
                Connection connection = ConnectionUtil.getConnection();
                //建立通道
                Channel channel = connection.createChannel();
                //声明队列
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "hello world";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("生产者已发送：" + message);
                channel.close();
                connection.close();
            }
        }
        消费者在接收消息的过程需要经历如下几个步骤： 1.与mqfuwu建立连接，2.建立通道，3.声明队列，4，接收消息，代码如下：

        public class Consumer1 {
            private static final String QUEUE_NAME = "basic_queue";
            public static void main(String[] args) throws Exception {
                //消息消费者与mq服务建立连接
                Connection connection = ConnectionUtil.getConnection();
                //建立通道
                Channel channel = connection.createChannel();
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                DefaultConsumer consumer = new DefaultConsumer(channel) {
                    // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                               byte[] body) throws IOException {
                        // body 即消息体
                        String msg = new String(body);
                        System.out.println("消费者1接收到消息：" + msg);
                    }
                };
                channel.basicConsume(QUEUE_NAME, true, consumer);
            }
        }

###Work Queues工作队列模型
        在基本消息模型中，一个生产者对应一个消费者，而实际生产过程中，往往消息生产会发送很多条消息，如果消费者只有一个的话效率就会很低，
        因此rabbitmq有另外一种消息模型， 这种模型下，一个生产发送消息到队列，允许有多个消费者接收消息，但是一条消息只会被一个消费者获取
    
        //消息生产者
        public static void main(String[] args) throws Exception {
        //获取连接和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "";
        for(int i = 0; i<100; i++){
        message = "" + i;
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("发送消息："+message);
        Thread.sleep(i);
        }
    
            channel.close();
            connection.close();
        }
    
        //消费者1
        public static void main(String[] args) throws Exception {
            Connection connection = ConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
    
            //同一时刻服务器只发送一条消息给消费端
            channel.basicQos(1);
    
            QueueingConsumer consumer = new QueueingConsumer(channel);
    
            channel.basicConsume(QUEUE_NAME,false,consumer);
    
            while(true){
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println("recive1:"+message);
                Thread.sleep(100);
                //消息消费完给服务器返回确认状态，表示该消息已被消费
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            }
        }
    
        //生产者2
        public static void main(String[] args) throws Exception {
            Connection connection = ConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
    
            channel.basicQos(1);
    
            QueueingConsumer consumer = new QueueingConsumer(channel);
    
            channel.basicConsume(QUEUE_NAME,true,consumer);
    
            while(true){
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println("recive1:"+message);
                Thread.sleep(10);
                //channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            }
        }
    
    消息消费的两种模式
    1、 自动模式
    
        消费者从消息队列获取消息后，服务端就认为该消息已经成功消费。
    
    2、 手动模式
    
        消费者从消息队列获取消息后，服务端并没有标记为成功消费
        消费者成功消费后需要将状态返回到服务端
    
    自动模式：
        channel.basicConsume(QUEUE_NAME,true,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("recive1:"+message);
            Thread.sleep(10);
            //无需反馈
            //channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    手动模式：
        channel.basicConsume(QUEUE_NAME,false,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("recive1:"+message);
            Thread.sleep(100);
            //消息消费完给服务器返回确认状态，表示该消息已被消费
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

###订阅模型
        在之前的模型中，一条消息只能被一个消费者获取，而在订阅模式中，可以实现一条消息被多个消费者获取。在这种模型下，
        消息传递过程中比之前多了一个exchange交换机，生产者不是直接发送消息到队列，而是先发送给交换机，经由交换机分配到不同的队列，而每个消费者都有自己的队列
        
        1、1个生产者，多个消费者

        2、每一个消费者都有自己的一个队列

        3、生产者没有将消息直接发送到队列，而是发送到了交换机

        4、每个队列都要绑定到交换机

        5、生产者发送的消息，经过交换机到达队列，实现一个消息被多个消费者获取的目的

    public static final String EXCHANGE_NAME = "test_exchange_fanout";
    //生产者，发送消息到交换机
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机 fanout：交换机类型 主要有fanout,direct,topics三种
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println(message);
        channel.close();
        connection.close();
    }

    //消费者1
    public final static String QUEUE_NAME = "test_queue_exchange_1";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机上
        channel.queueBind(QUEUE_NAME,Send.EXCHANGE_NAME,"");
        channel.basicQos(1);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,true,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(message);
        }
    }

    //消费者2
    public final static String QUEUE_NAME = "test_queue_exchange_2";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机上
        channel.queueBind(QUEUE_NAME,Send.EXCHANGE_NAME,"");
        channel.basicQos(1);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,true,consumer);

        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(message);
        }
    }
### 路由模式
    1、 发送消息到交换机并且要指定路由key
    
    2、 消费者将队列绑定到交换机时需要指定路由key
    
    是一种完全匹配，只有匹配到的消费者才能消费消息
    
    消息中的路由键（ routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。
    路由键与队列名完全匹配，如果一个队列绑定到交换机要求路由键为“ dog”，则只转发 routing key 标记为“ dog”的消息，不会转发“ dog.puppy”，也不会转发“ dog.guard”等等。它是完全匹配、单播的模式。

    public static final String EXCHANGE_NAME = "test_exchange_direct";
    //生产者
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机 fanout：交换机类型 主要有fanout,direct,topics三种
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME,"dog",null,message.getBytes());
        System.out.println(message);
        channel.close();
        connection.close();
    }

    //消费者1
    public final static String QUEUE_NAME = "test_queue_direct_1";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机上,并制定路由键为"dog"
        channel.queueBind(QUEUE_NAME, com.bw.rabbitmq.routing.Send.EXCHANGE_NAME,"dog");
        channel.basicQos(1);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,true,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(message);
        }
    }

    //消费者2
    public final static String QUEUE_NAME = "test_queue_direct_2";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机上,并制定路由键为"cat"
        channel.queueBind(QUEUE_NAME, com.bw.rabbitmq.routing.Send.EXCHANGE_NAME,"cat");
        channel.basicQos(1);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,true,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(message);
        }
    }

