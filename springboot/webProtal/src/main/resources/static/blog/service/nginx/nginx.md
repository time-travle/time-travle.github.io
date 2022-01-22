<p>
    <a href="#" onclick="refreshContent('service')">返回目录</a>
</p>

#nginx的使用
    
    高性能的HTTP和反向代理服务器，同时也是一个IMAP/POP3/SMTP 代理服务器。
    
    特点:占有内存少，并发能力强，易于开发，部署方便。Nginx 支持多语言通用服务器。
    
    缺点：Nginx 只适合静态和反向代理。
    
    优点：负载均衡、反向代理、处理静态文件优势。Nginx 处理静态请求的速度高于Apache。
    Nginx有动态分离机制，静态请求直接就可以通过Nginx处理，动态请求才转发请求到后台交由Tomcat进行处理。


##nginx的优点
- 高并发连接：官方测试能够支撑5万并发连接，在实际生产环境中跑到2-3万并发连接数
- 内存消耗少：在3万并发连接下，开启的10个nginx进程才消耗150M内存（15M*10=150M）
- 配置文件非常简单：风格跟程序一样通俗易懂
- 成本低廉：nginx为开源软件，可以免费使用。而购买F5 BIG-IP、NetScaler等硬件负载均衡交换机则需要十多万至几十万人民币
- 支持Rewrite重写规则：能够根据域名、URL的不同，将HTTP请求分到不同的后端服务器群组
- 内置的健康检查功能：如果Nginx Proxy后端的某台Web服务器宕机了，不会影响前端访问
- 节省带宽：支持GZIP压缩，可以添加浏览器本地缓存的Header头
- 稳定性高：用于反向代理，宕机的概率微乎其微
- 模块化设计：模块可以动态编译
- 外围支持好：文档全，二次开发和模块较多
- 支持热部署：可以不停机重载配置文件
- 支持事件驱动、AIO（AsyncIO，异步IO）、mmap（Memory Map，内存映射）等性能优化

##nginx反向代理
- 多个客户端给服务器发送的请求，Nginx服务器接收到之后，按照一定的规则分发给了后端的业务处理服务器进行处理了。此时~请求的来源也就是客户端是明确的，但是请求具体由哪台服务器处理的并不明确了，Nginx扮演的就是一个反向代理角色。

- 客户端是无感知代理的存在的，反向代理对外都是透明的，访问者并不知道自己访问的是一个代理。因为客户端不需要任何配置就可以访问。

- 反向代理，“它代理的是服务端”，主要用于服务器集群分布式部署的情况下，反向代理隐藏了服务器的信息。

###反向代理的作用
- 保证内网的安全，通常将反向代理作为公网访问地址，Web服务器是内网
- 负载均衡，通过反向代理服务器来优化网站的负载
###正向代理和反向代理的区别
- 在正向代理中，Proxy和Client同属于一个LAN（图中方框内），隐藏了客户端信息；
- 在反向代理中，Proxy和Server同属于一个LAN（图中方框内），隐藏了服务端信息；


[https://blog.csdn.net/weixin_43695104/article/details/88034435](https://blog.csdn.net/weixin_43695104/article/details/88034435)



##常用命令

#### 查看 Nginx 程序文件目录:/usr/sbin/nginx
    $ ps  -ef | grep nginx

#### 查看 nginx.conf 配置文件目录:/etc/nginx/nginx.conf
    $ nginx -t                 
    $ vim /etc/nginx/nginx.conf

#### 配置文件目录：/etc/nginx

#### 虚拟主机配置文件目录：/etc/nginx/sites-available/
#### 虚拟主机文件夹目录：/var/www/，详情可在 /etc/nginx/sites-available/ 中配置
#### 默认网页文件目录：/usr/share/nginx/html

#### 测试配置文件，只检查配置文件是否存在语法错误
    $ nginx -t -c <path-to-nginx.conf>
    $ sudo nginx -t -c /etc/nginx/nginx.conf

#### 启动 Nginx 服务
    $ nginx 安装目录 -c <path-to-nginx.conf>
    $ sudo /etc/init.d/nginx start

#### 停止 Nginx 服务
    $ sudo /usr/sbin/nginx -s stop 

#### 重启 Nginx 
    $ sudo /usr/sbin/nginx -s reload  # 0.8 版本之后的方法
    $ kill -HUP pid     # 向 master 进程发送信号从容地重启 Nginx，即服务不中断
    
    $ sudo service nginx start
    $ sudo service nginx stop
    $ sudo service nginx restart


##配置说明

###main模块
####main模块类似main函数包含其他子模块， 非模块配置项(包括模块内)分号结尾， 子模块配置花括号结尾
    user nobady; #一般按默认设置
    pid /var/run/nginx.pid; #进程标识符存放路径， 一般按默认设置
    worker_processes auto; #nginx对外提供web服务时的worder进程数， 可将其设置为可用的CPU内核数， auto为自动检测
    worker_rlimit_nofile 100000; # 更改worker进程的最大打开文件数限制
    error_log logs/error.log info; #错误日志存放路径
    keepalive_timeout 60; #keepalive_timeout 60;
    events{
        #见events模块
    }
    http{ #见http模块
        server{
            ...
            location /{
            }
        }
    }
    mail{
     #见mail模块
    }
    
####events模块
    events {
        worker_connections 2048; #设置可由一个worker进程同时打开的最大连接数
        multi_accept on; #告诉nginx收到一个新连接通知后接受尽可能多的连接
        use epoll; #设置用于复用客户端线程的轮询方法。 Linux 2.6+： 使用epoll； *BSD： 使用kqueue。
    }
    
####http模块
    http { #http模块
        server { #server模块， http服务上的虚拟主机， server 当做对应一个域名进行的配置
            listen 80; #配置监听端口
            server_name www.linuxidc.com; #配置访问域名
            access_log logs/linuxidc.access.log main; #指定日志文件的存放路径
            index index.html; #默认访问页面
            root /var/www/androidj.com/htdocs; # root 是指将本地的一个文件夹作为所有url 请求的根路径
            upstream backend { #反向代理的后端机器， 实现负载均衡
                ip_hash; #指明了我们均衡的方式是按照用户的 ip 地址进行分配
                server backend1.example.com;
                server backend2.example.com;
                server backend3.example.com;
                server backend4.example.com;
            }
            location / { #location 是在一个域名下对更精细的路径进行配置
                proxy_pass http://backend; #反向代理到后端机器
            }
        }
    
        server {
            listen 80;
            server_name www.Androidj.com;
            access_log logs/androidj.access.log main;
            location / {
                index index.html;
                root /var/www/androidj.com/htdocs;
            }
        }
    }
    
mail模块

    mail {
        auth_http 127.0.0.1:80/auth.php;
        pop3_capabilities "TOP" "USER";
        imap_capabilities "IMAP4rev1" "UIDPLUS";
    
        server {
            listen 110;
            protocol pop3;
            proxy on;
        }
        server {
            listen 25;
            protocol smtp;
            proxy on;
            smtp_auth login plain;
            xclient off;
        }
    }