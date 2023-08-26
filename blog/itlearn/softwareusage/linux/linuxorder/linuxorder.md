<p>
    <a href="#" onclick="refreshSoftWareUse('linux')">返 回</a>

</p>

---

# linux 命令

#### 立刻关机

    shutdown -h now 或者 poweroff # 离开关机
    shutdown -h 2 #两分钟后关机
    poweroff  立刻关机

#### 立刻重启

    shutdown -r now 或者 reboot # 马上重启    
    shutdown -r 2 # 两分钟后重启
    reboot     立刻重启

#### 刷新资源文件

    source file

#### 文件编辑 vim _fileName vi _fileName

    vi 是基于行的一个编辑器 常用的命令是
        :w 存盘当前文件
        :w!     强制保存当前文件
        :w file 将内容保存到指定的文件
        :w! file 将文件强制保存到指定的文件
        :q 退出编辑器
        :q!     强制退出编辑器

###### 操作步骤示例 ##

    1.保存文件：按"ESC" -> 输入":" -> 输入"wq",回车 //保存并退出编辑 
    2.取消操作：按"ESC" -> 输入":" -> 输入"q!",回车 //撤销本次修改并退出编辑

#### 文件路径切换

    cd /                 //切换到根目录
    cd /bin              //切换到根目录下的bin目录
    cd ../               //切换到上一级目录 或者使用命令：cd ..
    cd ~                 //切换到home目录
    cd -                 //切换到上次访问的目录
    cd xx(文件夹名)       //切换到本目录下的名为xx的文件目录，如果目录不存在报错
    cd /xxx/xx/x         //可以输入完整的路径，直接切换到目标目录，输入过程中可以使用tab键快速补全

#### 查看当前位置

    pwd

#### 查看当前目录下有什么文件和文件夹 列表显示

    ll == ls -l

    表格显示
        ls
    ll/ls 列出当前目录下的文件
    -r； 递归列出之目录的内容
    -a： 列出所有包括以 . 开头的文件
    -c： 多列 输出
    -i： 长列表输出显示文件信息

#### 新建文件夹

    mkdir tools //在当前目录下创建一个名为tools的目录 
    mkdir /bin/tools //在指定目录下创建一个名为tools的目录

#### 新建文件

    touch _fileName

