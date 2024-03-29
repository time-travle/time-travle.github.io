<p>
    <a href="#" onclick="refreshContent('java')">返回目录</a>
</p>

---

# java Lambda 新特性

## 与匿名类 概念相比较，

Lambda 其实就是匿名方法，这是一种把方法作为参数进行传递的编程思想 Java会在背后，悄悄的，把这些都还原成匿名类方式。

## Lambda的弊端顶折

Lambda表达式虽然带来了代码的简洁，但是也有其局限性。

    1. 可读性差，与啰嗦的但是清晰的匿名类代码结构比较起来，Lambda表达式一旦变得比较长，就难以理解
    2. 不便于调试，很难在Lambda表达式中增加调试信息，比如日志
    3. 版本支持，Lambda表达式在JDK8版本中才开始支持，如果系统使用的是以前的版本，考虑系统的稳定性等原因，而不愿意升级，那么就无法使用。
    4. Lambda比较适合用在简短的业务代码中，并不适合用在复杂的系统中，会加大维护成本

## 传统方式与聚合操作方式遍历数据顶折

遍历数据的传统方式就是使用for循环，然后条件判断，最后打印出满足条件的数据

    for (Hero h : heros) {
       if (h.hp > 100 && h.damage < 50)
          System.out.println(h.name);
    }
     
    使用聚合操作方式，画风就发生了变化：
     
    heros.stream()
        .filter(h -> h.hp > 100 && h.damage < 50)
        .forEach(h -> System.out.println(h.name));    

## 管道源

把Collection切换成管道源很简单，调用stream()就行了。

    heros.stream()

    但是数组却没有stream()方法，需要使用
     
    Arrays.stream(hs) 或者 Stream.of(hs)        

## Lambda表达式有什么作用?

最直观的作用就是使得代码变得异常简洁

所有的Lambda的类型都是一个接口，而Lambda表达式本身，也就是”那段代码“，需要是这个接口的实现

Lambda表达式本身就是一个接口的实现

由于Lambda可以直接赋值给一个变量，我们就可以直接把Lambda作为参数传给函数, 而传统的Java必须有明确的接口实现的定义，初始化才行

    只有一个接口函数需要被实现的接口类型，我们叫它”函数式接口“。
    为了避免后来的人在这个接口中增加接口函数导致其有多个接口函数需要被实现，变成"非函数接口”，
    我们可以在这个上面加上一个声明@FunctionalInterface, 这样别人就无法在里面添加新的接口函数了：

    @FunctionalInterface
    interface MyLambdaInterface {
        void doSomeThing();
    }

Lambda结合FunctionalInterface Lib, forEach, stream()，method reference等新特性可以使代码变的更加简洁

- <a href="https://www.zhihu.com/question/20125256" target="_blank">Lambda 表达式有何用处？如何使用？</a>

## lambda 中使用循环

数组不能直接在forEach中使用lambda表达式

    想要使用必须转换，如下
        PartnerType[] values = PartnerType.values();
    （1）转成list
        Arrays.asList(values).forEach(System.out::println);//转成list
    （2）转成steam
        Arrays.stream(values).forEach(System.out::println);//转成流

