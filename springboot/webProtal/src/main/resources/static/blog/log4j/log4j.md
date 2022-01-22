#Log4j 的使用

用来打印日志再服务器运行程序期间不用再进行多次的重启，才可以定位到问题，
工具的使用是方便的，主要的配置文件格式，需要进行配置，


##0、tomcat日志 tomcat日志配置

	在server.xml里的<host>标签下加上
	<Valve className="org.apache.catalina.valves.AccessLogValve"
	directory="logs" prefix="localhost_access_log." suffix=".txt"
	pattern="common" resolveHosts="false"/>
	className	官方文档上说了This MUST be set to org.apache.catalina.valves.AccessLogValve to use the default access log valve. &<60; 想配置访问日志？这就必须得写成这样。
	Directory	这个东西是日志文件放置的目录，在tomcat下面有个logs文件夹，那里面是专门放置日志文件的，当然你也可以修改，我就给改成了/opt (E:\)
	prefix	这个是日志文件的名称前缀，我的日志名称为localhost_access_log.2017-11-06.txt，前面的前缀就是这个localhost_access_log
	suffix	这就是后缀名啦，可以改成别的
	pattern	这个是最主要的参数了，具体的咱们下面讲，这个参数的内容比较丰富。
	resolveHosts	如果这个值是true的话，tomcat会将这个服务器IP地址通过DNS转换为主机名，如果是false，就直接写服务器IP地址啦
	rotatable	缺省值为true，默认的设置使得你的tomcat生成的文件命为prefix（前缀）+.+时间（一般是按天算）+.+suffix（后缀），参照我的日志名就知道了：localhost_access_log.2017-11-06.txt
				使用这个需要谨慎，因为你将其设置为false的话，tomcat会忽略时间，不会新生成文件，最后导致你的文件超级大，这样生成的文件名就是：localhost_access_log.txt

	pattern可以设置成两种方式，第一种是pattern="common"，第二种是pattern="combined",这就可以控制日志里面的格式，各位说了，pattern就这两种？common和combined又是什么意思，具体是什么格式呢？
	咱们一点一点看，其实pattern是可以设置的，common和combined只是集成了一些显示方式，就是将显示方式给组合了，pattern的实际值有如下几种，都是后面一个字母，前面一个%百分号
	common的值：%h %l %u %t %r %s %b
	combined的值：%h %l %u %t %r %s %b %{Referer}i %{User-Agent}i
	至于combined的值的最后两个：
		%{Referer}i：从那个页面链接跳转到的此页面
		%{User-agent}i：用户的User-Agent
	%a	 这是记录访问者的IP，在日志里是127.0.0.1
	%A	 这是记录本地服务器的IP，在日志里是192.168.254.108
	%b	 这是发送信息的字节数，不涵括http头，如果字节数为0的话，显示为- 

	%B	 看tomcat的解释，没看出来与b%的区别，但我这里显示为-1，没想明白，望知道者告知，我把官方解释贴出来吧 Bytes sent, excluding HTTP headers(发送的字节数，不包括HTTP头)
	%h	远端主机名(如果resolveHost=false，远端的IP地址）
	%H	访问者使用的协议，这里是HTTP/1.1
	%l	 官方说这个always return '-' 官方解释：Remote logical username from identd (可能这样翻译：记录浏览者进行身份验证时提供的名字){从identd返回的远端逻辑用户名（总是返回 '-'）};
	%m	 访问的方式，是GET还是POST，我这是GET
	%p	 收到请求的本地端口号，这里的是80。
	%q	查询字符串(如果存在，以 '?'开始)。 比如你访问的是aaa.jsp?bbb=ccc，那么这里就显示?bbb=ccc，明白了吧，这个q是querystring的意思
	%r	官方解释：First line of the request (method and request URI)——请求的第一行，包含了请求的方法和URI
	%s	 这个是响应http的状态码，这里返回的是304，咱们经常看见访问某个网页报错误500什么的，那也会返回500
	%S	 用户的session ID,这个session ID大家可以另外查一下详细的解释，反正每次都会生成不同的session ID
	%t	 日志和时间，使用通常的Log格式
	%u	认证以后的远端用户（如果存在的话，否则为'-'） 
	%U	 请求访问的URL地址，我这里是/seces/commonService?wsdl
	%v	 本地服务器名称，可能就是你url里面写的那个吧，我这里是localhost
	%D	 官方解释：Time taken to process the request, in millis，处理请求的时间，以毫秒为单位
	%T	 官方解释：Time taken to process the request, in seconds，处理请求的时间，以秒为单位



##1、日志中的几个日志级别

	共有8个级别，按照从低到高为：ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF。
	All:最低等级的，用于打开所有日志记录.
	Trace:是追踪，就是程序推进一下.
	Debug:指出细粒度信息事件对调试应用程序是非常有帮助的.
	Info:消息在粗粒度级别上突出强调应用程序的运行过程.
	Warn:输出警告及warn以下级别的日志.
	Error:输出错误信息日志.
	Fatal:输出每个严重的错误事件将会导致应用程序的退出的日志.
	OFF:最高等级的，用于关闭所有日志记录.
	

##demo：---properties
    
    log4j.logger.com.aa.ClassName=INFO,f
    
    log4j.appender.f = org.apache.log4j.RollingFileAppender
    log4j.appender.f.File = D:\\test.log
    log4j.appender.f.MaxFileSize = 30MB
    log4j.appender.f.MaxBackupIndex = 3
    log4j.appender.f.layout = org.apache.log4j.PatternLayout
    log4j.appender.f.layout.ConversionPattern = %d{yyyy-MM-dd hh:mm:ss}:%p %t %c - %m%n


        
        配置日志信息输出目的地
            log4j.appender.appenderName = fully.qualified.name.of.appender.class (下方的一个)
            org.apache.log4j.ConsoleAppender （控制台）
            org.apache.log4j.FileAppender （文件）
            org.apache.log4j.DailyRollingFileAppender （每天产生一个日志文件）
            org.apache.log4j.RollingFileAppender （文件大小到达指定尺寸的时候产生一个新的文件）
            org.apache.log4j.WriterAppender （将日志信息以流格式发送到任意指定的地方）
            可通过 log4j.appender.appenderName.MaxFileSize=100KB设置文件大小
            还可通过 log4j.appender.appenderName.MaxBackupIndex=1设置为保存一个备份文件。
        配置日志信息的格式
            log4j.appender.appenderName.layout = fully.qualified.name.of.layout.class (下方的一个)
            org.apache.log4j.HTMLLayout （以HTML表格形式布局），  
            org.apache.log4j.PatternLayout （可以灵活地指定布局模式），  
            org.apache.log4j.SimpleLayout （包含日志信息的级别和信息字符串），  
            org.apache.log4j.TTCCLayout （包含日志产生的时间、线程、类别等等信息）
        
        控制台选项
            ConsoleAppender控制台选项
                Threshold=DEBUG:指定日志消息的输出最低层次。
                ImmediateFlush=true:默认值是true,意谓着所有的消息都会被立即输出。
                Target=System.err：默认情况下是：System.out,指定输出控制台
            FileAppender 选项
                Threshold=DEBUF:指定日志消息的输出最低层次。
                ImmediateFlush=true:默认值是true,意谓着所有的消息都会被立即输出。
                File=mylog.txt:指定消息输出到mylog.txt文件。
                Append=false:默认值是true,即将消息增加到指定文件中，false指将消息覆盖指定的文件内容。
            RollingFileAppender 选项
                Threshold=DEBUG:指定日志消息的输出最低层次。
                ImmediateFlush=true:默认值是true,意谓着所有的消息都会被立即输出。
                File=mylog.txt:指定消息输出到mylog.txt文件。
                Append=false:默认值是true,即将消息增加到指定文件中，false指将消息覆盖指定的文件内容。
                MaxFileSize=100KB: 后缀可以是KB, MB 或者是 GB. 在日志文件到达该大小时，将会自动滚动，即将原来的内容移到mylog.log.1文件。
                MaxBackupIndex=2:指定可以产生的滚动文件的最大数。
                log4j.appender.A1.layout.ConversionPattern=%-4r %-5p %d{yyyy-MM-dd HH:mm:ssS} %c %m%n
---
##log4j.properties  

https://www.cnblogs.com/pigtail/archive/2013/02/16/2913195.html

### set log levels ###   log4j.rootLogger=[level],appenderName,appenderName,......
    log4j.rootLogger = INFO , console , debug , error ,stdout
 
### console ### 
    log4j.appender.console = org.apache.log4j.ConsoleAppender 
    log4j.appender.console.Target = System.out 
    log4j.appender.console.layout = org.apache.log4j.PatternLayout 
    log4j.appender.console.layout.ConversionPattern = %-d{yyyy-MM-dd HH\:mm\:ss} [%p]-[%c] %m%n 
 
### log file ### 
    log4j.appender.debug = org.apache.log4j.DailyRollingFileAppender 
    log4j.appender.debug.File = ../logs/springmvc-demo.log 
    log4j.appender.debug.Append = true 
    log4j.appender.debug.Threshold = INFO 
    log4j.appender.debug.layout = org.apache.log4j.PatternLayout 
    log4j.appender.debug.layout.ConversionPattern = %-d{yyyy-MM-dd HH\:mm\:ss} [%p]-[%c] %m%n 
 
### exception ### 
    log4j.appender.error = org.apache.log4j.DailyRollingFileAppender 
    log4j.appender.error.File = ../logs/springmvc-demo_error.log 
    log4j.appender.error.Append = true 
    log4j.appender.error.Threshold = ERROR 
    log4j.appender.error.layout = org.apache.log4j.PatternLayout 
    log4j.appender.error.layout.ConversionPattern = %-d{yyyy-MM-dd HH\:mm\:ss} [%p]-[%c] %m%n 
 
 
###需要声明，然后下方才可以使druid sql输出，否则会抛出log4j.error.key not found 
    log4j.appender.stdout=org.apache.log4j.ConsoleAppender 
    log4j.appender.stdout.Target=System.out 
    log4j.appender.stdout.layout=org.apache.log4j.PatternLayout 
    log4j.appender.stdout.layout.ConversionPattern=%d{ISO8601} %l %c%n%p: %m%n 
 
### druid sql ### 
    log4j.logger.druid.sql=warn,stdout 
    log4j.logger.druid.sql.DataSource=warn,stdout 
    log4j.logger.druid.sql.Connection=warn,stdout 
    log4j.logger.druid.sql.Statement=warn,stdout 
    log4j.logger.druid.sql.ResultSet=warn,stdout 	
	
---	
demo:  ---xml

log4j2.xml  
https://www.cnblogs.com/pjfmeng/p/11277124.html

    <?xml version="1.0" encoding="UTF-8"?>
    <!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
    <!--status="WARN" :用于设置log4j2自身内部日志的信息输出级别，默认是OFF-->
    <!--monitorInterval="30"  :间隔秒数,自动检测配置文件的变更和重新配置本身-->
    <configuration status="WARN" monitorInterval="30">
        <Properties>
            <!--自定义一些常量，之后使用${变量名}引用-->
            <Property name="logFilePath">log</Property>
            <Property name="logFileName">test.log</Property>
        </Properties>
        <!--appenders:定义输出内容,输出格式,输出方式,日志保存策略等,常用其下三种标签[console,File,RollingFile]-->
        <appenders>
            <!--console :控制台输出的配置-->
            <console name="Console" target="SYSTEM_OUT">
                <!--PatternLayout :输出日志的格式,LOG4J2定义了输出代码,详见第二部分-->
                <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
            </console>
            <!--File :同步输出日志到本地文件-->
            <!--append="false" :根据其下日志策略,每次清空文件重新输入日志,可用于测试-->
            <File name="log" fileName="${logFilePath}/${logFileName}" append="false">
                <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n"/>
            </File>
            <!--SMTP :邮件发送日志-->
            <SMTP name="Mail" subject="****SaaS系统正式版异常信息" to="message@message.info" from="message@lengjing.info" smtpUsername="message@message.info" smtpPassword="LENG****1234" smtpHost="mail.lengjing.info" smtpDebug="false" smtpPort="25" bufferSize="10">
                <PatternLayout pattern="[%-5p]:%d{YYYY-MM-dd HH:mm:ss} [%t] %c{1}:%L - %msg%n" />
            </SMTP>
            <!-- ${sys:user.home} :项目路径 -->
            <RollingFile name="RollingFileInfo" fileName="${sys:user.home}/logs/info.log"
                         filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log">
                <!--ThresholdFilter :日志输出过滤-->
                <!--level="info" :日志级别,onMatch="ACCEPT" :级别在info之上则接受,onMismatch="DENY" :级别在info之下则拒绝-->
                <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
                <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
                <!-- Policies :日志滚动策略-->
                <Policies>
                    <!-- TimeBasedTriggeringPolicy :时间滚动策略,默认0点小时产生新的文件,interval="6" : 自定义文件滚动时间间隔,每隔6小时产生新文件, modulate="true" : 产生文件是否以0点偏移时间,即6点,12点,18点,0点-->
                    <TimeBasedTriggeringPolicy interval="6" modulate="true"/>
                    <!-- SizeBasedTriggeringPolicy :文件大小滚动策略-->
                    <SizeBasedTriggeringPolicy size="100 MB"/>
                </Policies>
                <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件，这里设置了20 -->
                <DefaultRolloverStrategy max="20"/>
            </RollingFile>
    
            <RollingFile name="RollingFileWarn" fileName="${sys:user.home}/logs/warn.log"
                         filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/warn-%d{yyyy-MM-dd}-%i.log">
                <ThresholdFilter level="warn" onMatch="ACCEPT" onMismatch="DENY"/>
                <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
                <Policies>
                    <TimeBasedTriggeringPolicy/>
                    <SizeBasedTriggeringPolicy size="100 MB"/>
                </Policies>
            </RollingFile>
            <RollingFile name="RollingFileError" fileName="${sys:user.home}/logs/error.log"
                         filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log">
                <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY"/>
                <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
                <Policies>
                    <TimeBasedTriggeringPolicy/>
                    <SizeBasedTriggeringPolicy size="100 MB"/>
                </Policies>
            </RollingFile>
        </appenders>
        <!--然后定义logger，只有定义了logger并引入的appender，appender才会生效-->
        <loggers>
            <!--过滤掉spring和mybatis的一些无用的DEBUG信息-->
            <!--Logger节点用来单独指定日志的形式，name为包路径,比如要为org.springframework包下所有日志指定为INFO级别等。 -->
            <logger name="org.springframework" level="INFO"></logger>
            <logger name="org.mybatis" level="INFO"></logger>
            <!-- Root节点用来指定项目的根日志，如果没有单独指定Logger，那么就会默认使用该Root日志输出 -->
            <root level="all">
                <appender-ref ref="Console"/>
                <appender-ref ref="RollingFileInfo"/>
                <appender-ref ref="RollingFileWarn"/>
                <appender-ref ref="RollingFileError"/>
            </root>
            <!--AsyncLogger :异步日志,LOG4J有三种日志模式,全异步日志,混合模式,同步日志,性能从高到底,线程越多效率越高,也可以避免日志卡死线程情况发生-->
            <!--additivity="false" : additivity设置事件是否在root logger输出，为了避免重复输出，可以在Logger 标签下设置additivity为”false”-->
            <AsyncLogger name="AsyncLogger" level="trace" includeLocation="true" additivity="false">
                <appender-ref ref="RollingFileError"/>
            </AsyncLogger>
        </loggers>
    </configuration>

    
    <?xml version="1.0" encoding="UTF-8"?>
    <configuration status="OFF">
        <appenders>
            <Console name="Console" target="SYSTEM_OUT">
                <!--只接受程序中DEBUG级别的日志进行处理-->
                <ThresholdFilter level="DEBUG" onMatch="ACCEPT" onMismatch="DENY"/>
                <PatternLayout pattern="[%d{HH:mm:ss.SSS}] %-5level %class{36} %L %M - %msg%xEx%n"/>
            </Console>
            <!--处理DEBUG级别的日志，并把该日志放到logs/debug.log文件中-->
            <!--打印出DEBUG级别日志，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
            <RollingFile name="RollingFileDebug" fileName="./logs/debug.log"
                         filePattern="logs/$${date:yyyy-MM}/debug-%d{yyyy-MM-dd}-%i.log.gz">
                <Filters>
                    <!--只接受DEBUG级别的日志，其余的全部拒绝处理-->
                    <ThresholdFilter level="DEBUG"/>
                    <ThresholdFilter level="INFO" onMatch="DENY" onMismatch="NEUTRAL"/>
                </Filters>
                <!--输出日志的格式-->
                <PatternLayout
                        pattern="[%d{yyyy-MM-dd HH:mm:ss}] %-5level %class{36} %L %M - %msg%xEx%n"/>
                <Policies>
                    <SizeBasedTriggeringPolicy size="500 MB"/>
                    <TimeBasedTriggeringPolicy/>
                </Policies>
            </RollingFile>
            <!--处理INFO级别的日志，并把该日志放到logs/info.log文件中-->
            <RollingFile name="RollingFileInfo" fileName="./logs/info.log"
                         filePattern="logs/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log.gz">
                <Filters>
                    <!--只接受INFO级别的日志，其余的全部拒绝处理-->
                    <ThresholdFilter level="INFO"/>
                    <ThresholdFilter level="WARN" onMatch="DENY" onMismatch="NEUTRAL"/>
                </Filters>
                <PatternLayout
                        pattern="[%d{yyyy-MM-dd HH:mm:ss}] %-5level %class{36} %L %M - %msg%xEx%n"/>
                <Policies>
                    <SizeBasedTriggeringPolicy size="500 MB"/>
                    <TimeBasedTriggeringPolicy/>
                </Policies>
            </RollingFile>
            <!--处理WARN级别的日志，并把该日志放到logs/warn.log文件中-->
            <RollingFile name="RollingFileWarn" fileName="./logs/warn.log"
                         filePattern="logs/$${date:yyyy-MM}/warn-%d{yyyy-MM-dd}-%i.log.gz">
                <Filters>
                    <ThresholdFilter level="WARN"/>
                    <ThresholdFilter level="ERROR" onMatch="DENY" onMismatch="NEUTRAL"/>
                </Filters>
                <PatternLayout
                        pattern="[%d{yyyy-MM-dd HH:mm:ss}] %-5level %class{36} %L %M - %msg%xEx%n"/>
                <Policies>
                    <SizeBasedTriggeringPolicy size="500 MB"/>
                    <TimeBasedTriggeringPolicy/>
                </Policies>
            </RollingFile>
            <!--处理error级别的日志，并把该日志放到logs/error.log文件中-->
            <RollingFile name="RollingFileError" fileName="./logs/error.log"
                         filePattern="logs/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log.gz">
                <ThresholdFilter level="ERROR"/>
                <PatternLayout
                        pattern="[%d{yyyy-MM-dd HH:mm:ss}] %-5level %class{36} %L %M - %msg%xEx%n"/>
                <Policies>
                    <SizeBasedTriggeringPolicy size="500 MB"/>
                    <TimeBasedTriggeringPolicy/>
                </Policies>
            </RollingFile>
            <!--druid的日志记录追加器-->
            <RollingFile name="druidSqlRollingFile" fileName="./logs/druid-sql.log"
                         filePattern="logs/$${date:yyyy-MM}/api-%d{yyyy-MM-dd}-%i.log.gz">
                <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss}] %-5level %L %M - %msg%xEx%n"/>
                <Policies>
                    <SizeBasedTriggeringPolicy size="500 MB"/>
                    <TimeBasedTriggeringPolicy/>
                </Policies>
            </RollingFile>
        </appenders>
        <!--     然后定义logger，只有定义了logger并引入的appender，appender才会生效 -->
        <loggers>
            <!--默认的root的logger-->
            <root level="DEBUG">
                <appender-ref ref="Console"/>
                <appender-ref ref="RollingFileInfo"/>
                <appender-ref ref="RollingFileWarn"/>
                <appender-ref ref="RollingFileError"/>
                <appender-ref ref="RollingFileDebug"/>
            </root>
            <!--额外配置的logger-->
            <!--记录druid-sql的记录-->
            <logger name="druid.sql.Statement" level="debug" additivity="false">
                <appender-ref ref="druidSqlRollingFile"/>
            </logger>
            <!--log4j2 自带过滤日志-->
            <Logger name="org.apache.catalina.startup.DigesterFactory" level="error" />
            <Logger name="org.apache.catalina.util.LifecycleBase" level="error" />
            <Logger name="org.apache.coyote.http11.Http11NioProtocol" level="warn" />
            <logger name="org.apache.sshd.common.util.SecurityUtils" level="warn"/>
            <Logger name="org.apache.tomcat.util.net.NioSelectorPool" level="warn" />
            <Logger name="org.crsh.plugin" level="warn" />
            <logger name="org.crsh.ssh" level="warn"/>
            <Logger name="org.eclipse.jetty.util.component.AbstractLifeCycle" level="error" />
            <Logger name="org.hibernate.validator.internal.util.Version" level="warn" />
            <logger name="org.springframework.boot.actuate.autoconfigure.CrshAutoConfiguration" level="warn"/>
            <logger name="org.springframework.boot.actuate.endpoint.jmx" level="warn"/>
            <logger name="org.thymeleaf" level="warn"/>
        </loggers>
    </configuration>
    



