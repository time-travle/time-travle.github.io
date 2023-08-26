<p>
    <a href="#" onclick="refreshSoftWareUse('linux')">返 回</a>

</p>

---

# Linux 软件安装

## 软件在线安装操作

    说明：安装插件命令 yum install httpd //使用yum安装apache 
        yum update httpd //更新apache 
        yum remove httpd //卸载/删除apache
    

    说明：插件安装命令 rpm -ivh httpd-2.2.3-22.0.1.el5.i386.rpm //使用rpm文件安装apache 
                    rpm -uvh httpd-2.2.3-22.0.1.el5.i386.rpm //使用rpm更新apache 
                    rpm -ev httpd //卸载/删除apache
    
                    rpm -e --nodeps 强制删除模式

## linux安装jdk8

<a href="https://blog.csdn.net/pdsu161530247/article/details/81582980" target="_blank">linux安装jdk8 </a>

### 安装包获取

从网上获得linux的 压缩包，上传的Linux 机器

### 解压压缩包

    # tar -zxvf jdk-8u181-linux-x64.tar.gz

### 设置环境变量：

    编辑文件 
    # vim /etc/profile
    
    追加内容
    export  JAVA_HOME=/usr/local/jdk1.8.0_181  
    export  JRE_HOME=${JAVA_HOME}/jre
    export  CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$CLASSPATH
    export  JAVA_PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin
    export  PATH=$PATH:${JAVA_PATH}

### 刷新环境变量

    # source /etc/profile    

---

---

## Redis 安装

- <a href="https://www.cnblogs.com/hunanzp/p/12304622.html" target="_blank">Linux安装部署Redis(超级详细) </a>
- <a href="https://www.jianshu.com/p/bc84b2b71c1c" target="_blank">Linux下redis安装和部署 </a>
- <a href="https://www.cnblogs.com/xsge/p/13841875.html" target="_blank">Linux系统发行版ContOS7演示安装Redis </a>
- <a href="https://blog.csdn.net/yu102655/article/details/116791457" target="_blank">linux系统下安装和配置redis（2021版） </a>

### 步骤：

#### 1、下载安装包上传到linux服务器

#### 2、解压

        tar -zvxf 压缩包  是否需要移动包位置 根据自己需要

#### 3、编译

        进去 解压后的包 执行 make 命令

#### 4、安装

        make PREFIX=/usr/local/redis install
        
        这里多了一个关键字 PREFIX= 这个关键字的作用是编译的时候用于指定程序存放的路径。
        比如我们现在就是指定了redis必须存放在/usr/local/redis目录。
        假设不添加该关键字Linux会将可执行文件存放在/usr/local/bin目录，库文件会存放在/usr/local/lib目录。
        配置文件会存放在/usr/local/etc目录。其他的资源文件会存放在usr/local/share目录。
        这里指定号目录也方便后续的卸载，后续直接rm -rf /usr/local/redis 即可删除redis。
        
        站在最外层 将redis 文件夹以及下面文件夹文件 权限 755  防止后面执行时没权限
        chmod 755 file

#### 5、启动redis

- <a href="https://www.cnblogs.com/xzjf/p/15021036.html" target="_blank">linux 安装redis步骤 </a>
- <a href="https://www.jb51.net/article/179180.htm" target="_blank">Linux安装Redis、后台运行、系统自启动的设置方法 </a>

        在目录/usr/local/redis 输入下面命令启动redis
        ./bin/redis-server &   // 后台进程方式
        ./bin/redis-server ./redis.conf  // 显示启动方式  如在配置文件设置了daemonize属性为yes则跟后台进程方式启动其实一样

### 开启redis

    /usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf    
    显示启动方式(如在配置文件设置了daemonize属性为yes则跟后台进程方式启动其实一样)
    ./bin/redis-server & 后台进程方式,

-<a href="https://www.cnblogs.com/yanghj010/p/11148265.html" target="_blank">redis的三种启动方式 </a> 第三种比较实用

### 查看Redis是否正在运行

    采取查看进程方式
    ps -aux | grep redis
    采取端口监听查看方式   
    netstat -lanp | grep 6379

### 简单修改后台启动

    [root@localhost redis-6.0.1]# vim redis.conf  //将daemonize no 改成daemonize yes
    后台运行成功，我们查看一下，并终止程序
    [root@localhost redis-6.0.1]# ps -aux|grep redis| grep -v grep
    root     29836  0.0  0.0 162416  7912 ?        Ssl  16:56   0:00 /usr/redis-6.0.1/src/redis-server 127.0.0.1:6379
    [root@localhost redis-6.0.1]# kill -9 29836

### 客户端连接 redis

    连接 redis ：redis-cli
    redis-cli是连接本地redis服务的一个命令，通过该命令后可以既然怒redis的脚本控制台。如下图
    输入exit可以退出redis脚本控制台
    $ redis-cli -h host -p port -a password     使用密码鉴权连接

    /usr/local/redis/bin/redis-cli 

### 关闭运行中的Redis服务

    输入redis-cli 进入控制台后输入命令shutdown即可关闭运行中的Redis服务了。
    强行终止redis进程可能会导致redis持久化数据丢失。正确停止Redis的方式应该是向Redis发送SHUTDOWN命令，命令为：    
    cd /usr/local/redis
    ./bin/redis-cli shutdown
    强行终止redis
    pkill redis-server    

# Redis的bind的误区

<a href="https://blog.csdn.net/cw_hello1/article/details/83444013" target="_blank">https://blog.csdn.net/cw_hello1/article/details/83444013 </a>

    配置远程访问：
    修改bind 0.0.0.0
    为了安全 起见 设置密码 requirepass pwd
    显示启动方式启动

