<p>
    <a href="#" onclick="refreshContent('multithread')">返回目录</a>
</p>

---

# 高并发解决方案

优势

    速度：同时处理多个请求，响应更快；复杂的操作可以分成多个进程同时进行
    
    设计：程序设计在某些情况下更简单，也可以更多的选择
    
    资源利用：CPU能够在等待IO的时候做一些其他的事情

风险

    安全性：多个线程共享数据时可能会产生于期望不相符的结果
    
    活跃性：某个操作无法继续进行下去时，就会发生活跃性问题。比如死锁、饥饿等问题
    
    性能：线程过多时会使得CPU频繁切换，调度时间增多；同步机制；消耗过多内存

常见的互联网分层架构
![avatar](../blog/multithread/imgs/img_8.png)
常见互联网分布式架构如上，分为：

    （1）客户端层：典型调用方是浏览器browser或者手机应用APP
    
    （2）反向代理层：系统入口，反向代理
    
    （3）站点应用层：实现核心应用逻辑，返回html或者json
    
    （4）服务层：如果实现了服务化，就有这一层
    
    （5）数据-缓存层：缓存加速访问存储
    
    （6）数据-数据库层：数据库固化数据存储

高并发问题，关心问题：

    QPS：每秒钟请求或者查询的数量，在互联网领域，指每秒钟请求数（指HTTP请求）
    吞吐量：单位时间没处理的请求数量（通常由QPS与并发数决定）
    响应时间：从请求发出到收到响应花费的时间。例如系统处理一个HTTP请求需要100ms，这个100ms就是系统的响应时间
    PV：综合浏览量（Page View），即页面浏览量或者点击量，一个访客在24小时内访问的页面数量
    同一个人浏览你的网站同一页面，只记作一次PV
    UV：独立访客（UniQue Visitor），即一定时间范围内相同访客多次访问网站，只计算为1个独立访客
    带宽：计算带宽大小需关注两个指标，峰值流量和页面的平均大小
    日网站带宽= PV/统计时间（换算成秒）平均页面大小（单位KB）8峰值一般是平均数倍数，根据实际情况来定
    QPS不等于并发连接数
    QPS是每秒HTTP请求数量，并发连接数是系统同时处理的请求数量
    （总PV数80%）/（6小时秒数20%）=峰值每秒请求数（QPS）80%的访问量集中在20%的时间
    压力测试：测试能承受的最大并发，测试最大承受的QPS值

## 流量优化

    防盗链处理

## 前端优化

    减少HTTP请求（例如精灵图）
    合并css或js
    添加异步请求
    启用浏览器缓存和文件压缩
    CDN加速
    建立独立图片服务器

## 服务端优化

    页面静态化
    并发处理
    队列处理

## 数据库优化

    数据库缓存
    分库分表、分区操作
    读写分离
    负载均衡

    数据库表结构涉及
    数据类型的选用
    sql优化
    索引优化
    配置优化

## Web服务器优化

    负载均衡
    nginx反向代理，
    7,4层LVS软件

---

# 具体方案实施

## 减少HTTP请求次数

    性能黄金法则：
        只有10%-20%的最终用户响应时间花在接收请求的HTML文档上，剩下的80%-90%时间花在HTML文档所引用的所有组件(img，script，css，flash等)进行的HTTP请求上。
    
    如何改善：
        改善响应时间的最简单途径就是减少组件的数量，并由此减少HTTP请求的数量
    
    HTTP连接产生的开销：
        域名解析--TCP连接--发送请求--等待--下载资源--解析时间
    
    疑问：
        DNS缓存，查找DNS缓存也需要时间，多个缓存就要查找多次有可能缓存会被清除；Keep-Alive，HTTP1.1协议规定请求只能串行发送，前面的一个请求完成才能开始下个请求
    
    减少HTTP请求的方式：
        图片地图：允许在一个图片上关联多个URL，目标URL的选择取决于用户单击了图片上的哪个位置，以位置信息定位超链接，把HTTP请求减少为一个，可以保证设计的完整性和功能的齐全性，使用map和area标签；
    
    CSS Sprites：
        CSS精灵，通过使用合并图片，通过指定css的background-image和background-position来显示元素。图片地图与css精灵的响应时间基本上相同，但比使用各自独立图片的方式要快50%以上
    
    合并脚本和样式表：
        使用外部的js和css文件引用的方式，因为这要比直接写在页面中性能要更好一点；独立的一个js比用多个js文件组成的页面载入要快38%；把多个脚本合并为一个脚本，把多个样式表合并为一个样式表
    
    图片使用base64编码减少页面请求数：
        采用base64的编码方式将图片直接嵌入到网页中，而不是从外部载入

