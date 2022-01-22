#javascript 日常积累 积累

<a href="#" onclick="refreshJavascriptContent('question')">Javascript（question）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshJavascriptContent('newversion')">ES6（新特性）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshJavascriptContent('ajax')">Ajax 学习</a>&emsp;&emsp;&emsp;

---

1、浏览器支持下面的写法

            JSON.stringify(jsObj);// obj ---->json
            JSON.parse(jsObj);  // json--->obj

2、JQuery 的原生

	        $.parseJson(jsonStr);// JQuery.parseJson(jsonStr);  JQuery原生支持

3、URL编码

            %27 ：'
            Javascript函数：encodeURI()
            encodeURI()是Javascript中真正用来对URL编码的函数。
            它着眼于对整个URL进行编码，因此除了常见的符号以外，对其他一些在网址中有特殊含义的符号“; / ? : @ & = + $ , #”，也不进行编码。编码后，它输出符号的utf-8形式，并且在每个字节前加上%。
            它对应的解码函数是decodeURI()。
            需要注意的是，它不对单引号'编码。
        
            Javascript函数：encodeURIComponent()
            最后一个Javascript编码函数是encodeURIComponent()。与encodeURI()的区别是，它用于对URL的组成部分进行个别编码，而不用于对整个URL进行编码。
            因此，“; / ? : @ & = + $ , #”，这些在encodeURI()中不被编码的符号，在encodeURIComponent()中统统会被编码。至于具体的编码方法，两者是一样。
            它对应的解码函数是decodeURIComponent()。		

4、js判断对象是否为空对象的几种方法

                1.将json对象转化为json字符串，再判断该字符串是否为"{}"
                    var data = {};
                    var b = (JSON.stringify(data) == "{}");
                    alert(b);//true
                        1.1
                    if (JSON.stringify(d) == "{}") {
                        console.log(true);//为空
                    }else{
                        console.log(false);//不为空
                    }
                
                2.for in 循环判断
                    var obj = {};
                    var b = function() {
                        for(var key in obj) {
                            return false;
                        }
                        return true;
                    }
                    alert(b());//true
            
                3.jquery的isEmptyObject方法
                    此方法是jquery将2方法(for in)进行封装，使用时需要依赖jquery
                    var data = {};
                    var b = $.isEmptyObject(data);
                    alert(b);//true
            
                4.Object.getOwnPropertyNames()方法
                    此方法是使用Object对象的getOwnPropertyNames方法，获取到对象中的属性名，存到一个数组中，返回数组对象，我们可以通过判断数组的length来判断此对象是否为空
                    注意：此方法不兼容ie8，其余浏览器没有测试
                    var data = {};
                    var arr = Object.getOwnPropertyNames(data);
                    alert(arr.length == 0);//true
            
                5.使用ES6的Object.keys()方法
                    与4方法类似，是ES6的新方法, 返回值也是对象中属性名组成的数组
                    var data = {};
                    var arr = Object.keys(data);
                    alert(arr.length == 0);//true

5、	在js中判断一个对象是不是空的对象

                1、JSON.stringify  兼容ie8
                2、Object.getOwnPropertyNames 或者 Object.keys 兼容ie9
                3、循环判断 isEmptyObj 全兼容
                4、利用其他库判断如：jq类似
                var obj = {name:1};
                //兼容ie8
                if(JSON.stringify(obj) == "{}"){ 
                    console.log('JSON.stringify方法：is empty');
                }else{
                    console.log('JSON.stringify方法：not empty');
                }
                // 兼容ie9
                if(Object.getOwnPropertyNames(obj).length == 0){
                    console.log("Object.getOwnPropertyNames方法:is empty")
                }else{
                    console.log("Object.getOwnPropertyNames方法:not empty")
                }
                 
                // 或者 兼容ie9
                if(Object.keys(obj).length == 0){
                    console.log("Object.keys方法:is empty")
                }else{
                    console.log("Object.keys方法:not empty")
                }
                 
                function isEmptyObj(obj){
                    for(key in obj){
                        if(key){
                            return "for循环方法：not empty"
                        }
                    }
                    return "for循环方法：is empty"
                }
                console.log(isEmptyObj(obj));
	
6、表单相关选择器 （jquery 的）

                $(":input")     找到所有的input
                $(":button")     找到所有的按钮
                $(":checkbox")
                $(":password")
                $(":radio")
                $(":text")
                $(":image")
                $(":reset")
                $(":select")
