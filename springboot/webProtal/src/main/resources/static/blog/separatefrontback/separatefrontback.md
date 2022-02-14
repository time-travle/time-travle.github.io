# 前后端分离开发模式

## 对于后端Java工程师：

    把精力放在Java基础，设计模式，jvm原理，spring+springmvc原理及源码，linux，mysql事务隔离与锁机制，mongodb，http/tcp，多线程，
    分布式架构，弹性计算架构，微服务架构，Java性能优化，以及相关的项目管理等等。
    
        后端：只负责Model层，业务处理/数据等。
    
        后端追求的是：三高（高并发，高可用，高性能），安全，存储，业务等等。

## 对于前端工程师：

    把精力放在html5，css3，jquery，angularjs，bootstrap，reactjs，vuejs，webpack，less/sass，gulp，nodejs，Google V8引擎，javascript多线程，
    模块化，面向切面编程，设计模式，浏览器兼容性，性能优化等等。
    
        前端：负责View和Controller层。
    
        前端追求的是：页面表现，速度流畅，兼容性，用户体验等等。

## 新的方式的请求步骤：

    大量并发浏览器请求--->web服务器集群(nginx)--->应用服务器集群(tomcat)--->文件/数据库/缓存/消息队列服务器集群
    
        同时又可以玩分模块，还可以按业务拆成一个个的小集群，为后面的架构升级做准备

## 前后端分离的优势

    可以实现真正的前后端解耦，前端服务器使用nginx。
    前端/WEB服务器放的是css，js，图片等等一系列静态资源（甚至你还可以css，js，图片等资源放到特定的文件服务器，例如阿里云的oss，并使用cdn加速），
    前端服务器负责控制页面引用&跳转&路由，前端页面异步调用后端的接口，
    后端/应用服务器使用tomcat（把tomcat想象成一个数据提供者），加快整体响应速度。
    （这里需要使用一些前端工程化的框架比如nodejs，react，router，react，redux，webpack）

    发现bug，可以快速定位是谁的问题，不会出现互相踢皮球的现象。
    页面逻辑，跳转错误，浏览器兼容性问题，脚本错误，页面样式等问题，全部由前端工程师来负责。
    接口数据出错，数据没有提交成功，应答超时等问题，全部由后端工程师来解决。
    双方互不干扰，前端与后端是相亲相爱的一家人。

    在大并发情况下，我可以同时水平扩展前后端服务器，比如淘宝的一个首页就需要2000+台前端服务器做集群来抗住日均多少亿+的日均pv。
    
    减少后端服务器的并发/负载压力。除了接口以外的其他所有http请求全部转移到前端nginx上，接口的请求调用tomcat，参考nginx反向代理tomcat。
    
    且除了第一次页面请求外，浏览器会大量调用本地缓存。
    
    即使后端服务暂时超时或者宕机了，前端页面也会正常访问，只不过数据刷不出来而已。
    
    也许你也需要有微信相关的轻应用，那样你的接口完全可以共用，如果也有app相关的服务，那么只要通过一些代码重构，也可以大量复用接口，提升效率。（多端应用）
    
    页面显示的东西再多也不怕，因为是异步加载。
    
    nginx支持页面热部署，不用重启服务器，前端升级更无缝。
    
    增加代码的维护性&易读性（前后端耦在一起的代码读起来相当费劲）。
    
    提升开发效率，因为可以前后端并行开发，而不是像以前的强依赖。
    
    在nginx中部署证书，外网使用https访问，并且只开放443和80端口，其他端口一律关闭（防止黑客端口扫描），内网使用http，性能和安全都有保障。
    
    前端大量的组件代码得以复用，组件化，提升开发效率，抽出来！

## 总结

    前后端分离并非仅仅只是一种开发模式，而是一种架构模式（前后端分离架构）。
    千万不要以为只有在撸代码的时候把前端和后端分开就是前后端分离了，需要区分前后端项目。
    前端项目与后端项目是两个项目，放在两个不同的服务器，需要独立部署，两个不同的工程，两个不同的代码库，不同的开发人员。
    前后端工程师需要约定交互接口，实现并行开发，开发结束后需要进行独立部署，前端通过ajax来调用http请求调用后端的restful api。
    前端只需要关注页面的样式与动态数据的解析&渲染，而后端专注于具体业务逻辑

