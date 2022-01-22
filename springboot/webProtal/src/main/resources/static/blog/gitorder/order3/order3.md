#Git 中阶 Order 日常积累 
<p>
    <a href="#" onclick="refreshContent('gitorder')">返回目录</a>
</p>
<a href="#" onclick="refreshOrderContent('config')">Git（config）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('question')">Git（question）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order1')">Git（日常命令）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order2')">Git（初阶）</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshOrderContent('order4')">Git（高阶）</a>&emsp;&emsp;&emsp;

---

####忽略文件文件夹(不纳入git管理)
	使用gitignore文件来解决这个问题，步骤是：
	1） touch .gitignore #创建gitignore隐藏文件
	2） vim .gitignore #编辑文件，加入指定文件
		按"Esc"键后。按":w"保存。
		按":wq"或"ZZ"(大写的Z)保存并退出.gitignore文件的编辑。

	【方式一】拉取到代码的人都使用
		在仓库目录下新建一个名为.gitignore的文件
	（因为是点开头，没有文件名，没办法直接在windows目录下直接创建，必须通过右键Git Bash，
		按照linux的方式来新建.gitignore文件）。
		.gitignore文件对其所在的目录及所在目录的全部子目录均有效。
		通过将.gitignore文件添加到仓库，其他开发者更新该文件到本地仓库，以共享同一套忽略规则。

	【方式二】仅仅本地是OK的
	   通过配置.git/info/exclude文件来忽略文件。这种方式对仓库全局有效，只能对自己本地仓库有作用，其他人没办法通过这种方式来共享忽略规则，除非他人也修改其本地仓库的该文件。

	【方式三】仅仅本地是OK的
	   通过.git/config配置文件的core. Excludesfile选项，指定一个忽略规则文件（完整路径），如下图所示。忽略规则在文件e:/gitignore.txt中（当然该文件名可以任意取）。
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
	3.注意：如果你不慎在创建.gitignore文件之前就push了项目，那么即使你在.gitignore文件中写入新的过滤规则，
		这些规则也不会起作用，Git仍然会对所有文件进行版本管理。
		简单来说出现这种问题的原因就是Git已经开始管理这些文件了，所以你无法再通过过滤规则过滤它们。
		所以大家一定要养成在项目开始就创建.gitignore文件的习惯，否则一单push，处理起来会非常麻烦。	

	4、对已提交不必要文件的处理方法
    　　有时候当我们明白这个gitignore之后才发现我们已经提交不必要的文件了，而自己又是一个较完美主义者，不愿意让那些文件存在我们库里，该怎么做呢？有什么办法吗？办法肯定是有的。
    　　那么我们现在预设的问题模型是：项目文件里有一个node_modules文件，该文件是存储自动生成的模型的文件，然后我已经把这个文件提交到github库里了，
    现在我明白了这个道里过后想删掉它并且以后都不再提交它。处理的方法：首先，我们再终端进入项目的根文件下面，创建.gitignore文件，并且添加需要忽略提交的文件，
    如上面我的一个.gitignore文件，然后输入如下命令
    git rm -r --cached node_modules（要删除的文件名）
    　　然后再
    git push
    　　最后我们去我们的github的库里去就会发现刚刚删除的东西已经成功删除啦~	
	
	5、在使用.gitignore文件后如何删除远程仓库中以前上传的此类文件而保留本地文件
		比如我们在使用git和github的时候，之前没有写.gitignore文件，就上传了一些没有必要的文件，在添加了.gitignore文件后，就想删除远程仓库中的文件却想保存本地的文件。 
		这时候不可以直接使用git rm directory，这样会删除本地仓库的文件。 
		可以使用git rm -r –cached directory来删除缓冲，然后进行commit和push，这样会发现远程仓库中的不必要文件就被删除了，以后可以直接使用git add -A来添加修改的内容，上传的文件就会受到.gitignore文件的内容约束。

	6、强制忽略已经被管理的文件
		1、对于未加入版本控制的文件，我们可以创建.gitignore文件，忽略你想忽略的文件。
		2、对于已经加入版本控制的文件，我们可以强制忽略文件
		git update-index --assume-unchanged <files>
		如果不小心 local.properties 文件被同事加入版本库了，更改了自己的sdk目录后，可以强制忽略这个文件
		git update-index --assume-unchanged local.properties
		这样，即使已经更改了文件，用git status也不会看见文件已经更改。
		但在使用时需要小心，取消这种设定可以使用：
		git update-index --no-assume-unchanged <files>
		https://www.jianshu.com/p/e439e940f386

