<p>
    <a href="#" onclick="refreshSoftWareUse('linux')">返 回</a>

</p>

---

# linux 系统说明

## Linux系统的文件结构

    /bin        二进制文件，系统常规命令
    /boot       系统启动分区，系统启动时读取的文件
    /dev        设备文件
    /etc        大多数配置文件  /etc/profile 系统环境变量配置文件
    /home       普通用户的家目录
    /lib        32位函数库
    /lib64      64位库
    /media      手动临时挂载点
    /mnt        手动临时挂载点
    /opt        第三方软件安装位置
    /proc       进程信息及硬件信息
    /root       临时设备的默认挂载点
    /sbin       系统管理命令
    /srv        数据
    /var        数据
    /sys        内核相关信息
    /tmp        临时文件
    /usr        用户相关设定

## centos7 防火墙操作

    systemctl status firewalld.service //查看防火墙状态 
    systemctl stop firewalld.service //关闭运行的防火墙 
    systemctl disable firewalld.service //永久禁止防火墙服务

## 修改Ip 修改网络配置文件，

文件地址：/etc/sysconfig/network-scripts/ifcfg-eth0

    主要修改以下配置：  
        TYPE=Ethernet //网络类型 
        BOOTPROTO=static //静态IP 
        DEVICE=ens00 //网卡名 
        IPADDR=192.168.1.100 //设置的IP 
        NETMASK=255.255.255.0 //子网掩码 
        GATEWAY=192.168.1.1 //网关 
        DNS1=192.168.1.1 //DNS 
        DNS2=8.8.8.8 //备用DNS 
        ONBOOT=yes //系统启动时启动此设置

    修改保存以后使用命令重启网卡：service network restart

## 配置映射

    修改文件： vi /etc/hosts 在文件最后添加映射地址，
    示例如下： 192.168.1.101 node1 192.168.1.102 node2 192.168.1.103 node3
    
    配置好以后保存退出，输入命令：ping node1 ，可见实际 ping 的是 192.168.1.101。

## 两台linux之间 文件传输 scp使用说明：

        1、把本机的文件传给目的服务器： 
            scp get66.pcap root@192.168.1.147: /super
            备注：把本机get66.pcap拷贝到147这台服务器的super目录下，需要提供147的密码

        2、在本机上执行scp，把远端的服务器文件拷贝到本机上：
            scp  root@192.168.1.147: /super/dns .pcap /
            备注：在本机上执行scp，把远端服务器的dns.pcap文件拷贝到本机的根目录下

        3、拷贝目录下的所有文件：
            scp  -r  /super/  root@192.168.1.145:/
            备注：把/super/目录下的所有文件，拷贝到145服务器根目录下

## 用户管理命令

    usaradd username 创建用户
    passwd username 设置密码
    userdel -r username 删除用户 -r 删除用户的同时删除用户家目录
    groupadd groupname 创建用户组
    su - username 切换用户
        -：代表连带用户的环境一起切换
        -c：仅执行一次命令，而不切换用户身份 sudo su - 切换用户 w 查看当前登录用户（详细）
    who 查看当前所有登录用户
    last 查看用户的登录记录，包括多次登录的记录
    lastlog 查看所有用户的最后登录时间
    touch /etc/nologin 禁止除了root以外的所有用户登录，该命令是在/etc目录下创建了一个nologin文件
    passwd -l username 禁止指定用户登录
    passwd -u username 解除被禁止登陆的用户
    passwd -d username 清除指定用户的密码，可以无密码登录该用户

## 添加用户

    添加新的用户账号使用useradd命令，
    语法如下： useradd 选项 用户名
    其中各选项含义如下：
        -c comment 指定一段注释性描述。
        -d 目录 指定用户主目录，如果此目录不存在，则同时使用-m选项，能创建主目录。
        -g 用户组 指定用户所属的用户组。
        -G 用户组,用户组 指定用户所属的附加组。
        -s Shell文件 指定用户的登录Shell。
        -u 用户号 指定用户的用户号，如果同时有-o选项，则能重复使用其他用户的标识号。
        -p这个命令是需求提供md5码的加密口令，普通数字是不行的。
    示例：
        （1）useradd test1 : 在默认路径创建一个test1用户。
        （2）useradd -d /usr/test1 -m test1 : 创建一个test1用户，其中-d和-m选项用来为登录名test1产生一个主目录/usr/test1。
        （3）useradd -s /bin/sh -g group -G adm,root test1 : 创建一个test1用户，该用户的登录Shell是/bin/sh，他属于group用户组，同时又属于adm和root用户组，其中group用户组是其主组。

