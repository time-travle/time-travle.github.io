<p>
    <a href="#" onclick="refreshContent('messagemq')">返回目录</a>
</p>

# MessageMQ -websocket


##搭建webSocket非常简单，只需要以下两步

    后台创建websocket配置类
    后台创建websocket类
    前端页面连接

https://www.cnblogs.com/interdrp/p/7903736.html
https://www.zifangsky.cn/1355.html


##后台WebSocket配置类如下
    配置类，照搬即可

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.socket.server.standard.ServerEndpointExporter;

    @Configuration
    public class MyWebSocketConfig {
        /**
         * ServerEndpointExporter 作用
         *
         * 这个Bean会自动注册使用@ServerEndpoint 注解声明的websocket endpoint
         *
         * @return
         */
        @Bean
        public ServerEndpointExporter serverEndpointExporter() {
            return new ServerEndpointExporter();
        }
    }


##WebSocket类
    这个类可以根据实际需求来进行封装，简化调用

    import org.springframework.stereotype.Component;

    import javax.websocket.OnClose;
    import javax.websocket.OnMessage;
    import javax.websocket.OnOpen;
    import javax.websocket.Session;
    import javax.websocket.server.PathParam;
    import javax.websocket.server.ServerEndpoint;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.concurrent.CopyOnWriteArraySet;

    @Component
    //前端连接的地址路径
    @ServerEndpoint("/websocket/{key}")
    public class MyWebSocket {
        //与某个客户端的连接会话，需要通过它来给客户端发送数据
        private Session session;

        private static CopyOnWriteArraySet<MyWebSocket> webSockets =new CopyOnWriteArraySet<>();
        //用来存放每个客户端对应的WebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
        private static Map<String,Session> sessionPool = new HashMap<String,Session>();

        @OnOpen
        public void onOpen(Session session, @PathParam(value="key")String key) {
            this.session = session;
            webSockets.add(this);
            sessionPool.put(key, session);
            System.out.println("【websocket消息】有新的连接，总数为:"+webSockets.size());
            sendAllMessage("欢迎您，客户端");
        }

        @OnClose
        public void onClose() {
            webSockets.remove(this);
            System.out.println("【websocket消息】连接断开，总数为:"+webSockets.size());
        }

        @OnMessage
        public void onMessage(String message) {
            System.out.println("【websocket消息】收到客户端消息:"+message);
        }

        // 此为广播消息
        public void sendAllMessage(String message) {
            for(MyWebSocket webSocket : webSockets) {
                System.out.println("【websocket消息】广播消息:"+message);
                try {
                    webSocket.session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // 此为单点消息 (发送文本)
        public void sendTextMessage(String key, String message) {
            Session session = sessionPool.get(key);
            if (session != null) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



##创建一个ServerController类
    这个类是模拟服务器给客户端发送信息用的，通过WebSocket，服务器就可以直接给客户端发送信息。注意，为了便于测试，user变量写死了。代码如下：

    package com.websocket.demo.controller;
     
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RestController;
     
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpSession;
    import javax.websocket.Session;
    import java.io.IOException;
     
    @RestController
    public class ServerController {
        @RequestMapping(value = "/server",method={RequestMethod.POST, RequestMethod.GET})
        public String server(HttpServletRequest request) throws IOException {
            try {
                String msg = request.getParameter("msg");
                String user = request.getParameter("user");
                //注意，为了便于测试，这里写死了
                user = "abc";
                //获取用户的webSocket对象
                WebSocket ws = WebSocket.getClients().get(user);
                //发送消息
                ws.sendMessage(msg);
            }catch (Exception e){
                System.out.println(e.toString());
            }
            return "<script language=\"javascript\" type=\"text/javascript\">\n" +
                    "window.location.href=\"server.html\";\n" +
                    "</script>";
        }
    }



##前端页面
    前端，方法可以自由定义什么时候创建链接，我是直接页面初始化就创建链接

    <template>
    <a-card>
      <div>
        <h1>测试webSocket</h1>
        <button @click="send">点击请求后台数据</button>
        <button @click="onClose">关闭连接</button>
      </div>
      </a-card>
    </template>
    <script>
    export default {
      created() {
        // 页面创建生命周期函数
        this.initWebSocket();
      },
      methods: {
        onClose(){
          this.websock.close();
        },
        initWebSocket() {
          if ("WebSocket" in window) {
            // WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
            //d5533相当于我这台客户端的标识，key,用来单客户端通话
            this.websock = new WebSocket(
              "ws://localhost:8010/websocket/d5533"
            );
            this.websock.onopen = this.websocketonopen;
            this.websock.onerror = this.websocketonerror;
            this.websock.onmessage = this.websocketonmessage;
            this.websock.onclose = this.websocketclose;
          } else {
            alert("not error  不支持websocket");
          }
        },
        //连接回调
        websocketonopen() {
          console.log("WebSocket连接成功");
        },
        //发生错误回调
        websocketonerror(e) {
          console.log("WebSocket连接发生错误");
        },
        //接收到消息的回调函数
        websocketonmessage(e) {
          console.log(e.data); // console.log(e);
        },
        // 连接关闭时的回调函数
        websocketclose(e) {
          console.log("connection closed (" + e + ")");
        },
        send() {
          this.websock.send('后台你好，我是前端');
        }
      }
    };
    </script>
    <style lang="less" scoped>
    </style>

到这里，就完成了一个简单的WebSocket就搭建完成了，可以进行前后端互相通话

---
##Q1:Websocket是什么样的协议，具体有什么优点
    Websocket是一个持久化的协议，相对于HTTP这种非持久的协议来说 支持双向通信，更灵活，更高效，可扩展性更好
    Websocket是基于HTTP协议的，或者说借用了HTTP的协议来完成一部分握手
    WebSocket是一种双向通信协议。在建立连接后，WebSocket服务器端和客户端都能主动向对方发送或接收数据，就像Socket一样
    
    相比HTTP长连接，WebSocket有以下特点：
    是真正的全双工方式，建立连接后客户端与服务器端是完全平等的，可以互相主动请求。
    而HTTP长连接基于HTTP，是传统的客户端对服务器发起请求的模式
    HTTP长连接中，每次数据交换除了真正的数据部分外，服务器和客户端还要大量交换HTTP header，
    信息交换效率很低。Websocket协议通过第一个request建立了TCP连接之后，
    之后交换的数据都不需要发送 HTTP header就能交换数据，
    这显然和原有的HTTP协议有区别所以它需要对服务器和客户端都进行升级才能实现（主流浏览器都已支持HTML5）。
    此外还有 multiplexing、不同的URL可以复用同一个WebSocket连接等功能。这些都是HTTP长连接不能做到的
    
    WebSocket可以在浏览器里使用
    支持双向通信
    使用很简单
    
    https://developer.mozilla.org/zh-CN/docs/Web/API/WebSocket
    
    https://www.cnblogs.com/chyingp/p/websocket-deep-in.html