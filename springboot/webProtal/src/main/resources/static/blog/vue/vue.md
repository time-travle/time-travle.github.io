#VUE 知识点
<a href="#" onclick="refreshVueContent('order')">Vue 使用到的命令</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshVueContent('question')">Vue 常见的问题</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshVueContent('vueuse')">Vue 的使用</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshVueContent('eslint')">Vue 中 eslint 的使用</a>&emsp;&emsp;&emsp;

---

#如何搭建Vue开发环境 ##下载node.js 
- 中文： http://nodejs.cn/download/
- 英文： https://nodejs.org/en/

下载之后进行安装，安装和正常的软件安装时一样的操作，之后检查是不是完成了安装，

在 cmd 控制台 运行命令 node -v命令 查看是不是安装成功， 
我们在进行安装node.js时，对应的nmp也是会进行同步安装的 在cmd控制台中输入nmp -v 查看对应的nmp是不是安装OK

##引入淘宝镜像 由于我们在国内使用国内的淘宝镜像响应速度是比较快的，
    执行命令 
    npm install -g cnpm --registry=http://registry.npm.taobao.org 安装淘宝镜像 cnpm ，
    其使用方法和nmp是一样的就是前面加个c
    
    命令中加-g是进行全局安装的意思
    
    后面的命令安装时 npm的都可以使用cnpm进行代替
    

设置缓存文件夹

    npm config set cache "D:\vueProject\nodejs\node_cache"

设置全局模块存放路径

    npm config set prefix "D:\vueProject\nodejs\node_global"

设置成功后，之后用命令npm install XXX -g安装以后模块就在D:\vueProject\nodejs\node_global里


##安装依赖 npm install

    升级版本 npm install npm -g

##安装 Webpack 
    npm install webpack -g

##安装脚手架 vue-cli 
    npm install --global vue-cli
    或者
    cnpm install --global vue-cli
    或者
    cnpm install -g vue-cli
    或者
    npm install @vue/cli -g

##安装路由 vue-router 
    npm install vue-router
    或者
    cnpm install vue-router

##组合安装mockjs+axios

###mockjs模拟后端数据结构输出 
    nmp install mockjs 
###异步编程框架axios 用来和后端接口做异步交互用的 
    npm install axios 
###mockjs可监听异步接口是不是可用，如果不可用则返回mock测试数据 
    npm install axios-mock-adapter

##初始化工程 vue init webpack vueproject_name
    vue init webpack test //test是项目名称

##运行终端 开发模式下运行 
    npm run dev

#参考链接
- https://www.cnblogs.com/nx520zj/p/9605184.html
- https://www.cnblogs.com/winter92/p/7117057.html
- http://baijiahao.baidu.com/s?id=1627353528960430110&wfr=spider&for=pc
- https://www.jianshu.com/p/ecb6cb98c7af
- https://blog.csdn.net/wjnf012/article/details/80422313
- https://www.cnblogs.com/zhaomeizi/p/8483597.html


参考学习wiki：

vue中axios的安装使用： https://www.cnblogs.com/liluning/p/12164787.html