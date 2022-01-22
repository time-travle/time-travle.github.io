<p>
    <a href="#" onclick="refreshContent('javascript')">返回目录</a>
</p>

#Javascript  ajax 日常 

---
ajax

	使用浏览器提供的一个js对象(XMLHttpRequest)来发送请求，它能够用来发送http请求，它的特殊之处在于，它的
    请求不会导致页面的跳转（仍保持在当前页面）


XMLHttpRequest对象

1，创建XMLHttpRequest对象

                var xhr = new XMLHttpRequest();
                
                注意：不同的浏览器，创建对象方式上有差异。
                
                对于符合W3C标准的浏览器是用上述方式创建对象，对于IE的低版本，使用
                    
                    var xhr = ActiveXObject("Microsoft.XMLHTTP");
                    
2,发送请求
	
1）xhr.open("请求方式"，uri,true|false)
            
                参数1：表示请求方式，可以是：get|post|delete|put|head
                
                参数2：请求的目标地址，只能是本域下的地址，跨域需要浏览器支持。
                
                参数3：false同步   true异步
                


2）xhr.send(请求体内容)

                如果是get请求，没有请求体，则参数可以不传或者传递null。
                如果是post请求，则代表请求体内容。
                请求体的内容的格式由请求头中的content-type头设置，常见的格式。
                
                    application/x-www-form-urlencoded(普通的表单请求体就是这种格式：参数名=参数值，多个请求间用&分隔)
                    
                    multipart/form0-data(多部分格式，通常包含文件上传的表单提交时使用这种格式)
                
                     <embed src="1.swf" type="application/x-shockwave-flash"  width="400" higth="300" />
                     引入flash
                     
                     
                注意：xhr默认请求和响应所使用的编码都是UTF-8，为了处理简单一些，页面编码也使用utf-8
                

3）接收响应

    xhr.responseText


4）什么是同步，什么是异步

                在发送请求的瞬间，动画停止了，当5秒之后响应返回，动画恢复播放
                
                        xhr.open方法的第三个参数	 ，它的取值是false表示同步请求，含义是接下来的xhr.send方法发送请求会让页面其他
                部分的代码和操作均陷入等待（阻塞）状态，直到send方法调用结束（响应返回）后才得以恢复
                
                        把xhr.open方法的第三个参数由false改成true，这时代表异步请求。含义是接下来的send方法发送完请求，就会
                结束调用，不会让其他部分进行等待状态。
                
                因为send方法发送完请求立刻结束，执行下面的xhr.responseText去获取响应文本，但是响应是5秒之后才返回，所以
                这个时候无法获得内容。


5）异步请求下接收响应。

                xhr对象有代表状态的属性readyState
                取值                     含义
                0                   对象刚创建时的初始状态  
                1										当请求已发送
                2                   响应头已返回
                3										响应解析中
                4										响应完全返回
                    
                当这些状态发送改变从一个值到另外一个值时，会触发一个事件。onreadystatechange
                
                    function sendXhr(){
                    
                        //获得评论内容
                        var c = document.getElementById("c").value;
                        //创建XMLHttpRequest对象
                        var xhr = new XMLHttpRequest();
                        
                        xhr.onreadystatechange = function(){
                            if(xhr.readyState == 4){ //代表响应完全返回
                                alert(xhr.responseText);
                            }
                        }；
                        //发送请求
                        xhr.open("get","/0825/ajax/testServlet?comment="+c,true);
                        
                        xhr.send();
                        
                        
                    }
                
                事件代码应定义在open方法之前


6，发送post请求

    post请求与get请求的区别在于请求参数的位置处于请求体中而不是跟在请求行后，另外要设置content-type请求头

            function sendXhr(){
            
                //获得评论内容
                var c = document.getElementById("c").value;
                //创建XMLHttpRequest对象
                var xhr = new XMLHttpRequest();
                
                xhr.onreadystatechange = function(){
                    if(xhr.readyState == 4){ //代表响应完全返回
                        alert(xhr.responseText);
                    }
                };
                //发送请求
                xhr.open("post","/0825/ajax/testServlet",true);
                xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded")
                xhr.send("comment="+c);
            }
  