## 修改用户

    修改已有用户的信息使用usermod命令，
    格式如下： usermod 选项 用户名
    常用的选项包括-c,-d,-m,-g,-G,-s,-u,-o等，这些选项的意义和useradd命令中的相同，能为用户指定新的资源值。
    示例：
        usermod -s /bin/ksh -d /home/z -g developer test1 此命令将用户test1的登录Shell修改为ksh，主目录改为/home/z，用户组改 为developer。
        usermod -g testgroup1 test1 此命令是改动用户test1所属的组为testgroup1这个组。

## 删除用户

    删除一个已有的用户账号使用userdel命令，
    格式如下： userdel 选项 用户名
    常用的选项是-r，他的作用是把用户的主目录一起删除。
    示例：
        userdel -r test1 此命令删除用户test1在系统文件（主要是/etc/passwd，/etc/shadow，/etc/group等）中的记录，同时删除用户的主目录。

## 用户口令管理

    用户管理的一项重要内容是用户口令的管理。
    用户账号刚创建时没有口令，是被系统锁定的，无法使用，必须为其指定口令后才能使用，即使是空口令。
    指定和修改用户口令的Shell命令是passwd。
    终极用户能为自己和其他用户指定口令，普通用户只能修改自己的口令。
    命令的格式为： passwd 选项 用户名
    可使用的选项：
        -l 锁定口令，即禁用账号。
        -u 口令解锁。
        -d 使账号无口令。
        -f 强迫用户下次登录时修改口令。 如果默认用户名，则修改当前用户的口令。
    示例：
        （1）假设当前用户是test，则下面的命令修改该用户自己的口令： passwd New password:* Re-enter new password:*
        （2）如果是终极用户（root），能用下列形式指定任意用户（test）的口令： passwd test New password:* Re-enter new password:*
        （3）为用户test指定空口令时，执行下列形式的命令： passwd -d test 此命令将用户test的口令删除，这样用户test下一次登录时，系统就不再询问口令。
        （4）锁定test用户时，执行下列形式的命令： passwd -l test 此命令将用户test锁定，使其不能登录。

## 查看本机路由表

    route

## 防火墙操作

    service iptables status //查看iptables服务的状态
    service iptables start //开启iptables服务
    service iptables stop //停止iptables服务
    service iptables restart //重启iptables服务
    chkconfig iptables off //关闭iptables服务的开机自启动
    chkconfig iptables on //开启iptables服务的开机自启动

## 查看所有的进程（不是动态的）

    ps -aux

## 查看所有的进程（是动态的）

    top 

## 查看当前运行的进程

	ps aux|grep a.out

## 查看当前运行的轻量级进程

	ps -aL|grep a.out

## 查看主线程和新线程的关系

	pstree -p 主线程id

## 查看所有正在运行的进程

    ps -ef 

## 进程管理

    kill pid //杀死该pid的进程
    kill -9 pid //强制杀死该进程

## 显示磁盘分区

    df：显示磁盘分区上可以使用的磁盘空间		
    参数： -a #查看全部文件系统，单位默认KB -h #使用-h选项以KB、MB、GB的单位来显示，可读性高~~~（最常用）

## 查看Linux系统有哪些用户

- <a href="https://www.php.cn/linux-434456.html" target="_blank">linux查看系统有哪些用户 </a>
- <a href="https://linux.cn/article-9888-1.html" target="_blank">列出 Linux 系统上所有用户的 3 种方法 </a>
- <a href="https://blog.csdn.net/feikillyou/article/details/109129870" target="_blank">查看linux用户密码 </a>


  