## web资源防盗链

    盗链：在自己的页面上展示一些并不在自己服务器上的内容，获得他人服务器上的资源地址，绕过别人的资源展示页面，
        直接在自己的页面上向最终用户提供此内容，常见的是小站盗用大站的图片，音乐，视频，软件等资源，通过盗链的方法可以减轻自己服务器的负担，
        因为真实的空间和流量均是来自别人的服务器
    
    防盗链：防止别人通过一些技术手段绕过本站的资源展示页面，盗用本站的资源，让绕开本站资源展示页面的资源链接失效，可以大大减轻服务器及带宽的压力
    
    工作原理：通过请求头中的referer或者签名，网站可以检测目标网页访问的来源网页，如果是资源文件，
            则可以跟踪到显示它的网页地址，一旦检测到来源不是本站即进行阻止或者返回制定的页面，通过计算签名的方式，判断请求是否合法，如果合法则显示，否则返回错误信息
    
    实现方法：referer：nginx模块ngx_http_referer_module用于阻挡来源非法的域名请求，
        nginx指令valid_referers none | blocked | server_names | string...，
            none表示referer来源头部为空的情况，
            blocked表示referer来源头部不为空，但是里面的值被代理或者防火墙删除了，这些值都不以http://或者https://开头，
            server_names表示referer来源头部包含当前的server_names，全局变量$invalid_referer。
        不能彻底防范，只能提高门槛。也可以针对目录进行防盗链。
        
        //在nginx的conf中配置
        location ~.*\.(gif|jpg|png|flv|swf|rar|zip)$
        {
            valid_referers none blocked zi.com *.zi.com;
            if($invalid_referer)
            {
                #return 403;
                rewrite ^/ http://www.zi.com/403.jpg;
            }    
        }

    传统防盗链遇到的问题：伪造referer：可以使用加密签名解决
    
    加密签名：使用第三方模块HttpAccessKeyModule实现Nginx防盗链。
        accesskey on|off 模块开关，
        accesskey_hashmethod md5|sha-1 签名加密方式，
        accesskey_arg GET参数名称，
        accesskey_signature 加密规则，在nginx的conf中设置

        location ~.*\.(gif|jpg|png|flv|swf|rar|zip)$
        {
            accesskey on;
            accesskey_hashmethod md5;
            accesskey_arg sign;
            accesskey_signature "jason$remote_addr";
        }

## 反向代理

反向代理指的是客户端直接访问的服务器并不真正提供服务，它从别的服务器获取资源然后将结果返回给用户。

图：

![avatar](../blog/multithread/imgs/img_6.png)

## CDN

    cdn其实是一种特殊的集群页面缓存服务器，他和普通集群的多台页面缓存服务器相比，主要是它存放的位置和分配请求的方式有点特殊。
    CDN 服务器是分布在全国各地的，当接收到用户请求后会将请求分配到最合适的CDN服务器节点获取数据。比如联通的用户分配到联通的节点，上海的用户分配到上海的节点。
    
    CDN的每个节点其实就是一个页面缓存服务器，如果没有请求资源的缓存就会从主服务器获取，否则直接返回缓存的页面。
    
    CDN分配请求（负载均衡）的方式是用专门的CDN域名解析服务器在解析域名的时候就分配好的。
    一般的做法是在ISP哪里试用CNAME将域名解析到一个特定的域名，然后再将解析到的那个域名用专门的CDN服务器解析道相应的CDN节点。
    如图。