7，json响应格式

                    如果返回纯文本响应格式，一个回车，一个换行都可能导致前台js判断的不确定性，并且纯文本的格式的响应，只能
            当做字符串使用，如果要配合前台js做一些逻辑判断，算术运算，不是很方便
                
                    如果返回的是html代码直接显示，是可以的。但是html代码返回的是“数据+数据的展现方式”，相当于固定了数据
            的展示方式，不利于重用。
            
                    基于以上原因，需要返回支持各种数据类型的纯数据格式，并且还需要解析方便，引入了JSON格式：所谓json就是指
            特定格式的字符串，它能够表示多种数据类型，包括数组和对象。
            
            它的基本格式为：
            
              类型                         格式示例
            User user = new User()
            
                对象                         {"name":"zhangsan","age":18} 
                数组		                    [
                                                {"id":010,"city":"北京"},
                                                {"id":0531,"city":"济南"}			
                                                ]  		
                字符串                        "字符串"
                数字                           123或者123.45或1.2345e2
                布尔                           true或false
                空值                         null
            
            注意：json对象的属性名，严格来讲，需要两边加双引号
                        字符串类型，也该应该两边加双引号而不能用单引号
                        
            某些js库（如JQuery）在解析JSON时，如果没有按照这些规定来，会出现解析错误。
            
            用json作为响应格式，需要完成两个步骤
            
            1），java对象转换为json字符串
            2），json字符串转为为js对象
            
            
            json字符串其实充当了数据的传输载体，或称为java对象到js对象的桥梁


