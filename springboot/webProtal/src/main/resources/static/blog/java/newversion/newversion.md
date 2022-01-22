<p>
    <a href="#" onclick="refreshContent('java')">返回目录</a>
</p>


#java newversion 新特性

##java 8
Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法，示例如下：

    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

Java 8新增的特性主要包含以下几个新的语法：
https://blog.csdn.net/qq_36795474/article/details/85090055

    Lambda表达式。
    函数式接口
    方法引用与构造器引用
    Stream API
    接口中默认方法与静态方法
    新时间日期API
    其他新的特性


##Java 11

###变量类型推断

    新版Java引入了一个全新的类型关键字var，用var来定义的变量不用写具体类型，编译器能根据=右边的实际赋值来自动推断出变量的类型：
    使用 var 时 必须在使用的时候将完成赋值
    另外 类的成员变量类型、方法入参类型、返回值类型等是不能使用var类型的变量
    
    
    普通局部变量

        var name = "codesheep"; // 自动推断name为String类型
        System.out.println(name);

        var类型变量一旦赋值后，重新赋不同类型的值是不行的，比如：

        var name = "codesheep";
        name = 666;  // 此时编译会提示不兼容的类型

    for循环中使用
        var upList1 = List.of( "刘能", "赵四", "谢广坤" );
        var upList2 = List.of( "永强", "玉田", "刘英" );
        var upList3 = List.of( "谢飞机", "兰妮", "兰娜" );
        var upListAll = List.of( upList1, upList2, upList3 );
        for( var i : upListAll ) { // 用var接受局部变量的确非常简洁！
            for( var j : i  ) {
                System.out.println(j);
            }
        }
        

###官方HTTP Client加持
    现在JDK官方就自带HTTP Client了，位于java.net.http包下，支持发送同步、异步的HTTP请求，这样一来，
    以前咱们常用的HTTP请求客户端诸如：OKHttp、HttpClient这种现在都可以退下了
    
    demo：
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        //发送同步请求：
        var request = HttpRequest.newBuilder().uri(URI.create("http://www.baidu.com/")).GET().build();
        // 同步请求方式，拿到结果前会阻塞当前线程
        var httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body()); // 打印获取到的网页内容


        //发送异步请求：
        CompletableFuture<String> future = HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
        System.out.println("我先继续干点别的事情...");
        System.out.println(future.get()); // 打印获取到的网页内容

        
        //当然你也可以自定义请求头，比如携带JWT Token权限信息去请求等：
        var requestWithAuth = HttpRequest.newBuilder().uri(URI.create("http://www.xxxxxx.com/sth"))
                .header("Authorization", "Bearer ").GET().build();
        var response = HttpClient.newHttpClient().send(requestWithAuth, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body()); // 打印获取到的接口返回内容
    }
    

###String处理增强
    
    //新版字符串String类型增加了诸如：isBlank()、strip()、repeat()等方便的字符串处理方法

    String myName = " codesheep ";
    System.out.println( "  ".isBlank() ); // 打印：true
    System.out.println( "  ".isEmpty() ); // 打印：false

    System.out.println( myName.strip() );         // 打印codesheep，前后空格均移除  类似 trim()
    System.out.println( myName.stripLeading() );  // 打印codesheep ，仅头部空格移除
    System.out.println( myName.stripTrailing() ); // 打印 codesheep，仅尾部空格移除
    System.out.println( myName.repeat(2) );       // 打印 codesheep  codesheep


###集合增强

    主要是增加了诸如of()和copyOf()等方法用于更加方便的创建和复制集合类型

    var upList = List.of( "刘能", "赵四", "谢广坤" );
    var upListCopy = List.copyOf( upList );
    System.out.println(upList);     // 打印 [刘能, 赵四, 谢广坤]
    System.out.println(upListCopy); // 打印 [刘能, 赵四, 谢广坤]

    var upSet = Set.of("刘能","赵四");
    var upSetCopy = Set.copyOf( upSet );
    System.out.println(upSet);      // 打印 [赵四, 刘能]
    System.out.println(upSetCopy);  // 打印 [赵四, 刘能]

    var upMap = Map.of("刘能","58岁","赵四","59岁");
    var upMapCopy = Map.copyOf( upMap );
    System.out.println(upMap);      // 打印 {刘能=58岁, 赵四=59岁}
    System.out.println(upMapCopy);  // 打印 {刘能=58岁, 赵四=59岁}


###函数式编程增强
    我印象最深的是对Stream流增加了诸如takeWhile()和dropWhile()的截止结算方法：

    var upList = List.of( "刘能", "赵四", "谢广坤" );

    // 从集合中依次删除满足条件的元素，直到不满足条件为止
    var upListSub1 = upList.stream()
            .dropWhile( item -> item.equals("刘能") )
            .collect( Collectors.toList() );
    System.out.println(upListSub1);  // 打印 [赵四, 谢广坤]

    // 从集合中依次获取满足条件的元素，知道不满足条件为止
    var upListSub2 = upList.stream()
            .takeWhile( item -> item.equals("刘能") )
            .collect( Collectors.toList() );
    System.out.println( upListSub2 ); // 打印 [刘能]
    
    
###支持源文件直接运行（666！）
    比如我写一个最简单的Hello World程序：

    public class Hello {
        
        public static void main( String[] args ) {
            System.out.println("hello world");
        }
         
    }
    并保存为hello.java文件，这时候可以直接用java指令去运行这个Java源文件，直接省去以前javac编译源文件的过程：    
    
    jdk11中，通过 java xxx.java 命令，就可直接运行源码文件程序，而且不会产生.class 文件。
    一个java文件中包含多个类时，java xxx.java 执行排在最上面的一个类的main方法。
    java xxx.java 启动单个Java源代码文件的程序时，相关个类必须定义在同一个java文件中
    
    
    
    


