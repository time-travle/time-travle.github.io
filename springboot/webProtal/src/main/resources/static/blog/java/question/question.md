<p>
    <a href="#" onclick="refreshContent('java')">返回目录</a>
</p>

#java  question 日常积累 

---

## Q：这21个刁钻的HashMap面试题，我把阿里面试官吊打了

    https://cloud.tencent.com/developer/article/1629452
## Q：java面试题:HashMap面试题
- https://songzixian.com/javabook/265.html
- https://blog.csdn.net/ChrisLu777/article/details/106617512
- https://www.cnblogs.com/zengcongcong/p/11295349.html

## Q：HashMap为什么用到它
  
    HashMap 底层是 数组和链表
    HashMap可以接受null键值和值，而HashTable则不能
    HashMap是非synchronized
    HashMap很快
    以及HashMap储存的是键值对


## Q：HashMap 的工作原理？

    HashMap 底层是 hash 数组和单向链表实现，数组中的每个元素都是链表， jdk8后采用数组+链表+红黑树的数据结构
    由 Node 内部类（实现 Map.Entry<K,V>接口）实现，HashMap 通过 put & get 方法存储和获取。

    存储对象时，将 K/V 键值传给 put() 方法：
    ①、调用 hash(K) 方法计算 K 的 hash 值，然后结合数组长度，计算得数组下标；
    ②、调整数组大小（当容器中的元素个数大于 capacity * loadfactor 时，容器会进行扩容resize 为 2n）；
    ③、i.如果 K 的 hash 值在 HashMap 中不存在，则执行插入，若存在，则发生碰撞；
        ii.如果 K 的 hash 值在 HashMap 中存在，且它们两者 equals 返回 true，则更新键值对；
        iii. 如果 K 的 hash 值在 HashMap 中存在，且它们两者 equals 返回 false，则插入链表的尾部（尾插法）或者红黑树中（树的添加方式）。

    （JDK 1.7 之前使用头插法、JDK 1.8 使用尾插法）
    （注意：当碰撞导致链表大于 TREEIFY_THRESHOLD = 8 时，就把链表转换成红黑树）

    获取对象时，将 K 传给 get() 方法：
        ①、调用 hash(K) 方法（计算 K 的 hash 值）从而获取该键值所在链表的数组下标；
        ②、顺序遍历链表，equals()方法查找相同 Node 链表中 K 值对应的 V 值。

    hashCode 是定位的，存储位置；equals是定性的，比较两者是否相等

    
## Q：当两个对象的 hashCode 相同会发生什么？

    A：因为 hashCode 相同，不一定就是相等的（equals方法比较），所以两个对象所在数组的下标相同，"碰撞"就此发生。
    又因为 HashMap 使用链表存储对象，这个 Node 会存储到链表中。

## Q：HashMap & TreeMap & LinkedHashMap 使用场景？

    一般情况下，使用最多的是 HashMap。
    HashMap：在 Map 中插入、删除和定位元素时；
    TreeMap：在需要按自然顺序或自定义顺序遍历键的情况下；
    LinkedHashMap：在需要输出的顺序和输入的顺序相同的情况下

## Q：HashTable

    底层数组+链表实现，无论key还是value都不能为null，线程安全，实现线程安全的方式是在修改数据时锁住整个HashTable，效率低，ConcurrentHashMap做了相关优化
    初始size为11，扩容：newsize = olesize*2+1
    计算index的方法：index = (hash & 0x7FFFFFFF) % tab.length
## Q：HashMap

    底层数组+链表实现，可以存储null键和null值，线程不安全
    初始size为16，扩容：newsize = oldsize*2，size一定为2的n次幂
    扩容针对整个Map，每次扩容时，原来数组中的元素依次重新计算存放位置，并重新插入
    插入元素后才判断该不该扩容，有可能无效扩容（插入后如果扩容，如果没有再次插入，就会产生无效扩容）
    当Map中元素总数超过Entry数组的75%，触发扩容操作，为了减少链表长度，元素分配更均匀
    计算index方法：index = hash & (tab.length – 1)
