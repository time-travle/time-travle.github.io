# VUE 知识点

<p>
<a href="#" onclick="refreshVueContent('order')">Vue 使用到的命令</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshVueContent('question')">Vue 常见的问题</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshVueContent('vueuse')">Vue 的使用</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshVueContent('eslint')">Vue 中 eslint 的使用</a>&emsp;&emsp;&emsp;
</p>

---

# 如何搭建Vue开发环境 ##下载node.js

- 中文： <a href="http://nodejs.cn/download/#" target="_blank">http://nodejs.cn/download/ </a>&emsp;&emsp;&emsp;
- 英文： <a href="https://nodejs.org/en/#" target="_blank">https://nodejs.org/en/ </a>&emsp;&emsp;&emsp;

下载之后进行安装，安装和正常的软件安装时一样的操作，之后检查是不是完成了安装，

在 cmd 控制台 运行命令 node -v命令 查看是不是安装成功， 安装好后命令行查看npm -v安装成功的话会显示版本号。

我们在进行安装node.js时，对应的nmp也是会进行同步安装的 在cmd控制台中输入nmp -v 查看对应的nmp是不是安装OK

## 引入淘宝镜像 由于我们在国内使用国内的淘宝镜像响应速度是比较快的，

    安装cnpm
    在命令行输入以下命令试着安装cnpm（注：“-g”这个参数意思是装到global目录下，也就是上面说设置的“D:\Program Files\nodejs\node_global”里面。）

    执行命令 
    npm install -g cnpm --registry=http://registry.npm.taobao.org 安装淘宝镜像 cnpm ，
    其使用方法和nmp是一样的就是前面加个c
    
    命令中加-g是进行全局安装的意思
    
    后面的命令安装时 npm的都可以使用cnpm进行代替

    安装完毕后可以看到.\node_global\node_modules\cnpm 已经有内容，之后cnpm -v查看是否安装成功。
    由于更改了默认值的 我们在执行cnpm -v 命令时会报错的，这是我们就要将cnpm的安路径配置到环境变量中才可以执行了

参考：<a href="https://blog.csdn.net/wjnf012/article/details/80422313" target="_blank"> npm和cnpm（windows）安装步骤 </a>

设置缓存文件夹

    npm config set cache "D:\vueProject\nodejs\node_cache"

设置全局模块存放路径

    npm config set prefix "D:\vueProject\nodejs\node_global"

设置成功后，之后用命令npm install XXX -g安装以后模块就在D:\vueProject\nodejs\node_global里

在系统环境变量添加系统变量NODE_PATH，输入路径D:\Program Files\nodejs\node_global\node_modules，此后所安装的模块都会安装到改路径下

## 安装依赖 npm install

    升级版本 npm install npm -g

## 安装 Webpack

    npm install webpack -g

## 安装脚手架 vue-cli

    npm install --global vue-cli
    或者
    cnpm install --global vue-cli
    或者
    cnpm install -g vue-cli
    或者
    npm install @vue/cli -g

## 安装路由 vue-router

    npm install vue-router
    或者
    cnpm install vue-router

## 组合安装mockjs+axios

### mockjs模拟后端数据结构输出

    nmp install mockjs 

### 异步编程框架axios 用来和后端接口做异步交互用的

    npm install axios
    npm install axios --save
    npm install vue-axios --save

### mockjs可监听异步接口是不是可用，如果不可用则返回mock测试数据

    npm install axios-mock-adapter

## 初始化工程 vue init webpack vueproject_name

    vue init webpack vueproject_name
    vue init webpack test //test是项目名称

## 运行终端 开发模式下运行

    npm run dev

## npm命令

    npm -v 来测试是否成功安装
    查看当前目录已安装插件：npm list
    更新全部插件： npm update [ --save-dev ]
    使用 npm 更新对应插件： npm update <name> [ -g ] [ --save-dev]
    使用 npm 卸载插件： npm uninstall <name> [ -g ] [ --save-dev ]

