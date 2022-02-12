# Spring boot 知识积累

<p>
<a href="#" onclick="refreshSpringBootContent('annotaion')">注解 Annotaion</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('banner')">banner生成链接</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('servlet')">SpringBoot中使用servlet</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('crossdomain')">SpringBoot中跨域</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('better')">SpringBoot 性能优化</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('log')">SpringBoot 日志系统</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('question')">SpringBoot 常见问题</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('configdb')">SpringBoot 数据库连接配置</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('configthreadpool')">SpringBoot 线程池配置</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshSpringBootContent('availableport')">SpringBoot 随机端口</a>&emsp;&emsp;&emsp;
</p>

---

##1、SpringBoot入门，整合Redis实现缓存
	
- <a href="#" target="_blank">https://blog.csdn.net/qq_27317475/article/details/81188642 </a>
- <a href="#" target="_blank">https://my.oschina.net/u/2935623/blog/2223054 </a>

##2、springboot项目搭建及常用技术整合
- <a href="https://www.cnblogs.com/leskang/p/10942506.html#" target="_blank">https://www.cnblogs.com/leskang/p/10942506.html </a>

##3、常量类的定义
- <a href="https://www.cnblogs.com/lihaoyang/p/6913295.html#" target="_blank">https://www.cnblogs.com/lihaoyang/p/6913295.html </a>

##4、lombok的使用
- <a href="https://blog.csdn.net/qq_22860341/article/details/81224890#" target="_blank">lombok工具插件安装（idea、eclipse）</a>
- <a href="https://blog.csdn.net/Y_hahaha/article/details/89186284#" target="_blank">lombok工具插件安装（idea、eclipse） </a>
 

##5、若是项目是一个不需要数据的一个工程那么若是我们不对工程的启动类就处理一下的话，在工程启动的过程中就会报错导致工程无法启动 问题的修改方法是在启动类上将数据库配置进行去除 @SpringBootApplication(
exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
这样我们的工程就是一个脱离数据存在的工程了这样的配置一般是因为我这个工程是单独进行放置前端文件的。

##6、统一升级版本号
参考Wiki：<a href="https://www.cnblogs.com/lukelook/p/11298168.html#" target="_blank">https://www.cnblogs.com/lukelook/p/11298168.html </a>
### 1引用插件
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>versions-maven-plugin</artifactId>
            <version>2.3</version>
            <configuration>
                <generateBackupPoms>false</generateBackupPoms>
            </configuration>
        </plugin>
方法1

    只需要执行mvn -N versions:update-child-modules
    则会自动把子POM的<parent>标签中的version更新为和父POM一致。
    这样修改一处然后运行一下执行一下命令就可以达到统一修改版本号的目的了。
     （在父model上执行后，所有子model中parent中的version都会修改）
方法2

    仅修改子工程版本和父级pom一致
    执行mvn versions:update-child-modules: 
    自动把子POM的<parent>标签中的version更新为和父POM一致
方法3

     mvn versions:set -DnewVersion=0.0.2-SNAPSHOT:更新的父及子Module的版本号都改成了0.0.2-SNAPSHOT.
     包括父和子工程
### 2不引用插件
    相比引入了插件
    mvn versions:commit ：如果没有在父pom用引入插件，
    Maven还会生成一个pom.xml.versionsBackup的备份文件，还需要mvn versions:commit提交

