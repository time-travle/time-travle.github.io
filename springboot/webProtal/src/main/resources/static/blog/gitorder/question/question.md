#Git question  日常积累 
<p>
    <a href="#" onclick="refreshContent('gitorder')">返回目录</a>
</p>
<p>
<a href="#" onclick="refreshOrderContent('config')">Git（config）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order1')">Git（日常命令）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order2')">Git（初阶）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order3')">Git（中阶）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order4')">Git（高阶）</a>&emsp;&emsp;&emsp;
</p>

---
##Git使用中会出现的问题

1、ssh Permission denied：

	先看本地是否有ssh文件
	有则把公钥加到github
	如果以上操作问题还不能解决,并且执行 ssh -T git@github.com 出现如下提示 说明本地公钥没有问题
		ssh -T git@github.com
		Hi youcanping! You've successfully authenticated, but GitHub does not provide shell access.
	
	看本地的.git/config设置的仓库url地址和github使用的链接地址是否一致  不一致要修改
		> cat .git/config
		[core]
		repositoryformatversion = 0
		filemode = true
		bare = false
		logallrefupdates = true
		ignorecase = true
		precomposeunicode = true
		[remote "origin"]
		url = 应该配置的
		fetch = +refs/heads/*:refs/remotes/origin/*
		[branch "master"]
		remote = origin
		merge = refs/heads/master
		
2、使用命名或者工具和github网站交互时一直超时22 的解决方法

	测试 Github 连接是否畅通，可以使用如下的命令：

	ssh -T git@github.com  或者  ssh -V git@github.com
	此时，出现如下的问题：

	ssh: connect to host github.com port 22: Connection timed out
	在百度上搜“使用 ssh 连接 github 时,出现 timeout ”，找到了答案，ssh 阻塞了22端口。解决方案也很简单，修改 ssh 的配置文件。

	关于修改配置，存在两种解决方法，一种是 /etc/ssh/ssh_config 中修改全局配置，一种是在用户主目录下.ssh/中添加配置文件，这里选择的后者。

	cd ~/.ssh/
	touch config
	将如下内容添加到 config 中（当然，在全局文件中也是添加相同的内容）：
		Host github.com  
		User git  
		Hostname ssh.github.com 
		PreferredAuthentications publickey  
		IdentityFile ~/.ssh/id_rsa 
		Port 443
	
	https://www.cnblogs.com/sweetheartly/p/9439798.html
	
	
3、登录github网站时报超时解决方法

	选择相应快的DNS服务器添加到 C:\Windows\System32\drivers\etc\hosts 即可
	https://blog.csdn.net/qq_30460905/article/details/80205636
    
    # GitHub begin
    140.82.114.4 github.com 
    140.82.112.10 codeload.github.com
    140.82.113.3 github.com
    140.82.114.4 gist.github.com
    185.199.110.153 assets-cdn.github.com
    199.232.68.133 raw.githubusercontent.com
    199.232.68.133 gist.githubusercontent.com
    199.232.68.133 cloud.githubusercontent.com
    199.232.68.133 camo.githubusercontent.com
    199.232.69.194 github.global.ssl.fastly.net
    199.232.68.133 avatars0.githubusercontent.com
    199.232.68.133 avatars1.githubusercontent.com
    199.232.68.133 avatars2.githubusercontent.com
    199.232.68.133 avatars3.githubusercontent.com
    199.232.68.133 avatars4.githubusercontent.com
    199.232.68.133 avatars5.githubusercontent.com
    199.232.68.133 avatars6.githubusercontent.com
    199.232.68.133 avatars7.githubusercontent.com
    199.232.68.133 avatars8.githubusercontent.com
    140.82.114.6 api.github.com
    140.82.114.25 live.github.com
    185.199.110.153 http://documentcloud.github.com
    140.82.113.9 http://nodeload.github.com
    199.232.68.133 http://raw.github.com
    52.205.36.92 http://status.github.com
    140.82.112.18 http://training.github.com
    # GitHub End

4、解决Git中fatal: refusing to merge unrelated histories

    https://blog.csdn.net/u012145252/article/details/80628451
    
    问题
        1、本地初始化了git仓库，放了一些文件进去并进行了add操作和commit提交操作；
        $git add -A
        $git commit -m "start 2018-06-06"

        2、github创建了git仓库并建立了README文件；
			
        3、本地仓库添加了github上的git仓库作为远程仓库，起名origin；    
        
        4、问题来了，本地仓库在想做同步远程仓库到本地为之后本地仓库推送到远程仓库做准备时报错了，错误如下：

            fatal: refusing to merge unrelated historiesal
            （拒绝合并不相关的历史）
    
    解决
        出现这个问题的最主要原因还是在于本地仓库和远程仓库实际上是独立的两个仓库。
        假如我之前是直接clone的方式在本地建立起远程github仓库的克隆本地仓库就不会有这问题了。
        发现可以在pull命令后紧接着使用--allow-unrelated-history选项来解决问题（该选项可以合并两个独立启动仓库的历史）。
        
5、比较不长用的命令，部分仅仅在本地有效

    # git status显示unicode/乱码
    $ git config --global core.quotepath false
    # Checkout 出现文件路径超长时执行命令：
    $ git config --global core.longpaths true 

6、上传文件太大时（默认10m）：

 	进入远程git。找到project settings 打开Git hooks ，修改Maximum file size
 	
7、Another git process seems to be running in this repository, e.g.
		an editor opened by 'git commit'. Please make sure all processes
		are terminated then try again. If it still fails, a git process
		may have crashed in this repository earlier:
		remove the file manually to continue.
		
	解决方案：进入项目文件夹下的 .git文件中（显示隐藏文件夹或rm .git/index.lock）删除index.lock文件即可。
			使用手动也是可以的，使用命令行也是可以的 rm -f .git/index.lock
	
8、gitignore文件无法忽略某些文件

    在管理一个版本库时，有时候不想要管理某些文件，这个时候我就把这个问件写到.gitignore文件中，
    这样应该就可以将这个文件忽略，不再进行·版本管理了，但是经常出现的情况是：将这些文件名写到其中了，使用	git status
    查看发现这些文件并没有被忽略掉。查了资料发现，想要.gitignore起作用，必须要在这些文件不在暂存区中才可以，
    .gitignore文件只是忽略没有被staged(cached)文件，对于已经被staged文件，加入ignore文件时一定要先从staged移除，才可以忽略。 
    git rm –cached testFile

    这样，在.gitignore中写testFile，这个文件才可以被忽略掉

    本文来自 行者向阳 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/y491887095/article/details/73250583?utm_source=copy 

9、git commit 时：  
	fatal: could not open '.git/COMMIT_EDITMSG': Permission denied 1
	
	解决方案
	直接删除’.git/COMMIT_EDITMSG 文件解决
	---------------------
	
	本文来自 程序手艺人 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/z2066411585/article/details/80821311?utm_source=copy 
	
		 	        