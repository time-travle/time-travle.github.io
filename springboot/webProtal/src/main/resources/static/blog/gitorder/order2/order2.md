#Git 初阶 Order 日常积累 
<p>
    <a href="#" onclick="refreshContent('gitorder')">返回目录</a>
</p>
<P>
<a href="#" onclick="refreshOrderContent('config')">Git（config）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('question')">Git（question）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order1')">Git（日常命令）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order3')">Git（中阶）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order4')">Git（高阶）</a>&emsp;&emsp;&emsp;
</P>

---

查看代码行级修改

	$ git blame fileName

标签	

	# 列出所有tag
	$ git tag

	# 新建一个tag在当前commit
	$ git tag [tag]

    # 创建一个带注解的标签 
    # -a 选项意为"创建一个带注解的标签"。 不用 -a 选项也可以执行的，但它不会记录这标签是啥时候打的，谁打的，也不会让你添加个标签的注解
    $ git tag -a v1.0 
    
	# 新建一个tag在指定commit
	$ git tag [tag] [commit]

	# 删除本地tag
	$ git tag -d [tag]

	# 删除远程tag
	$ git push origin :refs/tags/[tagName]

	# 查看tag信息
	$ git show [tag]

	# 提交指定tag
	$ git push [remote] [tag]

	# 提交所有tag
	$ git push [remote] --tags

	# 拉取远程分支同时在 本地新建分支
	$ git checkout -b dev origin/dev
	# 拉取远程分支同时和本地分支合并
	$ git checkout dev origin/dev
	
	# 新建一个分支，指向某个tag
	$ git checkout -b [branch] [tag]
	
	
查看信息

	# 显示有变更的文件
	$ git status

	# 显示当前分支的版本历史
	$ git log
	$ git log --oneline
	
	# 如果嫌输出信息太多，看得眼花缭乱的，可以试试加上--pretty=oneline参数
	$ git log --pretty=oneline
    
    # 只显示提交信息的第一行
	$ git log --pretty=short
	
    # 显示过去5次提交
	$ git log -5 --pretty --oneline
	
	# 显示commit历史，以及每次commit发生变更的文件
	$ git log --stat

	# 搜索提交历史，根据关键词
	$ git log -S [keyword]

	# 显示某个commit之后的所有变动，每个commit占据一行
	$ git log [tag] HEAD --pretty=format:%s

	# 显示某个commit之后的所有变动，其"提交说明"必须符合搜索条件
	$ git log [tag] HEAD --grep feature

	# 显示某个文件的版本历史，包括文件改名
	$ git log --follow [file]
	$ git whatchanged [file]

	# 显示指定文件相关的每一次diff  显示文件的改动
	$ git log -p [file]

    # 只显示指定目录、文件的日志
    $ git log README.md

	# 分支的合并后显示log
	$ git log --oneline --graph --decorate

	# 看看分支历史
	$ git log --graph --pretty=oneline --abbrev-commit
	
	# 以图表形式查看分支
	$ git log --graph
	
	# 显示所有提交过的用户，按提交次数排序
	$ git shortlog -sn

	# 显示指定文件是什么人在什么时间修改过
	$ git blame [file]

	# 显示某次提交的元数据和内容变化
	$ git show [commit]

	# 显示某次提交发生变化的文件
	$ git show --name-only [commit]

	# 显示某次提交时，某个文件的内容
	$ git show [commit]:[filename]

	# 显示当前分支的最近几次提交
	$ git reflog

	# 从本地master拉取代码更新当前分支：branch 一般为master
	$ git rebase [branch]
	
	# 查看内容
	$ git cat index.html
	
	# Git bash中运行git log之后怎样终止这个命令
	查看git log中，一直出现冒号： ，   如果想要退出，那么就需要使用命令字符 q
	
对比

	# 显示暂存区和工作区的代码差异
	$ git diff

	# 显示暂存区和上一个commit的差异
	$ git diff --cached [file]

	# 显示工作区与当前分支最新commit之间的差异
	$ git diff HEAD

	# 显示两次提交之间的差异
	$ git diff [first-branch]...[second-branch]

	# 显示今天你写了多少行代码
	$ git diff --shortstat "@{0 day ago}"
	# 查看工作区和版本库里面最新版本的区别
	git diff HEAD -- readme.txt