## 为何有的前后分离的过程中使用nodejs

    为什么要增加一层NodeJS？
    现阶段我们主要以后端MVC的模式进行开发，这种模式严重阻碍了前端开发效率，也让后端不能专注于业务开发。
    解决方案是让前端能控制Controller层，但是如果在现有技术体系下很难做到，因为不可能让所有前端都学java，安装后端的开发环境，写VM。
    NodeJS就能很好的解决这个问题，我们无需学习一门新的语言，就能做到以前开发帮我们做的事情，一切都显得那么自然

链接：<a href="https://www.jianshu.com/p/2e3e50d90dfb" target="_blank">https://www.jianshu.com/p/2e3e50d90dfb </a>

## springboot和vue整合的关键操作

前后端分离的开发，前端开发好后将build构建好的dist下static中的文件拷贝到springboot的resource的static下， index.html则直接拷贝到springboot的resource的static下。

最简单的合并方式，但是如果作为工程级的项目开发，并不推荐使用手工合并，也不推荐将前端代码构建后提交到springboot的resouce下， 好的方式应该是保持前后端完全独立开发代码，项目代码互不影响，
借助jenkins这样的构建工具在构建springboot时触发前端构建并编写自动化脚本将前端webpack构建好的资源拷贝到springboot下再进行jar的打包， 最后就得到了一个完全包含前后端的springboot项目了

无法正常访问静态资源-----重新指定springboot的静态资源处理前缀，代码：

    @Configuration
    public class SpringWebMvcConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            super.addResourceHandlers(registry);
        }
    }

### vue router路由的路径无法正常解析

    解决问题的方式是对vue的路由的路径做rewrite，交给router来处理，而不是springboot自己处理，rewrite时可以考虑路由的路径统一增加后缀，
    然后在springboot中编写过滤拦截特定后缀来做请求转发交给vue的路由处理
    const router = new VueRouter({
        mode: 'history',
        base: __dirname,
        routes: [
            {
                path: '/ui/first.vhtml',
                component: First
            },
            {
                path: '/ui/second.vhtml',
                component: secondcomponent
            }
        ]
    })

后端拦截到带有vhtml的都交给router来处理，这种方式在后端写过滤器拦截后打包是完全可行的，但是前端开发的直接访问带后缀的路径会有问题。

另外一种方式是给前端的路由path统一加个前缀比如/ui，当然就可以把之前的后缀删除了，这时后端写过滤器匹配该前缀，也不会影响前端单独开发是的路由解析问题。 过滤器参考如下：

    /**
    * be used to rewrite vue router
    *
    * @author yu on 2017-11-22 19:47:23.
    */
    public class RewriteFilter implements Filter {

      /**
        * 需要rewrite到的目的地址
        */
      public static final String REWRITE_TO = "rewriteUrl";

      /**
        * 拦截的url,url通配符之前用英文分号隔开
        */
      public static final String REWRITE_PATTERNS = "urlPatterns";

      private Set<String> urlPatterns = null;//配置url通配符

      private String rewriteTo = null;
      @Override
      public void init(FilterConfig cfg) throws ServletException {
            //初始化拦截配置
            rewriteTo = cfg.getInitParameter(REWRITE_TO);
            String exceptUrlString = cfg.getInitParameter(REWRITE_PATTERNS);
          if (StringUtil.isNotEmpty(exceptUrlString)) {
              urlPatterns = Collections.unmodifiableSet(
              new HashSet<>(Arrays.asList(exceptUrlString.split(";", 0))));
          } else {
            urlPatterns = Collections.emptySet();
          }
      }

      @Override
      public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
          HttpServletRequest request = (HttpServletRequest) req;
          String servletPath = request.getServletPath();
          String context = request.getContextPath();
          //匹配的路径重写
          if (isMatches(urlPatterns, servletPath)) {
              req.getRequestDispatcher(context+"/"+rewriteTo).forward(req, resp);
          }else{
              chain.doFilter(req, resp);
          }
      }

      @Override
      public void destroy() {
    
      }

      /**
        * 匹配返回true，不匹配返回false
        * @param patterns 正则表达式或通配符
        * @param url 请求的url
        * @return
        */
      private boolean isMatches(Set<String> patterns, String url) {
          if(null == patterns){
            return false;
          }
          for (String str : patterns) {
              if (str.endsWith("/*")) {
                  String name = str.substring(0, str.length() - 2);
                      if (url.contains(name)) {
                        return true;
                      }
                  } else {
                      Pattern pattern = Pattern.compile(str);
                      if (pattern.matcher(url).matches()) {
                        return true;
                      }
                  }
              }
            return false;
          }
      }


      过滤器的注册：
    @SpringBootApplication
    public class SpringBootMainApplication {
        public static void main(String[] args) {
            SpringApplication.run(SpringBootMainApplication.class, args);
        }

        @Bean
        public EmbeddedServletContainerCustomizer containerCustomizer() {
            return (container -> {
                    ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errors/401.html");
                    ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errors/404.html");
                    ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errors/500.html");
                    container.addErrorPages(error401Page, error404Page, error500Page);
            });
        }
        @Bean
        public FilterRegistrationBean testFilterRegistration() {
            FilterRegistrationBean registration = new FilterRegistrationBean();
            registration.setFilter(new RewriteFilter());//注册rewrite过滤器
            registration.addUrlPatterns("/*");
            registration.addInitParameter(RewriteFilter.REWRITE_TO,"/index.html");
            registration.addInitParameter(RewriteFilter.REWRITE_PATTERNS, "/ui/*");
            registration.setName("rewriteFilter");
            registration.setOrder(1);
            return registration;
        }
    }   