#### 删除文件/夹

    rm 选项 文件或文件夹名称 选项一般就是-rf，若不加此选项则只能删除文件，若加次选择则删除文件夹及其子文件，f为强制执行的意思，有该参数则不会询问是否删除而是强制删除。 
    示例：   （1）rm abc.txt :    删除abc.txt文件。 
            （2）rm -rf abc : 删除abc文件夹及其子文件。

      rm 文件名              //删除当前目录下的文件
      rm -f 文件名           //删除当前目录的的文件（不询问）
      rm -r 文件夹名         //递归删除当前目录下此名的目录
      rm -rf 文件夹名        //递归删除当前目录下此名的目录（不询问）
      rm -rf *              //将当前目录下的所有目录和文件全部删除
      rm -rf /*             //将根目录下的所有文件全部删除【慎用！相当于格式化系统】

#### 移动文件/夹

    mv 选项 源文件或目录 目标文件或目录 
    选项一般有： 
        -b ：若需覆盖文件，则覆盖前先行备份。 
        -f ：force 强制的意思，如果目标文件已经存在，不会询问而直接覆盖。 
        -i ：若目标文件 (destination)
    
    已经存在时，就会询问是否覆盖。 
    示例： 
        （1）mv b1.txt b1.log : 将文件b1.txt重命名为b1.log。 
        （2）mv b1.log /home/test/b : 将b1.log文件移到目录b中。 
        （3）mv /home/test/a/*.txt /home/test/b : 将/home/test/a目录下所有txt文件移动到/home/test/b目录下。

    命令格式：
    mv 当前目录名 新目录名        //修改目录名，同样适用与文件操作
    mv /usr/tmp/tool /opt       //将/usr/tmp目录下的tool目录剪切到 /opt目录下面
    mv -r /usr/tmp/tool /opt    //递归剪切目录中所有文件和文件夹

#### 复制文件/夹

    cp 选项 源文件或目录 目的文件或目录 
    选项一般有： 
        -i : 覆盖既有文件之前先询问用户。 
        -R/r : 递归处理，将指定目录下的所有文件与子目录一并处理。 
        -b : 覆盖已存在的文件目标前将目标文件备份。 
        -a 复制的文件与原文件时间一样 
    示例： 
        （1）cp aaa.txt /home/test/a.txt : 将aaa.txt文件复制到/home/test目录下并改名为a.txt。 
        （2）cp -r /home/test/a /home/test/b : 将/home/test/a目录及目录下所有文件夹和子文件复制到/home/test/b目录下。 
        （3）cp /home/test/a/b*.txt /home/test/b :将/home/test/a目录下所有b开头的txt文件复制到/home/test/b目录下。

#### 清除屏幕

    clear

#### 命令上传/下载文件

    说明：
        使用wget从网上下载软件、音乐、视频
    示例：
        wget http://prdownloads.sourceforge.net/sourceforge/nagios/nagios-3.2.1.tar.gz//下载文件并以指定的文件名保存文件
        wget -O nagios.tar.gz http://prdownloads.sourceforge.net/sourceforge/nagios/nagios-3.2.1.tar.gz
    
    在Linux主机上，安装上传下载工具包rz及sz 
        yum install -y lrzsz

        上传
            在Linux命令行下输入rz，
        下载
            在linux下输入命令 sz 文件名

#### 文件解压/打包

    tar -zcvf 打包压缩后的文件名 要打包的文件
    
    参数说明：
        -c 建立新的压缩文件
        -f 指定压缩文件
        -r 添加文件到已经压缩文件包中
        -u 添加改了和现有的文件到压缩包中
        -x 从压缩包中抽取文件
        -t 显示压缩文件中的内容
        -z 支持gzip压缩
        -j 支持bzip2压缩
        -Z 支持compress解压文件
        -v 显示操作过程
    
    示例： 
        tar -zcvf a.tar file1 file2,... //多个文件压缩打包
        
        tar -zxvf a.tar //解包至当前目录
        tar -zxvf a.tar -C /usr------ //指定解压的位置
        unzip test.zip //解压*.zip文件
        unzip -l test.zip //查看*.zip文件的内容

### 文件搜索

      find /bin -name 'a*'        //查找/bin目录下的所有以a开头的文件或者目录 
      find . -name "*.c"     //将目前目录及其子目录下所有延伸档名是 c 的文件列出来 
      find . -type f //将目前目录其其下子目录中所有一般文件列出 
      find . -ctime -20 //将目前目录及其子目录下所有最近 20 天内更新过的文件列出 
      find /var/log -type f -mtime +7 -ok rm{} \; //查找/var/log目录中更改时间在7日以前的普通文件，并在删除之前询问它们 
      find . -type f -perm 644 -exec ls -l {} \; //查找前目录中文件属主具有读、写权限，并且文件所属组的用户和其他用户具有读权限的文件 
      find / -type f -size 0 -exec ls -l {} \; //为了查找系统中所有文件长度为0的普通文件，并列出它们的完整路径

    命令格式：
     find pathname -options [-print -exec -ok ...]
     命令参数：
       pathname: find命令所查找的目录路径。例如用.来表示当前目录，用/来表示系统根目录。
       -print： find命令将匹配的文件输出到标准输出。
       -exec： find命令对匹配的文件执行该参数所给出的shell命令。相应命令的形式为'command' {  } \;，注意{   }和\；之间的空格。
       -ok： 和-exec的作用相同，只不过以一种更为安全的模式来执行该参数所给出的shell命令，在执行每一个命令之前，都会给出提示，让用户来确定是否执行。

     命令选项：
       -name 按照文件名查找文件
       -perm 按文件权限查找文件
       -user 按文件属主查找文件
       -group  按照文件所属的组来查找文件。
       -type  查找某一类型的文件，诸如：
             b - 块设备文件
             d - 目录
             c - 字符设备文件
             l - 符号链接文件
             p - 管道文件
             f - 普通文件
       -size n :[c] 查找文件长度为n块文件，带有c时表文件字节大小
       -amin n   查找系统中最后N分钟访问的文件
       -atime n  查找系统中最后n*24小时访问的文件
       -cmin n   查找系统中最后N分钟被改变文件状态的文件
       -ctime n  查找系统中最后n*24小时被改变文件状态的文件
       -mmin n   查找系统中最后N分钟被改变文件数据的文件
       -mtime n  查找系统中最后n*24小时被改变文件数据的文件 (用减号-来限定更改时间在距今n日以内的文件，而用加号+来限定更改时间在距今n日以前的文件)
       -maxdepth n 最大查找目录深度
       -prune选项来指出需要忽略的目录。在使用-prune选项时要当心，因为如果你同时使用了-depth选项，那么-prune选项就会被find命令忽略
       -newer 如果希望查找更改时间比某个文件新但比另一个文件旧的所有文件，可以使用-newer选项
     
    实例：
     （1）查找48小时内修改过的文件
        find -atime -2
     （2）在当前目录查找 以.log结尾的文件。 ". "代表当前目录
        find ./ -name '*.log'
     （3）查找/opt目录下 权限为 777的文件
        find /opt -perm 777
     （4）查找大于1K的文件
         find -size +1000c
         find -size 1000c 查找等于1000字符的文件
              -exec      参数后面跟的是command命令，它的终止是以;为结束标志的，所以这句命令后面的分号是不可缺少的，考虑到各个系统中分号会有不同的意义，所以前面加反斜杠。{}   花括号代表前面find查找出来的文件名。
     （5）在当前目录中查找更改时间在10日以前的文件并删除它们(无提醒）
         find . -type f -mtime +10 -exec rm -f {} \;
     （6）当前目录中查找所有文件名以.log结尾、更改时间在5日以上的文件，并删除它们，只不过在删除之前先给出提示。 按y键删除文件，按n键不删除
         find . -name '*.log' mtime +5 -ok -exec rm {} \;
     （7）当前目录下查找文件名以passwd开头，内容包含"pkg"字符的文件
         find . -f -name 'passwd*' -exec grep "pkg" {} \;
     （8）用exec选项执行cp命令 
         find . -name '*.log' -exec cp {} test3 \;
         -xargs find命令把匹配到的文件传递给xargs命令，而xargs命令每次只获取一部分文件而不是全部，不像-exec选项那样。
                这样它可以先处理最先获取的一部分文件，然后是下一批，并如此继续下去。
     （9）查找当前目录下每个普通文件，然后使用xargs来判断文件类型
         find . -type f -print | xargs file
     （10）查找当前目录下所有以js结尾的并且其中包含'editor'字符的普通文件
         find . -type f -name "*.js" -exec grep -lF 'ueditor' {} \;
         find -type f -name '*.js' | xargs grep -lF 'editor'
     （11）利用xargs执行mv命令
         find . -name "*.log" | xargs -i mv {} test4
     （12）用grep命令在当前目录下的所有普通文件中搜索hostnames这个词,并标出所在行
         find . -name \*(转义） -type f -print | xargs grep -n 'hostnames'
     （13）查找当前目录中以一个小写字母开头，最后是4到9加上.log结束的文件
         find . -name '[a-z]*[4-9].log' -print
     （14）在test目录查找不在test4子目录查找
         find test -path 'test/test4' -prune -o -print
     （15）实例1：查找更改时间比文件log2012.log新但比文件log2017.log旧的文件
         find -newer log2012.log ! -newer log2017.log

#### 文件内容搜索

    grep -i "the" demo_file //在文件中查找字符串(不区分大小写)
    grep -A 3 -i "example" demo_text //输出成功匹配的行，以及该行之后的三行
    grep -r "ramesh" * //在一个文件夹中递归查询包含指定字符串的文件

#### 查看文件

    cat _fileName cat主要有三大功能： 1.一次显示整个文件:cat filename 2.从键盘创建一个文件:cat > filename 只能创建新文件,不能编辑已有文件. 3.将几个文件合并为一个文件:cat
    file1 file2 > file

#### 定位可执行文件

    whereis ls    将和ls文件相关的文件都查找出来

## Linux常用命令学习

- <a href="https://www.cnblogs.com/gaojun/p/3359355.html" target="_blank">Linux常用命令学习 </a>
- <a href="https://blog.csdn.net/qq_23329167/article/details/83856430" target="_blank">Linux常用命令 </a>