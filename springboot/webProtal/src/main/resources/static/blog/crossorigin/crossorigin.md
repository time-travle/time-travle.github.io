#跨域请求
<a href="#" onclick="refreshCrossOriginContent('httpclient')">Http Client 相关</a>&emsp;&emsp;&emsp;

---

0、什么是跨域请求

	首先，域=协议名+主机名+端口号，只有这三部分相同才能称为是相同的域访问。
	如下举例：
		http://www.baidu.com:80和ftp://www.baidu.com:80           不同域，协议不一样
		http://www.baidu.com:80和http://www.xiaomi.com:80           不同域，主机名不一样
		http://www.baidu.com:80和ftp://www.baidu.com:8080           不同域，端口号不一样
		http://www.baidu.com:80/a.html和ftp://www.baidu.com:80/b.js           同域

	跨域请求的前因后果
		为什么浏览器要限制跨域请求，其中最主要的原因就是安全性问题，比如CSRF攻击。
		但是，既然不安全，为什么我们又要跨域请求呢？
		原因是有时为了服务器便于管理和减轻服务器压力，公司会把不同的资源放在不同的服务器上，这样就存在很多子域


 
1、如何完成服务跨域访问    https://zhuanlan.zhihu.com/p/50416743

	1、前端对接nginx或者其他的中间服务器，通过转发完成跨域
		对跨域访问的支持在服务端实现起来更加容易，最常用的方法就是通过代理的方式，如：
		nginx或haproxy代理跨域
		nodejs中间件代理跨域

	2、解决跨域请求的方式  （野路子出身却异常好用的方式）
		常用的是JSONP
			ajax请求不同域会出现跨域请求，无访问权限，但平时在HTML页面写的<script>、<link>这些标签的src属性是不受跨域请求限制的，
			于是，JSONP的策略就是服务器端可以动态生成JSON文件，把客户端需要的数据放到这个文件中，
			让客户端通过<script>标签的src属性来请求这个文件
		JSONP优点是兼容性好，可用于解决主流浏览器的跨域数据访问的问题。
		缺点是仅支持get方法具有局限性。	
		
	3、通过CORS方式解决简单跨域请求 （跨源资源共享）（官方推荐的跨域资源共享方案）
		即跨源资源共享，这种机制允许浏览器向跨源服务器发出xmlhttprequest请求，这是浏览器所支持的，
		所以我们只需要做的是在服务器端判断是否允许这个域访问（Access-Control-Allow-Origin），剩下的浏览器会自动做好，添加一些头信息
		
		当使用 XMLHttpRequest 发送请求时，浏览器如果发现违反了同源策略就会自动加上一个请求头 origin，
		后端在接受到请求后确定响应后会在 Response Headers 中加入一个属性 Access-Control-Allow-Origin，
		值就是发起请求的源地址(http://127.0.0.1:8888)，浏览器得到响应会进行判断 Access-Control-Allow-Origin 的值是否和当前的地址相同，
		只有匹配成功后才进行响应处理。
		
		CORS优缺点
		CORS要求浏览器(>IE10)和服务器的同时支持，是跨域的根本解决方法，由浏览器自动完成。
		优点在于功能更加强大支持各种HTTP Method，
		缺点是兼容性不如JSONP
		
	4、postmessage跨域
		在 HTML5 中新增了 postMessage 方法，postMessage 可以实现跨文档消息传输 Cross Document Messaging，
		IE8，Firefox 3，Opera 9，Chrome 3 和 Safari 4 都支持 postMessage。
		该方法可以通过绑定 window 的 message 事件来监听发送跨文档消息传输内容。
		使用 postMessage 实现跨域的话原理就类似于 jsonp，动态插入 iframe标签，再从 iframe 里面拿回数据

		// 发送消息界面
		function run(){
			var frm=document.getElementById("frm");
			frm.contentWindow.postMessage("跨域请求信息","http://localhost:3000");
		}
	
		//接收信息页面 http://localhost:3000/message.html
		window.addEventListener("message",function(e){ //通过监听message事件，可以监听对方发送的消息。
			console.log(e.data);
		},false);

	5、抛弃HTTP，使用：Web Sockets； （看起来操作起来比较麻烦 先放放）
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-websocket</artifactId>
        </dependency>


Spring MVC自带的跨域问题解决方案

    Spring MVC 已经有很好的解决方案了 直接添加一个注解( @CrossOrigin) 即可解决跨域问题
    或者
    <mvc:cors>
        <mvc:mapping path="/**" allowed-origins="*" allow-credentials="true" max-age="1800"
        allowed-methods="GET,POST,PUT,DELETE,PATCH,OPTIONS"/>
    </mvc:cors>

带有cookie的跨域问题

    带有cookie时，配置Access-Control-Allow-Origin项不能为*，必须是具体的值！


四种跨域请求资源的方案  https://zhuanlan.zhihu.com/p/50416743

    野路子出身却好用的方式：JSONP；
    官方推荐的跨域资源共享方案：CORS；
    使用HTML5 API：postMessage；
    抛弃HTTP，使用：Web Sockets；
    
---
SpringBoot 处理跨域请求 ：

https://blog.csdn.net/weixin_42036952/article/details/88564647	

https://www.jianshu.com/p/85db845d3929

    
    
      