## springboot+vue + nginx 搭建前后端分离项目

<a href="https://blog.csdn.net/weixin_42912237/article/details/88054539" target="_blank">https://blog.csdn.net/weixin_42912237/article/details/88054539 </a>

### 1、首先得安装node.js

    检查是不是安装了node  ：   node -v
    安装Node.js的时候就已经自带了npm，输入npm -v可得到npm的版本。
    
        命令npm -g install npm，可以更新npm至最新版本
        运行命令 cnpm install -g vue-cli 安装脚手架
        同样的方法安装vue-resource、vuex、jquery、bootstrap
        npm install axios -S    安装axios
        npm install --save popper.js

### 2、配置nginx

    解压后，打开nginx.conf进行配置
    配置demo：
    
            #user  nobody;
            worker_processes  1;
            #error_log  logs/error.log;
            #error_log  logs/error.log  notice;
            #error_log  logs/error.log  info;
            #pid        logs/nginx.pid;
            events {
                worker_connections  1024;
            }
            http {
                include       mime.types;
                default_type  application/octet-stream;
                #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                #                  '$status $body_bytes_sent "$http_referer" '
                #                  '"$http_user_agent" "$http_x_forwarded_for"';
                #access_log  logs/access.log  main;
                sendfile        on;
                #tcp_nopush     on;
                #keepalive_timeout  0;
                keepalive_timeout  65;
                #gzip  on;
                #upstream fbm {
                #    server 172.168.11.79:8081;
                #	server 172.168.10.243:8088;
                #}
                server {
                    listen       80;  ------端口号
                    server_name  172.168.10.243; ------域名
                    root 你的dist文件夹的绝对路径;   ------根
                    autoindex on;    ------将你匹配的文件自动匹配到index.html
    
                    #添加头部信息
                    proxy_set_header Cookie $http_cookie;
                    proxy_set_header X-Forwarded-Host $host;
                    proxy_set_header X-Forwarded-Server $host;
                    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                    proxy_cookie_domain localhost:8080 172.168.10.243; # 把cookie的path部分从localhost:8080替换成your.domain.name
                    #charset koi8-r;
                    #access_log  logs/host.access.log  main;
                    location /匹配路径{
                        proxy_pass 接口反向代理的目标target;    ------在这里配置你的反向代理，若要配置多个代理路径，将此代码复制多个修改即可
                    }
                    location / {
                        proxy_pass http://172.168.10.243:8082/;
                    }
                    location /api/ { #用户
                        rewrite  ^/api/(.*)$ /$1 break;
                        proxy_pass   http://172.168.10.243:8088/api/;
                    }
                    location =/ {
                      root /Users/abee/WebstormProjects/Angular/dist;
                      index index.html index.htm;
                      try_files $uri $uri/ /index.html =404;
                    }
    
                    location ~* \.(js|css|htm|html|gif|jpg|jpeg|png|bmp)$ {
                      root /Users/abee/WebstormProjects/Angular/dist; 
    
                    }
                    location / {
                      proxy_pass http://localhost:8200/;
                    }
                    #error_page  404              /404.html;
                    # redirect server error pages to the static page /50x.html
                    #
                    error_page   500 502 503 504  /50x.html;
                    location = /50x.html {
                        root   html;
                    }
                    # proxy the PHP scripts to Apache listening on 127.0.0.1:80
                    #
                    #location ~ \.php$ {
                    #    proxy_pass   http://127.0.0.1;
                    #}
                    # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
                    #
                    #location ~ \.php$ {
                    #    root           html;
                    #    fastcgi_pass   127.0.0.1:9000;
                    #    fastcgi_index  index.php;
                    #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
                    #    include        fastcgi_params;
                    #}
                    # deny access to .htaccess files, if Apache's document root
                    # concurs with nginx's one
                    #
                    #location ~ /\.ht {
                    #    deny  all;
                    #}
                }
                # another virtual host using mix of IP-, name-, and port-based configuration
                #
                #server {
                #    listen       8000;
                #    listen       somename:8080;
                #    server_name  somename  alias  another.alias;
                #    location / {
                #        root   html;
                #        index  index.html index.htm;
                #    }
                #}
                # HTTPS server
                #
                #server {
                #    listen       443 ssl;
                #    server_name  localhost;
                #    ssl_certificate      cert.pem;
                #    ssl_certificate_key  cert.key;
                #    ssl_session_cache    shared:SSL:1m;
                #    ssl_session_timeout  5m;
                #    ssl_ciphers  HIGH:!aNULL:!MD5;
                #    ssl_prefer_server_ciphers  on;
                #    location / {
                #        root   html;
                #        index  index.html index.htm;
                #    }
                #}
            }