![avatar](../blog/multithread/imgs/img_7.png)

## 应用和静态资源分离

    刚开始的时候应用和静态资源是保存在一起的，当并发量达到一定程度的时候就需要将静态资源保存到专门的服务器中，
    静态资源主要包括图片、视频、js、css和一些资源文件等，这些文件因为没有状态所以分离比较简单，直接存放到响应的服务器就可以了，一般会使用专门的域名去访问。
    通过不同的域名可以让浏览器直接访问资源服务器而不需要再访问应用服务器了。架构图如下：

![avatar](../blog/multithread/imgs/img_4.png)

## 页面缓存

    页面缓存是将应用生成的页面缓存起来，这样就不需要每次都生成页面了，从而可以节省大量的CPU资源，如果将缓存的页面放到内存中速度就更快了。
    如果使用Nginx服务器就可以使用它自带的缓存功能，当然也可以使用专门的Squid 服务器。页面缓存的默认失效机制一班都是按缓存时间处理的，当然也可以在修改数据之后手动让相应的缓存失效。
    
    页面缓存主要是使用在数据很少发生变化的页面，但是很多页面是大部分数据都很少发生变化，而其中很少一部分数据变化频率却非常高，
    比如说一个显示文章的页面，正常来说完全可以静态化，但是如果文章后面有“顶”和“踩”的功能而且显示的有响应的数量，
    这个数据的变化频率就比较高了，这就会影响静态化。这个问题可以用先生成静态页面然后使用Ajax来读取并修改响应的数据，这样就可以一举两得来，
    既可以使用页面缓存也可以实时显示一些变化频率高的数据来。
    
    其实大家都知道，效率最高、消耗最小的就是纯静态化的html页面，所以我们尽可能使我们的网站上的页面采用静态页面来实现，这个最简单的方法其实也是最有效的方法。
    但是对于大量内容并且频繁更新的网站，我们无法全部手动去挨个实现，于是出现了我们常见的信息发布系统CMS，像我们常访问的各个门户站点的新闻频道，
    甚至他们的其他频道，都是通过信息发布系统来管理和实现的，信息发布系统可以实现最简单的信息录入自动生成静态页面，
    还能具备频道管理、权限管理、自动抓取等功能，对于一个大型网站来说，拥有一套高效、可管理的CMS是必不可少的。
    
    除了门户和信息发布类型的网站，对于交互性要求很高的社区类型网站来说，尽可能的静态化也是提高性能的必要手段，将社区内的帖子、文章进行实时的静态化，
    有更新的时候再重新静态化也是大量使用的策略，像Mop的大杂烩就是使用了这样的策略，网易社区等也是如此。
    
    同时，html静态化也是某些缓存策略使用的手段，对于系统中频繁使用数据库查询但是内容更新很小的应用，可以考虑使用html静态化来实现，比如论坛中论坛的公用设置信息，
    这些信息目前的主流论坛都可以进行后台管理并且存储再数据库中，这些信息其实大量被前台程序调用，但是更新频率很小，可以考虑将这部分内容进行后台更新的时候进行静态化，这样避免了大量的数据库访问请求。