## Q：ConcurrentHashMap

    底层采用分段的数组+链表实现，线程安全
    通过把整个Map分为N个Segment，可以提供相同的线程安全，但是效率提升N倍，默认提升16倍。(读操作不加锁，由于HashEntry的value变量是 volatile的，也能保证读取到最新的值。)
    Hashtable的synchronized是针对整张Hash表的，即每次锁住整张表让线程独占，ConcurrentHashMap允许多个修改操作并发进行，其关键在于使用了锁分离技术
    有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，操作完毕后，又按顺序释放所有段的锁
    扩容：段内扩容（段内元素超过该段对应Entry数组长度的75%触发扩容，不会对整个Map进行扩容），插入前检测需不需要扩容，有效避免无效扩容

## Q：HashMap 和 HashTable 有什么区别？

        ①、HashMap 是线程不安全的，HashTable 是线程安全的；
        ②、由于线程安全，所以 HashTable 的效率比不上 HashMap；
        ③、HashMap最多只允许一条记录的键为null，允许多条记录的值为null，而 HashTable 不允许；
        ④、HashMap 默认初始化数组的大小为16，HashTable 为 11，前者扩容时，扩大两倍，后者扩大两倍+1；
        ⑤、HashMap 需要重新计算 hash 值，而 HashTable 直接使用对象的 hashCode

- https://www.jianshu.com/p/75adf47958a7


## Q：如果两个键的hashcode相同，你如何获取值对象？

    HashMap会使用键对象的hashcode找到bucket位置，找到bucket位置之后，会调用keys.equals()方法去找到LinkedList中正确的节点，最终找到要找的值对象。
    从HashMap中get元素时，首先计算key的hashCode，找到数组中对应位置的某一元素，然后通过key的equals方法在对应位置的链表中找到需要的元素


## Q：HashTable和HashMap的区别有哪些？

    HashMap和Hashtable都实现了Map接口，但决定用哪一个之前先要弄清楚它们之间的分别。主要的区别有：线程安全性，同步(synchronization)，以及速度。

    理解HashMap是Hashtable的轻量级实现（非线程安全的实现，hashtable是非轻量级，线程安全的），都实现Map接口，主要区别在于：

    1、由于HashMap非线程安全，在只有一个线程访问的情况下，效率要高于HashTable

    2、HashMap允许将null作为一个entry的key或者value，而Hashtable不允许。

    3、HashMap把Hashtable的contains方法去掉了，改成containsValue和containsKey。因为contains方法容易让人引起误解。

    4、Hashtable继承自陈旧的Dictionary类，而HashMap是Java1.2引进的Map 的一个实现。

    5、Hashtable和HashMap扩容的方法不一样，HashTable中hash数组默认大小11，扩容方式是 old*2+1。HashMap中hash数组的默认大小是16，而且一定是2的指数，
        增加为原来的2倍，没有加1。

    6、两者通过hash值散列到hash表的算法不一样，HashTbale是古老的除留余数法，直接使用hashcode，而后者是强制容量为2的幂，重新根据hashcode计算hash值，
        在使用hash  位与  （hash表长度 – 1），也等价取膜，但更加高效，取得的位置更加分散，偶数，奇数保证了都会分散到。前者就不能保证。

    7、另一个区别是HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。
        所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException，
        但迭代器本身的remove()方法移除元素则不会抛出ConcurrentModificationException异常。但这并不是一个一定发生的行为，要看JVM。
        这条同样也是Enumeration和Iterator的区别。    
    
    8、HashMap可以存储null键和null值 Hashtable不可以存储null键和null值
    
    9、HashMap是线程不安全的  效率高   Hashtable是线程安全的，效率低，它的每个方法中都加入了Synchronize方法
    
    10、初始容量大小和每次扩充容量大小的不同 
        Hashtable默认的初始大小为11，之后每次扩充，容量变为原来的2n+1。HashMap默认的初始化大小为16。之后每次扩充，容量变为原来的2倍



