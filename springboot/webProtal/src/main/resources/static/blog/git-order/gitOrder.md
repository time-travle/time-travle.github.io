<pre style="text-align: initial;font-family: cursive;">
    GIT常用的命令

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


    增加/删除文件
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

    代码提交
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



    代码推送
    #第一次推送master分支时，加上了-u参数，Git不但会把本地的master分支内容推送的远程新的master分支，
    #还会把本地的master分支和远程的master分支关联起来，在以后的推送或者拉取时就可以简化命令。
    $ git push -u origin master
    # 不是第一次的
    $ git push origin master


    分支
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


    标签
    # 列出所有tag
    $ git tag

    # 新建一个tag在当前commit
    $ git tag [tag]

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

    # 显示指定文件相关的每一次diff
    $ git log -p [file]

    # 分支的合并后显示log
    $ git log --oneline --graph --decorate

    # 看看分支历史
    $ git log --graph --pretty=oneline --abbrev-commit

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



    Git 清理无效的远程追踪分支
    #在远程版本库创建了一个分支后，在本地可以使用  可以在本地创建远程追踪分支
    #但是，如果在远程版本库上删除了某一分支，该命令并不会删除本地的远程追踪分支
    $ git remote update

    #假如你的远程版本库名是 origin,则使用如下命令先查看哪些分支需要清理：
    $ git remote prune origin --dry-run

    #该命令可以删除本地版本库上那些失效的远程追踪分支
    $ git remote prune
    #执行命令，就完成了无效的远程追踪分支的清理工作
    $ git remote prune origin



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
        然后比较本地的master分支和origin/master分支的差别；
        最后进行合并。上述过程其实可以用以下更清晰的方式来进行：

    git fetch origin master:tmp //从远程仓库master分支获取最新，在本地建立tmp分支
    git diff tmp //將當前分支和tmp進行對比
    git merge tmp //合并tmp分支到当前分支

    从远程获取最新的版本到本地的tmp分支上之后再进行比较合并

    2. git pull：相当于是从远程获取最新版本并merge到本地

    git pull origin master
    git pull 相当于从远程获取最新版本并merge到本地


    # 下载远程仓库的所有变动
    $ git fetch [remote]

    # 显示所有远程仓库
    $ git remote -v

    # 显示某个远程仓库的信息
    $ git remote show [remote]

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

    #可以把暂存区的修改撤销掉（unstage），重新放回工作区
    $ git reset HEAD

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

    https://www.cnblogs.com/cspku/articles/Git_cmds.html


    忽略文件文件夹(不纳入git管理)

    使用gitignore文件来解决这个问题，步骤是：
    1） touch .gitignore #创建gitignore隐藏文件
    2） vim .gitignore #编辑文件，加入指定文件  ---按"Esc"键后。按":w"保存。按":wq"或"ZZ"(大写的Z)保存并退出.gitignore文件的编辑。





    【方式一】拉取到代码的人都使用

    在仓库目录下新建一个名为.gitignore的文件（因为是点开头，没有文件名，没办法直接在windows目录下直接创建，
    必须通过右键Git Bash，按照linux的方式来新建.gitignore文件）。

    .gitignore文件对其所在的目录及所在目录的全部子目录均有效。通过将.gitignore文件添加到仓库，
    其他开发者更新该文件到本地仓库，以共享同一套忽略规则。

    【方式二】仅仅本地是OK的

    通过配置.git/info/exclude文件来忽略文件。这种方式对仓库全局有效，只能对自己本地仓库有作用，
    其他人没办法通过这种方式来共享忽略规则，除非他人也修改其本地仓库的该文件。

    【方式三】仅仅本地是OK的

    通过.git/config配置文件的core. Excludesfile选项，指定一个忽略规则文件（完整路径）
        忽略规则在文件e:/gitignore.txt中（当然该文件名可以任意取）。

    该方式的作用域是也全局的

    【例子】
    # 忽略*.o和*.a文件
    *.[oa]
    # 忽略*.b和*.B文件，my.b除外
    *.[bB]
    !my.b
    # 忽略dbg文件和dbg目录
    dbg
    # 只忽略dbg目录，不忽略dbg文件
    dbg/
    # 只忽略dbg文件，不忽略dbg目录
    dbg
    !dbg/
    # 只忽略当前目录下的dbg文件和目录，子目录的dbg不在忽略范围内
    /dbg

    # 此为注释 – 将被 Git 忽略
    *.a       # 忽略所有 .a 结尾的文件
    !lib.a    # 但 lib.a 除外
    /TODO     # 仅仅忽略项目根目录下的 TODO 文件，不包括 subdir/TODO
    build/    # 忽略 build/ 目录下的所有文件
    doc/*.txt # 会忽略 doc/notes.txt 但不包括 doc/server/arch.txt

    开放模式 过滤文件夹设置：
    /mtk/ 表示过滤这个文件夹
    过滤文件设置
    指定过滤某种类型的文件：
    *.zip
    *.rar
    *.via
    *.tmp
    *.err
    指定过滤某个文件：
    /mtk/do.c
    /mtk/if.h

    1、.gitignore文件的创建：首先要强调一点，这个文件的完整文件名就是“.gitignore”，注意最前面有个“.”。
        这样没有扩展名的文件在Windows下不太好创建，这里给出win7的创建方法：创建一个文件，文件名为：“.gitignore.”，
        注意前后都有一个点。保存之后系统会自动重命名为“.gitignore”。
    2、过滤规则：
    build/ ：过滤整个文件夹；
    *.class：过滤所有.class后缀的文件；
    local.properties:过滤具体文件
    被过滤掉的文件就不会出现在你的GitHub库中了，当然本地库中还有，只是push的时候不会上传。
    需要注意的是.gitignore还可以指定哪些文件添加到版本管理中，添加规则：
    ！build/ ：添加整个文件夹；
    ！*.class：添加所有.class后缀的文件；
    ！local.properties:添加具体文件
    唯一的区别就是前面加了个感叹号

    3.注意：
    如果你不慎在创建.gitignore文件之前就push了项目，那么即使你在.gitignore文件中写入新的过滤规则，这些规则也不会起作用，
    Git仍然会对所有文件进行版本管理。简单来说出现这种问题的原因就是Git已经开始管理这些文件了，所以你无法再通过过滤规则过滤它们。
    所以大家一定要养成在项目开始就创建.gitignore文件的习惯，否则一单push，处理起来会非常麻烦。

    4、对已提交不必要文件的处理方法
        有时候当我们明白这个gitignore之后才发现我们已经提交不必要的文件了，而自己又是一个较完美主义者，
    不愿意让那些文件存在我们库里，该怎么做呢？有什么办法吗？办法肯定是有的。
    　　 那么我们现在预设的问题模型是：项目文件里有一个node_modules文件，该文件是存储自动生成的模型的文件，
    然后我已经把这个文件提交到github库里了，
        现在我明白了这个道里过后想删掉它并且以后都不再提交它。处理的方法：首先，我们再终端进入项目的根文件下面，创建.gitignore文件，
        并且添加需要忽略提交的文件，如上面我的一个.gitignore文件，然后输入如下命令
    git rm -r --cached node_modules（要删除的文件名）
    　　然后再
    git push
    　　最后我们去我们的github的库里去就会发现刚刚删除的东西已经成功删除啦~

    5、在使用.gitignore文件后如何删除远程仓库中以前上传的此类文件而保留本地文件
    比如我们在使用git和github的时候，之前没有写.gitignore文件，就上传了一些没有必要的文件，在添加了.gitignore文件后，
    就想删除远程仓库中的文件却想保存本地的文件。这时候不可以直接使用git rm directory，这样会删除本地仓库的文件。
    可以使用git rm -r –cached directory来删除缓冲，然后进行commit和push，这样会发现远程仓库中的不必要文件就被删除了，
    以后可以直接使用git add -A来添加修改的内容，上传的文件就会受到.gitignore文件的内容约束。


    ssh Permission denied：
    先看本地是否有ssh文件
    有则把公钥加到github
    如果以上操作问题还不能解决,并且执行 ssh -T git@github.com 出现如下提示 说明本地公钥没有问题
    ssh -T git@github.com
    Hi youcanping! You've successfully authenticated, but GitHub does not provide shell access.
    看本地的.git/config设置的仓库url地址和github使用的链接地址是否一致
    > cat .git/config
    [core]
    repositoryformatversion = 0
    filemode = true
    bare = false
    logallrefupdates = true
    ignorecase = true
    precomposeunicode = true
    [remote "origin"]
    url = https://github.com/youcanping/MyBlog.git
    fetch = +refs/heads/*:refs/remotes/origin/*
    [branch "master"]
    remote = origin
    merge = refs/heads/master


    https://youcanping.cn/2017/12/20/ssh-Permission-denied/

    连接测试命令：
    ssh-add -l 查看加入的密钥列表
    ssh -T git@git.oschina.net
    ssh -T git@github.com
    ssh -v git@github.com 查看调试信息

</pre>