## 系统集群化部署

    假设此时你的用户数开始快速增长，比如注册用户量增长了50倍，上升到了500万。
    
    此时日活用户是50万，高峰期对系统每秒请求是500/s。然后对数据库的每秒请求数量是1500/s，这个时候会怎么样呢？
    
    按照上述的机器配置来说，如果你的系统内处理的是较为复杂的一些业务逻辑，是那种重业务逻辑的系统的话，是比较耗费CPU的。
    
    此时，4核8G的机器每秒请求达到500/s的时候，很可能你会发现你的机器CPU负载较高了。
    
    然后数据库层面，以上述的配置而言，其实基本上1500/s的高峰请求压力的话，还算可以接受。
    
    这个主要是要观察数据库所在机器的磁盘负载、网络负载、CPU负载、内存负载，按照我们的线上经验而言，那个配置的数据库在1500/s请求压力下是没问题的。
    
    所以此时你需要做的一个事情，首先就是要支持你的系统集群化部署。
    
    你可以在前面挂一个负载均衡层，把请求均匀打到系统层面，让系统可以用多台机器集群化支撑更高的并发压力。
    
    比如说这里假设给系统增加部署一台机器，那么每台机器就只有250/s的请求了。
    
    这样一来，两台机器的CPU负载都会明显降低，这个初步的“高并发”不就先cover住了吗？
    
    要是连这个都不做，那单台机器负载越来越高的时候，极端情况下是可能出现机器上部署的系统无法有足够的资源响应请求了，然后出现请求卡死，甚至系统宕机之类的问题。
    
    所以，简单小结，第一步要做的：
    
    添加负载均衡层，将请求均匀打到系统层。 系统层采用集群化部署多台机器，扛住初步的并发压力。
    
    此时的架构图变成下面的样子：

![avatar](../blog/multithread/imgs/img_3.png)

    集群是每台服务器都具有相同的功能，处理请求时调用那台服务器都可以，主要起分流作用。
    
    分布式是将不同的业务放到不同的服务器中，处理一个请求可能需要用到多台服务器，这样就可以提高一个请求的处理速度，而且集群和分布式也可以同时使用。
    
    集群有两个方式：
        一种是在静态资源集群。
        另一种是应用程序集群。
    静态资源集群比较简单。应用程序集群在处理过程中最核心的问题就是Session 同步问题。
    
    Session 同步有两种处理方式：
        一种是在Session 发生变化后自动同步到其他服务器，
        另一种就是用个程序统一管理Session。
    所有集群的服务器都使用同一个Session，Tomcat 默认使用就是第一种方式，通过简单的配置就可以实现，
        第二种方式可以使用专门的服务器安装Mencached等高效的缓存程序统一来管理session，然后再应用程序中通过重写Request并覆盖getSession 方法来获取制定服务器中的Session。
    
    对于集群来说还有一个核心的问题就是负载均衡，也就是接收到一个请求后具体分配到那个服务器去处理的问题，这个问题可以通过软件处理也可以使用专门的硬件（如：F5）解决。

![avatar](../blog/multithread/imgs/img_5.png)

## 数据库分库分表 + 读写分离

    假设此时用户量继续增长，达到了1000万注册用户，然后每天日活用户是100万。
    
    那么此时对系统层面的请求量会达到每秒1000/s，系统层面，你可以继续通过集群化的方式来扩容，反正前面的负载均衡层会均匀分散流量过去的。
    
    但是，这时数据库层面接受的请求量会达到3000/s，这个就有点问题了。
    
    此时数据库层面的并发请求翻了一倍，你一定会发现线上的数据库负载越来越高。
    
    每次到了高峰期，磁盘IO、网络IO、内存消耗、CPU负载的压力都会很高，大家很担心数据库服务器能否抗住。
    
    没错，一般来说，对那种普通配置的线上数据库，建议就是读写并发加起来，按照上述我们举例的那个配置，不要超过3000/s。
    
    因为数据库压力过大，首先一个问题就是高峰期系统性能可能会降低，因为数据库负载过高对性能会有影响。
    
    另外一个，压力过大把你的数据库给搞挂了怎么办？
    
    所以此时你必须得对系统做分库分表 + 读写分离，也就是把一个库拆分为多个库，部署在多个数据库服务上，这是作为主库承载写入请求的。
    
    然后每个主库都挂载至少一个从库，由从库来承载读请求。
    
    此时假设对数据库层面的读写并发是3000/s，其中写并发占到了1000/s，读并发占到了2000/s。
    
    那么一旦分库分表之后，采用两台数据库服务器上部署主库来支撑写请求，每台服务器承载的写并发就是500/s。每台主库挂载一个服务器部署从库，那么2个从库每个从库支撑的读并发就是1000/s。
    
    简单总结，并发量继续增长时，我们就需要focus在数据库层面：分库分表、读写分离。
    
    此时的架构图如下所示：

