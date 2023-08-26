<p>
<a href="#" onclick="showITLearnPage('softwareusage')">返回</a>&emsp;&emsp;&emsp;
</p>

---

# Docker 命令的使用

#### 查看docker镜像

    docker images

#### 列出本机的所有 image 文件。

    $ docker image ls

#### 查看docker容器

    docker ps

#### 容器状态/日志查看

    docker ps -a ###列出当前容器（包括已经停止的）

#### 列出本机正在运行的容器

    $ docker container ls

#### 列出本机所有容器，包括终止运行的容器

    $ docker container ls --all


#### 获取镜像

    $ docker pull ubuntu

#### 制作命令

    docker build -t 10.1.93.194:5000/admin:v1.0 .

#### 停止/删除/启动/重启容器

    docker stop 容器ID/名称
    docker rm 容器ID/名称    ### 当容器发生重名时，我们就得删除以前的或者把新的改名
    docker start 容器ID/名称
    docker restart 容器ID/名称

#### 持久化（挂载主机硬盘）

    启动时通过-v 主机目录:容器目录选项即可将主机的目录挂载到容器中
    sudo docker run -d --name test -v /home/xxx:/root/xxx ubuntu:16.04

#### 端口映射

    有时候容器内启动的是一个网络服务，这个服务去监听一个接口。
    但它监听的实际上是容器的内部端口，直接去访问是不行的，需要映射一个主机端口到容器的端口。
    
    通过-p 主机端口:容器端口 或直接使用主机网络--net=host
        docker run -d -p 5000:5000 ubuntu:16.04
        docker run -d --net=host ubuntu:16.04

#### 启动命令

    docker run -d -p 8082:8082 --restart=always --name quartz -v /log:/log 10.1.92.1:5000/quartz:v1.0

#### 启动容器（docker run)

    docker run [OPTIONS] IMAGE [COMMAND] [ARG...]

        -d: 后台运行容器，并返回容器ID；
        -i: 以交互模式运行容器，通常与 -t 同时使用；
        -P: 随机端口映射，容器内部端口随机映射到主机的端口
        -p: 指定端口映射，格式为：主机(宿主)端口:容器端口
        -t: 为容器重新分配一个伪输入终端，通常与 -i 同时使用；
        --name="nginx-lb": 为容器指定一个名称；
        --cpuset="0-2" or --cpuset="0,1,2": 绑定容器到指定CPU运行；
        -m :设置容器使用内存最大值；
        --net=bridge: 指定容器的网络连接类型，支持 bridge/host/none/container: 四种类型；
        --expose=[]: 开放一个端口或一组端口；
        --volume , -v: 绑定一个卷
        --rm ,退出容器后删除名字 
        --restart ,重启选项，有no/always/on-failure/unless-stopped
        --entrypoint ,重写容器进程的入口

#### 进入容器有2种方式，一种是attach, 一种是

    docker exec -it 容器ID/名称	