## Q：为什么HashMap是线程不安全的，实际会如何体现？

    第一，如果多个线程同时使用put方法添加元素

    假设正好存在两个put的key发生了碰撞(hash值一样)，那么根据HashMap的实现，这两个key会添加到数组的同一个位置，
    这样最终就会发生其中一个线程的put的数据被覆盖。

    第二，如果多个线程同时检测到元素个数超过数组大小*loadFactor

    这样会发生多个线程同时对hash数组进行扩容，都在重新计算元素位置以及复制数据，但是最终只有一个线程扩容后的数组会赋给table，
    也就是说其他线程的都会丢失，并且各自线程put的数据也丢失。且会引起死循环的错误。        
    
    
## Q：能否让HashMap实现线程安全，如何做？

    1、直接使用Hashtable，但是当一个线程访问HashTable的同步方法时，其他线程如果也要访问同步方法，会被阻塞住。
        举个例子，当一个线程使用put方法时，另一个线程不但不可以使用put方法，连get方法都不可以，效率很低，现在基本不会选择它了。

    2、HashMap可以通过下面的语句进行同步：

    Collections.synchronizeMap(hashMap);
    
## Q：HashMap 中 put 方法过程？

    1.对key的hashCode做hash操作，然后再计算在bucket中的index（1.5 HashMap的哈希函数）；
    2.如果没碰撞直接放到bucket里；
    3.如果碰撞了，以链表的形式存在buckets后；
    4.如果节点已经存在就替换old value(保证key的唯一性)
    5.如果bucket满了(超过阈值，阈值=loadfactor*current capacity，load factor默认0.75)，就要resize


## Q：hashMap中put是如何实现的？

    1.计算关于key的hashcode值（与Key.hashCode的高16位做异或运算）
    2.如果散列表为空时，调用resize()初始化散列表
    3.如果没有发生碰撞，直接添加元素到散列表中去
    4.如果发生了碰撞(hashCode值相同)，进行三种判断
        4.1:若key地址相同或者equals后内容相同，则替换旧值
        4.2:如果是红黑树结构，就调用树的插入方法
        4.3：链表结构，循环遍历直到链表中某个节点为空，尾插法进行插入，插入之后判断链表个数是否到达变成红黑树的阙值8；
            也可以遍历到有节点与插入元素的哈希值和内容相同，进行覆盖。
    5.如果桶满了大于阀值，则resize进行扩容
- https://www.cnblogs.com/zengcongcong/p/11295349.html

  
## Q：分布式有哪些理论？

    CAP、BASE。
    分布式CAP理论，任何一个分布式系统都无法同时满足Consistency(一致性)、Availability(可用性)、Partition tolerance(分区容错性) 这三个基本需求。最多只能满足其中两项。
    而Partition tolerance(分区容错性) 是必须的，因此一般是CP，或者AP。    
    
## Q：你怎么理解分布式一致性？

    数据一致性通常指关联数据之间的逻辑关系是否正确和完整。
    在分布式系统中，数据一致性往往指的是由于数据的复制，不同数据节点中的数据内容是否完整并且相同。
    一致性还分为强一致性，弱一致性，还有最终一致性。
    强一致性就是马上就保持一致。
    最终一致性是指经过一段时间后，可以保持一致    
    
    
## Q：java面试题：分布式    

    https://www.cnblogs.com/expiator/p/10201004.html   
    
    
## Q：JAVA中集合类型都有哪些？各有什么特点？

    Collection两大体系：链表List、集合Set

    List特点：元素有序；元素可以重复；元素都有索引（角标）
    List里存放的对象是有序的，同时也是可以重复的，List关注的是索引，拥有一系列和索引相关的方法，查询速度快。
    因为往list集合里插入或删除数据时，会伴随着后面数据的移动，所有插入删除数据速度慢。

    Set特点：元素无序；元素不可以重复；
    Set里存放的对象是无序，不能重复的，集合中的对象不按特定的方式排序，只是简单地把对象加入集合中。

    同时集合中还有另外一种类型：Map(映射)。

    Map特点：键值对；键不可以重复；值可以重复；
    Map集合中存储的是键值对，键不能重复，值可以重复。根据键得到值，对map集合遍历时先得到键的set集合，对set集合进行遍历，得到相应的值。     
    
    