![avatar](../blog/multithread/imgs/img.png)
针对读多写少的请求，数据库层面的分库分表+读写分离

## 缓存集群引入

    接着就好办了，如果你的注册用户量越来越大，此时你可以不停的加机器，比如说系统层面不停加机器，就可以承载更高的并发请求。
    
    然后数据库层面如果写入并发越来越高，就扩容加数据库服务器，通过分库分表是可以支持扩容机器的，如果数据库层面的读并发越来越高，就扩容加更多的从库。
    
    但是这里有一个很大的问题：数据库其实本身不是用来承载高并发请求的，所以通常来说，数据库单机每秒承载的并发就在几千的数量级，而且数据库使用的机器都是比较高配置，比较昂贵的机器，成本很高。
    
    如果你就是简单的不停的加机器，其实是不对的。
    
    所以在高并发架构里通常都有缓存这个环节，缓存系统的设计就是为了承载高并发而生。
    
    所以单机承载的并发量都在每秒几万，甚至每秒数十万，对高并发的承载能力比数据库系统要高出一到两个数量级。
    
    所以你完全可以根据系统的业务特性，对那种写少读多的请求，引入缓存集群。
    
    具体来说，就是在写数据库的时候同时写一份数据到缓存集群里，然后用缓存集群来承载大部分的读请求。
    
    这样的话，通过缓存集群，就可以用更少的机器资源承载更高的并发。
    
    比如说上面那个图里，读请求目前是每秒2000/s，两个从库各自抗了1000/s读请求，但是其中可能每秒1800次的读请求都是可以直接读缓存里的不怎么变化的数据的。
    
    那么此时你一旦引入缓存集群，就可以抗下来这1800/s读请求，落到数据库层面的读请求就200/s。
    
    同样，给大家来一张架构图，一起来感受一下：

![avatar](../blog/multithread/imgs/img_1.png)

## 引入消息中间件集群

    接着再来看看数据库写这块的压力，其实是跟读类似的。
    
    假如说你所有写请求全部都落地数据库的主库层，当然是没问题的，但是写压力要是越来越大了呢？
    
    比如每秒要写几万条数据，此时难道也是不停的给主库加机器吗？
    
    可以当然也可以，但是同理，你耗费的机器资源是很大的，这个就是数据库系统的特点所决定的。
    
    相同的资源下，数据库系统太重太复杂，所以并发承载能力就在几千/s的量级，所以此时你需要引入别的一些技术。
    
    比如说消息中间件技术，也就是MQ集群，他是非常好的做写请求异步化处理，实现削峰填谷的效果。
    
    假如说，你现在每秒是1000/s次写请求，其中比如500次请求是必须请求过来立马写入数据库中的，但是另外500次写请求是可以允许异步化等待个几十秒，甚至几分钟后才落入数据库内的。
    
    那么此时你完全可以引入消息中间件集群，把允许异步化的每秒500次请求写入MQ，然后基于MQ做一个削峰填谷。比如就以平稳的100/s的速度消费出来然后落入数据库中即可，此时就会大幅度降低数据库的写入压力。
    此时，架构图变成了下面这样：

![avatar](../blog/multithread/imgs/img_2.png)
针对高写入的压力，引入消息中间件集群，

参考：

- <a href="https://blog.csdn.net/m0_37834446/article/details/104373479" target="_blank">高并发思路和解决方案 </a>
- <a href="https://www.cnblogs.com/cn-sbo/p/10853469.html" target="_blank">高并发解决方案 </a>
- <a href="https://blog.csdn.net/sanyaoxu_2/article/details/78992113" target="_blank">高并发的解决方案 </a>