
#Git Config Order 日常积累 
<p>
    <a href="#" onclick="refreshContent('gitorder')">返回目录</a>
</p>
<a href="#" onclick="refreshOrderContent('question')">Git（question）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order1')">Git（日常命令）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order2')">Git（初阶）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order3')">Git（中阶）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order4')">Git（高阶）</a>&emsp;&emsp;&emsp;

---
<pre style="text-align: initial;font-family: cursive;">
    GIT常用的配置命令

    Git 在window 的安装
    1、先获得安装包，途径自己选
    2、获得后开始安装，安装时可以一路默认，当然安装的位置你可以修改一下，其他的都是可以不用关注的直接默认就是可以的。
    3、安装完成后，在CMD控制台 输入  git 就可以查看是是不是安装OK了
    4、确定安装完成 ，我们还不可以直接使用还要进行一下配置
    找到Git Bash 打开它 就会看到一个属于git的控制台 在里面输入 下面的命令 配置 这个是区分每个操作人员的

    设置提交代码时的用户信息
    $ git config --global user.name "Your Name"
    $ git config --global user.email "email@example.com"

    使用
    $ git config -l 来查看所有的配置信息  当然了除了 我们刚才配的两个 其他的都是什么意思 有兴趣的可以自己搜索学习一下

    core.symlinks=false
    core.autocrlf=true
    core.fscache=true
    color.diff=auto
    color.status=auto
    color.branch=auto
    color.interactive=true
    help.format=html
    rebase.autosquash=true
    http.sslcainfo=f:/Program Files/Git/mingw64/ssl/certs/ca-bundle.crt
    http.sslbackend=openssl
    diff.astextplain.textconv=astextplain
    filter.lfs.clean=git-lfs clean -- %f
    filter.lfs.smudge=git-lfs smudge -- %f
    filter.lfs.process=git-lfs filter-process
    filter.lfs.required=true
    credential.helper=manager
    user.name=joven10230112
    user.email=18763137197@163.com
    credential.helper=manager

    5、创建心意的本地版本库
    找到你想建立版本库的文件夹 在Git控制台输入
    $ git init
    命令把这个目录变成Git可以管理的仓库：
    添加是也可以同时添加多个的
    $ git add file1.txt
    $ git add file2.txt file3.txt

    注：目录名字不要含有中文

    工作区和暂存区 简要说明
    Workspace：工作区
    Index / Stage：暂存区
    Repository：仓库区（或本地仓库）
    Remote：远程仓库

    工作区就是建立本地版本库的那个文件夹中除了.git 之外的其他的地方 我们都叫他们为工作区
    暂存区就是 .git里面的stage（或者叫index）就是暂存区，另外还有Git为我们自动创建的第一个分支master，以及指向master的一个指针叫HEAD

    第一步是用git add把文件添加进去，实际上就是把文件修改添加到暂存区；
    第二步是用git commit提交更改，实际上就是把暂存区的所有内容提交到当前分支。


    新建代码库 类似第5步
    # 在当前目录新建一个Git代码库
    $ git init

    # 新建一个目录，将其初始化为Git代码库
    $ git init [project-name]

    #  克隆远程仓库 下载一个项目和它的整个代码历史
    $ git clone [url]

    # 关联远程仓库 origin 这个是默认叫法
    $ git remote add origin [url]


    配置
    # 显示当前的Git配置
    $ git config --list

    # 编辑Git配置文件
    $ git config -e [--global]

    # 设置提交代码时的用户信息
    $ git config [--global] user.name "[name]"
    $ git config [--global] user.email "[email address]"
    
    # 提高命令输出的可读性
    $ git config --global color.ui auto
    
    将自己的公钥配置得到网站中
    
        # 使用以下命令生成SSH Key：
        $ ssh-keygen -t rsa -C "youremail@example.com"
    	
        # 为了验证是否成功，输入以下命令：
        $ ssh -T git@github.com
        
        
    连接测试命令：
    ssh-add -l 查看加入的密钥列表
    ssh -T git@git.oschina.net
    ssh -T git@github.com
    ssh -v git@github.com 查看调试信息
    
</pre>