## Q：集合结构

    Map
        HashMap
            Linkedhashmap
        Treemap
        
    Collection
        Set
            EnumSet
            SortedSet
                TreeSet
            HashSet
                LlinkedHashSet
        List
            LinkedList 线程不安全
                LinkedList实现了List接口，允许null元素。此外LinkedList提供额外的get，remove，insert方法在LinkedList的首部或尾部。
                这些操作使LinkedList可被用作堆栈（stack），队列（queue）或双向队列（deque）。
                注意LinkedList没有同步方法。如果多个线程同时访问一个List，则必须自己实现访问同步。一种解决方法是在创建List时构造一个同步的List：
                List list = Collections.synchronizedList(new LinkedList(…))
            ArrayList 线程不安全
                ArrayList实现了可变大小的数组。它允许所有元素，包括null。ArrayList没有同步。
                size，isEmpty，get，set方法运行时间为常数。但是add方法开销为分摊的常数，添加n个元素需要O(n)的时间。其他的方法运行时间为线性。
                每个ArrayList实例都有一个容量（Capacity），即用于存储元素的数组的大小。这个容量可随着不断添加新元素而自动增加，但是增长算法并没有定义。
                当需要插入大量元素时，在插入前可以调用ensureCapacity方法来增加ArrayList的容量以提高插入效率。
                和LinkedList一样，ArrayList也是非同步的（unsynchronized）
            Vector 线程安全
                Vector非常类似ArrayList，但是Vector是同步的。由Vector创建的Iterator，虽然和ArrayList创建的Iterator是同一接口，
                但是，因为Vector是同步的，当一个Iterator被创建而且正在被使用，
                另一个线程改变了Vector的状态（例如，添加或删除了一些元素），这时调用Iterator的方法时将抛出ConcurrentModificationException，因此必须捕获该异常。

                Stack
                    Stack继承自Vector，实现一个后进先出的堆栈
        Queue
            Deque
                LinkedList
            PriorityQueue
            


- https://www.jb51.net/article/92231.htm


在JAVA中，闭包是通过“接口+内部类”实现，JAVA的内部类也可以有匿名内部类。
闭包的价值在于可以作为函数对象或者匿名函数，持有上下文数据，作为第一级对象进行传递和保存。
闭包广泛用于回调函数、函数式编程中


闭包简单实例：

    package Test;
    public class Test {
        private int data=0;
        private class Inner{
            void print()
              {
                System.out.println(Test.this.data);
            }
        }
        Inner getInnerInstance()
         {
            return new Inner();
        }
        /**
     * @param args
     */
        public static void main(String[] args) {
            // TODO Auto-generated method stub
            Test t1=new Test();
            t1.data=1;
            Test t2=new Test();
            t2.data=2;
            Inner inner1=t1.getInnerInstance();
            Inner inner2=t2.getInnerInstance();
            inner1.print();
            //1
            inner2.print();
            //2
        }
    }  


## Q：内部类。

    在JAVA中，内部类可以访问到外围类的变量、方法或者其它内部类等所有成员，即使它被定义成private了，但是外部类不能访问内部类中的变量。
    这样通过内部类就可以提供一种代码隐藏和代码组织的机制，并且这些被组织的代码段还可以自由地访 问到包含该内部类的外围上下文环境。    

## Q：局部内部类。

　　局部内部类是指在方法的作用域内定义的的内部类。

## Q：匿名内部类。
    
    顾名思义，匿名内部类就是匿名、没有名字的内部类，通过匿名内部类可以更加简洁的创建一个内部类。

## Q：final关键字。

    闭包所绑定的本地变量必须使用final修饰符，以表示为一个恒定不变的数据，创建后不能被更改。

## Q：闭包的问题。

    让某些对象的生命周期加长。
    让自由变量的生命周期变长，延长至回调函数执行完毕。

## Q：闭包共享。

    final关键字   



## Q：如果hashCode 不冲突，那查找效率很高，但是如果hashCode一旦冲突，叫调用equals一个字节一个自己的去比较

    所以你把key设计的尽量短，一旦冲突也会少用点时间
    
    建议采用String,Integer 这样的类作为键，原因如下：
        特别是String，他是不可变的，也是final的，而且已经重写了equals 和hashCode 方法，这个和HashMap 要求的计算hashCode的不可变性要求不谋而合，
        核心思想就是保证键值的唯一性，不变性，其次是不可变性还有诸如线程安全的问题，以上这么定义键，可以最大限度的减少碰撞的出现

