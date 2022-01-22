<p>
    <a href="#" onclick="refreshContent('redis')">返回目录</a>
</p>

# 使用
---

启动与停止

    Windows下启动与停止：
    
        方式一：
            在命令行窗口中输入redis-server redis.windows.conf 启动redis
            关闭命令行窗口就是关闭redis。
            启动命令：D:\redis\redis-server.exe D:\redis\redis.windows.conf
            
        方式二：redis作为windows服务启动方式（推荐这种方式）
            redis-server --service-install redis.windows.conf --maxheap 内存大小
            启动服务：redis-server --service-start
            停止服务：redis-server --service-stop
            安装服务 D:\redis\redis-server.exe --service-install D:\redis\redis.windows.conf --maxheap 200m
            启动：D:\redis\redis-server.exe --service-start
            停止：D:\redis\redis-server.exe --service-stop
        
    Linux下
        只需要解压到任意下目录即可。
        例如：下载后的文件为：Redis-x64-3.2.100.tar.gz.
        
        解压放在Linux /opt目录下。
        
        1、tar -zxvf Redis-x64-3.2.100.tar.gz解压。
        
        2、解压后出现redis-3.2.100。
        
        3、进入目录：cd redis-3.2.100。
            在里面执行make命令，完成后继续执行make insatll。
        
        4、解压成功后。进入/usr/local/bin下可查看安装成功。
        
        5、启动命令：redis-server /myredis/redis.conf。
        
        6、停止命令：redis-cli shutdown
    
    