log4j xml  
 
 https://www.cnblogs.com/cyq9527/p/11671648.html

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE log4j:configuration SYSTEM  "http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/xml/doc-files/log4j.dtd">
    <log4j:configuration debug="false" >
        <appender name="myConsole" class="org.apache.log4j.ConsoleAppender">
           <layout class="org.apache.log4j.PatternLayout">
              <param name="ConversionPattern" value="[%d{dd HH:mm:ss,SSS\} %-5p] [%t] %c{2\} - %m%n"/>
           </layout>
           <!--过滤器设置输出的级别-->
           <filter class="org.apache.log4j.varia.LevelRangeFilter">
               <param name="levelMin" value="debug" />
               <param name="levelMax" value="warn" />
               <param name="AcceptOnMatch" value="true" />
           </filter>
        </appender>
        <appender name="myFile" class="org.apache.log4j.RollingFileAppender">
            <param name="File" value="D:/dubbo/logs/provider/rollingLog/dubbo.log" /><!-- 设置日志输出文件名 -->
            <!-- 设置是否在重新启动服务时，在原有日志的基础添加新日志 -->
            <param name="Append" value="true" />
            <param name="MaxBackupIndex" value="10" />
    
            <layout class="org.apache.log4j.PatternLayout">
              <param name="ConversionPattern" value="%p (%c:%L)- %m%n" />
            </layout>
        </appender>
    
        <appender name="activexAppender" class="org.apache.log4j.DailyRollingFileAppender">
            <param name="File" value="D:/dubbo/logs/provider/dailyLog/dubbo.log" />
            <param name="DatePattern" value="'.'yyyy-MM-dd'.log'" />
            <layout class="org.apache.log4j.PatternLayout">
               <param name="ConversionPattern" value="[%d{MMdd HH:mm:ss SSS\} %-5p] [%t] %c{3\} - %m%n" />
            </layout>
        </appender>
         <!-- 指定logger的设置，additivity指示是否遵循缺省的继承机制-->
        <logger name="com.runway.bssp.activeXdemo" additivity="false">
             <appender-ref ref="activexAppender" />
        </logger>
    
        <!-- 根logger的设置-->
        <root>
          <priority value ="debug"/>
          <appender-ref ref="myConsole"/>
          <appender-ref ref="myFile"/>
        </root>
    </log4j:configuration>
        