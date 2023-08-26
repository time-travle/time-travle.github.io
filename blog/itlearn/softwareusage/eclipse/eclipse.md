<p>
<a href="#" onclick="showITLearnPage('softwareusage')">返回</a>&emsp;&emsp;&emsp;
</p>

---

## Eclipse使用期间的配置问

    1、Eclipse启动时jre寻找顺序：
    1、	先到安装目录下的eclipse.ini 文件中查看是不是有配置 –vm ，有配置就使用配置的
    2、	当第一个不满足时，就去Eclipse的安装目录中寻找jre的文件夹，有的话就是用这个jre
    3、	若是第二个还是不满足，那么就会从本地的环境变量中查看是不是配置了path，有的话就从配置的列表路径中查看，还是查不到时就会报错

## 多个JDK时

    电脑安装多个jdk时 可以配置自己想用的那个jdk

## 修改软件运行内存 eclipse的配置文件eclipse.ini

    修改软件运行时的内存，使得软件运行更流畅 大小最好适中 不可以过大或者过小
    -Xms256m
    -Xmx1024m

## eclipse中运行tomcat出现错误：

-Djava.endorsed.dirs=D:\Tomcat 9.0\endorsed is not supported -Djava.endorsed.dirs=D:\Tomcat 9.0\endorsed is not
supported. Endorsed standards and standalone APIs in modular form will be supported via the concept of upgradeable modules.

	启动tomcat时出现这个问题，都说这是由于jdk版本过高引起的(本人使用的是tomcat9.0 jdk 10.0.1)
	参考办法1：
	在Eclipse的Run -> Run Configurations的界面里 有一个设置参数里Arguments页面里的VM arguments的参数里面把最后的有-Djava.endorsed.dirs="D:\java\tomcat\apache-tomcat-9.0.10\endorsed"的参数删除，然后点击Apply，再点Run就行了。（不过我的出现一个问题，就是每次运行之后都要删除，因为这些个数据会自动还原到VM arguments中，也是很让人头疼的。）

	>> 网上说 这样可以 但是我的eslipes里面这个参数根本删除不掉，每次删除什么都不做，再打开就又了，可想而知再运行的时候 <<

	解决办法2：
	创建server时，选用tomcat9.0, 对应的JRE选为 jdk1.8.0的版本，问题终于解决 >>同<<

	JRE选 10.0.1的版本就会出现这样的问题，选1.8.0_162的版本就OK了！！！
	参考tomcat与jdk版本的适配：
	tomcat v7.0-------support-------->Java EE 5 and 6
	tomcat v8.0-------support-------->Java EE 5,6 and 7
	tomcat v8.5-------support-------->Java EE 5,6 and 7
	tomcat v9.0-------support-------->Java EE 5,6,7 and 8

	答案来自网络，并实践通过
	http://www.cnblogs.com/we-smile/p/9822916.html

## tomcat 9 启东时报错问题

    使用 startup.bat 启东时 tomcat启动报错 Can't load log handler "1catalina.org.apache.juli.FileHandler"
    使用eslipse启东时没有报错
    网上说是 tomcat的catalina.sh的文件中的JAVA_OPS配置覆盖导致，经检查发现没有发这个问题
    具体的其他的问题待定位，（目前由于jdk10 不稳定，暂时放弃jdk10的使用）