=========================JQuery==================================JQuery=============================

                界面加载完之前执行的函数，与嵌入式的就是一样的加载方式
                    (function($){
                        do what you want to do...
                    })(jquery);
                界面加载后执行的函数 方式1
                    $(document).ready(function(){
                        do what you want to do...
                    });
                界面加载后执行的函数 方式2
                    $(function(){
                        do what you want to do...
                    });
                界面加载后执行的函数 方式3
                    jQuery(function($){
                        do what you want to do...
                    });
                    
                检测浏览器的类型和版本
                        $.browser.类型
                        $.browser.version
                        
                注册和禁用jQuery全局事件
                    // jQuery注册ajax全局事件 ajaxStart、ajaxStop
                    $(document).ajaxStart(function(){
                        do what you want to do...
                    }).ajaxStop(function(){
                        do what you want to do...
                    });
                
                设置IE特有功能
                    if($.browser.msie){
                        do ...
                    }
                    
                禁用右键单击
                    $(document).bind('contextmenu',function(){
                        do...
                    });
                
                淡入淡出 fadeIn fadeOut 
                    $(selector).fadeIn
                    $(selector).fadeOut
                淡入淡出切换 fadeToggle
                
                获取鼠标在屏幕中的坐标
                $(document).mousemove(function(e){
                    e.screenX;
                    e.screenY; // 获取鼠标的Y坐标
                });  
                获取鼠标在客户区中的坐标
                $(document).mousemove(function(e){
                    e.clientX;
                    e.clientY; // 获取鼠标的Y坐标
                });
                获取鼠标在窗口界面中的坐标
                $(document).mousemove(function(e){
                    e.pageX;
                    e.pageY; // 获取鼠标的Y坐标
                }); 

===========================================================================================================

jquery 和 javascript的区别(常用方法比较)

query 就对javascript的一个扩展，封装，就是让javascript更好用，更简单。

人家怎么说的来着，jquery就是要用更少的代码，漂亮的完成更多的功能。

