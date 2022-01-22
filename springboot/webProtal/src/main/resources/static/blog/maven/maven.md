# Maven 使用


1、maven项目eclipse提示pom.ml有错，提示信息就是org.apache.maven.plugin.war.WarMojo

        不论是编译时还是构建是抑或是构建时，在控制台出来还是通过弹框的方式提示出来，那么久需要检查这个对应的maven工程中的POM文件是不是是不是添加了对应的插件了。
        目前爆出上面的措时，是由于这个工程没有添加这个插件：
        <plugin>  
            <groupId>org.apache.maven.plugins</groupId>  
            <artifactId>maven-war-plugin</artifactId>  
            <version>3.0.0</version>  
        </plugin> 
        
        对应的版本符合自己的要求就好
        
        插件可以手动也可以通过eclipse添加
        

2、maven工程中的POM文件中的packaging 值，对应的是打包的方式

        1.pom工程：用在父级工程或聚合工程中。用来做jar包的版本控制。
        2.war工程：将会打包成war，发布在服务器上的工程。如网站或服务。
        3.jar工程：将会打包成jar用作jar包使用。
        
3、构建项目部分

        <build>
            <finalName>helloworld</finalName>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.5.1</version>
                    <configuration>
                        <source>1.7</source>
                        <target>1.7</target>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>3.0.1</version>
                    <configuration>
                        <encoding>UTF-8</encoding>
                    </configuration>
                </plugin>
            </plugins>
        </build>
        
        build：项目构建时的配置
        finalName：在浏览器中的访问路径，如果将它改成helloworld，再执行maven--update，这时运行项目的访问路径是 
                    http://localhost:8080/helloworld/   而不是项目名的  http://localhost:8080/test
        plugins：插件，之前篇章已经说过，第一个插件是用来设置java版本为1.7，第二个插件是我刚加的，用来设置编码为utf-8
        group id+artifact id+version：插件在仓库中的坐标
        configuration：设置插件的参数值


4、Maven 的主要生命周期，被用于构建应用，包括下面的 23 个阶段：

http://c.biancheng.net/view/4899.html
https://www.cnblogs.com/EasonJim/p/6816340.html

        生命周期阶段	                            描述
        validate（校验）	                        校验项目是否正确并且所有必要的信息可以完成项目的构建过程。
        initialize（初始化）	                    初始化构建状态，比如设置属性值。
        generate-sources（生成源代码）	            生成包含在编译阶段中的任何源代码。
        process-sources（处理源代码）	            处理源代码，比如说，过滤任意值。
        generate-resources（生成资源文件）	        生成将会包含在项目包中的资源文件。
        process-resources （处理资源文件）	        复制和处理资源到目标目录，为打包阶段最好准备。
        compile（编译）	                            编译项目的源代码。
        process-classes（处理类文件）	            处理编译生成的文件，比如说对Java class文件做字节码改善优化。
        generate-test-sources（生成测试源代码）	    生成包含在编译阶段中的任何测试源代码。
        process-test-sources（处理测试源代码）	    处理测试源代码，比如说，过滤任意值。
        generate-test-resources（生成测试资源文件）	为测试创建资源文件。
        process-test-resources（处理测试资源文件）	复制和处理测试资源到目标目录。
        test-compile（编译测试源码）	            编译测试源代码到测试目标目录.
        process-test-classes（处理测试类文件）	    处理测试源码编译生成的文件。
        test（测试）	                            使用合适的单元测试框架运行测试（Juint是其中之一）。
        prepare-package（准备打包）	                在实际打包之前，执行任何的必要的操作为打包做准备。
        package（打包）	                            将编译后的代码打包成可分发格式的文件，比如JAR、WAR或者EAR文件。
        pre-integration-test（集成测试前）	        在执行集成测试前进行必要的动作。比如说，搭建需要的环境。
        integration-test（集成测试）	            处理和部署项目到可以运行集成测试环境中。
        post-integration-test（集成测试后）	        在执行集成测试完成后进行必要的动作。比如说，清理集成测试环境。
        verify （验证）	                            运行任意的检查来验证项目包有效且达到质量标准。
        install（安装）	                            安装项目包到本地仓库，这样项目包可以用作其他本地项目的依赖。
        deploy（部署）	                            将最终的项目包复制到远程仓库中与其他开发者和项目共享。
    


