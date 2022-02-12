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

1、SpringBoot入门，整合Redis实现缓存
	
- <a href="#" target="_blank">https://blog.csdn.net/qq_27317475/article/details/81188642 </a>
- <a href="#" target="_blank">https://my.oschina.net/u/2935623/blog/2223054 </a>

2、springboot项目搭建及常用技术整合
<a href="https://www.cnblogs.com/leskang/p/10942506.html#" target="_blank">https://www.cnblogs.com/leskang/p/10942506.html </a>

3、常量类的定义
<a href="https://www.cnblogs.com/lihaoyang/p/6913295.html#" target="_blank">https://www.cnblogs.com/lihaoyang/p/6913295.html </a>

4、lombok的使用
<a href="https://blog.csdn.net/qq_22860341/article/details/81224890#" target="_blank">https://blog.csdn.net/qq_22860341/article/details/81224890 </a>

5、若是项目是一个不需要数据的一个工程那么若是我们不对工程的启动类就处理一下的话，在工程启动的过程中就会报错导致工程无法启动 问题的修改方法是在启动类上将数据库配置进行去除 @SpringBootApplication(
exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
这样我们的工程就是一个脱离数据存在的工程了这样的配置一般是因为我这个工程是单独进行放置前端文件的。

6、统一升级版本号
参考Wiki：<a href="https://www.cnblogs.com/lukelook/p/11298168.html#" target="_blank">https://www.cnblogs.com/lukelook/p/11298168.html </a>
 