JavaScript 与JQuery 常用方法比较


    1、加载DOM区别

        JavaScript： 界面加载完成之后的处理函数 window.onload

            function first(){
                alert('first');
            }
            function second(){
                alert('second');
            }
            window.onload = first;
            window.onload = second;
            //只会执行第二个window.onload；不过可以通过以下方法来进行改进：
            window.onload = function(){
                first();
                second();
            }

        Jquery：界面加载完成之后的处理函数 $(document).ready()

            $(document).ready(){
                function first(){
                    alert('first');
                }
                function second(){
                    alert('second');
                }
                $(document).ready(function(){
                    first();
                }
                $(document).ready(function(){
                    second();
                }
                //两条均会执行
            }

    2、获取ID

        JavaScript：
            document.getElementById('idName')

        JQuery：
            $('#idName')

    3、获取Class

        JavaScript：
            JavaScript没有默认的获取class的方法

        JQuery：
            $('.className')

    4、获取TagName

        JavaScript：
            document.getElementsByTagName('tagName')

        JQuery：
            $('tagName')

    5、创建对象并加入文档中

        JavaScript： 
            var para = document.createElement('p');
            //创建一个p元素
            document.body.appendElement(para);
            //将p元素追加为body的lastchild子节点，如果想将新创建的p元素插入到已存在的某个元素之前，可以使用insertBefore()方法

        JQuery：
            JQuery提供了4种将新元素插入到已有元素（内部）之前或者之后的方法：append()、appendTo()、prepend()、prependTo()。
            格式：$( html );
            eg，html代码：
            <p>World!</p>
            $('p').append('<b>Hello!</b>');
            //输出：<p>World!<b>Hello!</b></p>
            $('<b>Hello!</b>').appendTo('p'); 
            //输出：同上
            $('p').prepend('<b>Hello!</b>');
            //输出：<p><b>Hello!</b>World! </p>
            $('<b>Hello!</b>').prependTo('p');
            //输出：同上

    6、插入新元素

        JavaScript：
            insertBefore() 语法格式：
            parentElement.insertBefore(newElement,targetElement)
            eg, 将一个img元素插入一个段落之前。

            html代码：
            <img src="image.jpg" id="imgs" />
            <p>这是一段文字</p>

            JavaScript代码：
            var imgs = document.getElementById('imgs');
            var para = document.getElementsByTag('p');
            para.parenetNode.insertBefore(imgs,para);

        JQuery：
            JQuery提供了4种将新元素插入到已有元素（外部）之前或者之后的方法：after()、insertAfter()、before()、insertBefore()。
            格式：$( html );
            eg，html代码：
            <p>World!</p>

            JQuery代码
            $('p').after('<b>Hello!</b>');
            //输出：<p>World! </p><b>Hello!</b>
            $('<b>Hello!</b>'). insertAfter ('p');
            //输出：同上
            $('p').before('<b>Hello!</b>');
            //输出：<b>Hello!</b><p>World! </p>
            $('<b>Hello!</b>').insertBefore('p');
            //输出：同上

    7、复制节点

        JavaScript：
            reference = node.cloneNode(deep)
            这个方法只有一个布尔型的参数，它的可取值只能是true或者false。该参数决定是否把被复制节点的子节点也一同复制到新建节点里去。

        JQuery：
            clone() //复制节点后，被复制的新元素并不具有任何行为
            clone(true) //复制节点内容及其绑定的事件
            备注：该方法通常与appendTo()、prependTo()等方法结合使用。

    8、删除节点

        JavaScript：
            reference = element.removeChild(node)
            removeChild()方法将一个给定元素里删除一个子节点

        JQuery：
            remove();
                remove()方法作用就是从DOM中删除所有匹配的元素，remove()方法还可以与其他的过滤选择器结合使用，非常方便。
                eg，将ul li下的title不是"Hello"的li移除：
                $('ul li').remove(li[title!='Hello']);
            empty();
                empty()方法作用是清空节点。

    9、包裹节点

        JavaScript：
            JavaScript暂无

        JQuery：
            wrap() //将匹配元素用其他元素的结构化标记单独包裹起来
            wrapAll() //将所有匹配的元素用一个元素包裹起来
            wrapInner() //将匹配元素的子内容用其他结构化的标记包裹起来

    10、属性操作：设置属性节点、查找属性节点

        JavaScript：
            document.getElementsByTagName('tagName')

        JQuery：
            JQuery中设置和查找属性节点都是：attr() 。
            $('p').attr('title'); //获取p元素的title属性；
            $('p').attr('title','My title'); //设置p元素的title属性
            $('p').attr('title':'My title','class':'myClass'); //当需要添加多个属性时，可以用"名：值"对的形式，中间用逗号隔开。

    11、替换节点

        JavaScript：
            reference = element.replaceChild(newChild,oldChild)
            该方法是将一个给定父元素里的一个子节点替换为另外一个节点。

        JQuery：
            replaceWith()、replaceAll()
            eg:
            <p>hello</p>
            想替换为：
            <h2>Hi</h2>

            JQuery代码：
            $('p') .replaceWith('<h2>Hi</h2>');
            或者可以写成：
            $('<h2>Hi</h2>').replaceAll('p');

    12、CSS-DOM操作

        JavaScript：
            格式：element.style.property
            CSS-DOM能够读取和设置style对象的属性，其不足之处是无法通过它来提取外部CSS设置的样式信息，而JQuery的.css()方法是可以的。
            注意点：CSS中的如"font-size"这样有"-"的，要使用首字母小写的驼峰式表示，如fontSize。

        JQuery：
            格式：$(selector).css()
            css()方法获取元素的样式属性
            此外，JQuery还提供了height()和width()分别用来获取元素的高度和宽度（均不带单位），而css(height)、css(width)返回高宽，且带单位。

    13、显示隐藏元素
        JavaScript
            abc.style.display = "none";
            abc.style.display = "block";

        jQuery
            abc.hide();
            abc.show();
        
            $(id).toggle();  显示和隐藏自动切换
    14 获得焦点

        JavaScript 和 jQuery 是一样的，都是abc.focus();

    15 为表单赋值
        JavaScript
            abc.value = "test";
        jQuery
            abc.val("test");

    16 获得表单的值
        JavaScript
            alert(abc.value);
        jQuery
            alert(abc.val());

    17 设置元素不可用
        JavaScript
            abc.disabled = true;
        jQuery
            abc.attr("disabled", true);

    18 修改元素样式
        JavaScript
            abc.style.fontSize=size;
        jQuery
            abc.css('font-size', 20);

        JavaScript
            abc.className="test";
        JQuery
            abc.removeClass();
            abc.addClass("test");

    19 Ajax
        JavaScript
            自己创建对象，自己处理浏览器兼容等乱七八糟的问题，略去不表
        jQuery
            $.get("abc.php?a=1&b=2", recall);
            postvalue = "a=b&c=d&abc=123";
            $.post("abc.php", postvalue, recall);

            function recall(result) {
                alert(result);
                //如果返回的是json，则如下处理
                //result = eval('(' + result + ')');
                //alert(result);
            }

    20 判断复选框是否选中
        jQuery
            if(abc.attr("checked") == "checked")
            注意：网上说的.attr("checked") == true实际上不能用，上面这个测试过能用	