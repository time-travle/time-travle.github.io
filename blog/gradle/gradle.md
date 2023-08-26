# Gradle 使用

---

## 设置gradle 下载位置

gradle会下载相关需要依赖的jar包，

    默认的本地存放地址是：C:/Users/(用户名)/.gradle/caches/modules-2/files-2.1，很多人和我一样不愿意放在C盘，所以需要修改位置。
    添加变量GRADLE_USER_HOME，值为gradle依赖下载存放路径

对于低版本的IDEA来说没有用（当然上面的环境变量还是要添加的），在IDEA中使用gradle需要修改下面的路径

    打开idea的设置 Settings --> Build, Execution, Deployment ---> Build Tools --> Gradle ，
    页面中下方的Global Gradle Settings 选择Service directory path 选择自己的路径 一般使用.gradle来存。
    他会新建文件夹，也会去下载较新的gradle包

参考：

- <a href="https://www.cnblogs.com/Jimc/p/10082458.html" target="_blank">Gradle下载依赖jar包位置修改 </a>
- <a href="https://blog.csdn.net/qq_39935047/article/details/90346320" target="_blank">
  修改修改idea默认的gradle存储的仓库路径，已经下载的镜像地址 </a>

## gradle安装：

- <a href="https://www.cnblogs.com/zeussbook/p/10556025.html" target="_blank">Gradle（一）安装配置 </a>

    配置环境变量：GRADLE_HOME 变量值为Gradle文件解压的实际路径，
    例：E:\Gradle\gradle-5.2.1-all\gradle-5.2.1
    在系统变量 path中加入：%GRADLE_HOME%\bin;

    在cmd输入gradle -v验证是否安装成功

    配置Gradle使用maven本地仓库，这样Gradle就不会重新下载已经存在maven本地仓库的jar包，从而节省时间和空间。
    在环境变量中加入新的系统变量：GRADLE_USER_HOME  变量值是maven本地仓库的路径，
    例C:\Users\Administrator\.m2\repository
    此时，Gradle下载的文件将放到指定的仓库路径中。
    但是还需要修改build.gradle文件中加入mavenLocal() 引用本地仓库
        repositories { //repositories闭包
            mavenLocal() //配置先从本地仓库寻找jar包，优先寻找上一个配置，找到不执行下面的配置
            mavenCentral() //配置从中央仓库寻找
            google() //第三方仓库
            jcenter() //代码托管库：设置之后可以在项目中轻松引用jcenter上的开源项目
        }

参考： <a href="https://www.cnblogs.com/NyanKoSenSei/p/11458953.html" target="_blank">Gradle的安装与配置 </a>

## gradle 下载镜像

- <a href="https://yq.aliyun.com/articles/657575" target="_blank">为Gradle设置镜像，解决jcenter依赖无法下载或者下载过慢问题</a>
- <a href="https://www.cnblogs.com/chansblogs/p/12943991.html" target="_blank">Maven、Gradle 配置国内镜像源 </a>
- <a href="https://blog.csdn.net/u012184539/article/details/98962161" target="_blank">Gradle配置国内镜像 </a>

## gradle 国内加速，修改镜像源为什么慢

- <a href="https://www.cnblogs.com/huiyi0521/p/10997152.html" target="_blank">gradle 国内加速，修改镜像源 </a>

    由于默认情况下执行 gradle 各种命令是去国外的 gradle 官方镜像源获取需要安装的具体软件信息，所以在不使用代理、不翻墙的情况下，从国内访问国外服务器的速度相对比较慢

## 如何修改镜像源

    阿里旗下维护着一个国内 maven 镜像源，同样适用于 gradle。再一次对阿里表示感谢，到目前为止介绍过 npm、yarn、maven、composer 的国内加速方案全部使用着阿里旗下提供的国内镜像源，感谢为开发者提供的便利

    a). 配置只在当前项目生效
        在 build.gradle 文件内修改/添加 repositories 配置

        repositories {
            maven {
                url "http://maven.aliyun.com/nexus/content/groups/public"
            }
        }
    b). 配置全局生效
        找到 (用户家目录)/.gradle/init.gradle 文件，如果找不到 init.gradle 文件，自己新建一个

        修改/添加 init.gradle 文件内的 repositories 配置

        allprojects {
            repositories {
                maven {
                    url "http://maven.aliyun.com/nexus/content/groups/public"
                }
            }
        }
        验证是否修改成功
        在 build.gradle 文件内增加一个任务

        task showRepos {
            doLast {
                repositories.each {
                    println "repository: ${it.name} ('${it.url}')"
                }
            }
        }
        然后执行 gradle -q showRepos 任务，如果输出了刚刚配置的地址就说明修改成功，如下：

        $ gradle -q showRepos
        repository: maven ('http://maven.aliyun.com/nexus/content/groups/public')

## 本地Gradle配置方法，免去长时间的更新同步等待

    通常gradle项目在gradle\wrapper\gradle-wrapper.properties中配置在线gradle：

    distributionBase=GRADLE_USER_HOME
    distributionPath=wrapper/dists
    zipStoreBase=GRADLE_USER_HOME
    zipStorePath=wrapper/dists
    distributionUrl=https\://services.gradle.org/distributions/gradle-4.2.1-all.zip
     
    把它更写成：
    distributionUrl=file:///E:/gradle421/gradle-4.2.1-all.zip        

参考：

- <a href="https://blog.csdn.net/Knightletter/article/details/101074466" target="_blank">Android
  Studio解决Gradle下载缓慢的问题 </a>
- <a href="https://www.jianshu.com/p/fe0fa478e1cc" target="_blank">解决 Android Studio 创建项目时极其的慢的尴尬 </a>

## Gradle 提速几种方式

- <a href="https://www.jianshu.com/p/e45faaec1df0" target="_blank"> Gradle 提速几种方式 </a>

- <a href="https://www.cnblogs.com/duwenlei/p/9853705.html" target="_blank">快速解决 GRADLE 项目下载 gradle-*-all.zip 慢的问题 </a>
