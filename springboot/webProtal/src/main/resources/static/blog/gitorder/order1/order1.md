
#Git 日常 Order 积累 
<p>
    <a href="#" onclick="refreshContent('gitorder')">返回目录</a>
</p>
<a href="#" onclick="refreshOrderContent('config')">Git（config）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('question')">Git（question）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order2')">Git（初阶）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order3')">Git（中阶）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order4')">Git（高阶）</a>&emsp;&emsp;&emsp;

---

####增加/删除文件
    # 添加指定文件到暂存区
    $ git add [file1] [file2] ...
    
    #强制添加指定文件到暂存区
    $ git add -f ...
    
    # 添加指定目录到暂存区，包括子目录
    $ git add [dir]
    
    # 添加当前目录的所有文件到暂存区
    $ git add .
    
    # 添加每个变化前，都会要求确认
    # 对于同一个文件的多处变化，可以实现分次提交
    $ git add -p
    
    # 删除文件
    $ rm test.txt
    
    # 删除文件夹
    $ rm -r test
    # 加上 -n 这个参数，执行命令时，是不会删除任何文件，而是展示此命令要删除的文件列表预览
    $ git rm -r -n
    
    # 删除工作区文件，并且将这次删除放入暂存区
    $ git rm [file1] [file2] ...
    
    # 停止追踪指定文件，但该文件会保留在工作区
    $ git rm --cached [file]
    
    # 改名文件，并且将这个改名放入暂存区
    $ git mv [file-original] [file-renamed]

####代码提交
    # 提交暂存区到仓库区
    $ git commit -m [message]
    
    # 提交暂存区的指定文件到仓库区
    $ git commit [file1] [file2] ... -m [message]
    
    # 提交工作区自上次commit之后的变化，直接到仓库区
    $ git commit -a
    
    # 提交时显示所有diff信息
    $ git commit -v
    
    # 使用一次新的commit，替代上一次提交
    # 如果代码没有任何新变化，则用来改写上一次commit的提交信息
    $ git commit --amend -m [message]
    
    # 重做上一次commit，并包括指定文件的新变化
    $ git commit --amend [file1] [file2] ...

####代码推送
    #第一次推送master分支时，加上了-u参数，Git不但会把本地的master分支内容推送的远程新的master分支，
    #还会把本地的master分支和远程的master分支关联起来，在以后的推送或者拉取时就可以简化命令。
    $ git push -u origin master
    # 不是第一次的
    $ git push origin master
    
####分支
    # 列出所有本地分支 当前分支前面会标一个*号。
    $ git branch
    
    # 列出所有远程分支
    $ git branch -r
    
    # 列出所有本地分支和远程分支
    $ git branch -a
    
    # 新建一个分支，但依然停留在当前分支
    $ git branch [branch-name]
    
    # 新建一个分支，并切换到该分支
    $ git checkout -b [branch]
    
    # 新建一个分支，指向指定commit
    $ git branch [branch] [commit]
    
    # 新建一个分支，与指定的远程分支建立追踪关系
    $ git branch --track [branch] [remote-branch]
    
    # 切换到指定分支，并更新工作区
    $ git checkout [branch-name]
    
    #  在本地建立分支 同时拉取远端分支与之对应
    $ git checkout -b 本地分支名x origin/远程分支名x
    
    # 切换到上一个分支
    $ git checkout -
    
    # 建立追踪关系，在现有分支与指定的远程分支之间
    $ git branch --set-upstream [branch] [remote-branch]
    
    # 合并指定分支到当前分支
    $ git merge [branch]
    
    # 如果要强制禁用Fast forward模式，Git就会在merge时生成一个新的commit，
    # 这样，从分支历史上就可以看出分支信息。
    # 合并分支时，加上--no-ff参数就可以用普通模式合并，合并后的历史有分支，
    # 能看出来曾经做过合并，而fast forward合并就看不出来曾经做过合并。
    
    # 准备合并dev分支，请注意--no-ff参数，表示禁用Fast forward：
    $ git merge --no-ff -m "merge with no-ff" dev
    
    # 选择一个commit，合并进当前分支
    $ git cherry-pick [commit]
    
    # 分支合并图
    git log --graph
    
    # 删除分支
    $ git branch -d [branch-name]
    
    # 删除远程分支
    $ git push origin --delete [branch-name]
    $ git branch -dr [remote/branch]
    
    # git pull 设置默认分支
    git config branch.master.remote origin
    git config branch.master.merge refs/heads/master
    
    demo:
    例如要把push和pull的默认分支设置为master
    git branch --set-upstream-to=origin/master master    