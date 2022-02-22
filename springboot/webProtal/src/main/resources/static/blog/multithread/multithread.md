# Multithread 多线程
<p>
<a href="#" onclick="refreshMultithreadContent('lock')">锁的说明和使用 </a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshMultithreadContent('complicating')">高并发解决方案 </a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshMultithreadContent('thread')">线程的使用 </a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshMultithreadContent('pool')">线程池/连接池 </a>&emsp;&emsp;&emsp;
</p>

----

50个多线程面试题

<a href="https://blog.csdn.net/cmyperson/article/details/79610870#" target="_blank">https://blog.csdn.net/cmyperson/article/details/79610870 </a>

多线程面试题

<a href="https://blog.csdn.net/tanmomo/article/details/99671622#" target="_blank">https://blog.csdn.net/tanmomo/article/details/99671622 </a>

多线程模拟售票，多个窗口售票

<a href="https://www.cnblogs.com/majingang/p/9055669.html#" target="_blank">https://www.cnblogs.com/majingang/p/9055669.html </a>

## 并发编程三要素？

    1）原子性

    原子性指的是一个或者多个操作，要么全部执行并且在执行的过程中不被其他操作打断，要么就全部都不执行。

    2）可见性

    可见性指多个线程操作一个共享变量时，其中一个线程对变量进行修改后，其他线程可以立即看到修改的结果。

    3）有序性

    有序性，即程序的执行顺序按照代码的先后顺序来执行。

## JDK 7 提供了7个阻塞队列，如下

    1、ArrayBlockingQueue 数组结构组成的有界阻塞队列。

    此队列按照先进先出（FIFO）的原则对元素进行排序，但是默认情况下不保证线程公平的访问队列，即如果队列满了，那么被阻塞在外面的线程对队列访问的顺序是不能保证线程公平（即先阻塞，先插入）的。

    2、LinkedBlockingQueue一个由链表结构组成的有界阻塞队列

    此队列按照先出先进的原则对元素进行排序

    3、PriorityBlockingQueue支持优先级的无界阻塞队列

    4、DelayQueue支持延时获取元素的无界阻塞队列，即可以指定多久才能从队列中获取当前元素

    5、SynchronousQueue不存储元素的阻塞队列，每一个put必须等待一个take操作，否则不能继续添加元素。并且他支持公平访问队列。

    6、LinkedTransferQueue由链表结构组成的无界阻塞TransferQueue队列。相对于其他阻塞队列，多了tryTransfer和transfer方法

        transfer方法

        如果当前有消费者正在等待接收元素（take或者待时间限制的poll方法），transfer可以把生产者传入的元素立刻传给消费者。
        如果没有消费者等待接收元素，则将元素放在队列的tail节点，并等到该元素被消费者消费了才返回。

        tryTransfer方法

        用来试探生产者传入的元素能否直接传给消费者。，如果没有消费者在等待，则返回false。和上述方法的区别是该方法无论消费者是否接收，方法立即返回。而transfer方法是必须等到消费者消费了才返回。

    7、LinkedBlockingDeque链表结构的双向阻塞队列，优势在于多线程入队时，减少一半的竞争

## java多线程 —— 面试题集合（最全集合）

<a href="https://blog.csdn.net/chongbin007/article/details/91359728#" target="_blank">https://blog.csdn.net/chongbin007/article/details/91359728 </a>

## java面试题：分布式

<a href="https://www.cnblogs.com/expiator/p/10201004.html#" target="_blank">https://www.cnblogs.com/expiator/p/10201004.html </a>