### 用后台systemctl启动redis --->未测试

    首先把redis加入service服务
        vim /lib/systemd/system/redis.service
        写入
        [Unit]
        Description=redis
        After=network.target

        [Service]
        Type=forking
        PIDFile=/var/run/redis_6379.pid
        ExecStart=/usr/local/redis-6.0.1/src/redis-server /usr/local/redis-6.0.1/etc/redis.conf
        ExecReload=/bin/kill -s HUP $MAINPID
        ExecStop=/bin/kill -s QUIT $MAINPID
        PrivateTmp=true

        [Install]
        WantedBy=multi-user.target   
    测试命令：
        [root@localhost redis-6.0.1]# systemctl enable redis.service                   # 加入开机启动
        [root@localhost redis-6.0.1]# systemctl is-enabled redis.service               # 查看开机是否启动成功    enabled
        [root@localhost redis-6.0.1]# systemctl start redis                            #开启redis服务
        [root@localhost redis-6.0.1]# systemctl status redis //查看redis运行状态    

### 让redis开机自启

    vim /etc/rc.local
    //添加 根据安装位置确定
    /usr/local/redis/bin/redis-server /usr/local/redis/etc/redis-conf

### 其他conf参数：

    daemonize   	yes、no	        yes表示启用守护进程，默认是no即不以守护进程方式运行。其中Windows系统下不支持启用守护进程方式运行
    
    port	 	                    指定 Redis 监听端口，默认端口为 6379
    
    bind	 	                    绑定的主机地址,如果需要设置远程访问则直接将这个属性备注下或者改为bind * 即可, 这个属性和下面的protected-mode控制了是否可以远程访问 。
    
    protected-mode	yes 、no	    保护模式，该模式控制外部网是否可以连接redis服务，默认是yes,所以默认我们外网是无法访问的，如需外网连接rendis服务则需要将此属性改为no。
    
    timeout	300	                    当客户端闲置多长时间后关闭连接，如果指定为 0，表示关闭该功能
    
    loglevel	    debug、verbose、notice、warning	    日志级别，默认为 notice
    
    databases	    16	            设置数据库的数量，默认的数据库是0。整个通过客户端工具可以看得到
    
    rdbcompression	yes、no	        指定存储至本地数据库时是否压缩数据，默认为 yes，Redis 采用 LZF 压缩，如果为了节省 CPU 时间，可以关闭该选项，但会导致数据库文件变的巨大。
    
    dbfilename	    dump.rdb    	指定本地数据库文件名，默认值为 dump.rdb
    
    dir	                           	指定本地数据库存放目录
    
    requirepass	 	                设置 Redis 连接密码，如果配置了连接密码，客户端在连接 Redis 时需要通过 AUTH <password> 命令提供密码，  默认关闭
    
    maxclients	    0           	设置同一时间最大客户端连接数，默认无限制，Redis 可以同时打开的客户端连接数为 Redis 进程可以打开的最大文件描述符数，如果设置 maxclients 0，表示不作限制。当客户端连接数到达限制时，Redis 会关闭新的连接并向客户端返回 max number of clients reached 错误信息。
    
    maxmemory	    XXX <bytes>	    指定 Redis 最大内存限制，Redis 在启动时会把数据加载到内存中，达到最大内存后，Redis 会先尝试清除已到期或即将到期的 Key，当此方法处理 后，仍然到达最大内存设置，将无法再进行写入操作， 但仍然可以进行读取操作。Redis 新的 vm 机制，会把 Key 存放内存，Value 会存放在 swap 区。       配置项值范围列里XXX为数值。    

---

## Tomcat服务器配置

### (一) Tomcat部署

将tomcat6服务器上传至指定路径。

### (二) 配置Tomcat环境变量

    1、 配置Tomcat6所在用户主目录的.bash_profile环境变量文件
    执行vi .bash_profile对环境变量文件进行编辑，加入Tomcat6的环境变量：
    （1）JAVA_HOME
    （2）TOMCAT_HOME
    （3）LD_LIBRARY_PATH
    2、 热生效.bash_profile环境变量文件
    执行source .bash_profile将新增的环境变量生效。
    3、 让root用户对tomcat6文件夹及其子文件有最高权限
    执行chmod -R 764 tomcat6

### (三) 配置Tomcat相关参数

    1、 配置server.xml文件
    执行vi server.xml，在Connector 8080端口添加URIEncoding=”UTF-8”属性，避免网页页面字符集乱码。
    2、 配置catalina.sh文件
    在if [ $have_tty -eq 1 ]; then这行下，添加内存分配和内存溢出时输出HeapDump文件，语句如下：
    JAVA_OPTS=”-server -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/local/heapdump/heapdump.hprof”
    需要注意的地方：
    （1）-Xms : JVM初始分配的堆内存；-Xmx : JVM最大允许分配的堆内存，按需分配。
    （2）-XX:PermSize : JVM初始分配的非堆内存；-XX:MaxPermSize : JVM最大允许分配的非堆内存，按需分配。一般非堆内存分配是堆内存分配的1/2。
    （3）-XX:+HeapDumpOnOutOfMemoryError : 打开内存溢出时输出错误信息日志；-XX:HeapDumpPath : 内存溢出错误信息日志输出的路径和名称，路径要配置绝对路径并且必须是已存在和至少权限是755的路径，文件名建议用.hprof后缀结尾，后期通过内存分析工具可以直接打开。

### (四) 启动Tomcat

    1、 执行sh startup.sh将tomcat服务器启动。
    2、 通过ps –ef|grep tomcat命令可以看到tomcat服务器进程的详细信息。
    3、 若内存溢出后，可去配置好的-XX:HeapDumpPath路径下载heapdump.hprof文件，再通过ha456.jar（HeapAnalyzer）工具进行内存分析。