远程同步
	
	# git pull 和$ git fetch 的区别
		git pull = git fetch + git merge

		git pull会将本地库更新至远程库的最新状态 由于本地库进行了更新，HEAD也会相应的指向最新的commit id
		git fetch的时候只是将remote的origin进行update  但是并没有在local的branch进行merge
		https://blog.csdn.net/u011534057/article/details/77505770
		git fetch：这将更新git remote 中所有的远程仓库所包含分支的最新commit-id, 将其记录到.git/FETCH_HEAD文件中
		git pull : 首先，基于本地的FETCH_HEAD记录，比对本地的FETCH_HEAD记录与远程仓库的版本号，
					然后git fetch 获得当前指向的远程分支的后续版本的数据，然后再利用git merge将其与本地的当前分支合并。
					所以可以认为git pull是git fetch和git merge两个步骤的结合。
		
		在实际使用中，git fetch更安全一些，因为在merge前，我们可以查看更新情况，然后再决定是否合并。
		
		1. git fetch：相当于是从远程获取最新版本到本地，不会自动merge

		git fetch orgin master //将远程仓库的master分支下载到本地当前branch中
		git log -p master  ..origin/master //比较本地的master分支和origin/master分支的差别
		git merge origin/master //进行合并
		以上命令的含义： 
		首先从远程的origin的master主分支下载最新的版本到origin/master分支上；
			然后比较本地的master分支和origin/master分支的差别；最后进行合并。上述过程其实可以用以下更清晰的方式来进行：

		git fetch origin master:tmp //从远程仓库master分支获取最新，在本地建立tmp分支
		git diff tmp //將當前分支和tmp進行對比
		git merge tmp //合并tmp分支到当前分支
		
		从远程获取最新的版本到本地的tmp分支上之后再进行比较合并 
		
		2. git pull：相当于是从远程获取最新版本并merge到本地

		git pull origin master
		git pull 相当于从远程获取最新版本并merge到本地
		
	
	# 下载远程仓库的所有变动
	$ git fetch [remote]

	# 添加远程仓库 
	$ git remote add origin git@github.com:github-book/git-tutorial.git
	
	# 显示所有远程仓库
	$ git remote -v

	# 显示某个远程仓库的信息
	$ git remote show [remote]

    # 删除远程仓库可以使用命令：
    $ git remote rm [别名]

	# 增加一个新的远程仓库，并命名
	$ git remote add [shortname] [url]
	
	# -u参数 upstream  一般第一次提交需要这个 
	$ git push origin master -u   //获取最新代码

	# 取回远程仓库的变化，并与本地分支合并
	$ git pull [remote] [branch]

	# 上传本地指定分支到远程仓库
	$ git push [remote] [branch]

	# 强行推送当前分支到远程仓库，即使有冲突
	$ git push [remote] --force

	# 推送所有分支到远程仓库
	$ git push [remote] --all	
    
撤销	

	# 恢复暂存区的指定文件到工作区
	$ git checkout [file]

	# 恢复某个commit的指定文件到暂存区和工作区
	$ git checkout [commit] [file]

	# 恢复暂存区的所有文件到工作区
	$ git checkout .
	
	# git checkout -- file命令中的--很重要，没有--，就变成了“切换到另一个分支”的命令
	$ git checkout -- readme.txt
	
	## git reset命令既可以回退版本，也可以把暂存区的修改回退到工作区。
	
	# 可以把暂存区的修改撤销掉（unstage），重新放回工作区
	$ git reset HEAD <file>
	
	# 重置暂存区的指定文件，与上一次commit保持一致，但工作区不变
	$ git reset [file]

	# 重置暂存区与工作区，与上一次commit保持一致
	$ git reset --hard

	# 重置当前分支的指针为指定commit，同时重置暂存区，但工作区不变
	$ git reset [commit]

	# 重置当前分支的HEAD为指定commit，同时重置暂存区和工作区，与指定commit一致
	$ git reset --hard [commit]

	# 重置当前HEAD为指定commit，但保持暂存区和工作区不变
	$ git reset --keep [commit]
	
	#版本回退 上一个版本就是HEAD^，上上一个版本就是HEAD^^，当然往上100个版本写100个^比较容易数不过来，所以写成HEAD~100。
	$ git reset --hard HEAD^	回退一个版本
	
	# 新建一个commit，用来撤销指定commit
	# 后者的所有变化都将被前者抵消，并且应用到当前分支
	$ git revert [commit]

	# 暂时将未提交的变化移除，稍后再移入
	$ git stash
	
	#丢掉保存的内容
	$ git stash drop	
	
	# 在次切换分之后需要应用一下保留的内容
	$ git stash apply
	
	# 使用并丢掉
	$ git stash pop
		
<a href="https://www.cnblogs.com/cspku/articles/Git_cmds.html" target="_blank">https://www.cnblogs.com/cspku/articles/Git_cmds.html </a>
    	