5、指定多个源代码目录、多个资源文件目录

https://blog.csdn.net/majian_1987/article/details/50971301

https://www.cnblogs.com/fluffy/p/5215065.html

https://blog.csdn.net/z69183787/article/details/48933467

https://my.oschina.net/u/930279/blog/614238

https://blog.csdn.net/u012849872/article/details/51035938

        <plugins>  
                 <!-- 指定多个源代码目录、多个资源文件目录 -->
               <plugin>
                 <groupId>org.codehaus.mojo</groupId>
                 <artifactId>build-helper-maven-plugin</artifactId>
                 <version>1.8</version>
                 <executions>
                   <execution>
                     <id>add-source</id>
                     <phase>generate-sources</phase>
                     <goals>
                       <goal>add-source</goal>
                     </goals>
                     <configuration>
                       <sources>
                         <source>src/java/main</source>
                         <source>src/java/generated</source>
                       </sources>
                     </configuration>
                   </execution>
                 </executions>
               </plugin> 
             </plugins>  
        </build>


git commit -m '添加多个源代码目录、多个资源文件目录配置'




6、maven跳过单元测试-maven.test.skip和skipTests的区别

        skipTests，不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下。
        maven.test.skip=true，不执行测试用例，也不编译测试用例类。
    
        可以在pom.xml文件中修改
    
        <plugin>  
            <groupId>org.apache.maven.plugin</groupId>  
            <artifactId>maven-compiler-plugin</artifactId>  
            <version>2.1</version>  
            <configuration>  
                <skip>true</skip>  
            </configuration>  
        </plugin>  
        <plugin>  
            <groupId>org.apache.maven.plugins</groupId>  
            <artifactId>maven-surefire-plugin</artifactId>  
            <version>2.5</version>  
            <configuration>  
                <skip>true</skip>  
            </configuration>  
        </plugin>


单独添加本地jar到maven库中
        
        demo： ojdbc6-11.2.0.3.jar
        打开终端（windows用户打开cmd），输入：
        mvn install:install-file -Dfile=D:\Program Files\myjar\ojdbc6-11.2.0.3.jar -DgroupId=oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -DgeneratePom=true
        pom引用
        <!-- https://mvnrepository.com/artifact/oracle/ojdbc6 -->
        <dependency>
            <groupId>oracle</groupId>
            <artifactId>ojdbc6</artifactId>
            <version>11.2.0.3</version>
        </dependency>


阿里云云效 是企业级一站式 DevOps 平台，覆盖产品从需求到运营的研发全生命周期，其中云效也提供了免费、可靠的Maven私有仓库 Packages

        仓库名称
        阿里云仓库地址
        阿里云仓库地址(老版)
        源地址
    
        central
        https://maven.aliyun.com/repository/central
        https://maven.aliyun.com/nexus/content/repositories/central
        https://repo1.maven.org/maven2/
        
        jcenter
        https://maven.aliyun.com/repository/public
        https://maven.aliyun.com/nexus/content/repositories/jcenter
        http://jcenter.bintray.com/
        
        public
        https://maven.aliyun.com/repository/public
        https://maven.aliyun.com/nexus/content/groups/public
        central仓和jcenter仓的聚合仓
        
        google
        https://maven.aliyun.com/repository/google
        https://maven.aliyun.com/nexus/content/repositories/google
        https://maven.google.com/
        
        gradle-plugin
        https://maven.aliyun.com/repository/gradle-plugin
        https://maven.aliyun.com/nexus/content/repositories/gradle-plugin
        https://plugins.gradle.org/m2/
        
        spring
        https://maven.aliyun.com/repository/spring
        https://maven.aliyun.com/nexus/content/repositories/spring
        http://repo.spring.io/libs-milestone/
        
        spring-plugin
        https://maven.aliyun.com/repository/spring-plugin
        https://maven.aliyun.com/nexus/content/repositories/spring-plugin
        http://repo.spring.io/plugins-release/
        
        grails-core
        https://maven.aliyun.com/repository/grails-core
        https://maven.aliyun.com/nexus/content/repositories/grails-core
        https://repo.grails.org/grails/core
        
        apache snapshots
        https://maven.aliyun.com/repository/apache-snapshots
        https://maven.aliyun.com/nexus/content/repositories/apache-snapshots
        https://repository.apache.org/snapshots/
        