## Q：String 转 list

		//字符串转list<String>
        String str = "asdfghjkl";
        List<String> lis = Arrays.asList(str.split(""));
		
## Q：Integer转String
 
	//方法一:Integer类的静态方法toString()
	Integer a = 2;
	String str = Integer.toString(a)
	 
	//方法二:Integer类的成员方法toString()
	Integer a = 2;
	String str = a.toString();
	 
	//方法三:String类的静态方法valueOf()
	Integer a = 2;
	String str = String.valueOf(a);

## Q：String转Integer

	Integer.valueOf(str);
	
## Q：判断一个字符是不是数字
	
	工具包 判断一个字符串是不是全是数字组成
	org.apache.commons.lang.StringUtils.isNumeric()
	 
	
	// 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        String bigStr;
		Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
		isNum.matches()
		
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
		return pattern.matcher(str).matches();
			
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches(); 
		
	通过BigDecimal 转化
	String str	
	new BigDecimal(str).toString(); 是否抛出异常 抛出就不全是数字	
	
	只能判断正整数
	for (int i = str.length();--i>=0;){  
        if (!Character.isDigit(str.charAt(i))){
            return false;
        }
    }
	
	用ascii码
	for(int i=str.length();--i>=0;){
        int chr=str.charAt(i);
        if(chr<48 || chr>57)
            return false;
    }
 
 
## Q：indexOf 和 lastIndexOf的区别

    indexOf 和  lastIndexOf 是什么？
    　　indexOf 和 lastIndexOf 都是索引文件
    　　indexOf 是查某个指定的字符串在字符串首次出现的位置（索引值）（从左往右）
           lastIndexOf 是查某个指定的字符串在字符串最后一次出现的位置（索引值）（从右往左）
           eg：    
            var a="asdfghjkl"
            a.indexOf("d") // 2
            a.lastIndexOf("d") // 2

    注意：　　
            接下来在看一个例子：
            var a="asdddfghjkl"
            a.indexOf("d") // 2
            a.lastIndexOf("d") // 4
            

    　　　这个时候两个返回的索引值就不同了
     

    总结：
    　　　当数组（字符串）中所要查询的数（字符串/字符）在字符串（数组）中只出现一次的时候 二者返回的索引值相同
    　　　当数组（字符串）中所要查询的数（字符串/字符）在字符串（数组）中出现两次及以上的时候  
    　　　　　　indexOf  返回的是 valuesearch 第一次在数组（字符串）出现的位置（从左往右）
    　　　　　　lastIndexOf 返回的是 valuesearch 最后一次在数组（字符串）出现的位置（从右往左）
    补充说明

    定义：lastIndexOf() 方法可返回一个指定的元素在数组中最后出现的位置，从该字符串的后面向前查找。

    * lastIndexOf()方法虽然是从后往前搜索，但返回的位置是从前开始数的
    
## Q：什么是 CopyOnWriteArraySet 怎么使用

    - https://www.cnblogs.com/xiaolovewei/p/9142046.html
 
    它是线程安全的无序的集合，可以将它理解成线程安全的HashSet
    CopyOnWriteArraySet和HashSet虽然都继承于共同的父类AbstractSet；
    但是，HashSet是通过“散列表(HashMap)”实现的，
    而CopyOnWriteArraySet则是通过“动态数组(CopyOnWriteArrayList)”实现的，并不是散列表
 
    CopyOnWriteArraySet具有以下特性：
 
     1. 它最适合于具有以下特征的应用程序：Set 大小通常保持很小，只读操作远多于可变操作，需要在遍历期间防止线程间的冲突。
     2. 它是线程安全的。
     3. 因为通常需要复制整个基础数组，所以可变操作（add()、set() 和 remove() 等等）的开销很大。
     4. 迭代器支持hasNext(), next()等不可变操作，但不支持可变 remove()等 操作。
     5. 使用迭代器进行遍历的速度很快，并且不会与其他线程发生冲突。在构造迭代器时，迭代器依赖于不变的数组快照。




