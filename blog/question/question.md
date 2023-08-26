## 问题

### 网页禁止复制粘贴怎么解决教程,网页禁止复制粘贴怎么办

方法1：

        打开目标网页，选中网页的地址栏 在地址栏输入下行代码，全部输入。
            javascript：void($={});
        按下回车键(Enter)，破解完成。(回车后不会跳转网页), 注意的是，如果网页被刷新，限制会恢复，需要重新输入代码

### 如何禁止 网页的左键复制功能

方法1：

        在html.tpl.php 加入一行
        <script language="Javascript">
        document.οncοntextmenu=new Function("event.returnValue=false");
        document.onselectstart=new Function("event.returnValue=false");
        </script>

参考：<a href="https://blog.csdn.net/for023/article/details/77452718#" target="_blank">https://blog.csdn.net/for023/article/details/77452718 </a>

### 禁止页面另存为

#### jsp

        <body>标签加入以下代码：
        <noscript>
        <iframe src=”*.htm”></iframe>
        </noscript>

### 实现禁止复制粘贴

#### 脚本1

        <script type="text/javascript">
            // 禁止右键菜单
            document.oncontextmenu = function(){ return false; };
            // 禁止文字选择
            document.onselectstart = function(){ return false; };
            // 禁止复制
            document.oncopy = function(){ return false; };
            // 禁止剪切
            document.oncut = function(){ return false; };
            // 禁止粘贴
            document.onpaste = function(){ return false; };
        </script>
    弊端：执行了一次就会每次都会执行了

参考： <a href="https://www.cnblogs.com/momo798/p/6797670.html#" target="_blank">https://www.cnblogs.com/momo798/p/6797670.html </a>

#### 脚本2

        <script type="text/javascript">
            // 禁用右键菜单、复制、选择
            $(document).bind("contextmenu copy selectstart", function() {
             return false;
            });
            // 禁用Ctrl+C和Ctrl+V（所有浏览器均支持）
            $(document).keydown(function(e) {
             if(e.ctrlKey && (e.keyCode == 65 || e.keyCode == 67)) {
               return false;
             }
            });
        </script>
    弊端：执行了一次就会每次都会执行了

参考：<a href="https://www.jianshu.com/p/5550da3fad49#" target="_blank">https://www.jianshu.com/p/5550da3fad49 </a>

---

## js中window和document对象及如何操作iframe

### 一、 window对象

#### 1. 什么是window对象？

Window对象表示浏览器打开的窗口。如果文档包含iframe或者是frame标签，浏览器会为HTML文档创建一个window对象。所有浏览器都支持该对象。

补充：什么是父窗口和子窗口？

打开一个html页面就是一个窗口。如果该html中包含iframe或者是frame标签，则iframe或者frame就是子窗口，包含iframe或者是frame标签的窗口就是父窗口。个人理解，一个窗口就是一个window对象。

#### 2. Window对象集合

顾名思义，就是返回多个window对象。每个window对象，都是当前window的子窗口。

frames[]:返回窗口中所有命名的框架。该集合是window对象的数组，每个window对象在窗口中含有一个框架或者iframe。属性frames.length存放数组frames[]中含有的iframe的个数。注意：frames[]
数组中引用的框架可能还包含框架，它们自己也具有frames[]数组。

#### 3. Window对象的parent和top属性和iframe的使用

##### 1)    如何获取子iframe框架中的window对象呢（也就是获取当前window对象的子窗体iframe）？

获取指定的iframe的window对象有三种方式：

- a.通过window.frames[索引]：比如如果有两个当前窗体有两个iframe一个name=test1,一个name=test2,那么我想要获取name为test2的iframe我就可以这样使用：window.frames[1]
  ,获取的就是当前窗体中包含的名词为test2的子窗体

- b.通过window.frames[“iframe名称”]
  ：通过iframe的名词获取首先iframe得有name属性，这样才可以获取到，比如当前窗体包含了一个name=main的iframe，则访问这个iframe直接使用window.frames[“main”]就可以了。

- c.第三种方式其实就是通过document对象获取iframe，接着再获取iframe中的window对象，如：window.document.getElementById(“iframeid”).contentWindow；

总结：这三种方式其实都是获取子窗体iframe的window对象，可以直接操作iframe的window对象属性，可以直接操作该iframe中的函数。关键是获取到每个iframe中的window，这样才能获取到其的函数和document对象。

注意：也许会遇到奇葩的情况，比如通过window.frames[“iframe的名词”]. frames[“iframe的名词”]. frames[“iframe的名称”]
获取指定名词的iframe，但是获取不到，却也存在该iframe，此时该怎么办呢？通过名称获取不到，可以通过索引获取啊。

###### 2)    parent属性如何使用

刚才说了一下如何访问当前window对象子窗体iframe，那么，如何访问当前window窗体的父窗体呢？就是通过window对象的parent属性。

parent.window获取的就是父窗体的window对象。比如如果要调用父窗体的main函数，就可以使用parent.window.main();

##### 3)    top属性如何使用

如何获取一个iframe最顶层的window对象呢？可以使用top.window，获取的就是iframe最顶层的窗口。调用最顶层的方法一样调用，top.window.method

##### 4. 如何调用iframe中的函数，如果操作iframe中的标签元素？

通过3的分析可以获取iframe的window对象，那么操作iframe中的函数和标签就变得简单了，因为操作iframe中的函数其实就是调用iframe中window中的函数，直接用iframe的window对象调用函数就可以了；如：当前window中包含一个iframe，iframe框架中有一个main函数，那么如何通过父窗体调用子窗体的函数呢？有三种方式：

- window.frames[索引].main();

- window.frames[“iframe名称”].main();

- window.document.getElementById(“iframeId”).contentWindow.main();

现在iframe框架中的方法可以通过window对象直接调用，但是如何操作iframe中的标签元素呢？直接用iframe的window对象的document属性对象就可以操作了。获取到document对象，想怎么操作标签元素就怎么操作标签元素。

- window.frames[索引].document;

- window.frames[“iframe名称”].document;

- window.document.getElementById(“iframeId”).contentWindow.document;

- window.document.getElementById(“iframeId”).contentDocument;

通过这四种方式获取子窗体的document，document对象获取之后就可以操作iframe框架中的元素了。一般通过document的方法操作元素。如果想要调用父窗体的函数或者是操作父窗体的标签元素，直接使用：parent.window.document获取父窗体的document对象，使用parent.window.函数名，直接调用父窗体的函数。如果iframe有多层嵌套，那么一层一层去取，方法都相似。

### 二、 document对象

简单的说document对象就是window对象的属性对象。其实就是一个html文档结构。

那么document有啥用呢？document对象可以操作html文档中的任意一个节点，可以添加修改删除查找一个节点，可以获取某个标签的值，某个标签的属性节点的值。

常用方法：

- document.getElementById（“标签元素id属性”） 返回单个元素

- document.getElementsByName(“标签元素的name属性”)返回数组对象

- document.getElemetsByTagName(“标签名”)返回数组对象

=================================================================

    $("iframe").each(function(){
    var id = $(this).attr("id");/*获取当前页面所有iframe的id*/
    });

================================================================= jquery中获取iframe的id的方法：

    var frameId = window.frameElement && window.frameElement.id || '';
    alert(frameId);
    比如有个
    <iframe id="FrameX" src="../index.html" scrolling="no" frameborder="0" >
    用以上方法获取到后就显示FrameX。