### 3、创建springboot项目

    配置application.yml
    server:
    port: 8088
    定义过滤器跨域使用：
    import org.springframework.stereotype.Component;
    import javax.servlet.*;
    import javax.servlet.annotation.WebFilter;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;

        @Component
        @WebFilter(filterName = "OriginFilter",urlPatterns="/*")
        public class OriginFilter implements Filter {
            public void destroy() {
            }

            public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
                HttpServletResponse response = (HttpServletResponse) resp;
                HttpServletRequest request = (HttpServletRequest) req;
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
                response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
                response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域

                //response.reset();
              /*  response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACES");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "x-requested-with");*/
                chain.doFilter(req, resp);
            }

            public void init(FilterConfig config) throws ServletException {

            }

        }
    
    在controller层新建Hello

        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        @RestController
        public class Hello {
            @RequestMapping(value = "/hello")
            public String sayHello(){
                return "Hello springboot!";
            }
        }    

### 4、分别运行前后端项目

    在浏览器输入访问地址（通过nginx访问的地址）
    172.168.10.243:80

## 部署前后端分离式nginx配置的完整步骤

<a href="https://www.zhangshengrong.com/p/YjNKn8jLaW/" target="_blank">https://www.zhangshengrong.com/p/YjNKn8jLaW/ </a>

    请求转发
    location ^~ /api {
    proxy_pass http://api.xxx.com/;
    }
    这里就特别简单了，我通过正则匹配/api这个请求，通过proxy_pass属性，将请求定向到http://api.xxx.com。即可
    
    修改cookie domain
    有时候处于安全考虑，我们会设置一定的cookie的domain属性这是对于nginx转发来说就很不友好了。当然也是有解决手段的，也很简单。
    
        location {
          proxy_cookie_domain <本域的domain> <想修改的domain>;
        }
    
    修改cookie path
    当我们转发回api接口时，有时候api域名拿不到cookie，除了domain还有cookie path的可能性。当然解决方案也很简单
    
        location {
          proxy_cookie_path <本域的路径> <想修改的路径>;
        }
    
    location配置
    location / {
    root $root;
    }
    好了最简单的基于根路径配置就这样好了，这里无非是通过location配置一条路径，然后指向到$root文件夹下的index.html这个文件下
    
        多条location配置
            location ^~ /a {
              alias $root/a;
            }
    
            location ^~ /b {
              alias $root/b;
            }