8，JSON格式转换

                首先应当做的是在java代码中将java对象转换为json字符串，并且作为响应返回。
                
                常用的转换工具包有：
                
                工具包                     注释
                
                json-lib                   老牌的json转换工具
                
                gson                       google出品的转换工具，支持泛型，使用相对简单，但用于转换时使用field进行反射
                                            而不是用peroperty（即set，get方法）反射，因为会带来一些处理上的不便
                
                jackson                     支持多种方法进行转换：databind（按POJO对象进行转换），stream（按流进行转换）
                                            tree（按树进行转换），效率比较高
                
                示例gson
                        //转换实体类
                        User user = new User("zhangsan",18,true);
                        
                        String json = new Gson().toJson(user);
                        
                        System.out.println(json);	 
                
                        //转换实体类list集合
                        List<User> users = new ArrayList<User>();
                        
                        users.add(new User("张三",18,true));
                        users.add(new User("李四",22,true));
                        
                        json = new Gson().toJson(users);
                        
                        System.out.println(json);
                        
                                //转换map集合
                        
                        Map<String,String> map = new LinkedHashMap<String,String>();
                        
                        map.put("a", "李强");
                        map.put("b", "张倩");
                        
                        json = new Gson().toJson(map);
                        
                        
                jackson 示例
                        //转换实体类
                        User user = new User("zhangsan",18,true);
                        
                        ObjectMapper om = new ObjectMapper();
                        
                        try {
                            om.writeValue(new OutputStreamWriter(System.out, "utf-8"), user);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                                //转换list集合
                        
                        ObjectMapper om = new ObjectMapper();
                        
                        
                        List<User> users = new ArrayList<User>();
                        
                        users.add(new User("zhangsan",18,true));
                        users.add(new User("lisi",18,true));
                        
                        try {
                            om.writeValue(new OutputStreamWriter(System.out, "utf-8"), users);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                                
                        //转换map集合
                        
                        ObjectMapper om = new ObjectMapper();
                        
                        
                        Map<String,String> map = new LinkedHashMap<String, String>(){
                            {
                                put("a","apple");
                                put("b","banana");
                            }
                        };
                    
                        try {
                            om.writeValue(new OutputStreamWriter(System.out, "utf-8"), map);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                        
                        
                其次，在json字符串传送到客户端时，应当将json字符串转换为js对象
                
                方法1：
                    var json = '{"name":"张三","age":18,"sex":true,"hobby":["抽烟","喝酒"]}';
                    var object = eval("("+json+")");
                    
                方法2：
                    var json = '{"name":"张三","age":18,"sex":true,"hobby":["抽烟","喝酒"]}';
                    var object = new Function("return" + json);
                    
	
9，使用xhr和json实现注册用户名检查

                  <style type="text/css">
                    .success{color:green}
                    .fail{color:red}
                  </style>
                  <script type="text/javascript">
                    function checkName(name){
                    
                        //获得评论内容
                    //	var c = document.getElementById("username").value;
                        //创建XMLHttpRequest对象
                        var xhr = new XMLHttpRequest();
                        
                        
                        xhr.onreadystatechange = function(){
                            if(xhr.readyState == 4){ //代表响应完全返回
                                var span = document.getElementById("usernameSpan");
                                
                                var json = xhr.responseText;
                                
                                var obj = eval("("+json+")");
                                span.innerHTML = obj.message;
                                span.className = obj.css;
                            }
                        };
                        //发送请求
                        xhr.open("post","/0825/ajax/regServlet",true);
                        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded")
                        xhr.send("username="+name);
                        
                    }
                  </script>
                  </head>
                  
                  <body>
                 
                  注册
                  <form action="" method="post">
                    用户名：<input type="text" name="username" id="username" onblur="checkName(this.value)"/>
                    <span id="usernameSpan"></span><br/>
                    密码：<input type="password" id="password" name="password" /><br/>
                    <input type="button" value="注册" />
                  </form>
                  </body>
                
                
                protected void service(HttpServletRequest request,HttpServletResponse response) 
                                        throws ServletException,IOException{
                        request.setCharacterEncoding("utf-8");
                        
                        String username = request.getParameter("username");
                        
                        System.out.println(username);
                        
                        String dbUserName="zhangsan";
                        
                        Map<String,String> map = new HashMap<String,String>();
                        if(dbUserName.equals(username)){
                            map.put("message", "用户名已存在");
                            map.put("css", "fail");
                        }else{
                            map.put("message", "用户名可用");
                            map.put("css", "success");
                        }
                        
                        
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json");
                        String json = new Gson().toJson(map);
                        
                        response.getWriter().print(json);
                    }
                    
                    
                响应可以设置内容类型为	application/json ，这样有助于js库对响应格式进行判断

10，什么是ajax

            AJAX即“Asynchronous Javascript And XML”
            A:	Asynchronous 指XMLHttpRequest对象能够用来发送不刷新页面的异步请求
            J： Javascript 指操作XMLHttpRequest对象以及处理响应所使用的语言
            A：and
            X：	xml  早期的ajax技术使用xml作为响应格式。xml响应生成和解析不便，逐渐为更轻便的json格式所替代。


/////////////////////////////////////////////////////////////////////

ajax 应用

1，封装post

	1）每次请求的地址不同
	2）每次请求的参数不同
	3）每次拿到响应之后，处理的方式不同
		
	抽取出通用的发送post请求方法
	
	参数1：代表请求地址
	参数2：请求的参数字符串
	参数3：是一个函数，会在响应返回时执行，这个函数称为回调（callback）函数 		
	
	function post(url,params,fn){
		
		var xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			
			if(xhr.readyState == 4){
				//将响应的json字符串转换为js对象
				var obj = eval("("+xhr.responseText+")");
				//调用回调函数，参数为转换好的js对象
				fn(obj);
			}
		};
		
		xhr.open("post", url, true);
		
		xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
		
		xhr.send(params);
	}

      <script type="text/javascript" src="/0826/js/ajax.js"></script>
      <script type="text/javascript">
        function checkName(name){
            /*调用封装的post方法*/
            post("/0826/ajax/regServlet","username="+name,function(obj){
                var span = document.getElementById("usernameSpan");
                span.innerHTML = obj.message;	
                span.className = obj.css;
            });
            
        }
      </script>
    同理可以封装get方法


2，jquery中的$.get和$.post
                
                jQuery.post(url, [data], [callback], [type]) 
                
                
                参数名               参数类型                             说明
                url                  String                             请求地址
                data                 简单对象或字符串                    请求参数
                callback             Function(data,textStatus,jqXHR)    成功时的回调函数
                                                                        参数1：数据，根据响应类型而定，可以是xml json，html，text等
                                                                        参数2：响应文本
                                                                        参数3：jquery封装后的xhr对象
                type				String								期望服务器返回的数据格式：xml，script，json
                                                                                                                                                                                html，text
                                                                                                                                                                                
                
                例子：
                发送请求，但忽略响应
                $.post("/0826/ajax/regServlet");	
                
                发送请求，带请求参数（方式1），仍然忽略响应
                $.post("/0826/ajax/regServlet","username="+name);																																							
                username=name
                                                                                                                                                                                
                发送请求，带请求参数（方式2），仍然忽略响应																																																 
                $.post("/0826/ajax/regServlet",{username:name,age:"18"});//username=name&age=18
                username=name&age=18	
                
                发送请求，带数组请求参数，仍然忽略响应	
                $.post("/0826/ajax/regServlet",{'names[]':["zhangsan","lisi"]});
                
                <form action="" method="post" id="form1">
                    用户名：<input type="text" name="username" id="username" onblur="checkName(this.value)"/>
                    <span id="usernameSpan"></span><br/>
                    密码：<input type="password" id="password" name="password" /><br/>
                    邮箱：<input type="text" name="email" id="email" />
                    <input type="checkbox" name="hobby" value="唱歌"/>
                    <input type="checkbox" name="hobby" value="跳舞"/>
                </form>
                $.post("/0826/ajax/regServlet",$("#form1").serialize());//$("#form1")=> document.getElementById("from1")
                
                // username=123&password=123&email="bac"
                
                console.log() 在js控制台打印输出
                
                发送请求，带请求参数，使用回调函数接收响应
                $.post("/0826/ajax/regServlet",{username:name},function(obj){
                    console.log(obj.message);
                });	
                
                
                发送请求，带请求参数，使用Deffered接口接收响应
                $.post("/0826/ajax/regServlet",{username:name}).done(function(json){
                    console.log(json.css);
                });
                
                //链式调用
                
                对应get请求
                
                jQuery.get(url, [data], [callback], [type]) 
                参数
                urlString待载入页面的URL地址
                
                data (可选)Map待发送 Key/value 参数。
                
                callback (可选)Function载入成功时回调函数。
                
                type (可选)String返回内容格式，xml, html, script, json, text, _default。
                
                无论是get请求还是post请求，他们返回的都是Deffered接口类型
                
                Deffered.done(doneCallBacks)
                
                参数名              参数类型                            说明
                doneCallBacks      function（data,textStatus,jqXHR）    ajax成功时的回调函数
                                                                        参数1：数据，根据响应类型而定，可以是
                                                                                xml，json，html，text等
                Deffered.fail(failCallBacks)
                参数名              参数类型                            说明
                failCallBacks      function（jqXHR,textStatus,errorThrown）    ajax失败时的回调函数
                                                                                参数1：jquery封装后的xhr对象
                                                                                参数2：错误文本的描述
                                                                                    timeout  超时
                                                                                    error http错误
                                                                                    abort 取消
                                                                                    parseerror 解析错误
                                                                                                                                    
                                                                                                                                    
                Deffered接口是jquery1.5版本之后加入的。		
                
                
                $.ajax({
                    url:"/0826/ajax/regServlet",
                    type:'POST',
                    data:{'username':name,'age':18},//username=name&age=18
                    success:function(data){
                        alert(data.message);
                    },
                });																											
                
                
                省市区联动  参考0826 city.jsp
                
                jquery核心
                
                在DOM加载完成时运行的代码，可以这样写：
                
                jQuery 代码:
                $(document).ready(function(){
                  // 在这里写你的代码...
                }); 
                1， 
                
                
                id选择器   格式 $("#id")    根据id属性查找元素
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("#btn").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                    <input type="button" value="按钮" id="btn"/>
                  </body>
                
                element选择器  格式 $("element")     根据元素（标签名称）查找元素
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("p").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <p>春眠不觉晓</p>
                        <p>处处闻啼鸟</p>
                  </body>
                  
                class选择器    格式$(".class")        根据class属性查找元素   
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $(".a").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <p>春眠不觉晓</p>
                        <p class="a">处处闻啼鸟</p>
                  </body>
                  
                  
                descendant 后代选择器   格式$("祖先 后代")   根据给定的祖先，找到所有匹配的后代
                <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("div p").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <p>春眠不觉晓</p>
                        <p class="a">处处闻啼鸟</p>
                        <div id="d1">
                            <p>夜来风雨声</p>
                            <center>
                                <p>花落知多少</p>
                                <p>春晓</p>
                            </center>  		
                        </div>
                  </body>
                  
                  
                 child（子元素）选择器  格式$("parent > child") 根据给定的父元素，找到所有匹配的子元素
                 
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("div > p").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <p>春眠不觉晓</p>
                        <p class="a">处处闻啼鸟</p>
                        <div id="d1">
                            <p>夜来风雨声</p>
                            <center>
                                <p>花落知多少</p>
                                <p>春晓</p>
                            </center>  		
                        </div>
                  </body>
                
                  
                  
            eq(index)过滤器  
            
            按照下标进行过滤，下标从0开始，jquery1.8后支持负数。-1表示最后一个元素，-2倒数第2个。。
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a:eq(0)").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>																														
                                                                                                                                        
                                                                                                                            
                first过滤器  等价于eq(0)
                
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a:first").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>																											
                                                                                                                            
                last过滤器   找到最后一个元素
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a:last").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>
                  
                  
                even过滤器  过滤偶数元素，注意所谓奇偶，是根据下标而言
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a:even").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>
                                                                                                                            
                                                                                                                            
                odd过滤器  	过滤奇数元素，注意所谓奇偶，是根据下标而言
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a:odd").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>
                                                                                                                        
                                                                                                                            
                包含属性选择器   格式   "[name]"  查找包含name属性的元素
                
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a[title]").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>																											
                                                                                                                            
                属性值相等选择器  格式："[name=value]" 
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a[title=tt2]").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>
                  
                 属性值起始位置匹配选择器   格式："[name ^= value]"   查找包含name属性的元素，且起始位置值等于value的
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a[title ^=t]").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>
                  
                 属性值结束位置匹配选择器   格式："[name $= value]" 
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a[title $= 2]").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>
                  
                 属性值模糊匹配选择器     格式:"[name *= value]"
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a[title *= q]").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>
                  
                  
                取非选择器   格式： not(selector)
                    <script type="text/javascript">
                    $(document).ready(function(){
                         // 在这里写你的代码...
                         $("a:not([title *=t ])").css("color","red");
                    });
                        
                    </script>
                  </head>
                  
                  <body>
                        <a href="aaa" title="tt1">链接1</a>
                        <a href="aaa">链接2</a>
                        <a href="aaa" title="tt2">链接3</a>
                        <a href="aaa" title="qt2">链接4</a>
                  </body>
                
                事件
                
                jquery对象.click([eventData],handler)
                
                参数名                   参数类型                    说明
                eventData				意类型                    可以通过eventObject.data来获取
                handler                 Function(Event eventObject)  事件处理函数 eventObject为事件对象
                
                  <body>
                        <input type="button" value="按钮"/>
                        <input type="button" value="按钮"/>
                <script type="text/javascript">
                    $(":button").click(function(){  //给所有按钮添加单击事件
                            alert("ok");
                    });
                </script>
                  </body>
                  
                  
                   <body>
                        <input type="button" value="按钮"/>
                        <input type="button" value="按钮"/>
                <script type="text/javascript">
                    $(":button").click(123,function(e){ //给所有按钮添加单击事件，弹出对话框显示123
                            alert(e.data);
                    });
                </script>
                  </body> 
                  
                  
                注意：在事件处理函数中，this即dom对象 	
                            dom对象要转换为jquery对象的方法为$(dom对象) 返回jquery对象
                            jquery对象可以看做一个数组，可以通过size()或者length属性得到数组大小
                
                
                jquery对象.focusin	([eventData],handler)
                
                
                参数名                   参数类型                    说明
                eventData				 任意类型                    可以通过eventObject.data来获取
                handler                 Function(Event eventObject)  事件处理函数 eventObject为事件对象
                
                
                    <p> <input type="text" /><span>focusin fire</span> </p>  
                    
                    <script type="text/javascript">
                        $("p").focusin(function(){
                            $(this)
                                .find("span")
                                .css("display","inline")
                                .fadeOut(1000);
                        });
                    </script>			
                focusin类似于focus获得焦点事件，但不同之处它支持事件冒泡，事件加在父元素上，而不是像focus必须将
                事件加在文本框中
                
                
                jquery对象.focusout([eventData],handler)
                
                
                参数名                   参数类型                    说明
                eventData								 任意类型                    可以通过eventObject.data来获取
                handler                 Function(Event eventObject)  事件处理函数 eventObject为事件对象
                
                focusout类似于blur失去焦点事件，但不同之处它支持事件冒泡，事件加在父元素上，而不是像focus必须将
                事件加在文本框中
                
                
                
                    <div id="tip" style="width:100px;height:20px;position: absolute;background-color: black;color:white;display: none;">
                    </div>
                    <a class="tip" href="#" title="提示信息">
                    超链接
                    <span style="color: black;">其他部分</span>
                    </a>	
                    <script type="text/javascript">
                    
                        $(".tip").mouseover(function(){
                            var pos = $(this).position();
                            $("#tip")
                                .html(this.title)
                                .css({top:pos.top+20,left:pos.left+$(this).width()})
                                .show(300);
                        })
                        .mouseout(function(){
                            $("#tip").hide(300);
                        })
                    </script>		
                    
                    防止不断闪烁的现象，把mouseout替换为mouseleave  把mouseover替换成mouseenter
                    
                    parent.on(events[selector],[data],handler)
                    
                    events   String     一或多个  以空格分隔事件名称
                    selector  String    一个用于过滤后代元素的选择器
                    data    任意类型     
                    handler   fucntion
                    
                    <input type="button" value="按钮" id="btn" />
                    <script type="text/javascript">
                        $("#btn").on("click",function(){
                            alert("123");
                        });
                    </script>
                    
                    
                    
                        <div id="d">
                    <input type="button" value="按钮" id="btn" />
                    </div>
                    <script type="text/javascript">
                        $("#d").on("click","#btn",function(){
                            alert("123");
                        });
                    </script>	
                    
                    
                    
                方法
                
                jquery的大部分方法支持链式调用，因为每个方法内部返回的是jquery对象本身。
                
                链式调用：它是指通过重用一个初始操作来达到用少量代码表达复杂操作的目的。
                
                一个创建代表html元素对象的工厂
                一批对这个html元素执行某些操作的方法
                
                1，属性和内容的相关方法
                
                    获取属性值  .attr(attributename)
                    
                    
                    atributename              String          属性名
                    
                    <img src="/0827/images/1.gif" id="im">
                    $("#im").attr("src")
                
                
                    修改属性值 .attr（attributename,value）
                    <img src="/0827/images/1.gif" id="im">
                    $("#im").attr("src","/0827/images/2.gif");
                    
                    
                    获得属性值  .prop(propertyName)
                    propertyName             String           属性名
                    
                        <input type="checkbox" checked="checked"/>
                        <input type="button" id="bt"/>
                            <script type="text/javascript">
                        $("#bt").on("click",function(){
                            alert($(":checkbox").prop("checked"));
                        });
                    </script>
                    
                    如果使用$(":checkbox").attr("checked") 结果为checked字符串
                    
                    如果checked属性不存在attr返回是undefined 而prop返回是false
                    
                    prop方法时在jquery1.6以后加入的
                    
                    推荐checked   disabled   selected 这些属性使用prop 
                    
                    
                    修改属性值  .prop(propertyName,value)
                    
                    propertyName                  String     属性名
                    value                        boolean     属性值
                    
                            <input type="checkbox" checked="checked"/>
                        <input type="button" id="bt"/>
                            <script type="text/javascript">
                        $("#bt").on("click",function(){
                            $(":checkbox").prop("checked",false);
                        });
                    </script>
                    
                    
                    获取value属性值       .val()
                    <input type="text" id="t1"/>
                    $("#t1").val()
                    
                    修改value属性值        .val(value)
                    
                    value      String  or  Array                 属性值
                    
                    <input type="text" id="t1"  value="zhangsan"/>
                    $("#t1").val("lisi");
                    
                            唱歌<input type="checkbox" value="唱歌">
                        跳舞<input type="checkbox" value="跳舞">
                        学习<input type="checkbox" value="学习">
                        <input type="button" id="bt"/>
                            <script type="text/javascript">
                        $("#bt").on("click",function(){
                            $(":checkbox").val(["唱歌","跳舞"]);
                        });
                    </script>
                    
                    获取文本内容   .text()
                    
                            <p>纯棉布矩形，<b>处处为啼鸟</b></p>
                        <input type="button" id="bt"/>
                            <script type="text/javascript">
                        $("#bt").on("click",function(){
                            alert($("p").text());
                        });
                    </script>
                    
                    
                    设置文本内容    .text(value)
                    
                            <p></p>
                        <input type="button" id="bt"/>
                            <script type="text/javascript">
                        $("#bt").on("click",function(){
                            $("p").text("处处闻啼鸟");
                        });
                    </script>
                    用text修改文本内容，不要加html标签
                    
                    设置html内容   .html(value)
                            <p></p>
                        <input type="button" id="bt"/>
                            <script type="text/javascript">
                        $("#bt").on("click",function(){
                            $("p").html("<b>处处闻啼鸟</b>");
                        });
                    </script>
                    
                    相当于 dom.innerHTML="<b>...</b>"
                    
                    
                    
                    设置html内容    .empty()   清除标签之间的所有内容
                    
                        <select>
                            <option>北京</option>
                            <option>上海</option>
                            <option>广州</option>
                        </select>
                        <input type="button" id="bt"/>
                         <script type="text/javascript">
                            $("#bt").on("click",function(){
                                $("select").empty();
                            });
                         </script>	