## cnpm 命令

    淘宝团队做的国内镜像，因为npm的服务器位于国外可能会影响安装。淘宝镜像与官方同步频率目前为 10分钟 一次以保证尽量与官方服务同步。
    安装：命令提示符执行
    npm install cnpm -g --registry=https://registry.npm.taobao.org
    cnpm -v 来测试是否成功安装

## 通过改变地址来使用淘宝镜像

    npm的默认地址是https://registry.npmjs.org/
    可以使用npm config get registry查看npm的仓库地址
    可以使用npm config set registry https://registry.npm.taobao.org来改变默认下载地址，达到可以不安装cnpm就能采用淘宝镜像的目的，然后使用上面的get命令查看是否成功。

参考：<a href="https://www.jianshu.com/p/115594f64b41" target="_blank">npm 和 cnpm </a>

## 参数 -g -S -D 说明

    -g：全局安装。 将会安装在C：\ Users \ Administrator \ AppData \ Roaming \ npm，并且写入系统环境变量；非全局安装：将会安装在当前定位目录;全局安装可以通过命令行任何地方调用它，本地安装将安装在定位目录的node_modules文件夹下，通过要求调用;
    -S：即npm install module_name --save,写入package.json的dependencies ,dependencies 是需要发布到生产环境的，比如jq，vue全家桶，ele-ui等ui框架这些项目运行时必须使用到的插件就需要放到dependencies
    -D：即npm install module_name --save-dev,写入package.json的devDependencies ,devDependencies 里面的插件只用于开发环境，不用于生产环境。比如一些babel编译功能的插件、webpack打包插件就是开发时候的需要，真正程序打包跑起来并不需要的一些插件。

## 每一个 Vuex 应用的核心就是 store（仓库）。

    “store”基本上就是一个容器，它包含着你的应用中大部分的状态 (state)。Vuex 和单纯的全局对象有以下两点不同：

    Vuex 的状态存储是响应式的。当 Vue 组件从 store 中读取状态的时候，若 store 中的状态发生变化，那么相应的组件也会相应地得到高效更新。
    
    你不能直接改变 store 中的状态。改变 store 中的状态的唯一途径就是显式地提交 (commit) mutation。
        这样使得我们可以方便地跟踪每一个状态的变化，从而让我们能够实现一些工具帮助我们更好地了解我们的应用

Vuex : <a href="https://vuex.vuejs.org/zh/guide/" target="_blank">官网指导文档 </a>

# 参考链接

- <a href="https://www.cnblogs.com/nx520zj/p/9605184.html" target="_blank">https://www.cnblogs.com/nx520zj/p/9605184.html </a>
  &emsp;&emsp;&emsp;
- <a href="https://www.cnblogs.com/winter92/p/7117057.html#" target="_blank">https://www.cnblogs.com/winter92/p/7117057.html </a>
  &emsp;&emsp;&emsp;
- <a href="http://baijiahao.baidu.com/s?id=1627353528960430110&wfr=spider&for=pc#" target="_blank">http://baijiahao.baidu.com/s?id=1627353528960430110&wfr=spider&for=pc </a>
  &emsp;&emsp;&emsp;
- <a href="https://www.jianshu.com/p/ecb6cb98c7af#" target="_blank">https://www.jianshu.com/p/ecb6cb98c7af </a>
  &emsp;&emsp;&emsp;
- <a href="https://blog.csdn.net/wjnf012/article/details/80422313#" target="_blank">https://blog.csdn.net/wjnf012/article/details/80422313 </a>
  &emsp;&emsp;&emsp;
- <a href="https://www.cnblogs.com/zhaomeizi/p/8483597.html" target="_blank">https://www.cnblogs.com/zhaomeizi/p/8483597.html </a>
  &emsp;&emsp;&emsp;

参考学习wiki：

vue中axios的安装使用： <a href="https://www.cnblogs.com/liluning/p/12164787.html" target="_blank">https://www.cnblogs.com/liluning/p/12164787.html </a>
&emsp;&emsp;&emsp;